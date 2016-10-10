package com.alicesfavs.sitescraper.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.sitescraper.extractspec.ProductDetailExtractSpec;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.datamodel.MultirootTree.Node;
import com.alicesfavs.sitescraper.SiteScrapeException;
import com.alicesfavs.sitescraper.SiteScraper;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.NextPageExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductExtractSpec;

@Component("siteScraper")
public class SiteScraperImpl implements SiteScraper
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScraperImpl.class);

    private static final int MAX_NEXT_PAGE_LOOP = 100;

    private DataExtractor dataExtractor = new DataExtractor();

    private WebDriver webDriver;

    @Override
    public void setWebDriver(WebDriver webDriver)
    {
        this.webDriver = webDriver;
    }

    @Override
    public MultirootTree<CategoryExtract> extractCategories(Site site,
            List<CategoryExtractSpec> categoryExtractSpecList) throws SiteScrapeException
    {
        final MultirootTree<CategoryExtract> categoryTree = new MultirootTree<>();
        for (CategoryExtractSpec categorySpec : categoryExtractSpecList)
        {
            try
            {
                extractCategories(site, categorySpec, categoryTree);
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                LOGGER.warn(e + " - Not found for category spec " + categorySpec, e);
                // if not found, then try next
            }
        }

        return categoryTree;
    }

    private void extractCategories(Site site, CategoryExtractSpec categoryExtractSpec,
            MultirootTree<CategoryExtract> categoryTree) throws SiteScrapeException, ElementNotFoundException,
            DataNotFoundException
    {
        CategoryExtractSpec leafCategorySpec = categoryExtractSpec;
        final List<CategoryExtract> leafCategories = extractCategories(site, site.url, leafCategorySpec);
        List<Node<CategoryExtract>> leafNodes = toNodeList(leafCategories, null);
        categoryTree.roots.addAll(leafNodes);

        while (leafCategorySpec.subcategorySpec != null)
        {
            leafCategorySpec = leafCategorySpec.subcategorySpec;
            final List<Node<CategoryExtract>> parentNodes = categoryTree.getLeafNodesOf(leafNodes);
            leafNodes = parentNodes;
            for (Node<CategoryExtract> parentNode : parentNodes)
            {
                final List<CategoryExtract> extracts = extractCategories(site, parentNode.data.url, leafCategorySpec);
                addAll(parentNode.children, extracts, parentNode);
            }
        }
    }

    private List<Node<CategoryExtract>> toNodeList(List<CategoryExtract> extractList, Node<CategoryExtract> parent)
    {
        final List<Node<CategoryExtract>> nodeList = new ArrayList<>();
        for (CategoryExtract c : extractList)
        {
            nodeList.add(new Node<>(c, parent));
        }

        return nodeList;
    }

    private void addAll(List<Node<CategoryExtract>> nodeList, List<CategoryExtract> extractList,
            Node<CategoryExtract> parent)
    {
        for (CategoryExtract c : extractList)
        {
            nodeList.add(new Node<>(c, parent));
        }
    }

    @Override
    public List<ProductExtract> extractProducts(Site site, CategoryExtract categoryExtract,
            List<ProductExtractSpec> productExtractSpecList, List<NextPageExtractSpec> nextPageExtractSpecList)
            throws SiteScrapeException
    {
        String url = categoryExtract.url;
        int loopCount = 0;
        final List<ProductExtract> productList = new ArrayList<>();
        final boolean scrollDown = nextPageExtractSpecList.size() == 0;
        while (StringUtils.hasText(url) && loopCount++ < MAX_NEXT_PAGE_LOOP)
        {
            final Document doc = openUrl(site, url, true, scrollDown);
            if (doc == null)
            {
                break;
            }
            productList.addAll(extractProducts(doc, productExtractSpecList));
            url = extractNextPageUrl(doc, nextPageExtractSpecList);
        }

        return productList;
    }

    @Override
    public void extractProductDetail(Site site, ProductExtract productExtract,
        List<ProductDetailExtractSpec> productDetailExtractSpecList) throws SiteScrapeException
    {
        final Document doc = openUrl(site, productExtract.url, false, false);
        if (doc != null)
        {
            for (ProductDetailExtractSpec productDetailExtractSpec : productDetailExtractSpecList)
            {
                dataExtractor.extractProductDetail(doc, productDetailExtractSpec, productExtract);
            }
        }
    }

    private List<CategoryExtract> extractCategories(Site site, String pageUrl, CategoryExtractSpec categoryExtractSpec)
            throws SiteScrapeException, ElementNotFoundException, DataNotFoundException
    {
        String openUrl = StringUtils.hasText(categoryExtractSpec.baseUrl) ? categoryExtractSpec.baseUrl : pageUrl;
        final Document doc = openUrl(site, openUrl, false, false);
        if (doc != null)
        {
            return dataExtractor.extractCategories(doc, categoryExtractSpec);
        }
        else
        {
            return new ArrayList<>();
        }
    }

    private List<ProductExtract> extractProducts(Document document, List<ProductExtractSpec> productExtractSpecList)
    {
        final List<ProductExtract> productList = new ArrayList<>();
        for (ProductExtractSpec productExtractSpec : productExtractSpecList)
        {
            try
            {
                productList.addAll(dataExtractor.extractProducts(document, productExtractSpec));
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // if not found, then try next
            }
        }

        return productList;
    }

    private String extractNextPageUrl(Document document, List<NextPageExtractSpec> nextPageExtractSpecList)
    {
        for (NextPageExtractSpec nextPageExtractSpec : nextPageExtractSpecList)
        {
            try
            {
                final String nextPageUrl = dataExtractor.extractNextPageUrl(document, nextPageExtractSpec);
                if (nextPageUrl.matches(".*/[a-zA-Z0-9]+&goToPage=2"))
                {
                    return nextPageUrl.replace("&goToPage=2", "?&goToPage=2");
                }
                else
                {
                    return nextPageUrl;
                }
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // do nothing
            }
        }
        return null;
    }

    private Document openUrl(Site site, String url, boolean useWebDriver, boolean scrollDown) throws SiteScrapeException
    {
        int tryCount = 0;
        while (true)
        {
            try
            {
                return openUrl(url, 90 * 1000, "Alice's Favs SmartCrawler", site.cookies, useWebDriver, scrollDown);
            }
            catch (SocketTimeoutException e)
            {
                tryCount++;
                LOGGER.error("Timeout error on " + url, e);

                // retry on timeout exception
                if (tryCount >= 3)
                {
                    throw new SiteScrapeException("Failed to connect: " + url, e);
                }
                sleep(10 * 1000);
                continue;
            }
            catch (IOException e)
            {
                throw new SiteScrapeException("Failed to connect: " + url, e);
            }
        }
    }

    private Document openUrl(String url, int timeout, String userAgent, String cookies,
        boolean useWebDriver, boolean scrollDown) throws IOException
    {
        try
        {
            LOGGER.debug("Opening " + url);
            if (useWebDriver && webDriver != null)
            {
                webDriver.manage().window().maximize();
                webDriver.get(url);

                final String pageSource;
                if (scrollDown)
                {
                    pageSource = scrollDown(MAX_NEXT_PAGE_LOOP, 2000);
                }
                else
                {
                    pageSource = scrollDown(1, 2000);
                }

                //final String pageSource = webDriver.getPageSource();
                final Document document = Jsoup.parse(pageSource);
                document.setBaseUri(url);
                return document;
            }
            else
            {
                Connection conn = Jsoup.connect(url).timeout(timeout).userAgent(userAgent);
                if (StringUtils.hasText(cookies))
                {
                    conn.cookies(getCookieMap(cookies));
                }

                return conn.get();
            }
        }
        catch (HttpStatusException e)
        {
            // ignore and move forward to next
            LOGGER.error("Http Status Code " + e.getStatusCode() + " in opening " + url, e);
            return null;
        }
    }

    private String scrollDown(int count, int sleepMilliseconds)
    {
        String pageSource = null;
        final int MINIMUM_DIFFERENCE = 10;
        int pageSourceLength = 0;
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        for (int index = 0 ; index < count ; index++)
        {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            sleep(sleepMilliseconds);
            pageSource = webDriver.getPageSource();
            final int newPageSourceLength = pageSource.length();
            if (newPageSourceLength - pageSourceLength > MINIMUM_DIFFERENCE)
            {
                pageSourceLength = newPageSourceLength;
            }
            else
            {
                break;
            }
        }
        return pageSource;
    }

    private void sleep(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch (InterruptedException e)
        {}
    }

    private Map<String, String> getCookieMap(String cookies)
    {
        final Map<String, String> cookieMap = new HashMap<>();
        for (String cookie : cookies.split(";"))
        {
            final String[] keyValue = cookie.split("=", 2);
            cookieMap.put(keyValue[0].trim(), keyValue[1].trim());
        }

        return cookieMap;
    }
}

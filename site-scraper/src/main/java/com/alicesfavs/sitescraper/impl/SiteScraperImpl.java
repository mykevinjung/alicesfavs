package com.alicesfavs.sitescraper.impl;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
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

    private DataExtractor dataExtractor = new DataExtractor();

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
        final List<ProductExtract> productList = new ArrayList<>();
        while (StringUtils.hasText(url))
        {
            final Document doc = openUrl(site, url);
            if (doc == null)
            {
                break;
            }
            productList.addAll(extractProducts(doc, productExtractSpecList));
            url = extractNextPageUrl(doc, nextPageExtractSpecList);
        }

        return productList;
    }

    private List<CategoryExtract> extractCategories(Site site, String pageUrl, CategoryExtractSpec categoryExtractSpec)
            throws SiteScrapeException, ElementNotFoundException, DataNotFoundException
    {
        final Document doc = openUrl(site, pageUrl);
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
                return dataExtractor.extractNextPageUrl(document, nextPageExtractSpec);
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // do nothing
            }
        }
        return null;
    }

    private Document openUrl(Site site, String url) throws SiteScrapeException
    {
        int tryCount = 0;
        while (true)
        {
            try
            {
                return openUrl(url, 90 * 1000, "Alice's Favs SmartCrawler", site.cookies);
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
                try
                {
                    Thread.sleep(10 * 1000);
                }
                catch (InterruptedException e1)
                {
                    // ignore
                }
                continue;
            }
            catch (IOException e)
            {
                throw new SiteScrapeException("Failed to connect: " + url, e);
            }
        }
    }

    private Document openUrl(String url, int timeout, String userAgent, String cookies) throws IOException
    {
        try
        {
            LOGGER.debug("Opening " + url);
            Connection conn = Jsoup.connect(url).timeout(timeout).userAgent(userAgent);
            if (StringUtils.hasText(cookies))
            {
                conn.cookies(getCookieMap(cookies));
            }

            return conn.get();
        }
        catch (HttpStatusException e)
        {
            // ignore and move forward to next
            LOGGER.error("Http Status Code " + e.getStatusCode() + " in opening " + url, e);
            return null;
        }
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

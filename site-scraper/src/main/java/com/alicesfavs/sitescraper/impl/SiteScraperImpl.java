package com.alicesfavs.sitescraper.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public List<CategoryExtract> extractLeafCategories(Site site, List<CategoryExtractSpec> categoryExtractSpecList)
            throws SiteScrapeException
    {
        List<CategoryExtract> categories = new ArrayList<CategoryExtract>();
        for (CategoryExtractSpec categoryExtractSpec : categoryExtractSpecList)
        {
            try
            {
                categories.addAll(extractLeafCategories(site, categoryExtractSpec));
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                // if not found, then try next
            }
        }

        return categories;
    }

    @Override
    public MultirootTree<CategoryExtract> extractCategories(String homeUrl,
            List<CategoryExtractSpec> categoryExtractSpecList) throws SiteScrapeException
    {
        final MultirootTree<CategoryExtract> categoryTree = new MultirootTree<CategoryExtract>();
        for (CategoryExtractSpec categorySpec : categoryExtractSpecList)
        {
            try
            {
                extractCategories(homeUrl, categorySpec, categoryTree);
            }
            catch (ElementNotFoundException | DataNotFoundException e)
            {
                LOGGER.warn(e + " - Not found for category spec " + categorySpec, e);
                // if not found, then try next
            }
        }

        return categoryTree;
    }

    private void extractCategories(String siteUrl, CategoryExtractSpec categoryExtractSpec,
            MultirootTree<CategoryExtract> categoryTree) throws SiteScrapeException, ElementNotFoundException,
            DataNotFoundException
    {
        CategoryExtractSpec leafCategorySpec = categoryExtractSpec;
        final List<CategoryExtract> leafCategories = extractCategories(siteUrl, leafCategorySpec);
        final List<Node<CategoryExtract>> leafNodes = toNodeList(leafCategories, null);
        categoryTree.roots.addAll(leafNodes);

        List<Node<CategoryExtract>> parentNodes = null;
        while (leafCategorySpec.subcategorySpec != null)
        {
            leafCategorySpec = leafCategorySpec.subcategorySpec;
            parentNodes = leafNodes;
            for (Node<CategoryExtract> parentNode : parentNodes)
            {
                final List<CategoryExtract> extracts = extractCategories(parentNode.data.url, leafCategorySpec);
                addAll(parentNode.children, extracts, parentNode);
            }
        }
    }

    private List<Node<CategoryExtract>> toNodeList(List<CategoryExtract> extractList, Node<CategoryExtract> parent)
    {
        final List<Node<CategoryExtract>> nodeList = new ArrayList<Node<CategoryExtract>>();
        for (CategoryExtract c : extractList)
        {
            nodeList.add(new Node<CategoryExtract>(c, parent));
        }

        return nodeList;
    }

    private void addAll(List<Node<CategoryExtract>> nodeList, List<CategoryExtract> extractList,
            Node<CategoryExtract> parent)
    {
        for (CategoryExtract c : extractList)
        {
            nodeList.add(new Node<CategoryExtract>(c, parent));
        }
    }

    @Override
    public List<ProductExtract> extractProducts(CategoryExtract categoryExtract,
            List<ProductExtractSpec> productExtractSpecList, List<NextPageExtractSpec> nextPageExtractSpecList)
            throws SiteScrapeException
    {
        String url = categoryExtract.url;
        final List<ProductExtract> productList = new ArrayList<ProductExtract>();
        while (StringUtils.hasText(url))
        {
            Document doc = openUrl(url);
            productList.addAll(extractProducts(doc, productExtractSpecList));
            url = extractNextPageUrl(doc, nextPageExtractSpecList);
        }

        return productList;
    }

    @Override
    public List<ProductExtract> extractProducts(String pageUrl, List<ProductExtractSpec> productExtractSpecList)
            throws SiteScrapeException
    {
        Document doc = openUrl(pageUrl);
        return extractProducts(doc, productExtractSpecList);
    }

    private List<CategoryExtract> extractLeafCategories(Site site, CategoryExtractSpec categoryExtractSpec)
            throws SiteScrapeException, ElementNotFoundException, DataNotFoundException
    {
        // TODO use Set to avoid duplicate categories!!!!!!!!!
        final String url = "http://" + site.domain;
        CategoryExtractSpec leafCategorySpec = categoryExtractSpec;
        List<CategoryExtract> leafCategories = extractCategories(url, categoryExtractSpec);

        List<CategoryExtract> parentCategories = null;
        while (leafCategorySpec.subcategorySpec != null)
        {
            leafCategorySpec = leafCategorySpec.subcategorySpec;
            parentCategories = leafCategories;
            leafCategories = new ArrayList<CategoryExtract>();
            for (CategoryExtract category : parentCategories)
            {
                leafCategories.addAll(extractCategories(category.url, leafCategorySpec));
            }
        }

        return leafCategories;
    }

    private List<CategoryExtract> extractCategories(String pageUrl, CategoryExtractSpec categoryExtractSpec)
            throws SiteScrapeException, ElementNotFoundException, DataNotFoundException
    {
        final Document doc = openUrl(pageUrl);
        return dataExtractor.extractCategories(doc, categoryExtractSpec);
    }

    private List<ProductExtract> extractProducts(Document document, List<ProductExtractSpec> productExtractSpecList)
    {
        final List<ProductExtract> productList = new ArrayList<ProductExtract>();
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

    private Document openUrl(String url) throws SiteScrapeException
    {
        try
        {
            // System.out.println("Opening " + url);
            LOGGER.debug("Opening {}", url);
            return Jsoup.connect(url).timeout(90 * 1000).get();
        }
        catch (IOException e)
        {
            throw new SiteScrapeException("Failed to connect: " + url, e);
        }
    }

}
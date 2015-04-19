package com.alicesfavs;

import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.MultirootTree.Node;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.sitescraper.SiteScraper;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.DataExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ElementDataSpec;
import com.alicesfavs.sitescraper.extractspec.ElementExtractSpec;
import com.alicesfavs.sitescraper.extractspec.NextPageExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductExtractSpec;
import com.alicesfavs.sitescraper.impl.SiteScraperImpl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Hello world!
 *
 */
public class SiteScraperApp
{
    public static void main(String[] args) throws Exception
    {
        Site site = new Site(1, LocalDateTime.now(), "anthropologie");
        site.domain = "www.anthropologie.com";

        // TODO Select lower price when there are two prices!!!!!!!!!!!!!

        // test2();
        readSite(site);
        // extractProducts(site, "https://www.jcrew.com/girls_category/shoes.jsp?iNextCategory=1");
        // test();
        // readAnthropologies();
        // readBoden();
        // readJcrew();
        // readNordStrom();
    }

    private static void test2()
    {
        Pattern pattern = Pattern.compile(".*(\\$[0-9]+\\.[0-9][0-9]).*");
        Matcher matcher = pattern.matcher("afsd$94.21asdf");
        if (matcher.find())
        {
            System.out.println(matcher.group(1));
        }
        else
        {
            System.out.println("not found");
        }

    }

    private static void readSite(Site site) throws Exception
    {
        System.out.println("-------------------------------------------------------------");
        long start = System.currentTimeMillis();
        String categorySpecPath = getSpecPath(site, "category_spec.json");
        String nextpageSpecPath = getSpecPath(site, "nextpage_spec.json");
        String productSpecPath = getSpecPath(site, "product_spec.json");
        ObjectMapper mapper = new ObjectMapper();

        List<CategoryExtractSpec> categoryExtractSpecList = mapper.readValue(new File(categorySpecPath), mapper
                .getTypeFactory().constructCollectionType(List.class, CategoryExtractSpec.class));

        List<NextPageExtractSpec> nextPageExtractSpecList = mapper.readValue(new File(nextpageSpecPath), mapper
                .getTypeFactory().constructCollectionType(List.class, NextPageExtractSpec.class));

        List<ProductExtractSpec> productExtractSpecList = mapper.readValue(new File(productSpecPath), mapper
                .getTypeFactory().constructCollectionType(List.class, ProductExtractSpec.class));

        readSite(site, categoryExtractSpecList, nextPageExtractSpecList, productExtractSpecList);

        System.out.println("total time: " + (System.currentTimeMillis() - start));
    }

    private static void readSite(Site site, List<CategoryExtractSpec> categoryExtractSpecList,
            List<NextPageExtractSpec> nextPageExtractSpecList, List<ProductExtractSpec> productExtractSpecList)
            throws Exception
    {
        int count = 0;
        SiteScraper siteScraper = new SiteScraperImpl();
        final String homeUrl = "http://" + site.domain;
        final MultirootTree<CategoryExtract> categoryTree = siteScraper.extractCategories(homeUrl,
                categoryExtractSpecList);
        printCategoryTree(categoryTree.roots);
        System.out.println("Total Category: " + categoryCount);
        System.out.println("Total Product: " + count);
    }

    private static int categoryCount = 0;
    
    private static void printCategoryTree(List<Node<CategoryExtract>> nodeList)
    {
        for (final Node<CategoryExtract> categoryNode : nodeList)
        {
            if (categoryNode.isLeafNode())
            {
                final List<CategoryExtract> categoryHierarchy = toCategoryHierarchy(categoryNode);
                printCategoryHierarchy(categoryHierarchy);
                categoryCount++;
            }
            else
            {
                printCategoryTree(categoryNode.children);
            }
        }
    }

    private static void printCategoryHierarchy(List<CategoryExtract> categoryHierarchy)
    {
        System.out.print("CategoryHierarchy");
        for (int index = 0; index < categoryHierarchy.size(); index++)
        {
            if (index == categoryHierarchy.size() - 1)
            {
                System.out.println(" " + categoryHierarchy.get(index));
            }
            else
            {
                System.out.print(" " + categoryHierarchy.get(index));
            }
        }
    }

    private static List<CategoryExtract> toCategoryHierarchy(Node<CategoryExtract> leafCategoryNode)
    {
        final List<CategoryExtract> extracts = new ArrayList<CategoryExtract>();
        Node<CategoryExtract> node = leafCategoryNode;
        while (node != null)
        {
            extracts.add(0, node.data);
            node = node.parent;
        }

        return extracts;
    }

    private static void extractProducts(Site site, String pageUrl) throws Exception
    {
        String productSpecPath = getSpecPath(site, "product_spec.json");
        ObjectMapper mapper = new ObjectMapper();
        List<ProductExtractSpec> productExtractSpecList = mapper.readValue(new File(productSpecPath), mapper
                .getTypeFactory().constructCollectionType(List.class, ProductExtractSpec.class));
        SiteScraper siteScraper = new SiteScraperImpl();
        List<ProductExtract> productExtractList = siteScraper.extractProducts(pageUrl, productExtractSpecList);
        System.out.println("Total Found: " + productExtractList.size());
    }

    public static void readNordStrom() throws Exception
    {
        // System.out
        // .println("--------------------------------------------------------------------------------------------------------------------");
        // final CategoryExtractInfo categoryExtractInfo = new CategoryExtractInfo();
        // categoryExtractInfo.containerInfo.tagId = "primary-nav";
        // categoryExtractInfo.urlInfo.urlPattern = "http://shop.nordstrom.com/c/.*\\?dept=8000001&origin=topnav";
        //
        // Site site = new Site();
        // site.domain = "www.nordstrom.com";
        // List<CategoryExtractInfo> categoryExtractInfoList = new ArrayList<CategoryExtractInfo>();
        // categoryExtractInfoList.add(categoryExtractInfo);
        //
        // readSite(site, categoryExtractInfoList, null);
    }

    public static void readBoden() throws Exception
    {
        // System.out
        // .println("--------------------------------------------------------------------------------------------------------------------");
        // final CategoryExtractInfo categoryExtractInfo = new CategoryExtractInfo();
        // categoryExtractInfo.containerInfo.tagClass = "dMenu";
        // categoryExtractInfo.urlInfo.urlPattern = ".*/en-US/.*\\.html#nav";
        //
        // ProductExtractInfo productExtractInfo = new ProductExtractInfo();
        // productExtractInfo.containerInfo.tagClass = "product-item";
        // productExtractInfo.urlInfo.urlPattern = ".*/en-US/.*\\.html";
        //
        // Site site = new Site();
        // site.domain = "www.bodenusa.com";
        // List<CategoryExtractInfo> categoryExtractInfoList = new ArrayList<CategoryExtractInfo>();
        // categoryExtractInfoList.add(categoryExtractInfo);
        //
        // readSite(site, categoryExtractInfoList, null, productExtractInfo);
    }

    private static void readPage(Site site, String url) throws Exception
    {
        System.out.println("-------------------------------------------------------------");
        long start = System.currentTimeMillis();
        String productSpecPath = getSpecPath(site, "product_spec.json");
        ObjectMapper mapper = new ObjectMapper();

        List<ProductExtractSpec> productExtractSpecList = mapper.readValue(new File(productSpecPath), mapper
                .getTypeFactory().constructCollectionType(List.class, ProductExtractSpec.class));

        SiteScraper siteScraper = new SiteScraperImpl();
        List<ProductExtract> products = siteScraper.extractProducts(url, productExtractSpecList);
        System.out.println("Total Found: " + products.size());

        System.out.println("total time: " + (System.currentTimeMillis() - start));
    }

    private static String getSpecPath(Site site, String fileName)
    {
        String siteRoot = "/Users/kjung/src/sungmuk/alicesfavs/site-scrape-batch/src/main/resources/extractspec/";
        return siteRoot + site.stringId + "/" + site.stringId + "_" + fileName;
    }

    private static void writeProductJson() throws Exception
    {
        ProductExtractSpec spec = new ProductExtractSpec();
        spec.containerSpec = new ElementExtractSpec();
        spec.productSpec = new ElementExtractSpec();
        spec.nameSpec = new ElementDataSpec();
        spec.nameSpec.containerSpec = new ElementExtractSpec();
        spec.nameSpec.elementSpec = new ElementExtractSpec();
        spec.nameSpec.dataSpec = new DataExtractSpec();
        spec.urlSpec = new ElementDataSpec();
        spec.urlSpec.containerSpec = new ElementExtractSpec();
        spec.urlSpec.elementSpec = new ElementExtractSpec();
        spec.urlSpec.dataSpec = new DataExtractSpec();
        spec.priceSpecList = new ArrayList<ElementDataSpec>();
        ElementDataSpec priceSpec = new ElementDataSpec();
        ElementDataSpec priceSpec2 = new ElementDataSpec();
        spec.priceSpecList.add(priceSpec);
        spec.priceSpecList.add(priceSpec2);
        priceSpec.containerSpec = new ElementExtractSpec();
        priceSpec.elementSpec = new ElementExtractSpec();
        priceSpec.dataSpec = new DataExtractSpec();
        priceSpec2.containerSpec = new ElementExtractSpec();
        priceSpec2.elementSpec = new ElementExtractSpec();
        priceSpec2.dataSpec = new DataExtractSpec();

        List<ProductExtractSpec> productExtractSpecList = new ArrayList<ProductExtractSpec>();
        productExtractSpecList.add(spec);
        productExtractSpecList.add(spec);

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("json: " + mapper.writeValueAsString(productExtractSpecList));
    }

    private static void writeNextPageJson() throws Exception
    {
        NextPageExtractSpec spec = new NextPageExtractSpec();
        spec.nextPageSpec = new ElementDataSpec();
        spec.nextPageSpec.containerSpec = new ElementExtractSpec();
        spec.nextPageSpec.elementSpec = new ElementExtractSpec();
        spec.nextPageSpec.dataSpec = new DataExtractSpec();

        ObjectMapper mapper = new ObjectMapper();
        System.out.println("json: " + mapper.writeValueAsString(spec));
    }

    private static void test()
    {
        String url = "http://www.anthropologie.com/anthro/product/shopgifts-price/S27735307.jsp";
        Pattern pattern = Pattern.compile("/([0-9]*?).jsp");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find())
        {
            url = matcher.group(0);
            url = matcher.group(1);
            url = matcher.group(2);
            url = matcher.group(3);
        }
        System.out.println("urL" + url);
    }

}

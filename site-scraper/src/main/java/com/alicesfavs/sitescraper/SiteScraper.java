package com.alicesfavs.sitescraper;

import java.util.List;

import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.NextPageExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductExtractSpec;

public interface SiteScraper
{

    /**
     * 
     * @param homeUrl
     * @param categoryExtractSpecList
     * @return
     * @throws SiteScrapeException
     */
    MultirootTree<CategoryExtract> extractCategories(String homeUrl, List<CategoryExtractSpec> categoryExtractSpecList)
            throws SiteScrapeException;

    /**
     * Extract all products of a given category using product extract spec list in all pages of the category by
     * navigating next page in the category using next page extract spec
     * 
     * @param categoryExtract
     * @param productExtractSpecList
     * @param nextPageExtractSpecList
     * @return
     * @throws SiteScrapeException
     */
    List<ProductExtract> extractProducts(CategoryExtract categoryExtract,
            List<ProductExtractSpec> productExtractSpecList, List<NextPageExtractSpec> nextPageExtractSpecList)
            throws SiteScrapeException;

    /**
     * Extract all products on a page using product extract spec list
     * 
     * @param pageUrl
     * @param productExtractSpecList
     * @return
     * @throws SiteScrapeException
     */
    List<ProductExtract> extractProducts(String pageUrl, List<ProductExtractSpec> productExtractSpecList)
            throws SiteScrapeException;

}

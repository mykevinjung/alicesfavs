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

    MultirootTree<CategoryExtract> extractCategories(Site site, List<CategoryExtractSpec> categoryExtractSpecList)
        throws SiteScrapeException;

    /**
     * Extract all products of a given category using product extract spec list in all pages of the category by
     * navigating next page in the category using next page extract spec
     */
    List<ProductExtract> extractProducts(Site site, CategoryExtract categoryExtract,
        List<ProductExtractSpec> productExtractSpecList, List<NextPageExtractSpec> nextPageExtractSpecList)
        throws SiteScrapeException;

}

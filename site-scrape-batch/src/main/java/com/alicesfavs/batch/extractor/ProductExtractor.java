package com.alicesfavs.batch.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.batch.BatchConfig;
import com.alicesfavs.datamodel.*;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.sitescraper.SiteScrapeException;
import com.alicesfavs.sitescraper.SiteScraper;

@Component("productExtractor")
public class ProductExtractor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExtractor.class);

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private SiteScraper siteScraper;

    public Map<String, List<ProductExtract>> extractProduct(Site site) throws ExtractException
    {
        final MultirootTree<CategoryExtract> categoryExtracts = extractCategory(site);
        final List<MultirootTree.Node<CategoryExtract>> leafCategories = categoryExtracts.getAllLeafNodes();

        // same product is displayed in many categories
        // let's group them and save as one product and link
        final Map<String, List<ProductExtract>> productExtractMap = new HashMap<>();
        categoryExtracts.getAllLeafNodes();
        for (final MultirootTree.Node<CategoryExtract> category : leafCategories)
        {
            final List<ProductExtract> peList = extractCategoryProducts(site, category);
            buildProductExtractMap(productExtractMap, peList);
        }

        return productExtractMap;
    }

    private MultirootTree<CategoryExtract> extractCategory(Site site) throws ExtractException
    {
        try
        {
            final List<CategoryExtractSpec> categoryExtractSpecList = batchConfig.getCategoryExtractSpec(site);
            return siteScraper.extractCategories(site, categoryExtractSpecList);
        }
        catch (Exception e)
        {
            throw new ExtractException("Error in extracting categories", e);
        }
    }

    private List<ProductExtract> extractCategoryProducts(Site site,
        MultirootTree.Node<CategoryExtract> category)
        throws ExtractException
    {
        try
        {
            LOGGER.info("Extracting products for category " + category.data.url);
            return siteScraper.extractProducts(site, category.data, batchConfig.getProductExtractSpec(site),
                batchConfig.getNextPageExtractSpec(site));
        }
        catch (IOException e)
        {
            throw new ExtractException("Error in getting category extract spec", e);
        }
        catch (SiteScrapeException e)
        {
            throw new ExtractException("Error in extracting categories", e);
        }
    }

    private void buildProductExtractMap(Map<String, List<ProductExtract>> peMap, List<ProductExtract> peList)
    {
        if (peList == null || peList.size() == 0)
        {
            return;
        }

        for (ProductExtract pe : peList)
        {
            List<ProductExtract> peListInMap = peMap.get(pe.id);
            if (peListInMap == null)
            {
                peListInMap = new ArrayList<>();
                peMap.put(pe.id, peListInMap);
            }
            peListInMap.add(pe);
        }
    }
}

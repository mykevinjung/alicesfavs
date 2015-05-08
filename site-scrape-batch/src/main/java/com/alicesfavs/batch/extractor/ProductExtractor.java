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

import com.alicesfavs.service.CategoryService;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.sitescraper.SiteScrapeException;
import com.alicesfavs.sitescraper.SiteScraper;

@Component("productExtractor")
public class ProductExtractor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExtractor.class);

    private static final int THRESHOLD_PERCENTAGE = 10;

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private SiteScraper siteScraper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public void extractProduct(Job job, Site site) throws ExtractException
    {
        List<Category> categories = extractCategory(job, site);

        // same product is displayed in many categories
        // let's group them and save as one product and link
        final Map<String, List<ProductExtract>> productExtractMap = new HashMap<>();
        final Map<Category, List<ProductExtract>> categoryMap = new HashMap<>();
        for (final Category category : categories)
        {
            final List<ProductExtract> peList = extractCategoryProducts(job.id, site, category);
            buildProductExtractMap(productExtractMap, peList);
            categoryMap.put(category, peList);
        }

        LOGGER.info("Saving product map...");
        final ExtractStatus extractStatus = site.display ? ExtractStatus.EXTRACTED : ExtractStatus.HIDDEN;
        final Map<String, Product> productMap = productService
            .saveProduct(job.id, site.id, extractStatus, productExtractMap);
        job.foundProduct = productMap.size();
        job.notFoundProduct = productService.markNotFoundProduct(job.id, site.id);
        LOGGER.info("Number of not found product: " + job.notFoundProduct);

        //saveCategoryProduct(job, site, categoryMap, productMap);

        //createSearchableProduct(categoryMap, productMap);
        final SearchableProductCreator creator = new SearchableProductCreator(batchConfig);
    }

    private List<Category> extractCategory(Job job, Site site) throws ExtractException
    {
        try
        {
            // TODO need to change domain to actual url
            // because home page of a site like jcrew is https://www.jcrew.com, not http
            // although it should not make a difference
            final String homeUrl = "http://" + site.domain;
            final List<CategoryExtractSpec> categoryExtractSpecList = batchConfig.getCategoryExtractSpec(site);
            final MultirootTree<CategoryExtract> categoryExtracts = siteScraper.extractCategories(homeUrl,
                categoryExtractSpecList);
            final List<Category> categories = categoryService.getSiteCategories(site.id);
            testCategoryExtracts(categoryExtracts, categories);
            final List<Category> categoryList = categoryService.saveCategoryExtract(job.id, site.id, categoryExtracts);
            job.foundCategory = categoryList.size();
            job.notFoundCategory = categoryService.markNotFoundCategory(job.id, site.id);
            LOGGER.info("Number of not found category: " + job.notFoundCategory);

            return categoryList;
        }
        catch (ExtractException e)
        {
            throw e;
        }
        catch (Exception e)
        {
            throw new ExtractException("Error in extracting categories", e);
        }
    }

    private void testCategoryExtracts(MultirootTree<CategoryExtract> newCategoryExtracts,
        List<Category> existingCategories) throws ExtractException
    {
        final int existingCount = existingCategories.size();
        final int newCount = newCategoryExtracts.getLeafNodes().size();
        LOGGER.info("Existing categories: " + existingCount);
        LOGGER.info("New categories: " + newCount);

        if (existingCount > 0 && (((existingCount - newCount) * 100) / existingCount > THRESHOLD_PERCENTAGE))
        {
            throw new ExtractException("Too many categories not found");
        }
    }

    private void saveCategoryProduct(Job job, Site site, Map<Category, List<ProductExtract>> categoryMap,
        Map<String, Product> productMap)
    {
        LOGGER.info("Saving category-product map...");
        int count = 0;
        for (final Category category : categoryMap.keySet())
        {
            int displayOrder = 0;
            for (ProductExtract pe : categoryMap.get(category))
            {
                final Product product = productMap.get(pe.id);
                if (product != null)
                {
                    categoryService.saveCategoryProduct(category, product, ++displayOrder, job.id);
                    count++;
                }
            }
        }
        LOGGER.info("Saved category-product map count: " + count);
        final int countNotFoundCategoryProduct = categoryService.markNotFoundCategoryProduct(job.id, site.id);
        LOGGER.info("Number of not found category-product: " + countNotFoundCategoryProduct);
    }

    private List<ProductExtract> extractCategoryProducts(long jobId, Site site, Category category)
        throws ExtractException
    {
        try
        {
            LOGGER.info("Extracting products for category " + category.id);
            return siteScraper.extractProducts(category.getLeafExtract(), batchConfig.getProductExtractSpec(site),
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
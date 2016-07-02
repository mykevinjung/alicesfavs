package com.alicesfavs.batch.extractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.batch.BatchConfig;
import com.alicesfavs.datamodel.*;
import com.alicesfavs.service.CategoryService;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductDetailExtractSpec;
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
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteScraper siteScraper;

    public void extractProduct(Job job, Site site) throws ExtractException
    {
        final List<Category> categoryList = extractCategory(job, site);

        // same product is displayed in many categories
        // let's group them and save as one product and link
        final Map<String, List<ProductExtract>> productExtractMap = new HashMap<>();
        final Map<Category, List<ProductExtract>> categoryMap = new HashMap<>();
        for (final Category category : categoryList)
        {
            final List<ProductExtract> peList = extractCategoryProducts(site, category);
            buildProductExtractMap(productExtractMap, peList);
            categoryMap.put(category, peList);
        }

        LOGGER.info("Saving product map...");
        final Map<String, Product> productMap = productService
            .saveProduct(job, site, ExtractStatus.EXTRACTED, productExtractMap);
        job.foundProduct = productMap.size();
        job.notFoundProduct = productService.markNotFoundProduct(job, site);

        saveCategoryProduct(job, site, categoryMap, productMap);

        extractProductDetail(site, productMap.values());
    }

    private List<Category> extractCategory(Job job, Site site) throws ExtractException
    {
        try
        {
            final List<CategoryExtractSpec> categoryExtractSpecList = batchConfig.getCategoryExtractSpec(site);
            final MultirootTree<CategoryExtract> categoryExtracts = siteScraper.extractCategories(site, categoryExtractSpecList);

            //final List<Category> categories = categoryService.findSiteCategories(site.id);
            //testCategoryExtracts(categoryExtracts, categories);
            final List<Category> categoryList = categoryService.saveCategoryExtract(job.id, site, categoryExtracts);
            job.foundCategory = categoryList.size();
            job.notFoundCategory = categoryService.markNotFoundCategory(job.id, site.id);
            LOGGER.info("Number of not found category: " + job.notFoundCategory);

            return categoryList;
        }
        catch (Exception e)
        {
            throw new ExtractException("Error in extracting categories", e);
        }
    }

    private List<ProductExtract> extractCategoryProducts(Site site, Category category)
        throws ExtractException
    {
        try
        {
            LOGGER.info("Extracting products for category " + category.getLeafExtract().url);
            return siteScraper.extractProducts(site, category.getLeafExtract(), batchConfig.getProductExtractSpec(site),
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

    private void extractProductDetail(Site site, Collection<Product> productList) throws ExtractException
    {
        try
        {
            final List<ProductDetailExtractSpec> productDetailExtractSpecList =
                batchConfig.getProductDetailExtractSpec(site);
            if (productDetailExtractSpecList != null && productDetailExtractSpecList.size() > 0)
            {
                LOGGER.info("Extracting product details...");
                for (Product product : productList)
                {
                    extractProductDetail(site, product, productDetailExtractSpecList);
                }
            }
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

    private void extractProductDetail(Site site, Product product,
        List<ProductDetailExtractSpec> productDetailExtractSpecList) throws SiteScrapeException
    {
        final boolean oldSoldOut = product.productExtract.soldOut;
        product.productExtract.soldOut = false;
        siteScraper.extractProductDetail(site, product.productExtract, productDetailExtractSpecList);
        if (oldSoldOut != product.productExtract.soldOut)
        {
            productService.saveProduct(product);
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

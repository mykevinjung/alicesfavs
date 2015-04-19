package com.alicesfavs.batch.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.batch.BatchConfig;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.CategoryService;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.sitescraper.SiteScrapeException;
import com.alicesfavs.sitescraper.SiteScraper;

@Component("productExtractSiteProcessor")
public class ProductExtractSiteProcessor implements SiteProcessor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductExtractSiteProcessor.class);

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private SiteScraper siteScraper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    public void process(long jobId, Site site) throws SiteProcessException
    {
        // same product is displayed in many categories
        // let's group them and save as one product and link
        final Map<String, List<ProductExtract>> productExtractMap = new HashMap<String, List<ProductExtract>>();
        final List<Category> categories = categoryService.getSiteCategories(site.id);
        final Map<Category, List<ProductExtract>> categoryMap = new HashMap<Category, List<ProductExtract>>();
        for (final Category category : categories)
        {
            final List<ProductExtract> peList = extractCategoryProducts(jobId, site, category);
            buildProductExtractMap(productExtractMap, peList);
            categoryMap.put(category, peList);
        }

        LOGGER.info("Saving product map...");
        final Map<String, Product> productMap = productService.saveProduct(jobId, site.id, productExtractMap);
        final int countNotFoundProduct = productService.markNotFoundProduct(jobId, site.id);
        LOGGER.info("Number of not found product: " + countNotFoundProduct);

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
                    categoryService.saveCategoryProduct(category, product, ++displayOrder, jobId);
                    count++;
                }
            }
        }
        LOGGER.info("Saved category-product map count: " + count);
        final int countNotFoundCategoryProduct = categoryService.markNotFoundCategoryProduct(jobId, site.id);
        LOGGER.info("Number of not found category-product: " + countNotFoundCategoryProduct);
    }

    private List<ProductExtract> extractCategoryProducts(long jobId, Site site, Category category)
            throws SiteProcessException
    {
        try
        {
            LOGGER.info("Extracting products for category " + category.id);
            return siteScraper.extractProducts(category.getLeafExtract(), batchConfig.getProductExtractSpec(site),
                    batchConfig.getNextPageExtractSpec(site));
        }
        catch (IOException e)
        {
            throw new SiteProcessHardException("Error in getting category extract spec", e);
        }
        catch (SiteScrapeException e)
        {
            throw new SiteProcessHardException("Error in extracting categories", e);
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
                peListInMap = new ArrayList<ProductExtract>();
                peMap.put(pe.id, peListInMap);
            }
            peListInMap.add(pe);
        }
    }
}

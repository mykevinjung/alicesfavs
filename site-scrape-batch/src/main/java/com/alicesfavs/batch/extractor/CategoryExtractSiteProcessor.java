package com.alicesfavs.batch.extractor;

import java.io.IOException;
import java.util.List;

import com.alicesfavs.datamodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.batch.BatchConfig;
import com.alicesfavs.service.CategoryService;
import com.alicesfavs.sitescraper.SiteScrapeException;
import com.alicesfavs.sitescraper.SiteScraper;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;

@Component("categoryExtractSiteProcessor")
public class CategoryExtractSiteProcessor implements SiteProcessor
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryExtractSiteProcessor.class);

    @Autowired
    private BatchConfig batchConfig;

    @Autowired
    private SiteScraper siteScraper;

    @Autowired
    private CategoryService categoryService;

    public void process(Job job, Site site) throws SiteProcessException
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
            if (testCategoryExtracts(categoryExtracts, categories))
            {
                final List<Category> categoryList = categoryService.saveCategoryExtract(job.id, site.id, categoryExtracts);
                job.foundCategoryNo = categoryList.size();
                job.notFoundCategoryNo = categoryService.markNotFoundCategory(job.id, site.id);
                LOGGER.info("Number of not found category: " + job.notFoundCategoryNo);
            }
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

    private boolean testCategoryExtracts(MultirootTree<CategoryExtract> newCategoryExtracts,
            List<Category> existingCategories) throws SiteProcessException
    {
        LOGGER.info("Existing categories: " + existingCategories.size());
        LOGGER.info("New categories: " + newCategoryExtracts.getLeafNodes().size());
        // TODO if total count of new extracts is different from existing by threshold

        return true;
    }

}

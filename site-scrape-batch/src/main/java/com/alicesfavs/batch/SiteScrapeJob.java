package com.alicesfavs.batch;

import java.util.List;

import com.alicesfavs.batch.extractor.ProductExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alicesfavs.batch.extractor.SiteProcessException;
import com.alicesfavs.batch.extractor.SiteProcessor;
import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.JobService;
import com.alicesfavs.service.SiteService;

@Component("siteScrapeJob")
public class SiteScrapeJob
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeJob.class);

    @Autowired
    private SiteService siteService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ProductExtractor productExtractor;

    // job can run for sale category only
    public void execute(String siteId)
    {
        // select site by id
        final Site site = siteService.findSiteById(siteId);
        Assert.notNull(site, "Site not found with ID " + siteId);

        final Job job = jobService.createJob(site.id, Job.Mode.FULL_EXTRACT);
        LOGGER.info("Job is created: " + job.id);

        try
        {
            productExtractor.extractProduct(job, site);
        }
        catch (SiteProcessException e)
        {
            LOGGER.error("Error in productExtractor", e);
        }
        catch (Exception e)
        {
            LOGGER.error("Unknown exception in productExtractor", e);
        }

        jobService.completeJob(job);

        // create new job record
        // extract category structure
        // siteScraper.extractLeafCategories(site, categoryExtractSpecList);
        // save category structure

        // extract products

        // save products

        // mark not found category

        // mark not found category-product

        // mark not found product

        // update onsale table

        // end job
    }

}

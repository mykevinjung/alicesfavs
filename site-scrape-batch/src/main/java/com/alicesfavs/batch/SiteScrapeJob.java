package com.alicesfavs.batch;

import com.alicesfavs.batch.extractor.ProductExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.alicesfavs.batch.extractor.ExtractException;
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
        final Site site = siteService.findSiteById(siteId);

        final Job job = jobService.createJob(site.id, Job.Mode.FULL_EXTRACT);
        LOGGER.info("Job is created: " + job.id);

        try
        {
            productExtractor.extractProduct(job, site);
            jobService.completeJob(job);
            LOGGER.info(job.toString());
        }
        catch (Exception e)
        {
            LOGGER.error("Exception in extracting products", e);
            if (job != null)
            {
                jobService.failJob(job);
            }
        }
    }

}

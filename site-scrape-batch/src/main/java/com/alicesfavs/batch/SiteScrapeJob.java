package com.alicesfavs.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alicesfavs.batch.processor.SiteProcessException;
import com.alicesfavs.batch.processor.SiteProcessor;
import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.JobService;
import com.alicesfavs.service.SiteService;

public class SiteScrapeJob
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeJob.class);

    private SiteService siteService;

    private JobService jobService;

    private List<SiteProcessor> siteProcessors;

    // job can run for sale category only
    public void execute(String siteId)
    {
        // select site by id
        final Site site = siteService.findSiteById(siteId);
        Assert.notNull(site, "Site not found with ID " + siteId);

        final Job job = jobService.createJob(site.id, Job.Mode.FULL_EXTRACT);
        LOGGER.info("Job is created: " + job.id);

        for (int index = 0; index < siteProcessors.size(); index++)
        {
            try
            {
                final SiteProcessor siteProcessor = siteProcessors.get(index);
                siteProcessor.process(job.id, site);
                LOGGER.info("SiteProcessor " + index + " executed: " + siteProcessor);
            }
            catch (SiteProcessException e)
            {
                LOGGER.error("Error in siteProcessor", e);
                System.out.println("Error here: " + e);
                break;
            }
            catch (Exception e)
            {
                LOGGER.error("Unknown exception in siteProcessor", e);
                System.out.println("Error here: " + e);
                break;
            }
        }
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

    public static void main(String[] args)
    {
        // get site string id from args
        final String siteId = args[0];
        final SiteScrapeJob job = new SiteScrapeJob();
        job.execute(siteId);
    }

    public SiteService getSiteService()
    {
        return siteService;
    }

    public void setSiteService(SiteService siteService)
    {
        this.siteService = siteService;
    }

    public JobService getJobService()
    {
        return jobService;
    }

    public void setJobService(JobService jobService)
    {
        this.jobService = jobService;
    }

    public List<SiteProcessor> getSiteProcessors()
    {
        return siteProcessors;
    }

    public void setSiteProcessors(List<SiteProcessor> siteProcessors)
    {
        this.siteProcessors = siteProcessors;
    }

}

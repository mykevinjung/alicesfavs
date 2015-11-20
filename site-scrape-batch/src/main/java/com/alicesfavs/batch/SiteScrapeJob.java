package com.alicesfavs.batch;

import com.alicesfavs.batch.extractor.ProductExtractor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.JobService;
import com.alicesfavs.service.SiteService;

@Component("siteScrapeJob")
public class SiteScrapeJob
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeJob.class);

    private static final String REFRESH_SALE_URI = "/refresh/sale/";
    private static final String REFRESH_NEW_ARRIVAL_URI = "/refresh/new-arrivals/";

    @Autowired
    private SiteService siteService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ProductExtractor productExtractor;

    @Autowired
    private BatchConfig batchConfig;

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
            refreshSite(site);
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

    private void refreshSite(Site site)
    {
        for (String refreshAddr : batchConfig.getRefreshAddrArray())
        {
            refreshSite(refreshAddr, REFRESH_SALE_URI, site);
            refreshSite(refreshAddr, REFRESH_NEW_ARRIVAL_URI, site);
        }
    }

    private void refreshSite(String refreshAddr, String baseUri, Site site)
    {
        final String url = "http://" + refreshAddr + baseUri + site.stringId;
        try
        {
            LOGGER.info("Refreshing on " + url);
            Jsoup.connect(url).timeout(90 * 1000).userAgent("Alice's Favs SmartCrawler").get();
        }
        catch (HttpStatusException e)
        {
            LOGGER.error("Failed to refresh. Http Status Code " + e.getStatusCode() + " in opening " + url, e);
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to refresh " + url, e);
        }
    }

}

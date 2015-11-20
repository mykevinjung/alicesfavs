package com.alicesfavs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alicesfavs.batch.SiteScrapeJob;
import org.springframework.util.StringUtils;

/**
 * Hello world!
 */
public class SiteScrapeBatchApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeBatchApp.class);

    private static final String FORCE_SAVE = "forceSave";

    public static void main(String[] args) throws Exception
    {
        if (args == null || args.length < 1 || !StringUtils.hasText(args[0]))
        {
            throw new RuntimeException("siteId is not passed in the argument");
        }

        try
        {
            final String siteId = args[0].trim();
            final boolean forceSave = args.length >= 2 && FORCE_SAVE.equalsIgnoreCase(args[1]) ? true : false;
            ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
            SiteScrapeJob job = context.getBean(SiteScrapeJob.class);
            LOGGER.info("Starting scrape for " + siteId);
            job.execute(siteId, forceSave);
        }
        catch (Exception e)
        {
            LOGGER.error("Error in executing site scrape job", e);
        }
    }

}

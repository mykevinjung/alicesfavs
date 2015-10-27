package com.alicesfavs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alicesfavs.batch.SiteScrapeJob;
import org.springframework.util.StringUtils;

/**
 * Hello world!
 *
 */
public class SiteScrapeBatchApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeBatchApp.class);

    public static void main(String[] args) throws Exception
    {
        if (args == null || args.length < 1 || !StringUtils.hasText(args[0]))
        {
            throw new RuntimeException("siteId is not passed in the argument");
        }

        try
        {
            final String siteId = args[0].trim();
            ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
            SiteScrapeJob job = context.getBean(SiteScrapeJob.class);
            LOGGER.info("Starting scrape for " + siteId);
            job.execute(siteId);
        }
        catch (Exception e)
        {
            LOGGER.error("Error in executing site scrape job", e);
        }
    }

}

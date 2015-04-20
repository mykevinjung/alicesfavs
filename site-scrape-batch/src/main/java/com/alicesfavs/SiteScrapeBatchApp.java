package com.alicesfavs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alicesfavs.batch.SiteScrapeJob;

/**
 * Hello world!
 *
 */
public class SiteScrapeBatchApp
{
    public static void main(String[] args) throws Exception
    {
         testSiteScrapeJob();
    }

    private static void testSiteScrapeJob() throws Exception
    {
        @SuppressWarnings("resource")
        ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
        SiteScrapeJob job = context.getBean(SiteScrapeJob.class);
        job.execute("jcrew");
    }

}

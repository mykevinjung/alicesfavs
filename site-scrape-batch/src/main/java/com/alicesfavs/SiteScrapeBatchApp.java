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
        ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
        SiteScrapeJob job = context.getBean(SiteScrapeJob.class);
        job.execute(args[0]);
    }

}

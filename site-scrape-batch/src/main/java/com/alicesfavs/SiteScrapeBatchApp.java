package com.alicesfavs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alicesfavs.batch.SiteScrapeJob;
import org.springframework.util.StringUtils;

import java.io.File;

/**
 * Hello world!
 */
public class SiteScrapeBatchApp
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeBatchApp.class);

    private static final String FORCE_SAVE = "forceSave";
    private static final String USE_SELENIUM = "useSelenium";
    private static final String FIREFOX_PATH = "firefox.driver";

    public static void main(String[] args) throws Exception
    {
        if (args == null || args.length < 1 || !StringUtils.hasText(args[0]))
        {
            throw new RuntimeException("siteId is not passed in the argument");
        }

        WebDriver webDriver = null;
        try
        {
            final String siteId = args[0].trim();
            final boolean forceSave = args.length >= 2 && FORCE_SAVE.equalsIgnoreCase(args[1]) ? true : false;
            final boolean useSelenium = args.length >= 2 && USE_SELENIUM.equalsIgnoreCase(args[1]) ? true : false;
            final ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
            if (useSelenium)
            {
                LOGGER.info("Loading Firefox driver...");
                final String firefoxDriver = System.getProperty(FIREFOX_PATH);
                File file = new File(firefoxDriver);
                FirefoxProfile firefoxProfile = new FirefoxProfile();
                firefoxProfile.addExtension(file);
                // Avoid startup screen
                firefoxProfile.setPreference("extensions.firebug.currentVersion", "2.0.17");

                webDriver = new FirefoxDriver(firefoxProfile);
            }
            SiteScrapeJob job = context.getBean(SiteScrapeJob.class);
            job.setWebDriver(webDriver);
            LOGGER.info("Starting scrape for " + siteId);
            job.execute(siteId, forceSave);
        }
        catch (Exception e)
        {
            LOGGER.error("Error in executing site scrape job", e);
        }
        finally
        {
            if (webDriver != null)
            {
                webDriver.quit();
            }
        }
    }

}

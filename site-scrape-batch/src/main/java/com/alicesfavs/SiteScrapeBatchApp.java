package com.alicesfavs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
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
    private static final String USE_PHANTOMJS = "usePhantomJS";
    private static final String PHANTOMJS_PATH = "phantomjs.path";

    public static void main(String[] args) throws Exception
    {
        if (args == null || args.length < 1 || !StringUtils.hasText(args[0]))
        {
            throw new RuntimeException("siteId is not passed in the argument");
        }
        LOGGER.info("args size: " + args.length);

        WebDriver webDriver = null;
        try
        {
            final String siteId = args[0].trim();
            final boolean forceSave = args.length >= 2 && FORCE_SAVE.equalsIgnoreCase(args[1]) ? true : false;
            final boolean usePhantomJS = args.length >= 2 && USE_PHANTOMJS.equalsIgnoreCase(args[1]) ? true : false;
            final ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
            if (usePhantomJS)
            {
                LOGGER.info("Loading PhantomJS Driver...");
                final String[] phantomArgs = new  String[] { "--webdriver-loglevel=NONE" };
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setJavascriptEnabled(true);
                caps.setCapability("takesScreenshot", false);
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                    System.getProperty(PHANTOMJS_PATH));
                caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);

                webDriver = new PhantomJSDriver(caps);
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

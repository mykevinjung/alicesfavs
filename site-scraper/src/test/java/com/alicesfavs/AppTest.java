package com.alicesfavs;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    final String url = "http://www.bodenusa.com/en-us/clearance/womens-dresses";

    public void testHtmlUnit() throws Exception
    {
//        final String url = "http://www.bodenusa.com/en-us/clearance/womens-dresses";
//        try (final WebClient webClient = new WebClient()) {
//            webClient.getOptions().setThrowExceptionOnScriptError(false);
//            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
//            webClient.getOptions().setPopupBlockerEnabled(true);
//            webClient.getOptions().setRedirectEnabled(true);
//            webClient.getOptions().setCssEnabled(true);
//            webClient.getOptions().setJavaScriptEnabled(true);
//            webClient.getOptions().setHomePage(WebClient.URL_ABOUT_BLANK.toString());
//            final HtmlPage page = webClient.getPage(url);
//            System.out.println("asXml: " + page.asXml());
//        }
    }

    public void testSelenium() throws Exception
    {
//        System.setProperty("webdriver.chrome.driver", "/Users/kjung/src/sungmuk/chromedriver");
//        WebDriver webDriver = new HtmlUnitDriver(true);
//        webDriver.manage().window().maximize();
//        webDriver.get("http://www.bodenusa.com/en-us/clearance/womens-dresses?viewby=Colours&sort=StaffFavourite&viewtype=large&page=1");
//        Object o = ((JavascriptExecutor)webDriver).executeScript("window.scrollBy(0,3450);", "");
//        final String pageSource = webDriver.getPageSource();
//        System.out.println("pageSource: " + pageSource);
//        webDriver.quit();
    }

    public void testPhantomJS() throws Exception {
//        final String phantomJSPath = "/Users/kjung/src/sungmuk/alicesfavs/site-scrape-batch/src/main/resources/phantomjs-2.1.1-macosx/bin/phantomjs";
//        final String[] phantomArgs = new  String[] { "--webdriver-loglevel=NONE" };
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        caps.setCapability("takesScreenshot", true);
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomJSPath);
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, phantomArgs);
//
//        WebDriver webDriver = new PhantomJSDriver(caps);
//        webDriver.get(url);
//        final String pageSource = webDriver.getPageSource();
//        System.out.println("pageSource: " + pageSource);
//        webDriver.quit();
    }
}

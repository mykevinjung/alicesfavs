package com.alicesfavs;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception
    {
//        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45)) {
//            webClient.getOptions().setThrowExceptionOnScriptError(false);
//            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
//            webClient.getOptions().setPopupBlockerEnabled(true);
//            webClient.getOptions().setActiveXNative(true);
//            webClient.getOptions().setRedirectEnabled(true);
//            webClient.getOptions().setCssEnabled(false);
//            webClient.getOptions().setJavaScriptEnabled(true);
//            final HtmlPage page = webClient.getPage("http://www.bodenusa.com/en-us/clearance/womens-dresses?viewby=Colours&sort=StaffFavourite&viewtype=large&page=1");
//            webClient.waitForBackgroundJavaScriptStartingBefore(200);
//            webClient.waitForBackgroundJavaScript(15*1000);
//            final String asXml = page.getWebResponse().getContentAsString();
//            final String asXml2 = page.asXml();
//            System.out.println("asXml: " + asXml);
//        }
    }

    public void testSelenium() throws Exception
    {
//        System.setProperty("webdriver.chrome.driver", "/Users/kjung/src/sungmuk/chromedriver");
//        WebDriver webDriver = new ChromeDriver();
//        webDriver.manage().window().maximize();
//        webDriver.get("http://www.bodenusa.com/en-us/clearance/womens-dresses?viewby=Colours&sort=StaffFavourite&viewtype=large&page=1");
//        Object o = ((JavascriptExecutor)webDriver).executeScript("window.scrollBy(0,3450);", "");
//        final String pageSource = webDriver.getPageSource();
//        System.out.println("pageSource: " + pageSource);
//        webDriver.quit();
    }
}

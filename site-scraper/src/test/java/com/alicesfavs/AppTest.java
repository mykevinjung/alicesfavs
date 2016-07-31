package com.alicesfavs;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.DefaultJavaScriptErrorListener;
import com.gargoylesoftware.htmlunit.javascript.JavaScriptErrorListener;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Assert;

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
        try (final WebClient webClient = new WebClient(BrowserVersion.FIREFOX_45)) {
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setPrintContentOnFailingStatusCode(false);
            webClient.getOptions().setPopupBlockerEnabled(true);
            webClient.getOptions().setHomePage("http://www.bodenusa.com");
            webClient.getOptions().setActiveXNative(true);
            webClient.getOptions().setRedirectEnabled(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.setCssErrorHandler(new SilentCssErrorHandler());
            webClient.setJavaScriptErrorListener(new DefaultJavaScriptErrorListener());
            final HtmlPage page = webClient.getPage("http://www.bodenusa.com/en-us/clearance/womens-dresses?viewby=Colours&sort=StaffFavourite&viewtype=large&page=1");
            webClient.waitForBackgroundJavaScriptStartingBefore(200);
            webClient.waitForBackgroundJavaScript(15*1000);
            final String asXml = page.getWebResponse().getContentAsString();
            final String asXml2 = page.asXml();
            System.out.println("asXml: " + asXml);
        }
    }
}

package com.alicesfavs.sitescraper;

public class SiteScrapeException extends Exception
{
    private static final long serialVersionUID = 1L;

    public SiteScrapeException()
    {
	super();
    }
    
    public SiteScrapeException(String message)
    {
	super(message);
    }
    
    public SiteScrapeException(String message, Throwable cause)
    {
	super(message, cause);
    }
    
    public SiteScrapeException(Throwable cause)
    {
	super(cause);
    }
}

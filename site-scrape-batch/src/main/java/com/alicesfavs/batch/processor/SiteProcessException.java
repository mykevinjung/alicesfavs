package com.alicesfavs.batch.processor;

public class SiteProcessException extends Exception
{

    private static final long serialVersionUID = 1L;

    public SiteProcessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SiteProcessException(String message)
    {
        super(message);
    }

    public SiteProcessException(Throwable cause)
    {
        super(cause);
    }

}

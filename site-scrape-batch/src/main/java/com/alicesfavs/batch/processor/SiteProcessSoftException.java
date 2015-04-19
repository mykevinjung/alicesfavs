package com.alicesfavs.batch.processor;

public class SiteProcessSoftException extends SiteProcessException
{

    private static final long serialVersionUID = 1L;

    public SiteProcessSoftException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SiteProcessSoftException(String message)
    {
        super(message);
    }

    public SiteProcessSoftException(Throwable cause)
    {
        super(cause);
    }

}

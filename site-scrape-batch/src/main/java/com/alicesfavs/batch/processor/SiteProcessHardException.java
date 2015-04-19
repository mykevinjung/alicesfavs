package com.alicesfavs.batch.processor;

public class SiteProcessHardException extends SiteProcessException
{

    private static final long serialVersionUID = 1L;

    public SiteProcessHardException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SiteProcessHardException(String message)
    {
        super(message);
    }

    public SiteProcessHardException(Throwable cause)
    {
        super(cause);
    }

}

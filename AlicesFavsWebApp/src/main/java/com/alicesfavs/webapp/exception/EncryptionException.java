package com.alicesfavs.webapp.exception;

/**
 * Created by kjung on 1/29/16.
 */
public class EncryptionException extends Exception
{
    public EncryptionException()
    {
    }

    public EncryptionException(String message)
    {
        super(message);
    }

    public EncryptionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public EncryptionException(Throwable cause)
    {
        super(cause);
    }

    public EncryptionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

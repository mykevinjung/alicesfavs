package com.alicesfavs.mail;

/**
 * Created by kjung on 5/30/15.
 */
public class SendMailException extends Exception
{
    public SendMailException()
    {
    }

    public SendMailException(String message)
    {
        super(message);
    }

    public SendMailException(Throwable cause)
    {
        super(cause);
    }

    public SendMailException(String message, Throwable cause)
    {
        super(message, cause);
    }

}

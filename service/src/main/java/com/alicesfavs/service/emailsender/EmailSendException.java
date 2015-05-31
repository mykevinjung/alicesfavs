package com.alicesfavs.service.emailsender;

/**
 * Created by kjung on 5/30/15.
 */
public class EmailSendException extends RuntimeException
{
    public EmailSendException()
    {
    }

    public EmailSendException(String message)
    {
        super(message);
    }

    public EmailSendException(Throwable cause)
    {
        super(cause);
    }

    public EmailSendException(String message, Throwable cause)
    {
        super(message, cause);
    }

}

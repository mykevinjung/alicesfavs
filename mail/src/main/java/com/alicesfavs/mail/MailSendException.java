package com.alicesfavs.mail;

/**
 * Created by kjung on 5/30/15.
 */
public class MailSendException extends Exception
{
    public MailSendException()
    {
    }

    public MailSendException(String message)
    {
        super(message);
    }

    public MailSendException(Throwable cause)
    {
        super(cause);
    }

    public MailSendException(String message, Throwable cause)
    {
        super(message, cause);
    }

}

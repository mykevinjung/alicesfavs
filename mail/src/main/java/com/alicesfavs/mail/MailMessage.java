package com.alicesfavs.mail;

/**
 * Created by kjung on 3/26/16.
 */
public class MailMessage
{

    private String textMessage;
    private String htmlMessage;

    public String getTextMessage()
    {
        return textMessage;
    }

    public String getHtmlMessage()
    {
        return htmlMessage;
    }

    public MailMessage withHtmlMessage(String htmlMessage)
    {
        this.htmlMessage = htmlMessage;
        return this;
    }

    public MailMessage withTextMessage(String textMessage)
    {
        this.textMessage = textMessage;
        return this;
    }

}

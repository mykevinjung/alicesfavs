package com.alicesfavs.mail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 5/31/15.
 */
public class MailRequest
{

    private List<MailAddress> toAddressList;
    private MailAddress fromAddress;
    private MailAddress replyToAddress;
    private String subject;
    private String body;
    private BodyType bodyType = BodyType.TEXT;

    public List<MailAddress> getToAddressList()
    {
        return toAddressList;
    }

    public MailAddress getFromAddress()
    {
        return fromAddress;
    }

    public MailAddress getReplyToAddress()
    {
        return replyToAddress;
    }

    public String getSubject()
    {
        return subject;
    }

    public String getBody()
    {
        return body;
    }

    public BodyType getBodyType()
    {
        return bodyType;
    }

    public MailRequest withToAddress(MailAddress toAddress)
    {
        if (toAddressList == null)
        {
            toAddressList = new ArrayList<>();
        }
        toAddressList.add(toAddress);
        return this;
    }

    public MailRequest withToAddressList(List<MailAddress> toAddressList)
    {
        this.toAddressList = toAddressList;
        return this;
    }

    public MailRequest withFromAddress(MailAddress fromAddress)
    {
        this.fromAddress = fromAddress;
        return this;
    }

    public MailRequest withReplyToAddress(MailAddress replyToAddress)
    {
        this.replyToAddress = replyToAddress;
        return this;
    }

    public MailRequest withSubject(String subject)
    {
        this.subject = subject;
        return this;
    }

    public MailRequest withBody(String body)
    {
        this.body = body;
        return this;
    }

    public MailRequest withBodyType(BodyType bodyType)
    {
        this.bodyType = bodyType;
        return this;
    }

}

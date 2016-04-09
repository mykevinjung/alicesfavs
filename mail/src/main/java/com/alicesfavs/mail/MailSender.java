package com.alicesfavs.mail;

/**
 * Created by kjung on 5/31/15.
 */
public interface MailSender
{

    MailResult send(MailRequest mailRequest) throws SendMailException;

}

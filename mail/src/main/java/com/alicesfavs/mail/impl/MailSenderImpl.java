package com.alicesfavs.mail.impl;

import com.alicesfavs.mail.MailSender;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by kjung on 5/31/15.
 */
public class MailSenderImpl implements MailSender
{

    @Autowired
    private org.springframework.mail.MailSender mailSender;

    @Override public void sendUserVerificationMail()
    {

    }

}

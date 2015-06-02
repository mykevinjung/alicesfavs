package com.alicesfavs.mail.impl;

import com.alicesfavs.mail.MailSendException;
import com.alicesfavs.mail.MailSender;
import com.alicesfavs.mail.UserVerificationMailData;
import com.alicesfavs.mail.impl.delegate.UserVerificationMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by kjung on 5/31/15.
 */
public class MailSenderImpl implements MailSender
{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailConfig mailConfig;

    @Override
    public void sendUserVerificationMail(UserVerificationMailData userVerificationMailData) throws MailSendException
    {
        new UserVerificationMailSender(javaMailSender, mailConfig, userVerificationMailData).send();
    }

}

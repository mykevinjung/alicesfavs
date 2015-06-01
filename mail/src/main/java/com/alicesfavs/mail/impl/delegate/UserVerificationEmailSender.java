package com.alicesfavs.mail.impl.delegate;

import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by kjung on 5/30/15.
 */
public class UserVerificationEmailSender extends AbstractMailSender
{

    public UserVerificationEmailSender(JavaMailSender mailSender)
    {
        super(mailSender);
    }

    @Override
    protected MailMessage getMailMessageInstance()
    {
        return null;
    }
}

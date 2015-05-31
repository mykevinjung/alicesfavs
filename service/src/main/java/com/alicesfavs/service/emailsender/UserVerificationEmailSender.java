package com.alicesfavs.service.emailsender;

import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by kjung on 5/30/15.
 */
public class UserVerificationEmailSender extends EmailSender
{

    public UserVerificationEmailSender(JavaMailSender mailSender)
    {
        super(mailSender);
    }

    @Override protected MailMessage getMailMessageInstance()
    {
        return null;
    }
}

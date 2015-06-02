package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.UserVerificationMailData;
import com.alicesfavs.mail.impl.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by kjung on 5/30/15.
 */
public class UserVerificationMailSender extends MimeMailSender
{

    private final UserVerificationMailData userVerificationMailData;

    public UserVerificationMailSender(JavaMailSender mailSender, MailConfig mailConfig,
        UserVerificationMailData userVerificationMailData)
    {
        super(mailSender, mailConfig, userVerificationMailData);
        this.userVerificationMailData = userVerificationMailData;
    }

    @Override protected String getSubject()
    {
        return mailConfig.userVerificationSubject;
    }

    @Override protected String getText()
    {
        return "test message!!!";
    }

}

package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.UserVerificationMailData;
import com.alicesfavs.mail.impl.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Properties;

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

    @Override
    protected String getSubject()
    {
        return mailConfig.userVerificationSubject;
    }

    @Override
    protected Properties getContentProperties()
    {
        final Properties properties = new Properties();
        properties.setProperty("verification_url", userVerificationMailData.verificationUrl);

        return properties;
    }

    @Override
    protected String getTemplatePath()
    {
        return mailConfig.userVerificationTemplate;
    }

}

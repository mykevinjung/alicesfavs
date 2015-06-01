package com.alicesfavs.mail.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@PropertySource("classpath:/env/mail_config.properties")
public class MailConfig
{

    @Value(value = "${mail.template.userverificationmail}")
    private String userVerificationMailTemplate;

    public String getUserVerificationMailTemplate()
    {
        return userVerificationMailTemplate;
    }

}

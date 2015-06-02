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

    @Value(value = "${mail.data.address.noreply}")
    public String noreplyAddress;

    @Value(value = "${mail.data.address.noreply.personal}")
    public String noreplyPersonal;

    @Value(value = "${mail.data.address.feedback}")
    public String feedbackAddress;

    @Value(value = "${mail.data.address.feedback.personal}")
    public String feedbackPersonal;

    @Value(value = "${mail.data.address.support}")
    public String supportAddress;

    @Value(value = "${mail.data.address.support.personal}")
    public String supportPersonal;

    @Value(value = "${mail.subject.userverification}")
    public String userVerificationSubject;

    @Value(value = "${mail.template.userverification}")
    public String userVerificationTemplate;

}

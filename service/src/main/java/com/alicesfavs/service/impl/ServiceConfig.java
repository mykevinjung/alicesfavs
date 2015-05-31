package com.alicesfavs.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
@PropertySource("classpath:/env/${EXECUTION_ENV:dev}/service_config.properties")
public class ServiceConfig
{

    @Value(value = "${service.emailservice.template.userverificationemail}")
    private String userVerificationEmailTemplate;


}

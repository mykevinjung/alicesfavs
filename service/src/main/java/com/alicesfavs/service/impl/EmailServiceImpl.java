package com.alicesfavs.service.impl;

import com.alicesfavs.datamodel.User;
import com.alicesfavs.datamodel.UserVerification;
import com.alicesfavs.service.EmailService;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by kjung on 5/30/15.
 */
public class EmailServiceImpl implements EmailService
{
    private MailSender mailSender;
    private SimpleMailMessage simpleMailMessage;

    @Override
    public void sendUserVerificationEmail(User user, UserVerification userVerification)
    {

    }

}

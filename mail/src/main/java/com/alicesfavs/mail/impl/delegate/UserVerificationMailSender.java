package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.UserVerificationMailData;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;

/**
 * Created by kjung on 5/30/15.
 */
public class UserVerificationMailSender extends AbstractMailSender
{

    public UserVerificationMailSender(JavaMailSender mailSender, UserVerificationMailData userVerificationMailData)
    {
        super(mailSender, userVerificationMailData);
    }

    @Override
    protected MailMessage getMailMessageInstance()
    {
        return null;
    }

}

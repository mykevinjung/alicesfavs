package com.alicesfavs.mail;

/**
 * Created by kjung on 5/31/15.
 */
public interface MailSender
{

    void sendUserVerificationMail(UserVerificationMailData userVerificationMailData);

}
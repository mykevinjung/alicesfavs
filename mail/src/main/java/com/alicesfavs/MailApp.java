package com.alicesfavs;

import com.alicesfavs.mail.MailSendException;
import com.alicesfavs.mail.MailSender;
import com.alicesfavs.mail.UserVerificationMailData;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class MailApp
{
    public static void main( String[] args ) throws MailSendException
    {
        System.out.println("starting...");
        ApplicationContext context = new ClassPathXmlApplicationContext("mail.xml");
        MailSender sender = context.getBean(MailSender.class);
        UserVerificationMailData data = new UserVerificationMailData();
        data.toAddress = "mykevinjung@gmail.com";
        data.toPersonal = "Sung Muk Jung";
        data.verificationUrl = "http://www.alicesfavs.com/contact-us";

        System.out.println("sending...");
        sender.sendUserVerificationMail(data);
        System.out.println("sent!");
    }
}

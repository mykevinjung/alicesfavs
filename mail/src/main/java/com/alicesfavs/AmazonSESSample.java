package com.alicesfavs;

import com.alicesfavs.mail.MailAddress;
import com.alicesfavs.mail.MailRequest;
import com.alicesfavs.mail.MailResult;
import com.alicesfavs.mail.MailSender;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by kjung on 2/7/16.
 */
public class AmazonSESSample
{

    public static void main(String[] args) throws Exception {

        ApplicationContext context = new ClassPathXmlApplicationContext("mail.xml");
        MailSender mailSender = context.getBean(MailSender.class);

        MailRequest mailRequest = new MailRequest();
        mailRequest.withFromAddress(new MailAddress("alice@alicesfavs.com"))
        .withToAddress(new MailAddress("mykevinjung@gmail.com")).withSubject("test").withBody("test body");
        MailResult result = mailSender.send(mailRequest);
        System.out.println("Sent. Message id: " + result.getMailId());
        MailResult result2 = mailSender.send(mailRequest);
        System.out.println("Sent. Message id: " + result2.getMailId());
    }
}

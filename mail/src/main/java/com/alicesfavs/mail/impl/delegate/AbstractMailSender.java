package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.MailData;
import com.alicesfavs.mail.MailSendException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

/**
 * Created by kjung on 5/30/15.
 */
public abstract class AbstractMailSender
{
    private final JavaMailSender mailSender;
    private final MailData mailData;

    public AbstractMailSender(JavaMailSender mailSender, MailData mailData)
    {
        this.mailSender = mailSender;
        this.mailData = mailData;
    }

    public final void send() throws MailSendException
    {
        final MailMessage mailMessage = getMailMessageInstance();
        prepareMailMessage(mailMessage);

        if (mailMessage instanceof SimpleMailMessage)
        {
            mailSender.send((SimpleMailMessage) mailMessage);
        }
        else if (mailMessage instanceof MimeMailMessage)
        {
            mailSender.send(((MimeMailMessage) mailMessage).getMimeMessage());
        }
    }

    protected void prepareMailMessage(MailMessage mailMessage)
    {
        mailMessage.setFrom(mailData.fromAddress);
        mailMessage.setReplyTo(mailData.replyToAddress);
        mailMessage.setSubject("Subject");
        mailMessage.setTo(mailData.toAddress);
        mailMessage.setText("text");
    }

    protected abstract MailMessage getMailMessageInstance();

}

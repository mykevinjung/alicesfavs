package com.alicesfavs.service.emailsender;

import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;

/**
 * Created by kjung on 5/30/15.
 */
public abstract class EmailSender
{
    private final JavaMailSender mailSender;

    public EmailSender(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public final void send() throws EmailSendException
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
        mailMessage.setFrom("from");
        mailMessage.setReplyTo("replyTo");
        mailMessage.setSubject("Subject");
        mailMessage.setTo("to");
        mailMessage.setText("text");
    }

    protected abstract MailMessage getMailMessageInstance();

}

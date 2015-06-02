package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.MailData;
import com.alicesfavs.mail.MailSendException;
import com.alicesfavs.mail.impl.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by kjung on 6/1/15.
 */
public abstract class MimeMailSender
{
    protected final JavaMailSender mailSender;
    protected final MailConfig mailConfig;
    protected final MailData mailData;

    public MimeMailSender(JavaMailSender mailSender, MailConfig mailConfig, MailData mailData)
    {
        this.mailSender = mailSender;
        this.mailConfig = mailConfig;
        this.mailData = mailData;
    }

    public final void send() throws MailSendException
    {
        try
        {
            final MimeMailMessage mimeMailMessage = new MimeMailMessage(mailSender.createMimeMessage());
            prepare(mimeMailMessage.getMimeMessageHelper());
            mailSender.send(mimeMailMessage.getMimeMessage());
        }
        catch (Exception e)
        {
            throw new MailSendException(e);
        }
    }

    protected void prepare(MimeMessageHelper helper) throws MessagingException, UnsupportedEncodingException
    {
        helper.setFrom(getFromAddress());
        helper.setReplyTo(getReplyToAddress());
        helper.setSubject(getSubject());
        helper.setTo(getToAddress());
        helper.setText(getText());
        helper.setSentDate(getSentDate());
    }

    protected InternetAddress getFromAddress() throws UnsupportedEncodingException
    {
        return new InternetAddress(mailConfig.noreplyAddress, mailConfig.noreplyPersonal);
    }

    protected InternetAddress getReplyToAddress() throws UnsupportedEncodingException
    {
        return new InternetAddress(mailConfig.supportAddress, mailConfig.supportPersonal);
    }

    protected InternetAddress getToAddress() throws UnsupportedEncodingException
    {
        return new InternetAddress(mailData.toAddress, mailData.toPersonal);
    }

    protected Date getSentDate()
    {
        return new Date();
    }

    protected abstract String getSubject();

    protected abstract String getText();

}

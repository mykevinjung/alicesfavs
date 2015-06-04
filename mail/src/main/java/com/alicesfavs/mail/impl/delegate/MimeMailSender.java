package com.alicesfavs.mail.impl.delegate;

import com.alicesfavs.mail.MailData;
import com.alicesfavs.mail.MailSendException;
import com.alicesfavs.mail.impl.MailConfig;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

/**
 * Created by kjung on 6/1/15.
 */
public abstract class MimeMailSender
{

    protected final JavaMailSender mailSender;
    protected final MailConfig mailConfig;
    protected final MailData mailData;

    protected boolean isHtml = true;

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

    protected void prepare(MimeMessageHelper helper) throws MessagingException, IOException
    {
        helper.setFrom(getFromAddress());
        helper.setReplyTo(getReplyToAddress());
        helper.setSubject(getSubject());
        helper.setTo(getToAddress());
        helper.setText(getText(), isHtml);
        helper.setSentDate(new Date());
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

    protected String getText() throws IOException
    {
        final String templatePath = getTemplatePath();
        if (StringUtils.hasText(templatePath))
        {
            final MailTextGenerator generator = new MailTextGenerator(templatePath, getContentProperties());
            return generator.generateText();
        }

        return "";
    }

    protected Properties getContentProperties()
    {
        return null;
    }

    protected abstract String getTemplatePath();

    protected abstract String getSubject();

}

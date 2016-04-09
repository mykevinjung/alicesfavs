package com.alicesfavs.mail.impl;

import com.alicesfavs.mail.MailAddress;
import com.alicesfavs.mail.MailRequest;
import com.alicesfavs.mail.MailResult;
import com.alicesfavs.mail.SendMailException;
import com.alicesfavs.mail.MailSender;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.amazonaws.services.simpleemail.model.SendEmailResult;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 3/26/16.
 */
@Component("mailSender")
public class AwsSESMailSender implements MailSender
{

    private String awsCredentialProperties = "/aws-credentials.properties";

    private AmazonSimpleEmailServiceClient sesClient;

    @PostConstruct
    public void init()
    {
        try
        {
            AWSCredentials awsCredentials = new PropertiesCredentials(this.getClass().getResourceAsStream(awsCredentialProperties));
            sesClient = new AmazonSimpleEmailServiceClient(awsCredentials);
            sesClient.setRegion(Region.getRegion(Regions.US_WEST_2));
        }
        catch (IOException e)
        {
            throw new RuntimeException("Error in loading credential properties file", e);
        }
    }

    @Override
    public MailResult send(MailRequest mailRequest) throws SendMailException
    {
        // Construct an object to contain the recipient address.
        Destination destination = new Destination()
            .withToAddresses(getAddressTextList(mailRequest.getToAddressList()));

        // Create the subject and body of the message.
        Content subject = new Content().withData(mailRequest.getSubject());
        Content textBody = new Content().withData(mailRequest.getBody());
        Body body = new Body().withText(textBody);

        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);

        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(getAddressText(mailRequest.getFromAddress()))
            .withDestination(destination).withMessage(message);

        try
        {
            SendEmailResult result = sesClient.sendEmail(request);
            return new MailResult(result.getMessageId());
        }
        catch (Exception e)
        {
            throw new SendMailException("Send email failed", e);
        }
    }

    private List<String> getAddressTextList(List<MailAddress> mailAddressList)
    {
        List<String> addressTextList = new ArrayList<>();
        for (MailAddress mailAddress : mailAddressList)
        {
            addressTextList.add(getAddressText(mailAddress));
        }

        return addressTextList;
    }

    private String getAddressText(MailAddress mailAddress)
    {
        if (StringUtils.hasText(mailAddress.getPersonal()))
        {
            return mailAddress.getPersonal() + " <" + mailAddress.getAddress() + ">";
        }
        else
        {
            return mailAddress.getAddress();
        }
    }

}

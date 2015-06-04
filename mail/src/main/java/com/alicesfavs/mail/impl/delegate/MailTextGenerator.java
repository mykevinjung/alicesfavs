package com.alicesfavs.mail.impl.delegate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by kjung on 6/3/15.
 */
public class MailTextGenerator
{

    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String CID_OPEN = "${cid:";
    private static final String CID_CLOSE = "}";

    private final String templatePath;
    private final Properties properties;

    public MailTextGenerator(String templatePath, Properties properties)
    {
        Assert.hasText(templatePath);
        this.templatePath = templatePath;
        this.properties = properties;
    }

    public String generateText() throws IOException
    {
        return replaceProperties(loadTemplate());
    }

    private String loadTemplate() throws IOException
    {
        final StringBuilder textBuilder = new StringBuilder();
        final ClassPathResource classPathResource = new ClassPathResource(templatePath);
        try (final BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(classPathResource.getInputStream()));)
        {
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                textBuilder.append(line).append(NEW_LINE);
            }
        }

        return textBuilder.toString();
    }

    private String replaceProperties(String text)
    {
        if (properties != null)
        {
            for (final String key : properties.stringPropertyNames())
            {
                final String value = properties.getProperty(key);
                text = StringUtils.replace(text, CID_OPEN + key + CID_CLOSE, value);
            }
            return text;
        }

        return text;
    }
}

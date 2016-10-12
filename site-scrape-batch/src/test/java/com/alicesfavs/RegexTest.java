package com.alicesfavs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by kjung on 6/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegexTest
{

    @Test
    public void testRegex()
    {
        final String testString = "content/resources/artifacts/checkout/airflight_en_us.xml";
        final String regex = "content/resources/(views/default|templates|artifacts)/.*air.*";

        assertTrue("Does not match!", testString.matches(regex));
    }

    @Test
    public void testExtractText()
    {

        final String testString = "$ 123.00";
        final String regex = "\\$ ([0-9,.]+)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);

        assertTrue("Can't find the string!", matcher.find());
        assertEquals("Can't extract the string!", "123.00", matcher.group(1));
    }
}

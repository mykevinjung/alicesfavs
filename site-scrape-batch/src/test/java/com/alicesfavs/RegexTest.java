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

        final String testString = "View All";
        final String regex = "View All|New to Sale";

        assertTrue("Does not match!", testString.matches(regex));
    }

    @Test
    public void testExtractText()
    {

        final String testString = "https://www.abercrombie.com/shop/us/mens-graphic-tees-tops/logo-graphic-tee-7629650_02";
        final String regex = ".*-([0-9_]+)$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);

        assertTrue("Can't find the string!", matcher.find());
        assertEquals("Can't extract the string!", "7629650_02", matcher.group(1));
    }
}

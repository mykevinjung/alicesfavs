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

        final String testString = "$455  $228 (50%)";
        final String regex = ".* (\\$[0-9]+) .*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);

        assertTrue("Can't find the string!", matcher.find());
        assertEquals("Can't extract the string!", "$228", matcher.group(1));
    }
}

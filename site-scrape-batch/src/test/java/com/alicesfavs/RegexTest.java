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

        final String testString = "http://www.rebeccaminkoff.com/sale?cat=63";
        final String regex = ".*rebeccaminkoff.com/sale\\?cat=.*";

        assertTrue("Does not match!", testString.matches(regex));
    }

    @Test
    public void testExtractText()
    {

        final String testString = "/anthro/product/shopsale-freshcuts-jewelryaccessories/38097556.jsp";
        final String regex = ".*/([a-zA-Z0-9]+)\\.jsp.*";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(testString);

        assertTrue("Can't find the string!", matcher.find());
        assertEquals("Can't extract the string!", "38097556", matcher.group(1));
    }
}

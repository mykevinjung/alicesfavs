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
//        v-ikarsai: Merging origin/master, resolving merge conflicts
//        v-ikarsai: Merging master, resolving conflicts
//        v-ikarsai: Merge remote-tracking branch 'origin/master' into CSE-2827-Add-Axe-unit-test-to-UITK
//        v-ikarsai: Merge remote-tracking branch 'origin/master' into CSE-2827-Add-Axe-unit-test-to-UITK


        final String testString = "Merge remote-tracking branch 'origin/master' into CSE-2827-Add-Axe-unit-test-to-UITK";
        final String regex = "(merge|merging) .*master.*";

        assertTrue("Does not match!", testString.toLowerCase().matches(regex));
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

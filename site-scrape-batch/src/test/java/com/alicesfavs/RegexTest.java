package com.alicesfavs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by kjung on 6/11/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class RegexTest
{

    @Test
    public void testRegex() {

        final String testString = "http://www.anntaylor.com/sale-blouses-tops/cata000045/?SortByFacetSelectedValue=remove&DocSortOrder=remove&DocSortProp=";
        final String regex = ".*anntaylor.com/(sale-.*|new-to-sale)/cat.*";

        assertTrue("Does not match!", testString.matches(regex));
    }
}

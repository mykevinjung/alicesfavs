package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 11/7/15.
 */
public class SiteNameZtoaComparator extends CreationDateComparator1 implements Comparator<UiProduct>
{
    @Override
    public int compare(UiProduct p1, UiProduct p2)
    {
        final int siteNameCompare = p2.getSiteName().compareTo(p1.getSiteName());
        if (siteNameCompare != 0)
        {
            return siteNameCompare;
        }
        else
        {
            return super.compare(p1, p2);
        }
    }
}

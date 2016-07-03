package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class SiteDisplayOrderComparator extends DecoratedComparator
{

    public SiteDisplayOrderComparator()
    {
        super();
    }

    public SiteDisplayOrderComparator(Comparator<UiProduct> baseComparator)
    {
        super(baseComparator);
    }

    @Override
    protected int compare0(UiProduct p1, UiProduct p2)
    {
        return p1.getSiteStringId().compareTo(p2.getSiteStringId());
    }

}

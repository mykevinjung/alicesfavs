package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class CategoryDisplayOrderComparator extends DecoratedComparator
{

    public CategoryDisplayOrderComparator()
    {
        super();
    }

    public CategoryDisplayOrderComparator(Comparator<UiProduct> baseComparator)
    {
        super(baseComparator);
    }

    @Override
    protected int compare0(UiProduct p1, UiProduct p2)
    {
        return p1.getCategoryDisplayOrder() - p2.getCategoryDisplayOrder();
    }

}

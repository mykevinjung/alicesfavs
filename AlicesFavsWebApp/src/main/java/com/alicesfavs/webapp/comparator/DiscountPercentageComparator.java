package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountPercentageComparator extends DecoratedComparator
{

    public DiscountPercentageComparator()
    {
        super();
    }

    public DiscountPercentageComparator(Comparator<UiProduct> baseComparator)
    {
        super(baseComparator);
    }

    @Override
    protected int compare0(UiProduct p1, UiProduct p2)
    {
        return p2.getDiscountPercentage() - p1.getDiscountPercentage();
    }

}

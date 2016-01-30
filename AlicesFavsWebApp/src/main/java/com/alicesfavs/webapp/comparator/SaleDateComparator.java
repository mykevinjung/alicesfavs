package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Compare products by sale date in descending order, i.e. new sale first
 */
public class SaleDateComparator extends DecoratedComparator
{
    public SaleDateComparator()
    {
        super();
    }

    public SaleDateComparator(Comparator<UiProduct> baseComparator)
    {
        super(baseComparator);
    }

    @Override
    protected int compare0(UiProduct p1, UiProduct p2)
    {
        return p2.getSaleStartDate().compareTo(p1.getSaleStartDate());
    }

}

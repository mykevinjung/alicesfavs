package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountAmountComparator extends DecoratedComparator
{
    public DiscountAmountComparator()
    {
        super();
    }

    public DiscountAmountComparator(Comparator<UiProduct> baseComparator)
    {
        super(baseComparator);
    }

    @Override
    protected int compare0(UiProduct p1, UiProduct p2)
    {
        final double p1amount = p1.getWasPrice() - p1.getPrice();
        final double p2amount = p2.getWasPrice() - p2.getPrice();

        return (int) ((p2amount - p1amount) * 100);
    }
}

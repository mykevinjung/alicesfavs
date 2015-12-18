package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountAmountComparator implements Comparator<UiProduct>
{
    @Override 
    public int compare(UiProduct p1, UiProduct p2)
    {
        if (p1.getWasPrice() != null && p2.getWasPrice() != null)
        {
            final double p1amount = p1.getWasPrice() - p1.getPrice();
            final double p2amount = p2.getWasPrice() - p2.getPrice();

            // remove decimal (cents)
            return (int) ((p2amount - p1amount) * 100);
        }

        return 0;
    }
}

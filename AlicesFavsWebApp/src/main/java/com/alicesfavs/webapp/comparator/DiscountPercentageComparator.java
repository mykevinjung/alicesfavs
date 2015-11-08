package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountPercentageComparator implements Comparator<UiProduct>
{
    @Override 
    public int compare(UiProduct p1, UiProduct p2)
    {
        if (p1.getWasPrice() != null && p2.getWasPrice() != null)
        {
            if (p1.getPrice() != 0 && p2.getPrice() != 0)
            {
                final double p1percentage = (p1.getWasPrice() - p1.getPrice()) / p1.getWasPrice();
                final double p2percentage = (p2.getWasPrice() - p2.getPrice()) / p2.getWasPrice();
                if (p2percentage > p1percentage)
                {
                    return 1;
                }
                else if (p2percentage < p1percentage)
                {
                    return -1;
                }
            }
        }
        else if (p1.getWasPrice() != null)
        {
            return -1;
        }
        else if (p2.getWasPrice() != null)
        {
            return 1;
        }

        return 0;
    }
}

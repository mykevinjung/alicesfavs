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
        final Integer p1percentage = p1.getDiscountPercentage();
        final Integer p2percentage = p2.getDiscountPercentage();
        if (p1percentage != null && p2percentage != null)
        {
            return p2percentage - p1percentage;
        }
        else if (p1percentage != null)
        {
            return -1;
        }
        else if (p2percentage != null)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}

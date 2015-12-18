package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class SaleDateComparator implements Comparator<UiProduct>
{
    @Override 
    public int compare(UiProduct p1, UiProduct p2)
    {
        // TODO Fix this to avoid "Comparison method violates its general contract"
        // Check other comparators too!
        if (p1.getSaleStartDate() != null && p2.getSaleStartDate() != null)
        {
            long epochDay1 = p1.getSaleStartDate().getLong(ChronoField.EPOCH_DAY);
            long epochDay2 = p2.getSaleStartDate().getLong(ChronoField.EPOCH_DAY);
            if (epochDay1 == epochDay2)
            {
                final Integer p1percentage = p1.getDiscountPercentage();
                final Integer p2percentage = p2.getDiscountPercentage();
                if (p1percentage != null && p2percentage != null)
                {
                    return p2percentage - p1percentage;
                }
            }
            else
            {
                return (int) (epochDay2 - epochDay1) * 100;
            }
        }

        return 0;
    }
}

package com.alicesfavs.webapp.comparator;

import com.alicesfavs.datamodel.Product;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountAmountComparator implements Comparator<Product>
{
    @Override public int compare(Product p1, Product p2)
    {
        if (p1.wasPrice != null && p2.wasPrice != null)
        {
            final double p1amount = p1.wasPrice - p1.price;
            final double p2amount = p2.wasPrice - p2.price;
            if (p2amount > p1amount)
            {
                return 1;
            }
            else if (p2amount < p1amount)
            {
                return -1;
            }
        }
        else if (p1.wasPrice != null)
        {
            return -1;
        }
        else if (p2.wasPrice != null)
        {
            return 1;
        }

        return 0;
    }
}

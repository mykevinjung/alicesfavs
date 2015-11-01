package com.alicesfavs.webapp.comparator;

import com.alicesfavs.datamodel.Product;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class DiscountPercentageComparator implements Comparator<Product>
{
    @Override public int compare(Product p1, Product p2)
    {
        if (p1.wasPrice != null && p2.wasPrice != null)
        {
            if (p1.price != 0 && p2.price != 0)
            {
                final double p1percentage = (p1.wasPrice - p1.price) / p1.wasPrice;
                final double p2percentage = (p2.wasPrice - p2.price) / p2.wasPrice;
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

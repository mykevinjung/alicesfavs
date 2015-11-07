package com.alicesfavs.webapp.comparator;

import com.alicesfavs.datamodel.Product;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 11/7/15.
 */
public class CreationDateComparator implements Comparator<Product>
{
    @Override
    public int compare(Product p1, Product p2)
    {
        if (p1.createdDate != null && p2.createdDate != null)
        {
            // if within an hour, they must be in the same batch. use the order it's saved in this case
            return (int) p2.createdDate.until(p1.createdDate, ChronoUnit.DAYS);
        }
        else if (p2.createdDate != null)
        {
            return 1;
        }
        else if (p1.createdDate != null)
        {
            return -1;
        }

        return 0;
    }
}

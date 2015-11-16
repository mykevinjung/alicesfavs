package com.alicesfavs.webapp.comparator;

import com.alicesfavs.datamodel.Product;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 11/7/15.
 */
public class CreationDateComparator2 implements Comparator<Product>
{
    @Override
    public int compare(Product p1, Product p2)
    {
        // if within an hour, they must be in the same batch. use the order it's saved in this case
        if (p1.createdDate.until(p2.createdDate, ChronoUnit.HOURS) == 0)
        {
            return p1.createdDate.compareTo(p2.createdDate);
        }
        else
        {
            // descending order
            return p2.createdDate.compareTo(p1.createdDate);
        }
    }
}

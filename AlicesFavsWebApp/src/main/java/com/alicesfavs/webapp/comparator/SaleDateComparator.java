package com.alicesfavs.webapp.comparator;


import com.alicesfavs.datamodel.Product;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class SaleDateComparator implements Comparator<Product>
{
    @Override 
    public int compare(Product p1, Product p2)
    {
        // TODO if we add Sale Clothing All, then this should be changed!!!
        if (p1.saleStartDate != null && p2.saleStartDate != null)
        {
            // if within an hour, they must be in the same batch. use the order it's saved in this case
            if (p1.saleStartDate.until(p2.saleStartDate, ChronoUnit.HOURS) == 0)
            {
                return p1.saleStartDate.compareTo(p2.saleStartDate);
            }
            else
            {
                // descending order
                return p2.saleStartDate.compareTo(p1.saleStartDate);
            }
        }
        else if (p2.saleStartDate != null)
        {
            return 1;
        }
        else if (p1.saleStartDate != null)
        {
            return -1;
        }

        return 0;
    }
}

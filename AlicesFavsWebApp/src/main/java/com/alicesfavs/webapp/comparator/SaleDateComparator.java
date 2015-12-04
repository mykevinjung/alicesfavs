package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiProduct;

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
        // TODO if we add Sale Clothing All, then this should be changed!!!
        if (p1.getSaleStartDate() != null && p2.getSaleStartDate() != null)
        {
            // if within an hour, they must be in the same batch. use the order it's saved in this case
            if (p1.getSaleStartDate().until(p2.getSaleStartDate(), ChronoUnit.HOURS) == 0)
            {
                return p1.getSaleStartDate().compareTo(p2.getSaleStartDate());
            }
            else
            {
                // descending order
                return p2.getSaleStartDate().compareTo(p1.getSaleStartDate());
            }
        }
        else if (p2.getSaleStartDate() != null)
        {
            return 1;
        }
        else if (p1.getSaleStartDate() != null)
        {
            return -1;
        }

        return 0;
    }
}

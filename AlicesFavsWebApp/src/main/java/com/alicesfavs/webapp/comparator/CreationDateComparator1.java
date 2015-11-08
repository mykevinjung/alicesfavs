package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;

/**
 * Created by kjung on 11/7/15.
 */
public class CreationDateComparator1 implements Comparator<UiProduct>
{
    @Override
    public int compare(UiProduct p1, UiProduct p2)
    {
        if (p1.getCreatedDate() != null && p2.getCreatedDate() != null)
        {
            final long diff = p2.getCreatedDate().until(p1.getCreatedDate(), ChronoUnit.DAYS);
            if (diff == 0)
            {
                return p2.getSiteDisplayWeight() - p1.getSiteDisplayWeight();
            }
            else
            {
                return (int) diff;
            }
        }
        else if (p2.getCreatedDate() != null)
        {
            return 1;
        }
        else if (p1.getCreatedDate() != null)
        {
            return -1;
        }

        return 0;
    }
}

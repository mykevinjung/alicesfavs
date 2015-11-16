package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.time.temporal.ChronoField;
import java.util.Comparator;

/**
 * Created by kjung on 11/7/15.
 */
public class CreationDateComparator1 implements Comparator<UiProduct>
{
    @Override
    public int compare(UiProduct p1, UiProduct p2)
    {
        final long dayDiff =
            p2.getCreatedDate().getLong(ChronoField.EPOCH_DAY) - p1.getCreatedDate().getLong(ChronoField.EPOCH_DAY);
        if (dayDiff == 0)
        {
            // TODO verify this
            final int displayWeightDiff = p2.getSiteDisplayWeight() - p1.getSiteDisplayWeight();
            if (displayWeightDiff == 0)
            {
                return p1.getSiteName().compareTo(p2.getSiteName());
            }
            else
            {
                return displayWeightDiff;
            }
        }
        else
        {
            return (int) dayDiff;
        }
    }
}

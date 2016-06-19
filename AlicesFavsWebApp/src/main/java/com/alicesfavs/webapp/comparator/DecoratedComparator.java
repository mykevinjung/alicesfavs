package com.alicesfavs.webapp.comparator;

import com.alicesfavs.webapp.uimodel.UiProduct;

import java.util.Comparator;

/**
 * Created by kjung on 1/2/16.
 */
public abstract class DecoratedComparator implements Comparator<UiProduct>
{
    protected Comparator<UiProduct> baseComparator;

    public DecoratedComparator()
    {
        // default constructor
    }

    public DecoratedComparator(Comparator<UiProduct> baseComparator)
    {
        this.baseComparator = baseComparator;
    }

    @Override
    public int compare(UiProduct p1, UiProduct p2)
    {
        final int compare = compare0(p1, p2);
        if (compare > 0)
        {
            return 1;
        }
        else if (compare < 0)
        {
            return -1;
        }
        else if (baseComparator != null)
        {
            return baseComparator.compare(p1, p2);
        }
        else
        {
            return 0;
        }
    }

    protected abstract int compare0(UiProduct p1, UiProduct p2);

}

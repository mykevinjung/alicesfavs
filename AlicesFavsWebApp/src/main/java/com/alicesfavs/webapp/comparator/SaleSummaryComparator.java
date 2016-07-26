package com.alicesfavs.webapp.comparator;


import com.alicesfavs.webapp.uimodel.UiSaleSummary;

import java.util.Comparator;

/**
 * Created by kjung on 10/31/15.
 */
public class SaleSummaryComparator implements Comparator<UiSaleSummary>
{

    @Override
    public int compare(UiSaleSummary s1, UiSaleSummary s2)
    {
        final int salePercentage1 = s1.getSaleCountTotal() == 0 ? 0 : s1.getSaleCountThisWeek() * 100 / s1.getSaleCountTotal();
        final int salePercentage2 = s2.getSaleCountTotal() == 0 ? 0 : s2.getSaleCountThisWeek() * 100 / s2.getSaleCountTotal();
        return salePercentage2 - salePercentage1;
    }
}

package com.alicesfavs.webapp.uimodel;

/**
 * Created by kjung on 7/26/16.
 */
public class UiSaleSummary
{
    private int saleCountThisWeek;
    private int saleCountTotal;
    private String siteName;
    private String brandSaleUrl;

    public int getSaleCountThisWeek()
    {
        return saleCountThisWeek;
    }

    public void setSaleCountThisWeek(int saleCountThisWeek)
    {
        this.saleCountThisWeek = saleCountThisWeek;
    }

    public int getSaleCountTotal()
    {
        return saleCountTotal;
    }

    public void setSaleCountTotal(int saleCountTotal)
    {
        this.saleCountTotal = saleCountTotal;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getBrandSaleUrl()
    {
        return brandSaleUrl;
    }

    public void setBrandSaleUrl(String brandSaleUrl)
    {
        this.brandSaleUrl = brandSaleUrl;
    }
}

package com.alicesfavs.webapp.uimodel;

/**
 * Created by kjung on 11/4/15.
 */
public class Page
{

    private final int pageNo;
    private final String pageUrl;

    public Page(int pageNo, String pageUrl)
    {
        this.pageNo = pageNo;
        this.pageUrl = pageUrl;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public String getPageUrl()
    {
        return pageUrl;
    }

}

package com.alicesfavs.webapp.controller;

import com.alicesfavs.webapp.uimodel.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 11/4/15.
 */
public abstract class ProductController
{

    protected static final String PAGE_NUMBER = "pageNo";

    protected static final int DEFAULT_PAGE_SIZE = 60;

    protected int getPageSize()
    {
        return DEFAULT_PAGE_SIZE;
    }

    protected List<Page> getPageList(HttpServletRequest request, int totalPageNo, int currentPageNo)
    {
        final List<Page> pages = new ArrayList<>();
        for (int index = currentPageNo - 2 ; index <= currentPageNo + 2 ; index++)
        {
            if (1 <= index && index <= totalPageNo)
            {
                pages.add(new Page(index, getUrlWithPageNo(request, index)));
            }
        }

        return pages;
    }

    protected String getUrlWithPageNo(HttpServletRequest request, int newPageNo)
    {
        final String oldPageNo = request.getParameter(PAGE_NUMBER);
        if (oldPageNo == null)
        {
            return getUrl(request) + "&" + PAGE_NUMBER + "=" + String.valueOf(newPageNo);
        }
        else
        {
            return getUrl(request).replace(PAGE_NUMBER + "=" + oldPageNo, PAGE_NUMBER + "=" + newPageNo);
        }
    }

    protected String getUrl(HttpServletRequest request)
    {
        final StringBuffer url = request.getRequestURL().append("?");
        final String queryString = request.getQueryString();
        if (queryString != null)
        {
            url.append(queryString);
        }

        return url.toString();
    }

    protected int getTotalPageNo(int totalSize)
    {
        return (totalSize + getPageSize() - 1) / getPageSize();
    }

    protected int getActualPageNo(int requestPageNo, int totalPageNo)
    {
        if (1 <= requestPageNo && requestPageNo <= totalPageNo)
        {
            return requestPageNo;
        }
        else if (requestPageNo > totalPageNo)
        {
            return totalPageNo;
        }
        else
        {
            return 1;
        }
    }

    protected Page getNextPage(List<Page> pageList, int pageNo)
    {
        for (int index = 0 ; index < pageList.size() - 1 ; index++)
        {
            if (pageList.get(index).getPageNo() == pageNo)
            {
                return pageList.get(index + 1);
            }
        }

        return null;
    }

    protected Page getPrevPage(List<Page> pageList, int pageNo)
    {
        for (int index = 1 ; index < pageList.size() ; index++)
        {
            if (pageList.get(index).getPageNo() == pageNo)
            {
                return pageList.get(index - 1);
            }
        }

        return null;
    }

}

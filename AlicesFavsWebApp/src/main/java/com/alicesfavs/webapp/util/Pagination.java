package com.alicesfavs.webapp.util;

import com.alicesfavs.webapp.uimodel.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 11/6/15.
 */
public class Pagination
{

    private final int pageSize;
    private final int totalCount;
    private final String pageNoParam;
    private final HttpServletRequest request;

    public Pagination(int pageSize, int totalCount, HttpServletRequest request, String pageNoParam)
    {
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.pageNoParam = pageNoParam;
        this.request = request;
    }

    public List<Page> getPageList(int currentPageNo)
    {
        final List<Page> pages = new ArrayList<>();
        final String url = getUrl();
        final String oldPageNo = request.getParameter(pageNoParam);
        final int totalPageNo = getTotalPageNo();
        for (int index = currentPageNo - 2 ; index <= currentPageNo + 2 ; index++)
        {
            if (1 <= index && index <= totalPageNo)
            {
                pages.add(new Page(index, getUrlWithPageNo(url, oldPageNo, index)));
            }
        }

        return pages;
    }

    public int getTotalPageNo()
    {
        // should have at least one page
        final int totalPageNo = (totalCount + pageSize - 1) / pageSize;
        return (totalPageNo > 0) ? totalPageNo : 1;
    }

    public int getActualPageNo(int requestPageNo)
    {
        final int totalPageNo = getTotalPageNo();
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

    public Page getNextPage(List<Page> pageList, int pageNo)
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

    public Page getPrevPage(List<Page> pageList, int pageNo)
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

    public int getStartIndex(int pageNo)
    {
        return (pageNo - 1) * pageSize;
    }

    public int getEndIndex(int pageNo)
    {
        return Math.min(pageNo * pageSize, totalCount);
    }

    private String getUrlWithPageNo(String url, String oldPageNo, int newPageNo)
    {
        if (oldPageNo == null)
        {
            return url + "&" + pageNoParam + "=" + String.valueOf(newPageNo);
        }
        else
        {
            return url.replace(pageNoParam + "=" + oldPageNo, pageNoParam + "=" + newPageNo);
        }
    }

    private String getUrl()
    {
        final StringBuffer url = request.getRequestURL().append("?");
        final String queryString = request.getQueryString();
        if (queryString != null)
        {
            url.append(queryString);
        }

        return url.toString();
    }

}

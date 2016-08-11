package com.alicesfavs.datamodel;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by kjung on 8/10/16.
 */
public class SearchResultList<E> extends ArrayList<E>
{
    private int searchResultCount = 0;

    public SearchResultList()
    {
        // default
    }

    public SearchResultList(int initialCapacity)
    {
        super(initialCapacity);
    }

    public SearchResultList(Collection<? extends E> c)
    {
        super(c);
    }

    public int getSearchResultCount()
    {
        return searchResultCount;
    }

    public void setSearchResultCount(int searchResultCount)
    {
        this.searchResultCount = searchResultCount;
    }
}

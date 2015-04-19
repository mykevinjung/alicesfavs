package com.alicesfavs.datamodel;

import org.springframework.util.Assert;

/**
 * Category information extracted from a site
 */
public class CategoryExtract
{
    public final String name;
    public String url;

    public CategoryExtract(String name)
    {
        this(name, null);
    }

    public CategoryExtract(String name, String url)
    {
        Assert.hasText(name);
        this.name = name;
        this.url = url;
    }

    public String toString()
    {
        return "CategoryExtract[name=" + name + ", url=" + url + "]";
    }
}

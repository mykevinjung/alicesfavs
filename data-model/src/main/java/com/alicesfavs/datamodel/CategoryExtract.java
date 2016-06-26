package com.alicesfavs.datamodel;

import org.apache.commons.lang3.builder.HashCodeBuilder;
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

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 31).append(name).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof CategoryExtract))
            return false;
        if (obj == this)
            return true;

        final CategoryExtract other = (CategoryExtract) obj;
        return name.equals(other.name) && url.equals(other.url);
    }
}

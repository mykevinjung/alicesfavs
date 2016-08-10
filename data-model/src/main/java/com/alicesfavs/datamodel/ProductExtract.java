package com.alicesfavs.datamodel;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * Product information extracted from a site
 */
public class ProductExtract
{

    public final String id;
    public String name;
    public String price;
    public String wasPrice;
    public String url;
    public String imageUrl;
    public boolean soldOut;

    // for full text search
    public Set<String> aliceCategoryNames = new HashSet<>();
    public Set<String> categoryNames = new HashSet<>();

    public ProductExtract(String id)
    {
        Assert.hasText(id);
        this.id = id;
    }

    @Override
    public String toString()
    {
        return "ProductExtract[id=" + id + ", name=" + name + ", price=" + price + ", wasPrice=" + wasPrice
                + ", url=" + url + ", imageUrl=" + imageUrl + ", soldOut=" + soldOut + "]";
    }

    public void addAliceCategory(AliceCategory aliceCategory)
    {
        aliceCategoryNames.add(aliceCategory.name);
    }

    public void addCategory(Category category)
    {
        if (category.categoryExtract1 != null && StringUtils.hasText(category.categoryExtract1.name))
        {
            categoryNames.add(category.categoryExtract1.name);
        }
        if (category.categoryExtract2 != null && StringUtils.hasText(category.categoryExtract2.name))
        {
            categoryNames.add(category.categoryExtract2.name);
        }
        if (category.categoryExtract3 != null && StringUtils.hasText(category.categoryExtract3.name))
        {
            categoryNames.add(category.categoryExtract3.name);
        }
    }
}

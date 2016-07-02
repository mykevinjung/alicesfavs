package com.alicesfavs.datamodel;

import org.springframework.util.Assert;

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

    public ProductExtract(String id)
    {
        Assert.hasText(id);
        this.id = id;
    }

    public String toString()
    {
        return "ProductExtract[id=" + id + ", name=" + name + ", price=" + price + ", wasPrice=" + wasPrice
                + ", url=" + url + ", imageUrl=" + imageUrl + ", soldOut=" + soldOut + "]";
    }

}

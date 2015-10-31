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
    public String brandName;
    public String url;
    public String imageUrl;

    public ProductExtract(String id)
    {
        Assert.hasText(id);
        this.id = id;
    }

    public String toString()
    {
        return "ProductExtract[id=" + id + ", name=" + name + ", price=" + price + ", wasPrice=" + wasPrice
                + ", brandName=" + brandName + ", url=" + url + ", imageUrl=" + imageUrl + "]";
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }

    public String getWasPrice()
    {
        return wasPrice;
    }

    public void setWasPrice(String wasPrice)
    {
        this.wasPrice = wasPrice;
    }

    public String getBrandName()
    {
        return brandName;
    }

    public void setBrandName(String brandName)
    {
        this.brandName = brandName;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }
}

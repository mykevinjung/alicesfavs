package com.alicesfavs.webapp.uimodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 11/6/15.
 */
public class UiProduct
{

    // product info
    private String name;
    private String url;
    private String imageUrl;
    private String extractedPrice;
    private String extractedWasPrice;
    private Double price;
    private Double wasPrice;
    private LocalDateTime createdDate;
    private LocalDateTime saleStartDate;

    // site info
    private String siteName;
    private String siteStringId;
    private Integer siteDisplayWeight;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getExtractedPrice()
    {
        return extractedPrice;
    }

    public void setExtractedPrice(String extractedPrice)
    {
        this.extractedPrice = extractedPrice;
    }

    public String getExtractedWasPrice()
    {
        return extractedWasPrice;
    }

    public void setExtractedWasPrice(String extractedWasPrice)
    {
        this.extractedWasPrice = extractedWasPrice;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getWasPrice()
    {
        return wasPrice;
    }

    public void setWasPrice(Double wasPrice)
    {
        this.wasPrice = wasPrice;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getSiteStringId()
    {
        return siteStringId;
    }

    public void setSiteStringId(String siteStringId)
    {
        this.siteStringId = siteStringId;
    }

    public Integer getSiteDisplayWeight()
    {
        return siteDisplayWeight;
    }

    public void setSiteDisplayWeight(Integer siteDisplayWeight)
    {
        this.siteDisplayWeight = siteDisplayWeight;
    }

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDateTime getSaleStartDate()
    {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDateTime saleStartDate)
    {
        this.saleStartDate = saleStartDate;
    }

}

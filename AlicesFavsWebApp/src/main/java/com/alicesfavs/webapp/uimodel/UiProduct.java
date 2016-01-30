package com.alicesfavs.webapp.uimodel;

import java.time.LocalDate;

/**
 * Created by kjung on 11/6/15.
 */
public class UiProduct
{

    // product info
    private final String encryptedId;
    private String itemId;
    private String name;
    private String url;
    private String imageUrl;
    private String priceWithCurrency;
    private String wasPriceWithCurrency;
    private Double price;
    private Double wasPrice;
    private LocalDate createdDate;
    private LocalDate saleStartDate;

    // site info
    private String siteName;
    private String siteStringId;
    private Integer siteDisplayWeight;

    public UiProduct(String encryptedId)
    {
        this.encryptedId = encryptedId;
    }

    public int getDiscountPercentage()
    {
        if (price != null && wasPrice != null && wasPrice != 0)
        {
            return (int) ((wasPrice - price) * 100 / wasPrice);
        }

        return 0;
    }

    public String getEncryptedId()
    {
        return encryptedId;
    }

    public String getItemId()
    {
        return itemId;
    }

    public void setItemId(String itemId)
    {
        this.itemId = itemId;
    }

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

    public String getPriceWithCurrency()
    {
        return priceWithCurrency;
    }

    public void setPriceWithCurrency(String priceWithCurrency)
    {
        this.priceWithCurrency = priceWithCurrency;
    }

    public String getWasPriceWithCurrency()
    {
        return wasPriceWithCurrency;
    }

    public void setWasPriceWithCurrency(String wasPriceWithCurrency)
    {
        this.wasPriceWithCurrency = wasPriceWithCurrency;
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

    public LocalDate getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDate getSaleStartDate()
    {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDate saleStartDate)
    {
        this.saleStartDate = saleStartDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UiProduct product = (UiProduct) o;

        return encryptedId.equals(product.encryptedId);

    }

    @Override
    public int hashCode()
    {
        return encryptedId.hashCode();
    }
}

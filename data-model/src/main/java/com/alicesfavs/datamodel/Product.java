package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

import org.springframework.util.Assert;

public class Product extends Extractable
{

    public final long siteId;
    public final ProductExtract productExtract;

    /**
     * The current price.
     */
    public Double price;

    /**
     * The was price when a product is on sale showing both was price and now price. Was price will be populated when
     * site displays the was price, most likely during a sale. Was price will be nullified when was price is removed
     * from the site, most likely end of sale.
     */
    public Double wasPrice;

    /**
     * The regular price that is not shown as was price on site, but is derived based on our price change history.
     */
    public Double regularPrice;

    /**
     * Sale start date. This can be either the date when a product is first found in sale category together with was
     * price and now price, or the date when price has been first dropped meaningfully from the regular price. Sale
     * start date will be nullified either when existing was price is removed (if was price still exists, this date is
     * not nullified), or price goes back up meaningfully or current price becomes a regular price after certain period
     * of time. That is, if was price exists, this is updated by was price. If not, this is updated by our logic.
     */
    public LocalDateTime saleStartDate;

    /**
     * The date when this product is first found or the date when the product price was last changed.
     */
    public LocalDateTime priceChangedDate;

    public String storedImagePath;

    public Product(long id, LocalDateTime createdDate, long siteId, ProductExtract productExtract)
    {
        super(id, createdDate);
        Assert.notNull(productExtract);
        this.siteId = siteId;
        this.productExtract = productExtract;
    }

    public Product(ModelBase modelBase, long siteId, ProductExtract productExtract)
    {
        super(modelBase);
        Assert.notNull(productExtract);
        this.siteId = siteId;
        this.productExtract = productExtract;
    }

    public Product(Extractable extractable, long siteId, ProductExtract productExtract)
    {
        super(extractable);
        Assert.notNull(productExtract);
        this.siteId = siteId;
        this.productExtract = productExtract;
    }

    public String toString()
    {
        return "Product[" + super.toString() + ", siteId=" + siteId + ", productExtract=" + productExtract + ", price="
            + price + ", wasPrice=" + wasPrice + ", regularPrice=" + regularPrice + ", saleStartDate="
            + saleStartDate + ", storedImagePath=" + storedImagePath + "]";
    }

    public long getSiteId()
    {
        return siteId;
    }

    public ProductExtract getProductExtract()
    {
        return productExtract;
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

    public Double getRegularPrice()
    {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice)
    {
        this.regularPrice = regularPrice;
    }

    public LocalDateTime getSaleStartDate()
    {
        return saleStartDate;
    }

    public void setSaleStartDate(LocalDateTime saleStartDate)
    {
        this.saleStartDate = saleStartDate;
    }

    public LocalDateTime getPriceChangedDate()
    {
        return priceChangedDate;
    }

    public void setPriceChangedDate(LocalDateTime priceChangedDate)
    {
        this.priceChangedDate = priceChangedDate;
    }

    public String getStoredImagePath()
    {
        return storedImagePath;
    }

    public void setStoredImagePath(String storedImagePath)
    {
        this.storedImagePath = storedImagePath;
    }
}

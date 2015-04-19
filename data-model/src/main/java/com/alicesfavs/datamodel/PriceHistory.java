package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

public class PriceHistory extends ModelBase
{

    public long productId;
    public String oldPriceExtract;
    public String newPriceExtract;
    public Double oldPrice;
    public Double newPrice;

    public PriceHistory(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public PriceHistory(ModelBase modelBase, long productId, String oldPriceExtract, String newPriceExtract,
            Double oldPrice, Double newPrice)
    {
        super(modelBase);
        this.productId = productId;
        this.oldPriceExtract = oldPriceExtract;
        this.newPriceExtract = newPriceExtract;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public PriceHistory(long id, LocalDateTime createdDate, LocalDateTime updatedDate, long productId,
            String oldPriceExtract, String newPriceExtract, Double oldPrice, Double newPrice)
    {
        super(id, createdDate, updatedDate);
        this.productId = productId;
        this.oldPriceExtract = oldPriceExtract;
        this.newPriceExtract = newPriceExtract;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
    }

    public String toString()
    {
        return "PriceHistory[" + super.toString() + ", productId=" + productId + ", oldPriceExtract=" + oldPriceExtract
                + ", newPriceExtract=" + newPriceExtract + ", oldPrice=" + oldPrice + ", newPrice=" + newPrice + "]";
    }

}

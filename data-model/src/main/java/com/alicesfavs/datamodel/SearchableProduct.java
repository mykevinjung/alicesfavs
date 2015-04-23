package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * This class should have info that is needed for search indexing and also needed for UI display.
 */
public class SearchableProduct
{

    public long id;

    // site info
    public Country country;
    public long siteId;
    public String siteName;
    public Integer siteDisplayWeight;

    // category info
    public String[] categoryNames;

    // product info
    public String productId;
    public String name;
    public String priceText;
    public String wasPriceText;
    public String brandName;
    public String url;
    public String imageUrl;
    public Double price;
    public Double wasPrice;
    public LocalDateTime saleStartDate;

}

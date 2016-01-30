package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * This class should have info that is needed for search indexing and also needed for UI display.
 */
public class SearchableProduct
{

    // this is Product.id
    public long id;

    // site info
    public Country country;
    public long siteId;
    public String siteName;
    public Integer siteDisplayWeight;

    // category info
    public String[] categoryNames;

    // product info
    public String productExtractId;
    public String name;
    public String price;
    public String wasPrice;
    public String url;
    public String imageUrl;
    public LocalDateTime saleStartDate;

    // to be able to show new arrivals first
    public LocalDateTime createdDate;

}

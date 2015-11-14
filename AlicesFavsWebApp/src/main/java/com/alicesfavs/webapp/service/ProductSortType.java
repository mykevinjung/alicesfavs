package com.alicesfavs.webapp.service;

/**
 * Created by kjung on 10/31/15.
 */
public enum ProductSortType
{
    DATE("date"), AMOUNT("amount"), PERCENTAGE("percentage"), BRAND_ATOZ("brand-atoz"), BRAND_ZTOA("brand-ztoa");

    private final String code;

    private ProductSortType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public static ProductSortType fromCode(String code)
    {
        for (ProductSortType sortType : ProductSortType.values())
        {
            if (sortType.getCode().equals(code))
            {
                return sortType;
            }
        }

        return null;
    }

}

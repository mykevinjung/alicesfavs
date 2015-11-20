package com.alicesfavs.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by kjung on 11/6/15.
 */
@Configuration
@PropertySource("classpath:/env/${EXECUTION_ENV:dev}/WebAppConfig.properties")
public class WebAppConfig
{

    @Value(value = "${site.cache.timeoutseconds}")
    private int siteCacheTimeoutSeconds;

    @Value(value = "${saleproduct.cache.timeoutseconds}")
    private int saleProductCacheTimeoutSeconds;

    @Value(value = "${newproduct.cache.timeoutseconds}")
    private int newProductCacheTimeoutSeconds;

    @Value(value = "${saleproduct.pagesize}")
    private int saleProductPageSize;

    @Value(value = "${newproduct.pagesize}")
    private int newProductPageSize;

    @Value(value = "${saleproduct.cache.maxcount}")
    private int saleProductCacheCount;

    @Value(value = "${newproduct.cache.maxcount}")
    private int newProductCacheCount;

    @Value(value = "${newproduct.dayafter}")
    private int newProductDayAfter;

    @Value(value = "${refresh.allowedaddr}")
    private String refreshAllowedAddr;

    public int getSiteCacheTimeoutSeconds()
    {
        return siteCacheTimeoutSeconds;
    }

    public int getSaleProductCacheTimeoutSeconds()
    {
        return saleProductCacheTimeoutSeconds;
    }

    public int getNewProductCacheTimeoutSeconds()
    {
        return newProductCacheTimeoutSeconds;
    }

    public int getSaleProductPageSize()
    {
        return saleProductPageSize;
    }

    public int getNewProductPageSize()
    {
        return newProductPageSize;
    }

    public int getSaleProductCacheCount()
    {
        return saleProductCacheCount;
    }

    public int getNewProductCacheCount()
    {
        return newProductCacheCount;
    }

    public int getNewProductDayAfter()
    {
        return newProductDayAfter;
    }

    public String getRefreshAllowedAddr()
    {
        return refreshAllowedAddr;
    }

    public String[] getRefreshAllowedAddrArray()
    {
        return refreshAllowedAddr != null ? refreshAllowedAddr.split(",") : new String[0];
    }

}

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

    @Value(value = "${newproduct.dateafter}")
    private int newProductDateAfter;

    @Value(value = "${refresh.allowedaddr}")
    private String refreshAllowedAddr;

    public int getSiteCacheTimeoutSeconds()
    {
        return siteCacheTimeoutSeconds;
    }

    public void setSiteCacheTimeoutSeconds(int siteCacheTimeoutSeconds)
    {
        this.siteCacheTimeoutSeconds = siteCacheTimeoutSeconds;
    }

    public int getSaleProductCacheTimeoutSeconds()
    {
        return saleProductCacheTimeoutSeconds;
    }

    public void setSaleProductCacheTimeoutSeconds(int saleProductCacheTimeoutSeconds)
    {
        this.saleProductCacheTimeoutSeconds = saleProductCacheTimeoutSeconds;
    }

    public int getNewProductCacheTimeoutSeconds()
    {
        return newProductCacheTimeoutSeconds;
    }

    public void setNewProductCacheTimeoutSeconds(int newProductCacheTimeoutSeconds)
    {
        this.newProductCacheTimeoutSeconds = newProductCacheTimeoutSeconds;
    }

    public int getSaleProductPageSize()
    {
        return saleProductPageSize;
    }

    public void setSaleProductPageSize(int saleProductPageSize)
    {
        this.saleProductPageSize = saleProductPageSize;
    }

    public int getNewProductPageSize()
    {
        return newProductPageSize;
    }

    public void setNewProductPageSize(int newProductPageSize)
    {
        this.newProductPageSize = newProductPageSize;
    }

    public int getSaleProductCacheCount()
    {
        return saleProductCacheCount;
    }

    public void setSaleProductCacheCount(int saleProductCacheCount)
    {
        this.saleProductCacheCount = saleProductCacheCount;
    }

    public int getNewProductCacheCount()
    {
        return newProductCacheCount;
    }

    public void setNewProductCacheCount(int newProductCacheCount)
    {
        this.newProductCacheCount = newProductCacheCount;
    }

    public int getNewProductDateAfter()
    {
        return newProductDateAfter;
    }

    public void setNewProductDateAfter(int newProductDateAfter)
    {
        this.newProductDateAfter = newProductDateAfter;
    }

    public String getRefreshAllowedAddr()
    {
        return refreshAllowedAddr;
    }

    public String[] getRefreshAllowedAddrArray()
    {
        return refreshAllowedAddr != null ? refreshAllowedAddr.split(",") : new String[0];
    }

    public void setRefreshAllowedAddr(String refreshAllowedAddr)
    {
        this.refreshAllowedAddr = refreshAllowedAddr;
    }

}

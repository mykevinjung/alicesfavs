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

    @Value(value = "${saleproduct.pagesize}")
    private int saleProductPageSize;

    @Value(value = "${saleproduct.cache.maxcount}")
    private int saleProductCacheCount;

    @Value(value = "${home.category.productsize}")
    private int categoryProductSize;

    @Value(value = "${refresh.allowedaddr}")
    private String refreshAllowedAddr;

    @Value(value = "${encryptor.key}")
    private String encryptorKey;

    @Value(value = "${encryptor.initVector}")
    private String encryptorInitVector;

    public int getSiteCacheTimeoutSeconds()
    {
        return siteCacheTimeoutSeconds;
    }

    public int getSaleProductCacheTimeoutSeconds()
    {
        return saleProductCacheTimeoutSeconds;
    }

    public int getSaleProductPageSize()
    {
        return saleProductPageSize;
    }

    public int getSaleProductCacheCount()
    {
        return saleProductCacheCount;
    }

    public int getCategoryProductSize()
    {
        return categoryProductSize;
    }

    public String getRefreshAllowedAddr()
    {
        return refreshAllowedAddr;
    }

    public String[] getRefreshAllowedAddrArray()
    {
        return refreshAllowedAddr != null ? refreshAllowedAddr.split(",") : new String[0];
    }

    public String getEncryptorKey()
    {
        return encryptorKey;
    }

    public String getEncryptorInitVector()
    {
        return encryptorInitVector;
    }

}

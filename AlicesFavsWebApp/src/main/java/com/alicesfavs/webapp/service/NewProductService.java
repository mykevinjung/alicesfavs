package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.CreationDateComparator;
import com.alicesfavs.webapp.comparator.DiscountAmountComparator;
import com.alicesfavs.webapp.comparator.DiscountPercentageComparator;
import com.alicesfavs.webapp.comparator.SaleDateComparator;
import com.alicesfavs.webapp.config.WebAppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class NewProductService
{

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private WebAppConfig webAppConfig;

    private Map<Long, CachedList<Product>> siteProductMap = new Hashtable<>();

    public List<Product> getNewProducts(AliceCategory category)
    {
        final List<Site> siteList = siteManager.getSites(category);
        final List<Product> sortBySaleDate = getNewProducts(siteList);

        return sortBySaleDate;
    }

    private List<Product> getNewProducts(List<Site> siteList)
    {
        final List<Product> productList = new ArrayList<>();
        for (Site site : siteList)
        {
            productList.addAll(getNewProductsFromCache(site));
        }
        productList.sort(new CreationDateComparator());

        return productList;
    }

    /**
     * Returns sale products sorted by sale date descending, i.e. newest first
     */
    private List<Product> getNewProductsFromCache(Site site)
    {
        CachedList<Product> cachedProductList = siteProductMap.get(site.id);
        if (shouldRefresh(cachedProductList))
        {
            final CachedList<Product> newProductList = new CachedList<>();
            newProductList.list = getNewProductsFromDatabase(site);
            newProductList.cachedTime = LocalDateTime.now();
            siteProductMap.put(site.id, newProductList);
            cachedProductList = newProductList;
        }

        return cachedProductList.list;
    }

    /**
     * Returns new products sorted by creation date descending, i.e. newest first
     */
    private List<Product> getNewProductsFromDatabase(Site site)
    {
        final List<Product> productList = productService.searchNewProducts(site.id,
            LocalDateTime.now().minus(webAppConfig.getNewProductDateAfter(), ChronoUnit.DAYS));
        productList.sort(new CreationDateComparator());
        if (productList.size() > webAppConfig.getNewProductCacheCount())
        {
            return productList.subList(0, webAppConfig.getNewProductCacheCount());
        }

        return productList;
    }

    private boolean shouldRefresh(CachedList<Product> cachedProductList)
    {
        return cachedProductList == null
            || cachedProductList.cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig
            .getNewProductCacheTimeoutSeconds();
    }

}

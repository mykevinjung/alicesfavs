package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.DiscountAmountComparator;
import com.alicesfavs.webapp.comparator.DiscountPercentageComparator;
import com.alicesfavs.webapp.comparator.SaleDateComparator;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class SaleProductService
{

    @Autowired
    private ProductService productService;

    @Autowired
    private WebAppConfig webAppConfig;

    private Map<Long, CachedList<UiProduct>> siteProductMap = new Hashtable<>();

    public List<UiProduct> getSaleProducts(Site site, ProductSortType productSortType)
    {
        final List<UiProduct> sortBySaleDate = getSaleProductsFromCache(site);
        if (productSortType == ProductSortType.AMOUNT)
        {
            final List<UiProduct> sortByAmount = new ArrayList<>(sortBySaleDate);
            sortByAmount.sort(new DiscountAmountComparator());
            return sortByAmount;
        }
        else if (productSortType == ProductSortType.PERCENTAGE)
        {
            final List<UiProduct> sortByPercentage = new ArrayList<>(sortBySaleDate);
            sortByPercentage.sort(new DiscountPercentageComparator());
            return sortByPercentage;
        }

        return sortBySaleDate;
    }

    public synchronized void refresh(Site site)
    {
        final CachedList<UiProduct> newCachedList = new CachedList<>();
        newCachedList.list = getSaleProductsFromDatabase(site);
        newCachedList.cachedTime = LocalDateTime.now();
        siteProductMap.put(site.id, newCachedList);
    }

    /**
     * Returns sale products sorted by sale date descending, i.e. newest first
     */
    private List<UiProduct> getSaleProductsFromCache(Site site)
    {
        CachedList<UiProduct> cachedProductList = siteProductMap.get(site.id);
        if (shouldRefresh(cachedProductList))
        {
            refresh(site);
            cachedProductList = siteProductMap.get(site.id);
        }

        return cachedProductList.list;
    }

    /**
     * Returns sale products sorted by sale date descending, i.e. newest first
     */
    private List<UiProduct> getSaleProductsFromDatabase(Site site)
    {
        final List<Product> productList = productService.searchSaleProducts(site.id);
        productList.sort(new SaleDateComparator());
        final int endIndex = Math.min(productList.size(), webAppConfig.getNewProductCacheCount());

        return ModelConverter.convertProductList(site, productList, 0, endIndex);
    }

    private boolean shouldRefresh(CachedList<UiProduct> cachedProductList)
    {
        return cachedProductList == null
            || cachedProductList.cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig
            .getSaleProductCacheTimeoutSeconds();
    }

}

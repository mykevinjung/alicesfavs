package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.DiscountAmountComparator;
import com.alicesfavs.webapp.comparator.DiscountPercentageComparator;
import com.alicesfavs.webapp.comparator.SaleDateComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class SaleService
{

    private static final int CACHE_TIMEOUT_SECONDS = 60 * 60;

    private static final int MAX_CACHE_ITEMS = 1000;

    @Autowired
    private ProductService productService;

    private Map<Long, CachedList<Product>> siteProductMap = new Hashtable<>();

    public List<Product> getSaleProducts(Site site, ProductSortType productSortType)
    {
        final List<Product> sortBySaleDate = getSaleProductsFromCache(site);
        if (productSortType == ProductSortType.PRICE)
        {
            final List<Product> sortByPrice = new ArrayList<>(sortBySaleDate);
            sortByPrice.sort(new DiscountAmountComparator());
            return sortByPrice;
        }
        else if (productSortType == ProductSortType.PERCENTAGE)
        {
            final List<Product> sortByPercentage = new ArrayList<>(sortBySaleDate);
            sortByPercentage.sort(new DiscountPercentageComparator());
            return sortByPercentage;
        }

        return sortBySaleDate;
    }

    /**
     * Returns sale products sorted by sale date descending, i.e. newest first
     */
    private List<Product> getSaleProductsFromCache(Site site)
    {
        CachedList<Product> cachedProductList = siteProductMap.get(site.id);
        if (shouldRefresh(cachedProductList))
        {
            final CachedList<Product> newProductList = new CachedList<>();
            newProductList.list = getSaleProductsFromDatabase(site);
            newProductList.cachedTime = LocalDateTime.now();
            siteProductMap.put(site.id, newProductList);
            cachedProductList = newProductList;
        }

        return cachedProductList.list;
    }

    /**
     * Returns sale products sorted by sale date descending, i.e. newest first
     */
    private List<Product> getSaleProductsFromDatabase(Site site)
    {
        final List<Product> productList = productService.selectSaleProducts(site.id);
        productList.sort(new SaleDateComparator());
        if (productList.size() > MAX_CACHE_ITEMS)
        {
            return productList.subList(0, MAX_CACHE_ITEMS);
        }

        return productList;
    }

    private boolean shouldRefresh(CachedList<Product> cachedProductList)
    {
        return cachedProductList == null
            || cachedProductList.cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > CACHE_TIMEOUT_SECONDS;
    }

}

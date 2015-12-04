package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.DiscountAmountComparator;
import com.alicesfavs.webapp.comparator.DiscountPercentageComparator;
import com.alicesfavs.webapp.comparator.SaleDateComparator;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.util.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SaleProductService.class);

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteManager siteManager;

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

    public List<UiProduct> getNewSaleProducts(AliceCategory category)
    {
        // collect all sales of the category
        final List<Site> siteList = siteManager.getSites(category);
        final List<UiProduct> allProductList = new ArrayList<>();
        for (Site site : siteList)
        {
            allProductList.addAll(getSaleProducts(site, ProductSortType.DATE));
        }

        // get the latest sales
        allProductList.sort(new SaleDateComparator());
        int latestIndex = 0;
        for (int day = 1 ; day < 15 ; day++)
        {
            latestIndex = getLatestIndex(allProductList, day);
            if (latestIndex >= webAppConfig.getCategoryProductSize())
            {
                break;
            }
        }

        if (latestIndex >= webAppConfig.getCategoryProductSize())
        {
            final List<UiProduct> newProductList = allProductList.subList(0, latestIndex);
            newProductList.sort(new DiscountPercentageComparator());
            return newProductList.subList(0, webAppConfig.getCategoryProductSize());
        }
        else
        {
            allProductList.sort(new DiscountPercentageComparator());
            final int endIndex = Math.min(allProductList.size(), webAppConfig.getCategoryProductSize());
            return allProductList.subList(0, endIndex);
        }
    }

    public int getTotalSalesCount()
    {
        int count = 0;
        for (CachedList<UiProduct> cachedList : getSiteProductMap().values())
        {
            count += cachedList.list.size();
        }

        return count;
    }

    public synchronized void refresh(Site site)
    {
        LOGGER.info("Refreshing sale product list for " + site.stringId);
        final CachedList<UiProduct> newCachedList = new CachedList<>();
        newCachedList.list = getSaleProductsFromDatabase(site);
        newCachedList.cachedTime = LocalDateTime.now();
        siteProductMap.put(site.id, newCachedList);
    }

    private Map<Long, CachedList<UiProduct>> getSiteProductMap()
    {
        return siteProductMap;
    }

    private int getLatestIndex(List<UiProduct> productList, int day)
    {
        for (int index = 0 ; index < productList.size() ; index++)
        {
            if (productList.get(index).getSaleStartDate().until(LocalDateTime.now(), ChronoUnit.MINUTES) > 24 * 60 * day)
            {
                return index;
            }
        }

        return productList.size();
    }

    private List<UiProduct> filterLatest(List<UiProduct> productList, int day)
    {
        final List<UiProduct> latestProductList = new ArrayList<>();
        for (UiProduct uiProduct : productList)
        {
            if (uiProduct.getSaleStartDate().until(LocalDateTime.now(), ChronoUnit.HOURS) <= 24 * day)
            {
                latestProductList.add(uiProduct);
            }
        }

        return latestProductList;
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
        final List<UiProduct> uiProductList = ModelConverter
            .convertProductList(site, productService.searchSaleProducts(site.id));
        uiProductList.sort(new SaleDateComparator());
        final int endIndex = Math.min(uiProductList.size(), webAppConfig.getSaleProductCacheCount());

        return uiProductList.subList(0, endIndex);
    }

    private boolean shouldRefresh(CachedList<UiProduct> cachedProductList)
    {
        return cachedProductList == null
            || cachedProductList.cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig
            .getSaleProductCacheTimeoutSeconds();
    }

}

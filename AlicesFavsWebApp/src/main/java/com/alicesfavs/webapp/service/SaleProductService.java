package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.CategoryService;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.DiscountAmountComparator;
import com.alicesfavs.webapp.comparator.DiscountPercentageComparator;
import com.alicesfavs.webapp.comparator.SaleDateComparator;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.uimodel.SiteProduct;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.util.ModelConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
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
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private WebAppConfig webAppConfig;

    private Map<Site, SiteProduct> siteProductMap = new Hashtable<>();

    private Map<AliceCategory, List<UiProduct>> newSaleProductMap = new HashMap<>();

    public List<UiProduct> getSaleProducts(Site site, AliceCategory aliceCategory)
    {
        return getSaleProductsFromCache(site, aliceCategory);
    }

    public List<UiProduct> getSaleProducts(List<Site> siteList, AliceCategory aliceCategory, ProductSortType productSortType)
    {
        final List<UiProduct> productList = new ArrayList<>();
        for (Site site: siteList)
        {
            addAllIfNotExist(productList, getSaleProducts(site, aliceCategory));
        }
        sortSaleProducts(productList, productSortType);

        return productList;
    }

    private void sortSaleProducts(List<UiProduct> uiProductList, ProductSortType productSortType)
    {
        if (productSortType == ProductSortType.DATE)
        {
            uiProductList.sort(new SaleDateComparator(new DiscountPercentageComparator()));
        }
        else if (productSortType == ProductSortType.AMOUNT)
        {
            uiProductList.sort(new DiscountAmountComparator(new SaleDateComparator()));
        }
        else if (productSortType == ProductSortType.PERCENTAGE)
        {
            uiProductList.sort(new DiscountPercentageComparator(new SaleDateComparator()));
        }
    }

    public boolean hasSaleProducts(Site site, AliceCategory aliceCategory)
    {
        final SiteProduct siteProduct = siteProductMap.get(site);
        return siteProduct != null && siteProduct.hasProducts(aliceCategory);
    }

    public List<UiProduct> getNewSaleProducts(AliceCategory aliceCategory)
    {
        List<UiProduct> productList = newSaleProductMap.get(aliceCategory);
        if (productList != null && productList.size() > 0)
        {
            return productList;
        }

        // collect first items from each site
        productList = new ArrayList<>();
        final List<Site> siteList = getAliceCategorySites(aliceCategory);
        final int todayEpochDay = (int) LocalDate.now().toEpochDay();
        for (int index = 0; ; index++)
        {
            final Site site = siteList.get((todayEpochDay + index) % siteList.size());
            final List<UiProduct> siteProductList = getSaleProducts(site, aliceCategory);
            final int productIndex = webAppConfig.getHomeCategoryProductSize() / siteList.size();
            if (productIndex < siteProductList.size())
            {
                productList.add(siteProductList.get(productIndex));
            }
            if (productList.size() == webAppConfig.getHomeCategoryProductSize())
            {
                break;
            }
        }
        newSaleProductMap.put(aliceCategory, productList);

        return productList;
    }

    public synchronized void refresh(Site site)
    {
        LOGGER.info("Refreshing sale product list for " + site.stringId);
        final SiteProduct siteProduct = getSaleProductsFromDatabase(site);
        siteProductMap.put(site, siteProduct);
        for (AliceCategory aliceCategory : siteManager.getAliceCategoryList())
        {
            if (hasSaleProducts(site, aliceCategory))
            {
                newSaleProductMap.remove(aliceCategory);
            }
        }
    }

    public synchronized void refreshNewSaleProductMap()
    {
        newSaleProductMap.clear();
    }

    private List<Site> getAliceCategorySites(AliceCategory aliceCategory)
    {
        final List<Site> siteList = new ArrayList<>();
        for (Site site : siteManager.getSites())
        {
            if (hasSaleProducts(site, aliceCategory))
            {
                siteList.add(site);
            }
        }

        return siteList;
    }

    private List<UiProduct> getSaleProductsFromCache(Site site, AliceCategory aliceCategory)
    {
        SiteProduct siteProduct = siteProductMap.get(site);
        if (shouldRefresh(siteProduct))
        {
            refresh(site);
            siteProduct = siteProductMap.get(site);
        }

        final List<UiProduct> productList = new ArrayList<>();
        for (Map.Entry<Category, List<UiProduct>> entry : siteProduct.getCategoryProductMap().entrySet())
        {
            if (aliceCategory == null || entry.getKey().isAliceCategory(aliceCategory))
            {
                addAllIfNotExist(productList, entry.getValue());
            }
        }
        sortSaleProducts(productList, ProductSortType.DATE);

        return productList;
    }

    // we can use LinkedHashSet later
    private void addAllIfNotExist(List<UiProduct> productList, List<UiProduct> c)
    {
        for (UiProduct product : c)
        {
            if (!productList.contains(product))
            {
                productList.add(product);
            }
        }
    }

    private SiteProduct getSaleProductsFromDatabase(Site site)
    {
        final Map<Category, List<UiProduct>> categoryUiProductMap = new LinkedHashMap<>();
        final SiteProduct siteProduct = new SiteProduct(site, categoryUiProductMap);
        final List<Category> categoryList = categoryService.findSiteCategories(site);
        if (categoryList.size() > 0)
        {
            final Map<Category, List<Product>> categoryProductMap = productService.searchSaleProducts(categoryList);
            for (Map.Entry<Category, List<Product>> entry : categoryProductMap.entrySet())
            {
                final AliceCategory aliceCategory = siteManager.getAliceCategory(entry.getKey());
                if (aliceCategory != null)
                {
                    final List<UiProduct> uiProductList =
                        ModelConverter.convertProductList(site, entry.getValue(), aliceCategory);
                    if (uiProductList.size() > 0)
                    {
                        categoryUiProductMap.put(entry.getKey(), uiProductList);
                    }
                }
            }
        }
        siteProduct.setCachedTime(LocalDateTime.now());

        return siteProduct;
    }

    private boolean shouldRefresh(SiteProduct siteProduct)
    {
        return siteProduct == null
            || siteProduct.getCachedTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig
            .getSaleProductCacheTimeoutSeconds();
    }

}

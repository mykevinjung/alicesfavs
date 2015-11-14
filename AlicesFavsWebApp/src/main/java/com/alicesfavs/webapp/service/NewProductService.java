package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.comparator.CreationDateComparator1;
import com.alicesfavs.webapp.comparator.CreationDateComparator2;
import com.alicesfavs.webapp.comparator.SiteNameAtozComparator;
import com.alicesfavs.webapp.comparator.SiteNameZtoaComparator;
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
public class NewProductService
{

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private WebAppConfig webAppConfig;

    private Map<Long, CachedList<UiProduct>> siteProductMap = new Hashtable<>();

    public List<UiProduct> getNewProducts(AliceCategory category, ProductSortType productSortType)
    {
        final List<Site> siteList = siteManager.getSites(category);
        final List<UiProduct> sortByCreationDate = getNewProducts(siteList);
        if (productSortType == ProductSortType.BRAND_ATOZ)
        {
            final List<UiProduct> sortByBrandAtoz = new ArrayList<>(sortByCreationDate);
            sortByBrandAtoz.sort(new SiteNameAtozComparator());
            return sortByBrandAtoz;
        }
        else if (productSortType == ProductSortType.BRAND_ZTOA)
        {
            final List<UiProduct> sortByBrandZtoa = new ArrayList<>(sortByCreationDate);
            sortByBrandZtoa.sort(new SiteNameZtoaComparator());
            return sortByBrandZtoa;
        }

        return sortByCreationDate;
    }

    public List<UiProduct> getNewProducts(Site site)
    {
        return getNewProductsFromCache(site);
    }

    private List<UiProduct> getNewProducts(List<Site> siteList)
    {
        final List<UiProduct> productList = new ArrayList<>();
        for (Site site : siteList)
        {
            productList.addAll(getNewProductsFromCache(site));
        }
        productList.sort(new CreationDateComparator1());

        return productList;
    }

    /**
     * Returns new products sorted by creation date descending, i.e. newest first
     */
    private List<UiProduct> getNewProductsFromCache(Site site)
    {
        CachedList<UiProduct> cachedProductList = siteProductMap.get(site.id);
        if (shouldRefresh(cachedProductList))
        {
            final CachedList<UiProduct> newProductList = new CachedList<>();
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
    private List<UiProduct> getNewProductsFromDatabase(Site site)
    {
        final List<Product> productList = productService.searchNewProducts(site.id,
            LocalDateTime.now().minus(webAppConfig.getNewProductDateAfter(), ChronoUnit.DAYS));
        productList.sort(new CreationDateComparator2());
        final int endIndex = Math.min(productList.size(), webAppConfig.getNewProductCacheCount());

        return ModelConverter.convertProductList(site, productList, 0, endIndex);
    }

    private boolean shouldRefresh(CachedList<UiProduct> cachedProductList)
    {
        return cachedProductList == null
            || cachedProductList.cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig
            .getNewProductCacheTimeoutSeconds();
    }

}

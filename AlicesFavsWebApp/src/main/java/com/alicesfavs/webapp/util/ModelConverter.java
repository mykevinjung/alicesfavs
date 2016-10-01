package com.alicesfavs.webapp.util;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Country;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.SearchResultList;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.uimodel.UiSite;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

/**
 * Created by kjung on 11/6/15.
 */
public class ModelConverter
{

    public static UiSite convertSite(Site site)
    {
        final UiSite uiSite = new UiSite();
        uiSite.setStringId(site.stringId);
        uiSite.setDisplayName(site.displayName);
        uiSite.setNewSite(site.createdDate.until(LocalDateTime.now(), ChronoUnit.DAYS) <= 10);

        return uiSite;
    }

    public static List<UiSite> convertSiteList(List<Site> siteList)
    {
        final List<UiSite> uiSiteList = new ArrayList<>(siteList.size());
        for (Site site : siteList)
        {
            uiSiteList.add(convertSite(site));
        }

        return uiSiteList;
    }

    public static  UiProduct convertProduct(Site site, Product product, AliceCategory aliceCategory,
        int categoryDisplayOrder, int productDisplayOrder)
    {
        final UiProduct uiProduct = new UiProduct(product.id);
        uiProduct.setItemId(product.productExtract.id);
        uiProduct.setName(product.productExtract.name);
        uiProduct.setAliceCategory(aliceCategory.name.toLowerCase());
        uiProduct.setUrl(product.productExtract.url);
        uiProduct.setImageUrl(product.productExtract.imageUrl);
        uiProduct.setPrice(product.price);
        uiProduct.setWasPrice(product.wasPrice);
        uiProduct.setPriceWithCurrency(product.productExtract.price);
        if (StringUtils.hasText(product.productExtract.wasPrice))
        {
            uiProduct.setWasPriceWithCurrency(product.productExtract.wasPrice);
        }
        else if (product.wasPrice != null)
        {
            uiProduct.setWasPriceWithCurrency(formatWithCurrency(product.wasPrice, site));
        }
        uiProduct.setCategoryDisplayOrder(categoryDisplayOrder);
        uiProduct.setProductDisplayOrder(productDisplayOrder);
        uiProduct.setSiteName(site.displayName);
        uiProduct.setSiteStringId(site.stringId);
        uiProduct.setSiteDisplayWeight(site.displayWeight);
        uiProduct.setCreatedDate(LocalDateUtils.getLocalDate(product.createdDate));
        uiProduct.setSaleStartDate(LocalDateUtils.getLocalDate(product.priceChangedDate));

        return uiProduct;
    }

    public static UiProduct convertProduct(Product product, Site site)
    {
        final UiProduct uiProduct = new UiProduct(product.id);
        uiProduct.setItemId(product.productExtract.id);
        uiProduct.setName(product.productExtract.name);
        uiProduct.setUrl(product.productExtract.url);
        uiProduct.setImageUrl(product.productExtract.imageUrl);
        uiProduct.setPrice(product.price);
        uiProduct.setWasPrice(product.wasPrice);
        uiProduct.setPriceWithCurrency(product.productExtract.price);
        if (StringUtils.hasText(product.productExtract.wasPrice))
        {
            uiProduct.setWasPriceWithCurrency(product.productExtract.wasPrice);
        }
        else if (product.wasPrice != null)
        {
            uiProduct.setWasPriceWithCurrency(formatWithCurrency(product.wasPrice, site));
        }
        uiProduct.setSiteName(site.displayName);
        uiProduct.setSiteStringId(site.stringId);
        uiProduct.setSiteDisplayWeight(site.displayWeight);
        uiProduct.setCreatedDate(LocalDateUtils.getLocalDate(product.createdDate));
        uiProduct.setSaleStartDate(LocalDateUtils.getLocalDate(product.priceChangedDate));

        return uiProduct;
    }

    public static List<UiProduct> convertProductList(int categoryDisplayOrder, Site site, List<Product> productList, AliceCategory aliceCategory)
    {
        final List<UiProduct> uiProductList = new ArrayList<>();
        int productDisplayOrder = 0;
        for (int index = 0 ; index < productList.size() ; index++)
        {
            // filter out sold out product
            final Product product = productList.get(index);
            if (!product.productExtract.soldOut)
            {
                uiProductList.add(convertProduct(site, product, aliceCategory, categoryDisplayOrder, ++productDisplayOrder));
            }
        }

        return uiProductList;
    }

    public static SearchResultList<UiProduct> convertProductList(SearchResultList<Product> productList, List<Site> allSiteList)
    {
        final SearchResultList<UiProduct> uiProductList = new SearchResultList<>();
        for (Product product : productList)
        {
            final Site site = getProductSite(product, allSiteList);
            if (site != null)
            {
                uiProductList.add(convertProduct(product, site));
            }
        }
        uiProductList.setSearchResultCount(productList.getSearchResultCount());

        return uiProductList;
    }

    private static Site getProductSite(Product product, List<Site> allSiteList)
    {
        for(Site site : allSiteList)
        {
            if (site.id == product.siteId)
            {
                return site;
            }
        }
        return null;
    }

    private static String formatWithCurrency(double price, Site site)
    {
        if (site.country == Country.US)
        {
            Currency usd = Currency.getInstance("USD");
            NumberFormat format = NumberFormat.getCurrencyInstance(java.util.Locale.US);
            format.setCurrency(usd);
            return format.format(price);
        }
        else
        {
            throw new UnsupportedOperationException("Non-US site is not supported");
        }
    }

}

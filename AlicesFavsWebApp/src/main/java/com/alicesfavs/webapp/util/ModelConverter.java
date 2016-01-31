package com.alicesfavs.webapp.util;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Country;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.exception.EncryptionException;
import com.alicesfavs.webapp.service.Encryptor;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.uimodel.UiSite;
import org.springframework.util.StringUtils;

import java.text.NumberFormat;
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

    public static  UiProduct convertProduct(Site site, Product product, AliceCategory aliceCategory)
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
        uiProduct.setSiteName(site.displayName);
        uiProduct.setSiteStringId(site.stringId);
        uiProduct.setSiteDisplayWeight(site.displayWeight);
        uiProduct.setCreatedDate(product.createdDate.toLocalDate());
        uiProduct.setSaleStartDate(product.saleStartDate.toLocalDate());

        return uiProduct;
    }

    public static List<UiProduct> convertProductList(Site site, List<Product> productList, AliceCategory aliceCategory)
    {
        return convertProductList(site, productList, aliceCategory, 0, productList.size());
    }

    public static List<UiProduct> convertProductList(Site site, List<Product> productList, AliceCategory aliceCategory,
        int startIndex, int endIndex)
    {
        final List<UiProduct> uiProductList = new ArrayList<>(endIndex - startIndex);
        for (int index = startIndex ; index < endIndex ; index++)
        {
            uiProductList.add(convertProduct(site, productList.get(index), aliceCategory));
        }

        return uiProductList;
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

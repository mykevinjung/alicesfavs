package com.alicesfavs.webapp.util;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.uimodel.UiSite;

import java.util.ArrayList;
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

    public static  UiProduct convertProduct(Site site, Product product)
    {
        final UiProduct uiProduct = new UiProduct();
        uiProduct.setName(product.productExtract.name);
        uiProduct.setUrl(product.productExtract.url);
        uiProduct.setImageUrl(product.productExtract.imageUrl);
        uiProduct.setPrice(product.price);
        uiProduct.setWasPrice(product.wasPrice);
        uiProduct.setExtractedPrice(product.productExtract.price);
        uiProduct.setExtractedWasPrice(product.productExtract.wasPrice);
        uiProduct.setSiteName(site.displayName);
        uiProduct.setSiteStringId(site.stringId);
        uiProduct.setSiteDisplayWeight(site.displayWeight);

        return uiProduct;
    }

    public static List<UiProduct> convertProductList(Site site, List<Product> productList, int startIndex, int endIndex)
    {
        final List<UiProduct> uiProductList = new ArrayList<>(endIndex - startIndex);
        for (int index = startIndex ; index < endIndex ; index++)
        {
            uiProductList.add(convertProduct(site, productList.get(index)));
        }

        return uiProductList;
    }

}

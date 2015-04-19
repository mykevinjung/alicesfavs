package com.alicesfavs.sitescraper.extractspec;

import java.util.List;

public class ProductExtractSpec
{

    // spec of an element that contains a list of products
    public ElementExtractSpec containerSpec;

    // spec to extract a product element
    public ElementExtractSpec productSpec;

    // spec to extract product id data
    public ElementDataSpec idSpec;

    // spec to extract product name data
    public ElementDataSpec nameSpec;

    // spec to extract product url data
    public ElementDataSpec urlSpec;

    // spec to extract product image url data
    public ElementDataSpec imageUrlSpec;

    // spec to extract product brand name data
    public ElementDataSpec brandNameSpec;

    // spec to extract product price data
    public List<ElementDataSpec> priceSpecList;

    // spec to extract product was price data
    public List<ElementDataSpec> wasPriceSpecList;

    public String toString()
    {
        return "ProductExtractSpec[containerSpec=" + containerSpec + ", productSpec=" + productSpec + ", idSpec="
                + idSpec + ", nameSpec=" + nameSpec + ", urlSpec=" + urlSpec + ", brandNameSpec=" + brandNameSpec
                + ", imageUrlSpec=" + imageUrlSpec + ", priceSpecList=" + priceSpecList + ", wasPriceSpecList="
                + wasPriceSpecList + "]";
    }

}

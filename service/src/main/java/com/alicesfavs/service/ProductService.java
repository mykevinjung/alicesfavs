package com.alicesfavs.service;

import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

public interface ProductService
{
    /**
     * Returns a map of extracted product id and product
     */
    Map<String, Product> saveProduct(long jobId, long siteId, Map<String, List<ProductExtract>> productExtractMap);

    Product saveProduct(long jobId, long siteId, List<ProductExtract> productExtractList);

    Product findProduct(long siteId, String siteProductId);

    int markNotFoundProduct(long jobId, long siteId);

}

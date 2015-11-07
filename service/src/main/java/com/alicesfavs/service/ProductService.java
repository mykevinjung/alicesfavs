package com.alicesfavs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

public interface ProductService
{
    /**
     * Returns a map of extracted product id and product
     */
    Map<String, Product> saveProduct(long jobId, long siteId, ExtractStatus extractStatus,
        Map<String, List<ProductExtract>> productExtractMap);

    Product saveProduct(long jobId, long siteId, ExtractStatus extractStatus, List<ProductExtract> productExtractList);

    Product findProduct(long siteId, String siteProductId);

    int markNotFoundProduct(long jobId, long siteId);

    List<Product> searchSaleProducts(long siteId);

    List<Product> searchNewProducts(long siteId, LocalDateTime afterCreatedDate);

}

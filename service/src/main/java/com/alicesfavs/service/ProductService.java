package com.alicesfavs.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.datamodel.Site;

public interface ProductService
{
    /**
     * Returns a map of extracted product id and product
     */
    Map<String, Product> saveProduct(Job job, Site site, ExtractStatus extractStatus,
        Map<String, List<ProductExtract>> productExtractMap);

    Product saveProduct(Job job, Site site, ExtractStatus extractStatus, List<ProductExtract> productExtractList);

    Product findProduct(long siteId, String siteProductId);

    int markNotFoundProduct(Job job, Site site);

    List<Product> searchSaleProducts(long siteId);

    List<Product> searchNewProducts(long siteId, LocalDateTime afterCreatedDate);

}

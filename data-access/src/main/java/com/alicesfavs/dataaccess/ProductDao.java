package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

public interface ProductDao
{

    Product insertProduct(long siteId, ProductExtract productExtract, Double price, Double wasPrice,
        LocalDateTime priceChangedDate, LocalDateTime saleStartDate, ExtractStatus extractStatus,
        Long extractJobId, LocalDateTime extractedDate);

    Product updateProduct(Product product);

    Product selectProductById(Long productId);

    Product selectProductById(Long siteId, String productExtractId);

    int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus newStatus);

    List<Product> selectSaleProducts(long siteId, ExtractStatus status);

    Map<Category, List<Product>> selectSaleProducts(List<Category> categoryList);

    List<Product> selectNewProducts(long siteId, ExtractStatus status, LocalDateTime afterCreatedDate);

}

package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;
import java.util.List;

import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

public interface ProductDao
{

    Product insertProduct(long siteId, ProductExtract productExtract, Double price, Double wasPrice,
        Double regularPrice, LocalDateTime priceChangedDate, LocalDateTime saleStartDate, String storedImagePath,
        ExtractStatus extractStatus, Long extractJobId, LocalDateTime extractedDate);

    Product updateProduct(Product product);

    Product selectProductById(Long siteId, String productExtractId);

    int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus newStatus);

    List<Product> selectSaleProducts(long siteId, ExtractStatus status);

}

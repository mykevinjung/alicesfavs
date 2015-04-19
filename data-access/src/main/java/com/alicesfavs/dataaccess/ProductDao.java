package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;

import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

public interface ProductDao
{

    Product insertProduct(long siteId, ProductExtract productExtract, Double price, Double wasPrice,
            Double regularPrice, LocalDateTime saleStartDate, String storedImagePath, ExtractStatus extractStatus,
            Long extractJobId, LocalDateTime extractedDate);

    void updateProduct(Product product);

    Product selectProductById(Long siteId, String productId);

    int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus extractStatus);

}

package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;

import com.alicesfavs.datamodel.CategoryProduct;
import com.alicesfavs.datamodel.ExtractStatus;

public interface CategoryProductDao
{

    CategoryProduct insertCategoryProduct(long categoryId, long productId, Integer displayOrder,
            ExtractStatus extractStatus, Long extractJobId, LocalDateTime extractedDate);

    CategoryProduct selectCategoryProduct(long categoryId, long productId);

    CategoryProduct updateCategoryProduct(CategoryProduct categoryProduct);

    int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus extractStatus);

}

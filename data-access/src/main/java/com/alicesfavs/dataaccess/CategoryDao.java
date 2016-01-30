package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;
import java.util.List;

import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ExtractStatus;

public interface CategoryDao
{

    Category insertCategory(long siteId, Long aliceCategoryId, CategoryExtract categoryExtract1, CategoryExtract categoryExtract2,
            CategoryExtract categoryExtract3, Integer displayOrder, ExtractStatus extractStatus, Long extractJobId,
            LocalDateTime extractedDate);

    Category updateCategory(Category category);

    Category selectCategoryByName(long siteId, String categoryName1, String categoryName2, String categoryName3);

    List<Category> selectCategoryBySiteId(long siteId, ExtractStatus extractStatus);

    int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus currentStatus, ExtractStatus newStatus);

}

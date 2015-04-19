package com.alicesfavs.service;

import java.util.List;

import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.CategoryProduct;
import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.Product;

public interface CategoryService
{

    void saveCategoryExtract(long jobId, long siteId, MultirootTree<CategoryExtract> categoryExtracts);

    List<Category> getSiteCategories(long siteId);

    CategoryProduct saveCategoryProduct(Category category, Product product, Integer displayOrder, long extractJobId);

    int markNotFoundCategory(long jobId, long siteId);

    int markNotFoundCategoryProduct(long jobId, long siteId);

}

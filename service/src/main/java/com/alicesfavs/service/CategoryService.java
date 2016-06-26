package com.alicesfavs.service;

import java.util.List;

import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.CategoryProduct;
import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;

public interface CategoryService
{

    List<Category> saveCategoryExtract(long jobId, Site site, MultirootTree<CategoryExtract> categoryExtracts);

    /**
     * Returns the categories of the site in display order
     */
    List<Category> findSiteCategories(Site site);

    CategoryProduct saveCategoryProduct(Category category, Product product, Integer displayOrder, long extractJobId);

    int markNotFoundCategory(long jobId, long siteId);

    int markNotFoundCategoryProduct(long jobId, long siteId);

}

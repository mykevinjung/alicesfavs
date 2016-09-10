package com.alicesfavs.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.alicesfavs.datamodel.Site;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.dataaccess.CategoryDao;
import com.alicesfavs.dataaccess.CategoryProductDao;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.CategoryProduct;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.MultirootTree;
import com.alicesfavs.datamodel.MultirootTree.Node;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.service.CategoryService;

@Component("categoryService")
public class CategoryServiceImpl implements CategoryService
{

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryProductDao categoryProductDao;

    public List<Category> saveCategoryExtract(long jobId, Site site, MultirootTree<CategoryExtract> categoryExtracts)
    {
        final List<Node<CategoryExtract>> leafCategories = categoryExtracts.getAllLeafNodes();
        final LocalDateTime now = LocalDateTime.now();
        final List<Category> categories = new ArrayList<>();
        final List<Category> existingCategories = categoryDao.selectAllCategoryBySiteId(site.id);
        for (int index = 0; index < leafCategories.size(); index++)
        {
            final CategoryExtract[] categoryHierarchy = getCategoryHierarchy(leafCategories.get(index));
            final Category category = saveCategoryExtract(jobId, site, index, now, categoryHierarchy, existingCategories);
            categories.add(category);
        }

        return categories;
    }

    public List<Category> findSiteCategories(Site site)
    {
        return categoryDao.selectCategoryBySiteId(site.id, ExtractStatus.EXTRACTED);
    }

    @Override
    public CategoryProduct saveCategoryProduct(Category category, Product product, Integer displayOrder,
            long extractJobId)
    {
        CategoryProduct categoryProduct = categoryProductDao.selectCategoryProduct(category.id, product.id);
        if (categoryProduct == null)
        {
            categoryProduct = categoryProductDao.insertCategoryProduct(category.id, product.id, displayOrder,
                    ExtractStatus.EXTRACTED, extractJobId, LocalDateTime.now());
        }
        else
        {
            categoryProduct.displayOrder = displayOrder;
            categoryProduct.extractJobId = extractJobId;
            categoryProduct.extractedDate = LocalDateTime.now();
            categoryProduct.extractStatus = ExtractStatus.EXTRACTED;
            categoryProductDao.updateCategoryProduct(categoryProduct);
        }

        return categoryProduct;
    }

    @Override
    public int markNotFoundCategory(long jobId, long siteId)
    {
        return categoryDao.updateExtractStatus(siteId, jobId, ExtractStatus.EXTRACTED, ExtractStatus.NOT_FOUND);
    }

    @Override
    public int markNotFoundCategoryProduct(long jobId, long siteId)
    {
        return categoryProductDao.updateExtractStatus(siteId, jobId, ExtractStatus.EXTRACTED, ExtractStatus.NOT_FOUND);
    }

    private Category saveCategoryExtract(long jobId, Site site, int displayOrder, LocalDateTime extractTime,
            CategoryExtract[] categoryHierarchy, List<Category> existingCategories)
    {
        final Category existingCategory = findSameCategory(categoryHierarchy, existingCategories);
        if (existingCategory != null)
        {
            if (existingCategory.aliceCategoryId == null) {
                existingCategory.aliceCategoryId = site.defaultAliceCategoryId;
            }
            existingCategory.displayOrder = displayOrder;
            existingCategory.extractedDate = extractTime;
            existingCategory.extractJobId = jobId;
            existingCategory.extractStatus = ExtractStatus.EXTRACTED;
            categoryDao.updateCategory(existingCategory);
            return existingCategory;
        }
        else
        {
            return categoryDao.insertCategory(site.id, site.defaultAliceCategoryId, categoryHierarchy[0],
                categoryHierarchy[1], categoryHierarchy[2], displayOrder, ExtractStatus.EXTRACTED, jobId, extractTime);
        }
    }

    private Category findSameCategory(CategoryExtract[] categoryHierarchy, List<Category> existingCategoryList)
    {
        for (Category category : existingCategoryList)
        {
            if (isSameCategory(categoryHierarchy, category))
            {
                return category;
            }
        }
        return null;
    }

    private boolean isSameCategory(CategoryExtract[] categoryHierarchy, Category existingCategory)
    {
        if (existingCategory != null)
        {
            return isSameCategoryExtract(categoryHierarchy[0], existingCategory.categoryExtract1)
                && isSameCategoryExtract(categoryHierarchy[1], existingCategory.categoryExtract2)
                && isSameCategoryExtract(categoryHierarchy[2], existingCategory.categoryExtract3);
        }
        return false;
    }

    private boolean isSameCategoryExtract(CategoryExtract categoryExtract1, CategoryExtract categoryExtract2)
    {
        return (categoryExtract1 == null && categoryExtract2 == null)
            || (categoryExtract1 != null && categoryExtract1.equals(categoryExtract2));
    }

    private String getCategoryName(CategoryExtract categoryExtract)
    {
        return categoryExtract != null ? categoryExtract.name : null;
    }

    private CategoryExtract[] getCategoryHierarchy(Node<CategoryExtract> leafNode)
    {
        final int depth = leafNode.getDepth();
        Validate.isTrue(depth <= 3, "Category depth is greater than 3 for " + leafNode.data);
        final CategoryExtract[] categoryHierarchy = new CategoryExtract[3];
        Node<CategoryExtract> temp = leafNode;
        for (int index = depth; index > 0; index--)
        {
            categoryHierarchy[index - 1] = temp.data;
            temp = temp.parent;
        }

        return categoryHierarchy;
    }

}

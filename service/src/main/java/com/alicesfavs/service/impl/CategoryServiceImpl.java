package com.alicesfavs.service.impl;

import java.time.LocalDateTime;
import java.util.List;

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

    public void saveCategoryExtract(long jobId, long siteId, MultirootTree<CategoryExtract> categoryExtracts)
    {
        final List<Node<CategoryExtract>> leafCategories = categoryExtracts.getLeafNodes();
        final LocalDateTime now = LocalDateTime.now();
        for (int index = 0; index < leafCategories.size(); index++)
        {
            final CategoryExtract[] categoryHierarchy = getCategoryHierarchy(leafCategories.get(index));
            saveCategoryExtract(jobId, siteId, index, now, categoryHierarchy);
        }
    }

    public List<Category> getSiteCategories(long siteId)
    {
        return categoryDao.selectCategoryBySiteId(siteId, ExtractStatus.EXTRACTED);
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
        return categoryDao.updateExtractStatus(siteId, jobId, ExtractStatus.NOT_FOUND);
    }

    @Override
    public int markNotFoundCategoryProduct(long jobId, long siteId)
    {
        return categoryProductDao.updateExtractStatus(siteId, jobId, ExtractStatus.NOT_FOUND);
    }

    private void saveCategoryExtract(long jobId, long siteId, int displayOrder, LocalDateTime extractTime,
            CategoryExtract[] categoryHierarchy)
    {
        Category existingCategory = categoryDao.selectCategoryByName(siteId, getCategoryName(categoryHierarchy[0]),
                getCategoryName(categoryHierarchy[1]), getCategoryName(categoryHierarchy[2]));
        if (existingCategory == null)
        {
            categoryDao.insertCategory(siteId, categoryHierarchy[0], categoryHierarchy[1], categoryHierarchy[2],
                    displayOrder, ExtractStatus.EXTRACTED, jobId, extractTime);
        }
        else
        {
            copyCategoryExtractUrl(existingCategory.categoryExtract1, categoryHierarchy[0]);
            copyCategoryExtractUrl(existingCategory.categoryExtract2, categoryHierarchy[1]);
            copyCategoryExtractUrl(existingCategory.categoryExtract3, categoryHierarchy[2]);
            existingCategory.displayOrder = displayOrder;
            existingCategory.extractedDate = extractTime;
            existingCategory.extractJobId = jobId;
            existingCategory.extractStatus = ExtractStatus.EXTRACTED;
            categoryDao.updateCategory(existingCategory);
        }
    }

    private String getCategoryName(CategoryExtract categoryExtract)
    {
        return categoryExtract != null ? categoryExtract.name : null;
    }

    private void copyCategoryExtractUrl(CategoryExtract existingCategory, CategoryExtract newCategory)
    {
        if (existingCategory != null && newCategory != null)
        {
            existingCategory.url = newCategory.url;
        }
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

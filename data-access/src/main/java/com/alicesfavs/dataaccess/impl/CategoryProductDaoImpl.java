package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.CategoryProductDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.CategoryProduct;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Extractable;
import com.alicesfavs.datamodel.ModelBase;

@Repository
public class CategoryProductDaoImpl implements CategoryProductDao
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryProductDaoImpl.class);

    @Autowired
    private DaoSupport<CategoryProduct> daoSupport;

    private static final String INSERT = "INSERT INTO CATEGORY_PRODUCT (CATEGORY_ID, PRODUCT_ID, DISPLAY_ORDER, "
        + "EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE) VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE CATEGORY_PRODUCT SET CATEGORY_ID = ?, PRODUCT_ID = ?, "
        + "DISPLAY_ORDER = ?, EXTRACT_STATUS = ?, EXTRACT_JOB_ID = ?, EXTRACTED_DATE = ? WHERE ID = ?";

    private static final String UPDATE_EXTRACT_STATUS = "UPDATE CATEGORY_PRODUCT SET EXTRACT_STATUS = ? "
        + "WHERE EXISTS (SELECT * FROM CATEGORY C WHERE C.SITE_ID = ? AND C.ID = CATEGORY_PRODUCT.CATEGORY_ID "
        + "AND EXTRACT_JOB_ID <> ?)";

    private static final String SELECT_BY_IDS = "SELECT ID, CATEGORY_ID, PRODUCT_ID, DISPLAY_ORDER, "
        + "EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, CREATED_DATE, UPDATED_DATE FROM CATEGORY_PRODUCT "
        + "WHERE CATEGORY_ID = ? AND PRODUCT_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.BIGINT, Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.BIGINT, Types.BIGINT, Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.BIGINT, Types.BIGINT };

    private static final int[] UPDATE_EXTRACT_STATUS_PARAM_TYPES =
        { Types.INTEGER, Types.BIGINT, Types.BIGINT };

    public CategoryProduct insertCategoryProduct(long categoryId, long productId, Integer displayOrder,
        ExtractStatus extractStatus, Long extractJobId, LocalDateTime extractedDate)
    {
        final Object[] params =
            { categoryId, productId, displayOrder, extractStatus.getCode(), extractJobId,
                DateTimeUtils.toTimestamp(extractedDate) };
        final ModelBase modelBase = daoSupport.insert(INSERT, INSERT_PARAM_TYPES, params);

        final CategoryProduct categoryProduct = new CategoryProduct(modelBase, categoryId, productId);
        categoryProduct.displayOrder = displayOrder;
        categoryProduct.extractStatus = extractStatus;
        categoryProduct.extractJobId = extractJobId;
        categoryProduct.extractedDate = extractedDate;

        return categoryProduct;
    }

    public void updateCategoryProduct(CategoryProduct categoryProduct)
    {
        final Object[] params =
            { categoryProduct.categoryId, categoryProduct.productId, categoryProduct.displayOrder,
                categoryProduct.extractStatus.getCode(), categoryProduct.extractJobId,
                DateTimeUtils.toTimestamp(categoryProduct.extractedDate), categoryProduct.id };
        categoryProduct.updatedDate = daoSupport.update(UPDATE, UPDATE_PARAM_TYPES, params);
    }

    public CategoryProduct selectCategoryProduct(long categoryId, long productId)
    {
        final Object[] params =
            { categoryId, productId };

        try
        {
            return daoSupport.selectObject(SELECT_BY_IDS, SELECT_PARAM_TYPES, params, new CategoryProductRowMapper());
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            if (e.getActualSize() > 1)
            {
                throw new DaoException("Schema Alert - more than one record found: selectCategoryProduct ["
                    + categoryId + ", " + productId + "]", e);
            }
            return null;
        }
    }

    public int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus extractStatus)
    {
        final Object[] params =
            { extractStatus.getCode(), siteId, excludingJobId };

        return daoSupport.updateMultiple(UPDATE_EXTRACT_STATUS, UPDATE_EXTRACT_STATUS_PARAM_TYPES, params);
    }

    private class CategoryProductRowMapper implements RowMapper<CategoryProduct>
    {
        public CategoryProduct mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final Extractable extractable = RowMapperUtils.mapRowToExtractable(rs, rowNum);
            final long categoryId = rs.getLong("CATEGORY_ID");
            final long productId = rs.getLong("PRODUCT_ID");
            final Integer displayOrder = rs.getInt("DISPLAY_ORDER");

            return new CategoryProduct(extractable, categoryId, productId, displayOrder);
        }
    }

}

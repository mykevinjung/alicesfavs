package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.alicesfavs.dataaccess.CategoryDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Extractable;
import com.alicesfavs.datamodel.ModelBase;

@Repository
public class CategoryDaoImpl implements CategoryDao
{

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryDaoImpl.class);

    private static final String INSERT_CATEGORY = "INSERT INTO CATEGORY (SITE_ID, NAME_EXTRACT1, URL_EXTRACT1, "
            + "NAME_EXTRACT2, URL_EXTRACT2, NAME_EXTRACT3, URL_EXTRACT3, DISPLAY_ORDER, EXTRACT_STATUS, EXTRACT_JOB_ID, "
            + "EXTRACTED_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_CATEGORY = "UPDATE CATEGORY SET SITE_ID = ?, NAME_EXTRACT1 = ?, URL_EXTRACT1 = ?, "
            + "NAME_EXTRACT2 = ?, URL_EXTRACT2 = ?, NAME_EXTRACT3 = ?, URL_EXTRACT3 = ?, "
            + "DISPLAY_ORDER = ?, EXTRACT_STATUS = ?, EXTRACT_JOB_ID = ?, EXTRACTED_DATE = ? WHERE ID = ?";

    private static final String UPDATE_EXTRACT_STATUS = "UPDATE CATEGORY SET EXTRACT_STATUS = ? "
            + "WHERE SITE_ID = ? AND EXTRACT_JOB_ID <> ?";

    private static final String SELECT_CATEGORY_BY_SITE_ID = "SELECT ID, SITE_ID, NAME_EXTRACT1, URL_EXTRACT1, "
            + "NAME_EXTRACT2, URL_EXTRACT2, NAME_EXTRACT3, URL_EXTRACT3, DISPLAY_ORDER, EXTRACT_STATUS, EXTRACT_JOB_ID, "
            + "EXTRACTED_DATE, CREATED_DATE, UPDATED_DATE FROM CATEGORY WHERE SITE_ID = ? AND EXTRACT_STATUS = ?";

    private static final String SELECT_CATEGORY_BY_NAME = "SELECT ID, SITE_ID, NAME_EXTRACT1, URL_EXTRACT1, "
            + "NAME_EXTRACT2, URL_EXTRACT2, NAME_EXTRACT3, URL_EXTRACT3, DISPLAY_ORDER, EXTRACT_STATUS, EXTRACT_JOB_ID, "
            + "EXTRACTED_DATE, CREATED_DATE, UPDATED_DATE FROM CATEGORY WHERE SITE_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] UPDATE_EXTRACT_STATUS_PARAM_TYPES =
    { Types.INTEGER, Types.BIGINT, Types.BIGINT };

    @Autowired
    private DaoSupport<Category> daoSupport;

    public Category insertCategory(long siteId, CategoryExtract categoryExtract1, CategoryExtract categoryExtract2,
            CategoryExtract categoryExtract3, Integer displayOrder, ExtractStatus extractStatus, Long extractJobId,
            LocalDateTime extractedDate)
    {
        final Object[] params =
        { siteId, getCategoryName(categoryExtract1), getCategoryUrl(categoryExtract1),
                getCategoryName(categoryExtract2), getCategoryUrl(categoryExtract2), getCategoryName(categoryExtract3),
                getCategoryUrl(categoryExtract3), displayOrder, extractStatus.getCode(), extractJobId,
                DateTimeUtils.toTimestamp(extractedDate) };
        final ModelBase modelBase = daoSupport.insert(INSERT_CATEGORY, INSERT_PARAM_TYPES, params);

        final Category category = new Category(modelBase, siteId, categoryExtract1, categoryExtract2, categoryExtract3);
        category.displayOrder = displayOrder;
        category.extractStatus = extractStatus;
        category.extractJobId = extractJobId;
        category.extractedDate = extractedDate;

        return category;
    }

    public Category updateCategory(Category category)
    {
        final Object[] params =
        { category.siteId, getCategoryName(category.categoryExtract1), getCategoryUrl(category.categoryExtract1),
                getCategoryName(category.categoryExtract2), getCategoryUrl(category.categoryExtract2),
                getCategoryName(category.categoryExtract3), getCategoryUrl(category.categoryExtract3),
                category.displayOrder, category.extractStatus.getCode(), category.extractJobId,
                DateTimeUtils.toTimestamp(category.extractedDate), category.id };
        category.updatedDate = daoSupport.update(UPDATE_CATEGORY, UPDATE_PARAM_TYPES, params);

        return category;
    }

    public Category selectCategoryByName(long siteId, String categoryName1, String categoryName2, String categoryName3)
    {
        final List<Object> paramList = new ArrayList<Object>();
        final StringBuilder sql = new StringBuilder(SELECT_CATEGORY_BY_NAME);
        paramList.add(siteId);
        addIfNotEmpty(1, categoryName1, sql, paramList);
        addIfNotEmpty(2, categoryName2, sql, paramList);
        addIfNotEmpty(3, categoryName3, sql, paramList);
        final int[] types = new int[paramList.size()];
        types[0] = Types.BIGINT;
        for (int index = 1; index < types.length; index++)
        {
            types[index] = Types.VARCHAR;
        }

        try
        {
            return daoSupport.selectObject(sql.toString(), types, paramList.toArray(), new CategoryRowMapper());
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            if (e.getActualSize() > 1)
            {
                throw new DaoException("Schema Alert - more than one record found: selectCategoryByName [" + siteId
                        + ", " + categoryName1 + ", " + categoryName2 + ", " + categoryName3 + "]", e);
            }
            return null;
        }
    }

    public List<Category> selectCategoryBySiteId(long siteId, ExtractStatus extractStatus)
    {
        final Object[] params =
        { siteId, extractStatus.getCode() };
        final int[] types =
        { Types.BIGINT, Types.INTEGER };

        return daoSupport.selectObjectList(SELECT_CATEGORY_BY_SITE_ID, types, params, new CategoryRowMapper());
    }

    public int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus extractStatus)
    {
        final Object[] params =
        { extractStatus.getCode(), siteId, excludingJobId };

        return daoSupport.updateMultiple(UPDATE_EXTRACT_STATUS, UPDATE_EXTRACT_STATUS_PARAM_TYPES, params);
    }

    private String getCategoryName(CategoryExtract extract)
    {
        return extract != null ? extract.name : null;
    }

    private String getCategoryUrl(CategoryExtract extract)
    {
        return extract != null ? extract.url : null;
    }

    private void addIfNotEmpty(int index, String param, StringBuilder sql, List<Object> paramList)
    {
        sql.append(" AND NAME_EXTRACT").append(index);
        if (StringUtils.hasText(param))
        {
            sql.append(" = ?");
            paramList.add(param);
        }
        else
        {
            sql.append(" IS NULL");
        }
    }

    private class CategoryRowMapper implements RowMapper<Category>
    {

        public Category mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final Extractable extractable = RowMapperUtils.mapRowToExtractable(rs, rowNum);
            final long siteId = rs.getLong("SITE_ID");
            final CategoryExtract categoryExtract1 = getCategoryExtract(rs, 1);
            final CategoryExtract categoryExtract2 = getCategoryExtract(rs, 2);
            final CategoryExtract categoryExtract3 = getCategoryExtract(rs, 3);
            final Category category = new Category(extractable, siteId, categoryExtract1, categoryExtract2,
                    categoryExtract3);
            category.displayOrder = rs.getInt("DISPLAY_ORDER");

            return category;
        }

        private CategoryExtract getCategoryExtract(ResultSet rs, int index) throws SQLException
        {
            final String name = rs.getString("NAME_EXTRACT" + index);
            final String url = rs.getString("URL_EXTRACT" + index);
            if (StringUtils.hasText(name))
            {
                return new CategoryExtract(name, url);
            }
            else
            {
                return null;
            }

        }
    }

}

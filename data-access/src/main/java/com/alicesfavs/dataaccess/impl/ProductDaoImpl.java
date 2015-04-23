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

import com.alicesfavs.dataaccess.ProductDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Extractable;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

@Repository
public class ProductDaoImpl implements ProductDao
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

    private static final String INSERT_PRODUCT = "INSERT INTO PRODUCT (SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
            + "SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET SITE_ID = ?, ID_EXTRACT = ?, NAME_EXTRACT = ?, "
            + "PRICE_EXTRACT = ?, WAS_PRICE_EXTRACT = ?, BRAND_NAME_EXTRACT = ?, URL_EXTRACT = ?, IMAGE_URL_EXTRACT = ?, "
            + "PRICE = ?, WAS_PRICE = ?, REGULAR_PRICE = ?, SALE_START_DATE = ?, STORED_IMAGE_PATH = ?, EXTRACT_STATUS = ?, "
            + "EXTRACT_JOB_ID = ?, EXTRACTED_DATE = ? WHERE ID = ?";

    private static final String UPDATE_EXTRACT_STATUS = "UPDATE PRODUCT SET EXTRACT_STATUS = ? "
            + "WHERE SITE_ID = ? AND EXTRACT_JOB_ID <> ?";

    private static final String SELECT_PRODUCT_BY_IDS = "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
            + "SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, CREATED_DATE, "
            + "UPDATED_DATE FROM PRODUCT WHERE SITE_ID = ? AND ID_EXTRACT = ?";

    private static final int[] INSERT_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER,
            Types.BIGINT, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.VARCHAR, Types.INTEGER,
            Types.BIGINT, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] SELECT_BY_IDS_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR };

    private static final int[] UPDATE_EXTRACT_STATUS_PARAM_TYPES =
    { Types.INTEGER, Types.BIGINT, Types.BIGINT };

    @Autowired
    private DaoSupport<Product> daoSupport;

    public Product insertProduct(long siteId, ProductExtract productExtract, Double price, Double wasPrice,
            Double regularPrice, LocalDateTime saleStartDate, String storedImagePath, ExtractStatus extractStatus,
            Long extractJobId, LocalDateTime extractedDate)
    {
        final Object[] params =
        { siteId, productExtract.id, productExtract.name, productExtract.price, productExtract.wasPrice,
                productExtract.brandName, productExtract.url, productExtract.imageUrl, price, wasPrice, regularPrice,
                DateTimeUtils.toTimestamp(saleStartDate), storedImagePath, extractStatus.getCode(), extractJobId,
                DateTimeUtils.toTimestamp(extractedDate) };
        final ModelBase modelBase = daoSupport.insert(INSERT_PRODUCT, INSERT_PARAM_TYPES, params);

        final Product product = new Product(modelBase, siteId, productExtract);
        product.price = price;
        product.wasPrice = wasPrice;
        product.regularPrice = regularPrice;
        product.saleStartDate = saleStartDate;
        product.storedImagePath = storedImagePath;
        product.extractStatus = extractStatus;
        product.extractJobId = extractJobId;
        product.extractedDate = extractedDate;

        return product;
    }

    public void updateProduct(Product product)
    {
        final Object[] params =
        { product.siteId, product.productExtract.id, product.productExtract.name, product.productExtract.price,
                product.productExtract.wasPrice, product.productExtract.brandName, product.productExtract.url,
                product.productExtract.imageUrl, product.price, product.wasPrice, product.regularPrice,
                DateTimeUtils.toTimestamp(product.saleStartDate), product.storedImagePath,
                product.extractStatus.getCode(), product.extractJobId,
                DateTimeUtils.toTimestamp(product.extractedDate), product.id };
        product.updatedDate = daoSupport.update(UPDATE_PRODUCT, UPDATE_PARAM_TYPES, params);
    }

    public Product selectProductById(Long siteId, String productId)
    {
        final Object[] params =
        { siteId, productId };

        try
        {
            return daoSupport.selectObject(SELECT_PRODUCT_BY_IDS, SELECT_BY_IDS_PARAM_TYPES, params,
                    new ProductRowMapper());
        }
        catch (IncorrectResultSizeDataAccessException e)
        {
            if (e.getActualSize() > 1)
            {
                throw new DaoException("Schema Alert - more than one record found: selectProductById [" + siteId + ", "
                        + productId + "]", e);
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

    private class ProductRowMapper implements RowMapper<Product>
    {

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final Extractable extractable = RowMapperUtils.mapRowToExtractable(rs, rowNum);
            final ProductExtract productExtract = new ProductExtract(rs.getString("ID_EXTRACT"));
            productExtract.name = rs.getString("NAME_EXTRACT");
            productExtract.price = rs.getString("PRICE_EXTRACT");
            productExtract.wasPrice = rs.getString("WAS_PRICE_EXTRACT");
            productExtract.brandName = rs.getString("BRAND_NAME_EXTRACT");
            productExtract.url = rs.getString("URL_EXTRACT");
            productExtract.imageUrl = rs.getString("IMAGE_URL_EXTRACT");

            final Product product = new Product(extractable, rs.getLong("SITE_ID"), productExtract);
            product.price = rs.getDouble("PRICE");
            product.wasPrice = rs.getDouble("WAS_PRICE");
            product.regularPrice = rs.getDouble("REGULAR_PRICE");
            product.saleStartDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("SALE_START_DATE"));
            product.storedImagePath = rs.getString("STORED_IMAGE_PATH");

            return product;
        }
    }

}
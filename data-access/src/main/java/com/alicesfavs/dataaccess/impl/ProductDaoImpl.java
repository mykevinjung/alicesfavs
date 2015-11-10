package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

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

    private static final String INSERT_PRODUCT =
        "INSERT INTO PRODUCT (SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET SITE_ID = ?, ID_EXTRACT = ?, NAME_EXTRACT = ?, "
        + "PRICE_EXTRACT = ?, WAS_PRICE_EXTRACT = ?, BRAND_NAME_EXTRACT = ?, URL_EXTRACT = ?, IMAGE_URL_EXTRACT = ?, "
        + "PRICE = ?, WAS_PRICE = ?, REGULAR_PRICE = ?, PRICE_CHANGED_DATE = ?, SALE_START_DATE = ?, "
        + "STORED_IMAGE_PATH = ?, EXTRACT_STATUS = ?, EXTRACT_JOB_ID = ?, EXTRACTED_DATE = ? WHERE ID = ?";

    private static final String UPDATE_EXTRACT_STATUS = "UPDATE PRODUCT SET EXTRACT_STATUS = ? "
        + "WHERE SITE_ID = ? AND EXTRACT_STATUS <> ? AND EXTRACT_JOB_ID <> ?";

    private static final String SELECT_PRODUCT_BY_IDS = "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
        + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
        + "PRICE_CHANGED_DATE, SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
        + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SITE_ID = ? AND ID_EXTRACT = ?";

    private static final String SELECT_SALE_PRODUCTS_BY_SITE =
        "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SALE_START_DATE IS NOT NULL AND SITE_ID = ? AND EXTRACT_STATUS = ?";

    private static final String SELECT_NEW_PRODUCTS_BY_SITE =
        "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, BRAND_NAME_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, REGULAR_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, STORED_IMAGE_PATH, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SALE_START_DATE IS NULL AND "
            + "SITE_ID = ? AND EXTRACT_STATUS = ? AND CREATED_DATE >= ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR,
            Types.INTEGER, Types.BIGINT, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.TIMESTAMP, Types.VARCHAR,
            Types.INTEGER, Types.BIGINT, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] SELECT_SALE_PRODUCTS_BY_SITE_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER };

    private static final int[] SELECT_NEW_PRODUCTS_BY_SITE_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER, Types.TIMESTAMP };

    private static final int[] SELECT_BY_IDS_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR };

    private static final int[] UPDATE_EXTRACT_STATUS_PARAM_TYPES =
        { Types.INTEGER, Types.BIGINT, Types.INTEGER, Types.BIGINT };

    @Autowired
    private DaoSupport<Product> daoSupport;

    public Product insertProduct(long siteId, ProductExtract productExtract, Double price, Double wasPrice,
        Double regularPrice, LocalDateTime priceChangedDate, LocalDateTime saleStartDate, String storedImagePath,
        ExtractStatus extractStatus,
        Long extractJobId, LocalDateTime extractedDate)
    {
        final Object[] params =
            { siteId, productExtract.id, productExtract.name, productExtract.price, productExtract.wasPrice,
                productExtract.brandName, productExtract.url, productExtract.imageUrl, price, wasPrice, regularPrice,
                DateTimeUtils.toTimestamp(priceChangedDate), DateTimeUtils.toTimestamp(saleStartDate), storedImagePath,
                extractStatus.getCode(), extractJobId, DateTimeUtils.toTimestamp(extractedDate) };
        final ModelBase modelBase = daoSupport.insert(INSERT_PRODUCT, INSERT_PARAM_TYPES, params);

        final Product product = new Product(modelBase, siteId, productExtract);
        product.price = price;
        product.wasPrice = wasPrice;
        product.regularPrice = regularPrice;
        product.priceChangedDate = priceChangedDate;
        product.saleStartDate = saleStartDate;
        product.storedImagePath = storedImagePath;
        product.extractStatus = extractStatus;
        product.extractJobId = extractJobId;
        product.extractedDate = extractedDate;

        return product;
    }

    public Product updateProduct(Product product)
    {
        final Object[] params =
            { product.siteId, product.productExtract.id, product.productExtract.name, product.productExtract.price,
                product.productExtract.wasPrice, product.productExtract.brandName, product.productExtract.url,
                product.productExtract.imageUrl, product.price, product.wasPrice, product.regularPrice,
                DateTimeUtils.toTimestamp(product.priceChangedDate), DateTimeUtils.toTimestamp(product.saleStartDate),
                product.storedImagePath, product.extractStatus.getCode(), product.extractJobId,
                DateTimeUtils.toTimestamp(product.extractedDate), product.id };
        product.updatedDate = daoSupport.update(UPDATE_PRODUCT, UPDATE_PARAM_TYPES, params);

        return product;
    }

    public Product selectProductById(Long siteId, String productExtractId)
    {
        final Object[] params = { siteId, productExtractId };

        return daoSupport.selectObject(SELECT_PRODUCT_BY_IDS, SELECT_BY_IDS_PARAM_TYPES, params,
            new ProductRowMapper());
    }

    public int updateExtractStatus(long siteId, long excludingJobId, ExtractStatus newStatus)
    {
        final Object[] params = { newStatus.getCode(), siteId, newStatus.getCode(), excludingJobId };

        return daoSupport.updateMultiple(UPDATE_EXTRACT_STATUS, UPDATE_EXTRACT_STATUS_PARAM_TYPES, params);
    }

    @Override
    public List<Product> selectSaleProducts(long siteId, ExtractStatus status)
    {
        final Object[] params = { siteId, status.getCode() };

        return daoSupport
            .selectObjectList(SELECT_SALE_PRODUCTS_BY_SITE, SELECT_SALE_PRODUCTS_BY_SITE_PARAM_TYPES, params,
                new ProductRowMapper());
    }

    @Override public List<Product> selectNewProducts(long siteId, ExtractStatus status, LocalDateTime afterCreatedDate)
    {
        final Object[] params = { siteId, status.getCode(), DateTimeUtils.toTimestamp(afterCreatedDate) };

        return daoSupport
            .selectObjectList(SELECT_NEW_PRODUCTS_BY_SITE, SELECT_NEW_PRODUCTS_BY_SITE_PARAM_TYPES, params,
                new ProductRowMapper());
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
            product.priceChangedDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("PRICE_CHANGED_DATE"));
            product.saleStartDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("SALE_START_DATE"));
            product.storedImagePath = rs.getString("STORED_IMAGE_PATH");

            return product;
        }
    }

}

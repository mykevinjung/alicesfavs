package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.dataaccess.util.ResultSetUtils;
import com.alicesfavs.dataaccess.util.StringEscapeUtils;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.SearchResultList;
import com.alicesfavs.datamodel.Site;
import org.springframework.beans.factory.annotation.Autowired;
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
            + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, "
            + "EXTRACT_JOB_ID, EXTRACTED_DATE, SEARCHABLE_TEXT) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, %s)";

    private static final String UPDATE_PRODUCT = "UPDATE PRODUCT SET SITE_ID = ?, ID_EXTRACT = ?, NAME_EXTRACT = ?, "
        + "PRICE_EXTRACT = ?, WAS_PRICE_EXTRACT = ?, URL_EXTRACT = ?, IMAGE_URL_EXTRACT = ?, "
        + "PRICE = ?, WAS_PRICE = ?, PRICE_CHANGED_DATE = ?, SALE_START_DATE = ?, SOLD_OUT = ?, "
        + "EXTRACT_STATUS = ?, EXTRACT_JOB_ID = ?, EXTRACTED_DATE = ?, SEARCHABLE_TEXT = %s WHERE ID = ?";

    private static final String UPDATE_EXTRACT_STATUS = "UPDATE PRODUCT SET EXTRACT_STATUS = ? "
        + "WHERE SITE_ID = ? AND EXTRACT_STATUS <> ? AND EXTRACT_JOB_ID <> ?";

    private static final String UPDATE_EXTRACT_STATUS_AND_NULLIFY_SEARCHABLE_TEXT =
        "UPDATE PRODUCT SET EXTRACT_STATUS = ?, SEARCHABLE_TEXT = NULL "
        + "WHERE SITE_ID = ? AND EXTRACT_STATUS <> ? AND EXTRACT_JOB_ID <> ?";

    private static final String SELECT_PRODUCT_BY_ID = "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
        + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
        + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
        + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE ID = ?";

    private static final String SELECT_PRODUCT_BY_IDS = "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
        + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
        + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
        + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SITE_ID = ? AND ID_EXTRACT = ?";

    private static final String SELECT_SALE_PRODUCTS_BY_SITE =
        "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SALE_START_DATE IS NOT NULL AND SITE_ID = ? AND EXTRACT_STATUS = ?";

    private static final String SEARCH_SALE_PRODUCT2 =
        "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SALE_START_DATE IS NOT NULL AND EXTRACT_STATUS = ? "
            + "AND SEARCHABLE_TEXT @@ to_tsquery('english', '%s')";

    private static final String SEARCH_SALE_PRODUCT = "SELECT count(*) OVER() AS TOTAL_COUNT, * FROM ("
            + "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE, ts_rank_cd(searchable_text, to_tsquery('english', '%s')) AS score "
            + "FROM PRODUCT WHERE SALE_START_DATE IS NOT NULL AND EXTRACT_STATUS = ? "
            + ") s WHERE score > 0 ORDER BY score DESC LIMIT ? OFFSET ?";

    private static final String SELECT_SALE_PRODUCTS_BY_SITE_CATEGORY =
        "SELECT CP.CATEGORY_ID, P.ID, P.SITE_ID, P.ID_EXTRACT, P.NAME_EXTRACT, P.PRICE_EXTRACT, "
            + "P.WAS_PRICE_EXTRACT, P.URL_EXTRACT, P.IMAGE_URL_EXTRACT, P.PRICE, P.WAS_PRICE, "
            + "P.PRICE_CHANGED_DATE, P.SALE_START_DATE, P.SOLD_OUT, P.EXTRACT_STATUS, P.EXTRACT_JOB_ID, P.EXTRACTED_DATE, "
            + "P.CREATED_DATE, P.UPDATED_DATE FROM PRODUCT P JOIN CATEGORY_PRODUCT CP ON P.ID = CP.PRODUCT_ID "
            + "JOIN CATEGORY C ON C.ID = CP.CATEGORY_ID "
            + "WHERE P.SALE_START_DATE IS NOT NULL AND P.EXTRACT_STATUS = 1 "
            + "AND CP.EXTRACT_STATUS = 1 AND C.EXTRACT_STATUS = 1 AND C.ID IN (%s) ORDER BY C.DISPLAY_ORDER, CP.DISPLAY_ORDER";

    private static final String SELECT_NEW_PRODUCTS_BY_SITE =
        "SELECT ID, SITE_ID, ID_EXTRACT, NAME_EXTRACT, PRICE_EXTRACT, "
            + "WAS_PRICE_EXTRACT, URL_EXTRACT, IMAGE_URL_EXTRACT, PRICE, WAS_PRICE, "
            + "PRICE_CHANGED_DATE, SALE_START_DATE, SOLD_OUT, EXTRACT_STATUS, EXTRACT_JOB_ID, EXTRACTED_DATE, "
            + "CREATED_DATE, UPDATED_DATE FROM PRODUCT WHERE SALE_START_DATE IS NULL AND "
            + "SITE_ID = ? AND EXTRACT_STATUS = ? AND CREATED_DATE >= ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.TIMESTAMP,
            Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
            Types.VARCHAR, Types.DECIMAL, Types.DECIMAL, Types.TIMESTAMP, Types.TIMESTAMP,
            Types.INTEGER, Types.INTEGER, Types.BIGINT, Types.TIMESTAMP, Types.BIGINT };

    private static final int[] SELECT_SALE_PRODUCTS_BY_SITE_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER };

    private static final int[] SELECT_NEW_PRODUCTS_BY_SITE_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER, Types.TIMESTAMP };

    private static final int[] SEARCH_PARAM_TYPES =
        { Types.INTEGER, Types.INTEGER, Types.INTEGER };

    private static final int[] SELECT_BY_ID_PARAM_TYPES =
        { Types.BIGINT };

    private static final int[] SELECT_BY_IDS_PARAM_TYPES =
        { Types.BIGINT, Types.VARCHAR };

    private static final int[] UPDATE_EXTRACT_STATUS_PARAM_TYPES =
        { Types.INTEGER, Types.BIGINT, Types.INTEGER, Types.BIGINT };

    @Autowired
    private DaoSupport<Product> daoSupport;

    public Product insertProduct(Site site, ProductExtract productExtract, Double price, Double wasPrice,
        LocalDateTime priceChangedDate, LocalDateTime saleStartDate,
        ExtractStatus extractStatus,
        Long extractJobId, LocalDateTime extractedDate)
    {
        final Object[] params =
            { site.id, productExtract.id, productExtract.name, productExtract.price, productExtract.wasPrice,
                productExtract.url, productExtract.imageUrl, price, wasPrice,
                DateTimeUtils.toTimestamp(priceChangedDate), DateTimeUtils.toTimestamp(saleStartDate),
                productExtract.soldOut, extractStatus.getCode(), extractJobId, DateTimeUtils.toTimestamp(extractedDate)};
        final String sql = String.format(INSERT_PRODUCT, buildSearchableText(productExtract, site));
        final ModelBase modelBase = daoSupport.insert(sql, INSERT_PARAM_TYPES, params);

        final Product product = new Product(modelBase, site.id, productExtract);
        product.price = price;
        product.wasPrice = wasPrice;
        product.priceChangedDate = priceChangedDate;
        product.saleStartDate = saleStartDate;
        product.extractStatus = extractStatus;
        product.extractJobId = extractJobId;
        product.extractedDate = extractedDate;

        return product;
    }

    public Product updateProduct(Product product, Site site)
    {
        final Object[] params =
            { product.siteId, product.productExtract.id, product.productExtract.name, product.productExtract.price,
                product.productExtract.wasPrice, product.productExtract.url,
                product.productExtract.imageUrl, product.price, product.wasPrice,
                DateTimeUtils.toTimestamp(product.priceChangedDate), DateTimeUtils.toTimestamp(product.saleStartDate),
                product.productExtract.soldOut ? 1 : 0, product.extractStatus.getCode(), product.extractJobId,
                DateTimeUtils.toTimestamp(product.extractedDate), product.id };
        final String sql = String.format(UPDATE_PRODUCT, buildSearchableText(product.productExtract, site));
        product.updatedDate = daoSupport.update(sql, UPDATE_PARAM_TYPES, params);

        return product;
    }

    public Product selectProductById(Long productId)
    {
        final Object[] params = { productId };

        return daoSupport.selectObject(SELECT_PRODUCT_BY_ID, SELECT_BY_ID_PARAM_TYPES, params,
            new ProductRowMapper());
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

        if (newStatus == ExtractStatus.EXTRACTED)
        {
            return daoSupport.updateMultiple(UPDATE_EXTRACT_STATUS, UPDATE_EXTRACT_STATUS_PARAM_TYPES, params);
        }
        else
        {
            return daoSupport.updateMultiple(UPDATE_EXTRACT_STATUS_AND_NULLIFY_SEARCHABLE_TEXT,
                UPDATE_EXTRACT_STATUS_PARAM_TYPES, params);
        }
    }

    @Override
    public List<Product> selectSaleProducts(long siteId, ExtractStatus status)
    {
        final Object[] params = { siteId, status.getCode() };

        return daoSupport
            .selectObjectList(SELECT_SALE_PRODUCTS_BY_SITE, SELECT_SALE_PRODUCTS_BY_SITE_PARAM_TYPES, params,
                new ProductRowMapper());
    }

    @Override
    public SearchResultList<Product> searchSaleProducts(String searchText, ExtractStatus status, int count, int offset)
    {
        final Object[] params = { status.getCode(), count, offset };

        final String sql = String.format(SEARCH_SALE_PRODUCT, StringEscapeUtils.escapeSql(searchText));
        final List<Product> result = daoSupport.selectObjectList(sql, SEARCH_PARAM_TYPES,
            params, new ProductWithCountRowMapper());
        final SearchResultList<Product> searchResultList = new SearchResultList<>(result);
        if (result.isEmpty())
        {
            searchResultList.setSearchResultCount(0);
        }
        else
        {
            searchResultList.setSearchResultCount(((ProductWithCount)result.get(0)).totalCount);
        }

        return searchResultList;
    }

    @Override
    public Map<Category, List<Product>> selectSaleProducts(List<Category> categoryList)
    {
        final Map<Category, List<Product>> categoryProductMap = createCategoryProductMap(categoryList);

        daoSupport.selectObjectList(getSelectSaleProductsBySiteCategory(categoryList), null, null,
            new CategoryProductRowMapper(categoryProductMap));

        return categoryProductMap;
    }

    private String buildSearchableText(ProductExtract productExtract, Site site)
    {
        final StringBuilder builder = new StringBuilder();
        // site name with weight A
        builder.append("setweight(to_tsvector('english', '")
            .append(StringEscapeUtils.escapeSql(site.displayName)).append("'), 'A')");
        if (!site.stringId.equalsIgnoreCase(site.displayName))
        {
            builder.append(" || setweight(to_tsvector('english', '")
                .append(StringEscapeUtils.escapeSql(site.stringId)).append("'), 'A')");
        }

        // product name and id with weight B
        builder.append(" || setweight(to_tsvector('english', '")
            .append(StringEscapeUtils.escapeSql(productExtract.name)).append("'), 'B')");
        builder.append(" || setweight(to_tsvector('english', '")
            .append(StringEscapeUtils.escapeSql(productExtract.id)).append("'), 'B')");

        // alice category name with weight C
        if (productExtract.aliceCategoryNames != null && !productExtract.aliceCategoryNames.isEmpty())
        {
            builder.append(" || setweight(to_tsvector('english', '");
            for (String aliceCategoryName : productExtract.aliceCategoryNames)
            {
                builder.append(" ").append(StringEscapeUtils.escapeSql(aliceCategoryName));
            }
            builder.append("'), 'C')");
        }

        // category name with weight D
        if (productExtract.categoryNames != null && !productExtract.categoryNames.isEmpty())
        {
            builder.append(" || setweight(to_tsvector('english', '");
            for (String categoryName : productExtract.categoryNames)
            {
                builder.append(" ").append(StringEscapeUtils.escapeSql(categoryName));
            }
            builder.append("'), 'D')");
        }

        return builder.toString();
    }

    private String getSelectSaleProductsBySiteCategory(List<Category> categoryList)
    {
        final StringBuffer buf = new StringBuffer();
        for (Category category : categoryList)
        {
            if (buf.length() > 0)
            {
                buf.append(", ");
            }
            buf.append(category.id);
        }

        return String.format(SELECT_SALE_PRODUCTS_BY_SITE_CATEGORY, buf.toString());
    }

    private Map<Category, List<Product>> createCategoryProductMap(List<Category> categoryList)
    {
        final Map<Category, List<Product>> categoryProductMap = new LinkedHashMap<>();
        for (Category category : categoryList)
        {
            categoryProductMap.put(category, new ArrayList<>());
        }

        return categoryProductMap;
    }

    @Override public List<Product> selectNewProducts(long siteId, ExtractStatus status, LocalDateTime afterCreatedDate)
    {
        final Object[] params = { siteId, status.getCode(), DateTimeUtils.toTimestamp(afterCreatedDate) };

        return daoSupport
            .selectObjectList(SELECT_NEW_PRODUCTS_BY_SITE, SELECT_NEW_PRODUCTS_BY_SITE_PARAM_TYPES, params,
                new ProductRowMapper());
    }

    private class ProductWithCount extends Product
    {
        private int totalCount;

        private ProductWithCount(Extractable extractable, long siteId, ProductExtract productExtract)
        {
            super(extractable, siteId, productExtract);
        }
    }

    private class ProductWithCountRowMapper implements RowMapper<Product>
    {
        public ProductWithCount mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final Extractable extractable = RowMapperUtils.mapRowToExtractable(rs, rowNum);
            final ProductExtract productExtract = new ProductExtract(rs.getString("ID_EXTRACT"));
            productExtract.name = rs.getString("NAME_EXTRACT");
            productExtract.price = rs.getString("PRICE_EXTRACT");
            productExtract.wasPrice = rs.getString("WAS_PRICE_EXTRACT");
            productExtract.url = rs.getString("URL_EXTRACT");
            productExtract.imageUrl = rs.getString("IMAGE_URL_EXTRACT");
            productExtract.soldOut = ResultSetUtils.getBoolean(rs, "SOLD_OUT");

            final ProductWithCount product = new ProductWithCount(extractable, rs.getLong("SITE_ID"), productExtract);
            product.price = ResultSetUtils.getDouble(rs, "PRICE");
            product.wasPrice = ResultSetUtils.getDouble(rs, "WAS_PRICE");
            product.priceChangedDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("PRICE_CHANGED_DATE"));
            product.saleStartDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("SALE_START_DATE"));

            product.totalCount = rs.getInt("TOTAL_COUNT");

            return product;
        }
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
            productExtract.url = rs.getString("URL_EXTRACT");
            productExtract.imageUrl = rs.getString("IMAGE_URL_EXTRACT");
            productExtract.soldOut = ResultSetUtils.getBoolean(rs, "SOLD_OUT");

            final Product product = new Product(extractable, rs.getLong("SITE_ID"), productExtract);
            product.price = ResultSetUtils.getDouble(rs, "PRICE");
            product.wasPrice = ResultSetUtils.getDouble(rs, "WAS_PRICE");
            product.priceChangedDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("PRICE_CHANGED_DATE"));
            product.saleStartDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("SALE_START_DATE"));

            return product;
        }
    }

    private class CategoryProductRowMapper extends ProductRowMapper
    {
        private final Map<Category, List<Product>> categoryProductMap;

        private CategoryProductRowMapper(Map<Category, List<Product>> categoryProductMap)
        {
            this.categoryProductMap = categoryProductMap;
        }

        public Product mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final Product product = super.mapRow(rs, rowNum);
            final long categoryId = rs.getLong("CATEGORY_ID");
            getProductList(categoryId).add(product);

            return product;
        }

        private List<Product> getProductList(long categoryId)
        {
            for(Category category : categoryProductMap.keySet())
            {
                if (category.id == categoryId)
                {
                    return categoryProductMap.get(category);
                }
            }
            throw new RuntimeException("This should not happen");
        }
    }
}

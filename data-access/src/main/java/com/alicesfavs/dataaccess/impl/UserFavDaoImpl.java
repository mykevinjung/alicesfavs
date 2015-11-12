package com.alicesfavs.dataaccess.impl;

import com.alicesfavs.dataaccess.UserFavDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.dataaccess.util.ResultSetUtils;
import com.alicesfavs.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

@Repository
public class UserFavDaoImpl implements UserFavDao
{

    private static final String INSERT_USER_FAV = "INSERT INTO USER_FAV (USER_ID, PRODUCT_ID, ADD_ON_NAME, "
        + "ADD_ON_PRICE) VALUES (?, ?, ?, ?)";

    private static final String DELETE_USER_FAV = "DELETE FROM USER_FAV WHERE ID = ?";

    private static final String SELECT_BY = "SELECT UF.ID, UF.USER_ID, UF.PRODUCT_ID, UF.ADD_ON_NAME, "
        + "UF.ADD_ON_PRICE, UF.CREATED_DATE, UF.UPDATED_DATE, P.ID, P.SITE_ID, P.ID_EXTRACT, P.NAME_EXTRACT, "
        + "P.PRICE_EXTRACT, P.WAS_PRICE_EXTRACT, P.BRAND_NAME_EXTRACT, P.URL_EXTRACT, P.IMAGE_URL_EXTRACT, "
        + "P.PRICE, P.WAS_PRICE, P.REGULAR_PRICE, P.PRICE_CHANGED_DATE, P.SALE_START_DATE, P.STORED_IMAGE_PATH, "
        + "P.EXTRACT_STATUS, P.EXTRACT_JOB_ID, P.EXTRACTED_DATE, P.CREATED_DATE, P.UPDATED_DATE "
        + "FROM USER_FAV UF INNER JOIN PRODUCT P ON UF.PRODUCT_ID = P.ID ";

    private static final String SELECT_BY_ID = SELECT_BY + "WHERE UF.ID = ?";

    private static final String SELECT_BY_USER_ID = SELECT_BY + "WHERE UF.USER_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.DECIMAL };

    private static final int[] DELETE_PARAM_TYPES =
        { Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.BIGINT };

    @Autowired
    private DaoSupport<UserFav> daoSupport;

    @Override public UserFav insertUserFav(long userId, Product product, String addOnName, Double addOnPrice)
    {
        final Object[] params =
            { userId, product.id, addOnName, addOnPrice };
        final ModelBase modelBase = daoSupport.insert(INSERT_USER_FAV, INSERT_PARAM_TYPES, params);

        final UserFav userFav = new UserFav(modelBase);
        userFav.userId = userId;
        userFav.product = product;
        userFav.addOnName = addOnName;
        userFav.addOnPrice = addOnPrice;

        return userFav;
    }

    @Override public void deleteUserFav(UserFav userFav)
    {
        final Object[] params =
            { userFav.id };
        daoSupport.delete(DELETE_USER_FAV, DELETE_PARAM_TYPES, params);
    }

    @Override public UserFav selectUserFavById(long id)
    {
        final Object[] params =
            { id };
        return daoSupport.selectObject(SELECT_BY_ID, SELECT_PARAM_TYPES, params, new UserFavRowMapper());
    }

    @Override public List<UserFav> selectUserFavByUserId(long userId)
    {
        final Object[] params =
            { userId };
        return daoSupport.selectObjectList(SELECT_BY_USER_ID, SELECT_PARAM_TYPES, params, new UserFavRowMapper());
    }

    private class UserFavRowMapper implements RowMapper<UserFav>
    {
        public UserFav mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum, "UF");
            final UserFav userFav = new UserFav(modelBase);
            userFav.userId = rs.getLong("UF.USER_ID");
            userFav.addOnName = rs.getString("UF.ADD_ON_NAME");
            userFav.addOnPrice = ResultSetUtils.getDouble(rs, "UF.ADD_ON_PRICE");
            userFav.product = mapRowProduct(rs, rowNum);

            return userFav;
        }

        private Product mapRowProduct(ResultSet rs, int rowNum) throws SQLException
        {
            final Extractable extractable = RowMapperUtils.mapRowToExtractable(rs, rowNum, "P");

            final ProductExtract productExtract = new ProductExtract(rs.getString("P.ID_EXTRACT"));
            productExtract.name = rs.getString("P.NAME_EXTRACT");
            productExtract.price = rs.getString("P.PRICE_EXTRACT");
            productExtract.wasPrice = rs.getString("P.WAS_PRICE_EXTRACT");
            productExtract.brandName = rs.getString("P.BRAND_NAME_EXTRACT");
            productExtract.url = rs.getString("P.URL_EXTRACT");
            productExtract.imageUrl = rs.getString("P.IMAGE_URL_EXTRACT");

            final Product product = new Product(extractable, rs.getLong("P.SITE_ID"), productExtract);
            product.price = ResultSetUtils.getDouble(rs, "P.PRICE");
            product.wasPrice = ResultSetUtils.getDouble(rs, "P.WAS_PRICE");
            product.regularPrice = ResultSetUtils.getDouble(rs, "P.REGULAR_PRICE");
            product.priceChangedDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("P.PRICE_CHANGED_DATE"));
            product.saleStartDate = DateTimeUtils.toLocalDateTime(rs.getTimestamp("P.SALE_START_DATE"));
            product.storedImagePath = rs.getString("P.STORED_IMAGE_PATH");

            return product;
        }
    }

}

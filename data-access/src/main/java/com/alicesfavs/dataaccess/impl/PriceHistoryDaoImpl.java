package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.PriceHistoryDao;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.PriceHistory;

@Repository
public class PriceHistoryDaoImpl implements PriceHistoryDao
{

    private static final String INSERT_PRICE_HISTORY = "INSERT INTO PRICE_HISTORY (PRODUCT_ID, OLD_PRICE_EXTRACT, "
            + "NEW_PRICE_EXTRACT, OLD_PRICE, NEW_PRICE) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_BY_PRODUCT_ID = "SELECT ID, PRODUCT_ID, OLD_PRICE_EXTRACT, NEW_PRICE_EXTRACT, "
            + "OLD_PRICE, NEW_PRICE, CREATED_DATE, UPDATED_DATE FROM PRICE_HISTORY WHERE PRODUCT_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
    { Types.BIGINT, Types.VARCHAR, Types.VARCHAR, Types.DECIMAL, Types.DECIMAL };

    private static final int[] SELECT_PARAM_TYPES =
    { Types.BIGINT };

    @Autowired
    private DaoSupport<PriceHistory> daoSupport;

    public PriceHistory insertPriceHistory(long productId, String oldPriceExtract, String newPriceExtract,
            Double oldPrice, Double newPrice)
    {
        final Object[] params =
        { productId, oldPriceExtract, newPriceExtract, oldPrice, newPrice };
        final ModelBase modelBase = daoSupport.insert(INSERT_PRICE_HISTORY, INSERT_PARAM_TYPES, params);

        return new PriceHistory(modelBase, productId, oldPriceExtract, newPriceExtract, oldPrice, newPrice);
    }

    public List<PriceHistory> selectPriceHistoryByProductId(long productId)
    {
        final Object[] params =
        { productId };
        return daoSupport.selectObjectList(SELECT_BY_PRODUCT_ID, SELECT_PARAM_TYPES, params,
                new PriceHistoryRowMapper());
    }

    private class PriceHistoryRowMapper implements RowMapper<PriceHistory>
    {
        public PriceHistory mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final long productId = rs.getLong("PRODUCT_ID");
            final String oldPriceExtract = rs.getString("OLD_PRICE_EXTRACT");
            final String newPriceExtract = rs.getString("NEW_PRICE_EXTRACT");
            final Double oldPrice = rs.getDouble("OLD_PRICE");
            final Double newPrice = rs.getDouble("NEW_PRICE");

            return new PriceHistory(modelBase, productId, oldPriceExtract, newPriceExtract, oldPrice, newPrice);
        }
    }

}

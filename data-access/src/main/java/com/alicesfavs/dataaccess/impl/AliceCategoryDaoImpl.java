package com.alicesfavs.dataaccess.impl;

import com.alicesfavs.dataaccess.AliceCategoryDao;
import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.ModelBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

/**
 * Created by kjung on 6/15/15.
 */
@Repository
public class AliceCategoryDaoImpl implements AliceCategoryDao
{

    private static final String SELECT_ALL = "SELECT ID, NAME, DISPLAY, CREATED_DATE, UPDATED_DATE "
        + "FROM ALICE_CATEGORY WHERE DISPLAY = ? ORDER BY ID ASC";

    private static final int[] SELECT_PARAM_TYPES =
        { Types.CHAR };

    @Autowired
    private DaoSupport<AliceCategory> daoSupport;

    @Override
    public List<AliceCategory> selectAliceCategoriesByDisplay(boolean display)
    {
        final Object[] params =
            { display ? '1' : '0' };
        return daoSupport.selectObjectList(SELECT_ALL, SELECT_PARAM_TYPES, params,
            new AliceCategoryRowMapper());
    }

    private class AliceCategoryRowMapper implements RowMapper<AliceCategory>
    {
        public AliceCategory mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final AliceCategory aliceCategory = new AliceCategory(modelBase);
            aliceCategory.name = rs.getString("NAME");
            aliceCategory.display = rs.getInt("DISPLAY") == 1 ? true : false;

            return aliceCategory;
        }
    }

}

package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.SiteDao;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.BrandLevel;

@Repository
public class SiteDaoImpl implements SiteDao
{

    private static final String INSERT_SITE = "INSERT INTO SITE (STRING_ID, DISPLAY_NAME, DOMAIN, "
            + "DISPLAY, DISPLAY_WEIGHT, BRAND_LEVEL, USE_STORED_IMAGE) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SITE = "UPDATE SITE SET STRING_ID = ?, DISPLAY_NAME = ?, DOMAIN = ?, "
            + "DISPLAY = ?, DISPLAY_WEIGHT = ?, BRAND_LEVEL = ?, USE_STORED_IMAGE = ? WHERE ID = ?";

    private static final String SELECT_BY_STRING_ID = "SELECT ID, STRING_ID, DISPLAY_NAME, DOMAIN, DISPLAY, "
            + "DISPLAY_WEIGHT, BRAND_LEVEL, USE_STORED_IMAGE, CREATED_DATE, UPDATED_DATE FROM SITE "
            + "WHERE STRING_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
    { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.INTEGER, Types.INTEGER, Types.CHAR };

    private static final int[] UPDATE_PARAM_TYPES =
    { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.INTEGER, Types.INTEGER, Types.CHAR, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
    { Types.VARCHAR };

    @Autowired
    private DaoSupport<Site> daoSupport;

    public Site insertSite(String stringId, String displayName, String domain, boolean display, Integer displayWeight,
            BrandLevel brandLevel, boolean useStoredImage)
    {
        final Object[] params =
        { stringId, displayName, domain, display ? 1 : 0, displayWeight, brandLevel.getCode(),
                useStoredImage ? 1 : 0 };
        final ModelBase modelBase = daoSupport.insert(INSERT_SITE, INSERT_PARAM_TYPES, params);

        final Site site = new Site(modelBase, stringId);
        site.displayName = displayName;
        site.domain = domain;
        site.display = display;
        site.displayWeight = displayWeight;
        site.brandLevel = brandLevel;
        site.useStoredImage = useStoredImage;

        return site;
    }

    public void updateSite(Site site)
    {
        final Object[] params =
        { site.stringId, site.displayName, site.domain, site.display ? 1 : 0, site.displayWeight,
                site.brandLevel.getCode(), site.useStoredImage ? 1 : 0, site.id };
        site.updatedDate = daoSupport.update(UPDATE_SITE, UPDATE_PARAM_TYPES, params);
    }

    public Site selectSiteByStringId(String stringId)
    {
        final Object[] params =
        { stringId };
        return daoSupport.selectObject(SELECT_BY_STRING_ID, SELECT_PARAM_TYPES, params, new SiteRowMapper());
    }

    private class SiteRowMapper implements RowMapper<Site>
    {
        public Site mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final String stringId = rs.getString("STRING_ID");
            final String displayName = rs.getString("DISPLAY_NAME");
            final String domain = rs.getString("DOMAIN");
            final boolean display = rs.getInt("DISPLAY") == 1 ? true : false;
            final Integer displayWeight = rs.getInt("DISPLAY_WEIGHT");
            final BrandLevel brandLevel = BrandLevel.fromCode(rs.getInt("BRAND_LEVEL"));
            final boolean useStoredImage = rs.getInt("USE_STORED_IMAGE") == 1 ? true : false;

            final Site site = new Site(modelBase, stringId);
            site.displayName = displayName;
            site.domain = domain;
            site.display = display;
            site.displayWeight = displayWeight;
            site.brandLevel = brandLevel;
            site.useStoredImage = useStoredImage;

            return site;
        }
    }

}

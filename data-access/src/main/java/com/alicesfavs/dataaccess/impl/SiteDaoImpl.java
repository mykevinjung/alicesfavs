package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

import com.alicesfavs.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.SiteDao;

@Repository
public class SiteDaoImpl implements SiteDao
{

    private static final String INSERT_SITE = "INSERT INTO SITE (STRING_ID, COUNTRY_CODE, DISPLAY_NAME, DOMAIN, "
        + "DISPLAY, DISPLAY_WEIGHT, USE_STORED_IMAGE, CURRENCY) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SITE = "UPDATE SITE SET STRING_ID = ?, COUNTRY_CODE = ?, DISPLAY_NAME = ?, "
        + "DOMAIN = ?, DISPLAY = ?, DISPLAY_WEIGHT = ?, USE_STORED_IMAGE = ?, CURRENCY = ? WHERE ID = ?";

    private static final String SELECT_BY_STRING_ID =
        "SELECT ID, STRING_ID, COUNTRY_CODE, DISPLAY_NAME, DOMAIN, DISPLAY, "
            + "DISPLAY_WEIGHT, USE_STORED_IMAGE, CURRENCY, CREATED_DATE, UPDATED_DATE FROM SITE "
            + "WHERE STRING_ID = ?";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.INTEGER, Types.CHAR,
            Types.VARCHAR };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.CHAR, Types.INTEGER, Types.CHAR,
            Types.VARCHAR, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.VARCHAR };

    @Autowired
    private DaoSupport<Site> daoSupport;

    public Site insertSite(String stringId, Country country, String displayName, String domain, boolean display,
        Integer displayWeight, boolean useStoredImage, String currency)
    {
        final Object[] params =
            { stringId, country.getCode(), displayName, domain, display ? 1 : 0, displayWeight,
                useStoredImage ? 1 : 0, currency };
        final ModelBase modelBase = daoSupport.insert(INSERT_SITE, INSERT_PARAM_TYPES, params);

        final Site site = new Site(modelBase, stringId);
        site.country = country;
        site.displayName = displayName;
        site.domain = domain;
        site.display = display;
        site.displayWeight = displayWeight;
        site.useStoredImage = useStoredImage;
        site.currency = currency;

        return site;
    }

    public Site updateSite(Site site)
    {
        final Object[] params =
            { site.stringId, site.country.getCode(), site.displayName, site.domain, site.display ? 1 : 0,
                site.displayWeight, site.useStoredImage ? 1 : 0, site.currency, site.id };
        site.updatedDate = daoSupport.update(UPDATE_SITE, UPDATE_PARAM_TYPES, params);

        return site;
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
            final Country country = Country.fromCode(rs.getInt("COUNTRY_CODE"));
            final String displayName = rs.getString("DISPLAY_NAME");
            final String domain = rs.getString("DOMAIN");
            final boolean display = rs.getInt("DISPLAY") == 1 ? true : false;
            final Integer displayWeight = rs.getInt("DISPLAY_WEIGHT");
            final boolean useStoredImage = rs.getInt("USE_STORED_IMAGE") == 1 ? true : false;
            final String currency = rs.getString("CURRENCY");

            final Site site = new Site(modelBase, stringId);
            site.country = country;
            site.displayName = displayName;
            site.domain = domain;
            site.display = display;
            site.displayWeight = displayWeight;
            site.useStoredImage = useStoredImage;
            site.currency = currency;

            return site;
        }
    }

}

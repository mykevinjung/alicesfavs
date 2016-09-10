package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import com.alicesfavs.dataaccess.util.ResultSetUtils;
import com.alicesfavs.datamodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.SiteDao;

@Repository
public class SiteDaoImpl implements SiteDao
{

    private static final String INSERT_SITE = "INSERT INTO SITE (STRING_ID, COUNTRY_CODE, DISPLAY_NAME, URL, "
        + "DISPLAY, DISPLAY_WEIGHT, CURRENCY, COOKIES) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_SITE = "UPDATE SITE SET STRING_ID = ?, COUNTRY_CODE = ?, DISPLAY_NAME = ?, "
        + "URL = ?, DISPLAY = ?, DISPLAY_WEIGHT = ?, CURRENCY = ?, COOKIES = ? WHERE ID = ?";

    private static final String SELECT_BY_STRING_ID =
        "SELECT ID, STRING_ID, COUNTRY_CODE, DISPLAY_NAME, URL, DISPLAY, "
            + "DISPLAY_WEIGHT, CURRENCY, COOKIES, DEFAULT_ALICE_CATEGORY_ID, CREATED_DATE, UPDATED_DATE FROM SITE "
            + "WHERE STRING_ID = ?";

    private static final String SELECT_ALL =
        "SELECT ID, STRING_ID, COUNTRY_CODE, DISPLAY_NAME, URL, DISPLAY, "
            + "DISPLAY_WEIGHT, CURRENCY, COOKIES, DEFAULT_ALICE_CATEGORY_ID, CREATED_DATE, UPDATED_DATE FROM SITE "
            + "WHERE DISPLAY = 1 ORDER BY DISPLAY_NAME ASC";

    private static final String SELECT_BY_ALICE_CATEGORY_ID =
        "SELECT S.ID, S.STRING_ID, S.COUNTRY_CODE, S.DISPLAY_NAME, S.URL, S.DISPLAY, "
            + "S.DISPLAY_WEIGHT, S.CURRENCY, S.COOKIES, S.DEFAULT_ALICE_CATEGORY_ID, S.CREATED_DATE, S.UPDATED_DATE "
            + "FROM SITE S INNER JOIN ALICE_CATEGORY_SITE A ON S.ID = A.SITE_ID "
            + "WHERE S.DISPLAY = 1 AND A.ALICE_CATEGORY_ID = ? ORDER BY S.DISPLAY_NAME";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.INTEGER,
            Types.VARCHAR, Types.VARCHAR };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.VARCHAR, Types.NUMERIC, Types.INTEGER,
            Types.VARCHAR, Types.VARCHAR, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.VARCHAR };

    private static final int[] SELECT_BY_ALICE_CATEGORY_PARAM_TYPES =
        { Types.BIGINT };

    @Autowired
    private DaoSupport<Site> daoSupport;

    public Site insertSite(String stringId, Country country, String displayName, String url, boolean display,
        Integer displayWeight, String currency, String cookies)
    {
        final Object[] params =
            { stringId, country.getCode(), displayName, url, display ? 1 : 0, displayWeight,
                currency, cookies };
        final ModelBase modelBase = daoSupport.insert(INSERT_SITE, INSERT_PARAM_TYPES, params);

        final Site site = new Site(modelBase, stringId);
        site.country = country;
        site.displayName = displayName;
        site.url = url;
        site.display = display;
        site.displayWeight = displayWeight;
        site.currency = currency;
        site.cookies = cookies;

        return site;
    }

    public Site updateSite(Site site)
    {
        final Object[] params =
            { site.stringId, site.country.getCode(), site.displayName, site.url, site.display ? 1 : 0,
                site.displayWeight, site.currency, site.cookies, site.id };
        site.updatedDate = daoSupport.update(UPDATE_SITE, UPDATE_PARAM_TYPES, params);

        return site;
    }

    public Site selectSiteByStringId(String stringId)
    {
        final Object[] params =
            { stringId };
        return daoSupport.selectObject(SELECT_BY_STRING_ID, SELECT_PARAM_TYPES, params, new SiteRowMapper());
    }

    public List<Site> selectAllSites()
    {
        return daoSupport.selectObjectList(SELECT_ALL, null, null, new SiteRowMapper());
    }

    @Override
    public List<Site> selectSiteByAliceCategory(long aliceCategoryId)
    {
        final Object[] params =
            { aliceCategoryId };
        return daoSupport.selectObjectList(SELECT_BY_ALICE_CATEGORY_ID, SELECT_BY_ALICE_CATEGORY_PARAM_TYPES,
            params, new SiteRowMapper());
    }

    private class SiteRowMapper implements RowMapper<Site>
    {
        public Site mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final String stringId = rs.getString("STRING_ID");
            final Country country = Country.fromCode(ResultSetUtils.getInt(rs, "COUNTRY_CODE"));
            final String displayName = rs.getString("DISPLAY_NAME");
            final String url = rs.getString("URL");
            final boolean display = rs.getInt("DISPLAY") == 1 ? true : false;
            final Integer displayWeight = ResultSetUtils.getInt(rs, "DISPLAY_WEIGHT");
            final String currency = rs.getString("CURRENCY");
            final String cookies = rs.getString("COOKIES");
            final Long defaultAliceCategoryId = ResultSetUtils.getLong(rs, "DEFAULT_ALICE_CATEGORY_ID");

            final Site site = new Site(modelBase, stringId);
            site.country = country;
            site.displayName = displayName;
            site.url = url;
            site.display = display;
            site.displayWeight = displayWeight;
            site.currency = currency;
            site.cookies = cookies;
            site.defaultAliceCategoryId = defaultAliceCategoryId;

            return site;
        }
    }

}

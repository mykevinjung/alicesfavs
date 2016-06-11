package com.alicesfavs.dataaccess.impl;

import com.alicesfavs.dataaccess.ProductRedirectDao;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.ProductRedirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Types;

/**
 * Created by kjung on 5/14/16.
 */
@Repository
public class ProductRedirectDaoImpl implements ProductRedirectDao
{

    private static final String INSERT = "INSERT INTO PRODUCT_REDIRECT (SITE_ID, PRODUCT_ID, "
        + "PAGE_ID, PAGE_NO, ALICE_CATEGORY, POSITION, IS_MOBILE, REDIRECT_URL, SESSION_ID, "
        + "REMOTE_ADDRESS, USER_AGENT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.BIGINT, Types.VARCHAR, Types.INTEGER, Types.VARCHAR, Types.INTEGER,
            Types.NUMERIC, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR };

    @Autowired
    private DaoSupport<ProductRedirect> daoSupport;

    @Override
    public ProductRedirect insertProductRedirect(ProductRedirect productRedirect)
    {
        final Object[] params =
            { productRedirect.siteId, productRedirect.productId, productRedirect.pageId, productRedirect.pageNo,
                productRedirect.category, productRedirect.position, productRedirect.isMobile ? 1 : 0,
                productRedirect.redirectUrl, productRedirect.sessionId, productRedirect.remoteAddress,
                productRedirect.userAgent};
        final ModelBase modelBase = daoSupport.insert(INSERT, INSERT_PARAM_TYPES, params);
        productRedirect.setModelBase(modelBase);

        return productRedirect;
    }
}

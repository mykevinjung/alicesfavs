package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.ProductRedirect;

/**
 * Created by kjung on 6/8/15.
 */
public interface ProductRedirectDao
{

    ProductRedirect insertProductRedirect(long siteId, long productId, String redirectUrl, String sessionId,
        Long userId, Long userLoginSessionId, String remoteAddress, String userAgent);

}

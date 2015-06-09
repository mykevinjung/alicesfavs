package com.alicesfavs.datamodel;

import java.time.LocalDateTime;

/**
 * Created by kjung on 6/8/15.
 */
public class ProductRedirect extends ModelBase
{

    public long siteId;
    public long productId;
    public String redirectUrl;
    public String sessionId;
    public Long userId;
    public Long userLoginSessionId;
    public String remoteAddress;
    public String userAgent;

    public ProductRedirect(long id, LocalDateTime createdDate)
    {
        super(id, createdDate);
    }

    public ProductRedirect(ModelBase modelBase)
    {
        super(modelBase);
    }

}

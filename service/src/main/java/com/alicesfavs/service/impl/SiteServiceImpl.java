package com.alicesfavs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.dataaccess.SiteDao;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.SiteService;

@Component("siteService")
public class SiteServiceImpl implements SiteService
{

    @Autowired
    private SiteDao siteDao;

    public Site createSite(String stringId, String displayName, String domain, boolean display, Integer displayWeight,
        boolean useStoredImage)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Site findSiteById(String id)
    {
        return siteDao.selectSiteByStringId(id);
    }

}

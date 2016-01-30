package com.alicesfavs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.dataaccess.SiteDao;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.SiteService;

import java.util.List;

@Component("siteService")
public class SiteServiceImpl implements SiteService
{

    @Autowired
    private SiteDao siteDao;

    public Site createSite(String stringId, String displayName, String url, boolean display, Integer displayWeight,
        boolean useStoredImage)
    {
        // TODO Auto-generated method stub
        throw new RuntimeException("not implemented yet");
    }

    public Site findSiteById(String id)
    {
        return siteDao.selectSiteByStringId(id);
    }

    @Override
    public List<Site> findAllSites()
    {
        return siteDao.selectAllSites();
    }

    @Override
    public List<Site> findSitesByAliceCategory(long aliceCategoryId)
    {
        return siteDao.selectSiteByAliceCategory(aliceCategoryId);
    }

}

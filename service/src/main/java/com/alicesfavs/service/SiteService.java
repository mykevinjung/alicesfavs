package com.alicesfavs.service;

import com.alicesfavs.datamodel.Site;

import java.util.List;

public interface SiteService
{

    Site createSite(String stringId, String displayName, String url, boolean display, Integer displayWeight,
        boolean useStoredImage);

    Site findSiteById(String id);

    List<Site> findAllSites();

    List<Site> findSitesByAliceCategory(long aliceCategoryId);

}

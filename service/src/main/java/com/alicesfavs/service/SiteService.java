package com.alicesfavs.service;

import com.alicesfavs.datamodel.Site;

public interface SiteService
{

    Site createSite(String stringId, String displayName, String domain, boolean display, Integer displayWeight,
        boolean useStoredImage);

    Site findSiteById(String id);

}

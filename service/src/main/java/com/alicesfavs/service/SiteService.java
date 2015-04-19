package com.alicesfavs.service;

import com.alicesfavs.datamodel.BrandLevel;
import com.alicesfavs.datamodel.Site;

public interface SiteService
{

    Site createSite(String stringId, String displayName, String domain, boolean display, Integer displayWeight,
            BrandLevel brandLevel, boolean useStoredImage);

    Site findSiteById(String id);

}

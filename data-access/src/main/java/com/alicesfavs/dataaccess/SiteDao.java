package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.Country;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.BrandLevel;

public interface SiteDao
{

    Site insertSite(String stringId, Country country, String displayName, String domain, boolean display,
        Integer displayWeight, BrandLevel brandLevel, boolean useStoredImage, String currency);

    void updateSite(Site site);

    Site selectSiteByStringId(String stringId);

}

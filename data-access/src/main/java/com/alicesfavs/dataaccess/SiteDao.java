package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.BrandLevel;

public interface SiteDao
{

    Site insertSite(String stringId, String displayName, String domain, boolean display, Integer displayWeight,
            BrandLevel brandLevel, boolean useStoredImage);

    void updateSite(Site site);

    Site selectSiteByStringId(String stringId);

}

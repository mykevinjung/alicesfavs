package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.Country;
import com.alicesfavs.datamodel.Site;

public interface SiteDao
{

    Site insertSite(String stringId, Country country, String displayName, String domain, boolean display,
        Integer displayWeight, boolean useStoredImage, String currency);

    Site updateSite(Site site);

    Site selectSiteByStringId(String stringId);

    // TODO
    //Map<AliceCategory, Site> selectAllAliceCategory();

}

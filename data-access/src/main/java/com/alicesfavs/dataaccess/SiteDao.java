package com.alicesfavs.dataaccess;

import com.alicesfavs.datamodel.Country;
import com.alicesfavs.datamodel.Site;

import java.util.List;

public interface SiteDao
{

    Site insertSite(String stringId, Country country, String displayName, String url, boolean display,
        Integer displayWeight, boolean useStoredImage, String currency);

    Site updateSite(Site site);

    Site selectSiteByStringId(String stringId);

    List<Site> selectSiteByAliceCategory(long aliceCategoryId);

}

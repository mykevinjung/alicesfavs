package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.AliceCategoryService;
import com.alicesfavs.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 11/3/15.
 */
@Component
public class SiteManager
{

    private static final int CACHE_TIMEOUT_SECONDS = 60 * 60;

    @Autowired
    private AliceCategoryService aliceCategoryService;

    @Autowired
    private SiteService siteService;

    private Map<String, List<Site>> categorySiteMap = new HashMap<>();

    private LocalDateTime cachedTime;

    public Map<String, List<Site>> getCategorySiteMap()
    {
        if (shouldRefresh())
        {
            categorySiteMap = getCategorySiteMapFromDatabase();
            cachedTime = LocalDateTime.now();
        }

        return categorySiteMap;
    }

    public Site getSite(String stringId)
    {
        for (final Site site : getSites())
        {
            if (site.stringId.equals(stringId))
            {
                return site;
            }
        }

        return null;
    }

    public List<Site> getSites()
    {
        final List<Site> sites = new ArrayList<>();
        for (List<Site> categorySites : getCategorySiteMap().values())
        {
            sites.addAll(categorySites);
        }

        return sites;
    }

    private Map<String, List<Site>> getCategorySiteMapFromDatabase()
    {
        final Map<String, List<Site>> categorySiteMapFromDB = new HashMap<>();
        final List<AliceCategory> aliceCategories = aliceCategoryService.findAllAliceCategories();
        for (final AliceCategory aliceCategory : aliceCategories)
        {
            final List<Site> sites = siteService.findSitesByAliceCategory(aliceCategory.id);
            categorySiteMapFromDB.put(aliceCategory.name, sites);
        }

        return categorySiteMapFromDB;
    }

    private boolean shouldRefresh()
    {
        return categorySiteMap == null || cachedTime == null ||
            cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > CACHE_TIMEOUT_SECONDS;
    }

}

package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.AliceCategoryService;
import com.alicesfavs.service.SiteService;
import com.alicesfavs.webapp.config.WebAppConfig;
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

    @Autowired
    private AliceCategoryService aliceCategoryService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private WebAppConfig webAppConfig;

    private Map<AliceCategory, List<Site>> categorySiteMap = new HashMap<>();

    private LocalDateTime cachedTime;

    public Map<AliceCategory, List<Site>> getCategorySiteMap()
    {
        if (shouldRefresh())
        {
            final Map<AliceCategory, List<Site>> mapFromDb = getCategorySiteMapFromDatabase();
            if (mapFromDb != null && mapFromDb.size() > 0)
            {
                categorySiteMap = getCategorySiteMapFromDatabase();
                cachedTime = LocalDateTime.now();
            }
        }

        return categorySiteMap;
    }

    public Site getSiteByStringId(String stringId)
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

    public Site getSiteById(long id)
    {
        for (final Site site : getSites())
        {
            if (site.id == id)
            {
                return site;
            }
        }

        return null;
    }

    public AliceCategory getAliceCatgory(String categoryName)
    {
        for (AliceCategory category : getCategorySiteMap().keySet())
        {
            if (category.name.equalsIgnoreCase(categoryName))
            {
                return category;
            }
        }

        return null;
    }

    public List<Site> getSites(AliceCategory aliceCategory)
    {
        return getCategorySiteMap().get(aliceCategory);
    }

    // TODO refactor this with Set instead of List
    public List<Site> getSites()
    {
        final List<Site> sites = new ArrayList<>();
        for (List<Site> categorySites : getCategorySiteMap().values())
        {
            sites.addAll(categorySites);
        }

        return sites;
    }

    private Map<AliceCategory, List<Site>> getCategorySiteMapFromDatabase()
    {
        final Map<AliceCategory, List<Site>> categorySiteMapFromDB = new HashMap<>();
        final List<AliceCategory> aliceCategories = aliceCategoryService.findAllAliceCategories();
        for (final AliceCategory aliceCategory : aliceCategories)
        {
            final List<Site> sites = siteService.findSitesByAliceCategory(aliceCategory.id);
            categorySiteMapFromDB.put(aliceCategory, sites);
        }

        return categorySiteMapFromDB;
    }

    private boolean shouldRefresh()
    {
        return categorySiteMap == null || cachedTime == null ||
            cachedTime.until(LocalDateTime.now(), ChronoUnit.SECONDS) > webAppConfig.getSiteCacheTimeoutSeconds();
    }

}

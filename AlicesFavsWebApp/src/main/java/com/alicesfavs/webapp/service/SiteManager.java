package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.AliceCategoryService;
import com.alicesfavs.service.SiteService;
import com.alicesfavs.webapp.config.WebAppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 11/3/15.
 */
@Component
public class SiteManager
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteManager.class);

    @Autowired
    private AliceCategoryService aliceCategoryService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private WebAppConfig webAppConfig;

    // using LinkedHashMap to keep the insertion order
    private Map<AliceCategory, List<Site>> categorySiteMap = new LinkedHashMap<>();

    private LocalDateTime cachedTime;

    public Map<AliceCategory, List<Site>> getCategorySiteMap()
    {
        if (shouldRefresh())
        {
            refresh();
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

    public Collection<Site> getSites()
    {
        final Map<Long, Site> siteMap = new LinkedHashMap<>();
        for (List<Site> categorySites : getCategorySiteMap().values())
        {
            for (Site site : categorySites)
            {
                if (!siteMap.containsKey(site.id))
                {
                    siteMap.put(site.id, site);
                }
            }
        }

        return siteMap.values();
    }

    public synchronized void refresh()
    {
        LOGGER.info("Refreshing site map...");
        final Map<AliceCategory, List<Site>> mapFromDb = getCategorySiteMapFromDatabase();
        if (mapFromDb != null && mapFromDb.size() > 0)
        {
            categorySiteMap = mapFromDb;
            cachedTime = LocalDateTime.now();
        }
        else
        {
            throw new RuntimeException("Category-Site map is empty!");
        }
    }

    private Map<AliceCategory, List<Site>> getCategorySiteMapFromDatabase()
    {
        final Map<AliceCategory, List<Site>> categorySiteMapFromDB = new LinkedHashMap<>();
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

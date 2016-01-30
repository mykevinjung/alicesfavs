package com.alicesfavs.webapp.service;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.AliceCategoryService;
import com.alicesfavs.service.SiteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by kjung on 11/3/15.
 */
@Component
public class SiteManager
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteManager.class);

    @Autowired
    private SiteService siteService;

    @Autowired
    private AliceCategoryService aliceCategoryService;

    private List<Site> siteList;
    private List<AliceCategory> aliceCategoryList;

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
        for (AliceCategory category : aliceCategoryList)
        {
            if (category.name.equalsIgnoreCase(categoryName))
            {
                return category;
            }
        }

        return null;
    }

    public List<AliceCategory> getAliceCategoryList()
    {
        return aliceCategoryList;
    }

    public boolean isValidCategory(Category category)
    {
        for (AliceCategory aliceCategory : aliceCategoryList)
        {
            if (category.isAliceCategory(aliceCategory))
            {
                return true;
            }
        }

        return false;
    }

    public List<Site> getSites()
    {
        return siteList;
    }

    public synchronized void refresh()
    {
        LOGGER.info("Refreshing site manager...");
        aliceCategoryList = aliceCategoryService.findAllAliceCategories();
        siteList = siteService.findAllSites();
    }

}

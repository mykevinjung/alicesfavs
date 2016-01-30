package com.alicesfavs.webapp.uimodel;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.Site;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 1/23/16.
 */
public class SiteProduct
{

    private final Site site;
    private Map<Category, List<UiProduct>> categoryProductMap;
    private LocalDateTime cachedTime;

    public SiteProduct(Site site, Map<Category, List<UiProduct>> categoryProductMap)
    {
        this.site = site;
        this.categoryProductMap = categoryProductMap;
    }

    public Site getSite()
    {
        return site;
    }

    public Map<Category, List<UiProduct>> getCategoryProductMap()
    {
        return categoryProductMap;
    }

    public void setCachedTime(LocalDateTime cachedTime)
    {
        this.cachedTime = cachedTime;
    }

    public LocalDateTime getCachedTime()
    {
        return cachedTime;
    }

    public boolean hasProducts(AliceCategory aliceCategory)
    {
        for (Map.Entry<Category, List<UiProduct>> entry : categoryProductMap.entrySet())
        {
            if (entry.getKey().aliceCategoryId == aliceCategory.id && entry.getValue().size() > 0)
            {
                return true;
            }
        }

        return false;
    }

}

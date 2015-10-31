package com.alicesfavs.webapp.interceptor;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.AliceCategoryService;
import com.alicesfavs.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class CategorySiteInterceptor extends HandlerInterceptorAdapter
{

    @Autowired
    private AliceCategoryService aliceCategoryService;

    @Autowired
    private SiteService siteService;

    private Map<String, List<Site>> categorySiteMap;

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception
    {
        for (Map.Entry<String, List<Site>> categorySite : getCategorySiteMap().entrySet())
        {
            modelAndView.addObject(categorySite.getKey().toLowerCase(), categorySite.getValue());
        }
    }

    private synchronized Map<String, List<Site>> getCategorySiteMap()
    {
        if (refreshCategorySiteMap())
        {
            categorySiteMap = new HashMap<>();
            List<AliceCategory> aliceCategories = aliceCategoryService.findAllAliceCategories();
            for (AliceCategory aliceCategory : aliceCategories)
            {
                final List<Site> sites = siteService.findSitesByAliceCategory(aliceCategory.id);
                categorySiteMap.put(aliceCategory.name, sites);
            }
        }

        return categorySiteMap;
    }

    private boolean refreshCategorySiteMap()
    {
        return categorySiteMap == null;
    }
}

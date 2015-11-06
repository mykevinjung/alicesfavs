package com.alicesfavs.webapp.interceptor;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class CategorySiteInterceptor extends HandlerInterceptorAdapter
{

    @Autowired
    private SiteManager siteManager;

    /**
     * Add category-site map into ModelAndView so that the map can be used for things like top menu
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception
    {
        for (Map.Entry<AliceCategory, List<Site>> categorySite : siteManager.getCategorySiteMap().entrySet())
        {
            modelAndView.addObject(categorySite.getKey().name, ModelConverter.convertSiteList(categorySite.getValue()));
        }
    }

}

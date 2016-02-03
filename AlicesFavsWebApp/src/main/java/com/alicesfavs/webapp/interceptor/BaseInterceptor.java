package com.alicesfavs.webapp.interceptor;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Constants;
import com.alicesfavs.webapp.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 10/30/15.
 */
@Component
public class BaseInterceptor extends HandlerInterceptorAdapter
{

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    /**
     * Add category-site map into ModelAndView so that the map can be used for things like top menu
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception
    {
        if (modelAndView != null && !isRedirect(modelAndView))
        {
            for (AliceCategory aliceCategory : siteManager.getAliceCategoryList())
            {
                modelAndView.addObject(aliceCategory.name.toLowerCase(),
                    ModelConverter.convertSiteList(getAliceCategorySites(aliceCategory)));
            }

            if (!modelAndView.getModelMap().containsKey("logo"))
            {
                modelAndView.addObject("logo", "/resources/images/logo1.png");
            }
        }
    }

    private boolean isRedirect(ModelAndView modelAndView)
    {
        final String viewName = modelAndView.getViewName();
        return viewName != null && viewName.startsWith(Constants.REDIRECT_PREFIX);
    }

    private List<Site> getAliceCategorySites(AliceCategory aliceCategory)
    {
        final List<Site> siteList = new ArrayList<>();
        for (Site site : siteManager.getSites())
        {
            if (saleProductService.hasSaleProducts(site, aliceCategory))
            {
                siteList.add(site);
            }
        }

        return siteList;
    }

}

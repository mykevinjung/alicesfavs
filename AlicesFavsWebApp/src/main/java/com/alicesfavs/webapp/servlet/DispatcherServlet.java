package com.alicesfavs.webapp.servlet;

import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.service.NewProductService;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by kjung on 11/19/15.
 */
public class DispatcherServlet extends org.springframework.web.servlet.DispatcherServlet
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected WebApplicationContext initWebApplicationContext()
    {
        final WebApplicationContext webApplicationContext = super.initWebApplicationContext();
        loadData(webApplicationContext);

        return webApplicationContext;
    }

    private void loadData(WebApplicationContext webApplicationContext)
    {
        final SiteManager siteManager = webApplicationContext.getBean(SiteManager.class);
        final SaleProductService saleProductService = webApplicationContext.getBean(SaleProductService.class);
        final NewProductService newProductService = webApplicationContext.getBean(NewProductService.class);

        LOGGER.info("Loading sites...");
        siteManager.refresh();

        for (Site site : siteManager.getSites())
        {
            LOGGER.info("Loading products for {}...", site.stringId);
            saleProductService.refresh(site);
            newProductService.refresh(site);
        }
    }
}

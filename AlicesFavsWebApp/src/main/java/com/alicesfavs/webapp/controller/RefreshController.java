package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.exception.ResourceNotFoundException;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kjung on 11/4/15.
 */
@Controller
public class RefreshController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(RefreshController.class);

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private WebAppConfig webAppConfig;

    @RequestMapping(value = "/refresh/{siteId}", method = RequestMethod.GET)
    public String refreshSale(@PathVariable String siteId, HttpServletRequest request)
    {
        validateRequest(request);

        final Site site = siteManager.getSiteByStringId(siteId);
        if (site == null)
        {
            LOGGER.error("Site not found: " + siteId);
            throw new ResourceNotFoundException("Site '" + siteId + "' not found");
        }

        saleProductService.refresh(site);

        return Constants.VIEW_BLANK;
    }

    @RequestMapping(value = "/refresh/site-manager", method = RequestMethod.GET)
    public String refreshSiteManager(HttpServletRequest request)
    {
        validateRequest(request);

        siteManager.refresh();

        return Constants.VIEW_BLANK;
    }

    private void validateRequest(HttpServletRequest request)
    {
        final String remoteAddr = getRemoteAddr(request);
        if (StringUtils.hasText(remoteAddr))
        {
            for (String addr : webAppConfig.getRefreshAllowedAddrArray())
            {
                if (remoteAddr.equals(addr))
                {
                    return;
                }
            }
        }

        LOGGER.error("Refresh request from unknown address: " + remoteAddr);
        throw new ResourceNotFoundException("Unknown address: " + remoteAddr);
    }

    private String getRemoteAddr(HttpServletRequest request)
    {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null)
        {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}

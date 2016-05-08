package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductRedirect;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.webapp.service.BotDetector;
import com.alicesfavs.webapp.service.RedirectService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Constants;
import com.alicesfavs.webapp.util.HttpUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by kjung on 2/2/16.
 */
@Controller
public class RedirectController
{

    private static final Logger PRODUCT_REDIRECT_LOGGER = LoggerFactory.getLogger("ProductRedirectLog");

    @Autowired
    private BotDetector botDetector;

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private RedirectService redirectService;


    @RequestMapping(value = "/redirect/product", method = RequestMethod.GET)
    public String product(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(name = "siteId") String siteId, @RequestParam(name = "id") long productId, Device device)
    {
        final Site site = siteManager.getSiteByStringId(siteId);
        if (site == null)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        final Product product = productService.findProduct(productId);
        if (product == null || site.id != product.siteId)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if (!botDetector.isBot(request))
        {
            // siteId=michaelkors&id=10000365&pageId=sale-site&pageNo=1&category=clothing&position=1
            final ProductRedirect productRedirect = new ProductRedirect();
            productRedirect.siteId = product.siteId;
            productRedirect.productId = product.id;
            productRedirect.redirectUrl = product.productExtract.url;
            productRedirect.remoteAddress = HttpUtils.getRemoteAddr(request);
            productRedirect.userAgent = HttpUtils.getUserAgent(request);
            productRedirect.pageId = request.getParameter(Constants.PAGE_ID);
            productRedirect.pageNo = NumberUtils.toInt(request.getParameter(Constants.PAGE_NUMBER), 1);
            productRedirect.position = NumberUtils.toInt(request.getParameter(Constants.POSITION), 1);
            productRedirect.category = request.getParameter(Constants.CATEGORY);
            productRedirect.sessionId = request.getRequestedSessionId();
        }

        return Constants.REDIRECT_PREFIX + product.productExtract.url;
    }

}

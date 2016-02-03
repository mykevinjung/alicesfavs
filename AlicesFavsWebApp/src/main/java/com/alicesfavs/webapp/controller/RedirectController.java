package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.webapp.service.BotDetector;
import com.alicesfavs.webapp.service.RedirectService;
import com.alicesfavs.webapp.uimodel.Constants;
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
    private RedirectService redirectService;


    @RequestMapping(value = "/redirect/product", method = RequestMethod.GET)
    public String product(HttpServletRequest request, HttpServletResponse response,
        @RequestParam(name = "id") long productId, Device device)
    {
        final Product product = redirectService.getProduct(productId);
        if (product == null)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        final String redirectUrl = product.productExtract.url;
        if (!botDetector.isBot(request))
        {
            // siteId=michaelkors&id=10000365&pageId=sale-site&pageNo=1&category=clothing&position=1
            // store redirect info
            final String siteId = request.getParameter("siteId");
            final String pageId = request.getParameter(Constants.PAGE_ID);
            final int pageNo = NumberUtils.toInt(request.getParameter(Constants.PAGE_NUMBER), 1);
            final String category = request.getParameter("category");
            final int position = NumberUtils.toInt(request.getParameter("position"), 1);
            device.getDevicePlatform().name();

            PRODUCT_REDIRECT_LOGGER.info(category + " " + siteId + " " + pageId + " " + pageNo + " " + position + " " + productId);
        }

        return Constants.REDIRECT_PREFIX + redirectUrl;
    }

}

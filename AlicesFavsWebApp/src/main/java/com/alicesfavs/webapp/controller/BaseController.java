package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.ProductService;
import com.alicesfavs.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 10/19/15.
 */
@Controller
public class BaseController
{

    private static final String VIEW_INDEX = "index";
    private static final String VIEW_SALE = "sale";
    private static final String VIEW_ABOUT_US = "about-us";
    private static final String VIEW_CONTACT_US = "contact-us";
    private static final String VIEW_NOT_FOUND = "error404";

    private static final int MAX_COUNT = 100;

    @Autowired
    private ProductService productService;

    @Autowired
    private SiteService siteService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        return "comingsoon";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index1(ModelMap model)
    {
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String saleRedirect(ModelMap model)
    {
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, HttpServletRequest request, ModelMap model)
    {
        final Site site = siteService.findSiteById(siteId);
        final List<Product> productList = productService.selectSaleProducts(site.id);

        final String pageNo = request.getParameter("pageNo");

        int startIndex = 0;
        int endIndex = Math.min(MAX_COUNT, productList.size());
        model.addAttribute("site", site);
        model.addAttribute("productList", productList.subList(startIndex, endIndex));
        model.addAttribute("startIndex", startIndex + 1);
        model.addAttribute("endIndex", endIndex);
        model.addAttribute("productTotalCount", productList.size());
        return VIEW_SALE;
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String aboutUs(ModelMap model)
    {
        return VIEW_ABOUT_US;
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contactUs(ModelMap model)
    {
        return VIEW_CONTACT_US;
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404(ModelMap model)
    {
        return VIEW_NOT_FOUND;
    }

}

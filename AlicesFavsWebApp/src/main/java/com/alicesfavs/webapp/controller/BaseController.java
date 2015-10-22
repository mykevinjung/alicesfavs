package com.alicesfavs.webapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, ModelMap model)
    {
        model.addAttribute("siteId", siteId);
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

package com.alicesfavs.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by kjung on 10/19/15.
 */
@Controller
public class BaseController
{

    private static final String VIEW_INDEX = "index";
    private static final String VIEW_ABOUT_US = "about-us";
    private static final String VIEW_CONTACT_US = "contact-us";
    private static final String VIEW_DISCLAIMER = "disclaimer";
    private static final String VIEW_NOT_FOUND = "error404";

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        return "comingsoon";
    }

    @RequestMapping(value = "/sale", method = RequestMethod.GET)
    public String saleRedirect(ModelMap model)
    {
        return VIEW_INDEX;
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String aboutUs(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");

        return VIEW_ABOUT_US;
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contactUs(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");

        return VIEW_CONTACT_US;
    }

    @RequestMapping(value = "/disclaimer", method = RequestMethod.GET)
    public String disclaimer(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");
        
        return VIEW_DISCLAIMER;
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404(ModelMap model)
    {
        return VIEW_NOT_FOUND;
    }

}

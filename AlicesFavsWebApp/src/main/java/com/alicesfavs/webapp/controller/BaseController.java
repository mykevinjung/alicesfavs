package com.alicesfavs.webapp.controller;

import com.alicesfavs.webapp.service.NewProductService;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 10/19/15.
 */
@Controller
public class BaseController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    private static final String VIEW_INDEX = "index";
    private static final String VIEW_ABOUT_US = "about-us";
    private static final String VIEW_CONTACT_US = "contact-us";
    private static final String VIEW_DISCLAIMER = "disclaimer";
    private static final String VIEW_NOT_FOUND = "error404";

    private static final String TITLE = "title";
    private static final String SUBTITLE = "subtitle";
    private static final String PARAM_EMAIL_SENT = "emailSent";
    private static final String PARAM_SUBJECT = "subject";
    private static final String PARAM_EMAIL = "email";
    private static final String PARAM_MESSAGE = "message";
    private static final String PARAM_RESPONSE_MSG_LIST = "responseMsgList";
    private static final String PARAM_DEFAULT_SUBJECT = "defaultSubject";
    private static final String PARAM_DEFAULT_EMAIL = "defaultEmail";
    private static final String PARAM_DEFAULT_MESSAGE = "defaultMessage";
    private static final String DEFAULT_SUBJECT = "Subject";
    private static final String DEFAULT_EMAIL = "Your email";
    private static final String DEFAULT_MESSAGE = "Your message and name";

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private NewProductService newProductService;

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

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contactUs(ModelMap model)
    {
        model.putIfAbsent(PARAM_EMAIL_SENT, false);
        model.putIfAbsent(PARAM_SUBJECT, DEFAULT_SUBJECT);
        model.putIfAbsent(PARAM_EMAIL, DEFAULT_EMAIL);
        model.putIfAbsent(PARAM_MESSAGE, DEFAULT_MESSAGE);
        model.addAttribute(PARAM_DEFAULT_SUBJECT, DEFAULT_SUBJECT);
        model.addAttribute(PARAM_DEFAULT_EMAIL, DEFAULT_EMAIL);
        model.addAttribute(PARAM_DEFAULT_MESSAGE, DEFAULT_MESSAGE);
        model.addAttribute(SUBTITLE, "Contact Us");

        return VIEW_CONTACT_US;
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.POST)
    public String sendUsEmail(HttpServletRequest request, ModelMap model)
    {
        final String name = request.getParameter(PARAM_SUBJECT);
        final String email = request.getParameter(PARAM_EMAIL);
        final String message = request.getParameter(PARAM_MESSAGE);
        final List<String> responseMsgList = new ArrayList<>();

        model.addAttribute(PARAM_SUBJECT, name);
        model.addAttribute(PARAM_EMAIL, email);
        model.addAttribute(PARAM_MESSAGE, message);
        model.addAttribute(PARAM_RESPONSE_MSG_LIST, responseMsgList);

        if (!StringUtils.hasText(name) || DEFAULT_SUBJECT.equals(name))
        {
            responseMsgList.add("Please enter a subject.");
        }
        if (!StringUtils.hasText(email) || DEFAULT_EMAIL.equals(email) || !EmailValidator.getInstance().isValid(email))
        {
            responseMsgList.add("Please enter a valid email address.");
        }
        if (!StringUtils.hasText(message) || DEFAULT_MESSAGE.equals(message))
        {
            responseMsgList.add("Please enter a message.");
        }

        if (responseMsgList.size() == 0)
        {
            // then let's send an email
            if ("mykevinjung@gmail.com".equals(email))
            {
                model.addAttribute(PARAM_EMAIL_SENT, true);
                responseMsgList.add("Thank you. Your message has been sent.");
            }
            else
            {
                model.addAttribute(PARAM_EMAIL_SENT, false);
                responseMsgList.add("There was an error in sending an email. Please try later again.");
            }
        }

        return contactUs(model);
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String aboutUs(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");
        model.addAttribute(SUBTITLE, "About Us");
        DecimalFormat formatter = new DecimalFormat("#,###");
        model.addAttribute("totalBrandCount", formatter.format(siteManager.getSites().size()));
        model.addAttribute("totalSalesCount", formatter.format(saleProductService.getTotalSalesCount()));
        model.addAttribute("totalNewArrivalsCount", formatter.format(newProductService.getTotalNewArrivalsCount()));

        return VIEW_ABOUT_US;
    }

    @RequestMapping(value = "/disclaimer", method = RequestMethod.GET)
    public String disclaimer(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");
        model.addAttribute(SUBTITLE, "Disclaimer");
        
        return VIEW_DISCLAIMER;
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404(ModelMap model)
    {
        model.addAttribute(SUBTITLE, "Page Not Found");

        return VIEW_NOT_FOUND;
    }

}

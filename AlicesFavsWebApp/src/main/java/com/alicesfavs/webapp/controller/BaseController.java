package com.alicesfavs.webapp.controller;

import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Constants;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kjung on 10/19/15.
 */
@Controller
public class BaseController
{

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        return "comingsoon";
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.GET)
    public String contactUs(ModelMap model)
    {
        model.putIfAbsent(Constants.PARAM_EMAIL_SENT, false);
        model.putIfAbsent(Constants.PARAM_SUBJECT, Constants.DEFAULT_SUBJECT);
        model.putIfAbsent(Constants.PARAM_EMAIL, Constants.DEFAULT_EMAIL);
        model.putIfAbsent(Constants.PARAM_MESSAGE, Constants.DEFAULT_MESSAGE);
        model.addAttribute(Constants.PARAM_DEFAULT_SUBJECT, Constants.DEFAULT_SUBJECT);
        model.addAttribute(Constants.PARAM_DEFAULT_EMAIL, Constants.DEFAULT_EMAIL);
        model.addAttribute(Constants.PARAM_DEFAULT_MESSAGE, Constants.DEFAULT_MESSAGE);
        model.addAttribute(Constants.SUBTITLE, "Contact Us");

        return Constants.VIEW_CONTACT_US;
    }

    @RequestMapping(value = "/contact-us", method = RequestMethod.POST)
    public String sendUsEmail(HttpServletRequest request, ModelMap model)
    {
        final String name = request.getParameter(Constants.PARAM_SUBJECT);
        final String email = request.getParameter(Constants.PARAM_EMAIL);
        final String message = request.getParameter(Constants.PARAM_MESSAGE);
        final List<String> responseMsgList = new ArrayList<>();

        model.addAttribute(Constants.PARAM_SUBJECT, name);
        model.addAttribute(Constants.PARAM_EMAIL, email);
        model.addAttribute(Constants.PARAM_MESSAGE, message);
        model.addAttribute(Constants.PARAM_RESPONSE_MSG_LIST, responseMsgList);

        if (!StringUtils.hasText(name) || Constants.DEFAULT_SUBJECT.equals(name))
        {
            responseMsgList.add("Please enter a subject.");
        }
        if (!StringUtils.hasText(email) || Constants.DEFAULT_EMAIL.equals(email) || !EmailValidator.getInstance().isValid(email))
        {
            responseMsgList.add("Please enter a valid email address.");
        }
        if (!StringUtils.hasText(message) || Constants.DEFAULT_MESSAGE.equals(message))
        {
            responseMsgList.add("Please enter a message.");
        }

        if (responseMsgList.size() == 0)
        {
            // then let's send an email
            if ("mykevinjung@gmail.com".equals(email))
            {
                model.addAttribute(Constants.PARAM_EMAIL_SENT, true);
                responseMsgList.add("Thank you. Your message has been sent.");
            }
            else
            {
                model.addAttribute(Constants.PARAM_EMAIL_SENT, false);
                responseMsgList.add("There was an error in sending an email. Please try later again.");
            }
        }

        return contactUs(model);
    }

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String aboutUs(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");
        model.addAttribute(Constants.SUBTITLE, "About Us");

        return Constants.VIEW_ABOUT_US;
    }

    @RequestMapping(value = "/disclaimer", method = RequestMethod.GET)
    public String disclaimer(ModelMap model)
    {
        model.addAttribute("logo", "/resources/images/logo3.png");
        model.addAttribute(Constants.SUBTITLE, "Disclaimer");
        
        return Constants.VIEW_DISCLAIMER;
    }

    @RequestMapping(value = "/error404", method = RequestMethod.GET)
    public String error404(ModelMap model)
    {
        model.addAttribute(Constants.SUBTITLE, "Page Not Found");

        return "comingsoon";
    }

}

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model)
    {
        return VIEW_INDEX;
    }

}

package com.alicesfavs.webapp.service;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kjung on 1/30/16.
 */
@Component
public class BotDetector
{

    public boolean isBot(HttpServletRequest request)
    {
        return false;
    }

}

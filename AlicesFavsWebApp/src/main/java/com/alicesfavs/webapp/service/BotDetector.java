package com.alicesfavs.webapp.service;

import com.alicesfavs.webapp.util.HttpUtils;
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
        final String userAgent = HttpUtils.getUserAgent(request);

        return userAgent.contains("Googlebot")
            || userAgent.contains("MJ12bot")
            || userAgent.contains("James BOT")
            || userAgent.contains("linkdexbot")
            || userAgent.contains("Baiduspider");
    }

}

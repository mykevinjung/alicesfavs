package com.alicesfavs.webapp.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kjung on 4/11/16.
 */
public class HttpUtils
{

    public static String getRemoteAddr(HttpServletRequest request)
    {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null)
        {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

    public static String getUserAgent(HttpServletRequest request)
    {
        return request.getHeader("User-Agent");
    }

}

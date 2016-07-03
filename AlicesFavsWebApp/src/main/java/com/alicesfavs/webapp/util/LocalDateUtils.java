package com.alicesfavs.webapp.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

/**
 * Created by kjung on 11/6/15.
 */
public class LocalDateUtils
{

    private static final ZoneId ZONE_PST = ZoneId.of(ZoneId.SHORT_IDS.get("PST"));

    public static LocalDate getLocalDate(LocalDateTime localDateTime)
    {
        ZonedDateTime utcDateTime = localDateTime.atZone(ZoneOffset.UTC);
        return utcDateTime.withZoneSameInstant(ZONE_PST).toLocalDate();
    }

    public static LocalDate now()
    {
        return getLocalDate(LocalDateTime.now());
    }

}

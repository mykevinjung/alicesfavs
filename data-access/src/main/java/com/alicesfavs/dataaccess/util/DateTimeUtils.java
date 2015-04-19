package com.alicesfavs.dataaccess.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtils
{

    public static Timestamp toTimestamp(LocalDateTime localDateTime)
    {
        if (localDateTime == null)
        {
            return null;
        }
        return new Timestamp(localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
    }

    public static LocalDateTime toLocalDateTime(Timestamp timestamp)
    {
        if (timestamp == null)
        {
            return null;
        }
        return Instant.ofEpochMilli(timestamp.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}

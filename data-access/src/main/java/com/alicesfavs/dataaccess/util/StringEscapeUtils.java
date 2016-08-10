package com.alicesfavs.dataaccess.util;

/**
 * Created by kjung on 8/10/16.
 */
public class StringEscapeUtils
{

    public static String escapeSql(String sql)
    {
        return sql.replace("'", "''");
    }

}

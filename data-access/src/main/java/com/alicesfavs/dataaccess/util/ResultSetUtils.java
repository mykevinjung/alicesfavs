package com.alicesfavs.dataaccess.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kjung on 11/11/15.
 */
public class ResultSetUtils
{

    public static boolean getBoolean(ResultSet rs, String columnName) throws SQLException
    {
        final int result = rs.getInt(columnName);
        return rs.wasNull() ? false : result == 1;
    }

    public static Double getDouble(ResultSet rs, String columnName) throws SQLException
    {
        final double result = rs.getDouble(columnName);
        return rs.wasNull() ? null : result;
    }

    public static Integer getInt(ResultSet rs, String columnName) throws SQLException
    {
        final int result = rs.getInt(columnName);
        return rs.wasNull() ? null : result;
    }

    public static Long getLong(ResultSet rs, String columnName) throws SQLException
    {
        final long result = rs.getLong(columnName);
        return rs.wasNull() ? null : result;
    }

}

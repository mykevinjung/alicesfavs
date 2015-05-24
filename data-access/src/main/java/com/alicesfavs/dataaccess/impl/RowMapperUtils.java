package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Extractable;
import com.alicesfavs.datamodel.ModelBase;
import org.springframework.util.StringUtils;

class RowMapperUtils
{

    static ModelBase mapRowToModelBase(ResultSet rs, int rowNum) throws SQLException
    {
        return mapRowToModelBase(rs, rowNum, null);
    }

    static ModelBase mapRowToModelBase(ResultSet rs, int rowNum, String tableName) throws SQLException
    {
        return new ModelBase(rs.getLong(getColumnName(tableName, "ID")),
            DateTimeUtils.toLocalDateTime(rs.getTimestamp(getColumnName(tableName, "CREATED_DATE"))),
            DateTimeUtils.toLocalDateTime(rs.getTimestamp(getColumnName(tableName, "UPDATED_DATE"))));
    }

    static Extractable mapRowToExtractable(ResultSet rs, int rowNum) throws SQLException
    {
        return mapRowToExtractable(rs, rowNum, null);
    }

    static Extractable mapRowToExtractable(ResultSet rs, int rowNum, String tableName) throws SQLException
    {
        return new Extractable(mapRowToModelBase(rs, rowNum, tableName),
            ExtractStatus.fromCode(rs.getInt(getColumnName(tableName, "EXTRACT_STATUS"))),
            rs.getLong(getColumnName(tableName, "EXTRACT_JOB_ID")),
            DateTimeUtils.toLocalDateTime(rs.getTimestamp(getColumnName(tableName, "EXTRACTED_DATE"))));
    }

    private static String getColumnName(String tableName, String columnName)
    {
        return StringUtils.hasText(tableName) ? tableName + "." + columnName : columnName;
    }

}

package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Extractable;
import com.alicesfavs.datamodel.ModelBase;

class RowMapperUtils
{

    static ModelBase mapRowToModelBase(ResultSet rs, int rowNum) throws SQLException
    {
        return new ModelBase(rs.getLong("ID"), DateTimeUtils.toLocalDateTime(rs.getTimestamp("CREATED_DATE")),
                DateTimeUtils.toLocalDateTime(rs.getTimestamp("UPDATED_DATE")));
    }

    static Extractable mapRowToExtractable(ResultSet rs, int rowNum) throws SQLException
    {
        return new Extractable(mapRowToModelBase(rs, rowNum), ExtractStatus.fromCode(rs.getInt("EXTRACT_STATUS")),
                rs.getLong("EXTRACT_JOB_ID"), DateTimeUtils.toLocalDateTime(rs.getTimestamp("EXTRACTED_DATE")));
    }

}

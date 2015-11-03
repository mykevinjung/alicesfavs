package com.alicesfavs.dataaccess.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.ModelBase;

@Repository
public class DaoSupport<T> extends JdbcDaoSupport
{

    private final String COLUMN_ID = "id";
    private final String COLUMN_CREATED_DATE = "created_date";
    private final String COLUMN_UPDATED_DATE = "updated_date";

    private static final int DEFAULT_QUERY_TIMEOUT = 5;

    @Autowired
    public DaoSupport(DataSource dataSource)
    {
        super();
        setDataSource(dataSource);
    }

    final ModelBase insert(String sql, int[] types, Object[] params)
    {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, types);
        factory.setReturnGeneratedKeys(true);
        factory.setGeneratedKeysColumnNames(COLUMN_ID, COLUMN_CREATED_DATE, COLUMN_UPDATED_DATE);
        getJdbcTemplate(DEFAULT_QUERY_TIMEOUT).update(factory.newPreparedStatementCreator(params), keyHolder);

        Map<String, Object> result = keyHolder.getKeys();
        final ModelBase modelBase = new ModelBase((Long) result.get(COLUMN_ID),
            DateTimeUtils.toLocalDateTime((Timestamp) result.get(COLUMN_CREATED_DATE)),
            DateTimeUtils.toLocalDateTime((Timestamp) result.get(COLUMN_UPDATED_DATE)));

        return modelBase;
    }

    final LocalDateTime update(String sql, int[] types, Object[] params)
    {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, types);
        factory.setReturnGeneratedKeys(true);
        factory.setGeneratedKeysColumnNames(COLUMN_UPDATED_DATE);
        getJdbcTemplate(DEFAULT_QUERY_TIMEOUT).update(factory.newPreparedStatementCreator(params), keyHolder);

        Map<String, Object> result = keyHolder.getKeys();

        return DateTimeUtils.toLocalDateTime((Timestamp) result.get(COLUMN_UPDATED_DATE));
    }

    final int delete(String sql, int[] types, Object[] params)
    {
        final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, types);
        return getJdbcTemplate(DEFAULT_QUERY_TIMEOUT).update(factory.newPreparedStatementCreator(params));
    }

    final int updateMultiple(String sql, int[] types, Object[] params)
    {
        final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, types);
        return getJdbcTemplate(DEFAULT_QUERY_TIMEOUT).update(factory.newPreparedStatementCreator(params));
    }

    final T selectObject(String sql, int[] types, Object[] params, RowMapper<T> rowMapper)
    {
        return getJdbcTemplate(DEFAULT_QUERY_TIMEOUT).queryForObject(sql, params, types, rowMapper);
    }

    final List<T> selectObjectList(String sql, int[] types, Object[] params, RowMapper<T> rowMapper)
    {
        return selectObjectList(sql, types, params, rowMapper, DEFAULT_QUERY_TIMEOUT);
    }

    final List<T> selectObjectList(String sql, int[] types, Object[] params, RowMapper<T> rowMapper, int queryTimeoutSeconds)
    {
        final PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(sql, types);

        return getJdbcTemplate(queryTimeoutSeconds).query(factory.newPreparedStatementCreator(params), rowMapper);
    }

    private JdbcTemplate getJdbcTemplate(int timeoutSeconds)
    {
        final JdbcTemplate jdbcTemplate = getJdbcTemplate();
        jdbcTemplate.setQueryTimeout(timeoutSeconds);

        return jdbcTemplate;
    }

}

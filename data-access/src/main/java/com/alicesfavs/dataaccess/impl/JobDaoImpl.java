package com.alicesfavs.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.List;

import com.alicesfavs.dataaccess.util.ResultSetUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.alicesfavs.dataaccess.JobDao;
import com.alicesfavs.dataaccess.util.DateTimeUtils;
import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.ModelBase;
import com.alicesfavs.datamodel.Job.Mode;
import com.alicesfavs.datamodel.Job.Status;

@Repository
public class JobDaoImpl implements JobDao
{

    private static final String INSERT_JOB = "INSERT INTO JOB (SITE_ID, MODE, STATUS, START_TIME, END_TIME, "
        + "FOUND_CATEGORY, FOUND_PRODUCT, NOT_FOUND_CATEGORY, NOT_FOUND_PRODUCT, TOTAL_SALE_PRODUCT, "
        + "NEW_SALE_PRODUCT, NEW_NEW_ARRIVALS_PRODUCT) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_JOB = "UPDATE JOB SET SITE_ID = ?, MODE = ?, STATUS = ?, START_TIME = ?, "
        + "END_TIME = ?, FOUND_CATEGORY = ?, FOUND_PRODUCT = ?, NOT_FOUND_CATEGORY = ?, "
        + "NOT_FOUND_PRODUCT = ?, TOTAL_SALE_PRODUCT = ?, NEW_SALE_PRODUCT = ?, NEW_NEW_ARRIVALS_PRODUCT = ? "
        + "WHERE ID = ?";

    private static final String SELECT_JOB_BY_ID = "SELECT ID, SITE_ID, MODE, STATUS, START_TIME, END_TIME, "
        + "FOUND_CATEGORY, FOUND_PRODUCT, NOT_FOUND_CATEGORY, NOT_FOUND_PRODUCT, TOTAL_SALE_PRODUCT, "
        + "NEW_SALE_PRODUCT, NEW_NEW_ARRIVALS_PRODUCT, CREATED_DATE, UPDATED_DATE FROM JOB WHERE ID = ?";

    private static final String SELECT_JOBS_BY_SITE_ID = "SELECT ID, SITE_ID, MODE, STATUS, START_TIME, END_TIME, "
        + "FOUND_CATEGORY, FOUND_PRODUCT, NOT_FOUND_CATEGORY, NOT_FOUND_PRODUCT, TOTAL_SALE_PRODUCT, "
        + "NEW_SALE_PRODUCT, NEW_NEW_ARRIVALS_PRODUCT, CREATED_DATE, UPDATED_DATE FROM JOB "
        + "WHERE SITE_ID = ? AND CREATED_DATE >= ? ORDER BY CREATED_DATE ASC";

    private static final int[] INSERT_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER,
            Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER };

    private static final int[] UPDATE_PARAM_TYPES =
        { Types.BIGINT, Types.INTEGER, Types.INTEGER, Types.TIMESTAMP, Types.TIMESTAMP, Types.INTEGER, Types.INTEGER,
            Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.BIGINT };

    private static final int[] SELECT_PARAM_TYPES =
        { Types.BIGINT };

    private static final int[] SELECT_JOBS_BY_SITE_PARAM_TYPES =
        { Types.BIGINT, Types.TIMESTAMP };

    @Autowired
    private DaoSupport<Job> daoSupport;

    public Job insertJob(long siteId, Mode jobMode, Status jobStatus, LocalDateTime startTime, LocalDateTime endTime,
        Integer foundCategoryNo, Integer foundProductNo, Integer notFoundCategoryNo, Integer notFoundProductNo,
        Integer totalSaleProduct, Integer newSaleProduct, Integer newNewArrivalsProduct)
    {
        final Object[] params =
            { siteId, jobMode.getCode(), jobStatus.getCode(), DateTimeUtils.toTimestamp(startTime),
                DateTimeUtils.toTimestamp(endTime), foundCategoryNo, foundProductNo, notFoundCategoryNo,
                notFoundProductNo, totalSaleProduct, newSaleProduct, newNewArrivalsProduct };
        final ModelBase modelBase = daoSupport.insert(INSERT_JOB, INSERT_PARAM_TYPES, params);

        final Job job = new Job(modelBase, siteId, jobMode);
        job.status = jobStatus;
        job.startTime = startTime;
        job.endTime = endTime;
        job.foundCategory = foundCategoryNo;
        job.foundProduct = foundProductNo;
        job.notFoundCategory = notFoundCategoryNo;
        job.notFoundProduct = notFoundProductNo;
        job.totalSaleProduct = totalSaleProduct;
        job.newSaleProduct = newSaleProduct;
        job.newNewArrivalsProduct = newNewArrivalsProduct;

        return job;
    }

    public Job updateJob(Job job)
    {
        final Object[] params =
            { job.siteId, job.mode.getCode(), job.status.getCode(), DateTimeUtils.toTimestamp(job.startTime),
                DateTimeUtils.toTimestamp(job.endTime), job.foundCategory, job.foundProduct, job.notFoundCategory,
                job.notFoundProduct, job.totalSaleProduct, job.newSaleProduct, job.newNewArrivalsProduct, job.id };
        job.updatedDate = daoSupport.update(UPDATE_JOB, UPDATE_PARAM_TYPES, params);

        return job;
    }

    public Job selectJobById(long jobId)
    {
        final Object[] params =
            { jobId };
        return daoSupport.selectObject(SELECT_JOB_BY_ID, SELECT_PARAM_TYPES, params, new JobRowMapper());
    }

    @Override
    public List<Job> selectJobs(long siteId, LocalDateTime afterCreatedDate)
    {
        final Object[] params = { siteId, DateTimeUtils.toTimestamp(afterCreatedDate) };

        return daoSupport
            .selectObjectList(SELECT_JOBS_BY_SITE_ID, SELECT_JOBS_BY_SITE_PARAM_TYPES, params,
                new JobRowMapper());
    }

    private class JobRowMapper implements RowMapper<Job>
    {
        public Job mapRow(ResultSet rs, int rowNum) throws SQLException
        {
            final ModelBase modelBase = RowMapperUtils.mapRowToModelBase(rs, rowNum);
            final Job job = new Job(modelBase, rs.getLong("SITE_ID"), Mode.fromCode(rs.getInt("MODE")));
            job.status = Status.fromCode(rs.getInt("STATUS"));
            job.startTime = DateTimeUtils.toLocalDateTime(rs.getTimestamp("START_TIME"));
            job.endTime = DateTimeUtils.toLocalDateTime(rs.getTimestamp("END_TIME"));
            job.foundCategory = ResultSetUtils.getInt(rs, "FOUND_CATEGORY");
            job.foundProduct = ResultSetUtils.getInt(rs, "FOUND_PRODUCT");
            job.notFoundCategory = ResultSetUtils.getInt(rs, "NOT_FOUND_CATEGORY");
            job.notFoundProduct = ResultSetUtils.getInt(rs, "NOT_FOUND_PRODUCT");
            job.totalSaleProduct = ResultSetUtils.getInt(rs, "TOTAL_SALE_PRODUCT");
            job.newSaleProduct = ResultSetUtils.getInt(rs, "NEW_SALE_PRODUCT");
            job.newNewArrivalsProduct = ResultSetUtils.getInt(rs, "NEW_NEW_ARRIVALS_PRODUCT");

            return job;
        }
    }

}

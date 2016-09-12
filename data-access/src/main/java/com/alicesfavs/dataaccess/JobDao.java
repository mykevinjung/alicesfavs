package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;
import java.util.List;

import com.alicesfavs.datamodel.Job;

public interface JobDao
{

    Job insertJob(long siteId, Job.Mode jobMode, Job.Status jobStatus, LocalDateTime startTime, LocalDateTime endTime,
        Integer foundCategoryNo, Integer foundProductNo, Integer notFoundCategoryNo, Integer notFoundProductNo);

    Job updateJob(Job job);

    Job selectJobById(long jobId);

    /**
     * This returns a list of jobs for given siteId that were executed afterCreatedDate
     * order by createdDate ascending
     */
    List<Job> selectJobs(long siteId, LocalDateTime afterCreatedDate);

    /**
     * This returns a list of jobs that were executed afterCreatedDate
     * order by createdDate ascending
     */
    List<Job> selectJobs(LocalDateTime afterCreatedDate);
}

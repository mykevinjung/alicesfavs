package com.alicesfavs.dataaccess;

import java.time.LocalDateTime;

import com.alicesfavs.datamodel.Job;

public interface JobDao
{

    Job insertJob(long siteId, Job.Mode jobMode, Job.Status jobStatus, LocalDateTime startTime, LocalDateTime endTime,
        Integer foundCategoryNo, Integer foundProductNo, Integer notFoundCategoryNo, Integer notFoundProductNo);

    void updateJob(Job job);

    Job selectJobById(long jobId);

    Job selectLastJob(long siteId);

}

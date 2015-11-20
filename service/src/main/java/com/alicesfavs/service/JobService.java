package com.alicesfavs.service;

import com.alicesfavs.datamodel.Job;

import java.time.LocalDateTime;
import java.util.List;

public interface JobService
{

    Job createJob(long siteId, Job.Mode jobMode);

    Job completeJob(Job job);

    Job failJob(Job job);

    List<Job> selectJobs(long siteId, LocalDateTime afterCreatedDate);

}

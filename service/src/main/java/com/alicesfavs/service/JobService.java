package com.alicesfavs.service;

import com.alicesfavs.datamodel.Job;

public interface JobService
{

    Job createJob(long siteId, Job.Mode jobMode);

    Job completeJob(Job job);

    Job failJob(Job job);

    Job findJobById(long jobId);

}

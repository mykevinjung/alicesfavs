package com.alicesfavs.service;

import com.alicesfavs.datamodel.Job;

public interface JobService
{

    Job createJob(long siteId, Job.Mode jobMode);

    void completeJob(Job job);

    Job getJob(long jobId);

}

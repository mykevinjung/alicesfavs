package com.alicesfavs.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.dataaccess.JobDao;
import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Job.Mode;
import com.alicesfavs.service.JobService;

@Component("jobService")
public class JobServiceImpl implements JobService
{

    @Autowired
    private JobDao jobDao;
    
    public JobServiceImpl()
    {
        // TODO Auto-generated constructor stub
    }

    public Job createJob(long siteId, Mode jobMode)
    {
        return jobDao.insertJob(siteId, jobMode, Job.Status.CREATED, LocalDateTime.now(), null, 0, 0);
    }

    public void updateJob(Job job)
    {
        // TODO Auto-generated method stub

    }

    public Job getJob(long jobId)
    {
        // TODO Auto-generated method stub
        return null;
    }

}

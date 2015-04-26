package com.alicesfavs.batch.extractor;

import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;

public interface SiteProcessor
{

    void process(Job job, Site site) throws SiteProcessException;
    
}

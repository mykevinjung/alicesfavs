package com.alicesfavs.batch.processor;

import com.alicesfavs.datamodel.Site;

public interface SiteProcessor
{

    void process(long jobId, Site site) throws SiteProcessException;
    
}

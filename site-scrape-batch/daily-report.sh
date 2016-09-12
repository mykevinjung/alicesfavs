#!/bin/bash
cd /opt/alicesfavs/site-scrape-batch
java -cp af-site-scrape-batch-1.0.jar:lib/* -DEXECUTION_ENV=prod -Dlog4j.configurationFile=env/prod/log4j2.xml com.alicesfavs.ReportApp $1

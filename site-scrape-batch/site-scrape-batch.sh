#!/bin/bash
cd /Users/kjung/src/sungmuk/alicesfavs/site-scrape-batch
java -cp target/af-site-scrape-batch-1.0.jar:target/lib/* -DEXECUTION_ENV=prod -Dlog4j.configurationFile=env/dev/log4j2.xml -Dphantomjs.path=/Users/kjung/src/sungmuk/alicesfavs/resources/aws/phantomjs-2.1.1-macosx/bin/phantomjs com.alicesfavs.SiteScrapeBatchApp $1 $2

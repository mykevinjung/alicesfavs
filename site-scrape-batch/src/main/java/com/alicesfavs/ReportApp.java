package com.alicesfavs;

import com.alicesfavs.batch.report.DailySummaryReport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReportApp
{

    public static void main(String[] args) throws Exception
    {
        final ApplicationContext context = new ClassPathXmlApplicationContext("site-scrape-batch.xml");
        final DailySummaryReport report = context.getBean(DailySummaryReport.class);
        report.sendReport(args);
    }

}

package com.alicesfavs.batch.report;

import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.html.Alignment;
import com.alicesfavs.datamodel.html.Cell;
import com.alicesfavs.datamodel.html.Table;
import com.alicesfavs.mail.BodyType;
import com.alicesfavs.mail.MailAddress;
import com.alicesfavs.mail.MailRequest;
import com.alicesfavs.mail.MailSender;
import com.alicesfavs.service.JobService;
import com.alicesfavs.service.SiteService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 9/10/16.
 */
@Component
public class DailySummaryReport
{

    private static final Logger LOGGER = LoggerFactory.getLogger(DailySummaryReport.class);

    private static final ZoneId ZONE_PST = ZoneId.of(ZoneId.SHORT_IDS.get("PST"));
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("MM-dd HH:mm");

    @Autowired
    private JobService jobService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private MailSender mailSender;

    public void sendReport(String[] args) throws Exception
    {
        final String emailTemplate = IOUtils.toString(getClass().getClassLoader()
            .getResourceAsStream("template/DailySummaryReport.html"));
        final Map<String, String> valuesMap = new HashMap<>();
        valuesMap.put("body", generateBody(args));
        final StrSubstitutor sub = new StrSubstitutor(valuesMap);
        final String emailContent = sub.replace(emailTemplate);

        final MailRequest mailRequest = new MailRequest();
        mailRequest.withSubject("Daily Summary - " + getPstDateNow())
            .withFromAddress(new MailAddress("alice@alicesfavs.com", "Alice's Favs"))
            .withToAddress(new MailAddress("alice@alicesfavs.com", "Alice's Favs"))
            .withBody(emailContent).withBodyType(BodyType.HTML);
        mailSender.send(mailRequest);

        LOGGER.info("Email " + mailRequest.getSubject() + " sent");
    }

    private String generateBody(String[] args)
    {
        final LocalDateTime startDate = getStartDate(args);
        final List<Site> siteList = siteService.findAllSites();
        final StringBuilder builder = new StringBuilder();
        for (Site site : siteList)
        {
            final List<Job> jobs = jobService.selectJobs(site.id, startDate);
            if (!jobs.isEmpty())
            {
                appendSite(builder, site);
                final Table table = createTable(jobs);
                builder.append(table.getHtml()).append("<br>");
            }
        }

        return builder.toString();
    }

    private Table createTable(List<Job> jobs)
    {
        final Table table = new Table();
        table.addRow(new Cell("Start Time"), new Cell("F/C"),
            new Cell("F/P"), new Cell("N/C"), new Cell("N/P"));
        for (Job job : jobs)
        {
            table.addRow(new Cell(job.startTime.format(DATE_TIME_FORMATTER)),
                new Cell(job.foundCategory, Alignment.RIGHT),
                new Cell(job.foundProduct, Alignment.RIGHT),
                new Cell(job.notFoundCategory, Alignment.RIGHT),
                new Cell(job.notFoundProduct, Alignment.RIGHT));
        }
        return table;
    }

    private void appendSite(StringBuilder builder, Site site)
    {
        builder.append("<font size=\"5\" face=\"arial, sans-serif\">")
            .append("<a href=\"").append(site.url).append("\">")
            .append(site.displayName).append("</a>").append("</font>");
    }

    private LocalDate getPstDateNow()
    {
        ZonedDateTime utcDateTime = LocalDateTime.now().atZone(ZoneOffset.UTC);
        return utcDateTime.withZoneSameInstant(ZONE_PST).toLocalDate();
    }


    private LocalDateTime getStartDate(String[] args)
    {
        long days = 7;
        if (args != null && args.length > 0 && NumberUtils.isNumber(args[0]))
        {
            days = NumberUtils.toLong((args[0]));
        }
        return LocalDateTime.now().minusDays(days);
    }

}

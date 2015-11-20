package com.alicesfavs.batch;

import com.alicesfavs.batch.extractor.ExtractException;
import com.alicesfavs.batch.extractor.ProductExtractor;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.service.ProductService;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.service.JobService;
import com.alicesfavs.service.SiteService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Component("siteScrapeJob")
public class SiteScrapeJob
{

    private static final Logger LOGGER = LoggerFactory.getLogger(SiteScrapeJob.class);

    private static final String REFRESH_SALE_URI = "/refresh/sale/";
    private static final String REFRESH_NEW_ARRIVAL_URI = "/refresh/new-arrivals/";
    private static final int FOUND_PRODUCT_MAX_VARIATION_PCT = 15;
    private static final int TOTAL_SALE_PRODUCT_MAX_VARIATION_PCT = 40;

    @Autowired
    private SiteService siteService;

    @Autowired
    private JobService jobService;

    @Autowired
    private ProductExtractor productExtractor;

    @Autowired
    private ProductService productService;

    @Autowired
    private BatchConfig batchConfig;

    public void execute(String siteId, boolean forceSave)
    {
        final Site site = siteService.findSiteById(siteId);

        final Job job = jobService.createJob(site.id, Job.Mode.FULL_EXTRACT);
        final List<Job> jobList = jobService.selectJobs(site.id,
            LocalDateTime.now().minus(batchConfig.getCheckJobDayAfter(), ChronoUnit.DAYS));

        LOGGER.info("Job is created: " + job.id);

        try
        {
            final Map<String, List<ProductExtract>> productExtractMap = productExtractor.extractProduct(job, site);
            LOGGER.info("Number of products found: " + productExtractMap.size());

            if (!forceSave)
            {
                validateScrapeResult(jobList, site, productExtractMap);
            }
            saveProduct(job, site, productExtractMap);

            //createSearchableProduct(categoryMap, productMap);
            //final SearchableProductCreator creator = new SearchableProductCreator(batchConfig);

            jobService.completeJob(job);

            checkResult(jobList, job, site);
            refreshSite(site);
        }
        catch (Exception e)
        {
            LOGGER.error("Exception in extracting products", e);
            if (job != null)
            {
                jobService.failJob(job);
            }
        }
        LOGGER.info(job.toString());
    }

    private Map<String, Product> saveProduct(Job job, Site site, Map<String, List<ProductExtract>> productExtractMap)
    {
        LOGGER.info("Saving product map...");
        final Map<String, Product> productMap =
            productService.saveProduct(job, site, ExtractStatus.EXTRACTED, productExtractMap);
        job.foundProduct = productMap.size();
        job.notFoundProduct = productService.markNotFoundProduct(job, site);
        job.totalSaleProduct = getTotalSaleProductCount(productMap);

        return productMap;
    }

    private void validateScrapeResult(List<Job> jobList, Site site, Map<String, List<ProductExtract>> productExtractMap)
        throws ExtractException
    {
        final int lastFoundProduct = getLastFoundProduct(jobList);
        final int curFoundProduct = productExtractMap.size();
        final int variationFoundProduct = ((lastFoundProduct - curFoundProduct) * 100) / lastFoundProduct;
        if (variationFoundProduct > FOUND_PRODUCT_MAX_VARIATION_PCT)
        {
            throw new ExtractException(
                "Too many products missing for site " + site.stringId + ". Last found " + lastFoundProduct
                    + " vs Current found " + curFoundProduct);
        }
    }

    private void checkResult(List<Job> jobList, Job job, Site site)
    {
        final int avgFoundProduct = getAverageFoundProduct(jobList);
        final int variationFoundProduct =
            ((avgFoundProduct - job.foundProduct) * 100) / avgFoundProduct;
        if (variationFoundProduct > FOUND_PRODUCT_MAX_VARIATION_PCT)
        {
            LOGGER.warn("Too many products missing in average for site {}. Average count {} vs Current count {}.",
                site.stringId, avgFoundProduct, job.foundProduct);
        }

        final int avgTotalSaleProduct = getAverageTotalSaleProduct(jobList);
        final int variationTotalSaleProduct =
            ((avgTotalSaleProduct - job.totalSaleProduct) * 100) / avgTotalSaleProduct;
        if (variationTotalSaleProduct > TOTAL_SALE_PRODUCT_MAX_VARIATION_PCT)
        {
            LOGGER.warn("Too small number of sale products found for site {}. Average count {} vs Current count {}.",
                site.stringId, avgTotalSaleProduct, job.totalSaleProduct);
        }
    }

    private int getLastFoundProduct(List<Job> jobList)
    {
        for (int index = jobList.size() - 1; index >= 0; index--)
        {
            final Job job = jobList.get(index);
            if (Job.Status.COMPLETED == job.status)
            {
                return job.foundProduct;
            }
        }

        return 0;
    }

    private int getAverageFoundProduct(List<Job> jobList)
    {
        int sumFoundProduct = 0;
        int completedJobCount = 0;
        for (Job job : jobList)
        {
            if (Job.Status.COMPLETED == job.status)
            {
                sumFoundProduct += job.foundProduct;
                completedJobCount++;
            }
        }

        return (sumFoundProduct / completedJobCount);
    }

    private int getAverageTotalSaleProduct(List<Job> jobList)
    {
        int sumTotalSaleProduct = 0;
        for (Job job : jobList)
        {
            if (Job.Status.COMPLETED == job.status)
            {
                sumTotalSaleProduct += job.totalSaleProduct;
            }
        }

        return (sumTotalSaleProduct / jobList.size());
    }

    private int getTotalSaleProductCount(Map<String, Product> productMap)
    {
        int totalSaleProduct = 0;
        for (Product product : productMap.values())
        {
            if (product.saleStartDate != null)
            {
                totalSaleProduct++;
            }
        }

        return totalSaleProduct;
    }

    private void refreshSite(Site site)
    {
        for (String refreshAddr : batchConfig.getRefreshAddrArray())
        {
            refreshSite(refreshAddr, REFRESH_SALE_URI, site);
            refreshSite(refreshAddr, REFRESH_NEW_ARRIVAL_URI, site);
        }
    }

    private void refreshSite(String refreshAddr, String baseUri, Site site)
    {
        final String url = "http://" + refreshAddr + baseUri + site.stringId;
        try
        {
            LOGGER.info("Refreshing on " + url);
            Jsoup.connect(url).timeout(90 * 1000).userAgent("Alice's Favs SmartCrawler").get();
        }
        catch (HttpStatusException e)
        {
            LOGGER.error("Failed to refresh. Http Status Code " + e.getStatusCode() + " in opening " + url, e);
        }
        catch (Exception e)
        {
            LOGGER.error("Failed to refresh " + url, e);
        }
    }

}

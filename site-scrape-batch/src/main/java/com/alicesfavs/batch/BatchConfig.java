package com.alicesfavs.batch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.alicesfavs.sitescraper.extractspec.ProductDetailExtractSpec;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alicesfavs.datamodel.Site;
import com.alicesfavs.sitescraper.extractspec.CategoryExtractSpec;
import com.alicesfavs.sitescraper.extractspec.NextPageExtractSpec;
import com.alicesfavs.sitescraper.extractspec.ProductExtractSpec;

@Configuration
@PropertySource("classpath:/env/${EXECUTION_ENV:dev}/batch_config.properties")
public class BatchConfig
{

    private static final String CATEGORY_SPEC_SUFFIX = "_category_spec.json";
    private static final String NEXTPAGE_SPEC_SUFFIX = "_nextpage_spec.json";
    private static final String PRODUCT_SPEC_SUFFIX = "_product_spec.json";
    private static final String PRODUCT_DETAIL_SPEC_SUFFIX = "_product_detail_spec.json";

    @Value(value = "${batch.siteextract.root}")
    private String extractSpecRoot;

    @Value(value = "${batch.refreshaddr}")
    private String refreshAddr;

    @Value(value = "${batch.checkjob.dayafter}")
    private int checkJobDayAfter;

    public List<CategoryExtractSpec> getCategoryExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, CATEGORY_SPEC_SUFFIX, CategoryExtractSpec.class);
    }

    public List<ProductExtractSpec> getProductExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, PRODUCT_SPEC_SUFFIX, ProductExtractSpec.class);
    }

    public List<ProductDetailExtractSpec> getProductDetailExtractSpec(Site site) throws IOException
    {
        try
        {
            return getExtractSpec(site, PRODUCT_DETAIL_SPEC_SUFFIX, ProductDetailExtractSpec.class);
        }
        catch (FileNotFoundException e)
        {
            return null;
        }
    }

    public List<NextPageExtractSpec> getNextPageExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, NEXTPAGE_SPEC_SUFFIX, NextPageExtractSpec.class);
    }

    public String getRefreshAddr()
    {
        return refreshAddr;
    }

    public String[] getRefreshAddrArray()
    {
        return refreshAddr != null ? refreshAddr.split(",") : new String[0];
    }

    public int getCheckJobDayAfter()
    {
        return checkJobDayAfter;
    }

    private List getExtractSpec(Site site, String suffix, Class<?> clazz) throws IOException
    {
        final String specPath = extractSpecRoot + File.separator + site.stringId + File.separator + site.stringId
                + suffix;
        final File file = new File(specPath);
        if (!file.exists())
        {
            throw new FileNotFoundException(specPath + " not found");
        }
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File(specPath), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

}

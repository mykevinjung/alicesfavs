package com.alicesfavs.batch;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @Value(value = "${batch.siteextract.root}")
    private String extractSpecRoot;

    public List<CategoryExtractSpec> getCategoryExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, CATEGORY_SPEC_SUFFIX, CategoryExtractSpec.class);
    }

    public List<ProductExtractSpec> getProductExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, PRODUCT_SPEC_SUFFIX, ProductExtractSpec.class);
    }

    public List<NextPageExtractSpec> getNextPageExtractSpec(Site site) throws IOException
    {
        return getExtractSpec(site, NEXTPAGE_SPEC_SUFFIX, NextPageExtractSpec.class);
    }

    private List getExtractSpec(Site site, String suffix, Class<?> clazz) throws IOException
    {
        final String specPath = extractSpecRoot + File.separator + site.stringId + File.separator + site.stringId
                + suffix;
        final ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(new File(specPath), mapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

}
package com.alicesfavs.batch.extractor;

import com.alicesfavs.batch.BatchConfig;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;

import java.util.List;
import java.util.Map;

/**
 * This will be responsible for creating searchable products, generating a json file and then submit to cloudsearch.
 * Created by kjung on 4/30/15.
 */
public class SearchableProductCreator
{

    private final BatchConfig batchConfig;

    public SearchableProductCreator(BatchConfig batchConfig)
    {
        this.batchConfig = batchConfig;
    }

    public void createSearchableProduct(Map<Category, List<ProductExtract>> categoryMap,
        Map<String, Product> productMap)
    {
        for (final Category category : categoryMap.keySet())
        {
            for (ProductExtract pe : categoryMap.get(category))
            {
                final Product product = productMap.get(pe.id);
                if (product != null)
                {
                    // TODO create SearchableProduct
                }
            }
        }
    }

}

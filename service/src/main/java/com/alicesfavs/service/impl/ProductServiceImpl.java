package com.alicesfavs.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alicesfavs.dataaccess.PriceHistoryDao;
import com.alicesfavs.dataaccess.ProductDao;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.service.ProductService;

@Component("productService")
public class ProductServiceImpl implements ProductService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final double MIN_CHANGE_PERCENTAGE = 1D;
    private static final int MAX_SALE_DATE = 90;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PriceHistoryDao priceHistoryDao;

    public Map<String, Product> saveProduct(long jobId, long siteId, Map<String, List<ProductExtract>> peMap)
    {
        LOGGER.info("Size of Product Extract map: " + peMap.size());
        final Map<String, Product> productMap = new HashMap<>();
        for (String extractId : peMap.keySet())
        {
            try
            {
                final List<ProductExtract> peList = peMap.get(extractId);
                final Product product = saveProduct(jobId, siteId, peList);
                productMap.put(extractId, product);
            }
            catch (Exception e)
            {
                LOGGER.error("Error saving product", e);
            }
        }

        return productMap;
    }

    public Product saveProduct(long jobId, long siteId, List<ProductExtract> productExtractList)
    {
        final ProductExtract bestProduct = findBestExtract(productExtractList);
        return saveProduct(jobId, siteId, bestProduct);
    }

    public Product findProduct(long siteId, String siteProductId)
    {
        return productDao.selectProductById(siteId, siteProductId);
    }

    public Product saveProduct(long jobId, long siteId, ProductExtract productExtract)
    {
        Product product = findProduct(siteId, productExtract.id);
        if (product == null)
        {
            product = createProduct(jobId, siteId, productExtract);
        }
        else
        {
            updateProduct(jobId, product, productExtract);
        }

        return product;
    }

    @Override
    public int markNotFoundProduct(long jobId, long siteId)
    {
        return productDao.updateExtractStatus(siteId, jobId, ExtractStatus.NOT_FOUND);
    }

    private ProductExtract findBestExtract(List<ProductExtract> productExtractList)
    {
        ProductExtract bestExtract = null;
        for (ProductExtract pe : productExtractList)
        {
            if (bestExtract == null)
            {
                bestExtract = pe;
            }
            else
            {
                final Double pePrice = stringPriceToDouble(pe.price);
                final Double bestPrice = stringPriceToDouble(bestExtract.price);
                if (pePrice < bestPrice)
                {
                    bestExtract = pe;
                }
                else if (bestExtract.wasPrice == null && pe.wasPrice != null)
                {
                    bestExtract = pe;
                }
            }
        }

        return bestExtract;
    }

    private Product createProduct(long jobId, long siteId, ProductExtract productExtract)
    {
        final Double price = stringPriceToDouble(productExtract.price);
        final Double wasPrice = stringPriceToDouble(productExtract.wasPrice);
        final LocalDateTime saleStartDate = (wasPrice != null) ? LocalDateTime.now() : null;
        final Double regularPrice = (wasPrice != null) ? wasPrice : price;

        return productDao.insertProduct(siteId, productExtract, price, wasPrice, regularPrice, saleStartDate, null,
                ExtractStatus.EXTRACTED, jobId, LocalDateTime.now());
    }

    private void updateProduct(long jobId, Product existingProduct, ProductExtract newExtract)
    {
        final String newPriceExtract = newExtract.price;
        final Double newPrice = stringPriceToDouble(newPriceExtract);
        final Double newWasPrice = stringPriceToDouble(newExtract.wasPrice);
        final Double oldPrice = existingProduct.price;
        final Double oldWasPrice = existingProduct.wasPrice;
        final String oldPriceExtract = existingProduct.productExtract.price;

        existingProduct.price = newPrice;
        existingProduct.productExtract.brandName = newExtract.brandName;
        existingProduct.productExtract.imageUrl = newExtract.imageUrl;
        existingProduct.productExtract.name = newExtract.name;
        existingProduct.productExtract.price = newExtract.price;
        existingProduct.productExtract.url = newExtract.url;
        existingProduct.productExtract.wasPrice = newExtract.wasPrice;
        if (existingProduct.saleStartDate == null)
        {
            if ((newWasPrice != null) || hasPriceDecreased(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
            {
                existingProduct.saleStartDate = LocalDateTime.now();
            }
        }
        else if (newWasPrice == null)
        {
            if ((oldWasPrice != null) || (hasPriceIncreased(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
                    || (existingProduct.saleStartDate.isBefore(LocalDateTime.now().minusDays(MAX_SALE_DATE))))
            {
                existingProduct.saleStartDate = null;
            }
        }
        // TODO: regular price redefine
        existingProduct.regularPrice = (newWasPrice != null) ? newWasPrice : newPrice;
        existingProduct.wasPrice = newWasPrice;
        existingProduct.extractJobId = jobId;
        existingProduct.extractedDate = LocalDateTime.now();
        // TODO extract status should be hidden when site.display is false
        existingProduct.extractStatus = ExtractStatus.EXTRACTED;

        productDao.updateProduct(existingProduct);

        if (!newPrice.equals(oldPrice))
        {
            priceHistoryDao
                    .insertPriceHistory(existingProduct.id, oldPriceExtract, newPriceExtract, oldPrice, newPrice);
        }
    }

    private boolean hasPriceDecreased(Double oldPrice, Double newPrice, double minimumPercentage)
    {
        return (100D * (oldPrice.doubleValue() - newPrice.doubleValue()) / oldPrice.doubleValue()) > minimumPercentage;
    }

    private boolean hasPriceIncreased(Double oldPrice, Double newPrice, double minimumPercentage)
    {
        return (100D * (newPrice.doubleValue() - oldPrice.doubleValue()) / oldPrice.doubleValue()) > minimumPercentage;
    }

    private Double stringPriceToDouble(String price)
    {
        try
        {
            int lastCurrencyIndex = price.lastIndexOf("$");
            if (lastCurrencyIndex == -1)
            {
                return Double.parseDouble(price);
            }
            else
            {
                return Double.parseDouble(price.substring(lastCurrencyIndex + 1));
            }
        }
        catch (NullPointerException | NumberFormatException e)
        {
            return null;
        }
    }

}

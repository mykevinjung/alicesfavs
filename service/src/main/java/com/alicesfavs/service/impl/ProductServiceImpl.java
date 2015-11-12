package com.alicesfavs.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
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
import org.springframework.util.StringUtils;

@Component("productService")
public class ProductServiceImpl implements ProductService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final double MIN_CHANGE_PERCENTAGE = 1D;
    private static final int MAX_SALE_DATE = 30;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PriceHistoryDao priceHistoryDao;

    public Map<String, Product> saveProduct(long jobId, long siteId, ExtractStatus extractStatus,
        Map<String, List<ProductExtract>> peMap)
    {
        LOGGER.info("Size of Product Extract map: " + peMap.size());
        final Map<String, Product> productMap = new HashMap<>();
        for (String extractId : peMap.keySet())
        {
            final List<ProductExtract> peList = peMap.get(extractId);
            try
            {
                final Product product = saveProduct(jobId, siteId, extractStatus, peList);
                productMap.put(extractId, product);
            }
            catch (Exception e)
            {
                LOGGER.error("Error saving product " + peList.get(0), e);
            }
        }

        return productMap;
    }

    public Product saveProduct(long jobId, long siteId, ExtractStatus extractStatus,
        List<ProductExtract> productExtractList)
    {
        final ProductExtract bestProduct = findBestPriceExtract(productExtractList);
        return saveProduct(jobId, siteId, extractStatus, bestProduct);
    }

    public Product findProduct(long siteId, String siteProductId)
    {
        return productDao.selectProductById(siteId, siteProductId);
    }

    public Product saveProduct(long jobId, long siteId, ExtractStatus extractStatus, ProductExtract productExtract)
    {
        Product product = findProduct(siteId, productExtract.id);
        if (product == null)
        {
            product = createProduct(jobId, siteId, extractStatus, productExtract);
        }
        else
        {
            product = updateProduct(jobId, product, extractStatus, productExtract);
        }

        return product;
    }

    @Override
    public int markNotFoundProduct(long jobId, long siteId)
    {
        return productDao.updateExtractStatus(siteId, jobId, ExtractStatus.NOT_FOUND);
    }

    @Override public List<Product> searchSaleProducts(long siteId)
    {
        return productDao.selectSaleProducts(siteId, ExtractStatus.EXTRACTED);
    }

    @Override public List<Product> searchNewProducts(long siteId, LocalDateTime afterCreatedDate)
    {
        return productDao.selectNewProducts(siteId, ExtractStatus.EXTRACTED, afterCreatedDate);
    }

    private ProductExtract findBestPriceExtract(List<ProductExtract> productExtractList)
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

    private Product createProduct(long jobId, long siteId, ExtractStatus extractStatus, ProductExtract productExtract)
    {
        final Double price = stringPriceToDouble(productExtract.price);
        final Double wasPrice = stringPriceToDouble(productExtract.wasPrice);
        final LocalDateTime priceChangedDate = LocalDateTime.now();
        final LocalDateTime saleStartDate = (wasPrice != null) ? LocalDateTime.now() : null;
        final Double regularPrice = (wasPrice != null) ? wasPrice : price;

        return productDao.insertProduct(siteId, productExtract, price, wasPrice, regularPrice, priceChangedDate,
            saleStartDate, null, extractStatus, jobId, LocalDateTime.now());
    }

    private Product updateProduct(long jobId, Product existingProduct, ExtractStatus extractStatus,
        ProductExtract newExtract)
    {
        final String newPriceExtract = newExtract.price;
        final Double newPrice = stringPriceToDouble(newPriceExtract);
        final String oldPriceExtract = existingProduct.productExtract.price;
        final Double oldPrice = existingProduct.price;

        existingProduct.price = newPrice;
        existingProduct.productExtract.brandName = newExtract.brandName;
        existingProduct.productExtract.imageUrl = newExtract.imageUrl;
        existingProduct.productExtract.name = newExtract.name;
        existingProduct.productExtract.price = newExtract.price;
        existingProduct.productExtract.url = newExtract.url;

        // set was price
        if (StringUtils.hasText(newExtract.wasPrice))
        {
            existingProduct.wasPrice = stringPriceToDouble(newExtract.wasPrice);
            existingProduct.productExtract.wasPrice = newExtract.wasPrice;
        }
        else if (existingProduct.wasPrice == null)
        {
            if (hasPriceReduced(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
            {
                existingProduct.wasPrice = oldPrice;
                existingProduct.productExtract.wasPrice = oldPriceExtract;
            }
        }
        else if (hasSaleExpired(existingProduct.saleStartDate) ||
            hasPriceIncreased(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
        {
            existingProduct.wasPrice = null;
            existingProduct.productExtract.wasPrice = null;
        }

        // set sale date
        if (existingProduct.wasPrice == null)
        {
            existingProduct.saleStartDate = null;
        }
        else if (existingProduct.saleStartDate == null)
        {
            existingProduct.saleStartDate = LocalDateTime.now();
        }

        // TODO: regular price redefine
        existingProduct.regularPrice = (existingProduct.wasPrice != null) ? existingProduct.wasPrice : newPrice;
        existingProduct.extractJobId = jobId;
        existingProduct.extractedDate = LocalDateTime.now();
        existingProduct.extractStatus = extractStatus;
        final boolean hasPriceChanged = !newPrice.equals(oldPrice);
        if (hasPriceChanged)
        {
            existingProduct.priceChangedDate = LocalDateTime.now();
        }

        final Product product = productDao.updateProduct(existingProduct);
        if (hasPriceChanged)
        {
            priceHistoryDao
                .insertPriceHistory(existingProduct.id, oldPriceExtract, newPriceExtract, oldPrice, newPrice);
        }

        return product;
    }

    private boolean hasSaleExpired(LocalDateTime saleStartDate)
    {
        return saleStartDate != null && saleStartDate.isBefore(LocalDateTime.now().minusDays(MAX_SALE_DATE));
    }

    private boolean hasPriceReduced(Double oldPrice, Double newPrice, double minimumPercentage)
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
            // TODO use site.currency
            final int lastCurrencyIndex = price.lastIndexOf("$");
            final String priceWithoutCurrency = (lastCurrencyIndex == -1) ?
                price :
                price.substring(lastCurrencyIndex + 1);
            return NumberFormat.getInstance().parse(priceWithoutCurrency).doubleValue();
        }
        catch (NullPointerException | ParseException e)
        {
            return null;
        }
    }

}

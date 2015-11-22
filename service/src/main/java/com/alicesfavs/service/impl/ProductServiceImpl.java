package com.alicesfavs.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.Job;
import com.alicesfavs.datamodel.Site;
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
    private static final int MAX_SALE_DATE = 14;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private PriceHistoryDao priceHistoryDao;

    @Override
    public Map<String, Product> saveProduct(Job job, Site site, ExtractStatus extractStatus,
        Map<String, List<ProductExtract>> peMap)
    {
        LOGGER.info("Size of Product Extract map: " + peMap.size());
        final Map<String, Product> productMap = new HashMap<>();
        for (String extractId : peMap.keySet())
        {
            final List<ProductExtract> peList = peMap.get(extractId);
            try
            {
                final Product product = saveProduct(job, site, extractStatus, peList);
                productMap.put(extractId, product);
            }
            catch (Exception e)
            {
                LOGGER.error("Error saving product " + peList.get(0), e);
            }
        }

        return productMap;
    }

    @Override
    public Product saveProduct(Job job, Site site, ExtractStatus extractStatus,
        List<ProductExtract> productExtractList)
    {
        final ProductExtract bestProduct = findBestPriceExtract(site, productExtractList);
        return saveProduct(job, site, extractStatus, bestProduct);
    }

    @Override
    public Product findProduct(long siteId, String siteProductId)
    {
        return productDao.selectProductById(siteId, siteProductId);
    }

    @Override
    public int markNotFoundProduct(Job job, Site site)
    {
        return productDao.updateExtractStatus(site.id, job.id, ExtractStatus.NOT_FOUND);
    }

    @Override
    public List<Product> searchSaleProducts(long siteId)
    {
        return productDao.selectSaleProducts(siteId, ExtractStatus.EXTRACTED);
    }

    @Override
    public List<Product> searchNewProducts(long siteId, LocalDateTime afterCreatedDate)
    {
        return productDao.selectNewProducts(siteId, ExtractStatus.EXTRACTED, afterCreatedDate);
    }

    private Product saveProduct(Job job, Site site, ExtractStatus extractStatus, ProductExtract productExtract)
    {
        Product product = findProduct(site.id, productExtract.id);
        if (product == null)
        {
            product = createProduct(job, site, extractStatus, productExtract);
            if (product.saleStartDate != null)
            {
                job.newSaleProduct++;
            }
            else
            {
                job.newNewArrivalsProduct++;
            }
        }
        else
        {
            final LocalDateTime oldSaleStartDate = product.saleStartDate;
            product = updateProduct(job, site, product, extractStatus, productExtract);
            if (oldSaleStartDate == null && product.saleStartDate != null)
            {
                job.newSaleProduct++;
            }
        }

        return product;
    }

    private ProductExtract findBestPriceExtract(Site site, List<ProductExtract> productExtractList)
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
                final Double pePrice = stringPriceToDouble(site, pe.price);
                final Double bestPrice = stringPriceToDouble(site, bestExtract.price);
                if (pePrice != null && bestPrice != null && pePrice < bestPrice)
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

    private Product createProduct(Job job, Site site, ExtractStatus extractStatus, ProductExtract productExtract)
    {
        final Double price = stringPriceToDouble(site, productExtract.price);
        final Double wasPrice = stringPriceToDouble(site, productExtract.wasPrice);
        final LocalDateTime priceChangedDate = LocalDateTime.now();
        final LocalDateTime saleStartDate = (wasPrice != null) ? LocalDateTime.now() : null;
        final Double regularPrice = (wasPrice != null) ? wasPrice : price;

        return productDao.insertProduct(site.id, productExtract, price, wasPrice, regularPrice, priceChangedDate,
            saleStartDate, null, extractStatus, job.id, LocalDateTime.now());
    }

    private Product updateProduct(Job job, Site site, Product existingProduct, ExtractStatus extractStatus,
        ProductExtract newExtract)
    {
        final String newPriceExtract = newExtract.price;
        final Double newPrice = stringPriceToDouble(site, newPriceExtract);
        final String oldPriceExtract = existingProduct.productExtract.price;
        final Double oldPrice = existingProduct.price;
        final String oldWasPriceExtract = existingProduct.productExtract.wasPrice;

        existingProduct.price = newPrice;
        existingProduct.productExtract.brandName = newExtract.brandName;
        existingProduct.productExtract.imageUrl = newExtract.imageUrl;
        existingProduct.productExtract.name = newExtract.name;
        existingProduct.productExtract.price = newExtract.price;
        existingProduct.productExtract.url = newExtract.url;
        existingProduct.productExtract.wasPrice = newExtract.wasPrice;

        // set was price
        // if new wasPrice exists, use it
        if (StringUtils.hasText(newExtract.wasPrice))
        {
            existingProduct.wasPrice = stringPriceToDouble(site, newExtract.wasPrice);
        }
        // if there was wasPrice extracted before but not any more, then set wasPrice null
        else if (oldWasPriceExtract != null)
        {
            existingProduct.wasPrice = null;
        }
        // no wasPrice has existed ever, but price has reduced
        else if (existingProduct.wasPrice == null)
        {
            if (hasPriceReduced(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
            {
                existingProduct.wasPrice = oldPrice;
            }
        }
        else if (hasSaleExpired(existingProduct.saleStartDate) ||
            hasPriceIncreased(oldPrice, newPrice, MIN_CHANGE_PERCENTAGE))
        {
            existingProduct.wasPrice = null;
        }

        // set sale start date
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
        existingProduct.extractJobId = job.id;
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

    private Double stringPriceToDouble(Site site, String price)
    {
        try
        {
            final int lastCurrencyIndex = price.lastIndexOf(site.currency);
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

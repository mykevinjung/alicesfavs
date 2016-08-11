package com.alicesfavs.service.impl;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alicesfavs.datamodel.Category;
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
        // collect all category info into one
        for (ProductExtract pe : productExtractList)
        {
            bestProduct.aliceCategoryNames.addAll(pe.aliceCategoryNames);
            bestProduct.categoryNames.addAll(pe.categoryNames);
        }
        return saveProduct(job, site, extractStatus, bestProduct);
    }

    @Override
    public void saveProduct(Product product)
    {
        throw new UnsupportedOperationException("why do you need to use this?");
        //productDao.updateProduct(product);
    }

    @Override
    public Product findProduct(long productId)
    {
        return productDao.selectProductById(productId);
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
    public List<Product> searchSaleProducts(String searchText, int startNum, int endNum)
    {
        return productDao.searchSaleProducts(searchText, ExtractStatus.EXTRACTED, startNum, endNum);
    }

    public List<Product> searchSaleProducts(Site site, Category category)
    {
        return null;
    }

    public Map<Category, List<Product>> searchSaleProducts(List<Category> categoryList)
    {
        return productDao.selectSaleProducts(categoryList);
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
        }
        else
        {
            product = updateProduct(job, site, product, extractStatus, productExtract);
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

        return productDao.insertProduct(site, productExtract, price, wasPrice, priceChangedDate,
            saleStartDate, extractStatus, job.id, LocalDateTime.now());
    }

    private Product updateProduct(Job job, Site site, Product existingProduct, ExtractStatus extractStatus,
        ProductExtract newExtract)
    {
        final String newPriceExtract = newExtract.price;
        final Double newPrice = stringPriceToDouble(site, newPriceExtract);
        final String oldPriceExtract = existingProduct.productExtract.price;
        final Double oldPrice = existingProduct.price;

        existingProduct.price = newPrice;
        existingProduct.productExtract.imageUrl = newExtract.imageUrl;
        existingProduct.productExtract.name = newExtract.name;
        existingProduct.productExtract.price = newExtract.price;
        existingProduct.productExtract.url = newExtract.url;
        existingProduct.productExtract.wasPrice = newExtract.wasPrice;
        existingProduct.productExtract.aliceCategoryNames = newExtract.aliceCategoryNames;
        existingProduct.productExtract.categoryNames = newExtract.categoryNames;
        existingProduct.wasPrice = stringPriceToDouble(site, newExtract.wasPrice);

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
        existingProduct.extractJobId = job.id;
        existingProduct.extractedDate = LocalDateTime.now();
        existingProduct.extractStatus = extractStatus;
        final boolean hasPriceChanged = !newPrice.equals(oldPrice);
        if (hasPriceChanged)
        {
            existingProduct.priceChangedDate = LocalDateTime.now();
        }

        final Product product = productDao.updateProduct(existingProduct, site);
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

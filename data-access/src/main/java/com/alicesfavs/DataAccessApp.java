package com.alicesfavs;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alicesfavs.dataaccess.CategoryDao;
import com.alicesfavs.dataaccess.PriceHistoryDao;
import com.alicesfavs.dataaccess.ProductDao;
import com.alicesfavs.dataaccess.SiteDao;
import com.alicesfavs.datamodel.Category;
import com.alicesfavs.datamodel.CategoryExtract;
import com.alicesfavs.datamodel.ExtractStatus;
import com.alicesfavs.datamodel.PriceHistory;
import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.ProductExtract;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.datamodel.BrandLevel;

/**
 * Hello world!
 *
 */
public class DataAccessApp
{
    public static void main(String[] args) throws Exception
    {
        // testCategoryDao();
        // testProductDao();
        // testPriceHistory();
        testSiteDao();
    }

    private static void testSiteDao() throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("data-access.xml");
        SiteDao siteDao = context.getBean(SiteDao.class);
        Site site = siteDao.insertSite("jcrew", "J.Crew", "www.jcrew.com", true, 4,
                BrandLevel.PREMIUM, false);
        System.out.println(site);

        Thread.sleep(1000);

        site.display = false;
        site.displayWeight = 5;
        site.displayName = "";
        site.brandLevel = BrandLevel.GENERAL;
        site.useStoredImage = true;
        siteDao.updateSite(site);
        System.out.println(site);

        System.out.println(siteDao.selectSiteByStringId("jcrew"));
    }

    private static void testProductDao() throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("data-access.xml");
        ProductDao productDao = context.getBean(ProductDao.class);
        ProductExtract productExtract = new ProductExtract("234123416");
        productExtract.name = "product dresses";
        productExtract.url = "http://www.anthropologie.com/anthro/product/dresses/234123416.jsp";
        productExtract.price = "$134.32";
        productExtract.imageUrl = "http://www.anthropologie.com/anthro/product/image/234123416.jpg";
        productExtract.brandName = null;
        productExtract.wasPrice = null;

        Product product = productDao.insertProduct(1002L, productExtract, 134.32, null, 150.00, null, null,
                ExtractStatus.EXTRACTED, 1L, LocalDateTime.now());
        System.out.println(product);

        Thread.sleep(1000);

        product.productExtract.name = "product dress";
        product.saleStartDate = LocalDateTime.now();
        product.productExtract.brandName = "Armani";
        product.productExtract.wasPrice = "140.00";
        product.wasPrice = 140.00;
        product.storedImagePath = "/aafa.jpg";
        product.extractStatus = ExtractStatus.NOT_FOUND;
        product.extractJobId = 2L;
        product.extractedDate = LocalDateTime.now();
        productDao.updateProduct(product);
        System.out.println(product);

        System.out.println(productDao.selectProductById(product.siteId, "234123416"));
    }

    private static void testCategoryDao() throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("data-access.xml");
        CategoryDao categoryDao = context.getBean(CategoryDao.class);
        CategoryExtract categoryExtract1 = new CategoryExtract("Clothing");
        categoryExtract1.url = "http://www.anthropologie.com/";
        CategoryExtract categoryExtract2 = new CategoryExtract("dresses");
        categoryExtract2.url = "http://www.anthropologie.com/anthro/category/dresses/clothes-dresses.jsp";
        CategoryExtract categoryExtract3 = new CategoryExtract("test");
        categoryExtract3.url = "test invalid url";
        Category category = categoryDao.insertCategory(1002L, categoryExtract1, categoryExtract2, categoryExtract3, 1,
                ExtractStatus.EXTRACTED, 1L, LocalDateTime.now());
        System.out.println(category);

        Thread.sleep(1000);

        category.categoryExtract1.url = "http://www.anthropologie.com/#";
        category.categoryExtract2.url = "http://www.anthropologie.com/anthro/category/dresses/clothes-dresses2.jsp";
        category.categoryExtract3.url = "still invalid url";
        category.displayOrder = 3;
        category.extractStatus = ExtractStatus.NOT_FOUND;
        category.extractJobId = 2L;
        category.extractedDate = LocalDateTime.now();
        categoryDao.updateCategory(category);
        System.out.println(category);
    }

    private static void testPriceHistory() throws Exception
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("data-access.xml");
        PriceHistoryDao priceHistoryDao = context.getBean(PriceHistoryDao.class);
        PriceHistory priceHistory = priceHistoryDao.insertPriceHistory(10000002L, "$134.32", "$140.00", 134.32, 140.00);
        System.out.println(priceHistory);

        System.out.println("10000002L size: " + priceHistoryDao.selectPriceHistoryByProductId(10000002L).size());
        System.out.println("10000003L size: " + priceHistoryDao.selectPriceHistoryByProductId(10000003L).size());

    }

}

package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.exception.ResourceNotFoundException;
import com.alicesfavs.webapp.service.ProductSortType;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Constants;
import com.alicesfavs.webapp.uimodel.Page;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.uimodel.UiSaleSummary;
import com.alicesfavs.webapp.util.Pagination;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kjung on 11/4/15.
 */
@Controller
public class ProductController
{

    private static final int MAX_NEW_SALE_THIS_WEEK = 3;

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private WebAppConfig webAppConfig;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap model, Device device)
    {
        final Map<String, List<UiProduct>> categoryProductMap = new LinkedHashMap<>();
        for (AliceCategory aliceCategory : siteManager.getAliceCategoryList())
        {
            final List<UiProduct> newSaleList = saleProductService.getNewSaleProducts(aliceCategory);
            categoryProductMap.put(aliceCategory.name.toLowerCase(), newSaleList);
        }
        model.addAttribute("saleCategoryProductMap", categoryProductMap);
        model.addAttribute(Constants.PAGE_ID, Constants.PAGE_ID_HOME);
        model.addAttribute(Constants.MOBILE, device.isMobile());

        final List<UiSaleSummary> saleSummaryList = saleProductService.getSaleSummary();
        if (!saleSummaryList.isEmpty())
        {
            model.addAttribute(Constants.NEW_SALE_THIS_WEEK,
                saleSummaryList.subList(0, Math.min(MAX_NEW_SALE_THIS_WEEK, saleSummaryList.size())));
        }

        // seo
        final StringBuilder description = new StringBuilder();
        description.append("Alice's Favs - All sales from the favorite brands in one place")
        .append(" for clothing, shoes, bags, accessories and home.")
        .append(" Start your shopping at Alice's Favs!");
        model.addAttribute(Constants.META_DESCRIPTION, description.toString());

        return Constants.VIEW_HOME;
    }

    @RequestMapping(value = "/sale/{category}/{siteId}", method = RequestMethod.GET)
    public String saleByCategorySite(@PathVariable String category, @PathVariable String siteId,
        HttpServletRequest request, ModelMap model, Device device)
    {
        final AliceCategory aliceCategory = siteManager.getAliceCatgory(category);
        if (aliceCategory == null)
        {
            throw new ResourceNotFoundException("Category '" + category + "' not found");
        }
        final Site site = siteManager.getSiteByStringId(siteId);
        if (site == null)
        {
            throw new ResourceNotFoundException("Site '" + siteId + "' not found");
        }

        // category-site specific info
        final List<Site> siteList = new ArrayList<>();
        siteList.add(site);
        model.addAttribute(Constants.BREADCRUMB1, aliceCategory.name);
        model.addAttribute(Constants.BREADCRUMB2, site.displayName);
        model.addAttribute(Constants.SUBTITLE, "Sale - " + aliceCategory.name + " - " + site.displayName);
        model.addAttribute(Constants.PAGE_ID, Constants.PAGE_ID_SALE_CAT_SITE);

        // seo
        final String description = String.format("%s on sale at %s - Alice's Favs"
            + " - All sales from the favorite brands in one place for clothing, shoes, bags, accessories and home.",
            aliceCategory.name, site.displayName);
        model.addAttribute(Constants.META_DESCRIPTION, description);

        return renderSale(aliceCategory, siteList, request, model, device);
    }

    @RequestMapping(value = "/sale/{category}", method = RequestMethod.GET)
    public String saleByCategoryAll(@PathVariable String category, HttpServletRequest request, ModelMap model,
        Device device)
    {
        final AliceCategory aliceCategory = siteManager.getAliceCatgory(category);
        if (aliceCategory == null)
        {
            throw new ResourceNotFoundException("Category '" + category + "' not found");
        }

        // category specific info
        final List<Site> siteList = getAliceCategorySites(aliceCategory);
        model.addAttribute(Constants.BREADCRUMB1, aliceCategory.name);
        model.addAttribute(Constants.SUBTITLE, "Sale - " + aliceCategory.name);
        model.addAttribute(Constants.PAGE_ID, Constants.PAGE_ID_SALE_CAT_ALL);

        // seo
        final String description = String.format("%s on sale - Alice's Favs"
            + " - All sales from the favorite brands in one place for clothing, shoes, bags, accessories and home.",
            aliceCategory.name);
        model.addAttribute(Constants.META_DESCRIPTION, description);

        return renderSale(aliceCategory, siteList, request, model, device);
    }

    @RequestMapping(value = Constants.BRAND_SALE_URL_PREFIX + "{siteId}", method = RequestMethod.GET)
    public String saleByBrand(@PathVariable String siteId, HttpServletRequest request, ModelMap model, Device device)
    {
        final Site site = siteManager.getSiteByStringId(siteId);
        if (site == null)
        {
            throw new ResourceNotFoundException("Site '" + siteId + "' not found");
        }

        // brand specific info
        final List<Site> siteList = new ArrayList<>();
        siteList.add(site);
        model.addAttribute(Constants.BREADCRUMB1, site.displayName);
        model.addAttribute(Constants.SUBTITLE, "Sale - " + site.displayName);
        model.addAttribute(Constants.PAGE_ID, Constants.PAGE_ID_SALE_SITE);

        // seo
        final String description = String.format("All sales at %s - Alice's Favs"
            + " - All sales from the favorite brands in one place for clothing, shoes, bags, accessories and home.",
            site.displayName);
        model.addAttribute(Constants.META_DESCRIPTION, description);

        return renderSale(null, siteList, request, model, device);
    }

    private List<Site> getAliceCategorySites(AliceCategory aliceCategory)
    {
        final List<Site> siteList = new ArrayList<>();
        for (Site site : siteManager.getSites())
        {
            if (saleProductService.hasSaleProducts(site, aliceCategory))
            {
                siteList.add(site);
            }
        }

        return siteList;
    }

    private String renderSale(AliceCategory aliceCategory, List<Site> siteList, HttpServletRequest request,
        ModelMap model, Device device)
    {
        final ProductSortType productSortType = ProductSortType
            .fromCode(request.getParameter(Constants.SORT_BY), ProductSortType.DATE);
        final List<UiProduct> productList = saleProductService
            .getSaleProducts(siteList, aliceCategory, productSortType);
        addProductAttributes(request, model, productList, webAppConfig.getSaleProductPageSize());
        model.addAttribute(Constants.SORT_BY, productSortType.getCode());
        model.addAttribute(Constants.MOBILE, device.isMobile());

        return Constants.VIEW_SALE;
    }

    private void addProductAttributes(HttpServletRequest request, ModelMap model, List<UiProduct> productList,
        int pageSize)
    {
        final int totalCount = productList.size();
        final Pagination pagination = new Pagination(pageSize, totalCount, request,
            Constants.PAGE_NUMBER);
        final int totalPageNo = pagination.getTotalPageNo();
        final int pageNo = pagination
            .getActualPageNo(NumberUtils.toInt(request.getParameter(Constants.PAGE_NUMBER), 1));
        final int startIndex = pagination.getStartIndex(pageNo);
        final int endIndex = pagination.getEndIndex(pageNo);
        final List<Page> pageList = pagination.getPageList(pageNo);

        model.addAttribute(Constants.PRODUCT_LIST, productList.subList(startIndex, endIndex));
        model.addAttribute(Constants.START_INDEX, startIndex + 1);
        model.addAttribute(Constants.END_INDEX, endIndex);
        model.addAttribute(Constants.PAGE_NUMBER, pageNo);
        model.addAttribute(Constants.TOTAL_PAGE_NO, totalPageNo);
        model.addAttribute(Constants.TOTAL_COUNT, totalCount);
        model.addAttribute(Constants.PAGE_LIST, pageList);
        model.addAttribute(Constants.NEXT_PAGE, pagination.getNextPage(pageList, pageNo));
        model.addAttribute(Constants.PREV_PAGE, pagination.getPrevPage(pageList, pageNo));
    }

}

package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.AliceCategory;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.service.ProductSortType;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Page;
import com.alicesfavs.webapp.uimodel.UiProduct;
import com.alicesfavs.webapp.util.Pagination;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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

    private static final String PAGE_NUMBER = "pageNo";
    private static final String PRODUCT_LIST = "productList";
    private static final String START_INDEX = "startIndex";
    private static final String END_INDEX = "endIndex";
    private static final String TOTAL_PAGE_NO = "totalPageNo";
    private static final String TOTAL_COUNT = "totalCount";
    private static final String PAGE_LIST = "pageList";
    private static final String NEXT_PAGE = "nextPage";
    private static final String PREV_PAGE = "prevPage";
    private static final String SORT_BY = "sortBy";
    private static final String BREADCRUMB1 = "breadcrumb1";
    private static final String BREADCRUMB2 = "breadcrumb2";
    private static final String SUBTITLE = "subtitle";

    private static final String VIEW_HOME = "home";
    private static final String VIEW_SALE = "sale";

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private WebAppConfig webAppConfig;

    @RequestMapping(value = "/home2", method = RequestMethod.GET)
    public String home(ModelMap model, Device device)
    {
        final Map<String, List<UiProduct>> categoryProductMap = new LinkedHashMap<>();
        for (AliceCategory aliceCategory : siteManager.getAliceCategoryList())
        {
            final List<UiProduct> newSaleList = saleProductService.getNewSaleProducts(aliceCategory);
            categoryProductMap.put(aliceCategory.name.toLowerCase(), newSaleList);
        }
        model.addAttribute("saleCategoryProductMap", categoryProductMap);
        model.addAttribute("mobile", device.isMobile());

        return VIEW_HOME;
    }

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, @RequestParam(name = "category", required = false) String category,
        HttpServletRequest request, ModelMap model, Device device)
    {
        List<Site> siteList;
        AliceCategory aliceCategory = siteManager.getAliceCatgory(siteId);
        if (aliceCategory != null)
        {
            siteList = getAliceCategorySites(aliceCategory);
            model.addAttribute(BREADCRUMB1, aliceCategory.name);
            model.addAttribute(SUBTITLE, "Sale - " + aliceCategory.name);
        }
        else
        {
            final Site site = siteManager.getSiteByStringId(siteId);
            if (site == null)
            {
                throw new ResourceNotFoundException("Site '" + siteId + "' not found");
            }

            siteList = new ArrayList<>();
            siteList.add(site);
            aliceCategory = siteManager.getAliceCatgory(category);
            if (aliceCategory != null)
            {
                model.addAttribute(BREADCRUMB1, aliceCategory.name);
                model.addAttribute(BREADCRUMB2, site.displayName);
                model.addAttribute(SUBTITLE, "Sale - " + aliceCategory.name + " > " + site.displayName);
            }
            else
            {
                model.addAttribute(BREADCRUMB1, site.displayName);
                model.addAttribute(SUBTITLE, "Sale - " + site.displayName);
            }
        }

        final ProductSortType productSortType = ProductSortType.fromCode(request.getParameter(SORT_BY), ProductSortType.DATE);
        final List<UiProduct> productList = saleProductService.getSaleProducts(siteList, aliceCategory, productSortType);
        addProductAttributes(request, model, productList, webAppConfig.getSaleProductPageSize());
        model.addAttribute(SORT_BY, productSortType.getCode());
        model.addAttribute("mobile", device.isMobile());

        return VIEW_SALE;
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

    private void addProductAttributes(HttpServletRequest request, ModelMap model, List<UiProduct> productList,
        int pageSize)
    {
        final int totalCount = productList.size();
        final Pagination pagination = new Pagination(pageSize, totalCount, request,
            PAGE_NUMBER);
        final int totalPageNo = pagination.getTotalPageNo();
        final int pageNo = pagination.getActualPageNo(NumberUtils.toInt(request.getParameter(PAGE_NUMBER), 1));
        final int startIndex = pagination.getStartIndex(pageNo);
        final int endIndex = pagination.getEndIndex(pageNo);
        final List<Page> pageList = pagination.getPageList(pageNo);

        model.addAttribute(PRODUCT_LIST, productList.subList(startIndex, endIndex));
        model.addAttribute(START_INDEX, startIndex + 1);
        model.addAttribute(END_INDEX, endIndex);
        model.addAttribute(PAGE_NUMBER, pageNo);
        model.addAttribute(TOTAL_PAGE_NO, totalPageNo);
        model.addAttribute(TOTAL_COUNT, totalCount);
        model.addAttribute(PAGE_LIST, pageList);
        model.addAttribute(NEXT_PAGE, pagination.getNextPage(pageList, pageNo));
        model.addAttribute(PREV_PAGE, pagination.getPrevPage(pageList, pageNo));
    }

}

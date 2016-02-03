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
import javax.servlet.http.HttpServletResponse;
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
        model.addAttribute(Constants.PAGE_ID, "home");
        model.addAttribute("mobile", device.isMobile());

        return Constants.VIEW_HOME;
    }

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, @RequestParam(name = "category", required = false) String category,
        HttpServletRequest request, ModelMap model, Device device)
    {
        List<Site> siteList;
        AliceCategory aliceCategory = siteManager.getAliceCatgory(siteId);
        String pageId;
        if (aliceCategory != null)
        {
            siteList = getAliceCategorySites(aliceCategory);
            model.addAttribute(Constants.BREADCRUMB1, aliceCategory.name);
            model.addAttribute(Constants.SUBTITLE, "Sale - " + aliceCategory.name);
            pageId = "sale-category-all";
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
                model.addAttribute(Constants.BREADCRUMB1, aliceCategory.name);
                model.addAttribute(Constants.BREADCRUMB2, site.displayName);
                model.addAttribute(Constants.SUBTITLE, "Sale - " + aliceCategory.name + " > " + site.displayName);
                pageId = "sale-category-site";
            }
            else
            {
                model.addAttribute(Constants.BREADCRUMB1, site.displayName);
                model.addAttribute(Constants.SUBTITLE, "Sale - " + site.displayName);
                pageId = "sale-site";
            }
        }

        final ProductSortType productSortType = ProductSortType.fromCode(request.getParameter(Constants.SORT_BY), ProductSortType.DATE);
        final List<UiProduct> productList = saleProductService.getSaleProducts(siteList, aliceCategory, productSortType);
        addProductAttributes(request, model, productList, webAppConfig.getSaleProductPageSize());
        model.addAttribute(Constants.PAGE_ID, pageId);
        model.addAttribute(Constants.SORT_BY, productSortType.getCode());
        model.addAttribute("mobile", device.isMobile());

        return Constants.VIEW_SALE;
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
            Constants.PAGE_NUMBER);
        final int totalPageNo = pagination.getTotalPageNo();
        final int pageNo = pagination.getActualPageNo(NumberUtils.toInt(request.getParameter(Constants.PAGE_NUMBER), 1));
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

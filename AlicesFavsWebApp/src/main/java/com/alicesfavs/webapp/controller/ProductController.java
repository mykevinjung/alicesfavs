package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.config.WebAppConfig;
import com.alicesfavs.webapp.service.ProductSortType;
import com.alicesfavs.webapp.service.SaleProductService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Page;
import com.alicesfavs.webapp.util.ModelConverter;
import com.alicesfavs.webapp.util.Pagination;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    private static final String SITE = "site";

    private static final String VIEW_SALE = "sale";

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private WebAppConfig webAppConfig;

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, HttpServletRequest request, ModelMap model)
    {
        final Site site = siteManager.getSiteByStringId(siteId);
        if (site == null)
        {
            throw new ResourceNotFoundException("Site '" + siteId + "' not found");
        }

        final ProductSortType productSortType = ProductSortType.fromCode(request.getParameter(SORT_BY));
        final List<Product> productList = saleProductService.getSaleProducts(site, productSortType);
        addProductAttributes(request, model, site, productList, webAppConfig.getSaleProductPageSize());

        model.addAttribute(SITE, ModelConverter.convertSite(site));
        model.addAttribute(SORT_BY,
            productSortType == null ? ProductSortType.DATE.getCode() : productSortType.getCode());

        return VIEW_SALE;
    }

    private void addProductAttributes(HttpServletRequest request, ModelMap model, Site site, List<Product> productList,
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

        model.addAttribute(PRODUCT_LIST, ModelConverter.convertProductList(site, productList, startIndex, endIndex));
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

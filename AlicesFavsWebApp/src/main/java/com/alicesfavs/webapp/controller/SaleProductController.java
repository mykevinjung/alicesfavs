package com.alicesfavs.webapp.controller;

import com.alicesfavs.datamodel.Product;
import com.alicesfavs.datamodel.Site;
import com.alicesfavs.webapp.service.ProductSortType;
import com.alicesfavs.webapp.service.SaleService;
import com.alicesfavs.webapp.service.SiteManager;
import com.alicesfavs.webapp.uimodel.Page;
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
 * Created by kjung on 11/3/15.
 */

@Controller
public class SaleProductController extends ProductController
{

    private static final String VIEW_SALE = "sale";

    @Autowired
    private SiteManager siteManager;

    @Autowired
    private SaleService saleService;

    @RequestMapping(value = "/sale/{siteId}", method = RequestMethod.GET)
    public String sale(@PathVariable String siteId, HttpServletRequest request, ModelMap model)
    {
        final Site site = siteManager.getSite(siteId);
        if (site == null)
        {
            throw new ResourceNotFoundException("Site '" + siteId + "' not found");
        }

        final ProductSortType productSortType = ProductSortType.fromCode(request.getParameter("sortBy"));
        final List<Product> productList = saleService.getSaleProducts(site, productSortType);
        final int totalCount = productList.size();
        final int totalPageNo = getTotalPageNo(totalCount);
        final int pageNo = getActualPageNo(NumberUtils.toInt(request.getParameter("pageNo"), 1), totalPageNo);
        final int startIndex = (pageNo - 1) * getPageSize();
        final int endIndex = Math.min(pageNo * getPageSize(), totalCount);
        final List<Page> pageList = getPageList(request, totalPageNo, pageNo);

        model.addAttribute("site", site);
        model.addAttribute("productList", productList.subList(startIndex, endIndex));
        model.addAttribute("startIndex", startIndex + 1);
        model.addAttribute("endIndex", endIndex);
        model.addAttribute("pageNo", pageNo);
        model.addAttribute("totalPageNo", totalPageNo);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pageList", pageList);
        model.addAttribute("nextPage", getNextPage(pageList, pageNo));
        model.addAttribute("prevPage", getPrevPage(pageList, pageNo));
        model.addAttribute("sortBy", productSortType == null ? ProductSortType.DATE.getCode() : productSortType.getCode());

        return VIEW_SALE;
    }

}

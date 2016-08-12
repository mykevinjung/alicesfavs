<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
    <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

    <div class="row category-page">

        <div class="large-12 columns">
            <div class="breadcrumb-row">
                <div class="left">
                    <h3 class="breadcrumb" itemscope="breadcrumb"><a href="/">Sale</a><span>/</span>SEARCH RESULTS FOR "${searchText}"</h3>    </div><!-- .left -->

                <c:if test="${totalCount > 0}">
                    <div class="right">
                        <p class="woocommerce-result-count">
                            Showing ${startIndex}&ndash;${endIndex} of ${totalCount} results</p>
                    </div><!-- .right -->
                </c:if>
            </div><!-- .breadcrumb-row -->
        </div><!-- .large-12 breadcrumb -->

        <c:choose>
            <c:when test="${empty productList}">
                <div class="large-12 columns">
                    <p class="woocommerce-info">No products were found matching your selection.</p>
                </div>
            </c:when>
            <c:otherwise>
        <div class="large-12 columns">
            <div class="row">
                <div class="large-12 columns">
                    <ul class="products small-block-grid-2 large-block-grid-4">
                        <c:forEach items="${productList}" var="product" varStatus="loop">
                            <li class="product-small  grid1 grid-normal" sale-start-date="${product.saleStartDate}">
                                <div class="inner-wrap">
                                    <a <c:if test="${mobile != true}">target="_blank"</c:if> href="/redirect/product?siteId=${product.siteStringId}&id=${product.id}&pageId=${pageId}&pageNo=${pageNo}&category=${product.aliceCategory}&position=${loop.index+1}">
                                        <div class="product-image">
                                            <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image aligncenter" alt="${product.name}" /></div>
                                        </div><!-- end product-image -->
                                    </a>
                                    <div class="info style-grid1">
                                        <div class="text-center">
                                            <h5 class="category">
                                                <a href="/sale/brand/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                            <div class="tx-div small"></div>
                                            <a item-id="${product.id}" <c:if test="${mobile != true}">target="_blank"</c:if> href="/redirect/product?siteId=${product.siteStringId}&id=${product.id}&pageId=${pageId}&pageNo=${pageNo}&category=${product.aliceCategory}&position=${loop.index+1}"><p class="name">${product.name}</p></a>

                                            <del><span class="amount">${product.wasPriceWithCurrency}</span></del> <span class="price"><ins><span class="amount">${product.priceWithCurrency}</span></ins></span>

                                        </div><!-- text-center -->

                                        <div class="clear"></div>	</div><!-- end info -->
                                </div> <!-- .inner-wrap -->
                            </li><!-- li.product-small -->
                        </c:forEach>
                    </ul>
                </div>
            </div><!-- .large-12 -->
            </c:otherwise>
        </c:choose>

            <!-- PAGINATION -->
            <nav class="woocommerce-pagination">
                <div class="large-12 columns">
                    <div class="pagination-centered">
                        <ul class='page-numbers'>
                            <c:if test="${prevPage != null}"><li><a class="next page-numbers" href="${prevPage.pageUrl}"><span class="icon-angle-left"></span></a></li></c:if>
                        <c:forEach items="${pageList}" var="page">
                            <c:choose>
                                <c:when test="${pageNo == page.pageNo}"><li><span class='page-numbers current'>${page.pageNo}</span></li></c:when>
                                <c:otherwise><li><a class='page-numbers' href='${page.pageUrl}'>${page.pageNo}</a></li></c:otherwise>
                            </c:choose>
                        </c:forEach>
                            <c:if test="${nextPage != null}"><li><a class="next page-numbers" href="${nextPage.pageUrl}"><span class="icon-angle-right"></span></a></li></c:if>
                        </ul>
                    </div><!--  end pagination container -->
                </div><!-- end large-12 -->
            </nav>
            <!-- end PAGINATION -->


        </div><!-- .large-12 -->


    </div><!-- end row -->

    <div class="row"><div class="large-12 column"><div class="cat-footer"><hr/></div></div></div>

</div><!-- #main-content -->

<jsp:include page="footer.jsp" />
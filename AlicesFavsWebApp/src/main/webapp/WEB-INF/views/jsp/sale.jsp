<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
    <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

    <div class="row category-page">

        <div class="large-12 columns">
            <div class="breadcrumb-row">
                <div class="left">
                    <h3 class="breadcrumb" itemscope="breadcrumb"><a href="">Sale</a><span>/</span>${site.displayName}</h3>    </div><!-- .left -->

                <div class="right">
                    <p class="woocommerce-result-count">
                        Showing ${startIndex}&ndash;${endIndex} of ${totalCount} results</p>
                    <form class="woocommerce-ordering custom" method="get" action="">
                        <div class="select-wrapper">
                            <select name="sortBy" class="orderby" onchange="this.form.submit()">
                                <option value="date" <c:if test="${sortBy == 'date'}">selected='selected'</c:if>>Sort by newness</option>
                                <option value="amount" <c:if test="${sortBy == 'amount'}">selected='selected'</c:if>>Sort by discount $</option>
                                <option value="percentage" <c:if test="${sortBy == 'percentage'}">selected='selected'</c:if>>Sort by discount %</option>
                            </select>
                        </div>
                    </form>
                </div><!-- .right -->
            </div><!-- .breadcrumb-row -->
        </div><!-- .large-12 breadcrumb -->



        <div class="large-12 columns">



            <div class="row">
                <div class="large-12 columns">
                    <ul class="products small-block-grid-2 large-block-grid-4">



                        <c:forEach items="${productList}" var="product">

                        <li class="product-small  grid1 grid-normal">
                            <div class="inner-wrap">
                                <a <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}">
                                    <div class="product-image">
                                        <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image" alt="${product.name}" /></div>


                                    </div><!-- end product-image -->
                                </a>

                                <div class="info style-grid1">


                                    <div class="text-center">
                                        <h5 class="category">
                                            <a href="/sale/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                        <div class="tx-div small"></div>
                                        <a <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}"><p class="name">${product.name}</p></a>


                                        <span class="price"><del><span class="amount">${product.extractedWasPrice}</span></del> <ins><span class="amount">${product.extractedPrice}</span></ins></span>

                                    </div><!-- text-center -->

                                    <div class="clear"></div>	</div><!-- end info -->


                            </div> <!-- .inner-wrap -->
                        </li><!-- li.product-small -->

                        </c:forEach>
                    </ul>
                </div>
            </div><!-- .large-12 -->

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

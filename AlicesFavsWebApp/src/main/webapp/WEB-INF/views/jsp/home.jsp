<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
    <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

    <div class="row category-page">

        <c:forEach items="${saleCategoryProductMap}" var="saleCategoryProductEntry">
            <div class="large-12 columns">
                <div class="breadcrumb-row">
                    <div class="left">
                        <h3 class="breadcrumb" itemscope="breadcrumb"><a href="">Sale</a><span>/</span>${saleCategoryProductEntry.key}</h3>
                    </div><!-- .left -->
                </div><!-- .breadcrumb-row -->
            </div><!-- .large-12 breadcrumb -->

            <div class="large-12 columns">
                <ul class="products small-block-grid-2 large-block-grid-4">
                    <c:forEach items="${saleCategoryProductEntry.value}" var="product">
                        <li class="product-small  grid1 grid-normal">
                            <div class="inner-wrap">
                                <a <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}">
                                    <div class="product-image">
                                        <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image aligncenter" alt="${product.name}" /></div>
                                    </div><!-- end product-image -->
                                </a>
                                <div class="info style-grid1">
                                    <div class="text-center">
                                        <h5 class="category">
                                            <a href="/sale/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                        <div class="tx-div small"></div>
                                        <a item-id="${product.itemId}" <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}"><p class="name">${product.name}</p></a>
                                        <del><span class="amount">${product.wasPriceWithCurrency}</span></del> <span class="price"><ins><span class="amount">${product.priceWithCurrency}</span></ins></span>
                                    </div><!-- text-center -->
                                    <div class="clear"></div>	</div><!-- end info -->
                            </div> <!-- .inner-wrap -->
                        </li><!-- li.product-small -->
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>

        <div class="large-12 columns">
            <div class="breadcrumb-row">
                <div class="left">
                    <h3 class="breadcrumb" itemscope="breadcrumb">New Arrivals</h3>
                </div><!-- .left -->
            </div><!-- .breadcrumb-row -->
        </div><!-- .large-12 breadcrumb -->

        <div class="large-12 columns">
            <ul class="products small-block-grid-2 large-block-grid-4">
                <c:forEach items="${newProductList}" var="product">
                    <li class="product-small  grid1 grid-normal">
                        <div class="inner-wrap">
                            <a <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}">
                                <div class="product-image">
                                    <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image aligncenter" alt="${product.name}" /></div>
                                </div><!-- end product-image -->
                            </a>
                            <div class="info style-grid1">
                                <div class="text-center">
                                    <h5 class="category">
                                        <a href="/new-arrivals/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                    <div class="tx-div small"></div>
                                    <a <c:if test="${mobile != true}">target="_blank"</c:if> href="${product.url}"><p class="name">${product.name}</p></a>
                                    <span class="price"><del><span class="amount">${product.wasPriceWithCurrency}</span></del> <ins><span class="amount">${product.priceWithCurrency}</span></ins></span>
                                </div><!-- text-center -->
                                <div class="clear"></div>	</div><!-- end info -->
                        </div> <!-- .inner-wrap -->
                    </li><!-- li.product-small -->
                </c:forEach>
            </ul>
        </div>

    </div><!-- end row -->

    <div class="row"><div class="large-12 column"><div class="cat-footer"><hr/></div></div></div>

</div><!-- #main-content -->

<jsp:include page="footer.jsp" />

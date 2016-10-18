<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
    <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

    <div class="cat-header">
        <div class="ux-slider-wrapper relative">
            <div class="ux-slider iosSlider  slider-style-normal  slider-nav-dark slider-nav-inside slider-nav-circle js-flickity"
                 data-flickity-options='{
            "cellAlign": "center",
            "imagesLoaded": true,
            "lazyLoad": 1,
            "freeScroll": false,
            "wrapAround": true,
            "autoPlay": 5000,
            "prevNextButtons": true,
            "contain" : true,
            "percentPosition": true,
            "pageDots": true,
            "selectedAttraction" : 0.1,
            "friction": 0.6,
            "rightToLeft": false,
            "draggable": true        }'
                 style=""
            >
                <div id="banner_1" class="ux_banner dark    "  style="height:400px; " data-height="400px" role="banner">
                    <div class="banner-bg "  style="background-image:url('/resources/images/banner-red-bag-high-heeled-shoes.png'); ">
                        <img src="/resources/images/banner-red-bag-high-heeled-shoes.png"  alt="" style="visibility:hidden;" /></div>
                    <div class="row" >
                        <div class="inner center text-center "  style="width:80%;">
                            <div class="inner-wrap animated flipInX" style=" ">
                                <h1>ALL SALES IN ONE PLACE</h1>
                                <div class="tx-div medium"></div>
                                <h2 class="alt-font">Updated Everyday!</h2>
                            </div>
                        </div>
                    </div>
                </div><!-- end .ux_banner -->
                <div id="banner_2" class="ux_banner dark "  style="height:400px; " data-height="400px" role="banner">
                    <a target="_blank" href="http://bananarepublic.gap.com/browse/category.do?cid=1014739">
                        <div class="banner-bg " style="background-image:url('/resources/images/white.png'); ">
                            <img src="/resources/images/white.png"  alt="" style="visibility:hidden;" /></div>
                        <div class="row" >
                            <div class="inner right center text-center "  style="width:80%;">
                                <div class="inner-wrap animated fadeInLeft">
                                    <h3 style="color: white;">Until 10/16</h3>
                                    <h1 style="color: white;">ENJOY 25% OFF</h1>
                                    <h3 style="color: white;">With Code <strong>EVERYONE</strong></h3>
                                    <div class="tx-div medium" style="background-color: white;"></div>
                                    <h3 style="color: white;">kate spade</h3>
                                </div>
                            </div>
                        </div>
                    </a>
                </div><!-- end .ux_banner -->

                <c:if test="${not empty newSaleThisWeek}">
                    <div id="banner_3" class="ux_banner dark    "  style="height:400px; " data-height="400px" role="banner">
                        <div class="banner-bg "  style="background-image:url('/resources/images/leaf-fall.png'); ">
                            <img src="/resources/images/leaf-fall.png"  alt="" style="visibility:hidden;" /></div>
                        <div class="row" >
                            <div class="inner right center text-right "  style="width:100%;">
                                <div class="inner-wrap animated fadeInLeft">
                                    <h2 class="animated fadeInLeft">Updates This Week</h2>
                                    <div class="tx-div medium"></div>
                                    <c:forEach items="${newSaleThisWeek}" var="saleSummary" varStatus="loop">
                                        <h3 class="animated fadeInLeft"><a href="${saleSummary.brandSaleUrl}" style="color: inherit;">${saleSummary.saleCountThisWeek} items from ${saleSummary.siteName}</a></h3>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </div><!-- end .ux_banner-->
                </c:if>

            </div>
            <div class="ux-loading dark"></div>
        </div><!-- .ux-slider-wrapper -->
    </div>

    <div class="row category-page">

        <c:forEach items="${saleCategoryProductMap}" var="saleCategoryProductEntry">
            <div class="large-12 columns">
                <div class="breadcrumb-row">
                    <div class="left">
                        <h3 class="breadcrumb clearfix" itemscope="breadcrumb"><a href="/">Sale</a><span>/</span>${saleCategoryProductEntry.key}<span> </span><a href="/sale/${saleCategoryProductEntry.key}" target="">> MORE</a></h3>
                    </div><!-- .left -->
                </div><!-- .breadcrumb-row -->
            </div><!-- .large-12 breadcrumb -->

            <div class="large-12 columns">
                <ul class="products small-block-grid-2 large-block-grid-4">
                    <c:forEach items="${saleCategoryProductEntry.value}" var="product" varStatus="loop">
                        <li class="product-small  grid1 grid-normal">
                            <div class="inner-wrap" sale-start-date="${product.saleStartDate}">
                                <a <c:if test="${mobile != true}">target="_blank"</c:if> href="/redirect/product?siteId=${product.siteStringId}&id=${product.id}&pageId=${pageId}&category=${product.aliceCategory}&position=${loop.index+1}">
                                    <div class="product-image">
                                        <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image aligncenter" alt="${product.name}" <c:if test="${not empty product.imageOnErrorUrl}">onerror="this.onerror=null;this.src='${product.imageOnErrorUrl}';"</c:if>/></div>
                                    </div><!-- end product-image -->
                                </a>
                                <div class="info style-grid1">
                                    <div class="text-center">
                                        <h5 class="category">
                                            <a href="/sale/brand/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                        <div class="tx-div small"></div>
                                        <a item-id="${product.id}" <c:if test="${mobile != true}">target="_blank"</c:if> href="/redirect/product?siteId=${product.siteStringId}&id=${product.id}&pageId=${pageId}&category=${product.aliceCategory}&position=${loop.index+1}"><p class="name">${product.name}</p></a>
                                        <del><span class="amount">${product.wasPriceWithCurrency}</span></del> <span class="price"><ins><span class="amount">${product.priceWithCurrency}</span></ins></span>
                                    </div><!-- text-center -->
                                    <div class="clear"></div>	</div><!-- end info -->
                            </div> <!-- .inner-wrap -->
                        </li><!-- li.product-small -->
                    </c:forEach>
                </ul>
            </div>
        </c:forEach>

    </div><!-- end row -->

    <div class="row"><div class="large-12 column"><div class="cat-footer"><hr/></div></div></div>

</div><!-- #main-content -->

<jsp:include page="footer.jsp" />

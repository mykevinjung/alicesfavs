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
            "autoPlay": 6000,
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
                <div id="banner_1753316751" class="ux_banner dark    "  style="height:300px; " data-height="300px" role="banner">
                    <div class="banner-bg "  style="background-image:url('http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/slide3.jpg'); "><img src="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/slide3.jpg"  alt="" style="visibility:hidden;" /></div>
                    <div class="row" >
                        <div class="inner center text-center "  style="width:80%;">
                            <div class="inner-wrap animated flipInX" style=" ">
                                <h3 class="alt-font">An amazing...</h3>
                                <h1>CUSTOM CATEGORY SLIDER</h1>
                                <div class="tx-div medium"></div>
                            </div>
                        </div>
                    </div>
                </div><!-- end .ux_banner -->

                <div id="banner_1704027142" class="ux_banner dark    "  style="height:300px; " data-height="300px" role="banner">
                    <div class="banner-bg "  style="background-image:url('http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/slide2.jpg'); "><img src="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/slide2.jpg"  alt="" style="visibility:hidden;" /></div>
                    <div class="row" >
                        <div class="inner right center text-center "  style="width:40%;">
                            <div class="inner-wrap animated fadeInLeft" style=" ">
                                <h3 class="alt-font">Easy to customize...</h3>
                                <div class="tx-div medium"></div>
                                <h1 class="animated fadeInLeft">THIS TEXT IS</h1>
                                <h2 class="animated fadeInRight">EASY TO CHANGE</h2>
                                <div class="tx-div medium"></div>
                            </div>
                        </div>
                    </div>
                </div><!-- end .ux_banner -->

                <div id="banner_255853670" class="ux_banner dark    "  style="height:300px; " data-height="300px" role="banner">
                    <div class="banner-bg "  style="background-image:url('http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/for_men.jpg'); "><img src="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2013/08/for_men.jpg"  alt="" style="visibility:hidden;" /></div>
                    <div class="banner-effect effect-snow"></div>
                    <div class="row" >
                        <div class="inner center text-center "  style="width:80%;">
                            <div class="inner-wrap animated flipInX" style=" ">
                                <h3 class="alt-font">Banner with CSS Snow effect...</h3>
                                <h1 class="h-large">BIG FALL SALE</h1>
                            </div>
                        </div>
                    </div>
                </div><!-- end .ux_banner -->

            </div>
            <div class="ux-loading dark"></div>
        </div><!-- .ux-slider-wrapper -->

    </div>




    <div class="row category-page">

        <div class="large-12 columns">
            <div class="breadcrumb-row">
                <div class="left">
                    <h3 class="breadcrumb" itemscope="breadcrumb"><a href="">New Arrivals</a><span>/</span>${categoryName}</h3>    </div><!-- .left -->

                <div class="right">
                    <p class="woocommerce-result-count">
                        Showing ${startIndex}&ndash;${endIndex} of ${totalCount} results</p>
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
                                <a target="_blank" href="${product.url}">
                                    <div class="product-image">
                                        <div class="front-image"><img width="247" height="300" src="${product.imageUrl}" class="attachment-shop_catalog wp-post-image" alt="${product.name}" /></div>


                                    </div><!-- end product-image -->
                                </a>

                                <div class="info style-grid1">


                                    <div class="text-center">
                                        <h5 class="category">
                                            <a href="/new-arrivals/brand/${product.siteStringId}" rel="tag">${product.siteName}</a>          </h5>
                                        <div class="tx-div small"></div>
                                        <a target="_blank" href="${product.url}"><p class="name">${product.name}</p></a>


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

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
                    <h3 class="breadcrumb" itemscope="breadcrumb">${site.displayName}</h3>    </div><!-- .left -->

                <div class="right">
                    <p class="woocommerce-result-count">
                        Showing ${startIndex}&ndash;${endIndex} of ${productTotalCount} results</p>
                    <form class="woocommerce-ordering custom" method="get">
                        <div class="select-wrapper"><select name="orderby" class="orderby">
                            <option value="menu_order"  selected='selected'>Default sorting</option><option value="popularity" >Sort by popularity</option><option value="rating" >Sort by average rating</option><option value="date" >Sort by newness</option><option value="price" >Sort by price: low to high</option><option value="price-desc" >Sort by price: high to low</option>	</select></div>
                        <input type="hidden" name="no_sidebar" value="" /></form>
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
                                <a target="_blank" href="${product.productExtract.url}">
                                    <div class="product-image">
                                        <div class="front-image"><img width="247" height="300" src="${product.productExtract.imageUrl}" class="attachment-shop_catalog wp-post-image" alt="${product.productExtract.name}" /></div>


                                    </div><!-- end product-image -->
                                </a>

                                <div class="info style-grid1">


                                    <div class="text-center">
                                        <h5 class="category">
                                            <a href="/sale/${site.stringId}" rel="tag">${site.displayName}</a>          </h5>
                                        <div class="tx-div small"></div>
                                        <a target="_blank" href="${product.productExtract.url}"><p class="name">${product.productExtract.name}</p></a>


                                        <span class="price"><del><span class="amount">${product.productExtract.wasPrice}</span></del> <ins><span class="amount">${product.productExtract.price}</span></ins></span>

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
                            <li><span class='page-numbers current'>1</span></li>
                            <li><a class='page-numbers' href='#'>2</a></li>
                            <li><a class='page-numbers' href='#'>3</a></li>
                            <li><a class="next page-numbers" href="#"><span class="icon-angle-right"></span></a></li>
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

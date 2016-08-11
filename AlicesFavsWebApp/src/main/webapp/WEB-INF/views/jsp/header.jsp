<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lte IE 9 ]><html class="ie lt-ie9" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en-US"> <!--<![endif]-->
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <link rel="profile" href="http://gmpg.org/xfn/11" />

  <script type="text/javascript">document.documentElement.className = document.documentElement.className + ' yes-js js_active js'</script>
  <link rel="shortcut icon" href="/resources/images/favicon.png" type="image/x-icon" />

  <!-- Retina/iOS favicon -->
  <link rel="apple-touch-icon-precomposed" href="/resources/images/logo_touch.png" />
  <title><c:if test="${not empty subtitle}">${subtitle} | </c:if><c:choose><c:when test="${not empty title}">${title}</c:when><c:otherwise>Alice's Favs - All sales from the favorite brands</c:otherwise></c:choose></title>

  <link rel="canonical" href="http://www.alicesfavs.com${canonicalLink}">
  <c:if test="${not empty metaDescription}"><meta name="description" content="${metaDescription}" /></c:if>
  <link rel='stylesheet' id='flatsome-css-minified-css'  href='/resources/css/flatsome.min.css' type='text/css' media='all' />
  <link rel='stylesheet' id='flatsome-style-css'  href='/resources/css/style.css' type='text/css' media='all' />
  <link rel='stylesheet' id='flatsome-googlefonts-css'  href='//fonts.googleapis.com/css?family=Dancing+Script%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900&#038;subset=latin&#038;ver=4.3.1' type='text/css' media='all' />
  <script type='text/javascript' src='/resources/js/jquery/jquery.js'></script>
  <!-- Custom CSS Codes --><style type="text/css"> .top-bar-nav a.nav-top-link,body,p,#top-bar,.cart-inner .nav-dropdown,.nav-dropdown{font-family:Lato,helvetica,arial,sans-serif!important;}.header-nav a.nav-top-link, a.cart-link, .mobile-sidebar a{font-family:Lato,helvetica,arial,sans-serif!important;}h1,h2,h3,h4,h5,h6{font-family:Lato,helvetica,arial,sans-serif!important;}.alt-font{font-family:Dancing Script,Georgia,serif!important;} #masthead{ height:100px;}#logo a img{ max-height:70px} #masthead #logo{width:210px;}#masthead #logo a{max-width:210px} #masthead.stuck.move_down{height:70px;}.wide-nav.move_down{top:70px;}#masthead.stuck.move_down #logo a img{ max-height:60px } ul.header-nav li a {font-size:80%}body{background-color:#595959; } #masthead{background-color:#fff; ;} .slider-nav-reveal .flickity-prev-next-button, #main-content{background-color:#ffffff!important} .wide-nav {background-color:#eee} #top-bar{background-color:#627f9a } .alt-button.primary,.callout.style3 .inner .inner-text,.add-to-cart-grid .cart-icon strong,.tagcloud a,.navigation-paging a, .navigation-image a ,ul.page-numbers a, ul.page-numbers li > span,#masthead .mobile-menu a,.alt-button, #logo a, li.mini-cart .cart-icon strong,.widget_product_tag_cloud a, .widget_tag_cloud a,.post-date,#masthead .mobile-menu a.mobile-menu a,.checkout-group h3,.order-review h3 {color:#627f9a;}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .ux-box.ux-text-badge:hover .ux-box-text, .ux-box.ux-text-overlay .ux-box-image,.ux-header-element a:hover,.featured-table.ux_price_table .title,.scroll-to-bullets a strong,.scroll-to-bullets a.active,.scroll-to-bullets a:hover,.tabbed-content.pos_pills ul.tabs li.active a,.ux_hotspot,ul.page-numbers li > span,.label-new.menu-item a:after,.add-to-cart-grid .cart-icon strong:hover,.text-box-primary, .navigation-paging a:hover, .navigation-image a:hover ,.next-prev-nav .prod-dropdown > a:hover,ul.page-numbers a:hover,.widget_product_tag_cloud a:hover,.widget_tag_cloud a:hover,.custom-cart-count,.iosSlider .sliderNav a:hover span, li.mini-cart.active .cart-icon strong,.product-image .quick-view, .product-image .product-bg, #submit, button, #submit, button, .button, input[type="submit"],li.mini-cart.active .cart-icon strong,.post-item:hover .post-date,.blog_shortcode_item:hover .post-date,.column-slider .sliderNav a:hover,.ux_banner {background-color:#627f9a}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .ux-header-element a:hover,.featured-table.ux_price_table,.text-bordered-primary,.callout.style3 .inner,ul.page-numbers li > span,.add-to-cart-grid .cart-icon strong, .add-to-cart-grid .cart-icon-handle,.add-to-cart-grid.loading .cart-icon strong,.navigation-paging a, .navigation-image a ,ul.page-numbers a ,ul.page-numbers a:hover,.post.sticky,.widget_product_tag_cloud a, .widget_tag_cloud a,.next-prev-nav .prod-dropdown > a:hover,.iosSlider .sliderNav a:hover span,.column-slider .sliderNav a:hover,.woocommerce .order-review, .woocommerce-checkout form.login,.button, button, li.mini-cart .cart-icon strong,li.mini-cart .cart-icon .cart-icon-handle,.post-date{border-color:#627f9a;}.ux-loading{border-left-color:#627f9a;}.primary.alt-button:hover,.button.alt-button:hover{background-color:#627f9a!important}.flickity-prev-next-button:hover svg, .flickity-prev-next-button:hover .arrow, .featured-box:hover svg, .featured-img svg:hover{fill:#627f9a!important;}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .featured-box:hover .featured-img-circle svg{fill:#FFF!important;}.featured-box:hover .featured-img-circle{background-color:#627f9a!important; border-color:#627f9a!important;} .star-rating:before, .woocommerce-page .star-rating:before, .star-rating span:before{color:#d26e4b}.secondary.alt-button,li.menu-sale a{color:#d26e4b!important}.secondary-bg.button.alt-button.success:hover,.label-sale.menu-item a:after,.mini-cart:hover .custom-cart-count,.callout .inner,.button.secondary,.button.checkout,#submit.secondary, button.secondary, .button.secondary, input[type="submit"].secondary{background-color:#d26e4b}.button.secondary,.button.secondary{border-color:#d26e4b;}.secondary.alt-button:hover{color:#FFF!important;background-color:#d26e4b!important}ul.page-numbers li > span{color:#FFF;} .callout.style3 .inner.success-bg .inner-text,.woocommerce-message{color:#7a9c59!important}.success-bg,.woocommerce-message:before,.woocommerce-message:after{color:#FFF!important; background-color:#7a9c59}.label-popular.menu-item a:after,.add-to-cart-grid.loading .cart-icon strong,.add-to-cart-grid.added .cart-icon strong{background-color:#7a9c59;border-color:#7a9c59;}.add-to-cart-grid.loading .cart-icon .cart-icon-handle,.add-to-cart-grid.added .cart-icon .cart-icon-handle{border-color:#7a9c59}.product-gallery .scrollbarBlock2,.product-gallery .scrollbarBlock1{display:none!important}.label-new.menu-item > a:after{content:"New";}.label-hot.menu-item > a:after{content:"Hot";}.label-sale.menu-item > a:after{content:"Sale";}.label-popular.menu-item > a:after{content:"Popular";}.featured_item_image{max-height:250px}.callout .inner.callout-new-bg{background-color:#1e73be!important;}.callout.style3 .inner.callout-new-bg{background-color:none!important;border-color:#1e73be!important} iframe[name='google_conversion_frame'] {display:none;}.ninja-forms-required-items{display:none}.cat-header .iosSlider{margin-bottom:0}element.style {display:none;}.absolute-footer .menu-item-3111{display:none!important}a.not{display:block;-webkit-box-shadow:0px 0px 5px 0px rgba(50, 50, 50, 0.29);-moz-box-shadow: 0px 0px 5px 0px rgba(50, 50, 50, 0.29);box-shadow:0px 0px 5px 0px rgba(50, 50, 50, 0.29);-webkit-transition:opacity 0.3s;transition:opacity 0.3s;}a.not:hover{opacity:1;}</style></head>

<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-77565519-1', 'auto');
  ga('send', 'pageview');

</script>

<body class="home page page-id-17 page-parent page-template page-template-page-blank page-template-page-blank-php antialiased sticky_header breadcrumb-normal full-width">

<div class="home-intro"> </div>
<div id="wrapper">
  <div class="header-wrapper before-sticky">
    <div id="top-bar">
      <div class="row">
        <div class="large-12 columns">
          <!-- left text -->
          <div class="left-text left">
            <div class="html"><b>All sales from the favorite brands</b></div><!-- .html -->
          </div>
          <!-- top bar right -->
          <div class="right-text right">

            <ul id="menu-top-bar-menu" class="top-bar-nav">
              <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/about-us" class="nav-top-link">About us</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/contact-us" class="nav-top-link">Contact us</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/disclaimer" class="nav-top-link">Disclaimer</a></li>
              <li class="html-block">
                <div class="html-block-inner">
                  <div class="social-icons size-small">
                    <a href="http://www.facebook.com/sharer.php?u=http://www.alicesfavs.com/" onclick="window.open(this.href,this.title,'width=500,height=500,top=300px,left=300px');  return false;"  rel="nofollow" target="_blank" class="icon icon_facebook tip-top" title="Share on Facebook"><span class="icon-facebook"></span></a>
                  </div>
                </div>
              </li>
            </ul>
          </div><!-- top bar right -->

        </div><!-- .large-12 columns -->
      </div><!-- .row -->
    </div><!-- .#top-bar -->
    <header id="masthead" class="site-header" role="banner">
      <div class="row">
        <div class="large-12 columns header-container">
          <div class="mobile-menu show-for-small">
            <a href="#jPanelMenu" class="off-canvas-overlay" data-pos="left" data-color="light"><span class="icon-menu"></span></a>
          </div><!-- end mobile menu -->


          <div id="logo" class="logo-left">
              <a href="/" title="Alice's Favs" rel="home">
                <img src="/resources/images/logo1.png" class="header_logo " alt="Alice's Favs"/>
              </a>
          </div><!-- .logo -->

          <div class="left-links">
            <ul id="site-navigation" class="header-nav">

              <li class="search-dropdown">
                <a href="#" class="nav-top-link icon-search" onClick="return false;"></a>
                <div class="nav-dropdown">
                  <div class="row yith-search-premium collapse search-wrapper ">
                    <form role="search" method="get" class="yith-search-premium" action="/sale/search">
                      <div class="large-10 small-10 columns">
                        <input type="search" value="" name="searchText"  class="yith-s"
                               data-append-top placeholder="Search for products" data-loader-icon="" data-min-chars="3" />
                      </div><!-- input -->
                      <div class="large-2 small-2 columns">
                        <button type="submit" id="yith-searchsubmit" class="button secondary postfix"><i class="icon-search"></i></button>
                      </div><!-- button -->
                    </form>
                  </div><!-- row -->
                </div><!-- .nav-dropdown -->
              </li><!-- .search-dropdown -->

              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="/sale/clothing" class="nav-top-link">Clothing</a>
                <div class=nav-dropdown><ul>
                  <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-object-page"><a href="/sale/clothing" style="color:black"><b>All</b></a></li>
                  <c:forEach items="${clothing}" var="site">
                    <li class="<c:if test="${site.newSite}">label-new</c:if> menu-item-type-custom menu-item-object-custom menu-item menu-item-object-page"><a href="/sale/clothing/${site.stringId}">${site.displayName}</a></li>
                  </c:forEach>
                </ul></div>
              </li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="/sale/shoes" class="nav-top-link">Shoes</a>
                <div class=nav-dropdown><ul>
                  <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-object-page"><a href="/sale/shoes" style="color:black"><b>All</b></a></li>
                  <c:forEach items="${shoes}" var="site">
                    <li class="<c:if test="${site.newSite}">label-new</c:if> menu-item-type-custom menu-item-object-custom menu-item menu-item-object-page"><a href="/sale/shoes/${site.stringId}">${site.displayName}</a></li>
                  </c:forEach>
                </ul></div>
              </li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="/sale/bags" class="nav-top-link">Bags</a>
                <div class=nav-dropdown><ul>
                  <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-object-page"><a href="/sale/bags" style="color:black"><b>All</b></a></li>
                  <c:forEach items="${bags}" var="site">
                    <li class="<c:if test="${site.newSite}">label-new</c:if> menu-item-type-custom menu-item-object-custom menu-item menu-item-object-page"><a href="/sale/bags/${site.stringId}">${site.displayName}</a></li>
                  </c:forEach>
                </ul></div>
              </li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="/sale/accessories" class="nav-top-link">Accessories</a>
                <div class=nav-dropdown><ul>
                  <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-object-page"><a href="/sale/accessories" style="color:black"><b>All</b></a></li>
                  <c:forEach items="${accessories}" var="site">
                    <li class="<c:if test="${site.newSite}">label-new</c:if> menu-item-type-custom menu-item-object-custom menu-item menu-item-object-page"><a href="/sale/accessories/${site.stringId}">${site.displayName}</a></li>
                  </c:forEach>
                </ul></div>
              </li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="/sale/home" class="nav-top-link">Home</a>
                <div class=nav-dropdown><ul>
                  <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-object-page"><a href="/sale/home" style="color:black"><b>All</b></a></li>
                  <c:forEach items="${home}" var="site">
                    <li class="<c:if test="${site.newSite}">label-new</c:if> menu-item-type-custom menu-item-object-custom menu-item menu-item-object-page"><a href="/sale/home/${site.stringId}">${site.displayName}</a></li>
                  </c:forEach>
                </ul></div>
              </li>
            </ul>
          </div><!-- .left-links -->

        </div><!-- .large-12 -->
      </div><!-- .row -->


    </header><!-- .header -->

  </div><!-- .header-wrapper -->
</div>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<!--[if lte IE 9 ]><html class="ie lt-ie9" lang="en-US"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!--><html lang="en-US"> <!--<![endif]-->
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
  <link rel="profile" href="http://gmpg.org/xfn/11" />

  <script type="text/javascript">document.documentElement.className = document.documentElement.className + ' yes-js js_active js'</script>
  <link rel="shortcut icon" href="/resources/images/favicon.png" />

  <!-- Retina/iOS favicon -->
  <link rel="apple-touch-icon-precomposed" href="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/themes/flatsome/apple-touch-icon-precomposed.png" />
  <title>Alice's Favs</title>
  <style>
    .wishlist_table .add_to_cart, a.add_to_wishlist.button.alt { border-radius: 16px; -moz-border-radius: 16px; -webkit-border-radius: 16px; }			</style>
  <script type="text/javascript">
    var yith_wcwl_plugin_ajax_web_url = 'http://flatsome.uxthemes.com/wp-admin/admin-ajax.php';
  </script>
  <link rel="alternate" type="application/rss+xml" title="Flatsome &raquo; Feed" href="http://flatsome.uxthemes.com/feed/" />
  <link rel="alternate" type="application/rss+xml" title="Flatsome &raquo; Comments Feed" href="http://flatsome.uxthemes.com/comments/feed/" />
  <link rel="alternate" type="application/rss+xml" title="Flatsome &raquo; Homepage Comments Feed" href="http://flatsome.uxthemes.com/homepage/feed/" />
  <script type="text/javascript">
    window._wpemojiSettings = {"baseUrl":"http:\/\/s.w.org\/images\/core\/emoji\/72x72\/","ext":".png","source":{"concatemoji":"http:\/\/flatsome.uxthemes.com\/wp-includes\/js\/wp-emoji-release.min.js?ver=4.3.1"}};
    !function(a,b,c){function d(a){var c=b.createElement("canvas"),d=c.getContext&&c.getContext("2d");return d&&d.fillText?(d.textBaseline="top",d.font="600 32px Arial","flag"===a?(d.fillText(String.fromCharCode(55356,56812,55356,56807),0,0),c.toDataURL().length>3e3):(d.fillText(String.fromCharCode(55357,56835),0,0),0!==d.getImageData(16,16,1,1).data[0])):!1}function e(a){var c=b.createElement("script");c.src=a,c.type="text/javascript",b.getElementsByTagName("head")[0].appendChild(c)}var f,g;c.supports={simple:d("simple"),flag:d("flag")},c.DOMReady=!1,c.readyCallback=function(){c.DOMReady=!0},c.supports.simple&&c.supports.flag||(g=function(){c.readyCallback()},b.addEventListener?(b.addEventListener("DOMContentLoaded",g,!1),a.addEventListener("load",g,!1)):(a.attachEvent("onload",g),b.attachEvent("onreadystatechange",function(){"complete"===b.readyState&&c.readyCallback()})),f=c.source||{},f.concatemoji?e(f.concatemoji):f.wpemoji&&f.twemoji&&(e(f.twemoji),e(f.wpemoji)))}(window,document,window._wpemojiSettings);
  </script>
  <style type="text/css">
    img.wp-smiley,
    img.emoji {
      display: inline !important;
      border: none !important;
      box-shadow: none !important;
      height: 1em !important;
      width: 1em !important;
      margin: 0 .07em !important;
      vertical-align: -0.1em !important;
      background: none !important;
      padding: 0 !important;
    }
  </style>
  <link rel='stylesheet' id='deveraux-switch-css-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/css/switcherv2.css?v=1.1&#038;ver=4.3.1' type='text/css' media='all' />
  <link rel='stylesheet' id='deveraux-switch-demo-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/demo.css?v=1.1&#038;ver=4.3.1' type='text/css' media='all' />
  <link rel='stylesheet' id='jquery-selectBox-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/yith-woocommerce-wishlist/assets/css/jquery.selectBox.css?ver=4.3.1' type='text/css' media='all' />
  <link rel='stylesheet' id='flatsome-css-minified-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/themes/flatsome/css/flatsome.min.css?ver=2.7.6' type='text/css' media='all' />
  <link rel='stylesheet' id='flatsome-style-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/themes/flatsome/style.css?ver=2.7.6' type='text/css' media='all' />
  <link rel='stylesheet' id='flatsome-googlefonts-css'  href='//fonts.googleapis.com/css?family=Dancing+Script%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900%7CLato%3A300%2C400%2C700%2C900&#038;subset=latin&#038;ver=4.3.1' type='text/css' media='all' />
  <script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/jquery/jquery.js?ver=1.11.3'></script>
  <script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/jquery/jquery-migrate.min.js?ver=1.2.1'></script>
  <link rel="EditURI" type="application/rsd+xml" title="RSD" href="http://flatsome.uxthemes.com/xmlrpc.php?rsd" />
  <link rel="wlwmanifest" type="application/wlwmanifest+xml" href="http://flatsome.uxthemes.com/wp-includes/wlwmanifest.xml" />
  <link rel='canonical' href='http://flatsome.uxthemes.com/' />
  <link rel='shortlink' href='http://flatsome.uxthemes.com/' />
  <script>
    jQuery( document ).ready(function($) {
      setTimeout(function(){
        $('a.nav-top-login, a.checkout.wc-forward, .nav-top-not-logged-in').click(function(e){
          if(top != self) {
            window.open($(this).attr('href'), '_top');
            e.preventDefault();
          }
        });

        $('.checkout-button').click(function(e){
          if(top != self) {
            window.open('http://flatsome.uxthemes.com/checkout/', '_top');
            e.preventDefault();
          }
        });
      }, 1000);
    });
  </script>
  <!--[if lt IE 9]><link rel="stylesheet" type="text/css" href="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/themes/flatsome/css/ie8.css"><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><script>var head = document.getElementsByTagName('head')[0],style = document.createElement('style');style.type = 'text/css';style.styleSheet.cssText = ':before,:after{content:none !important';head.appendChild(style);setTimeout(function(){head.removeChild(style);}, 0);</script><![endif]-->	<style type="text/css">.recentcomments a{display:inline !important;padding:0 !important;margin:0 !important;}</style>
  <!-- Custom CSS Codes --><style type="text/css"> .top-bar-nav a.nav-top-link,body,p,#top-bar,.cart-inner .nav-dropdown,.nav-dropdown{font-family:Lato,helvetica,arial,sans-serif!important;}.header-nav a.nav-top-link, a.cart-link, .mobile-sidebar a{font-family:Lato,helvetica,arial,sans-serif!important;}h1,h2,h3,h4,h5,h6{font-family:Lato,helvetica,arial,sans-serif!important;}.alt-font{font-family:Dancing Script,Georgia,serif!important;} #masthead{ height:100px;}#logo a img{ max-height:70px} #masthead #logo{width:210px;}#masthead #logo a{max-width:210px} #masthead.stuck.move_down{height:70px;}.wide-nav.move_down{top:70px;}#masthead.stuck.move_down #logo a img{ max-height:60px } ul.header-nav li a {font-size:80%}body{background-color:#595959; background-image:url(""); } #masthead{background-color:#fff; ;} .slider-nav-reveal .flickity-prev-next-button, #main-content{background-color:#ffffff!important} .wide-nav {background-color:#eee} #top-bar{background-color:#627f9a } .alt-button.primary,.callout.style3 .inner .inner-text,.add-to-cart-grid .cart-icon strong,.tagcloud a,.navigation-paging a, .navigation-image a ,ul.page-numbers a, ul.page-numbers li > span,#masthead .mobile-menu a,.alt-button, #logo a, li.mini-cart .cart-icon strong,.widget_product_tag_cloud a, .widget_tag_cloud a,.post-date,#masthead .mobile-menu a.mobile-menu a,.checkout-group h3,.order-review h3 {color:#627f9a;}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .ux-box.ux-text-badge:hover .ux-box-text, .ux-box.ux-text-overlay .ux-box-image,.ux-header-element a:hover,.featured-table.ux_price_table .title,.scroll-to-bullets a strong,.scroll-to-bullets a.active,.scroll-to-bullets a:hover,.tabbed-content.pos_pills ul.tabs li.active a,.ux_hotspot,ul.page-numbers li > span,.label-new.menu-item a:after,.add-to-cart-grid .cart-icon strong:hover,.text-box-primary, .navigation-paging a:hover, .navigation-image a:hover ,.next-prev-nav .prod-dropdown > a:hover,ul.page-numbers a:hover,.widget_product_tag_cloud a:hover,.widget_tag_cloud a:hover,.custom-cart-count,.iosSlider .sliderNav a:hover span, li.mini-cart.active .cart-icon strong,.product-image .quick-view, .product-image .product-bg, #submit, button, #submit, button, .button, input[type="submit"],li.mini-cart.active .cart-icon strong,.post-item:hover .post-date,.blog_shortcode_item:hover .post-date,.column-slider .sliderNav a:hover,.ux_banner {background-color:#627f9a}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .ux-header-element a:hover,.featured-table.ux_price_table,.text-bordered-primary,.callout.style3 .inner,ul.page-numbers li > span,.add-to-cart-grid .cart-icon strong, .add-to-cart-grid .cart-icon-handle,.add-to-cart-grid.loading .cart-icon strong,.navigation-paging a, .navigation-image a ,ul.page-numbers a ,ul.page-numbers a:hover,.post.sticky,.widget_product_tag_cloud a, .widget_tag_cloud a,.next-prev-nav .prod-dropdown > a:hover,.iosSlider .sliderNav a:hover span,.column-slider .sliderNav a:hover,.woocommerce .order-review, .woocommerce-checkout form.login,.button, button, li.mini-cart .cart-icon strong,li.mini-cart .cart-icon .cart-icon-handle,.post-date{border-color:#627f9a;}.ux-loading{border-left-color:#627f9a;}.primary.alt-button:hover,.button.alt-button:hover{background-color:#627f9a!important}.flickity-prev-next-button:hover svg, .flickity-prev-next-button:hover .arrow, .featured-box:hover svg, .featured-img svg:hover{fill:#627f9a!important;}.slider-nav-circle .flickity-prev-next-button:hover svg, .slider-nav-circle .flickity-prev-next-button:hover .arrow, .featured-box:hover .featured-img-circle svg{fill:#FFF!important;}.featured-box:hover .featured-img-circle{background-color:#627f9a!important; border-color:#627f9a!important;} .star-rating:before, .woocommerce-page .star-rating:before, .star-rating span:before{color:#d26e4b}.secondary.alt-button,li.menu-sale a{color:#d26e4b!important}.secondary-bg.button.alt-button.success:hover,.label-sale.menu-item a:after,.mini-cart:hover .custom-cart-count,.callout .inner,.button.secondary,.button.checkout,#submit.secondary, button.secondary, .button.secondary, input[type="submit"].secondary{background-color:#d26e4b}.button.secondary,.button.secondary{border-color:#d26e4b;}.secondary.alt-button:hover{color:#FFF!important;background-color:#d26e4b!important}ul.page-numbers li > span{color:#FFF;} .callout.style3 .inner.success-bg .inner-text,.woocommerce-message{color:#7a9c59!important}.success-bg,.woocommerce-message:before,.woocommerce-message:after{color:#FFF!important; background-color:#7a9c59}.label-popular.menu-item a:after,.add-to-cart-grid.loading .cart-icon strong,.add-to-cart-grid.added .cart-icon strong{background-color:#7a9c59;border-color:#7a9c59;}.add-to-cart-grid.loading .cart-icon .cart-icon-handle,.add-to-cart-grid.added .cart-icon .cart-icon-handle{border-color:#7a9c59}.product-gallery .scrollbarBlock2,.product-gallery .scrollbarBlock1{display:none!important}.label-new.menu-item > a:after{content:"New";}.label-hot.menu-item > a:after{content:"Hot";}.label-sale.menu-item > a:after{content:"Sale";}.label-popular.menu-item > a:after{content:"Popular";}.featured_item_image{max-height:250px}.callout .inner.callout-new-bg{background-color:#1e73be!important;}.callout.style3 .inner.callout-new-bg{background-color:none!important;border-color:#1e73be!important} iframe[name='google_conversion_frame'] {display:none;}.ninja-forms-required-items{display:none}.cat-header .iosSlider{margin-bottom:0}element.style {display:none;}.absolute-footer .menu-item-3111{display:none!important}a.not{display:block;-webkit-box-shadow:0px 0px 5px 0px rgba(50, 50, 50, 0.29);-moz-box-shadow: 0px 0px 5px 0px rgba(50, 50, 50, 0.29);box-shadow:0px 0px 5px 0px rgba(50, 50, 50, 0.29);-webkit-transition:opacity 0.3s;transition:opacity 0.3s;}a.not:hover{opacity:1;}</style></head>

<body class="home page page-id-17 page-parent page-template page-template-page-blank page-template-page-blank-php antialiased sticky_header breadcrumb-normal full-width">

<div class="home-intro"> </div>
<div id="wrapper">
  <div class="header-wrapper before-sticky">
    <div id="top-bar">
      <div class="row">
        <div class="large-12 columns">
          <!-- left text -->
          <div class="left-text left">
            <div class="html"><b>EVERYTHING THAT WOMEN LOVE...</b></div><!-- .html -->
          </div>
          <!-- top bar right -->
          <div class="right-text right">

            <ul id="menu-top-bar-menu" class="top-bar-nav">
              <li id="menu-item-556" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-556"><a href="/about-us" class="nav-top-link">About us</a></li>
              <li id="menu-item-555" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-555"><a href="/contact-us" class="nav-top-link">Contact us</a></li>
              <li id="menu-item-3111" class="icon-envelop menu-item menu-item-type-custom menu-item-object-custom menu-item-3111"><a href="#newsletter-signup" class="nav-top-link">Newsletter Signup</a></li>



              <li class="html-block">
                <div class="html-block-inner">
                  <div class="social-icons size-small">
                    <a href="#" target="_blank"  rel="nofollow" class="icon icon_facebook tip-top" title="Follow us on Facebook"><span class="icon-facebook"></span></a>		<a href="#" target="_blank" rel="nofollow" class="icon icon_twitter tip-top" title="Follow us on Twitter"><span class="icon-twitter"></span></a>								<a href="#" target="_blank" rel="nofollow" class="icon icon_instagram tip-top" title="Follow us on Instagram"><span class="icon-instagram"></span></a>												     </div>


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
            <a href="http://www.alicesfavs.com" title="Alice's Favs" rel="home">
              <img src="/resources/images/logo_example.png" class="header_logo " alt="Alice's Favs"/>						</a>
          </div><!-- .logo -->

          <div class="left-links">
            <ul id="site-navigation" class="header-nav">

              <li class="search-dropdown">
                <a href="#" class="nav-top-link icon-search" onClick="return false;"></a>
                <div class="nav-dropdown">



                  <div class="row yith-search-premium collapse search-wrapper yith-ajaxsearchform-container yith-ajaxsearchform-container 1871850240_container">
                    <form role="search" method="get" class="yith-search-premium" id="yith-ajaxsearchform" action="http://flatsome.uxthemes.com/">
                      <div class="large-10 small-10 columns">
                        <input type="hidden" name="post_type" class="yit_wcas_post_type" id="yit_wcas_post_type" value="product" />
                        <input type="search"
                               value=""
                               name="s"
                               id="1871850240_yith-s"
                               class="yith-s"
                               data-append-top
                               placeholder="Search for products"
                               data-loader-icon=""
                               data-min-chars="3" />
                      </div><!-- input -->
                      <div class="large-2 small-2 columns">
                        <button type="submit" id="yith-searchsubmit" class="button secondary postfix"><i class="icon-search"></i></button>
                      </div><!-- button -->
                    </form>
                  </div><!-- row -->

                  <script type="text/javascript">
                    jQuery(function($){
                      if (jQuery().yithautocomplete) {
                        $('#1871850240_yith-s').yithautocomplete({
                          minChars: 3,
                          appendTo: '.1871850240_container',
                          serviceUrl: woocommerce_params.ajax_url + '?action=yith_ajax_search_products',
                          onSearchStart: function(){
                            $('.1871850240_container').append('<div class="ux-loading"></div>');
                          },
                          onSearchComplete: function(){
                            $('.1871850240_container .ux-loading').remove();

                          },
                          onSelect: function (suggestion) {
                            if( suggestion.id != -1 ) {
                              window.location.href = suggestion.url;
                            }
                          }
                        });

                      } else {
                        $('#1871850240_yith-s').autocomplete({
                          minChars: 3,
                          appendTo: '.1871850240_container',
                          serviceUrl: woocommerce_params.ajax_url + '?action=yith_ajax_search_products',
                          onSearchStart: function(){
                            $('.1871850240_container').append('<div class="ux-loading"></div>');
                          },
                          onSearchComplete: function(){
                            $('.1871850240_container .ux-loading').remove();

                          },
                          onSelect: function (suggestion) {
                            if( suggestion.id != -1 ) {
                              window.location.href = suggestion.url;
                            }
                          }
                        });

                      }
                    });
                  </script>
                </div><!-- .nav-dropdown -->
              </li><!-- .search-dropdown -->

              <li id="menu-item-504" class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-17 current_page_item current-menu-ancestor current-menu-parent current_page_parent current_page_ancestor menu-item-has-children menu-parent-item menu-item-504"><a href="http://flatsome.uxthemes.com/" class="nav-top-link">Homepage</a>
                <div class=nav-dropdown><ul>
                  <li id="menu-item-1366" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-ancestor current-menu-parent menu-item-has-children menu-parent-item menu-item-1366"><a href="#">Homepages</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-506" class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-17 current_page_item menu-item-506"><a href="http://flatsome.uxthemes.com/">Long</a></li>
                      <li id="menu-item-505" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-505"><a href="http://flatsome.uxthemes.com/homepage/homepage-2-simple/">Simple</a></li>
                      <li id="menu-item-674" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-674"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?simple">Ultra simple</a></li>
                      <li id="menu-item-3109" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-3109"><a href="http://flatsome.uxthemes.com/homepage/homepage-grid-style-1/">Grid Style 1</a></li>
                      <li id="menu-item-2139" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2139"><a href="http://flatsome.uxthemes.com/homepage/homepage-7-grid-style-2/">Grid Style 2</a></li>
                      <li id="menu-item-2569" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-2569"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-grid-style/?add_to_cart_grid">Grid Style 3</a></li>
                      <li id="menu-item-761" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-761"><a href="http://flatsome.uxthemes.com/homepage/homepage-5-parallax-banner/">Parallax style</a></li>
                      <li id="menu-item-2018" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2018"><a href="http://flatsome.uxthemes.com/homepage/homepage-6-video-blank-header/?dark_header">Transparent header</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-1367" class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-has-children menu-parent-item menu-item-1367"><a href="http://flatsome.uxthemes.com">Header Examples</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-2016" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2016"><a href="?dark_header">Dark header</a></li>
                      <li id="menu-item-1368" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1368"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?header_center">Logo Center</a></li>
                      <li id="menu-item-1369" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1369"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?header_center_widenav">Wide Nav Logo Center</a></li>
                      <li id="menu-item-1370" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1370"><a href="http://flatsome.uxthemes.com/?header_widenav_light">Wide Nav Light</a></li>
                      <li id="menu-item-1371" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1371"><a href="http://flatsome.uxthemes.com?header_widenav_dark">Wide Nav Dark</a></li>
                      <li id="menu-item-2811" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2811"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-grid-style/?add_to_cart_grid&amp;catalog-mode&amp;social">Social Icons (cart replace)</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-2192" class="image-column menu-item menu-item-type-custom menu-item-object-custom menu-item-2192"><a href="http://flatsome.uxthemes.com/product-category/men/">Custom Image<img src="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2014/12/custom-image.jpg" alt=" "/></a></li>
                </ul></div>
              </li>
              <li id="menu-item-52" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-52"><a href="http://flatsome.uxthemes.com/shop/" class="nav-top-link">Shop</a>
                <div class=nav-dropdown><ul>
                  <li id="menu-item-438" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-438"><a href="http://flatsome.uxthemes.com/shop/">Categories</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-439" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-439"><a href="http://flatsome.uxthemes.com/product-category/women/">Sidebar &#8211; Left</a></li>
                      <li id="menu-item-667" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-667"><a href="http://flatsome.uxthemes.com/product-category/women/?right_sidebar">Sidebar &#8211; Right</a></li>
                      <li id="menu-item-7921" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-7921"><a href="http://flatsome.uxthemes.com/product-category/women/?no_sidebar">Off-canvas</a></li>
                      <li id="menu-item-440" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-440"><a href="http://flatsome.uxthemes.com/product-category/women/?no_sidebar">Full width</a></li>
                      <li id="menu-item-449" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-449"><a href="http://flatsome.uxthemes.com/product-category/women/">Top slider</a></li>
                      <li id="menu-item-445" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-445"><a href="http://flatsome.uxthemes.com/product-category/men/">Top banner</a></li>
                      <li id="menu-item-448" class="label-sale menu-item menu-item-type-custom menu-item-object-custom menu-item-448"><a href="http://flatsome.uxthemes.com/product-category/men/">Shop Men</a></li>
                      <li id="menu-item-1923" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1923"><a href="http://flatsome.uxthemes.com/shop/?dark_bg">Dark background</a></li>
                      <li id="menu-item-444" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-444"><a href="http://flatsome.uxthemes.com/product-category/men/?catalog-mode">Catalog Mode</a></li>
                      <li id="menu-item-2543" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2543"><a href="http://flatsome.uxthemes.com/product-category/women/?add_to_cart_grid">Cart Icon</a></li>
                      <li id="menu-item-7179" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-7179"><a href="http://flatsome.uxthemes.com/product-category/women/?add_to_cart_button">Cart Button</a></li>
                      <li id="menu-item-7180" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-7180"><a href="http://flatsome.uxthemes.com/product-category/women/?masonry_grid">Masonry Grid</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-441" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-441"><a href="http://uxthemes.wpengine.com/shop/">Product Page</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-442" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-442"><a href="http://flatsome.uxthemes.com/product/all-star-canvas-hi-converse/">With variations</a></li>
                      <li id="menu-item-446" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-446"><a href="http://flatsome.uxthemes.com/product/harissa-o-neck-sweat/?section_style">Section style</a></li>
                      <li id="menu-item-6703" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6703"><a href="http://flatsome.uxthemes.com/product/adelia-bag-nypd/#">Affiliate Product</a></li>
                      <li id="menu-item-607" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-607"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/">Tab style</a></li>
                      <li id="menu-item-3713" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3713"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?pills_tabs">Tabs Pill Style</a></li>
                      <li id="menu-item-2544" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-2544"><a href="http://flatsome.uxthemes.com/product/osaka-entry-tee-superdry/?vertical_tabs">Vertical tabs</a></li>
                      <li id="menu-item-447" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-447"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?right_product">Right sidebar</a></li>
                      <li id="menu-item-1113" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1113"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?left_product">Left sidebar</a></li>
                      <li id="menu-item-1922" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1922"><a href="http://flatsome.uxthemes.com/product/all-star-canvas-hi-converse/?dark_bg&amp;pills_tabs">Dark background</a></li>
                      <li id="menu-item-6679" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-6679"><a href="http://flatsome.uxthemes.com/product/varanise-cn-tee-hilfiger-denim/?image_zoom">Image Zoom</a></li>
                      <li id="menu-item-9138" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-9138"><a href="/product/wicked-ss-o-neck-selected-homme/">Featured Video</a></li>
                      <li id="menu-item-900" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-900"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?catalog-mode&amp;search">Catalog Mode</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-616" class="image-column menu-item menu-item-type-custom menu-item-object-custom menu-item-616"><a href="http://flatsome.uxthemes.com/product-category/women/">Image Column<img src="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/uploads/sites/2/2014/12/image-column1.jpg" alt=" "/></a></li>
                </ul></div>
              </li>
              <li id="menu-item-290" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-290"><a href="http://flatsome.uxthemes.com/shortcodes/" class="nav-top-link">Shortcodes</a>
                <div class=nav-dropdown><ul>
                  <li id="menu-item-632" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-632"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-overview/">Overview</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-2591" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2591"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-background/">Background</a></li>
                      <li id="menu-item-460" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-460"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-banners/">UX Banners</a></li>
                      <li id="menu-item-465" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-465"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-ux-sliders/">UX Sliders</a></li>
                      <li id="menu-item-2563" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2563"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-ux-banner-grid/">UX Banner grid</a></li>
                      <li id="menu-item-461" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-461"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-blog-posts/">Blog posts slider</a></li>
                      <li id="menu-item-647" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-647"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-team-members/">Team members</a></li>
                      <li id="menu-item-648" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-648"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-featured-boxes/">Featured boxes</a></li>
                      <li id="menu-item-789" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-789"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-testemonials/">Testimonials</a></li>
                      <li id="menu-item-5918" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-5918"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-price-table/">Price Tables</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-3098" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-3098"><a href="#"> More Shortcodes</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-672" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-672"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-message-box/">Message box</a></li>
                      <li id="menu-item-649" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-649"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-titles-dividers/">Titles / Dividers</a></li>
                      <li id="menu-item-634" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-634"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-buttons/">Buttons</a></li>
                      <li id="menu-item-455" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-455"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-tabs-accordian/">Tabs / Accordion</a></li>
                      <li id="menu-item-646" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-646"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-sharefollow-icons/">Share/follow icons</a></li>
                      <li id="menu-item-517" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-517"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-google-map/">Google map</a></li>
                      <li id="menu-item-2061" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2061"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-lightbox/">Lightbox</a></li>
                      <li id="menu-item-3096" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-3096"><a href="http://flatsome.uxthemes.com/shortcodes/featured-items-shortcode/">Portfolio Grid</a></li>
                      <li id="menu-item-5926" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-5926"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-scroll-to/">Scroll To</a></li>
                      <li id="menu-item-8859" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-8859"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-instagram-feed/">Instagram Feed</a></li>
                    </ul></div>
                  </li>
                  <li id="menu-item-633" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-633"><a href="#">Shop shortcodes</a>
                    <div class=nav-column-links><ul>
                      <li id="menu-item-662" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-662"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-sliders/">Product sliders</a></li>
                      <li id="menu-item-291" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-291"><a href="http://flatsome.uxthemes.com/shortcodes/pinterest-style/">Pinterest Style</a></li>
                      <li id="menu-item-657" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-657"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-products-by-skuid/">Products by SKU/ID</a></li>
                      <li id="menu-item-658" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-658"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-categories/">Product Categories</a></li>
                      <li id="menu-item-2545" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2545"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-flip/">Product Flip</a></li>
                      <li id="menu-item-659" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-659"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-recent-products/">Recent Products</a></li>
                      <li id="menu-item-660" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-660"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-featured-products/">Featured Products</a></li>
                      <li id="menu-item-661" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-661"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-lookbook/">Product lookbook</a></li>
                    </ul></div>
                  </li>
                </ul></div>
              </li>
              <li id="menu-item-528" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-528"><a href="http://flatsome.uxthemes.com/pages/" class="nav-top-link">Pages</a>
                <div class=nav-dropdown><ul>
                  <li id="menu-item-597" class="label-new menu-item menu-item-type-post_type menu-item-object-page menu-item-597"><a href="http://flatsome.uxthemes.com/features/">Theme Features</a></li>
                  <li id="menu-item-780" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-780"><a href="http://flatsome.uxthemes.com/featured-item-portfolio/">Portfolio</a></li>
                  <li id="menu-item-529" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-529"><a href="http://flatsome.uxthemes.com/pages/about-us/">About us</a></li>
                  <li id="menu-item-531" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-531"><a href="http://flatsome.uxthemes.com/blog/">Blog</a></li>
                  <li id="menu-item-3287" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3287"><a href="http://flatsome.uxthemes.com/blog/?parallax">Blog &#8211; Parallax effect</a></li>
                  <li id="menu-item-2028" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2028"><a href="http://flatsome.uxthemes.com/blog/?list_style">Blog &#8211; List style</a></li>
                  <li id="menu-item-2029" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2029"><a href="http://flatsome.uxthemes.com/blog/?pinterest_style">Blog &#8211; Pinterest style</a></li>
                  <li id="menu-item-5947" class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-5947"><a href="http://flatsome.uxthemes.com/2013/12/30/just-a-cool-blog-post-with-a-gallery/?blog_featured_image">Blog &#8211; Big Featured Image</a></li>
                  <li id="menu-item-536" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-536"><a href="http://flatsome.uxthemes.com/pages/contact-us/">Contact us</a></li>
                  <li id="menu-item-549" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-549"><a href="http://flatsome.uxthemes.com/pages/faq/">FAQs</a></li>
                  <li id="menu-item-690" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-690"><a href="http://flatsome.uxthemes.com/wishlist/">Wishlist</a></li>
                  <li id="menu-item-666" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-666"><a href="http://flatsome.uxthemes.com/pages/our-services/">Our Services</a></li>
                </ul></div>
              </li>
              <li id="menu-item-705" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-705"><a href="http://themeforest.net/item/flat-responsive-woocommerce-theme/5484319" class="nav-top-link">Buy</a></li>



            </ul>
          </div><!-- .left-links -->


          <div class="right-links">
            <ul  class="header-nav">




              <li class="account-dropdown hide-for-small">
                <a href="http://flatsome.uxthemes.com/my-account/" class="nav-top-link nav-top-not-logged-in">Login</a>

              </li>

              <!-- Show mini cart if Woocommerce is activated -->

              <li class="mini-cart ">
                <div class="cart-inner">
                  <a href="http://flatsome.uxthemes.com/cart/" class="cart-link">
                    <strong class="cart-name hide-for-small">Cart</strong>
                    <span class="cart-price hide-for-small">/ <span class="amount">&#36;0.00</span></span>
                    <!-- cart icon -->
                    <div class="cart-icon">

                      <strong>0</strong>
                      <span class="cart-icon-handle"></span>
                    </div><!-- end cart icon -->
                  </a>
                  <div id="mini-cart-content" class="nav-dropdown">
                    <div class="nav-dropdown-inner">
                      <!-- Add a spinner before cart ajax content is loaded -->
                      <p class="empty">No products in the cart.</p>
                    </div><!-- nav-dropdown-innner -->
                  </div><!-- .nav-dropdown -->
                </div><!-- .cart-inner -->
              </li><!-- .mini-cart -->

            </ul><!-- .header-nav -->
          </div><!-- .right-links -->
        </div><!-- .large-12 -->
      </div><!-- .row -->


    </header><!-- .header -->

  </div><!-- .header-wrapper -->

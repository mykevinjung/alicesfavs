<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="footer-wrapper" role="contentinfo">


  <div id="newsletter-signup" class="mfp-hide mfp-content-inner lightbox-white" style="max-width:600px;padding:0px">
    <div id="banner_846657111" class="ux_banner light    "  style="height:450px; " data-height="450px" role="banner">
      <div class="banner-bg "  style="background-image:url('http://uxthemes.wpengine.com/wp-content/uploads/sites/2/2013/08/slide1.jpg'); "><img src="http://uxthemes.wpengine.com/wp-content/uploads/sites/2/2013/08/slide1.jpg"  alt="" style="visibility:hidden;" /></div>
      <div class="row" >
        <div class="inner left top text-left "  style="width:40%;">
          <div class="inner-wrap animated fadeInLeft" style=" ">
            <h3>Signup for our Newsletter!</h3>
            <div class="tx-div medium"></div>
            <p>
            <div id="ninja_forms_form_5_cont" class="ninja-forms-cont">
              <div id="ninja_forms_form_5_wrap" class="ninja-forms-form-wrap">
                <div id="ninja_forms_form_5_response_msg" style="" class="ninja-forms-response-msg "></div>	<form id="ninja_forms_form_5" enctype="multipart/form-data" method="post" name="" action="http://flatsome.uxthemes.com/wp-admin/admin-ajax.php?action=ninja_forms_ajax_submit" class="ninja-forms-form">

                <input type="hidden" id="_wpnonce" name="_wpnonce" value="d8bab7c0b2" /><input type="hidden" name="_wp_http_referer" value="/" />	<input type="hidden" name="_ninja_forms_display_submit" value="1">
                <input type="hidden" name="_form_id"  id="_form_id" value="5">
                <div class="hp-wrap">
                  <label>If you are a human and are seeing this field, please leave it blank.			<input type="text" value="" name="_tULMW">
                    <input type="hidden" value="_tULMW" name="_hp_name">
                  </label>
                </div>
                <div id="ninja_forms_form_5_all_fields_wrap" class="ninja-forms-all-fields-wrap">
                  <div class="ninja-forms-required-items">Fields marked with a * are required</div>
                  <div class="ninja-forms-field  nf-desc" id="ninja_forms_field_16_div_wrap" style="" rel="16"><p>Enter your email and we'll send you a coupon with <strong>10% off</strong> your next order. Add any text here</p>
                  </div>
                  <div class="field-wrap text-wrap label-inside"  id="ninja_forms_field_17_div_wrap" data-visible="1">
                    <input type="hidden" id="ninja_forms_field_17_type" value="text">
                    <input id="ninja_forms_field_17" data-mask="" data-input-limit="" data-input-limit-type="" data-input-limit-msg="" name="ninja_forms_field_17" type="text" placeholder="" class="ninja-forms-field  ninja-forms-req email " value="Your E-mail *" rel="17"   />
                    <input type="hidden" id="ninja_forms_field_17_label_hidden" value="Your E-mail *">
                    <div id="ninja_forms_field_17_error" style="display:none;" class="ninja-forms-field-error">
                    </div>
                  </div>
                  <div class="field-wrap submit-wrap label-above button-wrap secondary-wrap"  id="ninja_forms_field_18_div_wrap" data-visible="1">
                    <input type="hidden" id="ninja_forms_field_18_type" value="submit">
                    <div id="nf_submit_5">
                      <input type="submit" name="_ninja_forms_field_18" class="ninja-forms-field  button secondary" id="ninja_forms_field_18" value="Sign up!" rel="18" >
                    </div>
                    <div id="nf_processing_5" style="display:none;">
                      <input type="submit" name="_ninja_forms_field_18" class="ninja-forms-field  button secondary" id="ninja_forms_field_18" value="Processing" rel="18" disabled>
                    </div>
                    <div id="ninja_forms_field_18_error" style="display:none;" class="ninja-forms-field-error">
                    </div>
                  </div>
                </div>
              </form>
              </div>
            </div>

          </div>
        </div>
      </div>
    </div><!-- end .ux_banner -->

  </div><!-- Lightbox-newsletter-signup -->

  <script>
    jQuery(document).ready(function($) {


      $('a[href="#newsletter-signup"]').click(function(e){
        // Close openend lightboxes
        var delay = 0;

        if($.magnificPopup.open){
          $.magnificPopup.close();
          delay = 300;
        }

        // Start lightbox
        setTimeout(function(){
          $.magnificPopup.open({
            midClick: true,
            removalDelay: 300,
            items: {
              src: '#newsletter-signup',
              type: 'inline'
            }
          });
        }, delay);

        e.preventDefault();
      });
    });
  </script>

  <div class="absolute-footer dark" style="background-color:#5b5b5b">
    <div class="row">
      <div class="large-12 columns">
        <div class="left">
          <div class="menu-top-nav-and-footer-nav-container"><ul id="menu-top-nav-and-footer-nav-1" class="footer-nav"><li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-556"><a href="/about-us">About us</a></li>
            <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-555"><a href="/contact-us">Contact us</a></li>
            <li class="icon-envelop menu-item menu-item-type-custom menu-item-object-custom menu-item-3111"><a href="#newsletter-signup">Newsletter Signup</a></li>
          </ul></div>
          <div class="copyright-footer">Copyright 2015 © <strong>Alice's Favs, Inc.</strong></div>
        </div><!-- .left -->
      </div><!-- .large-12 -->
    </div><!-- .row-->
  </div><!-- .absolute-footer -->
</footer><!-- .footer-wrapper -->
</div><!-- #wrapper -->

<!-- back to top -->
<a href="#top" id="top-link" class="animated fadeInUp"><span class="icon-angle-up"></span></a>

<script>;(function ($){
  $('form.login').after('Try our demo account -  Username: <b>testuser</b> , Password: <b>testuser</b><br><br>');
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
            (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
          m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
  ga('create', 'UA-43580429-1', 'uxthemes.wpengine.com');
  ga('send', 'pageview');
}(jQuery));
</script>

<div id="sw-open" class="sw-open sw-open-right"><span class="sw-icon"></span></div>
<div id="sw-window" class="switcher switcher-right sw-hidden">
  <div class="sw-header">
    <div class="sw-title"></div>
    <div id="sw-close" class="sw-close"></div>
  </div>
  <div class="sw-main">
    <div class="sw-one-third">

      <span class="sw-label">Layout</span>
      <select name="switch-layout">
        <option value="full-width">Full width</option>
        <option value="boxed">Boxed</option>
        <option value="framed-layout boxed">Framed</option>
      </select>

      <span class="sw-label">Typography examples</span>
      <select name="switch-font">
        <option value="">Default (Lato)</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_6.css?1">Playfair Display (classic)</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_1.css?1">Montserrat / Muli</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_2.css?1">Abril Fatface / Open Sans</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_3.css?1">PT Sans / PT Serif</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_4.css?1">Oswald / Muli</option>
        <option value="http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/css/font_5.css?1">Jalla One / Roboto</option>
      </select>

    </div>

    <div class="sw-one-third">
      <span class="sw-label">Style examples</span>
      <div class="sw-section">
        <div class="switch-stylesheet sw-box-color-2 sw-box-v1" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_1.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v5" data-color="dark" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_5.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v2" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_2.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v3" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_3.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v4" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_4.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v6" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_6.css?1"><div></div><div></div></div>
        <div class="switch-stylesheet sw-box-color-2 sw-box-v7" data-stylesheet="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/css/style_7.css?1"><div></div><div></div></div>


      </div>




      <span class="sw-label">Background patterns</span>
      <div class="sw-section">
        <div class="switch-pattern sw-box-20" style="background: #FFF"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-2.png" style="background: #612168"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-3.png" style="background: #26394c"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-4.png" style="background: #612168"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-5.png" style="background: #122738"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-6.png" style="background: #111"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-7.png" style="background: #e8d2b0"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-8.png" style="background: #111"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-9.png" style="background: #eee"></div>
        <div class="switch-pattern sw-box-20" data-pattern="http://flatsome.uxthemes.com/wp-content/plugins/switcher/example/img/pattern-10.png" style="background: #ddd"></div>


      </div>
      <!--<span class="sw-label">Background image</span>
      <div class="sw-section">
          <div class="switch-background sw-box-20" style="background: #FFF"></div>
          <div class="switch-background sw-box-20" data-background="/example/img/bg-1.png" style="background: url('http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/example/img/bg-1-mini.png');"> </div>
      </div> -->
    </div>
    <div class="sw-one-third sw-last">
      <p>Note: This is just a demo to showcase whats possible with the theme. Every color, font, backrounds etc can completely be customized in a Theme Option Panel</p>
      <br class="sw-clear">
      <span class="switch-reset sw-reset">Reset styles</span>
    </div>
  </div>
</div>

<!-- Mobile Popup -->
<div id="jPanelMenu" class="mfp-hide">
  <div class="mobile-sidebar">

    <ul class="mobile-main-menu">
      <li class="search">



        <div class="row yith-search-premium collapse search-wrapper yith-ajaxsearchform-container yith-ajaxsearchform-container 1270158836_container">
          <form role="search" method="get" class="yith-search-premium" id="yith-ajaxsearchform" action="http://flatsome.uxthemes.com/">
            <div class="large-10 small-10 columns">
              <input type="hidden" name="post_type" class="yit_wcas_post_type" id="yit_wcas_post_type" value="product" />
              <input type="search"
                     value=""
                     name="s"
                     id="1270158836_yith-s"
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
              $('#1270158836_yith-s').yithautocomplete({
                minChars: 3,
                appendTo: '.1270158836_container',
                serviceUrl: woocommerce_params.ajax_url + '?action=yith_ajax_search_products',
                onSearchStart: function(){
                  $('.1270158836_container').append('<div class="ux-loading"></div>');
                },
                onSearchComplete: function(){
                  $('.1270158836_container .ux-loading').remove();

                },
                onSelect: function (suggestion) {
                  if( suggestion.id != -1 ) {
                    window.location.href = suggestion.url;
                  }
                }
              });

            } else {
              $('#1270158836_yith-s').autocomplete({
                minChars: 3,
                appendTo: '.1270158836_container',
                serviceUrl: woocommerce_params.ajax_url + '?action=yith_ajax_search_products',
                onSearchStart: function(){
                  $('.1270158836_container').append('<div class="ux-loading"></div>');
                },
                onSearchComplete: function(){
                  $('.1270158836_container .ux-loading').remove();

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
      </li><!-- .search-dropdown -->

      <li class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-17 current_page_item current-menu-ancestor current-menu-parent current_page_parent current_page_ancestor menu-item-has-children menu-parent-item menu-item-504"><a href="http://flatsome.uxthemes.com/">Homepage</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-ancestor current-menu-parent menu-item-has-children menu-parent-item menu-item-1366"><a href="#">Homepages</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-17 current_page_item menu-item-506"><a href="http://flatsome.uxthemes.com/">Long</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-505"><a href="http://flatsome.uxthemes.com/homepage/homepage-2-simple/">Simple</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-674"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?simple">Ultra simple</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-3109"><a href="http://flatsome.uxthemes.com/homepage/homepage-grid-style-1/">Grid Style 1</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2139"><a href="http://flatsome.uxthemes.com/homepage/homepage-7-grid-style-2/">Grid Style 2</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-2569"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-grid-style/?add_to_cart_grid">Grid Style 3</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-761"><a href="http://flatsome.uxthemes.com/homepage/homepage-5-parallax-banner/">Parallax style</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2018"><a href="http://flatsome.uxthemes.com/homepage/homepage-6-video-blank-header/?dark_header">Transparent header</a></li>
            </ul>
          </li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom current-menu-item current_page_item menu-item-home menu-item-has-children menu-parent-item menu-item-1367"><a href="http://flatsome.uxthemes.com">Header Examples</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2016"><a href="?dark_header">Dark header</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1368"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?header_center">Logo Center</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1369"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-ultra-simple/?header_center_widenav">Wide Nav Logo Center</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1370"><a href="http://flatsome.uxthemes.com/?header_widenav_light">Wide Nav Light</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1371"><a href="http://flatsome.uxthemes.com?header_widenav_dark">Wide Nav Dark</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2811"><a href="http://flatsome.uxthemes.com/homepage/homepage-3-grid-style/?add_to_cart_grid&#038;catalog-mode&#038;social">Social Icons (cart replace)</a></li>
            </ul>
          </li>
          <li class="image-column menu-item menu-item-type-custom menu-item-object-custom menu-item-2192"><a href="http://flatsome.uxthemes.com/product-category/men/">Custom Image</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-52"><a href="http://flatsome.uxthemes.com/shop/">Shop</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-438"><a href="http://flatsome.uxthemes.com/shop/">Categories</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-439"><a href="http://flatsome.uxthemes.com/product-category/women/">Sidebar &#8211; Left</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-667"><a href="http://flatsome.uxthemes.com/product-category/women/?right_sidebar">Sidebar &#8211; Right</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-7921"><a href="http://flatsome.uxthemes.com/product-category/women/?no_sidebar">Off-canvas</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-440"><a href="http://flatsome.uxthemes.com/product-category/women/?no_sidebar">Full width</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-449"><a href="http://flatsome.uxthemes.com/product-category/women/">Top slider</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-445"><a href="http://flatsome.uxthemes.com/product-category/men/">Top banner</a></li>
              <li class="label-sale menu-item menu-item-type-custom menu-item-object-custom menu-item-448"><a href="http://flatsome.uxthemes.com/product-category/men/">Shop Men</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1923"><a href="http://flatsome.uxthemes.com/shop/?dark_bg">Dark background</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-444"><a href="http://flatsome.uxthemes.com/product-category/men/?catalog-mode">Catalog Mode</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2543"><a href="http://flatsome.uxthemes.com/product-category/women/?add_to_cart_grid">Cart Icon</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-7179"><a href="http://flatsome.uxthemes.com/product-category/women/?add_to_cart_button">Cart Button</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-7180"><a href="http://flatsome.uxthemes.com/product-category/women/?masonry_grid">Masonry Grid</a></li>
            </ul>
          </li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-441"><a href="http://uxthemes.wpengine.com/shop/">Product Page</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-442"><a href="http://flatsome.uxthemes.com/product/all-star-canvas-hi-converse/">With variations</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-446"><a href="http://flatsome.uxthemes.com/product/harissa-o-neck-sweat/?section_style">Section style</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-6703"><a href="http://flatsome.uxthemes.com/product/adelia-bag-nypd/#">Affiliate Product</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-607"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/">Tab style</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3713"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?pills_tabs">Tabs Pill Style</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-2544"><a href="http://flatsome.uxthemes.com/product/osaka-entry-tee-superdry/?vertical_tabs">Vertical tabs</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-447"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?right_product">Right sidebar</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1113"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?left_product">Left sidebar</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-1922"><a href="http://flatsome.uxthemes.com/product/all-star-canvas-hi-converse/?dark_bg&#038;pills_tabs">Dark background</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-6679"><a href="http://flatsome.uxthemes.com/product/varanise-cn-tee-hilfiger-denim/?image_zoom">Image Zoom</a></li>
              <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-9138"><a href="/product/wicked-ss-o-neck-selected-homme/">Featured Video</a></li>
              <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-900"><a href="http://flatsome.uxthemes.com/product/lucy-slim-jeans-noisy-may/?catalog-mode&#038;search">Catalog Mode</a></li>
            </ul>
          </li>
          <li class="image-column menu-item menu-item-type-custom menu-item-object-custom menu-item-616"><a href="http://flatsome.uxthemes.com/product-category/women/">Image Column</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-290"><a href="http://flatsome.uxthemes.com/shortcodes/">Shortcodes</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-632"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-overview/">Overview</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2591"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-background/">Background</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-460"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-banners/">UX Banners</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-465"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-ux-sliders/">UX Sliders</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2563"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-ux-banner-grid/">UX Banner grid</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-461"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-blog-posts/">Blog posts slider</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-647"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-team-members/">Team members</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-648"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-featured-boxes/">Featured boxes</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-789"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-testemonials/">Testimonials</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-5918"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-price-table/">Price Tables</a></li>
            </ul>
          </li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-3098"><a href="#"> More Shortcodes</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-672"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-message-box/">Message box</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-649"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-titles-dividers/">Titles / Dividers</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-634"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-buttons/">Buttons</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-455"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-tabs-accordian/">Tabs / Accordion</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-646"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-sharefollow-icons/">Share/follow icons</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-517"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-google-map/">Google map</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2061"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-lightbox/">Lightbox</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-3096"><a href="http://flatsome.uxthemes.com/shortcodes/featured-items-shortcode/">Portfolio Grid</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-5926"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-scroll-to/">Scroll To</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-8859"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-instagram-feed/">Instagram Feed</a></li>
            </ul>
          </li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-633"><a href="#">Shop shortcodes</a>
            <ul class="sub-menu">
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-662"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-sliders/">Product sliders</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-291"><a href="http://flatsome.uxthemes.com/shortcodes/pinterest-style/">Pinterest Style</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-657"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-products-by-skuid/">Products by SKU/ID</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-658"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-categories/">Product Categories</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-2545"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-flip/">Product Flip</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-659"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-recent-products/">Recent Products</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-660"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-featured-products/">Featured Products</a></li>
              <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-661"><a href="http://flatsome.uxthemes.com/shortcodes/shortcode-product-lookbook/">Product lookbook</a></li>
            </ul>
          </li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item menu-item-528"><a href="http://flatsome.uxthemes.com/pages/">Pages</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page menu-item-597"><a href="http://flatsome.uxthemes.com/features/">Theme Features</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-780"><a href="http://flatsome.uxthemes.com/featured-item-portfolio/">Portfolio</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-529"><a href="http://flatsome.uxthemes.com/pages/about-us/">About us</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-531"><a href="http://flatsome.uxthemes.com/blog/">Blog</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-3287"><a href="http://flatsome.uxthemes.com/blog/?parallax">Blog &#8211; Parallax effect</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2028"><a href="http://flatsome.uxthemes.com/blog/?list_style">Blog &#8211; List style</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-2029"><a href="http://flatsome.uxthemes.com/blog/?pinterest_style">Blog &#8211; Pinterest style</a></li>
          <li class="label-new menu-item menu-item-type-custom menu-item-object-custom menu-item-5947"><a href="http://flatsome.uxthemes.com/2013/12/30/just-a-cool-blog-post-with-a-gallery/?blog_featured_image">Blog &#8211; Big Featured Image</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-536"><a href="http://flatsome.uxthemes.com/pages/contact-us/">Contact us</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-549"><a href="http://flatsome.uxthemes.com/pages/faq/">FAQs</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-690"><a href="http://flatsome.uxthemes.com/wishlist/">Wishlist</a></li>
          <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-666"><a href="http://flatsome.uxthemes.com/pages/our-services/">Our Services</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-705"><a href="http://themeforest.net/item/flat-responsive-woocommerce-theme/5484319">Buy</a></li>


      <li class="menu-item menu-account-item menu-item-has-children">
        <a href="http://flatsome.uxthemes.com/my-account/">Login</a>

      </li>
    </ul>

    <ul class="top-bar-mob">
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-556"><a href="http://flatsome.uxthemes.com/pages/about-us/">About us</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-557"><a href="http://flatsome.uxthemes.com/blog/">Blog</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-555"><a href="http://flatsome.uxthemes.com/pages/contact-us/">Contact us</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-554"><a href="http://flatsome.uxthemes.com/pages/faq/">FAQ</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-793"><a href="http://flatsome.uxthemes.com/wishlist/">My Wishlist</a></li>
      <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-has-children menu-parent-item menu-item-698"><a href="#">English</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-699"><a href="#">German</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-700"><a href="#">Spanish</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-702"><a href="#">French</a></li>
          <li class="menu-item menu-item-type-custom menu-item-object-custom menu-item-701"><a href="#">This is just for demo</a></li>
        </ul>
      </li>
      <li class="icon-envelop menu-item menu-item-type-custom menu-item-object-custom menu-item-3111"><a href="#newsletter-signup">Newsletter Signup</a></li>


      <li class="html-block">

        <div class="social-icons size-small">
          <a href="#" target="_blank"  rel="nofollow" class="icon icon_facebook tip-top" title="Follow us on Facebook"><span class="icon-facebook"></span></a>		<a href="#" target="_blank" rel="nofollow" class="icon icon_twitter tip-top" title="Follow us on Twitter"><span class="icon-twitter"></span></a>								<a href="#" target="_blank" rel="nofollow" class="icon icon_instagram tip-top" title="Follow us on Instagram"><span class="icon-instagram"></span></a>												     </div>


      </li>

    </ul>


  </div><!-- inner -->
</div><!-- #mobile-menu -->

<link rel='stylesheet' id='ninja-forms-display-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/ninja-forms/css/ninja-forms-display.css?nf_ver=2.9.27&#038;ver=4.3.1' type='text/css' media='all' />
<link rel='stylesheet' id='jquery-qtip-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/ninja-forms/css/qtip.css?ver=4.3.1' type='text/css' media='all' />
<link rel='stylesheet' id='jquery-rating-css'  href='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/ninja-forms/css/jquery.rating.css?ver=4.3.1' type='text/css' media='all' />
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/js/dx-switcher.min.js'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/switcher/js/custom.js'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var wc_add_to_cart_params = {"ajax_url":"\/wp-admin\/admin-ajax.php","wc_ajax_url":"\/?wc-ajax=%%endpoint%%","i18n_view_cart":"View Cart","cart_url":"http:\/\/flatsome.uxthemes.com\/cart\/","is_cart":"","cart_redirect_after_add":"no"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/frontend/add-to-cart.min.js?ver=2.4.7'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/jquery-blockui/jquery.blockUI.min.js?ver=2.70'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var woocommerce_params = {"ajax_url":"\/wp-admin\/admin-ajax.php","wc_ajax_url":"\/?wc-ajax=%%endpoint%%"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/frontend/woocommerce.min.js?ver=2.4.7'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/jquery-cookie/jquery.cookie.min.js?ver=1.4.1'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var wc_cart_fragments_params = {"ajax_url":"\/wp-admin\/admin-ajax.php","wc_ajax_url":"\/?wc-ajax=%%endpoint%%","fragment_name":"wc_fragments"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/frontend/cart-fragments.min.js?ver=2.4.7'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var zerospam = {"key":"Lg(uH0^NTN6RuGSaswUU#yJUGP^LGuRlBXO(awP*%6K8KDN#AP*DZ3qGqV5YcVFo"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/zero-spam/js/zerospam.js?ver=2.0.0'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/yith-woocommerce-ajax-search/assets/js/yith-autocomplete.min.js?ver=1.2.7'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/yith-woocommerce-wishlist/assets/js/jquery.selectBox.min.js?ver=4.3.1'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var yith_wcwl_l10n = {"ajax_url":"http:\/\/flatsome.uxthemes.com\/wp-admin\/admin-ajax.php","redirect_to_cart":"no","multi_wishlist":"","hide_add_button":"1","is_user_logged_in":"","ajax_loader_url":"http:\/\/flatsome.uxthemes.com\/wp-content\/plugins\/yith-woocommerce-wishlist\/assets\/images\/ajax-loader.gif","remove_from_wishlist_after_add_to_cart":"no","labels":{"cookie_disabled":"We are sorry, but this feature is available only if cookies are enabled on your browser.","added_to_cart_message":"<div class=\"woocommerce-message\">Product correctly added to cart<\/div>"},"actions":{"add_to_wishlist_action":"add_to_wishlist","remove_from_wishlist_action":"remove_from_wishlist","move_to_another_wishlist_action":"move_to_another_wishlsit","reload_wishlist_and_adding_elem_action":"reload_wishlist_and_adding_elem"}};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/yith-woocommerce-wishlist/assets/js/jquery.yith-wcwl.js?ver=2.0'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var ajaxURL = {"ajaxurl":"http:\/\/flatsome.uxthemes.com\/wp-admin\/admin-ajax.php"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/themes/flatsome/js/flatsome.min.js?ver=2.7.6'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/comment-reply.min.js?ver=4.3.1'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/yith-woocommerce-ajax-search/assets/js/devbridge-jquery-autocomplete.min.js?ver=1.2.7'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var wc_add_to_cart_variation_params = {"i18n_no_matching_variations_text":"Sorry, no products matched your selection. Please choose a different combination.","i18n_unavailable_text":"Sorry, this product is unavailable. Please choose a different combination."};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/woocommerce/assets/js/frontend/add-to-cart-variation.min.js?ver=2.4.7'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/jquery/jquery.form.min.js?ver=3.37.0'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/underscore.min.js?ver=1.6.0'></script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-includes/js/backbone.min.js?ver=1.1.2'></script>
<script type='text/javascript'>
  /* <![CDATA[ */
  var ninja_forms_settings = {"ajax_msg_format":"inline","password_mismatch":"Passwords do not match.","plugin_url":"http:\/\/flatsome.uxthemes.com\/wp-content\/plugins\/ninja-forms\/","datepicker_args":{"dateFormat":"mm\/dd\/yy"},"currency_symbol":"$","date_format":"mm\/dd\/yy"};
  var thousandsSeparator = ",";
  var decimalPoint = ".";
  var ninja_forms_form_5_settings = {"ajax":"1","hide_complete":"1","clear_complete":"1"};
  var ninja_forms_form_5_calc_settings = {"calc_value":"","calc_fields":[]};
  var ninja_forms_password_strength = {"empty":"Strength indicator","short":"Very weak","bad":"Weak","good":"Medium","strong":"Strong","mismatch":"Mismatch"};
  var thousandsSeparator = ",";
  var decimalPoint = ".";
  var ninja_forms_form_1_settings = {"ajax":"1","hide_complete":"1","clear_complete":"1"};
  var ninja_forms_form_1_calc_settings = {"calc_value":"","calc_fields":[]};
  var ninja_forms_password_strength = {"empty":"Strength indicator","short":"Very weak","bad":"Weak","good":"Medium","strong":"Strong","mismatch":"Mismatch"};
  /* ]]> */
</script>
<script type='text/javascript' src='http://41hmj38vkl98fqzebjp1112g.wpengine.netdna-cdn.com/wp-content/plugins/ninja-forms/js/min/ninja-forms-display.min.js?nf_ver=2.9.27&#038;ver=4.3.1'></script>

</body>
</html>

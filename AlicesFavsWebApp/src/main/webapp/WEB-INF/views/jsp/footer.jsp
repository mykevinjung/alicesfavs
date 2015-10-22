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
                <div id="ninja_forms_form_5_response_msg" style="" class="ninja-forms-response-msg "></div>	<form id="ninja_forms_form_5" enctype="multipart/form-data" method="post" name="" action="#" class="ninja-forms-form">

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
          <div class="copyright-footer">Copyright 2015 © <strong>Alice's Favs, Inc.</strong></div>
        </div><!-- .left -->
        <div class="right">
          <div class="menu-top-nav-and-footer-nav-container"><ul id="menu-top-nav-and-footer-nav-1" class="footer-nav">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/about-us">About us</a></li>
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/contact-us">Contact us</a></li>
          </ul></div>
        </div><!-- .right -->
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

<!-- Mobile Popup -->
<div id="jPanelMenu" class="mfp-hide">
  <div class="mobile-sidebar">

    <ul class="mobile-main-menu">
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Clothing</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/jcrew">Coming soon</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Shoes</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="#">Coming soon</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Accessories</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="#">Coming soon</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Home</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="#">Coming soon</a></li>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">New Arrivals</a>
        <ul class="sub-menu">
          <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="#">Coming soon</a></li>
        </ul>
      </li>
    </ul>

    <ul class="top-bar-mob">
      <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/about-us">About us</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/contact-us">Contact us</a></li>
      <li class="icon-envelop menu-item menu-item-type-custom menu-item-object-custom"><a href="#newsletter-signup">Newsletter Signup</a></li>


      <li class="html-block">

        <div class="social-icons size-small">
          <a href="#" target="_blank"  rel="nofollow" class="icon icon_facebook tip-top" title="Follow us on Facebook"><span class="icon-facebook"></span></a>		<a href="#" target="_blank" rel="nofollow" class="icon icon_twitter tip-top" title="Follow us on Twitter"><span class="icon-twitter"></span></a>								<a href="#" target="_blank" rel="nofollow" class="icon icon_instagram tip-top" title="Follow us on Instagram"><span class="icon-instagram"></span></a>												     </div>


      </li>

    </ul>


  </div><!-- inner -->
</div><!-- #mobile-menu -->

<script type='text/javascript' src='/resources/js/flatsome.min.js'></script>

</body>
</html>

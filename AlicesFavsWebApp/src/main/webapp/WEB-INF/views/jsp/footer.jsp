<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<footer class="footer-wrapper" role="contentinfo">


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


<!-- Mobile Popup -->
<div id="jPanelMenu" class="mfp-hide">
  <div class="mobile-sidebar">

    <ul class="mobile-main-menu">
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Clothing</a>
        <ul class="sub-menu">
          <c:forEach items="${clothing}" var="site">
            <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Shoes</a>
        <ul class="sub-menu">
          <c:forEach items="${shoes}" var="site">
            <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Accessories</a>
        <ul class="sub-menu">
          <c:forEach items="${accessories}" var="site">
            <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Home</a>
        <ul class="sub-menu">
          <c:forEach items="${home}" var="site">
            <li class="label-new menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
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

<!--
      <li class="html-block">
        <div class="social-icons size-small">
          <a href="#" target="_blank"  rel="nofollow" class="icon icon_facebook tip-top" title="Follow us on Facebook"><span class="icon-facebook"></span></a>		<a href="#" target="_blank" rel="nofollow" class="icon icon_twitter tip-top" title="Follow us on Twitter"><span class="icon-twitter"></span></a>								<a href="#" target="_blank" rel="nofollow" class="icon icon_instagram tip-top" title="Follow us on Instagram"><span class="icon-instagram"></span></a>												     </div>
      </li>
-->
    </ul>


  </div><!-- inner -->
</div><!-- #mobile-menu -->

<script type='text/javascript' src='/resources/js/flatsome.min.js'></script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<footer class="footer-wrapper" role="contentinfo">


  <div class="absolute-footer dark" style="background-color:#5b5b5b">
    <div class="row">
      <div class="large-12 columns">
        <div class="left">
          <div class="copyright-footer">Copyright 2016 © <strong>Alice's Favs, Inc.</strong></div>
        </div><!-- .left -->
        <div class="right">
          <div class="menu-top-nav-and-footer-nav-container"><ul id="menu-top-nav-and-footer-nav-1" class="footer-nav">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/about-us">About us</a></li>
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/contact-us">Contact us</a></li>
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/disclaimer">Disclaimer</a></li>
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
          <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/clothing">All</a></li>
          <c:forEach items="${clothing}" var="site">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/clothing/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Shoes</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/shoes">All</a></li>
          <c:forEach items="${shoes}" var="site">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/shoes/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Bags</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/bags">All</a></li>
          <c:forEach items="${bags}" var="site">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/bags/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Accessories</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/accessories">All</a></li>
          <c:forEach items="${accessories}" var="site">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/accessories/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page menu-item-has-children menu-parent-item"><a href="#">Home</a>
        <ul class="sub-menu">
          <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/home">All</a></li>
          <c:forEach items="${home}" var="site">
            <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/sale/home/${site.stringId}">${site.displayName}</a></li>
          </c:forEach>
        </ul>
      </li>
    </ul>

    <ul class="top-bar-mob">
      <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/about-us">About us</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/contact-us">Contact us</a></li>
      <li class="menu-item menu-item-type-post_type menu-item-object-page"><a href="/disclaimer">Disclaimer</a></li>

      <li class="html-block">
        <div class="social-icons size-small">
          <a href="http://www.facebook.com/sharer.php?u=http://www.alicesfavs.com/" onclick="window.open(this.href,this.title,'width=500,height=500,top=300px,left=300px');  return false;"  rel="nofollow" target="_blank" class="icon icon_facebook tip-top" title="Share on Facebook"><span class="icon-facebook"></span></a>
          <a href="https://twitter.com/share?url=http://www.alicesfavs.com/" onclick="window.open(this.href,this.title,'width=500,height=500,top=300px,left=300px');  return false;"  rel="nofollow" target="_blank" class="icon icon_twitter tip-top" title="Share on Twitter"><span class="icon-twitter"></span></a>
        </div>
      </li>
    </ul>


  </div><!-- inner -->
</div><!-- #mobile-menu -->

<script type='text/javascript' src='/resources/js/flatsome.min.js'></script>

</body>
</html>

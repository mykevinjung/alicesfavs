<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
  <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

  <div id="content" role="main">




    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    <script type="text/javascript">

      function initialize() {
        var styles = {
          'flatsome':  [{
            "featureType": "administrative",
            "stylers": [
              { "visibility": "on" }
            ]
          },
            {
              "featureType": "road",
              "stylers": [
                { "visibility": "on" },
                { "hue": "#58728a" }
              ]
            },
            {
              "stylers": [
                { "visibility": "on" },
                { "hue": "#58728a" },
                { "saturation": -30 }
              ]
            }
          ]};

        var myLatlng = new google.maps.LatLng(40.79028, -73.95972);
        var myOptions = {
          zoom: 17,
          center: myLatlng,
          mapTypeId: google.maps.MapTypeId.ROADMAP,
          disableDefaultUI: true,
          mapTypeId: 'flatsome',
          draggable: true,
          zoomControl: false,
          panControl: false,
          mapTypeControl: false,
          scaleControl: false,
          streetViewControl: false,
          overviewMapControl: false,
          scrollwheel: false,
          disableDoubleClickZoom: true
        }
        var map = new google.maps.Map(document.getElementById("1082497761"), myOptions);
        var styledMapType = new google.maps.StyledMapType(styles['flatsome'], {name: 'flatsome'});
        map.mapTypes.set('flatsome', styledMapType);

        var marker = new google.maps.Marker({
          position: myLatlng,
          map: map,
          title:""
        });
      }

      google.maps.event.addDomListener(window, 'load', initialize);
      google.maps.event.addDomListener(window, 'resize', initialize);

    </script>

    <div id="map_container">
      <div id="1082497761" style="height:400px;"></div>
      <div id="map_overlay_top"></div>
      <div id="map_overlay_bottom"></div>
      <div class="map-info">
        <div class="row">
          <div class="large-4 columns right">
            <div class="map_inner">
              <h3>Sesam Street 323b, 4010, Norway</h3>
              <p>We&#8217;re open Monday – Friday, 8 a.m. – 7:30 p.m. EST</p>
            </div> <!-- map_inner -->
          </div><!-- large-4 -->
        </div><!-- row -->
      </div><!-- .map-info -->
    </div>


    <div  class="row container ">
      <div class="small-12    large-6  columns   "  ><div class="column-inner"  >
        <h3 class="section-title clearfix  "><span>Frequently Asked Questions</span>  </h3><!-- end section_title -->
        <p class="lead">Please read our FAQ before sending us a message.</p>
        <div class="accordion" rel="0"><div class="accordion-title"><a href="#">What are the delivery charges for orders from the Online Shop?</a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante.</p>
        </div>
          <div class="accordion-title"><a href="#">Which payment methods are accepted in the Online Shop?</a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante.</p>
          </div>
          <div class="accordion-title"><a href="#">How long will delivery take? </a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante. </p>
          </div>
          <div class="accordion-title"><a href="#">How secure is shopping in the Online Shop? Is my data protected?</a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante.</p>
          </div>
          <div class="accordion-title"><a href="#">What exactly happens after ordering? </a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante. </p>
          </div>
          <div class="accordion-title"><a href="#">Do I receive an invoice for my order? </a></div><div class="accordion-inner"><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean id convallis tellus. Nulla aliquam in mi et convallis. Pellentesque rutrum feugiat ante ut imperdiet. Vivamus et dolor nec nisl consectetur vulputate id non ante. </p>
          </div>
        </div>
      </div></div>
      <div class="small-12    large-6  columns   "  ><div class="column-inner"  >
        <h3 class="section-title clearfix  "><span>Send us an email</span>  </h3><!-- end section_title -->
        <div id="ninja_forms_form_2_cont" class="ninja-forms-cont">
          <div id="ninja_forms_form_2_wrap" class="ninja-forms-form-wrap">
            <div id="ninja_forms_form_2_response_msg" style="" class="ninja-forms-response-msg "></div>	<form id="ninja_forms_form_2" enctype="multipart/form-data" method="post" name="" action="http://flatsome.uxthemes.com/wp-admin/admin-ajax.php?action=ninja_forms_ajax_submit" class="ninja-forms-form">

            <input type="hidden" id="_wpnonce" name="_wpnonce" value="d63575a40c" /><input type="hidden" name="_wp_http_referer" value="/pages/contact-us/" />	<input type="hidden" name="_ninja_forms_display_submit" value="1">
            <input type="hidden" name="_form_id"  id="_form_id" value="2">
            <div class="hp-wrap">
              <label>If you are a human and are seeing this field, please leave it blank.			<input type="text" value="" name="_JL1IH">
                <input type="hidden" value="_JL1IH" name="_hp_name">
              </label>
            </div>
            <div id="ninja_forms_form_2_all_fields_wrap" class="ninja-forms-all-fields-wrap">
              <div class="field-wrap text-wrap label-inside"  id="ninja_forms_field_5_div_wrap" data-visible="1">
                <input type="hidden" id="ninja_forms_field_5_type" value="text">
                <input id="ninja_forms_field_5" data-mask="" data-input-limit="" data-input-limit-type="" data-input-limit-msg="" name="ninja_forms_field_5" type="text" placeholder="" class="ninja-forms-field  email " value="Your email" rel="5"   />
                <input type="hidden" id="ninja_forms_field_5_label_hidden" value="Your email">
                <div id="ninja_forms_field_5_error" style="display:none;" class="ninja-forms-field-error">
                </div>
              </div>
              <div class="field-wrap textarea-wrap label-inside"  id="ninja_forms_field_4_div_wrap" data-visible="1">
                <input type="hidden" id="ninja_forms_field_4_type" value="textarea">
                <textarea name="ninja_forms_field_4" id="ninja_forms_field_4" class="ninja-forms-field " rel="4" data-input-limit="" data-input-limit-type="" data-input-limit-msg="">Your message</textarea>
                <input type="hidden" id="ninja_forms_field_4_label_hidden" value="Your message">
                <div id="ninja_forms_field_4_error" style="display:none;" class="ninja-forms-field-error">
                </div>
              </div>
              <div class="field-wrap submit-wrap label-above button-wrap"  id="ninja_forms_field_6_div_wrap" data-visible="1">
                <input type="hidden" id="ninja_forms_field_6_type" value="submit">
                <div id="nf_submit_2">
                  <input type="submit" name="_ninja_forms_field_6" class="ninja-forms-field  button" id="ninja_forms_field_6" value="Send" rel="6" >
                </div>
                <div id="nf_processing_2" style="display:none;">
                  <input type="submit" name="_ninja_forms_field_6" class="ninja-forms-field  button" id="ninja_forms_field_6" value="Processing" rel="6" disabled>
                </div>
                <div id="ninja_forms_field_6_error" style="display:none;" class="ninja-forms-field-error">
                </div>
              </div>
            </div>
          </form>
          </div>
        </div>

        <p>This is form is just for demo purpose. No inquiries will be answered.</p>
      </div></div>
    </div>

    <p>&nbsp;</p>
  </div>
</div><!-- #main-content -->

<jsp:include page="footer.jsp" />

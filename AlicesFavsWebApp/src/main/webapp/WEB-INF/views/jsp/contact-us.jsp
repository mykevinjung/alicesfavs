<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="header.jsp" />

<div id="main-content" class="site-main hfeed light">
  <div class="row"><div class="large-12 columns"><div class="top-divider"></div></div></div>

  <div  class="page-wrapper">
  <div id="content" role="main">


    <div  class="row container ">

      <div class="small-12    large-6  columns   "  ><div class="column-inner"  >
        <h3 class="section-title clearfix  "><span>Frequently Asked Questions</span>  </h3><!-- end section_title -->
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
      </div>
      </div>

      <div class="small-12    large-6  columns   "  ><div class="column-inner"  >

        <h3 class="section-title clearfix  "><span>Send us an email</span>  </h3><!-- end section_title -->
            <div id="ninja_forms_form_2_cont" class="ninja-forms-cont">
              <div id="ninja_forms_form_2_wrap" class="ninja-forms-form-wrap">
                <div id="ninja_forms_form_2_response_msg" style="" class="ninja-forms-response-msg ">
                  <p><c:forEach items="${responseMsgList}" var="responseMsg">${responseMsg}<br></c:forEach></p>
                </div>

                <c:if test="${emailSent != true}">
                  <form id="ninja_forms_form_2" method="post" name="send_us_email_form" action="" class="ninja-forms-form">
                    <div id="ninja_forms_form_2_all_fields_wrap" class="ninja-forms-all-fields-wrap">
                      <div class="field-wrap text-wrap label-inside"  id="send_us_email_name_div_wrap" data-visible="1">
                        <input type="hidden" id="send_us_email_name_type" value="text">
                        <input id="send_us_email_name" name="name" type="text" placeholder="" class="ninja-forms-field" value="${name}" rel="1"   />
                        <input type="hidden" id="send_us_email_name_label_hidden" value="${defaultName}">
                      </div>
                      <div class="field-wrap text-wrap label-inside"  id="send_us_email_email_div_wrap" data-visible="1">
                        <input type="hidden" id="send_us_email_email_type" value="text">
                        <input id="send_us_email_email" name="email" type="text" placeholder="" class="ninja-forms-field" value="${email}" rel="2"   />
                        <input type="hidden" id="send_us_email_email_label_hidden" value="${defaultEmail}">
                      </div>
                      <div class="field-wrap textarea-wrap label-inside"  id="send_us_email_message_div_wrap" data-visible="1">
                        <input type="hidden" id="send_us_email_message_type" value="textarea">
                        <textarea name="message" id="send_us_email_message" class="ninja-forms-field " rel="3">${message}</textarea>
                        <input type="hidden" id="send_us_email_message_label_hidden" value="${defaultMessage}">
                      </div>
                      <div class="field-wrap submit-wrap label-above button-wrap"  id="send_us_email_submit_div_wrap" data-visible="1">
                        <input type="hidden" id="send_us_email_submit_type" value="submit">
                        <div id="nf_submit_2">
                          <input type="submit" name="send_us_email_submit" class="ninja-forms-field  button" id="send_us_email_submit" value="Send" rel="4" >
                        </div>
                      </div>
                    </div>
                  </form>
                </c:if><!-- emailSent -->

              </div>
            </div>

      </div></div>
    </div>

    <p>&nbsp;</p>
  </div>
  </div><!-- .page-wrapper -->
</div><!-- #main-content -->


<link rel='stylesheet' id='ninja-forms-display-css'  href='/resources/ninja-forms/css/ninja-forms-display.css' type='text/css' media='all' />
<script type='text/javascript' src='/resources/ninja-forms/js/min/ninja-forms-display.min.js'></script>

<jsp:include page="footer.jsp" />

<%@include file="./header.jsp"%>

<div id="content-menu-container">

   <%@include file="./menuglobal.jsp"%>

<%
String nameOfCurrentFile = this.getClass().getSimpleName();
%>

   <div id="content-container">

      <div id="content-title">
         <h1>Legal Mapping Tool</h1>
      </div>
      <div id="content-onecolumn">

         <p>The administration page is under construction.</p>


      </div>
   </div>

</div>

<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="NoCountryAdminPage">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>

<%@include file="./footer.jsp"%>
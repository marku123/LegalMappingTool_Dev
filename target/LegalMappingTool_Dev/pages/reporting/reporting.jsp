<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@include file="/header.jsp"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/dot-luv/jquery-ui.css">
<link href="./css/pages/reporting/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="./css/pages/reporting/reporting.css" rel="stylesheet" type="text/css">
<script src="./js/pages/reporting/reporting.js"></script>
<script src="./js/pages/reporting/jspdf.min.js"></script>

<div id="content-menu-container" >
   <%@include file="/menu.jsp"%>

   <%
      String nameOfCurrentFile = this.getClass().getSimpleName();
   %>



</div>


<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>
 
 <!-- Footer -->
<%@include file="/footer.jsp"%>
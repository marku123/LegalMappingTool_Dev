<%@page import="model.*"%>
<%@page import="uploader.*"%>
<%@include file="/header.jsp"%>
<script src="./js/pages/countryhome/countryhome.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>
   <%
   //Initialize files uploaded to the Legal Mapping Tool server.

   UploadUtilities.initializeFiles();

   String nameOfCurrentFile = this.getClass().getSimpleName();

   %>

   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>
      <div id="content-onecolumn">
         <p class="mediumparagraph">
            Welcome to the Legal Mapping Tool for <%=countryName%>!<br> <br> 
            
            Overview page under construction.
         </p>
      </div>
      
   </div>
</div>

<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>

<%@include file="/footer.jsp"%>
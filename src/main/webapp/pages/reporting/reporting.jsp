<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/reporting/reporting.css" rel="stylesheet" type="text/css">
<script src="./js/pages/reporting/reporting.js"></script>
<div id="content-menu-container">
   <%@include file="/menu.jsp"%>

   <%
   
      //Authenticate the user.
      Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
         
      String nameOfCurrentFile = this.getClass().getSimpleName();
      
          
          
      
   %>
      
   <div id="content-container">

      <div id="content-title">
         <h1>
            Legal Mapping Tool for
            <%=countryName%>
         </h1>
      </div>
      <h2>D. CREATE REPORTS</h2>
      <br> <br>
      <div id="content-onecolumn">

         <p class="mediumparagraph">
            Click the below button to generate a report based on all of the data contained in the legal mapping tool for <%=countryName%>.
         </p><br>

         <!-- <p style="color:red; font-weight:bold;border:solid;">Andrea, this is where we can add the report customization options. We can give the user the option to filter out based on
         person of concern, rights category, or sections of the data collected (i.e. they can choose to have only a report on the framework,
         obstacles, analytics, etc)</p>  --><br>

         <div class="reportGenerationDiv">
               <a class="linkbutton" href='/LegalMappingTool_Dev/ReportingController?action=reporting_html&country=<%=countryName%>'  target="_blank">Generate Report</a>
         </div>
 
      </div>

</div>

</div>


<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>

<!-- Footer -->
<%@include file="/footer.jsp"%>
<%@page import="model.*"%>
<%@include file="/header.jsp"%>
<script src="./js/pages/countryhome/countryhome.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>
   <%
   	//Country countryObj = (Country) request.getAttribute("countryObj");
   	//String country = countryObj.getCountryName();
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
            
            <span style='text-decoration:line-through'>
            The Tool consists of two components: <br> <br> 1) Data Collection and Management: 
            The <a href='/LegalMappingTool/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>'>Data Collection Tool</a> 
            allows staff in your office to collect and browse legal data related refugees, asylum seekers, returnees, stateless persons and IDPs.
            <br> <br> 2) Analysis: The <a href='/LegalMappingTool/AnalyticalToolController?action=analytical&country=<%=countryNameForMenu%>'>Analytical Tool</a> facilitates the process of using
            the legal data that has been collected to help identify priority actions that can be taken to ensure Populations of Concern are able to enjoy
            their rights. <br> <br> To access the Data Collection Tool or the Analytical Tool, please click on the appropriate left menu
            item.</span>
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
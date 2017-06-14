<%@page import="model.*"%>
<%@include file="/header.jsp"%>
<script src="./js/pages/countryhome/countryhome.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>
   <%
   	//Country countryObj = (Country) request.getAttribute("countryObj");
   	//String country = countryObj.getCountryName();

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
            The Tool consists of two components: <br> <br> 1) Data Collection and Management: 
            The <a href='/LegalMappingTool/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>'>Data Collection Tool</a> 
            allows staff in your office to collect and browse legal data related refugees, asylum seekers, returnees, stateless persons and IDPs.
            <br> <br> 2) Analysis: The <a href='/LegalMappingTool/AnalyticalToolController?action=analytical&country=<%=countryNameForMenu%>'>Analytical Tool</a> facilitates the process of using
            the legal data that has been collected to help identify priority actions that can be taken to ensure Populations of Concern are able to enjoy
            their rights. <br> <br> To access the Data Collection Tool or the Analytical Tool, please click on the appropriate left menu
            item.
         </p>
      </div>
      
   </div>
</div>
<%@include file="/footer.jsp"%>
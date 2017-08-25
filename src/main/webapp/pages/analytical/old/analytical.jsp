<%@page import="access.*"%>
<%@include file="/header.jsp"%>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
 //Authenticate the user.
   Authentication.AuthenticateUser(request,response);
    %>

   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>

      <div id="content-onecolumn">
         <h2>ANALYTICAL TOOL</h2>

         <p class="mediumparagraph">
            <br> The Analytical Tool utilizes the data contained in the Data Collection Tool to help country offices understand and evaluate
            their country's operational context. The Tool consists of two components:<br><br>
            
            1) Analytics: This section contains data from the Data Collection Tool that has been processed, aggregated and assembled into 
            charts and tables so that country office staff can review key statistics and data sets about its operation.
            
 
            <br><br>
      
            2) Narrative Analysis: This section of the Tool is designed to facilitate the process of using the analytics and the 
            data contained in the Data Collection Tool, as well as the in-house knowledge of the country's operational context, to help identify 
            obstacles facing persons, opportunities for supporting them and the priority actions that can be taken to help them enjoy 
            their rights.   
            

         </p>

      </div>


   </div>

</div>

<%@include file="/footer.jsp"%>
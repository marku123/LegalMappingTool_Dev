<%@include file="/header.jsp"%>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
 	/* Country countryObj = (Country) request.getAttribute("countryObj");
 	String country = countryObj.getCountryName(); */
   %>

   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>

      <div id="content-onecolumn">
         <h2>NARRATIVE ANALYSIS</h2>

         <p class="mediumparagraph">
            <br> The Narrative Analysis component of the Analytical Tool is designed to facilitate the process of using the data contained in the Legal Mapping Tool and your wider
            knowledge of your country's operational context to identify: <br> <br> 1) The key obstacles persons of concern face in being
            able to enjoy their rights.<br> <br> 2) The opportunities that exist to help persons of concern enjoy their rights. <br> <br>
            3) The rights or rights groups that are of critical importance in your country's operation.<br> <br> 4) Priority actions that
            can be taken by your operation over the next two to three years that will improve the ability of persons of concern to enjoy their rights.
            <br> <br> To this end, you will be asked a series of questions that will help guide your thinking. Please ensure that you
            complete them to the best of your ability. <br> <br> 
         </p>

      </div>
   </div>

</div>

<%@include file="/footer.jsp"%>
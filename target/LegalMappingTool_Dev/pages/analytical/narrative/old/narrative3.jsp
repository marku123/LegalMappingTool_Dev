<%@include file="/header.jsp"%>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
 	/* Country countryObj = (Country) request.getAttribute("countryObj");
 	String country = countryObj.getCountryName() */;
   %>
   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>
      <div id="content-onecolumn">
            <form action="DataCollectionController?action=datacollection&saveorquery=s&country=<%=countryName%>" method="POST">
      
            <h2>NARRATIVE ANALYSIS</h2>

            <h3>C. The Importance of Specific Rights in Your Operational Context</h3>

            <p class="narrowparagraph">

               1. Are any rights or rights groups of particular importance in your operational context? If so, why? For example, the strategic
               importance of pathway towards durable solutions; the access to a right is a prerequisite for accessing other rights, etc.<br><br>
               <textarea name="imprights1"></textarea><br><br>

               <button type="button" onclick="goBack()">Previous Page</button>
               <input type="submit" value="Next Page" /><br> <br>
               
            </p>
            </form>
            
      </div>

   </div>
</div>
<%@include file="/footer.jsp"%>
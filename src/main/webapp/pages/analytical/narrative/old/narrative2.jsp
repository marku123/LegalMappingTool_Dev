<%@include file="/header.jsp"%>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
/*  	Country countryObj = (Country) request.getAttribute("countryObj");
 	String country = countryObj.getCountryName(); */
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

            <h3>B. Identifying Opportunities</h3>

            <p class="narrowparagraph">

               1. Using the findings in Part 1 of the Analytical Tool as well as your knowledge of the wider operational context, what opportunities
               exist that could allow persons of concern to further enjoy their rights? For example, is there legislation that formally applies to
               persons of concern that has not yet been implemented, is there a scheduled review of existing legislation, etc.?<br> <br>

               <textarea name="idopportunities1"></textarea>

               <br> <br> 2. For the opportunities you described above, please identify what actions can be taken make use of them? Examples
               of actions may include advocacy, information sharing, capacity building, financial support, etc.<br> <br>
               <textarea name="idopportunities2"></textarea><br> <br>

               3. For each of the obstacles identified in question 1, what actions are required to address them? Examples of actions may include
               advocacy, information sharing, capacity building, financial support, etc.<br> <br>
               <textarea name="idobstacles3"></textarea>
               <br> <br>
               <button type="button" onclick="goBack()">Previous Page</button>
               <input type="submit" value="Next Page" /><br> <br>
            </p>
             </form>

      </div>

   </div>
</div>
<%@include file="/footer.jsp"%>
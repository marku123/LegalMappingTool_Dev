<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/analytical/narrative/narrative.css" rel="stylesheet" type="text/css">
<script src="./js/pages/analytical/narrative/narrative.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/drilldown.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="./js/pages/analytical/analytics/sorttable.js"></script>
<script src="./js/pages/analytical/analytics/analytics.js"></script>
<link href="./css/pages/analytical/analytics/analytics.css" rel="stylesheet" type="text/css">

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
   
   //Authenticate the user.
   Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
   
   String nameOfCurrentFile = this.getClass().getSimpleName();
      
   //Narrative Analysis
   
   String allNarratives[] = countryObj.getNarrative();
   String NarrativesPriorities[] = countryObj.getNarrativePriorities();
   String NarrativesObstacles[] = countryObj.getNarrativeObstacles();
   
   %>
   
   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for
            <%=countryName%>
         </h1>
      </div>
      <div id="content-onecolumn">
      
         <h2>C. IDENTIFYING PRIORITIES</h2>         
         <!-- Narrative Analysis -->

         <h3>C.1 Narrative Analysis</h3>

         <form action="AnalyticalToolController?action=narrative&country=<%=countryNameForMenu%>" method="POST">
             
            <h4>The Importance of Specific Rights in Your Operational Context</h4>
            <br>
            <p class="mediumparagraph">
            
               1. In the opinion of the operation, are any rights of particular importance in your 
               operational context? If so, why?  <br> <br> 
               <textarea class="narrativeanswers" name="narrative4"><%=NarrativesPriorities[0]%></textarea><br><br>
            
              2. In the opinion  of populations of concern, are any rights of particular importance 
              in your operational context? If so, why? <br> <br> 
               <textarea class="narrativeanswers" name="narrative5"><%=NarrativesPriorities[1]%></textarea><br><br>           
            </p>  
             <div class="savebuttonandtooltip">
               <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
             </div>                                   
             <br> <br>  
            <h4>Obstacle Analysis</h4>
   
            <p class="mediumparagraph">
   
               1. Using the data from section "D1. Analytics" and your knowledge of your country's legal context, what are the main obstacles
               preventing populations of concern from being able to enjoy their rights?<br> <br> 
   
               <textarea class="narrativeanswers" name="narrative6"><%=NarrativesObstacles[0]%></textarea>
   
               <br> <br> 
               2. For the obstacles you identified in question 1, please describe the underlying reasons as to why these obstacles
               exist. <br><br>
               <textarea class="narrativeanswers" name="narrative7"><%=NarrativesObstacles[1]%></textarea><br><br> 
   
               
             </p>
             <div class="savebuttonandtooltip">
               <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
             </div>                                   
             <br> <br>  
            <h4>Areas for Law and Policy Reform</h4>
             
            <p class="mediumparagraph">
            
               1. On the basis of the information gathered in the Legal Mapping Tool, what priority areas of law and policy reform should be considered 
               to strengthen the protection of rights of populations of concern? <br> <br> 
               <textarea class="narrativeanswers" name="narrative1"><%=allNarratives[0]%></textarea><br><br>
            
               2. What advocacy will be required to achieve the priority reforms identified above?  <br> <br> 
               <textarea class="narrativeanswers" name="narrative2"><%=allNarratives[1]%></textarea><br><br>           

            </p>
            <div class="savebuttonandtooltip">
               <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
             </div>                                   
             <br> <br>               
            <h4>Priority Areas for Complementary Programming</h4>
            
            <p class="mediumparagraph">
            
              1. Please identify the priority areas of intervention that you would propose for your operation in the coming 2 to 3 years? <br> <br> 
               <textarea class="narrativeanswers" name="narrative3"><%=allNarratives[2]%></textarea><br><br>        

             </p>            

             <div class="savebuttonandtooltip">
               <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
             </div>                                   
             <br> <br>               
            </form>
               
      </div>


   </div>
</div>

<!-- Authentication-->
<input type="hidden" name="authEditView" value="<%=authenticatedToEd%>">
<%@include file="/authentication.jsp"%>
<script src="./js/pages/authentication.js"></script>
<link href="./css/pages/login/authentication.css" rel="stylesheet" type="text/css">

<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>
<%@include file="/footer.jsp"%>
<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="uploader.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/datacollection/datacollection_a.css" rel="stylesheet" type="text/css">
<script type="text/javascript"   src="./js/pages/datacollection/datacollection_a.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
   
   //Authenticate the user.
   Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
   
 //Initialize files uploaded to the Legal Mapping Tool server.
      UploadUtilities.initializeFiles(countryName);
   
      String nameOfCurrentFile = this.getClass().getSimpleName();

      //String rightsGroupDropDownOptions = FormatDataColBRepository.formatRightsGroupsDropDown(countryObj); 
      //String rightgroup = countryObj.getRightsGroup();
      
      String intlInstrumentsTables = FormatDataColBRepository.formatIntlInstrumentTables(countryObj);
      String natlInstrumentsTables = FormatDataColBRepository.formatNationalInstrumentTables(countryObj);

   %>

   <div id="content-container">

      <div id="content-title">
         <h1>Legal Mapping Tool for <%=countryName%></h1>
      </div>

      <div id="content-onecolumn">

         <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
           
         <h3>A.2 Legal Instrument Repository</h3>
         <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Legal Mapping Tool, should ensure that all data fields are completed as accurately as possible.</p>
         </div>
         <%-- <form action="DataCollectionController?action=datacollection_a&country=<%=countryNameForMenu%>" method="POST" >
            <div class="rightsGroups">
               Select a Rights Category:
               <select id="personofconcernselect" onchange="this.form.submit()" name="rightsgroup">
                  <%=rightsGroupDropDownOptions%>
               </select>
            </div>
         </form>  --%>
         <div id="allquestions">


         <form action="DataCollectionController?action=datacollection_a&country=<%=countryNameForMenu%>" method="POST" >
         
<%--             <input id='rightsgroup' name='rightsgroup' type='hidden' value='<%=rightgroup%>'> --%>
   
            <!-- International Instruments -->
            <div id="question1">
               <p id="question1para">
                  1. International, regional and bilateral instruments:<br><br>
               </p>
               
                   <%=intlInstrumentsTables%>       
               <br>
               <div id="" class="addinstrument">
                  <input onclick="intlInstruAddRow();" type="button" value="Add An Instrument" />  
               </div>
                                
               <br><br>
             
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" />
                  <span class="tooltiptext">All changes on page will be saved.</span>
               </div> 
            </div>
            
            <!-- National Instruments -->
            <div id="question2">
               <p  id="question2para">
                  2. National instruments:<br><br>
               </p>
                   <%=natlInstrumentsTables%> 
               <br>
               <div class="addinstrument">
                  <input type="button" value="Add An Instrument" onclick="natlInstruAddRow();" />  
               </div>
                                
               <br><br>
             
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" />
                  <span class="tooltiptext">All changes on page will be saved.</span>
               </div> 
               <input name="countryNameForFileUpload" type="hidden" value="<%=countryName%>">
            </div>


            <br><br>
            </form>
         </div>
 
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
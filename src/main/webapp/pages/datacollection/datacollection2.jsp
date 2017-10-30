<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="uploader.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/datacollection/datacollection2.css" rel="stylesheet" type="text/css">
<script type="text/javascript"   src="./js/pages/datacollection/datacollection2.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
   
   //Authenticate the user.
   Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
   
 //Initialize files uploaded to the Legal Mapping Tool server.
      UploadUtilities.initializeFiles(countryName);
   
      String nameOfCurrentFile = this.getClass().getSimpleName();

      String rightsGroupDropDownOptions = FormatDataColB.formatRightsGroupsDropDown(countryObj); 
      String rightgroup = countryObj.getRightsGroup();
      String POCs = countryObj.getPOCCountry();

      String intlInstrumentsTables = FormatDataColB.formatIntlInstrumentTables(countryObj);
      String natlInstrumentsTables = FormatDataColB.formatNationalInstrumentTables(countryObj);
      String constitutionTables = FormatDataColB.formatConstitutionTable(countryObj);


   %>

   <div id="content-container">

      <div id="content-title">
         <h1>Legal Mapping Tool for <%=countryName%></h1>
      </div>

      <div id="content-onecolumn">

         <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
           
         <h3>A.3 Rights Protection</h3>
         <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Legal Mapping Tool, should ensure that all data fields are completed as accurately as possible.</p>
         </div>
         <form action="DataCollectionController?action=datacollection2&country=<%=countryNameForMenu%>" method="POST" >
            <div class="rightsGroups">
               Select a Rights Category:
               <select id="personofconcernselect" onchange="this.form.submit()" name="rightsgroup">
                  <%=rightsGroupDropDownOptions%>
               </select>
            </div>
         </form>
         <div id="allquestions">


         <form action="DataCollectionController?action=datacollection2&country=<%=countryNameForMenu%>" method="POST" >
         
            <input id='rightsgroup' name='rightsgroup' type='hidden' value='<%=rightgroup%>'>
   
            <!-- International Instruments -->
            <div id="question1">
               <p id="question1para">
                  1. International and regional instruments related to <strong><%=rightgroup%></strong> rights:<br><br>
               </p>
               
                   <%=intlInstrumentsTables%>       
               <br>
               <!-- <div id="" class="addinstrument">
                  <input onclick="intlInstruAddRow();" type="button" value="Add An Instrument" />  
               </div> -->
                                
               <br><br>
             
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" />
                  <span class="tooltiptext">All changes on page will be saved.</span>
               </div> 
            </div>
            
            <!-- International Instruments -->
            <div id="question1">
               <p id="question1para">
                  2. The country's constitution and <strong><%=rightgroup%></strong> rights:<br><br>
               </p>
               
                   <%=constitutionTables%>       
               <br>
               <!-- <div id="" class="addinstrument">
                  <input onclick="intlInstruAddRow();" type="button" value="Add An Instrument" />  
               </div> -->
                                
               <br><br>
             
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" />
                  <span class="tooltiptext">All changes on page will be saved.</span>
               </div> 
            </div>
            
            
            <!-- National Instruments -->
            <div id="question2">
               <p  id="question2para">
                  3. Other national instruments that are relevant to <strong><%=rightgroup%></strong> rights:<br><br>
               </p>
                   <%=natlInstrumentsTables%> 
               <br>
               <br><br>
             
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" />
                  <span class="tooltiptext">All changes on page will be saved.</span>
               </div> 
               <input name="countryNameForFileUpload" type="hidden" value="<%=countryName%>">
               <input type='hidden' name='countrypocs' value='<%=POCs%>'>           
               
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
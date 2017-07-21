<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="uploader.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/datacollection/datacollection3.css" rel="stylesheet" type="text/css">
<script src="./js/pages/datacollection/datacollection3.js"></script>

<div id="content-menu-container">

   <%@include file="/menu.jsp"%>

   <%
   //Initialize files uploaded to the Legal Mapping Tool server.
   UploadUtilities.initializeFiles();
   
   String nameOfCurrentFile = this.getClass().getSimpleName();

   String POCDropDownOptions = FormatDataColC.formatPOCDropDown(countryObj); 

   String personofconcern = countryObj.getPOC();

   String obstacleTables = FormatDataColC.formatObstaclesTable(countryObj); 	

   String obstacleDocumentation = FormatDataColC.formatObstacleDocumentation(countryObj); 	

   %>

   <div id="content-container">

      <div id="content-title">
         <h1>Legal Mapping Tool for <%=countryName%></h1>
      </div>

      <div id="content-onecolumn">

         <h2>B. OBSTACLES TO ENJOYING RIGHTS</h2>
                   
         <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Legal Mapping Tool, should ensure that all data fields are completed as accurately as possible.</p>
         </div>
                 
         <form action="DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>" method="POST">
            <div class="personsofconcern">
               Select a Population of Concern:
               <select id="personofconcernselect" onchange="this.form.submit();" name="personofconcern">
                  <%=POCDropDownOptions%>
               </select>
            </div>
         </form>
         
         <form action="DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>" method="POST">
         
         <div id="question1">
         
         <input id='personofconcern' name='personofconcern' type='hidden' value='<%=personofconcern%>'>
         
         <h3>B.1 Obstacles Preventing <span class='bluetext'><%=personofconcern%></span> from Enjoying Their Rights</h3>
         
         <div class='showhidediv'>
             <input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAll()'>
             <input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAll()'> 
         </div>
         
         <div  id='allObstacleTablesDiv'>
            <%=obstacleTables%> 
         </div>
                                
         <h3>B.2 Documentation</h3>
         
               <div id='obstacleDocumentationSection'>
                  <%=obstacleDocumentation%> 
                  <input type="button" value="Add File(s)" id="showHideUpload" /><br><br>
               </div>
	     <div id='uploadButtonsSection' >
        
                  <label class='browsefile'>
                     <input id='uploadbutton' name='browseButton' type='file' />Click to Browse for File
                  </label>
                  <input class='inputfilename' type='text' name='natlinstrurefupload' />
                  <button id='upLoadFileButton' name='uploadButton' type='button' value='' >Upload File</button>
                  <p class='uploadfilesuccess' id='uploadsuccess' ></p>

               </div><br><br>
         
         <div class="savebuttonandtooltip">
               <input class="savebutton" type="submit" name="savedata" value="Save Changes" onclick='test()'/><span class="tooltiptext">All changes on page will be saved.</span>
          </div>                                   
         </div>
         
         </form>
      </div>
   </div>
</div>

<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>


<%@include file="/footer.jsp"%>
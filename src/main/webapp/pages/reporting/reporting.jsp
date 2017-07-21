<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/reporting/reporting.css" rel="stylesheet" type="text/css">
<script src="./js/pages/reporting/reporting.js"></script>

<script src="https://code.highcharts.com/highcharts.js"></script>

<script type="text/javascript" src="http://www.cloudformatter.com/Resources/Pages/CSS2Pdf/Script/xepOnline.jqPlugin.js"></script>


<div id="content-menu-container">
   <%@include file="/menu.jsp"%>

   <%
   	String nameOfCurrentFile = this.getClass().getSimpleName();

   	//A.1 The country's constitution
   	String constYesNo = FormatingUtilities.capitalizeString(countryObj.getConstitutionYesNo());
   	String constEffectDate = countryObj.getConstitutionDate();
   	String constAmendYesNo = FormatingUtilities.capitalizeString(countryObj.getConstitutionAmendYesNo());
   	String constAmendDate = countryObj.getConstitutionAmendDate();
   	String constLinkFrenEng = countryObj.getConstLinkFrenEng();
   	String constLinkOrig = countryObj.getConstLinkOrig();
   	String billLinkFrenEng = countryObj.getBillLinkFrenEng();
   	String billLinkOrig = countryObj.getBillLinkOrig();

   	//Applicability of Constitution to POCs Table
   	String constAppToPoC[][] = countryObj.getConstAppToPoC();
   	String POC1Checked = FormatingUtilities.capitalizeString(constAppToPoC[0][0].replace("null", ""));
   	String POC2Checked = FormatingUtilities.capitalizeString(constAppToPoC[1][0].replace("null", ""));
   	String POC3Checked = FormatingUtilities.capitalizeString(constAppToPoC[2][0].replace("null", ""));
   	String POC4Checked = FormatingUtilities.capitalizeString(constAppToPoC[3][0].replace("null", ""));
   	String POC5Checked = FormatingUtilities.capitalizeString(constAppToPoC[4][0].replace("null", ""));

   	String POC1Comment = constAppToPoC[0][1].replace("null", "");
   	String POC2Comment = constAppToPoC[1][1].replace("null", "");
   	String POC3Comment = constAppToPoC[2][1].replace("null", "");
   	String POC4Comment = constAppToPoC[3][1].replace("null", "");
   	String POC5Comment = constAppToPoC[4][1].replace("null", "");

   	//A2 Intro
   	String commonCivilPlural = FormatingUtilities.capitalizeString(countryObj.getCommonCivilPlural());
   	String pluralText = countryObj.getPluralText();
   	String federalState = FormatingUtilities.capitalizeString(countryObj.getFederalState());

   	//A2 Judicial, Admin, Traditional
   	String judicialEntitiesTable = FormatDataColAReporting.formatJudicalEntities(countryObj);
   	String adminEntitiesTable = FormatDataColAReporting.formatAdminEntities(countryObj);
   	String tradMechTable = FormatDataColAReporting.formatTradMechanisms(countryObj);
   	String tradmechcomments = countryObj.getTradMechComments();

   	//A2 Comments
   	String comments = countryObj.getComments();

   	//A3 International and National Instruments      
   	String intlInstrumentsTables = FormatDataColBReporting.formatNatIntlInstrumentTables(countryObj);

   	//B Obstacles to Enjoying Rights

          String obstacleTables = FormatDataColCReporting.formatObstacleTablesDocs(countryObj); 	
      
      
          //Analytics 
          //Finding table.  
          String obstaclesFindingsTable = FormatAnalytics.formatFindingsTable(countryObj);
          
          //Consistency with international standards. Chart and table. 
          String legalFrameworkConsistentWithInternational = FormatAnalytics.formatLegalFrameworkConsistencyTable(countryObj); 
          String legalFrameworkConsistencyMissingData = FormatAnalytics.formatLegalFrameworkConsistencyMissingData(countryObj);
          String legalFrameworkConsistentWithInternationalChartData = FormatAnalytics.formatLegalFrameworkConsistencyChart(countryObj); 
      
          //National Instruments Support Rights of POCs. Chart and table.
          String NatInstrumentsCharts = FormatAnalytics.formatPOCNatInstrumentsChartSummary(countryObj);   
          String NatInstrumentsTable = FormatAnalytics.formatPOCNatInstrumentsTable(countryObj);
          String NatInstrumentsMissingData = FormatAnalytics.formatPOCNatInstrumentsMissingData(countryObj);
          
          //Obstacles to enjoying rights. Charts and tables. 
          String POCObstaclesCharts = FormatAnalytics.formatPOCObstaclesChartSummary(countryObj);   


          String POCRatingObstaclesCharts = FormatAnalytics.formatPOCObstaclesChartDetails(countryObj);   
          String POCRatingObstaclesTable = FormatAnalytics.formatPOCObstaclesTable(countryObj);
          
          
          String POCRightsGroupsObstaclesCharts = FormatAnalytics.formatPOCRightsGroupsObstaclesCharts(countryObj);   
          String POCRightsGroupsObstaclesTable = FormatAnalytics.formatPOCRightsGroupsObstaclesTable(countryObj);

          String obstaclesMissingData = FormatAnalytics.formatObstaclesMissingData(countryObj);
          
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
      <h2>D. CREATE REPORTS</h2>
      <br> <br>
      <div id="content-onecolumn">



         <p style="color:red; font-weight:bold;border:solid;">Andrea, this is where we can add the report customization options. We can give the user the option to filter out based on
         person of concern, rights category, or sections of the data collected (i.e. they can choose to have only a report on the framework,
         obstacles, analytics, etc)</p> <br><br>

         <div class="reportGenerationDiv">
               <a class="linkbutton" href='/LegalMappingTool_Dev/ReportingController?action=reporting_html&country=<%=countryName%>'  target="_blank">Generate Report</a>
         </div>
 
      </div>

</div>

</div>


<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>

<!-- Footer -->
<%@include file="/footer.jsp"%>
<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<link href="./css/pages/reporting/reporting_html.css" rel="stylesheet" type="text/css">
<link href="./css/pages/reporting/reporting_html_print.css" rel="stylesheet" type="text/css">
<script src="./js/pages/reporting/reporting_html.js"></script>
<script src="./js/pages/reporting/sorttable.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>

<!-- <script type="text/javascript" src="http://www.cloudformatter.com/Resources/Pages/CSS2Pdf/Script/xepOnline.jqPlugin.js"></script> -->


<div id="content-menu-container">

   <%
      //Authenticate the user.
      Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
   
      Country countryObj = (Country) request.getAttribute("countryObj");
      String countryName = countryObj.getCountryName();
      String countryNameForMenu = countryName.replaceAll("'", "%27").replaceAll("\"", "%22").replaceAll("&", "%26"); 

   
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
          
       //Table of Contents
       
       String tableOfContentsSectionA = FormatDataColBReporting.formatTableOfContents(countryObj);
       String tableOfContentsSectionB = FormatDataColCReporting.formatTableOfContents(countryObj);

      
   %>
   
   <!-- This is a repeated title of the report for printing purposes only. It is not displayed when the user is not in print preview.  -->
   <div class="reportTitleDivPrint">
      <h1>
         Legal Mapping Tool Report for the <%=countryName%> Country Operation
      </h1>
      <div class="reportTitleDateDiv">
         Report Date: <span class="reportTitleDate"></span>
      </div>
   </div>
   
   <!-- End of the repeated title of the report.  -->
   
   <div id="left-col">


      <div id='left-index'>

   <!--***************Table of Contents**************-->

         <div class="tableofcontents_htmlreport">
            <p>Table of Contents</p>
            <%=tableOfContentsSectionA%>
            <%=tableOfContentsSectionB%>
            <a class="tableofcontentshead" href="#SectionC">C. Identifying Priorities</a>
            <a class="tableofcontentssub1" href="#C1">C.1 Analytics</a>
            <a class="tableofcontentssub2" href="#C11">C.1.1 Summary Data Findings</a>
            <a class="tableofcontentssub2" href="#C12"><span class="tableofcontentssubNum">C.1.2 </span><span class="tableofcontentssubText">Consistency of National Legal Instruments With International Standards</span> </a>            
            <a class="tableofcontentssub2" href="#C13"><span class="tableofcontentssubNum">C.1.3 </span><span class="tableofcontentssubText"> National Legal Instrument Support for the Rights of Nationals and Persons of Concern</span></a>
            <a class="tableofcontentssub2" href="#C14"><span class="tableofcontentssubNum">C.1.4 </span><span class="tableofcontentssubText"> Obstacles Populations of Concern Face in Being Able to Enjoy Their Rights</span></a>
            <a class="tableofcontentssub2" href="#C15"><span class="tableofcontentssubNum">C.1.5 </span><span class="tableofcontentssubText"> Obstacles Preventing Populations of Concern Face From Being Able to Enjoy Their Rights</span></a>
            <a class="tableofcontentssub2" href="#C16"><span class="tableofcontentssubNum">C.1.6 </span><span class="tableofcontentssubText"> The Accessibility of Rights to Populations of Concern</span></a>
            <a class="tableofcontentssub1" href="#C2">C.2 Narrative Analysis</a>
         </div>
      </div>
      
   </div>
      
   <div id="content-container">

      <div id="content-onecolumn">

   <!--***************Title of the Report**************-->

         <div class="reportTitleDivNoPrint">
            <h1>
               Legal Mapping Tool Report for the <%=countryName%> Country Operation
            </h1>
            <div class="reportTitleDateDiv">
               Report Date: <span class="reportTitleDate"></span>
            </div>
         </div>
         <br> <br>


<!--***************A The Legal Protection Framework**************-->

         <h2 id="SectionA">A. THE LEGAL PROTECTION FRAMEWORK</h2>
<!--***************A2 The Country's Legal System**************-->

         <h3  id="A1">A.1 The Country's Constitution</h3>

         <p>
            1. The country has a constitution:
            <%=constYesNo%>
         </p>
         <br>
         <p>
            2. Date the constitution came into effect:
            <%=constEffectDate%>
         </p>
         <br>
         <p>
            3. The constitution has been amended:
            <%=constAmendYesNo%>
         </p>
         <br>
         <p class="consitutionindent">
            The date the constitution was last amended:
            <%=constAmendDate%>
         </p>
         <br>
         <p>
            4. Link to the constitution in Refworld:<br>
         </p><br>
         <p class="consitutionindent">French or English:</p>

         <a class="refworldlink" href="<%=constLinkFrenEng%>" target="_blank"><%=constLinkFrenEng%></a>
         <p class="consitutionindent">Original Language (If Other Than French or English):</p>
         <a class="refworldlink" href="<%=constLinkOrig%>" target="_blank"><%=constLinkOrig%></a> <br><br>
         <p>
            5. Link to the Bill of Rights in Refworld:<br>
         </p><br>
         <p class="consitutionindent">French or English:</p>
         <a class="refworldlink" href="<%=billLinkFrenEng%>" target="_blank"><%=billLinkFrenEng%></a><br>

         <p class="consitutionindent">Original Language (If Other Than French or English):</p>
         <a class="refworldlink" href="<%=billLinkOrig%>" target="_blank"><%=billLinkOrig%></a> <br><br>
         <p>
            6. Application of rights in the constitution to Populations of Concern:<br><br>
         </p>
         <table class="constpersons">
            <thead>
               <tr>
                  <th>Populations of Concern</th>
                  <th>Rights in the Constitution Apply to Populations of Concern</th>
                  <th>Comments</th>

               </tr>
            </thead>
            <tbody>
               <tr>
                  <td>Internally Displaced Persons</td>
                  <td><%=POC1Checked%></td>
                  <td><%=POC1Comment%></td>
               </tr>
               <tr>
                  <td>Refugees</td>
                  <td><%=POC2Checked%></td>
                  <td><%=POC2Comment%></td>
               </tr>
               <tr>
                  <td>Asylum Seekers</td>
                  <td><%=POC3Checked%></td>
                  <td><%=POC3Comment%></td>
               </tr>
               <tr>
                  <td>Stateless Persons</td>
                  <td><%=POC4Checked%></td>
                  <td><%=POC4Comment%></td>
               </tr>
               <tr>
                  <td>Returnees</td>
                  <td><%=POC5Checked%></td>
                  <td><%=POC5Comment%></td>
               </tr>
            </tbody>
         </table>
         <br> <br>

<!--***************A2 The Country's Legal System**************-->

         <h3 id="A2">A.2 The Country's Legal System</h3>

         <p>
            1. Type of legal system:
            <%=commonCivilPlural%>
         </p>
         <p class="legalsystemindent">
            <%=pluralText%>
         </p>
         <br>
         <p>
            2. Political and Administrative Structure:
            <%=federalState%>
         </p>
         <br>

         <p>3. Judicial entities/courts:</p>
         <br>

         <table class="a2Tables">
            <thead>
               <tr>
                  <th>Judicial Entity/Court</th>
                  <th>Refugees Can Formally Access the Entity/Court</th>
                  <th>IDPs Can Formally Access the Entity/Court</th>
                  <th>Returnees Can Formally Access the Entity/Court</th>
                  <th>Stateless Persons Can Formally Access the Entity/Court</th>
                  <th>Asylum Seekers Can Formally Access the Entity/Court</th>
               </tr>
            </thead>
            <tbody>
               <%=judicialEntitiesTable%>
            </tbody>
         </table>
         <br>
         <p>4. Administrative entities:</p>
         <br>
         <table class="a2Tables">
            <thead>
               <tr>
                  <th>Administrative Entities</th>
                  <th>Refugees Can Formally Access the Entity</th>
                  <th>IDPs Can Formally Access the Entity</th>
                  <th>Returnees Can Formally Access the Entity</th>
                  <th>Stateless Persons Can Formally Access the Entity</th>
                  <th>Asylum Seekers Can Formally Access the Entity</th>
               </tr>
            </thead>
            <tbody>
               <%=adminEntitiesTable%>
            </tbody>
         </table>
         <br>

         <p>5. Traditional mechanisms:</p>
         <br>
         <table class="a2Tables">
            <thead>
               <tr>
                  <th>Traditional Mechanisms</th>
                  <th>Refugees Can Formally Access the Entity</th>
                  <th>IDPs Can Formally Access the Entity</th>
                  <th>Returnees Can Formally Access the Entity</th>
                  <th>Stateless Persons Can Formally Access the Entity</th>
                  <th>Asylum Seekers Can Formally Access the Entity</th>
               </tr>
            </thead>
            <tbody>
               <%=tradMechTable%>
            </tbody>
         </table>
         <br>
         <p class="legalsystemindent">
            <%=tradmechcomments%>
         </p>
         <br>
         <p>6. Additional comments on the formal legal system:</p>
         <br>
         <p class="legalsystemindent">
            <%=comments%>
         </p>

         <br> <br>

<!--***************A3 Rights Protection**************-->

         <h3  id="A3">A.3 Rights Protection</h3>

         <%=intlInstrumentsTables%>
         <br>
         
         
<!--***************B Obstacles to Enjoying Rights**************-->
         <h2 id="SectionB">B. OBSTACLES TO ENJOYING RIGHTS</h2>

         <%=obstacleTables%>
         
         

<!--***************Analytics and Narrative Section**************-->
         
         <h2 id="SectionC">C. IDENTIFYING PRIORITIES</h2>
         
<!--***************Analytics**************-->
         
          <h3 id="C1">C.1 Analytics</h3>

        <h4 id="C11">C.1.1 Summary Data Findings</h4>
         <!-- Summary Findings -->
         <div id='findingstablediv'>
            <%=obstaclesFindingsTable%>
         </div>      

         <h4 id="C12">C.1.2 Consistency of National Legal Instruments With International Standards</h4>
         <div id="detail-consistency-chart"> </div>
         <div id="belowchart-missingdata"><%=legalFrameworkConsistencyMissingData%> </div>
         <div id='detail-consistency-table'><%=legalFrameworkConsistentWithInternational%></div>            

         <h4 id="C13">C.1.3 National Legal Instrument Support for the Rights of Nationals and Persons of Concern</h4>
         <div id="summary-poc-natinstsruments-chart"> 
         </div>
        <div id="belowchart-missingdata"><%=NatInstrumentsMissingData%> </div>        
         <div id='detail-poc-natinstsruments-table'>
            <%=NatInstrumentsTable%>
         </div>

         <h4 id="C14">C.1.4 Obstacles Populations of Concern Face in Being Able to Enjoy Their Rights</h4>
         <div id="summary-poc-obstacles-chart"> 
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> 
         </div>        
         
         <h4 id="C15">C.1.5 Obstacles Preventing Populations of Concern Face From Being Able to Enjoy Their Rights</h4>
         <div id="detail-poc-obstacles-chart">  
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> </div>        
         <div id='detail-poc-obstacles-table'>
            <%=POCRatingObstaclesTable%>
         </div>
         
         
         <h4 id="C16">C.1.6 The Accessibility of Rights to Populations of Concern</h4>
         <div id="detail-poc-obstacles-rightgroups-chart">  
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> 
         </div>        
         <div id='detail-poc-obstacles-rightgroups-table'>
            <%=POCRightsGroupsObstaclesTable%>
         </div>
         
         <br><br>
         
<!--***************End Analytics**************-->
         
 <!--***************Narrative Analysis**************-->
         
         <h3 id="C2">C.2 Narrative Analysis</h3>

             
         <h4>The Importance of Specific Rights in Your Operational Context</h4>
         <br>
         <p>
            
               1. In the opinion of the operation, are any rights of particular importance in your 
               operational context? If so, why? 
         </p> <br> 
         <p class="narrativeanswer">      
                <%=NarrativesPriorities[0]%>
         </p>   <br>
         <p>
              2. In the opinion  of populations of concern, are any rights of particular importance 
              in your operational context? If so, why? 
         </p>     
              <br> 
         <p class="narrativeanswer">      

              <%=NarrativesPriorities[1]%>       
         </p>                                  
         <br> 
         <h4>Obstacle Analysis</h4>
         <br>
   
         <p class="mediumparagraphquestion">
               1. Using the data from section "D1. Analytics" and your knowledge of your country's legal context, what are the main obstacles
               preventing populations of concern from being able to enjoy their rights?
         </p><br> 
               
         <p class="narrativeanswer">      
               <%=NarrativesObstacles[0]%>
         </p>
         <br> 
         <p>
               2. For the obstacles you identified in question 1, please describe the underlying reasons as to why these obstacles
               exist. 
         </p> <br> 
         <p class="narrativeanswer">      
               <%=NarrativesObstacles[1]%>
         </p>
         <br> 
         <h4>Areas for Law and Policy Reform</h4>
         <br>
         
         <p class="mediumparagraphquestion">
            
               1. On the basis of the information gathered in the Legal Mapping Tool, what priority areas of law and policy reform should be considered 
               to strengthen the protection of rights of populations of concern?              
         </p>
         <br> 
         <p class="narrativeanswer">      
               <%=allNarratives[0]%>
         </p> 
         <br>
         <p>
               2. What advocacy will be required to achieve the priority reforms identified above?  
         </p>
         <br>   
         <p class="narrativeanswer">      
              <%=allNarratives[1]%>           
         </p>
         <br>           
         <h4>Priority Areas for Complementary Programming</h4>
         <br>
         <p>
              1. Please identify the priority areas of intervention that you would propose for your operation in the coming 2 to 3 years? 
         </p>
         <br> 
         <p class="narrativeanswer">      
            <%=allNarratives[2]%>
         </p>            
         <br>        
         
  <!--***************End Narrative Analysis**************-->
      </div>

</div>

</div>

   
   <!-- Higharts-->

<script>
$(function () {
    
    //Configuration of the consistency with international standards chart. 
    
    
        var firstChartTitle = 'The Consistency of National Legal Instruments with International Standards for Key Rights Categories';
        var firstChartSubTitle = '(1=\"Legal Instruments Are Not Consistent\", 3=\"Legal Instruments Are Consistent\")';
        //var footNote = '*Rating is based on a scale from 1 to 3, where 1 indicates there is no consistency with international standards and 3 full consistency.';
        var footNote = '';

        $('#detail-consistency-chart').highcharts({
            
            
            chart: {
                type: 'bar',
                style: {fontFamily: 'calibri'},
            },
            title: {
                text: firstChartTitle,
                style:{fontWeight:'bold',fontSize:'17px'}
                
            },
            subtitle: {
                text: firstChartSubTitle,
                style: {fontSize:'15px'}
            },
            xAxis: {
                type: 'category',
                title: {text: 'Rights Categories',style: {fontSize:'16px',fontWeight:'bold'}},labels:{style: {fontSize:'13px'}}
            },
            yAxis: {
                max:3,
                min: 1,
                title: {
                    text: footNote,
                    align:'left'
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
           },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                         enabled: true,
                    }
                }
            },
            tooltip: {
                pointFormat: '<span class="boldbluetext">Rating</span>: <b>{point.y:.2f}</b><br/>'
            },
            credits: {
                enabled: false
            },
            
            series: [{
                name: 'Populations of Concern',
                data: [ <%=legalFrameworkConsistentWithInternationalChartData%>   ]
            }]
        });
});



//Highchart configuration for the National Instruments supporting POC Rigths section of the Analytics page. 

$(function () {    
    
        var firstChartTitle = 'National Legal Instrument Support for the Rights of Nationals and Populations of Concern';
        var firstChartSubTitle = '(1=\"National Legal Instruments Do Not Support Their Rights\", 3=\"National Legal Instruments Support Their Rights\")';        
        var footnote = ''; 

        $('#summary-poc-natinstsruments-chart').highcharts({
            
            chart: {
                type: 'bar',
                style: {fontFamily: 'calibri'},
            },
            title: {
                text: firstChartTitle,
                style:{fontWeight:'bold',fontSize:'17px'}
                
            },
            subtitle: {
                text: firstChartSubTitle,
                style: {
                    fontSize:'15px'
                }
            },
            xAxis: {
                type: 'category',
                title: {text: 'Nationals and Populations of Concern',style: {fontSize:'16px',fontWeight:'bold'}},
                
                
         labels:{style: {fontSize:'13px'}}
            },
            yAxis: {
                max:3,
                min: 1,
                title: {
                    text: footnote,
                    align:'left'
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
           },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                         enabled: true,
                    }
                }
            },
            tooltip: {
                pointFormat: 'Rating: <b>{point.y:.2f}</b><br/>'
            },
            credits: {
                enabled: false
            },
            
            series: [{
                name: 'Populations of Concern',
                data: [ <%=NatInstrumentsCharts%> ]
            
            }],

        });
}); 



//Highchart configuration for the Obstacles section of the Analytics page. 

$(function () {
    
    //Configuration of the POC obstacles summary chart. 
    
    
        var firstChartTitle = 'Obstacles Populations of Concern Face in Being Able to Enjoy Their Rights';
        var firstChartSubTitle = '(1=\"No or Minor Obstacles to Enjoying Their Rights\", 3=\"Determinative Obstacles to Enjoying Their Rights\")';
       /*  var footnote = '*Rating is based on a scale from 1 to 3 where 1 indicates persons of concern face no obstacles to enjoying their '+ 
                'rights and 3 indicates they face significant obstacles.'; */
        
        var footnote = ''; 

        $('#summary-poc-obstacles-chart').highcharts({
            
            
            chart: {
                type: 'bar',
                style: {fontFamily: 'calibri'},
            },
            title: {
                text: firstChartTitle,
                style:{fontWeight:'bold',fontSize:'17px'}
                
            },
            subtitle: {
                text: firstChartSubTitle,
                style: {
                    fontSize:'15px'
                }
            },
            xAxis: {
                type: 'category',
                title: {text: 'Populations of Concern',style: {fontSize:'16px',fontWeight:'bold'}},
                
                
         labels:{style: {fontSize:'13px'}}
            },
            yAxis: {
                max:3,
                min: 1,
                title: {
                    text: footnote,
                    align:'left'
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
           },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                         enabled: true,
                    }
                }
            },
            tooltip: {
                pointFormat: 'Rating: <b>{point.y:.2f}</b><br/>'
            },
            credits: {
                enabled: false
            },
            
            series: [{
                name: 'Populations of Concern',
                data: [ <%=POCObstaclesCharts%> ]
            
            }],

        });
}); 


$(function () {
    
    //Configuration of the POC obstacles detail chart. 
    
    
        var firstChartTitle = 'The Obstacles Preventing Populations of Concern From Enjoying Their Rights';
        var firstChartSubTitle = '(1=\"The Obstacles Do Not, or Minimally, Prevent Populations of Concern From Enjoying Their Rights\"' +
                         ',<br>3=\"The Obstacles Determinatively Prevent Populations of Concern From Enjoying Their Rights\")';
        /* var footnote = '*Rating is based on a scale from 1 to 3 where 1 indicates that the obstacle does not ' + 
                       'prevent persons of concern from enjoying their rights and 3 indicates the obstacle does so significantly.'; */
        var footnote = '';
        $('#detail-poc-obstacles-chart').highcharts({
            
            
            chart: {
                type: 'bar',
                style: {fontFamily: 'calibri'}
            },
            title: {
                text: firstChartTitle,
                style:{fontWeight:'bold',fontSize:'17px'}
                
            } ,
            subtitle: {
                text: firstChartSubTitle,
                style: {
                    fontSize:'15px'
                }
            },
            xAxis: {
                type: 'category',
                title: {text: 'Obstacles',style: {fontSize:'16px',fontWeight:'bold'}},
                
         labels:{style: {fontSize:'13px'}}
            },
            yAxis: {
                max:3,
                min: 1,
                title: {
                    text: footnote,
                    align:'left'
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
           },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                         enabled: true,
                    }
                }
            },
            tooltip: {
                pointFormat: 'Rating: <b>{point.y:.2f}</b><br/>'
            },
            credits: {
                enabled: false
            },
            
            series: [{
                name: 'Populations of Concern',
                data: [ <%=POCRatingObstaclesCharts%>   ]
            }],

        });
});

$(function () {
    
    //Configuration of the rights group obstacles detail chart. 
    
    
        var firstChartTitle = 'The Accessibility of Rights to Populations of Concern';
        var firstChartSubTitle = '(1="There Are No, or Minimal, Obstacles to Enjoying the Rights", 3=" There Are Determinative Obstacles to Enjoying the Rights")';
        /* var footnote = '*Rating is based on a scale from 1 to 3 where 1 indicates there are no obstacles to enjoying the rights and' + 
                       '3 indicates that there are significant obstacles.'; */
        var footnote = '';
        
        
        $('#detail-poc-obstacles-rightgroups-chart').highcharts({
            chart: {
                type: 'bar',
                style: {fontFamily: 'calibri'},
            },
            title: {
                text: firstChartTitle,
                style:{fontWeight:'bold',fontSize:'17px'}
                
            },
            subtitle: {
                text: firstChartSubTitle,
                style: {fontSize:'15px'}
            },
            xAxis: {
                type: 'category',
                title: {text: 'Rights Categories',style: {fontSize:'16px',fontWeight:'bold'}},
         labels:{style: {fontSize:'13px'}}
            },
            yAxis: {
                max:3,
                min: 1,
                title: {
                    text: footnote,
                    align:'left'
                }
            },
            legend: {
                enabled: false
            },
            exporting: {
                enabled: false
           },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                         enabled: true,
                    }
                }
            },
            tooltip: {
                pointFormat: '<span class="boldbluetext">Rating</span>: <b>{point.y:.2f}</b><br/>'
            },
            credits: {
                enabled: false
            },
            
            series: [{
                name: 'Populations of Concern',
                data: [ <%=POCRightsGroupsObstaclesCharts%>   ]
            }],

        });
        
       
}); 


</script>



<!-- Footer -->
<%@include file="/footer.jsp"%>
<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@include file="/header.jsp"%>
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

      String nameOfCurrentFile = this.getClass().getSimpleName();

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

      
   %>

   <div id="content-container">
      <div id="content-title">
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>

      <div id="content-onecolumn">
         <h2>C. IDENTIFYING PRIORITIES</h2>         
         
         <h3>C.1 Analytics</h3>

        
         <!-- Summary Findings -->
         <div id='findingstablediv'>
            <%=obstaclesFindingsTable%>
         </div>      

         
         <div id="detail-consistency-chart"> </div>
         <div id="belowchart-missingdata"><%=legalFrameworkConsistencyMissingData%> </div>
         <div class="showdatadetailsdiv" >        
            <button class="showdatadetails"  onclick='toggledatatable("detail-consistency-table")'>Show/Hide Additional Data on the Above Chart</button>          
         </div>  
         <div id='detail-consistency-table'><%=legalFrameworkConsistentWithInternational%></div>            


         <div id="summary-poc-natinstsruments-chart"> 
         </div>
        <div id="belowchart-missingdata"><%=NatInstrumentsMissingData%> </div>        
         <div >        
               <button class="showdatadetails"  onclick='toggledatatable("detail-poc-natinstsruments-table")'>Show/Hide Additional Data on the Above Chart</button>          
         </div> 
         <div id='detail-poc-natinstsruments-table'>
            <%=NatInstrumentsTable%>
         </div>

         <div id="summary-poc-obstacles-chart"> 
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> 
         </div>        
         
         
         <div id="detail-poc-obstacles-chart">  
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> </div>        
         <div >        
               <button class="showdatadetails"  onclick='toggledatatable("detail-poc-obstacles-table")'>Show/Hide Additional Data on the Above Chart</button>          
         </div> 
         <div id='detail-poc-obstacles-table'>
            <%=POCRatingObstaclesTable%>
         </div>
         
         
         
         <div id="detail-poc-obstacles-rightgroups-chart">  
         </div>
         <div id="belowchart-missingdata"><%=obstaclesMissingData%> 
         </div>        
         <div >        
               <button class="showdatadetails"  onclick='toggledatatable("detail-poc-obstacles-rightgroups-table")'>Show/Hide Additional Data on the Above Chart</button>          
         </div> 
         <div id='detail-poc-obstacles-rightgroups-table'>
            <%=POCRightsGroupsObstaclesTable%>
         </div>
         </div>
         
         <br><br><br><br>
      </div>
   </div>
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
                title: {text: 'Rights Categories',style: {fontSize:'16px',fontWeight:'bold'}},
	      labels:{style: {fontSize:'13px'}}
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

<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>
<%@include file="/footer.jsp"%>


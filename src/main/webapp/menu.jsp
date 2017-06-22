<%@page import="model.*"%>
<%
   Country countryObj = (Country) request.getAttribute("countryObj");
   String countryName = countryObj.getCountryName();
   String countryNameForMenu = countryName.replaceAll("'", "%27").replaceAll("\"", "%22").replaceAll("&", "%26"); 
   String actionMenu = String.valueOf(request.getParameter("action"));
   String anchor = request.getParameter("anchor"); 
%>

<div id="left-col">
   <div id="left-index-logo">
      <a href="/LegalMappingTool_Dev/BaseController?action=home"><img src="./images/unhcr_logo.jpg"></a>
   </div>
   <div id='left-index'>
      <ul>
         <!-- Overview page menu items. -->
         <li id='menu-country-overview'><a href='/LegalMappingTool_Dev/BaseController?action=countryhome&country=<%=countryNameForMenu%>'>Overview of the Tool</a></li>

         <!--Section A: The Legal Protection Framework. -->
 
         <li id='menu-country-datacollection-2'><a href='/LegalMappingTool_Dev/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>'> A. The Legal Protection Framework</a></li>
         <li class="submenu" id='menu-country-datacollection-2_1'><a href="#A1">A.1 The Country's Constitution</a></li>
         <li class="submenu" id='menu-country-datacollection-2_1_a'><a href='/LegalMappingTool_Dev/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>&anchor=%23A1'>A.1 The Country's Constitution</a></li>
         <li class="submenu" id='menu-country-datacollection-2_2'><a href="#A2">A.2 The Country's Legal System</a></li>
         <li class="submenu" id='menu-country-datacollection-2_2_a'><a href='/LegalMappingTool_Dev/DataCollectionController?action=datacollection_a&country=<%=countryNameForMenu%>&anchor=%23A2'>A.2 The Country's Legal System</a></li>
         <li class="submenu" id='menu-country-datacollection-3'><a href='/LegalMappingTool_Dev/DataCollectionController?action=datacollection2&country=<%=countryNameForMenu%>'>A.3 Rights Protection</a></li>
         
         <!--Section B: Obstacles to Enjoying Rights -->
         <li id='menu-country-datacollection-4'><a href='/LegalMappingTool_Dev/DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>'> B. Obstacles to Enjoying Rights</a></li>
                
         <!--Section C: Areas for Improvement-->
         <li id='menu-country-analytical-3'><a href='/LegalMappingTool_Dev/AnalyticalToolController?action=analytics&country=<%=countryNameForMenu%>'> C. Identifying Priorities</a></li>
          <li class="submenu" id='menu-country-analytical-5'><a href='/LegalMappingTool_Dev/AnalyticalToolController?action=analytics&country=<%=countryNameForMenu%>'>C.1 Analytics</a></li>
         <li class="submenu" id='menu-country-analytical-6_a'><a href='/LegalMappingTool_Dev/AnalyticalToolController?action=narrative&country=<%=countryNameForMenu%>&anchor=%23B'>C.2 Narrative Analysis</a></li>

         <!--Section D: Reporting-->
         <li id='menu-country-reporting'><a href='/LegalMappingTool_Dev/ReportingController?action=reporting&country=<%=countryNameForMenu%>'> D. Create Reports</a></li>


      </ul>
   </div>
   
</div>

<script>
      
$(document).ready(function(){

    
	$(window).load(function(){
	  var page = '<%=actionMenu%>';
	  var anchor = '<%=anchor%>';
            if (page == 'countryhome') {
                /* Country home page. */
               $("#dataright").show();
               $("#datadown").hide();
               $("#analright").show();
               $("#analdown").hide();
               
                $("#menu-country-overview").addClass("visited-menu");
                $("#menu-home").hide();
                $("#menu-admin").hide();

                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_2_a").hide();
                $("#menu-country-datacollection-3").hide();

                $("#menu-country-analytical-5").hide(); 
                $("#menu-country-analytical-6_a").hide(); 


            } else if (page == 'datacollection') {
                /* Section B: The Legal Protection Framework */
                $("#dataright").hide();
                $("#datadown").show();
                $("#analright").show();
                $("#analdown").hide();
                
                $("#menu-home").hide();
                $("#menu-admin").hide();
                $("#menu-country-datacollection-2").addClass("visited-menu");
                $("#menu-country-datacollection-2_1_a").addClass("visited-submenu");

                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_2").hide();
                
                $("#menu-country-analytical-5").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                
      	      /* if(!(anchor === 'null')) {
                  $(document).scrollTop($(anchor).offset().top );  
                } */
      	      
            } else if (page == 'datacollection_a') {
                /* Section B.2 */
                $("#dataright").hide();
                $("#datadown").show();
                $("#analright").show();
                $("#analdown").hide();
                
                $("#menu-home").hide();
                $("#menu-admin").hide();
                $("#menu-country-datacollection-2").addClass("visited-menu");
                $("#menu-country-datacollection-2_2_a").addClass("visited-submenu");

                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_2").hide();

                $("#menu-country-analytical-5").hide(); 
                $("#menu-country-analytical-6_a").hide(); 
                
      	      /* if(!(anchor === 'null')) {
                  $(document).scrollTop($(anchor).offset().top );  
                } */
      	      
            }else if (page == 'datacollection2') {
                /* Section B.3 */
	     $("#dataright").hide();
               $("#datadown").show();
               $("#analright").show();
               $("#analdown").hide();
                
                $("#menu-home").hide();
                $("#menu-admin").hide();
                $("#menu-country-datacollection-2").addClass("visited-menu");
                $("#menu-country-datacollection-3").addClass("visited-submenu");


                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_2").hide();

                $("#menu-country-analytical-5").hide(); 
                $("#menu-country-analytical-6_a").hide(); 
                
                
            } else if (page == 'datacollection3') {
                /* Third Data Collection page. (Section C) */
	     $("#dataright").hide();
               $("#datadown").show();
               $("#analright").show();
               $("#analdown").hide();
               
                $("#menu-home").hide();
                $("#menu-admin").hide();
                $("#menu-country-datacollection-4").addClass("visited-menu");
  
                
                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_2_a").hide();
                $("#menu-country-datacollection-3").hide();
                
                $("#menu-country-analytical-5").hide(); 
                $("#menu-country-analytical-6_a").hide(); 
                
            } else if (page == 'analytics') {
                /* Section D Identifying Priorities, Analytics. */
                /* $("#dataright").show();
                $("#datadown").hide();
                $("#analright").hide();
                $("#analdown").show(); */
                
                 $("#menu-home").hide();
                 $("#menu-admin").hide();

                 
                 $("#menu-country-datacollection-2_1").hide();
                 $("#menu-country-datacollection-2_2").hide();
                 $("#menu-country-datacollection-2_1_a").hide();
                 $("#menu-country-datacollection-2_2_a").hide(); 
                 $("#menu-country-datacollection-3").hide(); 
                 
                 $("#menu-country-analytical-3").addClass("visited-menu");
                 $("#menu-country-analytical-5").addClass("visited-submenu");

       	      /* if(!(anchor === 'null')) {
                   $(document).scrollTop($(anchor).offset().top );  
               } */
             } else if (page == 'narrative') {
                /* Section D Identifying Priorities, Narrative. */
               /* $("#dataright").show();
               $("#datadown").hide();
               $("#analright").hide();
               $("#analdown").show(); */
               
                $("#menu-home").hide();
                $("#menu-admin").hide();

               
                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2_a").hide(); 
                $("#menu-country-datacollection-3").hide(); 
                
                $("#menu-country-analytical-3").addClass("visited-menu");
                $("#menu-country-analytical-6_a").addClass("visited-submenu");

                
      	      /* if(!(anchor === 'null')) {
                  $(document).scrollTop($(anchor).offset().top );  
              } */
            } else if (page == 'reporting') {
                 /* Section D Reporting. */

                  $("#menu-home").hide();
                  $("#menu-admin").hide();

                 
                  $("#menu-country-datacollection-2_1").hide();
                  $("#menu-country-datacollection-2_2").hide();
                  $("#menu-country-datacollection-2_1_a").hide();
                  $("#menu-country-datacollection-2_2_a").hide(); 
                  $("#menu-country-datacollection-3").hide(); 
                  
                  $("#menu-country-analytical-5").hide(); 
                  $("#menu-country-analytical-6_a").hide(); 
                  
                  $("#menu-country-reporting").addClass("visited-menu");

                  

              } 
                     
        });
    });

//Make sure that the menu is not fixed when the user scrolls to the right. 
$(document).scroll(function(e) {
    $('#left-index').css({
        'left': 1- $(document).scrollLeft()
    });
});

</script>
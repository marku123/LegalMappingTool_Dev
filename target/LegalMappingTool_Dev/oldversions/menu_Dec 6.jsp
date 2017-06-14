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
      <a href="/LegalMappingTool/BaseController?action=home"><img src="./images/unhcr_logo.jpg"></a>
   </div>
   <div id='left-index'>
      <ul>
         <!-- Overview page menu items. -->
         <li id='menu-country-overview'><a href='/LegalMappingTool/BaseController?action=countryhome&country=<%=countryNameForMenu%>'>A. Overview of the Tool</a></li>

         <!--Section A: Priorities of Population of Concern. -->
         <%--<li id='menu-country-datacollection'><label id='dataright'>&#9658;</label><label id='datadown'>&#9660;</label> <a href='/LegalMappingTool/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>'> Data Collection Tool</a></li>--%>         
         
         <%-- <li id='menu-country-datacollection-5'><a href='/LegalMappingTool/DataCollectionController?action=datacollection4&country=<%=countryNameForMenu%>'> A. Priorities of Populations of Concern</a></li> --%>
         
          <!--Section B: The Legal Protection Framework. -->
 
         <li id='menu-country-datacollection-2'><a href='/LegalMappingTool/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>'> B. The Legal Protection Framework</a></li>
         <li class="submenu" id='menu-country-datacollection-2_1'><a href="#A1">B.1 The Country's Constitution</a></li>
         <li class="submenu" id='menu-country-datacollection-2_1_a'><a href='/LegalMappingTool/DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>&anchor=%23A1'>B.1 The Country's Constitution</a></li>
         <li class="submenu" id='menu-country-datacollection-2_2'><a href="#A2">B.2 The Country's Legal System</a></li>
         <li class="submenu" id='menu-country-datacollection-2_2_a'><a href='/LegalMappingTool/DataCollectionController?action=datacollection_a&country=<%=countryNameForMenu%>&anchor=%23A2'>B.2 The Country's Legal System</a></li>
         <li class="submenu" id='menu-country-datacollection-3'><a href='/LegalMappingTool/DataCollectionController?action=datacollection2&country=<%=countryNameForMenu%>'>B.3 Rights Protection</a></li>
         
         <!--Section C: Obstacles to Enjoying Rights -->
         <li id='menu-country-datacollection-4'><a href='/LegalMappingTool/DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>'> C. Obstacles to Enjoying Rights</a></li>
         <li class="submenu" id='menu-country-datacollection-4_1'><a href="#C1">C.1 List of Obstacles</a></li>
         <li class="submenu" id='menu-country-datacollection-4_1a'><a href='/LegalMappingTool/DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>&anchor=%23C1'>C.1 List of Obstacles</a></li>
          <li class="submenu" id='menu-country-datacollection-4_2'><a href="#C2">C.2 Obstacle Analysis</a></li>
         <li class="submenu" id='menu-country-datacollection-4_2a'><a href='/LegalMappingTool/DataCollectionController?action=datacollection3&country=<%=countryNameForMenu%>&anchor=%23C2'>C.2 Obstacle Analysis</a></li>
                 
         <!--Section D: Areas for Improvement-->
         <li id='menu-country-analytical-3'><a href='/LegalMappingTool/AnalyticalToolController?action=narrative&country=<%=countryNameForMenu%>'> D. Identifying Priorities</a></li>
         <li class="submenu" id='menu-country-analytical-6'><a href="#B">D.1 Areas for Law and Policy Reform</a></li>
         <li class="submenu" id='menu-country-analytical-6_a'><a href='/LegalMappingTool/AnalyticalToolController?action=narrative&country=<%=countryNameForMenu%>&anchor=%23B'>D.1 Areas for Law and Policy Reform</a></li>
         <li class="submenu" id='menu-country-analytical-8'><a href="#D">D.2 Priority Areas for Complementary Programming</a></li>
         <li class="submenu" id='menu-country-analytical-8_a'><a href='/LegalMappingTool/AnalyticalToolController?action=narrative&country=<%=countryNameForMenu%>&anchor=%23D'>D.2 Priority Areas for Complementary Programming</a></li>
      </ul>
   </div>
   
</div>

<script>
      
$(document).ready(function(){
    
          /*  $("#dataright").click(function(){
               $('#left-col').css('height',$('#left-col').height()+100); 

               $("#datadown").show();
               $("#dataright").hide();

               $("#menu-country-datacollection-2").show();
               $("#menu-country-datacollection-3").show();
               $("#menu-country-datacollection-4").show();
               $("#menu-country-datacollection-5").show();
               
               $("#menu-country-datacollection-2_1").hide();
               $("#menu-country-datacollection-2_2").hide();
               $("#menu-country-datacollection-2_1_a").show();
               $("#menu-country-datacollection-2_2_a").show(); 

               $("#menu-country-datacollection-5_1").hide();
               $("#menu-country-datacollection-5_2").hide();
               $("#menu-country-datacollection-5_3").hide();
               $("#menu-country-datacollection-5_4").hide();
               $("#menu-country-datacollection-5_5").hide();
               $("#menu-country-datacollection-5_1_a").show();
               $("#menu-country-datacollection-5_2_a").show();
               $("#menu-country-datacollection-5_3_a").show();
               $("#menu-country-datacollection-5_4_a").show();
               $("#menu-country-datacollection-5_5_a").show(); 
           });
    
           $("#datadown").click(function(){
               $("#dataright").show();
               $("#datadown").hide();

               $("#menu-country-datacollection-2").hide();
               $("#menu-country-datacollection-3").hide();
               $("#menu-country-datacollection-4").hide();
               $("#menu-country-datacollection-5").hide();

               $("#menu-country-datacollection-2_1").hide();
               $("#menu-country-datacollection-2_1_a").hide();
               $("#menu-country-datacollection-2_2").hide();
               $("#menu-country-datacollection-2_2_a").hide();
              
               $("#menu-country-datacollection-5_1").hide();
               $("#menu-country-datacollection-5_2").hide();
               $("#menu-country-datacollection-5_3").hide();
               $("#menu-country-datacollection-5_4").hide();
               $("#menu-country-datacollection-5_5").hide();
               $("#menu-country-datacollection-5_1_a").hide();
               $("#menu-country-datacollection-5_2_a").hide();
               $("#menu-country-datacollection-5_3_a").hide();
               $("#menu-country-datacollection-5_4_a").hide();
               $("#menu-country-datacollection-5_5_a").hide(); 
           });
           
           $("#analright").click(function(){
                $('#left-col').css('height',$('#left-col').height()+100); 
               
               $("#analdown").show();
               $("#analright").hide();
               $("#menu-country-analytical-2").show();
               $("#menu-country-analytical-3").show();
               $("#menu-country-analytical-4").show();
               $("#menu-country-analytical-5").show();
               $("#menu-country-analytical-6").show();
               $("#menu-country-analytical-7").show();
               $("#menu-country-analytical-8").show(); 
               
               $("#menu-country-analytical-4").hide();
               $("#menu-country-analytical-5").hide();
               $("#menu-country-analytical-6").hide();
               $("#menu-country-analytical-7").hide();
               $("#menu-country-analytical-8").hide(); 
               
               $("#menu-country-analytical-4").hide();
               $("#menu-country-analytical-5").hide();
               $("#menu-country-analytical-6").hide();
               $("#menu-country-analytical-7").hide();
               $("#menu-country-analytical-8").hide(); 
               
               $("#menu-country-analytical-4_a").show();
               $("#menu-country-analytical-5_a").show();
               $("#menu-country-analytical-6_a").show();
               $("#menu-country-analytical-7_a").show();
               $("#menu-country-analytical-8_a").show(); 

           });
    
           $("#analdown").click(function(){
               $("#analright").show();
               $("#analdown").hide();
               $("#menu-country-analytical-2").hide();
               $("#menu-country-analytical-3").hide();
               $("#menu-country-analytical-4").hide();
               $("#menu-country-analytical-5").hide();
               $("#menu-country-analytical-6").hide();
               $("#menu-country-analytical-7").hide();
               $("#menu-country-analytical-8").hide();
               
               $("#menu-country-analytical-4_a").hide();
               $("#menu-country-analytical-5_a").hide();
               $("#menu-country-analytical-6_a").hide();
               $("#menu-country-analytical-7_a").hide();
               $("#menu-country-analytical-8_a").hide(); 

           }); */
    
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

                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_1a").hide();

                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_2a").hide();

                $("#menu-country-analytical-6").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                $("#menu-country-analytical-8").hide();
                $("#menu-country-analytical-8_a").hide();


            } else if (page == 'datacollection') {
                /* First Data Collection page. (Section A) */
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

                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_1a").hide();

                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_2a").hide();


                $("#menu-country-analytical-6").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                $("#menu-country-analytical-8").hide();
                $("#menu-country-analytical-8_a").hide();

                
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

                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_1a").hide();

                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_2a").hide();


                $("#menu-country-analytical-6").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                $("#menu-country-analytical-8").hide();
                $("#menu-country-analytical-8_a").hide();
                
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

                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_1a").hide();

                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_2a").hide();

                $("#menu-country-analytical-6").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                $("#menu-country-analytical-8").hide();
                $("#menu-country-analytical-8_a").hide();
                
                
            } else if (page == 'datacollection3') {
                /* Third Data Collection page. (Section C) */
	     $("#dataright").hide();
               $("#datadown").show();
               $("#analright").show();
               $("#analdown").hide();
               
                $("#menu-home").hide();
                $("#menu-admin").hide();
                $("#menu-country-datacollection-4").addClass("visited-menu");
                $("#menu-country-datacollection-4_1").addClass("visited-submenu");

                $("#menu-country-datacollection-4_1a").hide();
                $("#menu-country-datacollection-4_2a").hide();
                
                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_2_a").hide();
                $("#menu-country-datacollection-3").hide();
                
                $("#menu-country-analytical-6").hide();
                $("#menu-country-analytical-8").hide(); 
                $("#menu-country-analytical-6_a").hide();
                $("#menu-country-analytical-8_a").hide(); 
                
            } else if (page == 'datacollection4') {

                
                $("#menu-home").hide();
                $("#menu-admin").hide();
                
                $("#menu-country-datacollection-5").addClass("visited-menu");

                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_2_a").hide();
                $("#menu-country-datacollection-3").hide();

                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_1a").hide();

                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_2a").hide();

                $("#menu-country-analytical-6").hide(); 
                $("#menu-country-analytical-6_a").hide(); 

                $("#menu-country-analytical-8").hide();
                $("#menu-country-analytical-8_a").hide();

                
	      if(!(anchor === 'null')) {
                    $(document).scrollTop($(anchor).offset().top );  
                }
                
            } else if (page == 'narrative') {
                /* Section D Areas for Improvement. */
               /* $("#dataright").show();
               $("#datadown").hide();
               $("#analright").hide();
               $("#analdown").show(); */
               
                $("#menu-home").hide();
                $("#menu-admin").hide();

                $("#menu-country-analytical-3").addClass("visited-menu");
                
                $("#menu-country-datacollection-2_1").hide();
                $("#menu-country-datacollection-2_2").hide();
                $("#menu-country-datacollection-2_1_a").hide();
                $("#menu-country-datacollection-2_2_a").hide(); 
                $("#menu-country-datacollection-3").hide(); 

                
                $("#menu-country-datacollection-4_1").hide();
                $("#menu-country-datacollection-4_2").hide();
                $("#menu-country-datacollection-4_1a").hide();
                $("#menu-country-datacollection-4_2a").hide(); 
                

                $("#menu-country-analytical-6_a").hide();
                $("#menu-country-analytical-8_a").hide(); 
                
      	      if(!(anchor === 'null')) {
                  $(document).scrollTop($(anchor).offset().top );  
              }
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
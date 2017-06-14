<%
   String actionMenu = String.valueOf(request.getParameter("action"));
%>

<div id="left-col">
   <div id="left-index-logo">
      <a href="/LegalMappingTool_Dev/BaseController?action=home"><img src="./images/unhcr_logo.jpg"></a>
   </div>
   <div id='left-index'>
      <ul>
         <li id='menu-home'><a href='/LegalMappingTool_Dev/BaseController?action=home'>Home</a></li>
         <li id='menu-admin'><a href='/LegalMappingTool_Dev/BaseController?action=admin'>Administration</a></li>
      </ul>
   </div>
</div>

<script>
      
$(document).ready(function(){
	$(window).load(function(){
	    var page = '<%=actionMenu%>';
            if (page == 'admin') {
                /* Admin home page. */
                $("#menu-admin").addClass("visited-menu");
                $("#menu-country-overview").hide();
                $("#menu-country-datacollection").hide();
                $("#menu-country-datacollection-2").hide();
                $("#menu-country-datacollection-3").hide();
                $("#menu-country-datacollection-4").hide();
                $("#menu-country-analytical").hide();
                $("#menu-country-analytical-2").hide();
                $("#menu-country-analytical-3").hide();
                $("#menu-country-analytical-4").hide();
                $("#menu-country-analytical-5").hide();
                $("#menu-country-analytical-6").hide();
            } else {
                /* Global home page. */
                $("#menu-home").addClass("visited-menu");
                $("#menu-country-overview").hide();
                $("#menu-country-datacollection").hide();
                $("#menu-country-datacollection-2").hide();
                $("#menu-country-datacollection-3").hide();
                $("#menu-country-datacollection-4").hide();
                $("#menu-country-analytical").hide();
                $("#menu-country-analytical-2").hide();
                $("#menu-country-analytical-3").hide();
                $("#menu-country-analytical-4").hide();
                $("#menu-country-analytical-5").hide();
                $("#menu-country-analytical-6").hide();
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
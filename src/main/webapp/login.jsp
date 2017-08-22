<%@page import="dbhelper.home.*"%>
<%@include file="./header.jsp"%>
<link href="./css/pages/login/login.css" rel="stylesheet" type="text/css">
<script src="./js/pages/login.js"></script>

<div id="content-menu-container">

   <%
   	Boolean loginattempted = (Boolean) request.getAttribute("loginattempted");
   %>


   <div id="left-col">
   </div>

   <div id="content-container">


      <div id="logindiv">


         <div id="content-onecolumn">

            <div id="content-title">
               <p>Log in to the Legal Mapping Tool</p>
            </div>
            <form method="post" action="Login">
               <input type="text" name="user" placeholder="User ID" /><br> <br> <input type="password" name="pass" placeholder="Password" /><br>
               <br> <input type="submit" name="loginbutton" value="Login" /> <input type="hidden" name="loginattempted"
                  value="<%=loginattempted%>" />
               <p class="tryagainmessage">
                  Your log in attempt was unsuccessful. Please try again. <br><br>If you are having difficulty signing in to the Legal Mapping Tool contact the
                  Comprehensive Solutions Unit at <a href="mailto:ComprehensiveSolutions@unhcr.org">ComprehensiveSolutions@unhcr.org</a>.
               </p>
            </form>

         </div>
         <div id="unhcr-logo">
            <img src="./images/unhcr_logo.jpg">
         </div>

      </div>

   </div>
</div>

<%@include file="./footer.jsp"%>
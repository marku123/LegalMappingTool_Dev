<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@page import="access.*"%>
<%@include file="/header.jsp"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/dot-luv/jquery-ui.css">
<link href="./css/pages/datacollection/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="./css/pages/datacollection/datacollection.css" rel="stylesheet" type="text/css">
<script src="./js/pages/datacollection/datacollection.js"></script>

<div id="content-menu-container" >

   <%@include file="/menu.jsp"%>

   <%
   
   //Authenticate the user.
   Boolean authenticatedToEd = Authentication.AuthenticateUser(request,response);
   
      String nameOfCurrentFile = this.getClass().getSimpleName();

      String countryPOCs = countryObj.getPOCCountry();
      String countryPOCsComments = countryObj.getPOCCommentsCountry();

      //A2 Intro
      String[] POCs = FormatingUtilities.setCheckedBoxes(countryPOCs, "Asylum Seekers,Internally Displaced Persons,Refugees,Returnees,Stateless Persons");
      String[] commonCivilPlural = FormatingUtilities.setCheckedRadioButtons(countryObj.getCommonCivilPlural(), "common,civil,plural");
      String pluralText = countryObj.getPluralText();
      String[] federalState = FormatingUtilities.setCheckedRadioButtons(countryObj.getFederalState(), "federal,state");

      //A2 Judicial, Admin, Traditional
      String judicialEntitiesTable = FormatDataColA.formatJudicalEntities(countryObj);
      String adminEntitiesTable = FormatDataColA.formatAdminEntities(countryObj);
      String tradMechTable = FormatDataColA.formatTradMechanisms(countryObj);
      String tradmechcomments = countryObj.getTradMechComments();

      
      //A2 Comments
      String comments = countryObj.getComments();

   	%>

   <div id="content-container">
      <div id="content-title" >
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>

      <div id="content-onecolumn" >

         <form action="DataCollectionController?action=datacollection&country=<%=countryNameForMenu%>" method="POST">
            <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
            <!-- Note About that the Data Collection section has to be complete.-->

            
            <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Legal Mapping Tool, should ensure that all data fields are completed as accurately as possible.</p>
            </div>
            <h3 id="A2">A.1 Overview of the Country's Legal System and Population Groups</h3>
                  
                  <p>1. Population groups that are in the country (select all that apply):</p>
                  
                     <div class='populationgroups'>
                        <input type='checkbox' name='populationgroupschecked[]' value='Asylum Seekers' <%=POCs[0]%>>Asylum Seekers <br>
                        <input type='checkbox' name='populationgroupschecked[]' value='Internally Displaced Persons' <%=POCs[1]%> >Internally Displaced Persons<br>
                        <input type='checkbox' name='populationgroupschecked[]' value='Refugees' <%=POCs[2]%>>Refugees<br>
                        <input type='checkbox' name='populationgroupschecked[]' value='Returnees'  <%=POCs[3]%>>Returnees<br> 
                        <input type='checkbox' name='populationgroupschecked[]' value='Stateless Persons' <%=POCs[4]%> >Stateless Persons<br>
                        <input type='checkbox' name='populationgroupscheckedselectall' >Select All<br>
                     </div>
                     <div id="pocincountrycomments">
                        <p>Comments:
                        <br> 
                        <textarea class="pocincountrycomments" name='populationgroupscomments'><%=countryPOCsComments%></textarea>
                        </p>  
                     </div>
                  <p>
                     2. Type of legal system:&nbsp;<input type="radio" name="commoncivilplural" value="common" <%=commonCivilPlural[0]%>>Common 
                     <input type="radio" name="commoncivilplural" value="civil" <%=commonCivilPlural[1]%>>Civil
                     <input type="radio" name="commoncivilplural" value="plural" <%=commonCivilPlural[2]%>>Plural
                     <br><br>
                  </p>
                     
                     <div id="commoncivilpluraltext">
                  <p>
                     &emsp;&emsp;Description of the Pluralistic System: <br>
                     &emsp;&emsp;<textarea class="commoncivilpluraltext" name="commoncivilpluraltext"><%=pluralText%></textarea>                      
                     <br><br>
                  </p>
                     </div> 
                  <p>
                     3. Political and Administrative Structure:&nbsp;
                     <input type="radio" name="federalstate" value="federal" <%=federalState[0]%>>Federal (Decentralised) 
                     <input type="radio" name="federalstate" value="state" <%=federalState[1]%>>Unitary State (Centralised)
                     <br><br>            
                  
                     4. Judicial entities/courts:
                     <br>  <br>
                  </p>
                        <%=judicialEntitiesTable%>  
                  <br>
                  <div class="addrowbutton">              
                     <input onclick="judicialAddRow();" type="button" value="Add Another Judicial Entity/Court" />
                  </div> 
                 <br>  
                 <p>                   
                      5. Administrative entities:
                     <br> <br> 
                  </p>
                        <%=adminEntitiesTable%>  
                  <br>
                  <div class="addrowbutton">              
                     <input onclick="adminAddRow();" type="button" value="Add Another Administrative Entity" />
                  </div>                
                  <br>
                  
                  <p>                     
                     6. Traditional mechanisms:
                     <br> <br> 
                  </p>
                        <%=tradMechTable%>  
                  <br>
                  <div class="addrowbutton">              
                     <input onclick="tradAddRow();" type="button" value="Add Another Traditional Mechanism" />
                  </div>                 
                  <br> <br> 
                  <div id="tradmechcomments">
                 
                     <p>                     
                       Please describe the relationship between any traditional mechanisms and the formal legal system:
                        <br> 
                       <textarea class="tradmechcomments" name="tradmechcomments"><%=tradmechcomments%></textarea>
                     </p>  
                  </div>
               <br><br><br>  
               <p>                     
                    7. Additional comments on the formal legal system:
                     <br> <br> 
                     <textarea class="legalsystemcomments" name="legalsystemcomments"><%=comments%></textarea>
               </p>  
               <br><br>  
               <input type='hidden' name='countrypocs' value='<%=countryPOCs%>'>           
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
               </div>
               <br><br>  
         </form>
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
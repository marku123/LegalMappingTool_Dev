<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
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
      String nameOfCurrentFile = this.getClass().getSimpleName();

      //A2 Intro
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

         <form action="DataCollectionController?action=datacollection_a&country=<%=countryNameForMenu%>" method="POST">
            <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
            <!-- Note About that the Data Collection section has to be complete.-->

            
            <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Legal Mapping Tool, should ensure that all data fields are completed as accurately as possible.</p>
            </div>
            <h3 id="A2">A.2 The Country's Legal System</h3>
                  
                  <p>
                     1. Type of legal system:&nbsp;<input type="radio" name="commoncivilplural" value="common" <%=commonCivilPlural[0]%>>Common 
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
                     2. Political and Administrative Structure:&nbsp;
                     <input type="radio" name="federalstate" value="federal" <%=federalState[0]%>>Federal (Decentralised) 
                     <input type="radio" name="federalstate" value="state" <%=federalState[1]%>>Unitary State (Centralised)
                     <br><br>            
                  
                     3. Judicial entities/courts:
                     <br>  <br>
                  </p>
                     <table id="judicialTable" class="A2Tables">
                     <thead>
                        <tr>
                           <th class='col1'>Judicial Entity/Court</th>
                           <th class='col2'>Refugees Can Formally Access the Entity/Court</th>
                           <th class='col3'>IDPs Can Formally Access the Entity/Court</th>
                           <th class='col4'>Returnees Can Formally Access the Entity/Court</th>
                           <th class='col5'>Stateless Persons Can Formally Access the Entity/Court</th>
                           <th class='col6'>Asylum Seekers Can Formally Access the Entity/Court</th>                           
                        </tr>
                     </thead>
                     <tbody id="judicialTableAddRow">
                        <%=judicialEntitiesTable%>  
                     </tbody>
                  </table>
                  <br>
                  <div class="addrowbutton">              
                     <input onclick="judicialAddRow();" type="button" value="Add Another Judicial Entity/Court" />
                  </div> 
                 <br>  
                 <p>                   
                      4. Administrative entities:
                     <br> <br> 
                  </p>
                     <table id="adminTable" class="A2Tables">
                     <thead>
                        <tr>
                           <th class='col1'>Administrative Entities</th>
                           <th class='col2'>Refugees Can Formally Access the Entity</th>
                           <th class='col3'>IDPs Can Formally Access the Entity</th>
                           <th class='col4'>Returnees Can Formally Access the Entity</th>
                           <th class='col5'>Stateless Persons Can Formally Access the Entity</th>
                           <th class='col6'>Asylum Seekers Can Formally Access the Entity</th>                           
                        </tr>
                     </thead>
                     <tbody id="administrativeTableAddRow">
                        <%=adminEntitiesTable%>  
                     </tbody>
                  </table>
                  <br>
                  <div class="addrowbutton">              
                     <input onclick="adminAddRow();" type="button" value="Add Another Administrative Entity" />
                  </div>                
                  <br>
                  
                  <p>                     
                     5. Traditional mechanisms:
                     <br> <br> 
                  </p>
                     <table id="tradTable" class="A2Tables">
                     <thead>
                        <tr>
                           <th class='col1'>Traditional Mechanisms</th>
                           <th class='col2'>Refugees Can Formally Access the Entity</th>
                           <th class='col3'>IDPs Can Formally Access the Entity</th>
                           <th class='col4'>Returnees Can Formally Access the Entity</th>
                           <th class='col5'>Stateless Persons Can Formally Access the Entity</th>
                           <th class='col6'>Asylum Seekers Can Formally Access the Entity</th>                           
                        </tr>
                     </thead>
                     <tbody id="traditionalTableAddRow">
                        <%=tradMechTable%>  
                     </tbody>
                  </table>
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
                    6. Additional comments on the formal legal system:
                     <br> <br> 
                     <textarea class="legalsystemcomments" name="legalsystemcomments"><%=comments%></textarea>
               </p>  
               <br><br>                                   
               <div class="savebuttonandtooltip">
                  <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
               </div>
               <br><br>  
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
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
      String[] constYesNoChecked = FormatingUtilities.setCheckedRadioButtons(countryObj.getConstitutionYesNo(), "yes,no");
      String constEffectDate = countryObj.getConstitutionDate();
      String[] constAmendYesNoChecked = FormatingUtilities.setCheckedRadioButtons(countryObj.getConstitutionAmendYesNo(), "yes,no");
      String constAmendDate = countryObj.getConstitutionAmendDate();
      String constLinkFrenEng = countryObj.getConstLinkFrenEng();
      String constLinkOrig = countryObj.getConstLinkOrig();
      String billLinkFrenEng = countryObj.getBillLinkFrenEng();
      String billLinkOrig = countryObj.getBillLinkOrig();
      
      //Applicability of Constitution to POCs
      String constAppToPoC[][] = countryObj.getConstAppToPoC();
      String[] POC1Checked = FormatingUtilities.setCheckedRadioButtons(constAppToPoC[0][0], "yes,partially,no,unclear");
      String[] POC2Checked = FormatingUtilities.setCheckedRadioButtons(constAppToPoC[1][0], "yes,partially,no,unclear");
      String[] POC3Checked = FormatingUtilities.setCheckedRadioButtons(constAppToPoC[2][0], "yes,partially,no,unclear");
      String[] POC4Checked = FormatingUtilities.setCheckedRadioButtons(constAppToPoC[3][0], "yes,partially,no,unclear");
      String[] POC5Checked = FormatingUtilities.setCheckedRadioButtons(constAppToPoC[4][0], "yes,partially,no,unclear");     
   
      String POC1Comment = constAppToPoC[0][1];
      String POC2Comment = constAppToPoC[1][1];
      String POC3Comment = constAppToPoC[2][1];
      String POC4Comment = constAppToPoC[3][1];
      String POC5Comment = constAppToPoC[4][1];

      //Guaranteeing of rights in the constitution table.
   //   String guaranteeConstRightsTable = FormatDataColA.formatRightConstTable(countryObj);
      
      //A2 Intro
      String[] commonCivilPlural = FormatingUtilities.setCheckedRadioButtons(countryObj.getCommonCivilPlural(), "common,civil,plural");
      String pluralText = countryObj.getPluralText();
      String[] federalState = FormatingUtilities.setCheckedRadioButtons(countryObj.getFederalState(), "federal,state");

      //A2 Judicial, Admin, Traditional
      String judicialEntitiesTable = FormatDataColA.formatJudicalEntities(countryObj);
      String adminEntitiesTable = FormatDataColA.formatAdminEntities(countryObj);
      String tradMechTable = FormatDataColA.formatTradMechanisms(countryObj);
     
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
            <h2>DATA COLLECTION TOOL</h2>
            <!-- Note About that the Data Collection section has to be complete.-->

            <h3  >A. Overview of the Legal Framework of the Country</h3>
            
            <div id='dataaccuracyreminder'> 
               <p>Note: In order to allow for the effective analysis of a country's operational context, users that are responsible for 
               inputting data into the Data Collection Tool, should ensure that all data fields are completed as accurately as possible.</p>
            </div>
            <h4 id="A1">A.1 The Country's Constitution</h4>

               <p>
                  1. Country has a constitution:<input type="radio" name="constitutionyesno" value="yes" <%=constYesNoChecked[0]%>> Yes 
                  <input type="radio" name="constitutionyesno" value="no" <%=constYesNoChecked[1]%>>No
                  <br><br> 
               </p>
               <div id="constyes">
               <p>
                  
                  2. Date the constitution came into effect:&emsp;
                  <input type="text" id="constdateeffect" name="constdateeffect" size="9" value="<%=constEffectDate%>" readonly>
                  <br> <br>
                  
               <p>
                  3. The constitution has been amended:<input type="radio" name="constamendyesno" value="yes" <%=constAmendYesNoChecked[0]%>> Yes 
                  <input type="radio" name="constamendyesno" value="no" <%=constAmendYesNoChecked[1]%>>No
                  <br><br> 
               </p>
                  <div id="constamenddate">
                     &emsp;Date the constitution was last amended:&emsp;
                     <input type="text" id="constdateeffectamend" name="constdateeffectamend" size="9" value="<%=constAmendDate%>" readonly>
                     <br> <br>
                  </div>
               
                  4. Link to the constitution in <a href="http://www.refworld.org/" target="_blank">Refworld</a>:<br><br>

                  &emsp;French or English:
                  &emsp;<input type="text" name="constlinkfreneng" size="25" value="<%=constLinkFrenEng%>">
                  &emsp;<a class="refworldlink" href="<%=constLinkFrenEng%>"  target="_blank">Visit Link</a> 
                  <br> <br>
                  &emsp;Original Language (If Other Than French or English):
                  &emsp;<input type="text" name="constlinkorig" size="25" value="<%=constLinkOrig%>">
                  &emsp;<a class="refworldlink" href="<%=constLinkOrig%>" target="_blank">Visit Link</a>
                  <br><br>
                  
                  5. Link to the Bill of Rights in Refworld:<br><br>

                  &emsp;French or English:
                  &emsp;<input type="text" name="billlinkfreneng" size="25" value="<%=billLinkFrenEng%>">
                  &emsp;<a class="refworldlink" href="<%=billLinkFrenEng%>" target="_blank">Visit Link</a> 
                  <br> <br>
                  &emsp;Original Language (If Other Than French or English):
                  &emsp;<input type="text" name="billlinkorig" size="25" value="<%=billLinkOrig%>">
                  &emsp;<a class="refworldlink" href="<%=billLinkOrig%>" target="_blank">Visit Link</a>
                  <br><br>
                  
                  6. Application of rights in the constitution to Populations of Concern:<br><br>
                                               

                 <table class="constpersons">
                     <thead>
                        <tr>
                           <th class='col1'>Populations of Concern</th>
                           <th class='col2'>Rights in the Constitution Apply to Populations of Concern</th>
                           <th class='col3'>Comments</th>

                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>Internally Displaced Persons <input type="hidden" name="POC1" value="POC1"></td>
                           <td>
	                          <input type="radio" name="constitutionPOC1" value="yes" <%=POC1Checked[0]%>> Yes <br>
                              <input type="radio" name="constitutionPOC1" value="partially" <%=POC1Checked[1]%>> Partially <br>
                              <input type="radio" name="constitutionPOC1" value="no" <%=POC1Checked[2]%>> No <br>
                              <input type="radio" name="constitutionPOC1" value="unclear" <%=POC1Checked[3]%>> Unclear <br>
                           </td>
                           <td><textarea class="constpersonstable" name="constitutionPOC1Comment"><%=POC1Comment%></textarea></td>
                        </tr>
                        <tr>
                           <td>Refugees <input type="hidden" name="POC2" value="POC2"></td>
                           <td>
	                          <input type="radio" name="constitutionPOC2" value="yes" <%=POC2Checked[0]%>> Yes <br>
                              <input type="radio" name="constitutionPOC2" value="partially" <%=POC2Checked[1]%>> Partially <br>
                              <input type="radio" name="constitutionPOC2" value="no" <%=POC2Checked[2]%>> No <br>
                              <input type="radio" name="constitutionPOC2" value="unclear" <%=POC2Checked[3]%>> Unclear <br>
                           </td>
                           <td><textarea class="constpersonstable" name="constitutionPOC2Comment"><%=POC2Comment%></textarea></td>
                        </tr>
                        <tr>
                           <td>Asylum Seekers <input type="hidden" name="POC3" value="POC3"></td>
                           <td>
	                          <input type="radio" name="constitutionPOC3" value="yes" <%=POC3Checked[0]%>> Yes <br>
                              <input type="radio" name="constitutionPOC3" value="partially" <%=POC3Checked[1]%>> Partially <br>
                              <input type="radio" name="constitutionPOC3" value="no" <%=POC3Checked[2]%>> No <br>
                              <input type="radio" name="constitutionPOC3" value="unclear" <%=POC3Checked[3]%>> Unclear <br>
                           </td>
                           <td><textarea class="constpersonstable" name="constitutionPOC3Comment"><%=POC3Comment%></textarea></td>
                        </tr>
                        <tr>
                           <td>Stateless Persons <input type="hidden" name="POC4" value="POC4"></td>
                           <td>
	                          <input type="radio" name="constitutionPOC4" value="yes" <%=POC4Checked[0]%>> Yes <br>
                              <input type="radio" name="constitutionPOC4" value="partially" <%=POC4Checked[1]%>> Partially <br>
                              <input type="radio" name="constitutionPOC4" value="no" <%=POC4Checked[2]%>> No <br>
                              <input type="radio" name="constitutionPOC4" value="unclear" <%=POC4Checked[3]%>> Unclear <br>
                           </td>
                           <td><textarea class="constpersonstable" name="constitutionPOC4Comment"><%=POC4Comment%></textarea></td>
                        </tr>
                        <tr>
                           <td>Returnees <input type="hidden" name="POC5" value="POC5"></td>
                           <td>
	                          <input type="radio" name="constitutionPOC5" value="yes" <%=POC5Checked[0]%>> Yes <br>
                              <input type="radio" name="constitutionPOC5" value="partially" <%=POC5Checked[1]%>> Partially <br>
                              <input type="radio" name="constitutionPOC5" value="no" <%=POC5Checked[2]%>> No <br>
                              <input type="radio" name="constitutionPOC5" value="unclear" <%=POC5Checked[3]%>> Unclear <br>
                           </td>
                           <td><textarea class="constpersonstable" name="constitutionPOC5Comment"><%=POC5Comment%></textarea></td>
                        </tr>
                     </tbody>
                  </table>
                  <br><br>
                  <p>
                     7. Guaranteeing of rights in the constitution:<br><br>
                  </p>
                  <table class="constrightsguar">
                     <thead>
                        <tr>
                           <th class='col1'>Name of Right</th>
                           <th class='col2'>Degree to Which the Right is Guaranteed</th>
                           <th class='col3'>Reservations in the Constitution (If Applicable)</th>
                           <th class='col4'>Groups Explicitly Excluded from Being Able to Enjoy the Right</th>
                        </tr>
                     </thead>
                     <tbody>
<%--                        <%=guaranteeConstRightsTable%>                                     --%>
                     </tbody>
                  </table>
                  </div>
                  <br><br>
                  
                  <div class="savebuttonandtooltip">
                     <input class="savebutton" type="submit" name="savedata" value="Save Changes" /><span class="tooltiptext">All changes on page will be saved.</span>
                  </div>                                   
                  <br> <br>
                  <h4 id="A2">A.2 The Country's Legal System</h4>
                  
                  <p>
                     8. Type of legal system:&nbsp;<input type="radio" name="commoncivilplural" value="common" <%=commonCivilPlural[0]%>>Common 
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
                     9. Political and Administrative Structure:&nbsp;
                     <input type="radio" name="federalstate" value="federal" <%=federalState[0]%>>Federal (Decentralised) 
                     <input type="radio" name="federalstate" value="state" <%=federalState[1]%>>Unitary State (Centralised)
                     <br><br>
                  
                     <%-- 10. Monist/Dualist system:&nbsp;
                     <input type="radio" name="monistdualist" value="monist" <%=monistDualist[0]%>>Monist 
                     <input type="radio" name="monistdualist" value="dualist" <%=monistDualist[1]%>>Dualist
                     <input type="radio" name="monistdualist" value="mixed" <%=monistDualist[2]%>>Mixed (Monist-Dualist)
                     <br><br>    --%>          
                  
                     11. Judicial entities/courts:
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
                      12. Administrative entities:
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
                     13. Traditional mechanisms:
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
                 
                 
                  <br>  
               <p>                     
                    14. Additional comments on the formal legal system:
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
 
<%@include file="/footer.jsp"%>
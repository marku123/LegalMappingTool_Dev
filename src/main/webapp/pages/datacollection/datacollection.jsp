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
<script src="./js/pages/datacollection/jspdf.min.js"></script>




<div id="content-menu-container" >

   <%@include file="/menu.jsp"%>

   <%
      String nameOfCurrentFile = this.getClass().getSimpleName();
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
            <h3 id="A1">A.1 The Country's Constitution</h3>

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
                  </div>
                  
                  <br><br>



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
 
 <!-- Footer -->
<%@include file="/footer.jsp"%>
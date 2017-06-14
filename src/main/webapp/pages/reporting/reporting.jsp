<%@page import="model.*"%>
<%@page import="htmlformat.*"%>
<%@page import="java.util.*"%>
<%@include file="/header.jsp"%>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/dot-luv/jquery-ui.css">
<link href="./css/pages/reporting/jquery-ui-1.11.4.custom/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="./css/pages/reporting/reporting.css" rel="stylesheet" type="text/css">
<script src="./js/pages/reporting/reporting.js"></script>
<script src="./js/pages/reporting/jspdf.min.js"></script>

<div id="content-menu-container" >
   <%@include file="/menu.jsp"%>

   <%
      String nameOfCurrentFile = this.getClass().getSimpleName();

      String constYesNo = countryObj.getConstitutionYesNo().substring(0, 1).toUpperCase() + countryObj.getConstitutionYesNo().substring(1);
      String constEffectDate = countryObj.getConstitutionDate();
      String constAmendYesNo = countryObj.getConstitutionAmendYesNo().substring(0, 1).toUpperCase() + countryObj.getConstitutionAmendYesNo().substring(1);
      String constAmendDate = countryObj.getConstitutionAmendDate();
      String constLinkFrenEng = countryObj.getConstLinkFrenEng();
      String constLinkOrig = countryObj.getConstLinkOrig();
      String billLinkFrenEng = countryObj.getBillLinkFrenEng();
      String billLinkOrig = countryObj.getBillLinkOrig();
      
      
      //Applicability of Constitution to POCs Table
      String constAppToPoC[][] = countryObj.getConstAppToPoC();
      String POC1Checked = constAppToPoC[0][0].substring(0, 1).toUpperCase() + constAppToPoC[0][0].substring(1);
      String POC2Checked = constAppToPoC[1][0].substring(0, 1).toUpperCase() + constAppToPoC[1][0].substring(1);
      String POC3Checked = constAppToPoC[2][0].substring(0, 1).toUpperCase() + constAppToPoC[2][0].substring(1);
      String POC4Checked = constAppToPoC[3][0].substring(0, 1).toUpperCase() + constAppToPoC[3][0].substring(1);
      String POC5Checked = constAppToPoC[4][0].substring(0, 1).toUpperCase() + constAppToPoC[4][0].substring(1);
   
      POC1Checked = POC1Checked.replace("Null", "");
      POC2Checked = POC2Checked.replace("Null", "");
      POC3Checked = POC3Checked.replace("Null", "");
      POC4Checked = POC4Checked.replace("Null", "");
      POC5Checked = POC5Checked.replace("Null", "");
      
      String POC1Comment = constAppToPoC[0][1].replace("null", "");
      String POC2Comment = constAppToPoC[1][1].replace("null", "");
      String POC3Comment = constAppToPoC[2][1].replace("null", "");
      String POC4Comment = constAppToPoC[3][1].replace("null", "");
      String POC5Comment = constAppToPoC[4][1].replace("null", "");
      
      
   %>
   <div id="content-container">

      <div id="content-title" >
         <h1>
            Legal Mapping Tool for <%=countryName%>
         </h1>
      </div>
      
      <div id="content-onecolumn" >
         <h2>D. REPORTING</h2>
         <br><br> 
    
         <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
      
         <h3>A.1 The Country's Constitution</h3>
      
            1. The country has a constitution: <%=constYesNo%>
            <br><br> 
            2. Date the constitution came into effect: <%=constEffectDate%>                  
            <br><br> 
         
            3. The constitution has been amended: <%=constAmendYesNo%>
            <br><br> 

             &emsp;The date the constitution was last amended: <%=constAmendDate%>
             <br> <br>
         
            4. Link to the constitution in Refworld:<br><br>
   
               &emsp;French or English:<a class="refworldlink" href="<%=constLinkFrenEng%>"  target="_blank"><%=constLinkFrenEng%></a> 
               <br> <br>
               &emsp;Original Language (If Other Than French or English): <a class="refworldlink" href="<%=constLinkOrig%>" target="_blank"><%=constLinkOrig%></a>
               <br> <br>
            
            5. Link to the Bill of Rights in Refworld:<br><br>

               &emsp;French or English: <a class="refworldlink" href="<%=billLinkFrenEng%>" target="_blank"><%=billLinkFrenEng%></a> 
               <br> <br>
               &emsp;Original Language (If Other Than French or English): <a class="refworldlink" href="<%=billLinkOrig%>" target="_blank"><%=billLinkOrig%></a>
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
                           <td> <%=POC1Checked%>
                           </td>
                           <td><%=POC1Comment%></td>
                        </tr>
                        <tr>
                           <td>Refugees <input type="hidden" name="POC2" value="POC2"></td>
                           <td><%=POC2Checked%> 
                           </td>
                           <td><%=POC2Comment%></td>
                        </tr>
                        <tr>
                           <td>Asylum Seekers <input type="hidden" name="POC3" value="POC3"></td>
                           <td><%=POC3Checked%>
                           </td>
                           <td><%=POC3Comment%></td>
                        </tr>
                        <tr>
                           <td>Stateless Persons <input type="hidden" name="POC4" value="POC4"></td>
                           <td><%=POC4Checked%>
                           </td>
                           <td><%=POC4Comment%></td>
                        </tr>
                        <tr>
                           <td>Returnees <input type="hidden" name="POC5" value="POC5"></td>
                           <td><%=POC5Checked%>
                           </td>
                           <td><%=POC5Comment%></td>
                        </tr>
                     </tbody>
                  </table>
               
         <br><br>
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
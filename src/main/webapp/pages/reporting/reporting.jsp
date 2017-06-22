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

<script  type="text/javascript" src="http://www.cloudformatter.com/Resources/Pages/CSS2Pdf/Script/xepOnline.jqPlugin.js"></script>

<script type="text/javascript">

function genPDF(){
    alert('test2');

    xepOnline.Formatter.Format('content-onecolumn',{pageWidth:'216mm', pageHeight:'279mm'});
    //xepOnline.Formatter.Format('content-onecolumn',{render:'download'},{pageWidth:"11in", pageHeight:"8in"});

   // var doc = new jsPDF("l", "pt", "letter");
    //var doc = new jsPDF("l", "pt", "letter");

    alert('test2');
    //doc.fromHTML($('#content-title').get(0),20,20,{'width':500});
   // doc.fromHTML($('#content-onecolumn').get(0),20,20,{'width':500});

   // doc.fromHTML($('#content-onecolumn').get(0));

    //doc.save('test.pdf');

    
   /*  html2canvas(document.getElementById('content-onecolumn'), {
        onrendered: function(canvas) {
            var img = canvas.toDataURL();
            var doc = new jsPDF();
            doc.addImage(img,'JPEG',20,20);
            doc.save('test.pdf');
        }
    }); */

}

</script>

<div id="content-menu-container" >
   <%@include file="/menu.jsp"%>

   <%
      String nameOfCurrentFile = this.getClass().getSimpleName();

      String constYesNo = countryObj.getConstitutionYesNo();
      String constEffectDate = countryObj.getConstitutionDate();
      String constAmendYesNo = countryObj.getConstitutionAmendYesNo();
      String constAmendDate = countryObj.getConstitutionAmendDate();
      String constLinkFrenEng = countryObj.getConstLinkFrenEng();
      String constLinkOrig = countryObj.getConstLinkOrig();
      String billLinkFrenEng = countryObj.getBillLinkFrenEng();
      String billLinkOrig = countryObj.getBillLinkOrig();
      
      
      //Applicability of Constitution to POCs Table
      String constAppToPoC[][] = countryObj.getConstAppToPoC();
      String POC1Checked = constAppToPoC[0][0].replace("null", "");
      String POC2Checked = constAppToPoC[1][0].replace("null", "");
      String POC3Checked = constAppToPoC[2][0].replace("null", "");
      String POC4Checked = constAppToPoC[3][0].replace("null", "");
      String POC5Checked = constAppToPoC[4][0].replace("null", "");
     
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
               <h2>D. CREATE REPORTS</h2>
         <br><br>
      <div id="content-onecolumn">
      
      
<header style="display:none; margin-bottom:50px;">
    <table width="100%">
        <tr>
            <td>Legal Mapping Tool</td>
            <td style="text-align:right"><%=countryName%></td>
        </tr>
    </table>
</header>
      

   
         <h2>A. THE LEGAL PROTECTION FRAMEWORK</h2>
      
         <h3>A.1 The Country's Constitution</h3>

          
            1. The country has a constitution: <%=constYesNo%>
            <br><br> 
            
<p> 
            2.  Date the constitution came into effect: <%=constEffectDate%>                       
              <br><br>     
</p>                              
<p>              
            3. The constitution has been amended: <%=constAmendYesNo%>
            <br><br>  
</p>
<p>
             &emsp;The date the constitution was last amended: <%=constAmendDate%>
             <br> <br>
 </p>
 <p>
         
            4. Link to the constitution in Refworld:<br><br>
 </p> <p>
               &emsp;French or English:<a class="refworldlink" href="<%=constLinkFrenEng%>"  target="_blank"><%=constLinkFrenEng%></a> 
             <br> <br>
 </p> <p>
             
               &emsp;Original Language (If Other Than French or English): <a class="refworldlink" href="<%=constLinkOrig%>" target="_blank"><%=constLinkOrig%></a>
               <br> <br>
 </p> <p>
             
            5. Link to the Bill of Rights in Refworld:<br><br>
 </p> <p>

               &emsp;French or English: <a class="refworldlink" href="<%=billLinkFrenEng%>" target="_blank"><%=billLinkFrenEng%></a> 
               <br> <br>
 </p> <p>

               &emsp;Original Language (If Other Than French or English): <a class="refworldlink" href="<%=billLinkOrig%>" target="_blank"><%=billLinkOrig%></a>
               <br><br>
 </p> <p>
               
            6. Application of rights in the constitution to Populations of Concern:<br><br>
 </p> 
            <table class="constpersons">
                     <thead>
                        <tr>
                           <th>Populations of Concern</th>
                           <th>Rights in the Constitution Apply to Populations of Concern</th>
                           <th>Comments</th>

                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>Internally Displaced Persons </td>
                           <td> <%=POC1Checked%>
                           </td>
                           <td><%=POC1Comment%></td>
                        </tr>
                        <tr>
                           <td>Refugees </td>
                           <td><%=POC2Checked%> 
                           </td>
                           <td><%=POC2Comment%></td>
                        </tr>
                        <tr>
                           <td>Asylum Seekers </td>
                           <td><%=POC3Checked%>
                           </td>
                           <td><%=POC3Comment%></td>
                        </tr>
                        <tr>
                           <td>Stateless Persons </td>
                           <td><%=POC4Checked%>
                           </td>
                           <td><%=POC4Comment%></td>
                        </tr>
                        <tr>
                           <td>Returnees </td>
                           <td><%=POC5Checked%>
                           </td>
                           <td><%=POC5Comment%></td>
                        </tr>
                     </tbody>
                  </table>
         <br><br>
         
         
                     <table >
                     <thead>
                        <tr>
                           <th >Populations of Concern</th>
                           <th >Rights in the Constitution Apply <br>to Populations of Concern</th>
                           <th>Comments</th>

                        </tr>
                     </thead>
                     <tbody>
                        <tr>
                           <td>Internally Displaced Persons </td>
                           <td> <%=POC1Checked%>
                           </td>
                           <td><%=POC1Comment%></td>
                        </tr>
                        <tr>
                           <td>Refugees </td>
                           <td><%=POC2Checked%> 
                           </td>
                           <td><%=POC2Comment%></td>
                        </tr>
                        <tr>
                           <td>Asylum Seekers </td>
                           <td><%=POC3Checked%>
                           </td>
                           <td><%=POC3Comment%></td>
                        </tr>
                        <tr>
                           <td>Stateless Persons </td>
                           <td><%=POC4Checked%>
                           </td>
                           <td><%=POC4Comment%></td>
                        </tr>
                        <tr>
                           <td>Returnees </td>
                           <td><%=POC5Checked%>
                           </td>
                           <td><%=POC5Comment%></td>
                        </tr>
                     </tbody>
                  </table>
         <br><br>
            <footer style="display:none">
            <p class="foot">Page
            <pagenum/> of <totpages/></p>
            </footer>
           </div>              

<button style="height:30px" onclick="genPDF()">Generate Report</button>

      
   </div>

</div>


<!-- Feedback form -->
<%@include file="/feedback.jsp"%>
<input id="feedbackcountry" type="hidden" value="<%=countryNameForMenu%>">
<input id="feedbackfilename" type="hidden" value="<%=nameOfCurrentFile%>">
<script src="./js/pages/feedback.js"></script>
 
 <!-- Footer -->
<%@include file="/footer.jsp"%>
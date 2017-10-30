package htmlformat;


import dbhelper.dbutilities.RightsCategoriesManagement;
import model.Country;

public class FormatDataColBRepository {

	
	public static String formatIntlInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String IntlInstruments[][] = countryObj.getIntlInstruments();
		String rightCategoryAbbreviations = RightsCategoriesManagement.getRightCategoryAbbreviation("All");

        int i = 0;
		
        
        if (!(IntlInstruments.length == 0)){
        	if (IntlInstruments[0][0] != "") {
    		
        	
	        	//Create the expand and collapse buttons.
	        	htmlTable = htmlTable + "<div class='showhidediv'> \n "
						  + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllIntl()'> \n "
						  + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllIntl()'> \n "
						  + "</div> \n ";
	        	
	        	while(i<IntlInstruments.length) {

	        		//Format the drop down boxes.
	        		String[] typeofinstrument = FormatingUtilities.setSelectedDropDownOptions(IntlInstruments[i][1], "international,regional,bilateral");
					String[] ratified = FormatingUtilities.setSelectedDropDownOptions(IntlInstruments[i][6], "ratified,signed,notaparty,notgeo");

					String[] rightsCategoriesCheck =  FormatingUtilities.setCheckedBoxes(RightsCategoriesManagement.getRightCategoryAbbreviation(IntlInstruments[i][7]),rightCategoryAbbreviations);			

	
					//Create the HTML table.
					htmlTable = htmlTable + ""
						+ "<table id='intltable"+ i +"' class='interinstruments'> \n"
							+ "<thead  onclick=\"intltoggletablebody('intltable"+ i +"','intlinputtable"+ i +"')\"> \n "
								+ "<tr> \n "
									+ "<th class='tablehead' colspan='2'> \n "
									+ "<input class='inputinstrumenthead' id='intlinputtable"+ i +"' type='text' value='"+ IntlInstruments[i][0].replaceAll("'", "&#39;")  +"' onfocus='this.blur()' readonly> \n "
									+ "<span class='tooltiptext'>Click to Expand/Collapse</span> \n "
									+ "</th> \n "
								+ "</tr> \n"
							+ "</thead> \n "
							+ "<tbody class='intltablebody'> \n "
								+ "<tr> \n "
									+ "<td class='col1' >Name of the instrument:</td> \n "
									+ "<td id='tdhide1class"+ i +"' class='col2'> \n"
										+ "<input id='intlinputtable"+ i +"a' name='intlinstruname["+ i +"]' type='text' value='"+ IntlInstruments[i][0].replaceAll("'", "&#39;")  +"' size='90'  > \n "
									+ "</td> \n "
									+ "<td id='tdhide2class"+ i +"' class='col2'> \n"
										+ ""+ IntlInstruments[i][0].replaceAll("'", "&#39;")  +""
									+ "</td> \n "
								+ "</tr> \n "
									
								//Link to Refworld.
								+ "<tr class='trhideclass"+ i +"'> \n "
									+ "<td>Link to the instrument in Refworld:</td> \n "
									+ "<td> \n"
										+ "<input name='intlinstrureflink["+ i +"]' type='text' value='"+ IntlInstruments[i][2].replaceAll("'", "&#39;")  +"' size='65'>  \n"
										+ "<a class='refworldlink' href='"+ IntlInstruments[i][2] +"' target='_blank'>Visit Link</a> \n "
									+ "</td> \n "
								+ "</tr> \n "
								
								//Upload instrument.
								+ "<tr class='trhideclass"+ i +"'> \n "
									+ "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> \n "
									+ "<td> \n"
											+ "<a class='linkToUploadedFile' name='intlFileURLLink["+ i +"]' href='"+ IntlInstruments[i][5] +"' target='_blank'>"+ IntlInstruments[i][4] +"</a>\n"
											+ "<br><br><label class='browsefile' id='browseFileLabelIntl["+ i +"]'> \n"
												+ "<input id='uploadbuttonintl' name='browseButtonIntl["+ i +"]' type='file' /> \n "
												+ "Click to Browse for File \n"
											+ "</label>\n"
											+ "<input class='inputfilenameIntl' type='text' name='intlinstrurefupload["+ i +"]' />\n"
											+ "<button id='upLoadFileButtonIntl' name='uploadButtonIntl["+ i +"]' type='button' value='"+ i +"'>Upload File</button>\n"
											+ "<p class='uploadfilesuccess' id='uploadsuccessIntl["+ i +"]' ></p>\n"
											+ "<input type='hidden' name='intlFileStorageName["+ i +"]' value='"+ IntlInstruments[i][3] +"' />\n"
											+ "<input type='hidden' name='intlFileDisplayName["+ i +"]' value='"+ IntlInstruments[i][4] +"' />\n"
											+ "<input type='hidden' name='intlFileURL["+ i +"]' value='"+ IntlInstruments[i][5] +"' />\n"
									+ "</td> \n "
								+ "</tr> \n "
									 
								//Type of Instrument 
								+ "<tr> \n "
									+ "<td>Type of instrument:</td> \n "
									+ "<td id='tdhide1classtype"+ i +"'> \n "
										+ "<select name='intlinstrutype["+ i +"]'> \n "
												+ "<option value=''></option> \n "
												+ "<option value='international' "+ typeofinstrument[0] +">International </option> \n "
												+ "<option value='regional' "+ typeofinstrument[1] +">Regional </option> \n "
												+ "<option value='bilateral' "+ typeofinstrument[2] +">Bilateral</option> \n "
										+ "</select> \n "
									+ "</td> \n "
									+ "<td id='tdhide2classtype"+ i +"' class='col2'> "+ FormatingUtilities.formatInstrumentData(IntlInstruments[i][1])  +"</td> \n "	
								+ "</tr>"
									
								//Instrument Ratified
								+ "<tr> \n "
									+ "<td>The instrument has been ratified:</td> \n "
									+ "<td > \n "
										+ "<select class='.optionhideclass"+ i +"' name='intlinstruratified["+ i +"]'> \n "
												+ "<option value=''></option> \n "
												+ "<option value='ratified' "+ ratified[0] +">Ratified</option> \n "
												+ "<option value='signed' "+ ratified[1] +">Signed</option> \n "
												+ "<option value='notaparty' "+ ratified[2] +">Not a Party</option> \n "
												+ "<option  value='notgeo' "+ ratified[3] +">Not Geographically Applicable</option> \n "
										+ "</select> \n "										
									+ "</td> \n "
								+ "</tr>"
									
								//Rights Categories
								+ "<tr class='trhideclass"+ i +"'> \n "
									+ "<td>The instrument regulates or affects the following rights categories:</td> \n "
									+ "<td> \n "
										+ "<div class='otherlegischeckboxes1'> \n"
											+ "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='fair' "+ rightsCategoriesCheck[0] +">Access to Justice <br>\n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='docu'  "+ rightsCategoriesCheck[1] +">Documentation<br>  \n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='free' "+ rightsCategoriesCheck[4] +">Freedom of Movement <br>\n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='lib'  "+ rightsCategoriesCheck[7] +">Liberty and Security of Person<br> \n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='nondis'  "+ rightsCategoriesCheck[8] +">Non-Discrimination<br> \n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='poli'  "+ rightsCategoriesCheck[9] +">Political Participation <br> \n"										
											+ "<p class='otherlegisheaders'>Economic Rights Categories</p>"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='housing'  "+ rightsCategoriesCheck[6] +">Housing, Land and Property Rights<br>  \n"
										+ "</div> \n"
										+ "<div class='otherlegischeckboxes2'> \n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='work'  "+ rightsCategoriesCheck[10] +">Right to Work and Rights at Work<br> \n "
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='soc'  "+ rightsCategoriesCheck[11] +">Social Security<br>\n "
											+ "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='edu'  "+ rightsCategoriesCheck[2] +">Education<br>  \n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='fam' "+ rightsCategoriesCheck[3] +">Family Unity <br>\n"
												+ "<input type='checkbox' name='intlinstrrightschecked["+ i +"]' value='heal' "+ rightsCategoriesCheck[5] +">Health <br>\n"
										+ "</div> \n"
									+ "</td> \n "
								+ "</tr> \n "

							+ "</tbody> \n "		
						+ "</table> \n "
						+ "<input type='hidden' name='intlinstrnameOrig["+ i +"]' value='"+ IntlInstruments[i][0].replaceAll("'", "&#39;")  +"'> \n "
						+ "<input type='hidden' name='intlinstruratifiedOrig["+ i +"]' value='"+ IntlInstruments[i][6] +"'> \n "
						+ "<br><br> <br>\n ";		
					i++;
				}
	        }
        }
		return htmlTable; 
	}
	
	public static String formatConstitutionTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String NatlInstruments[][] = countryObj.getNatlInstruments();
		String countryName = countryObj.getCountryName();
		String rightCategoryAbbreviations = RightsCategoriesManagement.getRightCategoryAbbreviation("All");
		
        int i = 0;     

        if (NatlInstruments[0][0] != "No Data in DB") {
    		
        	
        	
        	//while(i<NatlInstruments.length) {
       		
        		//Format the drop down boxes.
				String[] allPart = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][5], "federal,state");
				
				String[] rightsCategoriesCheck =  FormatingUtilities.setCheckedBoxes(RightsCategoriesManagement.getRightCategoryAbbreviation(NatlInstruments[i][8]),rightCategoryAbbreviations);			
			
				//Create the HTML table.
				htmlTable = htmlTable + ""
					+ "<table id='natltable"+ i +"' class='constitutiontable'> \n"
						+ "<thead  onclick=\"natltoggletablebody('natltable"+ i +"','natlinputtable"+ i +"')\"> \n "
							+ "<tr> \n "
								+ "<th class='tablehead' colspan='2'> \n "
								+ "<input class='inputinstrumenthead' id='natlinputtable"+ i +"' type='text' value='Constitution of "+countryName+"' size='65' onfocus='this.blur()' readonly> \n "
								+ "<span class='tooltiptext'>Click to Expand/Collapse</span> \n "
								+ "</th> \n "
							+ "</tr> \n"
						+ "</thead> \n "

						+ "<tbody> \n "		

						    //Name of the instrument.
							+ "<tr> \n "
								+ "<td class='col1' >Official name of the constitution:</td> \n "
								+ "<td class='col2'> \n"
									+ "<input id='natlinputtable"+ i +"a' name='natlinstruname["+ i +"]' type='text' value='Constitution of "+countryName+"' size='65'> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							
							//Date the constitution came into effect.
							+ "<tr> \n "
								+ "<td>The date the constitution came into effect:</td> \n "
								+ "<td> \n"
									+ "<input type='text' id='constdateeffect' name='constdateeffect' size='9' value='' readonly> \n "
									+ "</td> \n "
							+ "</tr> \n "
							
							//Date the constitution was last amended.
							+ "<tr> \n "
								+ "<td>The date the constitution was last amended (if applicable):</td> \n "
								+ "<td> \n"
									+ "<input type='text' id='constdateeffectamend' name='constdateeffectamend' size='9' value='' readonly> \n "
									+ "</td> \n "
							+ "</tr> \n "
							
							//Link to constitution Refworld.
							+ "<tr> \n "
								+ "<td>Link to the constitution in Refworld:</td> \n "
								+ "<td> \n"
									+ "French or English Version of the Constitution: <br><br><input name='natlinstrureflink["+ i +"]' type='text' value='"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"' size='65'>  \n"
									+ "<a class='refworldlink' href='"+ NatlInstruments[i][1] +"' target='_blank'>Visit Link</a><br><br><br> \n "
									+ "Original Language of the Constitution (If Other Than French or English): <br><br><input name='natlinstrureflink["+ i +"]' type='text' value='"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"' size='65'>  \n"
									+ "<a class='refworldlink' href='"+ NatlInstruments[i][1] +"' target='_blank'>Visit Link</a> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Link to the Bill of Rights in Refworld.
							+ "<tr> \n "
								+ "<td>Link to the Bill of Rights in Refworld:</td> \n "
								+ "<td> \n"
									+ "French or English Version of the Bill of Rights: <br><br><input name='natlinstrureflink["+ i +"]' type='text' value='"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"' size='65'>  \n"
									+ "<a class='refworldlink' href='"+ NatlInstruments[i][1] +"' target='_blank'>Visit Link</a><br><br><br> \n "
									+ "Original Language of the Bill of Rights (If Other Than French or English): <br><br><input name='natlinstrureflink["+ i +"]' type='text' value='"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"' size='65'>  \n"
									+ "<a class='refworldlink' href='"+ NatlInstruments[i][1] +"' target='_blank'>Visit Link</a> \n "
								+ "</td> \n "
							+ "</tr> \n "
								
						    // Rights categories.

							+ "<tr> \n "
								+ "<td>The constitution regulates or affects the following rights categories:</td> \n "
									+ "<td> \n "
										+ "<div class='otherlegischeckboxes1'> \n"
											+ "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='fair' "+ rightsCategoriesCheck[0] +">Access to Justice <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='docu'  "+ rightsCategoriesCheck[1] +">Documentation<br>  \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='free' "+ rightsCategoriesCheck[4] +">Freedom of Movement <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='lib'  "+ rightsCategoriesCheck[7] +">Liberty and Security of Person<br> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='nondis'  "+ rightsCategoriesCheck[8] +">Non-Discrimination<br> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='poli'  "+ rightsCategoriesCheck[9] +">Political Participation <br> \n"										
											+ "<p class='otherlegisheaders'>Economic Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='housing'  "+ rightsCategoriesCheck[6] +">Housing, Land and Property Rights<br>  \n"
										+ "</div> \n"
										+ "<div class='otherlegischeckboxes2'> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='work'  "+ rightsCategoriesCheck[10] +">Right to Work and Rights at Work<br> \n "
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='soc'  "+ rightsCategoriesCheck[11] +">Social Security<br>\n "
											+ "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='edu'  "+ rightsCategoriesCheck[2] +">Education<br>  \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='fam' "+ rightsCategoriesCheck[3] +">Family Unity <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='heal' "+ rightsCategoriesCheck[5] +">Health <br>\n"
	
										+ "</div> \n"
									+ "</td> \n "
								+ "</tr> \n "
							
						
							//Comments on the instrument.
							+ "<tr> \n "
								+ "<td>Comments on the constitution:</td> \n "
								+ "<td> \n "
									+ "<textarea class='natcomments' name='natlinstrucomments["+ i +"]'>" + NatlInstruments[i][7] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n "

						+ "</tbody> \n "		
	
					+ "</table> \n "
					+ "<input type='hidden' name='natinstrnameOrig["+ i +"]' value='"+ NatlInstruments[i][0].replaceAll("'", "&#39;")  +"'> \n "
					+ "<br><br><br> \n ";		
				i++;
			//}
        }

		return htmlTable; 
	}
	
	public static String formatNationalInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String NatlInstruments[][] = countryObj.getNatlInstruments();
		String rightCategoryAbbreviations = RightsCategoriesManagement.getRightCategoryAbbreviation("All");
		
        int i = 0;     

        if (NatlInstruments[0][0] != "No Data in DB") {
    		
        	
        	//Create the expand and collapse buttons.
        	htmlTable = htmlTable + "<div class='showhidediv'> \n "
					  + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllNatl()'> \n "
					  + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllNatl()'> \n "
					  + "</div> \n ";
        	
        	while(i<NatlInstruments.length) {
       		
        		//Format the drop down boxes.
				String[] allPart = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][5], "federal,state");
				
				String[] rightsCategoriesCheck =  FormatingUtilities.setCheckedBoxes(RightsCategoriesManagement.getRightCategoryAbbreviation(NatlInstruments[i][8]),rightCategoryAbbreviations);			
			
				//Create the HTML table.
				htmlTable = htmlTable + ""
					+ "<table id='natltable"+ i +"' class='natinstruments'> \n"
						+ "<thead  onclick=\"natltoggletablebody('natltable"+ i +"','natlinputtable"+ i +"')\"> \n "
							+ "<tr> \n "
								+ "<th class='tablehead' colspan='2'> \n "
								+ "<input class='inputinstrumenthead' id='natlinputtable"+ i +"' type='text' value='"+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"' size='65' onfocus='this.blur()' readonly> \n "
								+ "<span class='tooltiptext'>Click to Expand/Collapse</span> \n "
								+ "</th> \n "
							+ "</tr> \n"
						+ "</thead> \n "

						+ "<tbody> \n "		

						    //Name of the instrument.
							+ "<tr> \n "
								+ "<td class='col1' >Name of the national instrument:</td> \n "
								+ "<td class='col2'> \n"
									+ "<input id='natlinputtable"+ i +"a' name='natlinstruname["+ i +"]' type='text' value='"+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"' size='65'> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Link to Refworld.
							+ "<tr> \n "
								+ "<td>Link to the instrument in Refworld:</td> \n "
								+ "<td> \n"
									+ "<input name='natlinstrureflink["+ i +"]' type='text' value='"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"' size='65'>  \n"
									+ "<a class='refworldlink' href='"+ NatlInstruments[i][1] +"' target='_blank'>Visit Link</a> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Upload instrument.
							+ "<tr> \n "
								+ "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> \n "
								+ "<td> \n"
										+ "<a class='linkToUploadedFile' name='natFileURLLink["+ i +"]' href='"+ NatlInstruments[i][4] +"' target='_blank'>"+ NatlInstruments[i][3] +"</a>\n"
										+ "<br><br><label class='browsefile' id='browseFileLabel["+ i +"]'> \n"
											+ "<input id='uploadbutton' name='browseButtonNat["+ i +"]' type='file' /> \n "
											+ "Click to Browse for File \n"
										+ "</label>\n"
										+ "<input class='inputfilename' type='text' name='natlinstrurefupload["+ i +"]' />\n"
										+ "<button id='upLoadFileButton' name='uploadButtonNat["+ i +"]' type='button' value='"+ i +"'>Upload File</button>\n"
										+ "<p class='uploadfilesuccess' id='uploadsuccess["+ i +"]' ></p>\n"
										+ "<input type='hidden' name='natFileStorageName["+ i +"]' value='"+ NatlInstruments[i][2] +"' />\n"
										+ "<input type='hidden' name='natFileDisplayName["+ i +"]' value='"+ NatlInstruments[i][3] +"' />\n"
										+ "<input type='hidden' name='natFileURL["+ i +"]' value='"+ NatlInstruments[i][4] +"' />\n"
								+ "</td> \n "
							+ "</tr> \n "
								 
							//Applicable in all or parts of the country.
							+ "<tr> \n "
								+ "<td>The instrument is applicable in all or parts of the country:</td> \n "
								+ "<td> \n "
									+ "<div id='instruconsistentcommdiv'> \n"									
									+ "<select class='consistentintlstandards' name='natlinstruapplicable["+ i +"]'> \n "
										+ "<option value=''></option> \n "
										+ "<option value='federal' "+ allPart[0]+">All</option> \n "
										+ "<option value='state' "+ allPart[1]+">Part(s)</option> \n "
									+ "</select> \n "
									+ "</div> \n"
									+ "<label id='instruconsistentlabel' class='instruconsistentlabel'>Explanation as to where it is applicable if 'Part(s)' is selected: </label>  \n"									
									+ "<textarea class='instruconsistentcomm' name='natlinstruapplicablecomm["+ i +"]'>" + NatlInstruments[i][6] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n"	
							
						    // Rights categories.

							+ "<tr> \n "
								+ "<td>The instrument regulates or affects the following rights categories:</td> \n "
									+ "<td> \n "
										+ "<div class='otherlegischeckboxes1'> \n"
											+ "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='fair' "+ rightsCategoriesCheck[0] +">Access to Justice <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='docu'  "+ rightsCategoriesCheck[1] +">Documentation<br>  \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='free' "+ rightsCategoriesCheck[4] +">Freedom of Movement <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='lib'  "+ rightsCategoriesCheck[7] +">Liberty and Security of Person<br> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='nondis'  "+ rightsCategoriesCheck[8] +">Non-Discrimination<br> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='poli'  "+ rightsCategoriesCheck[9] +">Political Participation <br> \n"										
											+ "<p class='otherlegisheaders'>Economic Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='housing'  "+ rightsCategoriesCheck[6] +">Housing, Land and Property Rights<br>  \n"
										+ "</div> \n"
										+ "<div class='otherlegischeckboxes2'> \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='work'  "+ rightsCategoriesCheck[10] +">Right to Work and Rights at Work<br> \n "
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='soc'  "+ rightsCategoriesCheck[11] +">Social Security<br>\n "
											+ "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='edu'  "+ rightsCategoriesCheck[2] +">Education<br>  \n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='fam' "+ rightsCategoriesCheck[3] +">Family Unity <br>\n"
												+ "<input type='checkbox' name='natinstrrightscategories["+ i +"]' value='heal' "+ rightsCategoriesCheck[5] +">Health <br>\n"
	
										+ "</div> \n"
									+ "</td> \n "
								+ "</tr> \n "
							
						
							//Comments on the instrument.
							+ "<tr> \n "
								+ "<td>Comments on the instrument:</td> \n "
								+ "<td> \n "
									+ "<textarea class='natcomments' name='natlinstrucomments["+ i +"]'>" + NatlInstruments[i][7] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n "

						+ "</tbody> \n "		
	
					+ "</table> \n "
					+ "<input type='hidden' name='natinstrnameOrig["+ i +"]' value='"+ NatlInstruments[i][0].replaceAll("'", "&#39;")  +"'> \n "
					+ "<br><br><br> \n ";		
				i++;
			}
        }

		return htmlTable; 
	}
	
	

	
	
}

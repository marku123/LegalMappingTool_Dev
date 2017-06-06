package htmlformat;

import org.apache.commons.lang3.StringEscapeUtils;

import model.Country;

public class FormatDataColB {

	public static String formatRightsGroupsDropDown(Country countryObj) {
		 
		String htmlDropDown = new String(""); 
		String rightsGroup = countryObj.getRightsGroup();
		String selectedOptionsDefault;
		String selectedOptions[];
		String dropDownOptions = ""
				+ "Documentation,"
				+ "Education,"
				+ "Fair Trial and Right to Redress,"
				+ "Family Unity,"
				+ "Freedom of Movement,"
				+ "Health,"
				+ StringEscapeUtils.escapeCsv("Housing, Land and Property") + ","
				+ "Liberty and Security of Person,"
				+ "Non-Discrimination,"
				+ "Political Participation,"			
				+ "Right to Work and Rights at Work,"
				+ "Social Security";
		
		//Format the options, ensure that the one that is selected is set to "selected". 
		selectedOptions = FormatingUtilities.setSelectedDropDownOptions(rightsGroup, dropDownOptions);


		//If none of the options have been select ensure a default one has been set. 
		if (rightsGroup == "") {
			selectedOptionsDefault = "selected";
		} else {
			selectedOptionsDefault = "";
		}
		
        //Create the HTML table.
        htmlDropDown = htmlDropDown + ""
				+ "<option "+selectedOptionsDefault +" disabled>Select a Rights Category</option> \n"
				+ "<optgroup label='Civil/Political'> \n"
					+ "<option " +selectedOptions[2]+ ">Fair Trial and Right to Redress</option> \n"
					+ "<option " +selectedOptions[4]+ ">Freedom of Movement</option> \n"
					+ "<option " +selectedOptions[7]+ ">Liberty and Security of Person</option> \n"
					+ "<option " +selectedOptions[8]+ ">Non-Discrimination</option> \n"
					+ "<option " +selectedOptions[9]+ ">Political Participation</option> \n"
				+ "</optgroup> \n"
				+ "<optgroup label='Economic'> \n"
					+ "<option " +selectedOptions[6]+ ">Housing, Land and Property</option> \n"
					+ "<option " +selectedOptions[10]+ ">Right to Work and Rights at Work</option> \n"			
					+ "<option " +selectedOptions[11]+ ">Social Security</option> \n"	
				+ "</optgroup> \n"
				+ "<optgroup label='Legal'> \n"
					+ "<option " +selectedOptions[0]+ ">Documentation</option> \n"
				+ "</optgroup> \n"
				+ "<optgroup label='Socio-cultural'> \n"
					+ "<option " +selectedOptions[1]+ ">Education</option> \n"
					+ "<option " +selectedOptions[3]+ ">Family Unity</option> \n"
					+ "<option " +selectedOptions[5]+ ">Health</option> \n"
				+ "</optgroup> \n";

		return htmlDropDown; 
	}

	
	
	
	
	
	public static String formatIntlInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String IntlInstruments[][] = countryObj.getIntlInstruments();
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
					String[] ratified = FormatingUtilities.setSelectedDropDownOptions(IntlInstruments[i][2], "ratified,signed,notaparty,notgeo");
					String[] reservations =  FormatingUtilities.setCheckedRadioButtons(IntlInstruments[i][4],"yes,no");			
			
	
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
									+ "<td class='col2'> \n"
										+ "<input id='intlinputtable"+ i +"a' name='intlinstruname["+ i +"]' type='text' value='"+ IntlInstruments[i][0].replaceAll("'", "&#39;")  +"' size='90'  > \n "
									+ "</td> \n "
								+ "</tr> \n "
								+ "<tr> \n "
									+ "<td>Type of instrument:</td> \n "
									+ "<td> \n "
										+ "<select name='intlinstrutype["+ i +"]'> \n "
												+ "<option value=''></option> \n "
												+ "<option value='international' "+ typeofinstrument[0] +">International </option> \n "
												+ "<option value='regional' "+ typeofinstrument[1] +">Regional </option> \n "
												+ "<option value='bilateral' "+ typeofinstrument[2] +">Bilateral</option> \n "
										+ "</select> \n "
									+ "</td> \n "
								+ "</tr>"
								+ "<tr> \n "
									+ "<td>The instrument has been ratified:</td> \n "
									+ "<td> \n "
										+ "<select name='intlinstruratified["+ i +"]'> \n "
												+ "<option value=''></option> \n "
												+ "<option value='ratified' "+ ratified[0] +">Ratified</option> \n "
												+ "<option value='signed' "+ ratified[1] +">Signed</option> \n "
												+ "<option value='notaparty' "+ ratified[2] +">Not a Party</option> \n "
												+ "<option value='notgeo' "+ ratified[3] +">Not Geographically Applicable</option> \n "
										+ "</select> \n "										
									+ "</td> \n "
								+ "</tr>"
								+ "<tr> \n "
									+ "<td>Articles from the instrument related to the rights:</td> \n "
									+ "<td> \n "
										+ "<textarea name='intlinstruarticles["+ i +"]'>" + IntlInstruments[i][3] + "</textarea> \n "
									+ "</td> \n "
								+ "</tr> \n "
								+ "<tr> \n "
									+ "<td>Reservations related to the right have been taken:</td> \n "
									+ "<td> \n "
										+ "<input type='radio' name='intlinstrures["+ i +"]' value='yes' "+ reservations[0] +">Yes \n "
										+ "<input type='radio' name='intlinstrures["+ i +"]' value='no'  "+ reservations[1] +">No \n "
									+ "</td> \n "
								+ "</tr> \n "
								+ "<tr> \n "
									+ "<td>Nature and scope of the reservations (if any):</td> \n "
									+ "<td> \n "
										+ "<textarea name='intlinstruresnature["+ i +"]'>" + IntlInstruments[i][5] + "</textarea> \n "
									+ "</td> \n "
								+ "</tr> \n "
							+ "</tbody> \n "		
						+ "</table> \n "
						+ "<input type='hidden' name='intlinstrID["+ i +"]' value='"+ IntlInstruments[i][6] +"'> \n "
						+ "<br><br> <br>\n ";		
					i++;
				}
	        }
        }
		return htmlTable; 
	}
	
	
	
	
	
	public static String formatNationalInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String NatlInstruments[][] = countryObj.getNatlInstruments();
		String rightsGroup = countryObj.getRightsGroup();
        int i = 0;     

        if (NatlInstruments[0][0] != "No Data in DB") {
    		
        	//Create the expand and collapse buttons.
        	htmlTable = htmlTable + "<div class='showhidediv'> \n "
					  + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllNatl()'> \n "
					  + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllNatl()'> \n "
					  + "</div> \n ";
        	
        	while(i<NatlInstruments.length) {
       		
        		//Format the drop down boxes.
				String[] federal = FormatingUtilities.setCheckedRadioButtons(NatlInstruments[i][5], "federal,state,local");
				
				String[] consistentintlstantdards = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][6], "yes,partially,no,unclear");	
				String[] consistentintlstantdards1951 = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][8], "yes,partially,no,unclear");
				
        		String[] supportnat = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][10], "yes,partially,no,unclear");
        		String[] restrictnat = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][11], "yes,partially,no,unclear");
        		     		
        		String[] supportidp = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][13], "yes,partially,no,unclear");
        		String[] restrictidp = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][14], "yes,partially,no,unclear");
        		
        		String[] supportref = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][16], "yes,partially,no,unclear");
        		String[] restrictref = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][17], "yes,partially,no,unclear");
        		
        		String[] supportasyl = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][19], "yes,partially,no,unclear");
        		String[] restrictasyl = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][20], "yes,partially,no,unclear");
        		
        		String[] supportreturn = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][22], "yes,partially,no,unclear");
        		String[] restrictreturn = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][23], "yes,partially,no,unclear");
        		
        		String[] supportstatless = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][25], "yes,partially,no,unclear");
        		String[] restrictstateless = FormatingUtilities.setSelectedDropDownOptions(NatlInstruments[i][26], "yes,partially,no,unclear");
        		
				String[] discriminationchecked =  FormatingUtilities.setCheckedBoxes(NatlInstruments[i][28],"sex,gender,age,national,ethnicity,religion,residence,disability,other");			
		 
				String[] otherlegis =  FormatingUtilities.setCheckedRadioButtons(NatlInstruments[i][30],"yes,no");			
				String[] otherlegischecked =  FormatingUtilities.setCheckedBoxes(NatlInstruments[i][32],"docu,edu,fair,fam,free,heal,housing,lib,nondis,poli,work,soc");			

				
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
		
						//Separator for the overview of the national instrument section.
						+ "<tbody id='overviewseparator'> \n "
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'> \n "
									+ "Overview of the Instrument\n "
								+ "</td> \n "
							+ "</tr> \n"
						+ "</tbody> \n "						
						+ "<tbody id='overviewrows'> \n "	    
							
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
											+ "<input id='uploadbutton' name='browseButton["+ i +"]' type='file' /> \n "
											+ "Click to Browse for File \n"
										+ "</label>\n"
										+ "<input class='inputfilename' type='text' name='natlinstrurefupload["+ i +"]' />\n"
										+ "<button id='upLoadFileButton' name='uploadButton["+ i +"]' type='button' value='"+ i +"'>Upload File</button>\n"
										+ "<p class='uploadfilesuccess' id='uploadsuccess["+ i +"]' ></p>\n"
										+ "<input type='hidden' name='natFileStorageName["+ i +"]' value='"+ NatlInstruments[i][2] +"' />\n"
										+ "<input type='hidden' name='natFileDisplayName["+ i +"]' value='"+ NatlInstruments[i][3] +"' />\n"
										+ "<input type='hidden' name='natFileURL["+ i +"]' value='"+ NatlInstruments[i][4] +"' />\n"
								+ "</td> \n "
							+ "</tr> \n "
								 
							//Instrument is federal, state or local.
							+ "<tr> \n "
								+ "<td>The instrument is applicable at the federal, state or local levels:</td> \n "
								+ "<td> \n "
									+ "<input type='radio' name='natlinstrufederal["+ i +"]' value='federal' "+ federal[0] +">Federal \n "
									+ "<input type='radio' name='natlinstrufederal["+ i +"]' value='state' "+ federal[1] +">State \n "
									+ "<input type='radio' name='natlinstrufederal["+ i +"]' value='local' "+ federal[2] +">Local \n "							
								+ "</td> \n "
							+ "</tr> \n"
							
							//Consistency with international standards.
							+ "<tr> \n "
								+ "<td>The content of the instrument is consistent with international standards:</td> \n "
								+ "<td> \n "
									+ "<div id='instruconsistentcommdiv'> \n"									
									+ "<select class='consistentintlstandards' name='natlinstruconsistent["+ i +"]'> \n "
										+ "<option value=''></option> \n "
										+ "<option value='yes' "+ consistentintlstantdards[0] +">Yes</option> \n "
										+ "<option value='partially' "+ consistentintlstantdards[1] +">Partially</option> \n "
										+ "<option value='no' "+ consistentintlstantdards[2] +">No</option> \n "
										+ "<option value='unclear' "+ consistentintlstantdards[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"
									+ "<label id='instruconsistentlabel' class='instruconsistentlabel'>Explanation as to why there is a lack of clarity (if 'Unclear' is selected): </label>  \n"									
									+ "<textarea class='instruconsistentcomm' name='natlinstruconsistentcomm["+ i +"]'>" + NatlInstruments[i][7] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n"	
								
							//Consistency with 1951 Refugee convention.
							+ "<tr> \n "
								+ "<td>The content of the instrument is consistent with the 1951 Refugee Conventions:</td> \n "
								+ "<td> \n "
									+ "<div id='instruconsistent1951div'> \n"									
									+ "<select class='consistentintlstandards1951' name='natlinstruconsistent1951["+ i +"]'> \n "
										+ "<option value=''></option> \n "
										+ "<option value='yes' "+ consistentintlstantdards1951[0] +">Yes</option> \n "
										+ "<option value='partially' "+ consistentintlstantdards1951[1] +">Partially</option> \n "
										+ "<option value='no' "+ consistentintlstantdards1951[2] +">No</option> \n "
										+ "<option value='unclear' "+ consistentintlstantdards1951[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"
									+ "<label id='instruconsistent1951label' class='instruconsistent1951label'>Explanation as to why there is a lack of clarity (if 'Unclear' is selected): </label>  \n"									
									+ "<textarea class='instruconsistentcomm1951' name='natlinstruconsistentcomm1951["+ i +"]'>" + NatlInstruments[i][9] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n"
						
						+ "</tbody> \n "
 
						
	                  //Beginning of the Supports/Restricts section. 						

						//Separator for the Support/Restricts section.							
						+ "<tbody id='supportrestrictssepartor'> \n "							
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Formal Support and/or Restriction of the "+rightsGroup+" Rights of Nationals and Populations of Concern\n "
								+ "</td> \n "
							+ "</tr> \n"
						+ "</tbody> \n "
							
						+ "<tbody id='supportrestrictsrows'> \n "
							
							//The instrument formally SUPPORTS the rights of NATIONALS.
							+ "<tr> \n "
								+ "<td class='col1'>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>nationals</strong>:</td> \n "
								+ "<td class='col2'> \n "
								
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportnatls["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportnat[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportnat[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportnat[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportnat[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictnatls["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictnat[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictnat[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictnat[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictnat[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									

									+ "<textarea class='supportrestrict' name='natlinstrusupportnatlscom["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][12] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
														
							
							//The instrument formally SUPPORTS the rights of IDPs.
							+ "<tr> \n "
								+ "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>internally displaced persons</strong>:</td> \n "
								+ "<td> \n "
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportidps["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportidp[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportidp[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportidp[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportidp[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictidps["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictidp[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictidp[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictidp[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictidp[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									
	
									+ "<textarea class='supportrestrict' name='natlinstrusupportidpscom["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][15] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
									
							
							//The instrument formally SUPPORTS the rights of Refugees.
							+ "<tr> \n "
								+ "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>refugees</strong>:</td> \n "
								+ "<td> \n "
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportrefug["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportref[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportref[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportref[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportref[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictrefug["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictref[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictref[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictref[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictref[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									
	
									+ "<textarea class='supportrestrict' name='natlinstrusupportrefugcom["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][18] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
								
								
							//The instrument formally SUPPORTS the rights of Asylum Seekers.
							+ "<tr> \n "
								+ "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>asylum seekers</strong>:</td> \n "
								+ "<td> \n "
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportasyl["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportasyl[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportasyl[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportasyl[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportasyl[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictasyl["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictasyl[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictasyl[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictasyl[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictasyl[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									
	
									+ "<textarea class='supportrestrict' name='natlinstrusupportasylcom["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][21] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
								
								
							//The instrument formally SUPPORTS the rights of Returnees.
							+ "<tr> \n "
								+ "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>returnees</strong>:</td> \n "
								+ "<td> \n "
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportreturn["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportreturn[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportreturn[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportreturn[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportreturn[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictreturn["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictreturn[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictreturn[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictreturn[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictreturn[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									
	
									+ "<textarea class='supportrestrict' name='natlinstrusupportreturncom["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][24] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
												
							
							//The instrument formally SUPPORTS the rights of Stateless Persons.
							+ "<tr> \n "
								+ "<td>The instrument formally supports and/or restricts the "+rightsGroup+" rights of <strong>stateless persons</strong>:</td> \n "
								+ "<td> \n "
									+ "<div id='supportrestrictdiv'> \n"									
									+ "<label class='supportrestrict'>Supports</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrusupportstateless["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ supportstatless[0] +">Yes</option> \n "
											+ "<option value='partially' "+ supportstatless[1] +">Partially</option> \n "
											+ "<option value='no' "+ supportstatless[2] +">No</option> \n "
											+ "<option value='unclear' "+ supportstatless[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "<label class='supportrestrict'>Restricts</label>  \n"									
									+ "<select class='supportrestrict' name='natlinstrurestrictstateless["+ i +"]'> \n "
											+ "<option value=''></option> \n "
											+ "<option value='yes' "+ restrictstateless[0] +">Yes</option> \n "
											+ "<option value='partially' "+ restrictstateless[1] +">Partially</option> \n "
											+ "<option value='no' "+ restrictstateless[2] +">No</option> \n "
											+ "<option value='unclear' "+ restrictstateless[3] +">Unclear</option> \n "
									+ "</select> \n "
									+ "</div> \n"									
	
									+ "<textarea class='supportrestrict' name='natlinstrusupportcomstateless["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][27] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n"
								
						+ "</tbody> \n "

						//Discrimination in the instrument.
						
						//Separator.
						+ "<tbody id='discriminationseparator'> \n "														
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Grounds on Which the Instrument Discriminates\n "
								+ "</td> \n "
							+ "</tr> \n"		
						+ "</tbody> \n "
						
						+ "<tbody id='discriminationrows'> \n "
							+ "<tr> \n "
								+ "<td>In the context of <strong>"+rightsGroup+"</strong> rights, the instrument discriminates based on the following social identifiers:</td> \n "
								+ "<td> \n "
									+ "<div class='discriminationcheckboxes'> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='sex' "+ discriminationchecked[0] +">Sex <br>\n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='gender'  "+ discriminationchecked[1] +">Gender \n"
									+ "</div> \n"
									+ "<div class='discriminationcheckboxes'> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='age'  "+ discriminationchecked[2] +">Age<br> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='national'  "+ discriminationchecked[3] +">National Origin \n"
									+ "</div> \n"
									+ "<div class='discriminationcheckboxes'> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='ethnicity'  "+ discriminationchecked[4] +">Ethnicity<br> \n "
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='religion'  "+ discriminationchecked[5] +">Religion\n "
									+ "</div> \n"
									+ "<div class='discriminationcheckboxes'> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='residence'  "+ discriminationchecked[6] +">Residence Status<br>\n "
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='disability'  "+ discriminationchecked[7] +">Disability\n"
									+ "</div> \n"
									+ "<div class='discriminationcheckboxes'> \n"
										+ "<input type='checkbox' name='natinstrudiscrimination["+ i +"]' value='other'  "+ discriminationchecked[8] +">Other:\n "
										+ "<input type='text' name='natinstrudiscriminationother["+ i +"]' value='"+ NatlInstruments[i][29].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"'>\n "
									+ "</div> \n"
								+ "</td> \n "
							+ "</tr> \n "				
						+ "</tbody> \n "							
						
						//Reference to Other Rights Groups Legislation.
						
						//Separator.
						+ "<tbody id='linksotherlegisseparator'> \n "														
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Links to Other Rights Categories\n "
								+ "</td> \n "
							+ "</tr> \n"		
						+ "</tbody> \n "
						
						+ "<tbody id='linksotherlegisrows'> \n "
							//References to other legislation in other rights groups. 
							+ "<tr> \n "
								+ "<td>The instrument references pieces of legislation related to other rights categories:</td> \n "
								+ "<td> \n "
									+ "<div class='otherlegisradio'>"
										+ "<input type='radio' name='natlinstruotherlegis["+ i +"]' value='yes' "+ otherlegis[0] +">Yes <br> \n "
										+ "<input type='radio' name='natlinstruotherlegis["+ i +"]' value='no'  "+ otherlegis[1] +">No \n "
									+ "</div>"
									+ "<textarea class='otherlegistextarea' name='natlinstruotherlegistextarea["+ i +"]' placeholder='Comments'>" + NatlInstruments[i][31] + "</textarea> \n "		
								+ "</td> \n "
							+ "</tr> \n "
							
							+ "<tr> \n "
							+ "<td>The instrument should be read in conjunction with instruments related to the following rights categories:</td> \n "
								+ "<td> \n "
									+ "<div class='otherlegischeckboxes1'> \n"
										+ "<p class='otherlegisheadertop'>Civil/Political Rights Categories</p>"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='fair' "+ otherlegischecked[2] +">Fair Trial and Right to Redress <br>\n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='free' "+ otherlegischecked[4] +">Freedom of Movement <br>\n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='lib'  "+ otherlegischecked[7] +">Liberty and Security of Person<br> \n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='nondis'  "+ otherlegischecked[8] +">Non-Discrimination<br> \n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='poli'  "+ otherlegischecked[9] +">Political Participation <br> \n"										
										+ "<p class='otherlegisheaders'>Economic Rights Categories</p>"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='housing'  "+ otherlegischecked[6] +">Housing, Land and Property Rights<br>  \n"
									+ "</div> \n"
									+ "<div class='otherlegischeckboxes2'> \n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='work'  "+ otherlegischecked[10] +">Right to Work and Rights at Work<br> \n "
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='soc'  "+ otherlegischecked[11] +">Social Security<br>\n "
										+ "<p class='otherlegisheaders'>Legal Rights Categories</p>"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='docu'  "+ otherlegischecked[0] +">Documentation<br>  \n"
										+ "<p class='otherlegisheaders'>Socio-cultural Rights Categories</p>"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='edu'  "+ otherlegischecked[1] +">Education<br>  \n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='fam' "+ otherlegischecked[3] +">Family Unity <br>\n"
											+ "<input type='checkbox' name='natinstruotherlegischecked["+ i +"]' value='heal' "+ otherlegischecked[5] +">Health <br>\n"

									+ "</div> \n"
								+ "</td> \n "
							+ "</tr> \n "
							
						+ "</tbody> \n "		

                        //Articles section.
						//Separator for the Articles section.

						+ "<tbody id='articlesseparator'> \n "														
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Articles in the Instrument Relevant to "+rightsGroup+" Rights\n "
								+ "</td> \n "
							+ "</tr> \n"
						+ "</tbody> \n "
							
						+ "<tbody id='articlesrows'> \n "
							
							//Articles in the  instrument.
							+ "<tr> \n "
								+ "<td>Full text of articles from the instrument (in English or French) that are relevant to "+rightsGroup+" rights:</td> \n "
	
								+ "<td> \n "
									+ "<textarea class='natcomments' name='natlinstruarticles["+ i +"]'>" + NatlInstruments[i][33] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
						
							//Comments on the Articles in the  instrument.
							+ "<tr> \n "
								+ "<td>Comments on the articles:</td> \n "
								+ "<td> \n "
									+ "<textarea class='natcomments' name='natlinstruarticlescomm["+ i +"]'>" + NatlInstruments[i][34] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
						+ "</tbody> \n "	
                        
                        //Other Comments section.
						//Separator for the Other Comments national instrument section.

						+ "<tbody id='othercommentsseparator'> \n "														
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Additional Comments on the Instrument\n "
								+ "</td> \n "
							+ "</tr> \n"
						+ "</tbody> \n "
							
						+ "<tbody id='othercommentsrows'> \n "
							
							//Comments on the instrument.
							+ "<tr> \n "
								+ "<td colspan='2'> \n "
									+ "<textarea class='natcomments' name='natlinstrucomments["+ i +"]' placeholder='Additional Comments'>" + NatlInstruments[i][35] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
						+ "</tbody> \n "		

	
					+ "</table> \n "
					+ "<br><br><br> \n ";		
				i++;
			}
        }

		return htmlTable; 
	}
	
	

	
	
}

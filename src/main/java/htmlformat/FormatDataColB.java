package htmlformat;

import java.util.List;

import dbhelper.dbutilities.RightsCategoriesManagement;
import model.Country;

public class FormatDataColB {

	public static String formatRightsGroupsDropDown(Country countryObj) {

		String htmlDropDown2 = new String("");
		String selectedRightsGroup = countryObj.getRightsGroup();
		List<String> rightsCategoryGroupName;
		List<String> rightsCategoriesPerGroupName;
		String selectedOption = "";
		String selectedOptionsDefault;
		int i = 0;

		// If none of the options have been select ensure a default one has been set.
		if (selectedRightsGroup == "") {
			selectedOptionsDefault = "selected";
		} else {
			selectedOptionsDefault = "";
		}

		//Get all of the rights category group names.
		rightsCategoryGroupName = RightsCategoriesManagement.getRightsCategoryGroupName();
		htmlDropDown2 = "<option " + selectedOptionsDefault + " disabled>Select a Rights Category</option> \n";

		while (i < rightsCategoryGroupName.size()) {

			rightsCategoriesPerGroupName = RightsCategoriesManagement.getRightsCategoriesPerGroupName(rightsCategoryGroupName.get(i));
			htmlDropDown2 = htmlDropDown2 + "<optgroup label='" + rightsCategoryGroupName.get(i) + "'> \n";

			int j = 0;
			while (j < rightsCategoriesPerGroupName.size()) {

				if (selectedRightsGroup.equals(rightsCategoriesPerGroupName.get(j))) {
					selectedOption = "selected"; 
				} else {
					selectedOption = ""; 
				}
				htmlDropDown2 = htmlDropDown2 + "<option " + selectedOption + ">" + rightsCategoriesPerGroupName.get(j) + "</option> \n";
				j++;
			}
			i++;
		}

		return htmlDropDown2;
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
	        		/*String[] typeofinstrument = FormatingUtilities.setSelectedDropDownOptions(IntlInstruments[i][1], "international,regional,bilateral");
					String[] ratified = FormatingUtilities.setSelectedDropDownOptions(IntlInstruments[i][2], "ratified,signed,notaparty,notgeo");*/
					String[] reservations =  FormatingUtilities.setCheckedRadioButtons(IntlInstruments[i][8],"yes,no");			
			
	
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
									+ "<td class='col2'> "+ IntlInstruments[i][0].replaceAll("'", "&#39;") +" \n "
										+ "<input type='hidden' name='intlinstruname["+ i +"]' value='"+ IntlInstruments[i][0].replaceAll("'", "&#39;") +"'> \n "
									+ "</td> \n "
								+ "</tr> \n "
									
								//Link to Refworld.
								+ "<tr class='trhideclass"+ i +"'> \n "
									+ "<td>Link to the instrument in Refworld:</td> \n "
									+ "<td> \n"
										+ "<a  href='"+ IntlInstruments[i][2] +"' target='_blank'>"+ IntlInstruments[i][2] +"</a> \n "
										+ "<input type='hidden' name='intlinstrureflink["+ i +"]' value='"+ IntlInstruments[i][2] +"' />\n"
									+ "</td> \n "
								+ "</tr> \n "
								
								//Upload instrument.
								+ "<tr class='trhideclass"+ i +"'> \n "
									+ "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> \n "
									+ "<td> \n"
											+ "<a class='linkToUploadedFile' name='intlFileURLLink["+ i +"]' href='"+ IntlInstruments[i][5] +"' target='_blank'>"+ IntlInstruments[i][4] +"</a>\n"
											+ "<input type='hidden' name='intlFileStorageName["+ i +"]' value='"+ IntlInstruments[i][3] +"' />\n"
											+ "<input type='hidden' name='intlFileDisplayName["+ i +"]' value='"+ IntlInstruments[i][4] +"' />\n"
											+ "<input type='hidden' name='intlFileURL["+ i +"]' value='"+ IntlInstruments[i][5] +"' />\n"
									+ "</td> \n "
								+ "</tr> \n "
								
								//Type of instrument.
								+ "<tr> \n "
									+ "<td>Type of instrument:</td> \n "
									+ "<td>" + FormatingUtilities.formatInstrumentData(IntlInstruments[i][1]) + "</td> \n "
								+ "</tr>"
									
								//The instrument has been ratified.
								+ "<tr> \n "
									+ "<td>The instrument has been ratified:</td> \n "
									+ "<td>" + FormatingUtilities.formatInstrumentData(IntlInstruments[i][6]) + "</td> \n "
								+ "</tr>"
									
								//Articles
								+ "<tr> \n "
									+ "<td>Articles from the instrument related to the rights:</td> \n "
									+ "<td class='articlescolumn' id='tdhide1classtype"+ i +"'> \n "
										+ "<textarea name='intlinstruarticles["+ i +"]'>" + IntlInstruments[i][7] + "</textarea> \n "
									+ "</td> \n "
									+ "<td id='tdhide2classtype"+ i +"' >" + IntlInstruments[i][7] + "</td> \n "
								+ "</tr> \n "
									
								//Reservations
								+ "<tr> \n "
									+ "<td>Reservations related to the right have been taken:</td> \n "
									+ "<td> \n "
										+ "<input type='radio' name='intlinstrures["+ i +"]' value='yes' "+ reservations[0] +">Yes \n "
										+ "<input type='radio' name='intlinstrures["+ i +"]' value='no'  "+ reservations[1] +">No \n "
									+ "</td> \n "
								+ "</tr> \n "
								+ "<tr> \n "
									+ "<td>Nature and scope of the reservations (if any):</td> \n "
									+ "<td><textarea name='intlinstruresnature["+ i +"]'>" + IntlInstruments[i][9] + "</textarea></td> \n "
								+ "</tr> \n "
							+ "</tbody> \n "		
						+ "</table> \n "
						+ "<br><br> <br>\n ";		
					i++;
				}
	        }
        }
		return htmlTable; 
	}
	
	
	
	public static String formatConstitutionTable(Country countryObj) {
		
		String htmlTable = new String(""); 
		String NatlInstruments[][] = countryObj.getNatlInstruments();
		String countryName = countryObj.getCountryName();
		String rightsGroup = countryObj.getRightsGroup();
		String POCs = countryObj.getPOCCountry();

        int i = 0;     

        if (NatlInstruments[0][0] != "No Data in DB") {
    		
      		
        		//Format the drop down boxes.
				
        		
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
		 
				
				//Create the HTML table.
				htmlTable = htmlTable + ""
					+ "<table id='natltable"+ i +"' class='natinstruments'> \n"
						+ "<thead  onclick=\"natltoggletablebody('natltable"+ i +"','natlinputtable"+ i +"')\"> \n "
							+ "<tr> \n "
								+ "<th class='tablehead' colspan='2'> \n "
								+ "<input class='inputinstrumenthead' id='natlinputtable"+ i +"' type='text' value='Constitution of "+countryName+"' size='65' onfocus='this.blur()' readonly> \n "
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
								+ "<td class='col1' >Official name of the constitution:</td> \n "
								+ "<td class='col2'> Constitution of "+countryName+"\n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Date of constitution.
							+ "<tr> \n "
								+ "<td>The date the constitution came into effect:</td> \n "
								+ "<td> \n"
								+ "</td> \n "
							+ "</tr> \n "
							
							//Amendment date.
							+ "<tr> \n "
								+ "<td>The date the constitution was last amended (if applicable):</td> \n "
								+ "<td> \n"
								+ "</td> \n "
							+ "</tr> \n "
							
							//Link to constitution Refworld.
							+ "<tr> \n "
								+ "<td>Link to the constitution in Refworld:</td> \n "
								+ "<td> \n"
									+ "French or English Version of the Constitution: <br><br> \n"
									+ "Original Language of the Constitution (If Other Than French or English): <br><br>\n"
								+ "</td> \n "
							+ "</tr> \n "
							
							//Link to the Bill of Rights in Refworld.
							+ "<tr> \n "
								+ "<td>Link to the Bill of Rights in Refworld:</td> \n "
								+ "<td> \n"
									+ "French or English Version of the Bill of Rights: <br><br>\n"
									+ "Original Language of the Bill of Rights (If Other Than French or English): <br><br> \n"
								+ "</td> \n "
							+ "</tr> \n "
							
	                        
	                        //Articles section.
							//Separator for the Articles section.

							+ "<tbody id='articlesseparator'> \n "														
								+ "<tr> \n "
									+ "<td class='separatorstyling' colspan='2'>"
										+ "Articles in the constitution Relevant to "+rightsGroup+" Rights\n "
									+ "</td> \n "
								+ "</tr> \n"
							+ "</tbody> \n "

							+ "<tbody id='articlesrows'> \n "
								
								//Articles in the  instrument.
								+ "<tr> \n "
									+ "<td>Full text of articles from the constitution (in English or French) that are relevant to "+rightsGroup+" rights:</td> \n "
		
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
							
							//Consistency with international standards separator.

							+ "<tbody > \n "														
								+ "<tr> \n "
									+ "<td class='separatorstyling' colspan='2'>"
										+ "Consistency of the Constitution with International Standards\n "
									+ "</td> \n "
								+ "</tr> \n"
							+ "</tbody> \n "
								
							+ "<tbody > \n "														

							//Consistency with international standards.
							+ "<tr> \n "
								+ "<td>The content of the constitution is consistent with international standards in relation to "+rightsGroup+" rights:</td> \n "
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
									+ "<label id='instruconsistentlabel' class='instruconsistentlabel'>Comments: </label>  \n"									
									+ "<textarea class='instruconsistentcomm' name='natlinstruconsistentcomm["+ i +"]'>" + NatlInstruments[i][7] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n"	
								
							//Consistency with 1951 Refugee convention.
							+ "<tr> \n "
								+ "<td>The content of the constitution is consistent with the 1951 Refugee Conventions international standards in relation to "+rightsGroup+" rights:</td> \n "
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
									+ "<label id='instruconsistent1951label' class='instruconsistent1951label'>Comments: </label>  \n"									
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
								+ "<td class='col1'>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>nationals</strong>:</td> \n "
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
							+ "</tr> \n";
														
							
							//The instrument formally SUPPORTS the rights of IDPs.
					        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){

					        	htmlTable = htmlTable + "<tr> \n "
										+ "<td>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>internally displaced persons</strong>:</td> \n "
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
									+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportidps[" + i + "]' value='"+ NatlInstruments[i][13]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictidps[" + i + "]' value='"+ NatlInstruments[i][14]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportidpscom[" + i + "]' value='"+ NatlInstruments[i][15].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
	
							
							//The instrument formally SUPPORTS the rights of Refugees.
					        if (POCs.equals("") || POCs.contains("Refugees")){

								htmlTable = htmlTable + "<tr> \n "
									+ "<td>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>refugees</strong>:</td> \n "
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
								+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportrefug[" + i + "]' value='"+ NatlInstruments[i][16]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictrefug[" + i + "]' value='"+ NatlInstruments[i][17]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportrefugcom[" + i + "]' value='"+ NatlInstruments[i][18].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
								
							//The instrument formally SUPPORTS the rights of Asylum Seekers.
					        if (POCs.equals("") || POCs.contains("Asylum Seekers")){

						        htmlTable = htmlTable + "<tr> \n "
									+ "<td>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>asylum seekers</strong>:</td> \n "
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
								+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportasyl[" + i + "]' value='"+ NatlInstruments[i][19]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictasyl[" + i + "]' value='"+ NatlInstruments[i][20]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportasylcom[" + i + "]' value='"+ NatlInstruments[i][21].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
								
							//The instrument formally SUPPORTS the rights of Returnees.
					        
					        if (POCs.equals("") || POCs.contains("Returnees")){

						        htmlTable = htmlTable + "<tr> \n "
									+ "<td>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>returnees</strong>:</td> \n "
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
								+ "</tr> \n";
												
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportreturn[" + i + "]' value='"+ NatlInstruments[i][22]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictreturn[" + i + "]' value='"+ NatlInstruments[i][23]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportreturncom[" + i + "]' value='"+ NatlInstruments[i][24].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
					        
					        
							//The instrument formally SUPPORTS the rights of Stateless Persons.
					        
					        if (POCs.equals("") || POCs.contains("Stateless Persons")){

						        htmlTable = htmlTable + "<tr> \n "
									+ "<td>The constitution formally supports and/or restricts the "+rightsGroup+" rights of <strong>stateless persons</strong>:</td> \n "
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
								+ "</tr> \n";
								
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportstateless[" + i + "]' value='"+ NatlInstruments[i][25]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictstateless[" + i + "]' value='"+ NatlInstruments[i][26]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportcomstateless[" + i + "]' value='"+ NatlInstruments[i][27].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }	
						
					        htmlTable = htmlTable + "</tbody> \n "

							//Discrimination in the instrument.
					        		//Separator.
							+ "<tbody id='discriminationseparator'> \n "														
								+ "<tr> \n "
									+ "<td class='separatorstyling' colspan='2'>"
										+ "Grounds on Which the Constitution Discriminates\n "
									+ "</td> \n "
								+ "</tr> \n"		
							+ "</tbody> \n "
							
							+ "<tbody id='discriminationrows'> \n "
								+ "<tr> \n "
									+ "<td>In the context of <strong>"+rightsGroup+"</strong> rights, the constitution discriminates based on the following social identifiers:</td> \n "
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
							
	                        //Other Comments section.
							//Separator for the Other Comments national instrument section.
	
							+ "<tbody id='othercommentsseparator'> \n "														
								+ "<tr> \n "
									+ "<td class='separatorstyling' colspan='2'>"
										+ "Additional Comments on the Constitution\n "
									+ "</td> \n "
								+ "</tr> \n"
							+ "</tbody> \n "
								
							+ "<tbody id='othercommentsrows'> \n "
									
									//Comments on the instrument.
									+ "<tr> \n "
										+ "<td colspan='2'> \n "
											+ "" + NatlInstruments[i][35] + " \n "
										+ "</td> \n "
									+ "</tr> \n "
									
								+ "</tbody> \n "		
							+ "</table> \n "
							+ "<br><br><br> \n ";		
				i++;
        }

		return htmlTable; 
	}
	
	
	public static String formatNationalInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		String NatlInstruments[][] = countryObj.getNatlInstruments();
		String rightsGroup = countryObj.getRightsGroup();
		String POCs = countryObj.getPOCCountry();

        int i = 0;     

        if (NatlInstruments[0][0] != "No Data in DB") {
    		
        	//Create the expand and collapse buttons.
        	htmlTable = htmlTable + "<div class='showhidediv'> \n "
					  + "<input class='expandbutton' type='button' name='show' value='Expand All' onclick='expandAllNatl()'> \n "
					  + "<input class='collapsebutton' type='button' name='hide' value='Collapse All' onclick='collapseAllNatl()'> \n "
					  + "</div> \n ";
        	
        	while(i<NatlInstruments.length) {
       		
        		//Format the drop down boxes.
				
        		
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
									+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +" \n "
									+ "<input name='natlinstruname["+ i +"]' type='hidden' value='"+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"'> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Link to Refworld.
							+ "<tr> \n "
								+ "<td>Link to the instrument in Refworld:</td> \n "
								+ "<td> \n"
									+ "<a  href='"+ NatlInstruments[i][1] +"' target='_blank'>"+ NatlInstruments[i][1] +"</a> \n "
								+ "</td> \n "
							+ "</tr> \n "
							
							//Upload instrument.
							+ "<tr> \n "
								+ "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> \n "
								+ "<td> \n"
										+ "<a class='linkToUploadedFile' name='natFileURLLink["+ i +"]' href='"+ NatlInstruments[i][4] +"' target='_blank'>"+ NatlInstruments[i][3] +"</a>\n"
										+ "<input type='hidden' name='natFileStorageName["+ i +"]' value='"+ NatlInstruments[i][2] +"' />\n"
										+ "<input type='hidden' name='natFileDisplayName["+ i +"]' value='"+ NatlInstruments[i][3] +"' />\n"
										+ "<input type='hidden' name='natFileURL["+ i +"]' value='"+ NatlInstruments[i][4] +"' />\n"
								+ "</td> \n "
							+ "</tr> \n "
								 
							//Instrument is applicable in All or Parts of the country. Used to be federal, state or local.
							+ "<tr> \n "
								+ "<td>The instrument is applicable in all or part(s) of the country:</td> \n "
								+ "<td> \n "
									+ "" + FormatingUtilities.formatInstrumentData(NatlInstruments[i][5]) + "\n"									
									+ "" + NatlInstruments[i][36] + " \n "
								+ "</td> \n "
							+ "</tr> \n"
							
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
							
							//Consistency with international standards separator.

							+ "<tbody > \n "														
								+ "<tr> \n "
									+ "<td class='separatorstyling' colspan='2'>"
										+ "Consistency of the Instrument with International Standards\n "
									+ "</td> \n "
								+ "</tr> \n"
							+ "</tbody> \n "
								
							+ "<tbody > \n "														

							//Consistency with international standards.
							+ "<tr> \n "
								+ "<td>The content of the instrument is consistent with international standards in relation to "+rightsGroup+" rights:</td> \n "
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
									+ "<label id='instruconsistentlabel' class='instruconsistentlabel'>Comments: </label>  \n"									
									+ "<textarea class='instruconsistentcomm' name='natlinstruconsistentcomm["+ i +"]'>" + NatlInstruments[i][7] + "</textarea> \n "
								+ "</td> \n "
							+ "</tr> \n"	
								
							//Consistency with 1951 Refugee convention.
							+ "<tr> \n "
								+ "<td>The content of the instrument is consistent with the 1951 Refugee Conventions international standards in relation to "+rightsGroup+" rights:</td> \n "
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
									+ "<label id='instruconsistent1951label' class='instruconsistent1951label'>Comments: </label>  \n"									
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
							+ "</tr> \n";
														
							
							//The instrument formally SUPPORTS the rights of IDPs.
					        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){

					        	htmlTable = htmlTable + "<tr> \n "
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
									+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportidps[" + i + "]' value='"+ NatlInstruments[i][13]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictidps[" + i + "]' value='"+ NatlInstruments[i][14]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportidpscom[" + i + "]' value='"+ NatlInstruments[i][15].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
	
							
							//The instrument formally SUPPORTS the rights of Refugees.
					        if (POCs.equals("") || POCs.contains("Refugees")){

								htmlTable = htmlTable + "<tr> \n "
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
								+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportrefug[" + i + "]' value='"+ NatlInstruments[i][16]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictrefug[" + i + "]' value='"+ NatlInstruments[i][17]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportrefugcom[" + i + "]' value='"+ NatlInstruments[i][18].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
								
							//The instrument formally SUPPORTS the rights of Asylum Seekers.
					        if (POCs.equals("") || POCs.contains("Asylum Seekers")){

						        htmlTable = htmlTable + "<tr> \n "
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
								+ "</tr> \n";
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportasyl[" + i + "]' value='"+ NatlInstruments[i][19]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictasyl[" + i + "]' value='"+ NatlInstruments[i][20]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportasylcom[" + i + "]' value='"+ NatlInstruments[i][21].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
								
							//The instrument formally SUPPORTS the rights of Returnees.
					        
					        if (POCs.equals("") || POCs.contains("Returnees")){

						        htmlTable = htmlTable + "<tr> \n "
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
								+ "</tr> \n";
												
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportreturn[" + i + "]' value='"+ NatlInstruments[i][22]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictreturn[" + i + "]' value='"+ NatlInstruments[i][23]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportreturncom[" + i + "]' value='"+ NatlInstruments[i][24].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }
					        
					        
							//The instrument formally SUPPORTS the rights of Stateless Persons.
					        
					        if (POCs.equals("") || POCs.contains("Stateless Persons")){

						        htmlTable = htmlTable + "<tr> \n "
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
								+ "</tr> \n";
								
					        }	else {
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportstateless[" + i + "]' value='"+ NatlInstruments[i][25]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrurestrictstateless[" + i + "]' value='"+ NatlInstruments[i][26]+"'>\n ";
					        	htmlTable = htmlTable 	+ "<input type='hidden' name='natlinstrusupportcomstateless[" + i + "]' value='"+ NatlInstruments[i][27].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")+"'>\n ";
					        }	
						
					        htmlTable = htmlTable + "</tbody> \n "

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
											+ "" + NatlInstruments[i][35] + " \n "
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

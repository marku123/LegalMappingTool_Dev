package htmlformat;

import model.Country;
import model.RightsGroup;

public class FormatDataColBReporting {

	public static String formatNatIntlInstrumentTables(Country countryObj) {
		
		String htmlTable = new String(""); 
		RightsGroup[] RightsGroups = countryObj.getRightsGroups();
		
        int j = 0;

        while (j < RightsGroups.length){

            int headingIndex = j+1;

            htmlTable = htmlTable + ""
            
            + "<h4 id=\"A3"+headingIndex+"\"> A.2."+headingIndex + " "+ RightsGroups[j].getRightsGroupName() + "</h4>\n"
            + "<p> 1. International and regional instruments related to " + RightsGroups[j].getRightsGroupName() + " rights:</p><br> \n";           
            
           
            htmlTable = htmlTable + formatIntlInstrumentTables(RightsGroups[j]);
        	
        	
            htmlTable = htmlTable + "<p> 2. National instruments that are relevant to " + RightsGroups[j].getRightsGroupName() + " rights:</p><br> \n";           

           htmlTable = htmlTable + formatNatlInstrumentTables(RightsGroups[j],countryObj);
           
           j++;
        }
		return htmlTable; 		
		
	}
	
	
	public static String formatIntlInstrumentTables(RightsGroup RightsGroups) {
		
		int i=0;
		String htmlTable = "";

    	while(i<RightsGroups.getIntlInstruments().length) {
       		
    		String IntlInstruments[][] = RightsGroups.getIntlInstruments();

			//Create the HTML table.
			htmlTable = htmlTable + ""
				+ "<table class='a3IntTables'> \n"
					+ "<thead> \n "
						+ "<tr> \n "
							+ "<th colspan='2'> \n "
							+ IntlInstruments[i][0].replaceAll("'", "&#39;") + "\n "
							+ "</th> \n "
						+ "</tr> \n"
					+ "</thead> \n "
					+ "<tbody > \n "
						+ "<tr> \n "
							+ "<td class='col1' >Name of the instrument:</td> \n "
							+ "<td class='col2'> \n"
								+ IntlInstruments[i][0].replaceAll("'", "&#39;")  + "\n "
							+ "</td> \n "
						+ "</tr> \n ";


			//If we are dealing with a pre-entered instrument then do not include the links to refworld. 
			if (!IntlInstruments[i][2].equals("preentered")) {
				htmlTable = htmlTable + ""
							+ "<tr> \n "
								+ "<td>Link to Instrument in Refworld:</td> \n "
								+ "<td> \n "
									+ "<a  class='refworldlinknat' href='"+ IntlInstruments[i][2] +"' target='_blank'>"+ IntlInstruments[i][2] +"</a> \n "
								+ "</td> \n "
							+ "</tr>"
							+ "<tr class='trhideclass"+ i +"'> \n "
								+ "<td>Uploaded PDF version of the instrument (if not in Refworld):</td> \n "
								+ "<td> \n "
									+ "<a  class='refworldlinknat' href='"+ IntlInstruments[i][5] +"' target='_blank'>"+ IntlInstruments[i][4] +"</a>\n"
								+ "</td> \n "
							+ "</tr>";
			}
			
			htmlTable = htmlTable + ""
						+ "<tr> \n "
							+ "<td>Type of instrument:</td> \n "
							+ "<td>" + FormatingUtilities.formatInstrumentData(IntlInstruments[i][1]) + "</td> \n "
						+ "</tr>"
						+ "<tr> \n "
							+ "<td>The instrument has been ratified:</td> \n "
							+ "<td> \n "
								+ FormatingUtilities.formatInstrumentData(IntlInstruments[i][6]) +"\n "
							+ "</td> \n "
						+ "</tr>"
						+ "<tr> \n "
							+ "<td>Articles from the instrument related to the rights:</td> \n "
							+ "<td> \n "
								+ IntlInstruments[i][7] + "\n "
							+ "</td> \n "
						+ "</tr> \n "
						+ "<tr> \n "
							+ "<td>Reservations related to the right have been taken:</td> \n "
							+ "<td> \n "
								+ FormatingUtilities.formatInstrumentData(IntlInstruments[i][8]) + "\n "
							+ "</td> \n "
						+ "</tr> \n "
						+ "<tr> \n "
							+ "<td>Nature and scope of the reservations (if any):</td> \n "
							+ "<td> \n "
								+ IntlInstruments[i][9] + "\n "
							+ "</td> \n "
						+ "</tr> \n "
					+ "</tbody> \n "		
				+ "</table> \n "
				+ "<br><br> <br>\n ";		
			i++;
		}
		
		
		return htmlTable; 		

		
	}

	
	
	public static String formatNatlInstrumentTables(RightsGroup RightsGroups, Country countryObj) {
		int i=0;
		String POCs = countryObj.getPOCCountry();
		String htmlTable = "";

    	while(i<RightsGroups.getNatlInstruments().length) {
       		
    		String NatlInstruments[][] = RightsGroups.getNatlInstruments();
    		
    		//Format comment for consistency with international standards. 
    		String commentConsistencyIntlStan = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][7]);
    		
    		//Format comment for consistency with 1951 Refugee Conventions. 
    		String comment1951Conven = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][9]);   		
    		
    		//Format comment for supports/restricts nationals. 
    		String commentSupResNat = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][12]);   

    		//Format comment for supports/restricts IDPs. 
    		String commentSupResIDP = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][15]);
    		
    		//Format comment for supports/restricts Refugees. 
    		String commentSupResRef = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][18]);
    		
    		//Format comment for supports/restricts Asylum Seekers. 
    		String commentSupResAsy = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][21]);  		
    		
    		//Format comment for supports/restricts Asylum Seekers. 
    		String commentSupResRet = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][24]);  	
    		
    		//Format comment for supports/restricts Stateless Persons. 
    		String commentSupResState = FormatingUtilities.formatReportCommentNatInstru("Comment:",NatlInstruments[i][27]);  	
    		
    		//Format the discrimination groups. 
    		String groupsInstrumentDiscriminatesFinal = FormatingUtilities.formatNatInstrurmentsDiscrimString(NatlInstruments[i][28],NatlInstruments[i][29]); 

    		
    		

			//Create the HTML table.
			htmlTable = htmlTable + ""
				+ "<table class='a3NatTables'> \n"
					+ "<thead> \n "
						+ "<tr> \n "
							+ "<th colspan='2'> \n "
								+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n"
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
								+ NatlInstruments[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
							+ "</td> \n "
						+ "</tr> \n "				
					
						//Link to Refworld.
						+ "<tr> \n "
							+ "<td>Link to the instrument in Refworld:</td> \n "
							+ "<td> \n"
								+ "<a class='refworldlinknat' href='"+ NatlInstruments[i][1] +"' target='_blank'>"+ NatlInstruments[i][1].replaceAll("'", "&#39;")  +"\n "
							+ "</td> \n "
						+ "</tr> \n "	
					
					
						//Uploaded instrument.
						+ "<tr> \n "
							+ "<td>Link to the PDF version of the instrument stored in the Legal Mapping Tool (if not in Refworld):</td> \n "
							+ "<td> \n"
									+ "<a class='refworldlinknat' href='"+ NatlInstruments[i][4] +"' target='_blank'>"+ NatlInstruments[i][3] +"</a>\n"
							+ "</td> \n "
						+ "</tr> \n "
					
						//Instrument is applicable in All or Parts of the country. Used to be federal, state or local.
						+ "<tr> \n "
							+ "<td>The instrument is applicable in all or part(s) of the country:</td> \n "
							+ "<td> \n "
								+ "" + FormatingUtilities.formatInstrumentData(NatlInstruments[i][36]) + "\n"									
								+ "" + NatlInstruments[i][37] + " \n "
							+ "</td> \n "
						+ "</tr> \n"

					+ "</tbody> \n "	

					
	                //Articles section.
					//Separator for the Articles section.

					+ "<tbody> \n "														
							+ "<tr> \n "
								+ "<td class='separatorstyling' colspan='2'>"
									+ "Articles in the Instrument Relevant to "+RightsGroups.getRightsGroupName()+" Rights\n "
								+ "</td> \n "
							+ "</tr> \n"
					+ "</tbody> \n "
						
					+ "<tbody> \n "
						
							//Articles in the  instrument.
							+ "<tr> \n "
								+ "<td>Full text of articles from the instrument (in English or French) that are relevant to "+RightsGroups.getRightsGroupName()+" rights:</td> \n "
								+ "<td> \n "
									+ NatlInstruments[i][33] +"\n "
								+ "</td> \n "
							+ "</tr> \n "
	
							//Comments on the Articles in the  instrument.
							+ "<tr> \n "
								+ "<td>Comments on the articles:</td> \n "
								+ "<td> \n "
									+ NatlInstruments[i][34] + "\n "
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
							+ "<td>The content of the instrument is consistent with international standards:</td> \n "
							+ "<td> \n "
									+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][6]) +" \n "
									+ commentConsistencyIntlStan + "\n "
							+ "</td> \n "
						+ "</tr> \n"		
					
						//Consistency with 1951 Refugee convention.
						+ "<tr> \n "
							+ "<td>The content of the instrument is consistent with the 1951 Refugee Conventions:</td> \n "
							+ "<td> \n "
								+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][8]) +" \n "
								+ comment1951Conven + "\n "
							+ "</td> \n "
						+ "</tr> \n"
					
					+ "</tbody> \n "

	                //Beginning of the Supports/Restricts section. 						
	
					//Separator for the Support/Restricts section.							
					+ "<tbody> \n "							
						+ "<tr> \n "
							+ "<td class='separatorstyling' colspan='2'>"
								+ "Formal Support and/or Restriction of the "+RightsGroups.getRightsGroupName()+" Rights of Nationals and Populations of Concern\n "
							+ "</td> \n "
						+ "</tr> \n"
					+ "</tbody> \n "
							
					+ "<tbody> \n "
	
					//The instrument formally SUPPORTS the rights of NATIONALS.
					+ "<tr> \n "
						+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>nationals</strong>:</td> \n "
						+ "<td > \n "
						
							+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][10]) +"<br><br>\n "
							+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][11]) + "\n "

							+ commentSupResNat +"\n "		
						+ "</td> \n "
					+ "</tr> \n";
				
			
		        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){
	
						//The instrument formally SUPPORTS the rights of IDPs.
		        	htmlTable = htmlTable + "<tr> \n "
							+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>internally displaced persons</strong>:</td> \n "
							+ "<td > \n "
							
								+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][13]) +"<br><br>\n "
								+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][14]) + "\n "
	
								+ commentSupResIDP +"\n "		
							+ "</td> \n "
						+ "</tr> \n";
		        }	
		        
		        if (POCs.equals("") || POCs.contains("Refugees")){

					//The instrument formally SUPPORTS the rights of Refugees.
			        htmlTable = htmlTable + "<tr> \n "
							+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>refugees</strong>:</td> \n "
							+ "<td > \n "
							
								+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][16]) +"<br><br>\n "
								+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][17]) + "\n "
	
								+ commentSupResRef +"\n "		
							+ "</td> \n "
						+ "</tr> \n";
		        }
		        
		        if (POCs.equals("") || POCs.contains("Asylum Seekers")){

						//The instrument formally SUPPORTS the rights of Asylum Seekers.
			        htmlTable = htmlTable + "<tr> \n "
							+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>asylum seekers</strong>:</td> \n "
							+ "<td > \n "
							
								+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][19]) +"<br><br>\n "
								+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][20]) + "\n "
	
								+ commentSupResAsy +"\n "		
							+ "</td> \n "
						+ "</tr> \n";
		        }
		        
		        if (POCs.equals("") || POCs.contains("Returnees")){

						//The instrument formally SUPPORTS the rights of Returnees.
			        htmlTable = htmlTable + "<tr> \n "
							+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>returnees</strong>:</td> \n "
							+ "<td > \n "
							
								+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][22]) +"<br><br>\n "
								+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][23]) + "\n "
	
								+ commentSupResRet +"\n "		
							+ "</td> \n "
						+ "</tr> \n";
		        }		
		        
		        if (POCs.equals("") || POCs.contains("Stateless Persons")){

					//The instrument formally SUPPORTS the rights of Stateless Persons.
		        	 htmlTable = htmlTable + "<tr> \n "
						+ "<td >The instrument formally supports and/or restricts the "+RightsGroups.getRightsGroupName()+" rights of <strong>stateless persons</strong>:</td> \n "
						+ "<td > \n "
						
							+ "Supports: "+ FormatingUtilities.formatInstrumentData(NatlInstruments[i][25]) +"<br><br>\n "
							+ "Restricts: " + FormatingUtilities.formatInstrumentData(NatlInstruments[i][26]) + "\n "

							+ commentSupResState +"\n "		
						+ "</td> \n "
					+ "</tr> \n";	
		        }
		        htmlTable = htmlTable +  "</tbody> \n "
				
				//Discrimination in the instrument.
				
				//Separator.
				+ "<tbody> \n "														
					+ "<tr> \n "
						+ "<td class='separatorstyling' colspan='2'>"
							+ "Grounds on Which the Instrument Discriminates\n "
						+ "</td> \n "
					+ "</tr> \n"		
				+ "</tbody> \n "
				+ "<tbody> \n "														
					+ "<tr> \n "
						+ "<td>In the context of <strong>"+RightsGroups.getRightsGroupName()+"</strong> rights, the instrument discriminates based on the following social identifiers:</td> \n "
						+ "<td> \n "
							+ groupsInstrumentDiscriminatesFinal.replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "		
						+ "</td> \n "
					+ "</tr> \n "				
				+ "</tbody> \n "
				//Reference to Other Rights Groups Legislation.
				
                //Other Comments section.
				//Separator for the Other Comments national instrument section.

				+ "<tbody> \n "														
					+ "<tr> \n "
						+ "<td class='separatorstyling' colspan='2'>"
							+ "Additional Comments on the Instrument\n "
						+ "</td> \n "
					+ "</tr> \n"
				+ "</tbody> \n "
				
				+ "<tbody> \n "
				
					//Comments on the instrument.
					+ "<tr> \n "
						+ "<td colspan='2'> \n "
							+ NatlInstruments[i][35] + "\n "
						+ "</td> \n "
					+ "</tr> \n "
					
				+ "</tbody> \n "		
				
				+ "</table> \n "
				+ "<br><br> <br>\n ";		
			i++;
		}
		
		
		
		return htmlTable; 		

	}
	
	public static String formatTableOfContents(Country countryObj) {
		
		String htmlString = "";
		RightsGroup[] RightsGroups = countryObj.getRightsGroups();

		
		htmlString = htmlString +"<a class=\"tableofcontentshead\" href=\"#SectionA\">A. The Legal Protection Framework</a>\n" 
								/*+ "<a class=\"tableofcontentssub1\" href=\"#A1\">A.1 The Country's Constitution</a>\n"*/
								+ "<a class=\"tableofcontentssub1\" href=\"#A2\">A.1 The Country's Legal System and Population Groups</a>\n" 
								+ "<a class=\"tableofcontentssub1\" href=\"#A3\">A.2 Rights Protection</a>\n";
		
        int j = 0;

        while (j < RightsGroups.length){
					
            int headingIndex = j+1;

            htmlString = htmlString + ""
            
            + "<a class=\"tableofcontentssub2\" href=\"#A3"+headingIndex+"\"> A.2."+headingIndex + " "+ RightsGroups[j].getRightsGroupName() + "</a><br>\n";
         	
            j++;
        } 	
		return htmlString;

		
	}
}
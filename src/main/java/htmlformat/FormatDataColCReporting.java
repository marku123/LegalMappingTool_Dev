package htmlformat;

import java.util.ArrayList;
import java.util.List;

import dbhelper.dbutilities.RightsCategoriesManagement;
import model.Country;
import model.PersonsOfConcern;
public class FormatDataColCReporting {

	public static String formatObstacleTablesDocs(Country countryObj) {
		String htmlTable = new String(""); 
		PersonsOfConcern[] PersonsOfConcern = countryObj.getPersonsOfConcern();
		
        int j = 0;

        while (j < PersonsOfConcern.length){

            int headingIndex = j+1;
            
            htmlTable = htmlTable + "<h3 id='B"+headingIndex+"'>B."+headingIndex+" Obstacles Impacting <span class='bluetext'>"+PersonsOfConcern[j].getPersonOfConcernName()+"</span></h3><br>\n";   

            htmlTable = htmlTable + ""
                        + "<h4> B."+headingIndex+".1 Obstacles Preventing <span class='bluetext'>"+ PersonsOfConcern[j].getPersonOfConcernName() + "</span> from Enjoying Their Rights</h4><br>\n";
           
            htmlTable = htmlTable + formatIntlObstacleTables(PersonsOfConcern[j]);
        	
            htmlTable = htmlTable + ""
            		+ "<h4> B."+headingIndex+".2 Links to Supporting Documentation</h4><br>\n";
           
            htmlTable = htmlTable + formatObstacleDocs(PersonsOfConcern[j]);

            j++;
        
        }
		return htmlTable; 		
		
	}
	
	
	public static String formatObstacleDocs(PersonsOfConcern PersonsOfConcern) {

		String html = new String(""); 
		String AllObstaclesDocumenation[][] = PersonsOfConcern.getObstaclesDocumenation();

        int i = 0;
    	
        if (AllObstaclesDocumenation[0][0] != "noData"){
        	
    		html = html + "<div id=\"obstacleDocumentationSection\">\n";

	    	while(AllObstaclesDocumenation[i][0] != null) {
 		
	    		html = html + ""
	    				+ "<a class='docVisibleLink' href='"+AllObstaclesDocumenation[i][2] +"'>"+AllObstaclesDocumenation[i][2]+"</a><br> <br>\n";
	    		
	    		i++;
	    	}
	    	
    		html = html + "</div>\n";

    	}	
    	
    	return html;
	}
	
	public static String formatIntlObstacleTables(PersonsOfConcern PersonsOfConcern) {
		
		int i=0;
		String htmlTable = "";
		String [] RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();
		String AllRightsGrpObs[][] = PersonsOfConcern.getObstacles();

		
		
    	while(i<RightsGroupsNames.length) {

    		//Formatting for Legal Status and Documentation.
    		String legalseverity = formatObstacleSeverity(AllRightsGrpObs[i][1]); 
    		String legalGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][3]);

    		//Formatting for Financial.
    		String financialseverity = formatObstacleSeverity(AllRightsGrpObs[i][4]); 
    		String financialGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][6]);
    		
    		//Formatting for Geographic.
    		String geoseverity = formatObstacleSeverity(AllRightsGrpObs[i][7]); 
    		String geoGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][9]);
    		
    		//Formatting for Admin and Institutional.
    		String adminseverity = formatObstacleSeverity(AllRightsGrpObs[i][10]); 
    		String adminGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][12]);
    		
    		//Formatting for Security.
    		String secseverity = formatObstacleSeverity(AllRightsGrpObs[i][13]); 
    		String secGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][15]);
    		
    		//Formatting for Discrimination.
    		String discrimseverity = formatObstacleSeverity(AllRightsGrpObs[i][16]); 
    		String discrimGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][18]);
    		
    		//Formatting for Lack of Information.
    		String infoseverity = formatObstacleSeverity(AllRightsGrpObs[i][19]); 
    		String infoGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][21]);
    		
    		//Formatting for Other.
    		String otherseverity = formatObstacleSeverity(AllRightsGrpObs[i][23]); 
    		String otherGroupsAffected = formatGroupsAffected(AllRightsGrpObs[i][25]);
    		
			//Create the HTML table.
			htmlTable = htmlTable + ""
    	
			+ "<table  class='rightsgrpobs'> \n"
				+ "<thead > \n "
					+ "<tr> \n "
						+ "<th colspan='4'> \n "
							+ "Obstacles Preventing <span class='bluetext'>" + PersonsOfConcern.getPersonOfConcernName() + "</span> "
							+ "From Enjoying Their <span class='bluetext'>" + AllRightsGrpObs[i][0] + "</span> Rights \n "
						+ "</th> \n "
					+ "</tr> \n"
					+ "<tr class='tableheadings' > \n "
						+ "<th class='col1'>Type of Obstacles</td> \n "
						+ "<th class='col2'> Severity of the Obstacles </td> \n "
						+ "<th class='col3'> Description of the Obstacles (If Applicable)</td> \n "
						+ "<th class='col4'> Groups Affected by the Obstacles</td> \n " 		
					+ "</tr> \n "
				+ "</thead> \n "
				+ "<tbody class='tablebody'> \n "
			
					+ "<tr> \n "
						+ "<td>Legal Status and Documentation</td> \n "
						+ "<td> \n"
							+ legalseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][2] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ legalGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "
				
					+ "<tr> \n "
						+ "<td>Financial</td> \n "
						+ "<td> \n"
							+ financialseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][5] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ financialGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "
						
					+ "<tr> \n "
						+ "<td>Geographic</td> \n "
						+ "<td> \n"
							+ geoseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][8] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ geoGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "	
				
				
					+ "<tr> \n "
						+ "<td>Administrative and Institutional</td> \n "
						+ "<td> \n"
							+ adminseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][11] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ adminGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "	
				
					+ "<tr> \n "
						+ "<td>Security</td> \n "
						+ "<td> \n"
							+ secseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][14] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ secGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "	
				
					+ "<tr> \n "
						+ "<td>Discrimination</td> \n "
						+ "<td> \n"
							+ discrimseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][17] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ discrimGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "	
				
					+ "<tr> \n "
						+ "<td>Lack of Information</td> \n "
						+ "<td> \n"
							+ infoseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][20] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ infoGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "	
						
					+ "<tr> \n "
						+ "<td>Other: "+AllRightsGrpObs[i][22]+"</td> \n "
						+ "<td> \n"
							+ otherseverity +""
						+ "</td> \n "
						+ "<td> \n"
							+ AllRightsGrpObs[i][24] + "\n "
						+ "</td> \n "
						+ "<td class='col4'> \n"
							+ otherGroupsAffected + "\n "
						+ "</td> \n " 
					+ "</tr> \n "
				
				
				+ "</tbody> \n "
			+ "</table><br> <br> \n ";
			
			
			
			i++;
    	}
		return htmlTable; 		

	}	

	public static String formatObstacleSeverity(String Severity) {
	
		String returnSeverity = "";
		
		if(Severity.equals("significant")){
			
			returnSeverity = "Determinative obstacles, effectively preventing access to the right.";
			
		} else if(Severity.equals("moderate"))  {
			
			returnSeverity = "Some obstacles, making access to the right a challenge.";

		}else if(Severity.equals("none"))  {
			
			returnSeverity = "No or minor obstacles.";

		}else if(Severity.equals("unknown"))  {
			
			returnSeverity = "Unknown";
		}
		
		return returnSeverity; 		

	}
	
	public static String formatGroupsAffected(String rawGroupsAffected) {
		String formattedGroupsAffected = "";
		String[] legaloldnewgroups = FormatingUtilities.getNewOldGroups(rawGroupsAffected);
		String oldGroups[] = legaloldnewgroups[0].split(",");
		String newGroupsRaw = legaloldnewgroups[1];
		String[] spliteArrayOfValues = newGroupsRaw.split(",:");
		List<String> newGroupsFinal = new ArrayList<String>();

		int i=0;
		
		//Create the string for the existing groups.
		while(i<oldGroups.length) {
			
			if(oldGroups[i].equals("women")) {
				formattedGroupsAffected = formattedGroupsAffected + "Women; ";				
			} else if (oldGroups[i].equals("men")) {
				formattedGroupsAffected = formattedGroupsAffected + "Men; ";				
			} else if (oldGroups[i].equals("girls")) {
				formattedGroupsAffected = formattedGroupsAffected + "Girls; ";				
			} else if (oldGroups[i].equals("boys")) {
				formattedGroupsAffected = formattedGroupsAffected + "Boys; ";				
			} else if (oldGroups[i].equals("olderwomen")) {
				formattedGroupsAffected = formattedGroupsAffected + "Older Women; ";				
			} else if (oldGroups[i].equals("oldermen")) {
				formattedGroupsAffected = formattedGroupsAffected + "Older Men; ";				
			} else if (oldGroups[i].equals("femalesdisabilities")) {
				formattedGroupsAffected = formattedGroupsAffected + "Females With Disabilities; ";				
			} else if (oldGroups[i].equals("malesdisabilities")) {
				formattedGroupsAffected = formattedGroupsAffected + "Males With Disabilities; ";				
			} else if (oldGroups[i].equals("lgbtipersons")) {
				formattedGroupsAffected = formattedGroupsAffected + "LGBTI Persons; ";				
			}
		
			i++;
		}

		//Create the string for the new groups that are added.

		for (int j = 0; j < spliteArrayOfValues.length; j++) {
			newGroupsFinal.add(spliteArrayOfValues[j].replaceAll(",", ", "));
		}
		for (int m = 0; m < newGroupsFinal.size(); m++) {
			if(!newGroupsFinal.get(m).equals("")) {
				formattedGroupsAffected = formattedGroupsAffected + newGroupsFinal.get(m) +"; ";
			}
		}
		
        //Get right of the last semi-colon separator.
		if(formattedGroupsAffected.length()>2){
			formattedGroupsAffected = formattedGroupsAffected.substring(0,formattedGroupsAffected.length() - 2);
		}

		return formattedGroupsAffected;
	
	}

	public static String formatTableOfContents(Country countryObj) {
		
		String htmlString = "";
		PersonsOfConcern[] PersonsOfConcern = countryObj.getPersonsOfConcern();

		htmlString = htmlString +"<a class=\"tableofcontentshead\" href=\"#SectionB\">B. Obstacles to Enjoying Rights</a>\n"; 
		
        int j = 0;

        while (j < PersonsOfConcern.length){
			
        	int headingIndex = j+1;
        	
            htmlString = htmlString + ""
            + "<a class=\"tableofcontentssub1\" href=\"#B"+headingIndex+"\"><span class=\"tableofcontentssubNum\">B."+headingIndex+"</span><span class=\"tableofcontentssubText\"> Obstacles Impacting "+PersonsOfConcern[j].getPersonOfConcernName()+"</span></a>\n";
            
            
            j++;
        } 	
		return htmlString;

		
	}

}

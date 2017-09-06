package htmlformat;


import java.util.List;
import dbhelper.dbutilities.RightsCategoriesManagement;
import model.Country;

public class FormatDataColC {

	
	public static String formatPOCDropDown(Country countryObj) {
		
		String htmlDropDown = new String(""); 
		String personsofconcern = countryObj.getPOC();
		String selectedOptionsDefault;
		String selectedOptions[];
		String dropDownOptions = ""
				+ "Asylum Seekers,"
				+ "Internally Displaced Persons,"
				+ "Refugees,"
				+ "Returnees,"
				+ "Stateless Persons";
		
		//Format the options, ensure that the one that is selected is set to "selected". 
		selectedOptions = FormatingUtilities.setSelectedDropDownOptions(personsofconcern, dropDownOptions);
		
		//If none of the options have been select ensure a default one has been set. 
		if (personsofconcern == "") {
			selectedOptionsDefault = "selected";
		} else {
			selectedOptionsDefault = "";
		}
		
        //Create the HTML options.
        htmlDropDown = htmlDropDown + ""
				+ "<option "+selectedOptionsDefault +" disabled></option> \n"
				+ "<option " +selectedOptions[0]+ ">Asylum Seekers</option> \n"
				+ "<option " +selectedOptions[1]+ ">Internally Displaced Persons</option> \n"
				+ "<option " +selectedOptions[2]+ ">Refugees</option> \n"
				+ "<option " +selectedOptions[3]+ ">Returnees</option> \n"
				+ "<option " +selectedOptions[4]+ ">Stateless Persons</option> \n";
		
		return htmlDropDown; 
	}
	
	public static String formatObstaclesTable(Country countryObj) {
		
		String htmlTable = new String(""); 
		String AllRightsGrpObs[][] = countryObj.getObstacles();
		String personsofconcern = countryObj.getPOC();
		String[][] htmlWithGroupNames = new String[AllRightsGrpObs.length][2];
		List<String> RightsGroupNames = RightsCategoriesManagement.getRightsCategoryGroupName();
    	String finalHTMLTable = "";

		int i = 0;
    	while(i<AllRightsGrpObs.length) {
    		//Format the drop down boxes.
    		String[] legaloldnewgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][3]);
    		String[] legalobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][1], "significant,moderate,none,unknown");
    		String[] legalobsgrpsselected =  FormatingUtilities.setCheckedBoxes(legaloldnewgroups[0],"men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String legalobsgrpsother = FormatingUtilities.formatObstacleGroups(i,legaloldnewgroups[1],"legal","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] finoldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][6]);
    		String[] finobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][4], "significant,moderate,none,unknown");
			String[] finobsgrpsselected =  FormatingUtilities.setCheckedBoxes(finoldgroups[0],"men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String finobsgrpsother = FormatingUtilities.formatObstacleGroups(i,finoldgroups[1],"fin","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] docoldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][9]);
    		String[] docobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][7], "significant,moderate,none,unknown");
			String[] docobsgrpsselected =  FormatingUtilities.setCheckedBoxes(docoldgroups[0],"men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String docobsgrpsother = FormatingUtilities.formatObstacleGroups(i,docoldgroups[1],"doc","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] geooldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][12]);
    		String[] geoobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][10], "significant,moderate,none,unknown");
			String[] geoobsgrpsselected =  FormatingUtilities.setCheckedBoxes(geooldgroups[0],"men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String geoobsgrpsother = FormatingUtilities.formatObstacleGroups(i,geooldgroups[1],"geo","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] adminoldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][15]);
    		String[] adminobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][13], "significant,moderate,none,unknown");
    		String[] adminobsgrpselected = FormatingUtilities.setCheckedBoxes(adminoldgroups[0], "men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String adminobsgrpsother = FormatingUtilities.formatObstacleGroups(i,adminoldgroups[1],"admin","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] secoldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][18]);
    		String[] secobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][16], "significant,moderate,none,unknown");
    		String[] secobsgrpselected = FormatingUtilities.setCheckedBoxes(secoldgroups[0], "men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String secobsgrpsother = FormatingUtilities.formatObstacleGroups(i,secoldgroups[1],"sec","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] discrimoldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][21]);
    		String[] discrimobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][19], "significant,moderate,none,unknown");
    		String[] discrimobsgrpselected = FormatingUtilities.setCheckedBoxes(discrimoldgroups[0], "men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String discrimobsgrpsother = FormatingUtilities.formatObstacleGroups(i,discrimoldgroups[1],"discrim","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

    		String[] otheroldgroups = FormatingUtilities.getNewOldGroups(AllRightsGrpObs[i][25]);
    		String[] otherobsselected = FormatingUtilities.setSelectedDropDownOptions(AllRightsGrpObs[i][23], "significant,moderate,none,unknown");
    		String[] otherobsgrpselected = FormatingUtilities.setCheckedBoxes(otheroldgroups[0], "men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");
			String otherobsgrpsother = FormatingUtilities.formatObstacleGroups(i,otheroldgroups[1],"other","men,boys,oldermen,malesdisabilities,lgbtipersons,women,girls,olderwomen,femalesdisabilities");

			String rightsCategoryGroupName = RightsCategoriesManagement.getRightGroupName(AllRightsGrpObs[i][0]);

			//Create the HTML table.
			htmlTable = ""
				+ "<div id='table"+ i +"div' class='rightsgrouptablediv'>"
				+ "<table id='table"+ i +"' class='rightsgrpobs'> \n"
					+ "<thead  onclick=\"toggletablebody('table"+ i +"')\"> \n "
						+ "<tr> \n "
							+ "<th class='tablehead' colspan='4'> \n "
								+ "Obstacles Preventing <span class='bluetext'>" + personsofconcern + "</span> "
								+ "From Enjoying Their <span class='bluetext'>" + AllRightsGrpObs[i][0] + "</span> Rights \n "
								+ "<span class='tooltiptext'>[Click to Expand/Collapse]</span> \n "
								+ "<input name='rightsgroup["+ i +"]' type='hidden' value='" + AllRightsGrpObs[i][0] + "'> \n "
							+ "</th> \n "
						+ "</tr> \n"
					+ "</thead> \n "
					+ "<tbody class='tablebody'> \n "
 
					+ "<tr> \n "
						+ "<td class='col1' >Type of Obstacles</td> \n "
						+ "<td class='col2'> Severity of the Obstacles </td> \n "
						+ "<td class='col3'> Description of the Obstacles (If Applicable)</td> \n "
						+ "<td class='col4'> Groups Affected by the Obstacles</td> \n " 		
					+ "</tr> \n "
						
						+ "<tr> \n "
							+ "<td class='col1' >Legal Status and Documentation</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='legalobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ legalobsselected[0] +">Determinative obstacles, "
											+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ legalobsselected[1] +">Some obstacles, "
											+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ legalobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ legalobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='legalobscomments["+ i +"]'>" + AllRightsGrpObs[i][2] + "</textarea> \n "
							+ "</td> \n "
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='legalobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='women'  "+ legalobsgrpsselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='men' "+ legalobsgrpsselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='girls'  "+ legalobsgrpsselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='boys' "+ legalobsgrpsselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='olderwomen'  "+ legalobsgrpsselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='oldermen'  "+ legalobsgrpsselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='femalesdisabilities'  "+ legalobsgrpsselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='legalobsgrps["+ i +"]' value='malesdisabilities'  "+ legalobsgrpsselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input  type='checkbox' name='legalobsgrps["+ i +"]' value='lgbtipersons'  "+ legalobsgrpsselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ legalobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='legalobsdiv"+ i +"' inputCheckBoxName='legalobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "

						+ "<tr> \n "
							+ "<td class='col1' >Financial</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='financeobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ finobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ finobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ finobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ finobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='financeobscomments["+ i +"]'>" + AllRightsGrpObs[i][5] + "</textarea> \n "
							+ "</td> \n "
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='finobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='women'  "+ finobsgrpsselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='men' "+ finobsgrpsselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='girls'  "+ finobsgrpsselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='boys' "+ finobsgrpsselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='olderwomen'  "+ finobsgrpsselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='oldermen'  "+ finobsgrpsselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='femalesdisabilities'  "+ finobsgrpsselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='finobsgrps["+ i +"]' value='malesdisabilities'  "+ finobsgrpsselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input  type='checkbox' name='finobsgrps["+ i +"]' value='lgbtipersons'  "+ finobsgrpsselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ finobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='finobsdiv"+ i +"' inputCheckBoxName='finobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "
				
						+ "<tr> \n "
							+ "<td class='col1' >Geographic</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='docobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ docobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ docobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ docobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ docobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='docobscomments["+ i +"]'>" + AllRightsGrpObs[i][8] + "</textarea> \n "
							+ "</td> \n "
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
								+ "<div id='docobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='women'  "+ docobsgrpsselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='men' "+ docobsgrpsselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='girls'  "+ docobsgrpsselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='boys' "+ docobsgrpsselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='olderwomen'  "+ docobsgrpsselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='oldermen'  "+ docobsgrpsselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='femalesdisabilities'  "+ docobsgrpsselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='docobsgrps["+ i +"]' value='malesdisabilities'  "+ docobsgrpsselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input  type='checkbox' name='docobsgrps["+ i +"]' value='lgbtipersons'  "+ docobsgrpsselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ docobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
							+ "<div id='addgroupaffectedbutton'> \n" 
								+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='docobsdiv"+ i +"' inputCheckBoxName='docobsgrps'><br> \n"
							+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "
					
						+ "<tr> \n "
							+ "<td class='col1' >Administrative and Institutional</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='geoobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ geoobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ geoobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ geoobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ geoobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='geoobscomments["+ i +"]'>" + AllRightsGrpObs[i][11] + "</textarea> \n "
							+ "</td> \n " 	
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='geoobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='women'  "+ geoobsgrpsselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='men' "+ geoobsgrpsselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='girls'  "+ geoobsgrpsselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='boys' "+ geoobsgrpsselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='olderwomen'  "+ geoobsgrpsselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='oldermen'  "+ geoobsgrpsselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='femalesdisabilities'  "+ geoobsgrpsselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='geoobsgrps["+ i +"]' value='malesdisabilities'  "+ geoobsgrpsselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input  type='checkbox' name='geoobsgrps["+ i +"]' value='lgbtipersons'  "+ geoobsgrpsselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ geoobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='geoobsdiv"+ i +"' inputCheckBoxName='geoobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "
					
						+ "<tr> \n "
							+ "<td class='col1' >Security</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='adminobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ adminobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ adminobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ adminobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ adminobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='adminobscomments["+ i +"]'>" + AllRightsGrpObs[i][14] + "</textarea> \n "
							+ "</td> \n " 	
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='adminobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='women'  "+ adminobsgrpselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='men' "+ adminobsgrpselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='girls'  "+ adminobsgrpselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='boys' "+ adminobsgrpselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='olderwomen'  "+ adminobsgrpselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='oldermen'  "+ adminobsgrpselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='femalesdisabilities'  "+ adminobsgrpselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='adminobsgrps["+ i +"]' value='malesdisabilities'  "+ adminobsgrpselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input  type='checkbox' name='adminobsgrps["+ i +"]' value='lgbtipersons'  "+ adminobsgrpselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ adminobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='adminobsdiv"+ i +"' inputCheckBoxName='adminobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "

						+ "<tr> \n "
							+ "<td class='col1' >Discrimination</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='secobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ secobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ secobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ secobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ secobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='secobscomments["+ i +"]'>" + AllRightsGrpObs[i][17] + "</textarea> \n "
							+ "</td> \n " 	
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='secobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='women'  "+ secobsgrpselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='men' "+ secobsgrpselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='girls'  "+ secobsgrpselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='boys' "+ secobsgrpselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='olderwomen'  "+ secobsgrpselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='oldermen'  "+ secobsgrpselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='femalesdisabilities'  "+ secobsgrpselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='malesdisabilities'  "+ secobsgrpselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='secobsgrps["+ i +"]' value='lgbtipersons'  "+ secobsgrpselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ secobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='secobsdiv"+ i +"' inputCheckBoxName='secobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "	
					
						+ "<tr> \n "
							+ "<td class='col1' >Lack of Information</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='discrimobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ discrimobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ discrimobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ discrimobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ discrimobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='discrimobscomments["+ i +"]'>" + AllRightsGrpObs[i][20] + "</textarea> \n "
							+ "</td> \n " 	
							+ "<td class='col4'> \n"
								+ "<div id='groupaffected'> \n"
									+ "<div id='discrimobsdiv"+ i +"'> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='women'  "+ discrimobsgrpselected[5] +"><label>Women</label> <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='men' "+ discrimobsgrpselected[0] +"><label>Men</label>  <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='girls'  "+ discrimobsgrpselected[6] +"><label>Girls</label>  <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='boys' "+ discrimobsgrpselected[1] +" ><label>Boys</label>  <br> \n"	
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='olderwomen'  "+ discrimobsgrpselected[7] +"><label>Older Women</label>  <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='oldermen'  "+ discrimobsgrpselected[2] +"><label>Older Men</label>  <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='femalesdisabilities'  "+ discrimobsgrpselected[8] +"><label>Females With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='malesdisabilities'  "+ discrimobsgrpselected[3] +"><label>Males With Disabilities</label> <br> \n"
										+ "<input type='checkbox' name='discrimobsgrps["+ i +"]' value='lgbtipersons'  "+ discrimobsgrpselected[4] +"><label>LGBTI Persons</label> <br> \n "
										+ ""+ discrimobsgrpsother +""									
									+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='discrimobsdiv"+ i +"' inputCheckBoxName='discrimobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "	
							
						+ "<tr> \n "
							+ "<td class='col1' >Other: "
								+ "<input type='text' name='otherobsname["+ i +"]' value='"+ AllRightsGrpObs[i][22].replaceAll("'", "&#39;").replaceAll("\"", "&#34;")  +"' placeholder='Name of Obstacle'>\n"  
							+ "</td> \n "
							+ "<td class='col2'> \n"
								+ "<select id='obstacleoptions' name='otherobsdrop["+ i +"]'> \n "
									+ "<option value=''></option> \n "
									+ "<option value='significant' "+ otherobsselected[0] +">Determinative obstacles, "
										+ "effectively preventing access to the right.</option> \n "
									+ "<option value='moderate' "+ otherobsselected[1] +">Some obstacles, "
										+ "making access to the right a challenge.</option> \n "
									+ "<option value='none' "+ otherobsselected[2] +">No or minor obstacles.</option> \n "
									+ "<option value='unknown' "+ otherobsselected[3] +">Unknown</option> \n "
								+ "</select> \n "
							+ "</td> \n "
							+ "<td class='col3'> \n"
								+ "<textarea name='otherobscomments["+ i +"]'>" + AllRightsGrpObs[i][24] + "</textarea> \n "
							+ "</td> \n " 	
							+ "<td class='col4'> \n"
							+ "<div id='groupaffected'> \n"
								+ "<div id='otherobsdiv"+ i +"'> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='women'  "+ otherobsgrpselected[5] +"><label>Women</label> <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='men' "+ otherobsgrpselected[0] +"><label>Men</label>  <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='girls'  "+ otherobsgrpselected[6] +"><label>Girls</label>  <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='boys' "+ otherobsgrpselected[1] +" ><label>Boys</label>  <br> \n"	
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='olderwomen'  "+ otherobsgrpselected[7] +"><label>Older Women</label>  <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='oldermen'  "+ otherobsgrpselected[2] +"><label>Older Men</label>  <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='femalesdisabilities'  "+ otherobsgrpselected[8] +"><label>Females With Disabilities</label> <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='malesdisabilities'  "+ otherobsgrpselected[3] +"><label>Males With Disabilities</label> <br> \n"
									+ "<input type='checkbox' name='otherobsgrps["+ i +"]' value='lgbtipersons'  "+ otherobsgrpselected[4] +"><label>LGBTI Persons</label> <br> \n "
									+ ""+ otherobsgrpsother +""									
								+ "</div> \n"
								+ "</div> \n"
								+ "<div id='addgroupaffectedbutton'> \n" 
									+ "<input class='addgroupbutton' type='button' name='addgroup' value='Add Group' arrayIndex='"+i+"' divname='otherobsdiv"+ i +"' inputCheckBoxName='otherobsgrps'><br> \n"
								+ "</div> \n"
							+ "</td> \n " 
						+ "</tr> \n "		
					
					+ "</tbody> \n "
				+ "</table> \n "
				+ "</div> \n";
			
			htmlWithGroupNames[i][0] = rightsCategoryGroupName;
			htmlWithGroupNames[i][1] = htmlTable;

			
			i++;
		}

    	//Group the rights categories by rights groups. 
    	for(int z = 0; z < RightsGroupNames.size();z++) {
    		
    		RightsGroupNames.get(z);
    		finalHTMLTable = finalHTMLTable + "<p class='obstaclesub'>"+RightsGroupNames.get(z) +"</p>";

        	for(int x = 0; x < AllRightsGrpObs.length;x++){
        		if(RightsGroupNames.get(z).equals(htmlWithGroupNames[x][0])){
        			finalHTMLTable = finalHTMLTable + htmlWithGroupNames[x][1]; 
        		} 
        	}
    	}
    	

		return finalHTMLTable ;  
	}
	
	public static String formatObstacleDocumentation(Country countryObj) {
		
		String html = new String(""); 
		String AllObstaclesDocumenation[][] = countryObj.getObstaclesDocumenation();

        int i = 0;
    	
        if (AllObstaclesDocumenation[0][0] != "noData"){
	    	while(AllObstaclesDocumenation[i][0] != null) {
 		
	    		html = html + ""
	    				+ "<a class='docVisibleLink' href='"+AllObstaclesDocumenation[i][2] +"'>"+AllObstaclesDocumenation[i][1]+"</a><br> <br>\n"
	    				+ "<input type='hidden' name='docStorageName["+i+"]' value='"+AllObstaclesDocumenation[i][0]+"'> \n"
	    				+ "<input type='hidden' name='docDisplayName["+i+"]' value='"+AllObstaclesDocumenation[i][1]+"'> \n"
	    				+ "<input type='hidden' name='docURL["+i+"]' value='"+AllObstaclesDocumenation[i][2]+"'> \n";
	    		
	    		i++;
	    	}
    	}	
    	
    	return html;
	}	
}
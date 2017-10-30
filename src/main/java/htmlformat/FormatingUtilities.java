package htmlformat;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.bytecode.opencsv.CSVReader;

public class FormatingUtilities {

	// This method takes a string of checked radio boxes and a string of all
	// check radio box options and returns an array
	// of all of the checked radio boxes.
	public static String[] setCheckedRadioButtons(String checkTestString, String buttons) {

		String[] splitButtons = buttons.split(",");
		int splitButtonsSize = splitButtons.length;
		String[] checkedRadioButtons = new String[splitButtonsSize];

		for (int i = 0; i < splitButtonsSize; i++) {
			if (splitButtons[i].equals(checkTestString)) {
				checkedRadioButtons[i] = "checked";
			} else {
				checkedRadioButtons[i] = "";
			}
		}
		return checkedRadioButtons;
	}

	// This method takes a string of checked boxes and a string of all check box
	// options and returns an array of all of the checked boxes.
	public static String[] setCheckedBoxes(String checkTestString, String checkboxes) {

		String[] splitCheckboxes = checkboxes.split(",");
		int splitCheckboxesSize = splitCheckboxes.length;
		String[] finalCheckedCheckboxes = new String[splitCheckboxesSize];
		String tempCheckedGroups[] = checkTestString.split(",");
		int tempCheckedGroupsSize = tempCheckedGroups.length;

		for (int i = 0; i < splitCheckboxesSize; i++) {
			String foundCheck = "";
			for (int j = 0; j < tempCheckedGroupsSize; j++) {
				if (splitCheckboxes[i].equals(tempCheckedGroups[j])) {
					foundCheck = "success";
				}
			}
			if (foundCheck.equals("success")) {
				finalCheckedCheckboxes[i] = "checked";
			} else {
				finalCheckedCheckboxes[i] = "";
			}
		}

		return finalCheckedCheckboxes;
	}

	public static String[] setSelectedDropDownOptions(String selectedString, String options) {

		try {
			CSVReader reader = new CSVReader(new StringReader(options));
			String[] CSVString = reader.readNext();
			String[] selectedDropDownOptions = new String[CSVString.length];
			reader.close();

			for (int i = 0; i < CSVString.length; i++) {
				if (CSVString[i].equals(selectedString)) {
					selectedDropDownOptions[i] = "selected";
				} else {
					selectedDropDownOptions[i] = "";
				}
			}

			return selectedDropDownOptions;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// Find the largest values in a string array.
	public static String getMaxValuesOfStringArray(String[] arrayOfMaxValues, String[] arraryOfNames) {

		String returnString = "";
		int tempHighestValueIndex = 0;
		List<Integer> arrayOfHigestValueIndexes = new ArrayList<Integer>();

		// Find the indexes of the largest numbers.
		for (int i = 0; i < arrayOfMaxValues.length; i++) {

			for (int j = 0; j < arrayOfMaxValues.length; j++) {
				if (Float.parseFloat(arrayOfMaxValues[tempHighestValueIndex].replace("-", "0")) < Float.parseFloat(arrayOfMaxValues[j].replace("-", "0"))) {
					tempHighestValueIndex = j;
					break;
				}
			}
		}

		// Check to see if there are duplicates.
		for (int i = 0; i < arrayOfMaxValues.length; i++) {
			if (Float.parseFloat(arrayOfMaxValues[tempHighestValueIndex].replace("-", "0")) == Float.parseFloat(arrayOfMaxValues[i].replace("-", "0"))) {
				arrayOfHigestValueIndexes.add(i);
			}
		}

		// Create the string to return to the calling method.
		for (int i = 0; i < arrayOfHigestValueIndexes.size(); i++) {
			returnString = returnString + "<span class='boldbluetext'>" + arraryOfNames[arrayOfHigestValueIndexes.get(i)] + "</span>";
			if (i + 1 != arrayOfHigestValueIndexes.size()) {
				if (i + 2 == arrayOfHigestValueIndexes.size()) {
					returnString = returnString + " and ";
				} else {
					returnString = returnString + ", ";
				}
			}

		}

		return returnString;
	}

	// Find the smallest values in a string array.
	public static String getMinValuesOfStringArray(String[] arrayOfValuesToEvaluate, String[] arraryOfNames) {

		String returnString = "";
		int tempLowestValueIndex = 0;
		List<Integer> arrayOfLowestValueIndexes = new ArrayList<Integer>();

		// Find the indexes of the largest numbers.
		for (int i = 0; i < arrayOfValuesToEvaluate.length; i++) {

			if (arrayOfValuesToEvaluate[tempLowestValueIndex] == "-") {
				tempLowestValueIndex++;
				continue;
			}

			for (int j = 0; j < arrayOfValuesToEvaluate.length; j++) {

				// Skip to the next value if the current one is a dash ("-");
				if ((arrayOfValuesToEvaluate[j] == "-")) {
					continue;
				}

				if (Float.parseFloat(arrayOfValuesToEvaluate[tempLowestValueIndex]) > Float.parseFloat(arrayOfValuesToEvaluate[j])) {
					tempLowestValueIndex = j;
					break;
				}
			}
		}

		// Check to see if there are duplicates.
		for (int i = 0; i < arrayOfValuesToEvaluate.length; i++) {
			// Skip to the next value if the current one is a dash ("-");
			if ((arrayOfValuesToEvaluate[i] == "-") || (arrayOfValuesToEvaluate[tempLowestValueIndex] == "-")) {
				continue;
			}
			if (Float.parseFloat(arrayOfValuesToEvaluate[tempLowestValueIndex]) == Float.parseFloat(arrayOfValuesToEvaluate[i])) {
				arrayOfLowestValueIndexes.add(i);
			}
		}

		// Create the string to return to the calling method.
		for (int i = 0; i < arrayOfLowestValueIndexes.size(); i++) {
			returnString = returnString + "<span class='boldbluetext'>" + arraryOfNames[arrayOfLowestValueIndexes.get(i)] + "</span>";
			if (i + 1 != arrayOfLowestValueIndexes.size()) {
				if (i + 2 == arrayOfLowestValueIndexes.size()) {
					returnString = returnString + " and ";
				} else {
					returnString = returnString + ", ";
				}
			}
		}

		return returnString;
	}

	// Test to make sure that an array is not just filled with dashes. This
	// pertains to the analytics tables.
	public static boolean valuesExist(String[] arrayOfValues) {
		boolean returnTrueFalse = false;

		for (int i = 0; i < arrayOfValues.length; i++) {

			if (arrayOfValues[i] != "-") {
				returnTrueFalse = true;
			}
		}

		return returnTrueFalse;

	}

	// Format the new groups impacted by Obstacles.
	public static String formatObstacleGroups(int arrayIndex, String arrayOfValues, String obstacleType, String oldGroups) {
		String finalHtml = "";
		List<String> newGroups = new ArrayList<String>();

		// If there are no new groups then leave the function.
		if (!arrayOfValues.isEmpty()) {

			// Test to see if there are more than one new group (by checking for
			// the ",:" separator).
			// If there is only one new group than just return the one new
			// group.
			if (arrayOfValues.contains(",:")) {

				String[] spliteArrayOfValues = arrayOfValues.split(",:");

				for (int i = 0; i < spliteArrayOfValues.length; i++) {
					newGroups.add(spliteArrayOfValues[i].replaceAll(",", ", "));
				}
				// Create HTML for the new groups.
				for (int j = 0; j < newGroups.size(); j++) {
					finalHtml = finalHtml + "<input  type='checkbox' name='" + obstacleType + "obsgrps[" + arrayIndex + "]' value=':" + newGroups.get(j).replaceAll("'", "&#39;") + "' checked><label>"
							+ newGroups.get(j) + "</label> <br> \n";
				}
			} else {
				// Create HTML for the one new groups.
				finalHtml = "<input  type='checkbox' name='" + obstacleType + "obsgrps[" + arrayIndex + "]' value=':" + arrayOfValues.replaceAll("'", "&#39;") + "' checked><label>"
						+ arrayOfValues.replaceAll(",", ", ") + "</label> <br> \n";
			}
		}

		return finalHtml;

	}

	// Divide the obstacle groups into new groups and old groups.
	public static String[] getNewOldGroups(String arrayOfGroups) {
		String[] oldNewGroups = new String[2];
		String oldGroups = "";
		String newGroups = "";

		if (arrayOfGroups.isEmpty()) {
			// If there are no groups at all then set to the empty string.
			oldGroups = "";
			newGroups = "";

		} else if (arrayOfGroups.substring(0, 1).equals(":")) {
			// If there are only new groups set the groups to the new group
			// variable.

			oldGroups = "";
			newGroups = arrayOfGroups.substring(1);

		} else if (!arrayOfGroups.contains(":")) {
			// If there are no new groups then set the groups to the old group
			// variable.

			oldGroups = arrayOfGroups;
			newGroups = "";

		} else {
			// If there are new groups and old groups then split them and add
			// them to the appropriate variable.

			Pattern pattern = Pattern.compile("(([A-Za-z]+,)+):(.*)");
			Matcher matcher = pattern.matcher(arrayOfGroups);
			if (matcher.find()) {
				oldGroups = matcher.group(1);
				newGroups = matcher.group(3);
			}
		}

		oldNewGroups[0] = oldGroups;
		oldNewGroups[1] = newGroups;

		return oldNewGroups;

	}


	
	
	//Format the grounds on which an instrument discriminates for the National Instruments section of the reports. 
	//This function is called by FormatDataColBReporting.java 
	public static String formatNatInstrurmentsDiscrimString(String discrimGroups, String otherDiscrimGrp) {

		String[] groupsInstrumentDiscriminatesSplit = discrimGroups.split(",");
		
		String groupsInstrumentDiscriminatesFinal = "";
		
		int ii = 0;
		while (ii< groupsInstrumentDiscriminatesSplit.length){
			
			if(!(groupsInstrumentDiscriminatesSplit[ii].equals("other")) ){
			
				groupsInstrumentDiscriminatesFinal = groupsInstrumentDiscriminatesFinal + FormatingUtilities.capitalizeString(groupsInstrumentDiscriminatesSplit[ii]) + ", ";
			} else {
				
				groupsInstrumentDiscriminatesFinal = groupsInstrumentDiscriminatesFinal +  otherDiscrimGrp + ", ";
			}
			ii++;
		}
		
		groupsInstrumentDiscriminatesFinal = groupsInstrumentDiscriminatesFinal.substring(0,groupsInstrumentDiscriminatesFinal.length() - 2);
		return groupsInstrumentDiscriminatesFinal;

	}
	
	
	//Format the links to other Rights Categories for the National Instruments section of the reports. 
	//This function is called by FormatDataColBReporting.java 
	public static String formatNatInstruLinks(String linksOtherRightsCats) {

		String linksOtherRightsCatsFinal = "";
		String[] linksOtherRightsCatsSplit = linksOtherRightsCats.split(",");
		
		int ii = 0;
		while (ii< linksOtherRightsCatsSplit.length){
			
			
			if(linksOtherRightsCatsSplit[ii].equals("docu")){
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Documentation<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("edu")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Education<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("fair")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Access to Justics<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("fam")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Family Unity<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("free")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Freedom of Movement<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("heal")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Health<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("housing")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Housing, Land and Property<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("lib")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Liberty and Security of Person<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("nondis")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Non-Discrimination<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("poli")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Political Participation<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("work")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Right to Work and Rights at Work<br>";
			} else if(linksOtherRightsCatsSplit[ii].equals("soc")) {
				linksOtherRightsCatsFinal = linksOtherRightsCatsFinal + "Social Security<br>";
			}
			ii++;
		}
		
		if (linksOtherRightsCatsFinal.length()>4) {
			linksOtherRightsCatsFinal = linksOtherRightsCatsFinal.substring(0,linksOtherRightsCatsFinal.length() - 4);
		}
		return linksOtherRightsCatsFinal;

	}
	
	//Format the comments boxes in the National Instruments section of the reports. 
	//This function is called by FormatDataColBReporting.java 
	public static String formatReportCommentNatInstru(String Title, String Text) {
		String finalString = "";
	   if(!(Text.equals(""))){
		   finalString = "<br><br>" + Title + " " + Text;
	   } 
		return finalString;
	}
	
	
	//Format the comments boxes in the National Instruments section of the reports. 
	//This function is called by FormatDataColBReporting.java 
	public static String formatInstrumentData(String Text) {
		String finalString = "";
	   if((Text.equals("notaparty"))){
		   finalString = "Not a Party";
	   } else if((Text.equals("state"))){
		   finalString = "Part(s)<br><br>";
	   } else if((Text.equals("federal"))){
		   finalString = "All<br><br>";
	   } else {
		   finalString = capitalizeString(Text);
	   }
	   
		return finalString;
	}
	
	public static String capitalizeString(String upcapString) {

		String capString = "";
		
		
	    if (!(upcapString == null || upcapString.length() == 0)) {
	    
	    	capString = upcapString.substring(0, 1).toUpperCase() + upcapString.substring(1);
	    }
	    
		return capString;

	}
	
	//Format the comments boxes in the National Instruments section of the reports. 
	//This function is called by FormatDataColBReporting.java 
	public static String formatAddBreakIfNotEmpty(String Text) {
		String finalString = "";
	   if(!(Text.equals(""))){
		   finalString = Text + "<br><br>";
	   } 
		return finalString;
	}
	
}

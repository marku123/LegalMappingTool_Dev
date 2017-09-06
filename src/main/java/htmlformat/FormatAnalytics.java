package htmlformat;

import java.util.Arrays;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.RightsCategoriesManagement;
import model.Country;

public class FormatAnalytics {

	public static String formatFindingsTable(Country countryObj) {

		String htmlTable = new String();
		String[][] ConsistencyWithInternational = countryObj.getConsistencyWithInternational();
		String[] ConsistencyWithInternationalRatingOnly = new String[12];
		String[] AllPOCRightsGrouopsObstacles = countryObj.getAllPOCRightsGrouopsObstacles();
		String[] POCALLRightsGroupsObtacles = countryObj.getPOCALLRightsGroupsObtacles();
		String POCObstaclesDetail[][] = countryObj.getPOCObstaclesDetail();
		String POCNatInstrumentsSummary[] = countryObj.getNatInstruPOCSummary();
		Boolean missingDataConsistencyWithInternational = countryObj.getConsistencyWithInternationalDataMissing();
		Boolean NatInstruPOCDataMissing = countryObj.getNatInstruPOCDataMissing();
		Boolean ObstaclesMissingData = countryObj.getObstaclesMissingData();
		int numOfRightsCategories = CountRows.countRows("rightscategory");

		String leastConsistentRightsGroups;
		String natInstrumentsSupportRightsGroups;
		String greatestObstaclesRights;
		String greatestObstaclePOC;
		String greatestObstacles;

		String[] RightsGroupsNames = new String[numOfRightsCategories];
		String[] POCNames = new String[5];
		String[] POCNamesNationals = new String[6];

		String[] ObstacleNames = new String[9];

		POCNames[0] = "Asylum Seekers";
		POCNames[1] = "Internally Displaced Persons";
		POCNames[2] = "Refugees";
		POCNames[3] = "Returnees";
		POCNames[4] = "Stateless Persons";

		POCNamesNationals[0] = "Asylum Seekers";
		POCNamesNationals[1] = "Internally Displaced Persons";
		POCNamesNationals[2] = "Nationals";
		POCNamesNationals[3] = "Refugees";
		POCNamesNationals[4] = "Returnees";
		POCNamesNationals[5] = "Stateless Persons";

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();
		
		ObstacleNames[0] = "Legal Status and Documentation";
		ObstacleNames[1] = "Financial";
		ObstacleNames[2] = "Geographic";
		ObstacleNames[3] = "Administrative and Institutional";
		ObstacleNames[4] = "Security";
		ObstacleNames[5] = "Discrimination";
		ObstacleNames[6] = "Lack of Information";
		ObstacleNames[7] = "Other";

		// Extract the array for the
		// "consistency with international instruments" array. It needs to be
		// put into a 1 dimensional
		// array before I can go further.
		for (int i = 0; i < ConsistencyWithInternational.length; i++) {
			ConsistencyWithInternationalRatingOnly[i] = ConsistencyWithInternational[i][2];
		}

		// Check to see if the arrays have data in them. If so, format the
		// arrays.
		if (FormatingUtilities.valuesExist(ConsistencyWithInternationalRatingOnly)) {
			leastConsistentRightsGroups = FormatingUtilities.getMinValuesOfStringArray(ConsistencyWithInternationalRatingOnly, RightsGroupsNames);
			// Check to see if there is data missing for this item.
			if (missingDataConsistencyWithInternational) {
				leastConsistentRightsGroups = leastConsistentRightsGroups + "<span class='missingDataAsterisk'>*</span>";
			}

		} else {
			leastConsistentRightsGroups = "No Data Currently Available";
		}

		if (FormatingUtilities.valuesExist(POCNatInstrumentsSummary)) {
			natInstrumentsSupportRightsGroups = FormatingUtilities.getMinValuesOfStringArray(POCNatInstrumentsSummary, POCNamesNationals);
			if (NatInstruPOCDataMissing) {
				natInstrumentsSupportRightsGroups = natInstrumentsSupportRightsGroups + "<span class='missingDataAsterisk'>*</span>";
			}

		} else {
			natInstrumentsSupportRightsGroups = "No Data Currently Available";
		}

		if (FormatingUtilities.valuesExist(AllPOCRightsGrouopsObstacles)) {
			greatestObstaclesRights = FormatingUtilities.getMaxValuesOfStringArray(AllPOCRightsGrouopsObstacles, RightsGroupsNames);
			if (ObstaclesMissingData) {
				greatestObstaclesRights = greatestObstaclesRights + "<span class='missingDataAsterisk'>*</span>";
			}

		} else {
			greatestObstaclesRights = "No Data Currently Available";
		}

		if (FormatingUtilities.valuesExist(POCALLRightsGroupsObtacles)) {
			greatestObstaclePOC = FormatingUtilities.getMaxValuesOfStringArray(POCALLRightsGroupsObtacles, POCNames);
			if (ObstaclesMissingData) {
				greatestObstaclePOC = greatestObstaclePOC + "<span class='missingDataAsterisk'>*</span>";
			}
		} else {
			greatestObstaclePOC = "No Data Currently Available";
		}

		if (FormatingUtilities.valuesExist(Arrays.copyOf(POCObstaclesDetail[5], POCObstaclesDetail[5].length - 1))) {
			// Get rid of the last element in the array. It is for All persons
			// of concern.

			greatestObstacles = FormatingUtilities.getMaxValuesOfStringArray(Arrays.copyOf(POCObstaclesDetail[5], POCObstaclesDetail[5].length - 1), ObstacleNames) + " Obstacles";
			if (ObstaclesMissingData) {
				greatestObstacles = greatestObstacles + "<span class='missingDataAsterisk'>*</span>";
			}
		
		} else {
			greatestObstacles = "No Data Currently Available";
		}

		htmlTable = "" + "<table id='obstacleskeyfindings'> \n" + "<thead> \n" + "<tr> \n" + "<th colspan='2'> Summary Data Findings</th> \n" + "</tr> \n" + "</thead> \n" + "<tbody> \n" + "<tr>	\n"
				+ "<td>National instruments related to the following rights category(s) are the least consistent with " + "international standards:</td> \n" + "<td> "
				+ leastConsistentRightsGroups
				+ "</td> \n"
				+ "</tr> \n"
				+ "<tr>	\n"
				+ "<td>National instruments provide the least support to the following populations of concern:</td> \n"
				+ "<td> "
				+ natInstrumentsSupportRightsGroups
				+ "</td> \n"
				+ "</tr> \n"
				+ "<tr>	\n"
				+ "<td>Populations of concern face the greatest obstacles to enjoying rights in the following rights category(s):</td> \n"
				+ "<td> "
				+ greatestObstaclesRights
				+ "</td> \n"
				+ "</tr> \n"
				+ "<tr>	\n"
				+ "<td> The populations of concern that face the greatest number of obstacles in being able to enjoy their rights are:</td> \n"
				+ "<td> "
				+ greatestObstaclePOC
				+ "</td> \n"
				+ "</tr> \n"
				+ "<tr>	\n"
				+ "<td> The most significant obstacle(s) facing populations of concern are:</td> \n"
				+ "<td> "
				+ greatestObstacles
				+ "</td> \n" + "</tr> \n" + "</table> \n";

		if (missingDataConsistencyWithInternational || NatInstruPOCDataMissing || ObstaclesMissingData) {
			htmlTable = htmlTable + "" + "<div id=\"findingstable-missingdata\"><p>*The data in the Legal Mapping Tool is incomplete. The findings will be more accurate once "
					+ "all of the relevant data has been entered.</p></div>";
		}

		return htmlTable;
	}

	public static String formatLegalFrameworkConsistencyChart(Country countryObj) {

		String chartData = "";
		String ConsistencyWithInternational[][] = countryObj.getConsistencyWithInternational();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsGroupsNames = new String[numOfRightsCategories];

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();

		// Formatting for the parent chart.

		for (int i = 0; i < RightsGroupsNames.length; i++) {
			chartData = chartData + "" + "{ color : '#EFD468', name: '" + RightsGroupsNames[i] + "', y : " + ConsistencyWithInternational[i][2].replace("-", "null") + " },";
		}

		// Remove the last comma from the output data.
		chartData = chartData.substring(0, chartData.length() - 1);
		return chartData;
	}

	public static String formatLegalFrameworkConsistencyMissingData(Country countryObj) {
		String htmlMissingData = new String();
		Boolean missingData = countryObj.getConsistencyWithInternationalDataMissing();

		if (missingData) {

			htmlMissingData = "<p>Note: The data in the Legal Mapping Tool is incomplete. The analytics in the above chart will be more accurate once "
					+ "all relevant data has been entered.</p>";
		}
		return htmlMissingData;

	}

	public static String formatLegalFrameworkConsistencyTable(Country countryObj) {

		String htmlTable = new String();
		String ConsistencyWithInternational[][] = countryObj.getConsistencyWithInternational();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsGroupsNames = new String[numOfRightsCategories];

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();

		htmlTable = "" + "<table id='consistencyinternationaltabletitle' >" + "<thead> \n" + "<tr > \n" + "<th colspan='5'>"
				+ "The Consistency of National Legislations/Regulations with International Standards \n" + "</th>" + "</tr> \n" + "</thead> \n" + "</table>"
				+ "<table id='consistencyinternationaltable' class='sortable'> \n" + "<thead> \n"

				+ "<tr> \n" + "<th> Rights Category</th> \n" + "<th> Number of National Instruments Related to the Rights Category</th> \n"
				+ "<th> Rating of the Consistency of the National Instruments with International Standards " + "<br>(1=\"Not Consistent\", 3=\"Consistent\")</th> \n" + "</tr> \n" + "</thead> \n"
				+ "<tbody> \n";

		for (int i = 0; i < RightsGroupsNames.length; i++) {
			htmlTable = htmlTable + "" + "<tr>	\n" + "<td> " + RightsGroupsNames[i] + " </td> \n" + "<td> " + ConsistencyWithInternational[i][1] + "</td> \n" + "<td> "
					+ ConsistencyWithInternational[i][2] + " </td> \n" + "</tr> \n";

		}
		htmlTable = htmlTable + "</tbody> \n";

		htmlTable = htmlTable + "" + "</table> \n";

		return htmlTable;
	}

	public static String formatPOCNatInstrumentsChartSummary(Country countryObj) {

		String chartData;
		String POCNatInstrumentsSummary[] = countryObj.getNatInstruPOCSummary();
		String[] POCNames = new String[6];

		POCNames[0] = "Asylum Seekers";
		POCNames[1] = "Internally Displaced Persons";
		POCNames[2] = "Nationals";
		POCNames[3] = "Refugees";
		POCNames[4] = "Returnees";
		POCNames[5] = "Stateless Persons";

		chartData = "" + "{ color : '#EFD468', name: '" + POCNames[0] + "', y : " + POCNatInstrumentsSummary[0].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[1] + "', y : "
				+ POCNatInstrumentsSummary[1].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[2] + "', y : " + POCNatInstrumentsSummary[2].replace("-", "null") + "},"
				+ "{ color : '#EFD468', name: '" + POCNames[3] + "', y : " + POCNatInstrumentsSummary[3].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[4] + "', y : "
				+ POCNatInstrumentsSummary[4].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[5] + "', y : " + POCNatInstrumentsSummary[5].replace("-", "null") + "}";

		return chartData;
	}

	public static String formatPOCNatInstrumentsMissingData(Country countryObj) {
		String htmlMissingData = new String();
		Boolean missingData = countryObj.getNatInstruPOCDataMissing();

		if (missingData) {

			htmlMissingData = "<p>Note: The data in the Legal Mapping Tool is incomplete. The analytics in the above chart will be more accurate once "
					+ "all relevant data has been entered.</p>";		
			}
		return htmlMissingData;

	}

	public static String formatPOCNatInstrumentsTable(Country countryObj) {

		String htmlTable = new String();
		String NatInstruPOCDetail[][] = countryObj.getNatInstruPOCDetail();
		String NatInstruPOCSummary[] = countryObj.getNatInstruPOCSummary();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsGroupsNames = new String[numOfRightsCategories];

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();

		htmlTable = "" + "<table id='tabletitle' >" + "<thead> \n" + "<tr > \n" + "<th colspan='5'>"
				+ "Rating National Legal Instrument Support for the Rights of Nationals and Populations of Concern\n" + "<br>(1=\"National Instruments Do Not Support the Rights Category\", "
				+ "3=\"National Instruments Support the Rights Category\") \n" + "</th>" + "</tr> \n" + "</thead> \n" + "</table>" + "<table id='obstaclesdetail' class='sortable'> \n" + "<thead> \n"

				+ "<tr> \n" + "<th> Rights Category </th> \n" + "<th> Asylum Seekers</th> \n" + "<th> Internally Displaced Persons </th> \n" + "<th> Nationals</th> \n" + "<th> Refugees</th> \n"
				+ "<th> Returnees </th> \n" + "<th> Stateless Persons</th> \n" + "</tr> \n" + "</thead> \n" + "<tbody> \n";

		for (int i = 0; i < numOfRightsCategories; i++) {
			htmlTable = htmlTable + "" + "<tr>	\n" + "<td> " + RightsGroupsNames[i] + " </td> \n" + "<td> " + NatInstruPOCDetail[i][0] + " </td> \n" + "<td> " + NatInstruPOCDetail[i][1] + "</td> \n"
					+ "<td> " + NatInstruPOCDetail[i][2] + " </td> \n" + "<td> " + NatInstruPOCDetail[i][3] + " </td> \n" + "<td> " + NatInstruPOCDetail[i][4] + " </td> \n" + "<td> "
					+ NatInstruPOCDetail[i][5] + " </td> \n" + "</tr> \n";

		}
		htmlTable = htmlTable + "</tbody> \n";

		htmlTable = htmlTable + "" + "<tfoot> \n" + "<tr>	\n"

		+ "<td> All Rights: </td> \n" + "<td> " + NatInstruPOCSummary[0] + " </td> \n" + "<td> " + NatInstruPOCSummary[1] + "</td> \n" + "<td> " + NatInstruPOCSummary[2] + " </td> \n" + "<td> "
				+ NatInstruPOCSummary[3] + " </td> \n" + "<td> " + NatInstruPOCSummary[4] + " </td> \n" + "<td> " + NatInstruPOCSummary[5] + " </td> \n" + "</tr> \n" + "</tfoot> \n";

		htmlTable = htmlTable + "" + "</table> \n";

		return htmlTable;
	}

	public static String formatPOCObstaclesChartSummary(Country countryObj) {

		String chartData;
		String POCObstaclesDetail[][] = countryObj.getPOCObstaclesDetail();
		String[] POCNames = new String[6];

		POCNames[0] = "Asylum Seekers";
		POCNames[1] = "Internally Displaced Persons";
		POCNames[2] = "Refugees";
		POCNames[3] = "Returnees";
		POCNames[4] = "Stateless Persons";
		POCNames[5] = "All Populations of Concern";

		chartData = "" + "{ color : '#EFD468', name: '" + POCNames[0] + "', y : " + POCObstaclesDetail[0][8].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[1] + "', y : "
				+ POCObstaclesDetail[1][8].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[2] + "', y : " + POCObstaclesDetail[2][8].replace("-", "null") + "},"
				+ "{ color : '#EFD468', name: '" + POCNames[3] + "', y : " + POCObstaclesDetail[3][8].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[4] + "', y : "
				+ POCObstaclesDetail[4][8].replace("-", "null") + "}," + "{ color : '#EFD468', name: '" + POCNames[5] + "', y : " + POCObstaclesDetail[5][8].replace("-", "null") + "}";

		return chartData;
	}

	public static String formatObstaclesMissingData(Country countryObj) {
		String htmlMissingData = new String();
		Boolean missingData = countryObj.getObstaclesMissingData();

		if (missingData) {

			htmlMissingData = "<p>Note: The data in the Legal Mapping Tool is incomplete. The analytics in the above chart will be more accurate once "
					+ "all relevant data has been entered.</p>";	
			}
		return htmlMissingData;

	}

	public static String formatPOCObstaclesChartDetails(Country countryObj) {

		String chartData = "";
		String POCObstaclesDetail[][] = countryObj.getPOCObstaclesDetail();
		String[] ObstacleNames = new String[8];

		ObstacleNames[0] = "Legal Status and Documentation";
		ObstacleNames[1] = "Financial";
		ObstacleNames[2] = "Geographic";
		ObstacleNames[3] = "Administrative and Institutional";
		ObstacleNames[4] = "Security";
		ObstacleNames[5] = "Discrimination";
		ObstacleNames[6] = "Lack of Information";
		ObstacleNames[7] = "Other";

		for (int i = 0; i < ObstacleNames.length; i++) {
			chartData = chartData + "" + "{ color : '#EFD468', name: '" + ObstacleNames[i] + "', y : " + POCObstaclesDetail[5][i].replace("-", "null") + "},";
		}

		// Remove the last comma from the output data.
		chartData = chartData.substring(0, chartData.length() - 1);
		return chartData;
	}

	public static String formatPOCObstaclesTable(Country countryObj) {

		String htmlTable = new String();
		String POCObstaclesDetail[][] = countryObj.getPOCObstaclesDetail();
		String[] ObstacleNames = new String[8];
		String[] POCNames = new String[6];

		POCNames[0] = "Asylum Seekers";
		POCNames[1] = "Internally Displaced Persons";
		POCNames[2] = "Refugees";
		POCNames[3] = "Returnees";
		POCNames[4] = "Stateless Persons";
		POCNames[5] = "All Populations of Concern";

		ObstacleNames[0] = "Legal Status and Documentation";
		ObstacleNames[1] = "Financial";
		ObstacleNames[2] = "Geographic";
		ObstacleNames[3] = "Administrative and Institutional";
		ObstacleNames[4] = "Security";
		ObstacleNames[5] = "Discrimination";
		ObstacleNames[6] = "Lack of Information";
		ObstacleNames[7] = "Other";

		htmlTable = "" + "<table id='tabletitle' >" + "<thead> \n" + "<tr > \n" + "<th colspan='5'>" + "Rating the Obstacles Preventing Populations of Concern From Enjoying Their Rights"
				+ "<br>(1=\"The Obstacles Do Not, or Minimally, Prevent Populations of Concern From Enjoying Their Rights\",<br> "
				+ "3=\"The Obstacles Determinatively Prevent Populations of Concern From Enjoying Their Rights\") \n" + "</th>" + "</tr> \n" + "</thead> \n" + "</table>"
				+ "<table id='obstaclesdetail' class='sortable'> \n" + "<thead> \n"

				+ "<tr> \n" + "<th> Obstacles </th> \n" + "<th> " + POCNames[0] + " </th> \n" + "<th> " + POCNames[1] + "</th> \n" + "<th> " + POCNames[2] + "</th> \n" + "<th> " + POCNames[3]
				+ "</th> \n" + "<th> " + POCNames[4] + "</th> \n" + "<th> " + POCNames[5] + "</th> \n" + "</tr> \n" + "</thead> \n" + "<tbody> \n";

		for (int i = 0; i < ObstacleNames.length; i++) {
			htmlTable = htmlTable + "" + "<tr>	\n" + "<td> " + ObstacleNames[i] + " </td> \n" + "<td> " + POCObstaclesDetail[0][i] + " </td> \n" + "<td> " + POCObstaclesDetail[1][i] + "</td> \n"
					+ "<td> " + POCObstaclesDetail[2][i] + " </td> \n" + "<td> " + POCObstaclesDetail[3][i] + " </td> \n" + "<td> " + POCObstaclesDetail[4][i] + " </td> \n" + "<td> "
					+ POCObstaclesDetail[5][i] + " </td> \n" + "</tr> \n";

		}
		htmlTable = htmlTable + "</tbody> \n";

		htmlTable = htmlTable + "" + "<tfoot> \n" + "<tr>	\n"

		+ "<td> All Obstacles: </td> \n" + "<td> " + POCObstaclesDetail[0][8] + " </td> \n" + "<td> " + POCObstaclesDetail[1][8] + "</td> \n" + "<td> " + POCObstaclesDetail[2][8] + " </td> \n"
				+ "<td> " + POCObstaclesDetail[3][8] + " </td> \n" + "<td> " + POCObstaclesDetail[4][8] + " </td> \n" + "<td> " + POCObstaclesDetail[5][8] + " </td> \n" + "</tr> \n" + "</tfoot> \n";

		htmlTable = htmlTable + "" + "</table> \n";

		return htmlTable;
	}

	public static String formatPOCRightsGroupsObstaclesCharts(Country countryObj) {

		String chartData = "";
		String AllPOCRightsGrouopsObstacles[] = countryObj.getAllPOCRightsGrouopsObstacles();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsGroupsNames = new String[numOfRightsCategories];

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();

		for (int i = 0; i < RightsGroupsNames.length; i++) {
			chartData = chartData + "" + "{ color : '#EFD468', name: '" + RightsGroupsNames[i] + "', y : " + AllPOCRightsGrouopsObstacles[i].replace("-", "null") + "},";
		}

		// Remove the last comma from the output data.
		chartData = chartData.substring(0, chartData.length() - 1);
		return chartData;
	}

	public static String formatPOCRightsGroupsObstaclesTable(Country countryObj) {

		String htmlTable = new String();
		String POCRightsGrouopsObstacles[][] = countryObj.getPOCRightsGrouopsObstacles();
		String AllPOCRightsGrouopsObstacles[] = countryObj.getAllPOCRightsGrouopsObstacles();
		String POCALLRightsGroupsObtacles[] = countryObj.getPOCALLRightsGroupsObtacles();
		String AllPOCALLRightsGroupsObtacles = countryObj.getAllPOCALLRightsGroupsObtacles();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsGroupsNames = new String[numOfRightsCategories];

		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();

		htmlTable = "" + "<table id='tabletitle' >" + "<thead> \n" + "<tr > \n" + "<th colspan='5'>" + "Rating the Accessibility of Rights to Populations of Concern\n"
				+ "<br>(1=\"There Are No, or Minimal, Obstacles to Enjoying the Rights in the Rights Category\", "
				+ "3=\"There Are Determinative Obstacles to Enjoying the Rights in the Rights Category\") \n" + "</th>" + "</tr> \n" + "</thead> \n" + "</table>"
				+ "<table id='obstaclesdetail' class='sortable'> \n" + "<thead> \n"

				+ "<tr> \n" + "<th> Rights Category </th> \n" + "<th> Asylum Seekers</th> \n" + "<th> Internally Displaced Persons </th> \n" + "<th> Refugees</th> \n" + "<th> Returnees </th> \n"
				+ "<th> Stateless Persons</th> \n" + "<th> All Populations of Concern</th> \n" + "</tr> \n" + "</thead> \n" + "<tbody> \n";

		for (int i = 0; i < RightsGroupsNames.length; i++) {
			htmlTable = htmlTable + "" + "<tr>	\n" + "<td> " + RightsGroupsNames[i] + " </td> \n" + "<td> " + POCRightsGrouopsObstacles[0][i] + " </td> \n" + "<td> " + POCRightsGrouopsObstacles[1][i]
					+ "</td> \n" + "<td> " + POCRightsGrouopsObstacles[2][i] + " </td> \n" + "<td> " + POCRightsGrouopsObstacles[3][i] + " </td> \n" + "<td> " + POCRightsGrouopsObstacles[4][i]
					+ " </td> \n" + "<td> " + AllPOCRightsGrouopsObstacles[i] + " </td> \n" + "</tr> \n";

		}
		htmlTable = htmlTable + "</tbody> \n";

		htmlTable = htmlTable + "" + "<tfoot> \n" + "<tr>	\n"

		+ "<td> All Rights: </td> \n" + "<td> " + POCALLRightsGroupsObtacles[0] + " </td> \n" + "<td> " + POCALLRightsGroupsObtacles[1] + "</td> \n" + "<td> " + POCALLRightsGroupsObtacles[2]
				+ " </td> \n" + "<td> " + POCALLRightsGroupsObtacles[3] + " </td> \n" + "<td> " + POCALLRightsGroupsObtacles[4] + " </td> \n" + "<td> " + AllPOCALLRightsGroupsObtacles + " </td> \n"
				+ "</tr> \n" + "</tfoot> \n";

		htmlTable = htmlTable + "" + "</table> \n";

		return htmlTable;
	}
}

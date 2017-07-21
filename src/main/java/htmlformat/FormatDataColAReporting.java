package htmlformat;


import model.Country;

public class FormatDataColAReporting {

	
	public static String formatJudicalEntities(Country countryObj) {
		
		String htmlTable = new String(""); 
		String JudicialEntities[][] = countryObj.getJudicialEntityCourt();
        int i = 0;
		
		while(i<JudicialEntities.length) {

			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ JudicialEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][1]) + "\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][2]) + "\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][3]) + "\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][4]) + "\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][5]) + "\n "
					+ "</td> \n"
					+ "</tr> \n";			
			
				i++;
		}

		return htmlTable; 
	}
	
	
	public static String formatAdminEntities(Country countryObj) {
		
		String htmlTable = new String(""); 
		String AdminEntities[][] = countryObj.getAdminEntity();
        int i = 0;
		
		while(i<AdminEntities.length) {


			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ AdminEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(AdminEntities[i][1]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(AdminEntities[i][2]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(AdminEntities[i][3]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(AdminEntities[i][4]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(AdminEntities[i][5]) +"\n "
					+ "</td> \n"
					+ "</tr> \n";			
			
				i++;
		}

		return htmlTable; 
	}
	
	
	public static String formatTradMechanisms(Country countryObj) {
		
		String htmlTable = new String(""); 
		String TradMechanisms[][] = countryObj.getTradMech();
        int i = 0;
		
		while(i<TradMechanisms.length) {


			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ TradMechanisms[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(TradMechanisms[i][1]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(TradMechanisms[i][2]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(TradMechanisms[i][3]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(TradMechanisms[i][4]) +"\n "
					+ "</td> \n"
					+ "<td> \n "
						+ FormatingUtilities.capitalizeString(TradMechanisms[i][5]) +"\n "
					+ "</td> \n"
					+ "</tr> \n";			
			
				i++;
		}

		return htmlTable; 
	}
	

	
}
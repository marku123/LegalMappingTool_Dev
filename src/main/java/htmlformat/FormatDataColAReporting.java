package htmlformat;


import model.Country;

public class FormatDataColAReporting {

	
	public static String formatJudicalEntities(Country countryObj) {
		
		String htmlTable = new String(""); 
		String POCs = countryObj.getPOCCountry();
		String JudicialEntities[][] = countryObj.getJudicialEntityCourt();
        int i = 0;
		
        //Create the header
		htmlTable = htmlTable + "<table id='judicialTable' class='a2Tables'>";
		htmlTable = htmlTable + "<thead><tr>";
		htmlTable = htmlTable + "<th  class='col1'>Judicial Entity/Court</th>";
        if (POCs.equals("") || POCs.contains("Asylum Seekers"))
        	htmlTable = htmlTable + "<th class='col6'>Asylum Seekers Can Formally Access the Entity/Court</th>";                           
        if (POCs.equals("") || POCs.contains("Internally Displaced Persons"))
        	htmlTable = htmlTable + "<th class='col3'>IDPs Can Formally Access the Entity/Court</th>";
		if (POCs.equals("") || POCs.contains("Refugees"))
        	htmlTable = htmlTable + "<th class='col2'>Refugees Can Formally Access the Entity/Court</th>";
        if (POCs.equals("") || POCs.contains("Returnees"))
        	htmlTable = htmlTable + "<th class='col4'>Returnees Can Formally Access the Entity/Court</th>";
        if (POCs.equals("") || POCs.contains("Stateless Persons"))
        	htmlTable = htmlTable + "<th class='col5'>Stateless Persons Can Formally Access the Entity/Court</th>";
        htmlTable = htmlTable + "</tr>";
		htmlTable = htmlTable + "</thead>";
		htmlTable = htmlTable + "<tbody id='judicialTableAddRow'>";
        
		while(i<JudicialEntities.length) {

			//Create the HTML table.
			htmlTable = htmlTable + ""
			
			+ "<tr> \n"
			+ "<td> \n "
				+ JudicialEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
			+ "</td> \n";

	        if (POCs.equals("") || POCs.contains("Asylum Seekers")){
	        	htmlTable = htmlTable 	+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][1]) + "\n "
					+ "</td> \n";
	        }	
	        
	        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){
	        	htmlTable = htmlTable 	+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][2]) + "\n "
					+ "</td> \n";
	        }	
	        
			if (POCs.equals("") || POCs.contains("Refugees")){
	        	htmlTable = htmlTable 	+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][3]) + "\n "
					+ "</td> \n";
	        }	
	        
	        if (POCs.equals("") || POCs.contains("Returnees")){
	    		
	        	htmlTable = htmlTable 	+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][4]) + "\n "
					+ "</td> \n";
	        }	

	        if (POCs.equals("") || POCs.contains("Stateless Persons")){
	        	htmlTable = htmlTable 	+ "<td> \n "
						+ FormatingUtilities.capitalizeString(JudicialEntities[i][5]) + "\n "
					+ "</td> \n";
	        }	

			htmlTable = htmlTable 	+  "</tr> \n";		
			i++;
		}

		htmlTable = htmlTable 	+  "</tbody></table> \n";			

		return htmlTable; 
	}
	
	
	public static String formatAdminEntities(Country countryObj) {
		
		String htmlTable = new String(""); 
		String AdminEntities[][] = countryObj.getAdminEntity();
		String POCs = countryObj.getPOCCountry();
        int i = 0;
		
        //Create the header
		htmlTable = htmlTable + "<table id='adminTable' class='a2Tables'>";
		htmlTable = htmlTable + "<thead><tr>";
		htmlTable = htmlTable + "<th  class='col1'>Administrative Entities</th>";
        if (POCs.equals("") || POCs.contains("Asylum Seekers"))
        	htmlTable = htmlTable + "<th class='col6'>Asylum Seekers Can Formally Access the Entity</th>";                           
        if (POCs.equals("") || POCs.contains("Internally Displaced Persons"))
        	htmlTable = htmlTable + "<th class='col3'>IDPs Can Formally Access the Entity</th>";
		if (POCs.equals("") || POCs.contains("Refugees"))
        	htmlTable = htmlTable + "<th class='col2'>Refugees Can Formally Access the Entity</th>";
        if (POCs.equals("") || POCs.contains("Returnees"))
        	htmlTable = htmlTable + "<th class='col4'>Returnees Can Formally Access the Entity</th>";
        if (POCs.equals("") || POCs.contains("Stateless Persons"))
        	htmlTable = htmlTable + "<th class='col5'>Stateless Persons Can Formally Access the Entity</th>";
        htmlTable = htmlTable + "</tr>";
		htmlTable = htmlTable + "</thead>";
		htmlTable = htmlTable + "<tbody id='administrativeTableAddRow'>";
        
        
		while(i<AdminEntities.length) {
			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ AdminEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
					+ "</td> \n";

			        if (POCs.equals("") || POCs.contains("Asylum Seekers")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(AdminEntities[i][1]) +"\n "
							+ "</td> \n";
			        }	
			        
			        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(AdminEntities[i][2]) +"\n "
							+ "</td> \n";
			        }	
			        
					if (POCs.equals("") || POCs.contains("Refugees")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(AdminEntities[i][3]) +"\n "
							+ "</td> \n";
			        }	

			        if (POCs.equals("") || POCs.contains("Returnees")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(AdminEntities[i][4]) +"\n "
							+ "</td> \n";
			        }	

			        if (POCs.equals("") || POCs.contains("Stateless Persons")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(AdminEntities[i][5]) +"\n "
							+ "</td> \n";
			        }	

					htmlTable = htmlTable 	+  "</tr> \n";	
				i++;
		}
		htmlTable = htmlTable 	+  "</tbody></table> \n";			

		return htmlTable; 
	}
	
	
	public static String formatTradMechanisms(Country countryObj) {
		
		String htmlTable = new String(""); 
		String TradMechanisms[][] = countryObj.getTradMech();
		String POCs = countryObj.getPOCCountry();
        int i = 0;
		
        
        //Create the header
		htmlTable = htmlTable + "<table id='tradTable' class='a2Tables'>";
		htmlTable = htmlTable + "<thead><tr>";
		htmlTable = htmlTable + "<th  class='col1'>Traditional Mechanisms</th>";
        if (POCs.equals("") || POCs.contains("Asylum Seekers"))
        	htmlTable = htmlTable + "<th class='col6'>Asylum Seekers Can Formally Access the Entity</th>";                           
        if (POCs.equals("") || POCs.contains("Internally Displaced Persons"))
        	htmlTable = htmlTable + "<th class='col3'>IDPs Can Formally Access the Entity</th>";
		if (POCs.equals("") || POCs.contains("Refugees"))
        	htmlTable = htmlTable + "<th class='col2'>Refugees Can Formally Access the Entity</th>";
        if (POCs.equals("") || POCs.contains("Returnees"))
        	htmlTable = htmlTable + "<th class='col4'>Returnees Can Formally Access the Entity</th>";
        if (POCs.equals("") || POCs.contains("Stateless Persons"))
        	htmlTable = htmlTable + "<th class='col5'>Stateless Persons Can Formally Access the Entity</th>";
        htmlTable = htmlTable + "</tr>";
		htmlTable = htmlTable + "</thead>";
		htmlTable = htmlTable + "<tbody id='traditionalTableAddRow'>";
		
		
		while(i<TradMechanisms.length) {

			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ TradMechanisms[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"\n "
					+ "</td> \n";

			        if (POCs.equals("") || POCs.contains("Asylum Seekers")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(TradMechanisms[i][1]) +"\n "
							+ "</td> \n";
			        }	
			        
			        if (POCs.equals("") || POCs.contains("Internally Displaced Persons")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(TradMechanisms[i][2]) +"\n "
							+ "</td> \n";
			        }	
			        
					if (POCs.equals("") || POCs.contains("Refugees")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(TradMechanisms[i][3]) +"\n "
							+ "</td> \n";
			        }	
			        
			        if (POCs.equals("") || POCs.contains("Returnees")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(TradMechanisms[i][4]) +"\n "
							+ "</td> \n";
			        }	

			        if (POCs.equals("") || POCs.contains("Stateless Persons")){
			        	htmlTable = htmlTable 	+ "<td> \n "
								+ FormatingUtilities.capitalizeString(TradMechanisms[i][5]) +"\n "
							+ "</td> \n";
			        }	

					htmlTable = htmlTable 	+  "</tr> \n";	
			
			
				i++;
		}
		htmlTable = htmlTable 	+  "</tbody></table> \n";			

		return htmlTable; 
	}
	

	
}
package htmlformat;


import model.Country;

public class FormatDataColA {

	
	public static String formatJudicalEntities(Country countryObj) {
		
		String htmlTable = new String(""); 
		String JudicialEntities[][] = countryObj.getJudicialEntityCourt();
        int i = 0;
		
		while(i<JudicialEntities.length) {

			//Format the radio buttons.
			String[] refugees = FormatingUtilities.setCheckedRadioButtons(JudicialEntities[i][1], "yes,no,unclear");
			String[] IDPs =  FormatingUtilities.setCheckedRadioButtons(JudicialEntities[i][2],"yes,no,unclear");			
			String[] returnees =  FormatingUtilities.setCheckedRadioButtons(JudicialEntities[i][3],"yes,no,unclear");			
			String[] stateless =  FormatingUtilities.setCheckedRadioButtons(JudicialEntities[i][4],"yes,no,unclear");			
			String[] asylum =  FormatingUtilities.setCheckedRadioButtons(JudicialEntities[i][5],"yes,no,unclear");			

			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ "<input type='text' maxlength='50' name='entitycourt["+ i +"]' value='"+ JudicialEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"'>\n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='refugeesaccesscourt[" + i + "]' value='yes' "+refugees[0]+">Yes <br> \n "
						+ "<input type='radio' name='refugeesaccesscourt[" + i + "]' value='no' "+refugees[1]+">No <br> \n "
						+ "<input type='radio' name='refugeesaccesscourt[" + i + "]' value='unclear' "+refugees[2]+">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='IDPsaccesscourt[" + i + "]' value='yes' " + IDPs[0] + ">Yes <br> \n "
						+ "<input type='radio' name='IDPsaccesscourt[" + i + "]' value='no' " + IDPs[1] + ">No <br> \n "
						+ "<input type='radio' name='IDPsaccesscourt[" + i + "]' value='unclear' " + IDPs[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='returneesaccesscourt[" + i + "]' value='yes' " + returnees[0] + ">Yes <br> \n "
						+ "<input type='radio' name='returneesaccesscourt[" + i + "]' value='no' " + returnees[1] + ">No <br> \n "
						+ "<input type='radio' name='returneesaccesscourt[" + i + "]' value='unclear' " + returnees[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='statelessaccesscourt[" + i + "]' value='yes' " + stateless[0] + ">Yes <br> \n "
						+ "<input type='radio' name='statelessaccesscourt[" + i + "]' value='no' " + stateless[1] + ">No <br> \n "
						+ "<input type='radio' name='statelessaccesscourt[" + i + "]' value='unclear' " + stateless[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='asylumaccesscourt[" + i + "]' value='yes' " + asylum[0] + ">Yes <br> \n "
						+ "<input type='radio' name='asylumaccesscourt[" + i + "]' value='no' " + asylum[1] + ">No <br> \n "
						+ "<input type='radio' name='asylumaccesscourt[" + i + "]' value='unclear' " + asylum[2] + ">Unclear <br> \n "
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

			//Format the radio buttons.
			String[] refugees = FormatingUtilities.setCheckedRadioButtons(AdminEntities[i][1], "yes,no,unclear");
			String[] IDPs =  FormatingUtilities.setCheckedRadioButtons(AdminEntities[i][2],"yes,no,unclear");			
			String[] returnees =  FormatingUtilities.setCheckedRadioButtons(AdminEntities[i][3],"yes,no,unclear");			
			String[] stateless =  FormatingUtilities.setCheckedRadioButtons(AdminEntities[i][4],"yes,no,unclear");			
			String[] asylum =  FormatingUtilities.setCheckedRadioButtons(AdminEntities[i][5],"yes,no,unclear");			

			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ "<input type='text' maxlength='50' name='adminentityname["+ i +"]' value='"+ AdminEntities[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"'>\n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='refugeesaccessadmin[" + i + "]' value='yes' "+refugees[0]+">Yes <br> \n "
						+ "<input type='radio' name='refugeesaccessadmin[" + i + "]' value='no' "+refugees[1]+">No <br> \n "
						+ "<input type='radio' name='refugeesaccessadmin[" + i + "]' value='unclear' "+refugees[2]+">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='IDPsaccessadmin[" + i + "]' value='yes' " + IDPs[0] + ">Yes <br> \n "
						+ "<input type='radio' name='IDPsaccessadmin[" + i + "]' value='no' " + IDPs[1] + ">No <br> \n "
						+ "<input type='radio' name='IDPsaccessadmin[" + i + "]' value='unclear' " + IDPs[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='returneesaccessadmin[" + i + "]' value='yes' " + returnees[0] + ">Yes <br> \n "
						+ "<input type='radio' name='returneesaccessadmin[" + i + "]' value='no' " + returnees[1] + ">No <br> \n "
						+ "<input type='radio' name='returneesaccessadmin[" + i + "]' value='unclear' " + returnees[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='statelessaccessadmin[" + i + "]' value='yes' " + stateless[0] + ">Yes <br> \n "
						+ "<input type='radio' name='statelessaccessadmin[" + i + "]' value='no' " + stateless[1] + ">No <br> \n "
						+ "<input type='radio' name='statelessaccessadmin[" + i + "]' value='unclear' " + stateless[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='asylumaccessadmin[" + i + "]' value='yes' " + asylum[0] + ">Yes <br> \n "
						+ "<input type='radio' name='asylumaccessadmin[" + i + "]' value='no' " + asylum[1] + ">No <br> \n "
						+ "<input type='radio' name='asylumaccessadmin[" + i + "]' value='unclear' " + asylum[2] + ">Unclear <br> \n "
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

			//Format the radio buttons.
			String[] refugees = FormatingUtilities.setCheckedRadioButtons(TradMechanisms[i][1], "yes,no,unclear");
			String[] IDPs =  FormatingUtilities.setCheckedRadioButtons(TradMechanisms[i][2],"yes,no,unclear");			
			String[] returnees =  FormatingUtilities.setCheckedRadioButtons(TradMechanisms[i][3],"yes,no,unclear");			
			String[] stateless =  FormatingUtilities.setCheckedRadioButtons(TradMechanisms[i][4],"yes,no,unclear");			
			String[] asylum =  FormatingUtilities.setCheckedRadioButtons(TradMechanisms[i][5],"yes,no,unclear");			

			//Create the HTML table.
			htmlTable = htmlTable + ""
					+ "<tr> \n"
					+ "<td> \n "
						+ "<input type='text' maxlength='50' name='tradmechname["+ i +"]' value='"+ TradMechanisms[i][0].replaceAll("'", "&#39;").replaceAll("\"", "&#34;") +"'>\n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='refugeesaccesstrad[" + i + "]' value='yes' "+refugees[0]+">Yes <br> \n "
						+ "<input type='radio' name='refugeesaccesstrad[" + i + "]' value='no' "+refugees[1]+">No <br> \n "
						+ "<input type='radio' name='refugeesaccesstrad[" + i + "]' value='unclear' "+refugees[2]+">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='IDPsaccesstrad[" + i + "]' value='yes' " + IDPs[0] + ">Yes <br> \n "
						+ "<input type='radio' name='IDPsaccesstrad[" + i + "]' value='no' " + IDPs[1] + ">No <br> \n "
						+ "<input type='radio' name='IDPsaccesstrad[" + i + "]' value='unclear' " + IDPs[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='returneesaccesstrad[" + i + "]' value='yes' " + returnees[0] + ">Yes <br> \n "
						+ "<input type='radio' name='returneesaccesstrad[" + i + "]' value='no' " + returnees[1] + ">No <br> \n "
						+ "<input type='radio' name='returneesaccesstrad[" + i + "]' value='unclear' " + returnees[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='statelessaccesstrad[" + i + "]' value='yes' " + stateless[0] + ">Yes <br> \n "
						+ "<input type='radio' name='statelessaccesstrad[" + i + "]' value='no' " + stateless[1] + ">No <br> \n "
						+ "<input type='radio' name='statelessaccesstrad[" + i + "]' value='unclear' " + stateless[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "<td> \n "
						+ "<input type='radio' name='asylumaccesstrad[" + i + "]' value='yes' " + asylum[0] + ">Yes <br> \n "
						+ "<input type='radio' name='asylumaccesstrad[" + i + "]' value='no' " + asylum[1] + ">No <br> \n "
						+ "<input type='radio' name='asylumaccesstrad[" + i + "]' value='unclear' " + asylum[2] + ">Unclear <br> \n "
					+ "</td> \n"
					+ "</tr> \n";			
			
				i++;
		}

		return htmlTable; 
	}
	
}

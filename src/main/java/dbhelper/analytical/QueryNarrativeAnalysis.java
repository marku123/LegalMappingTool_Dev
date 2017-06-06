package dbhelper.analytical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.MySql;

public class QueryNarrativeAnalysis {

	public static Country getAnalyticalNarrative(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String allFields[] = new String[3]; 
		String noFields[] = new String[3];
	
		try {
		
			String sql = "SELECT * FROM analytical_narrative WHERE CountryName = ?";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					

					allFields[0] = StringUtils.defaultString(rs.getString("Narrative1"), "");
					allFields[1] = StringUtils.defaultString(rs.getString("Narrative2"), "");
					allFields[2] = StringUtils.defaultString(rs.getString("Narrative3"), "");

				}		

				countryObj.setNarrative(allFields);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				noFields[0] = "";
				noFields[1] = "";
				noFields[2] = "";


				countryObj.setNarrative(noFields);
			}
			rs.close();
			pst.close();

			MySql.close(c);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
}

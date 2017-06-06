package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CountryExists {

	static public boolean countryExists(String country, String databaseTable) {

		try {
			String CountryFromDB = "";

			Connection c = MySql.connect();
			String sql = "SELECT CountryName FROM " + databaseTable + " WHERE CountryName = ? ";
			
			
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				CountryFromDB = rs.getString("CountryName");
			}
			rs.close();
			pst.close();

			MySql.close(c);

			if (CountryFromDB.equals(country)) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}

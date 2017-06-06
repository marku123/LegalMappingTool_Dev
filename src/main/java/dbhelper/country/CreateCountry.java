package dbhelper.country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;
import dbhelper.dbutilities.*;

public class CreateCountry {

	static public Country createCountry(String country) {

		Connection c = MySql.connect();
		String sql = null;
		Country countryObj = new Country();
  
		
		
		try {
			
			if (!countryExists(country, "countries")) {

				sql = "INSERT INTO countries (CountryName) VALUES (?)";

				PreparedStatement pst = c.prepareStatement(sql);
				
				pst.setString(1, country);

				pst.executeUpdate();

				pst.close();
				MySql.close(c);
			} 
			countryObj.setCountryName(country);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}

	static public boolean countryExists(String country, String databaseTable) {

		try {
			Connection c = MySql.connect();
			String sql = "SELECT CountryName FROM " + databaseTable + " WHERE CountryName = ? ";

			PreparedStatement pst = c.prepareStatement(sql);
			String CountryFromDB = "";

			pst.setString(1, country);

			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {

				CountryFromDB = rs.getString("CountryName");
			}
			rs.close();
			pst.close();

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

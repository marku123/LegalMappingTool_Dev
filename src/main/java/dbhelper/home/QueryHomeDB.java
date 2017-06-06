package dbhelper.home;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Country;
import dbhelper.dbutilities.MySql;

public class QueryHomeDB {

	
	public static String getAllCountriesOptions() {

		String stringToReturn = "";

		ArrayList<Country> allCountries = getGlobalCountriesDB();
		for (int i = 0; i < allCountries.size(); i++) {
			stringToReturn = stringToReturn + "<option value='" + allCountries.get(i).getCountryName().replaceAll("'", "&#39;")  + "'>" + allCountries.get(i).getCountryName() + "</option>";
		}

		return stringToReturn;
	}

	public static ArrayList<Country> getGlobalCountriesDB() {

		Connection c = MySql.connect();
		ArrayList<Country> allCountries = new ArrayList<>();
		try {

			String sql = "SELECT * FROM countries";

			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Country country = new Country();
				country.setCountryName(rs.getString("CountryName"));
				allCountries.add(country);
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return allCountries;
	}
}

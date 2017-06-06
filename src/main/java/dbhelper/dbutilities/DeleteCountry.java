package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteCountry {
	
	
	static public void deleteCountryFromTable(String country, String databaseTable) {

		try {
			Connection c = MySql.connect();
			String sql = "DELETE FROM " + databaseTable + " WHERE CountryName = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);
			
			pst.executeUpdate();

			pst.close();

			MySql.close(c);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void deleteCountryRightsGroupFromTable(String country, String rightsgroup, String databaseTable) {

		try {
			Connection c = MySql.connect();
			String sql = "DELETE FROM " + databaseTable + " WHERE CountryName = ? AND RightsGroup = '" + rightsgroup + "' ";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);

			pst.executeUpdate();

			pst.close();

			MySql.close(c);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void deleteCountryPOCFromTable(String country, String poc, String databaseTable) {

		try {
			Connection c = MySql.connect();
			String sql = "DELETE FROM " + databaseTable + " WHERE CountryName = ? AND POC = '" + poc + "' ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);

			pst.executeUpdate();

			pst.close();

			MySql.close(c);


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}

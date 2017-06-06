package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

public class CountRows {

	
	static public int countRowsCountry(String database, String country) {
		Connection c = MySql.connect();
		int rowCount = 0;
		
		try {

			String sql = "SELECT Count(*) AS Count FROM "+database+" WHERE CountryName = ? ";
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt("Count");
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;

	}

	static public int countRowsRightsGroups(String database, String rightsgroups, String country) {
		Connection c = MySql.connect();
		int rowCount = 0;
		
		try {

			String sql = "SELECT Count(*) AS Count FROM "+database+" WHERE CountryName = ? AND  RightsGroup = ? ";
			
			
			PreparedStatement pst = c.prepareStatement(sql);

			pst.setString(1, country);
			pst.setString(2, rightsgroups);


			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt("Count");
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;

	}
	
	static public int countRowsCountryPOC(String database, String poc, String country) {
		Connection c = MySql.connect();
		int rowCount = 0;
		
		try {

			String sql = "SELECT Count(*) AS Count FROM "+database+" WHERE CountryName = ? AND  POC = ? ";
			
			
			PreparedStatement pst = c.prepareStatement(sql);

			pst.setString(1, country);
			pst.setString(2, poc);


			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rowCount = rs.getInt("Count");
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;

	}
	//Count the number of rows in a table regardless of country or rights group.
	static public int countRows(String database) {
		Connection c = MySql.connect();
		int rowCount = 0;
		
		try {

			String sql = "SELECT Count(*) AS Count FROM "+database+" ";
			PreparedStatement pst = c.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				rowCount = rs.getInt("Count");
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount;

	}
	//This is a helper utility. It is used to identify how many rows are in an HTML table that is to be stored in the database.
	
	static public int getNumberOfRowsInTable(HttpServletRequest request, String parameterName) { 
		
		int numberOfRows = 0;
		boolean loop = true;
		while(loop){			
			if(request.getParameter(parameterName + "[" + numberOfRows + "]") != null) {
				numberOfRows++;
			} else {
				loop = false;
			}
		}
		return numberOfRows;		
	}
	

	
}

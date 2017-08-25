package dbhelper.authentication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbhelper.dbutilities.MySql;

public class QueryAuthentication {

	
	public static String[] authenticateLogin(String UserName, String Password) {
		Connection c = MySql.connect();
		String[] authenticationData = new String[3];
		
		try {

			String sql = "SELECT * FROM authentication WHERE UserName = ? AND Password = ?";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, UserName);
			pst.setString(2, Password);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the username and password exists.  
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
				
					authenticationData[0] = "true";
					authenticationData[1] = rs.getString("Editor");
					authenticationData[2] = rs.getString("Country");

				}
			} else {
				authenticationData[0] = "false";
				authenticationData[1] = "false";
				authenticationData[2] = "None";
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return authenticationData;

	}
	
}

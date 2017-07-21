package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RightsCategoriesManagement {

	public static String[] getRightsCategories() {
		Connection c = MySql.connect();
		int i =0;
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String[] RightsCategories = new String[numOfRightsCategories];
		String sql;
		PreparedStatement pst;
		ResultSet rs;

		try {
			sql = "SELECT NameOfRightsCategory FROM rightscategory  ORDER BY NameOfRightsCategory";
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {

				RightsCategories[i] = rs.getString("NameOfRightsCategory");

				i++;
			}

			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return RightsCategories;

	}

}

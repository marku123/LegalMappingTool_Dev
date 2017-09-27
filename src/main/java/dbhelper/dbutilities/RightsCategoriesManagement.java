package dbhelper.dbutilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	
	public static List<String> getRightsCategoriesPerGroupName(String groupName) {
		Connection c = MySql.connect();
		List<String> RightsCategories = new ArrayList<String>();
		String sql;
		PreparedStatement pst;
		ResultSet rs;

		try {
			sql = "SELECT NameOfRightsCategory FROM rightscategory WHERE RightsCategoryGroupName = ? ORDER BY NameOfRightsCategory";
			pst = c.prepareStatement(sql);
			pst.setString(1, groupName);

			rs = pst.executeQuery();

			while (rs.next()) {
				RightsCategories.add(rs.getString("NameOfRightsCategory"));
			}

			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return RightsCategories;

	}
	
	
	public static List<String> getRightsCategoryGroupName() {
		Connection c = MySql.connect();
		List<String> RightsCategories = new ArrayList<String>();
		String sql;
		PreparedStatement pst;
		ResultSet rs;
		
		try {
			sql = "SELECT DISTINCT RightsCategoryGroupName FROM rightscategory  ORDER BY RightsCategoryGroupName";
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
				RightsCategories.add(rs.getString("RightsCategoryGroupName"));
			}

			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return RightsCategories;
	}
	
	
	public static String getRightGroupName(String rightsCategory) {
		Connection c = MySql.connect();
		String rightsCategoryGroupName = "";
		String sql;
		PreparedStatement pst;
		ResultSet rs;

		try {
			sql = "SELECT RightsCategoryGroupName FROM rightscategory WHERE NameOfRightsCategory = ?  ORDER BY RightsCategoryGroupName";
			pst = c.prepareStatement(sql);
			pst.setString(1, rightsCategory);
			rs = pst.executeQuery();

			while (rs.next()) {
				rightsCategoryGroupName = rs.getString("RightsCategoryGroupName");
			}

			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rightsCategoryGroupName;
	}
	
	public static String getRightCategoryAbbreviation(String rightsCategories) {
		Connection c = MySql.connect();
		String rightsCategoryAbbreviations = "";
		String sql;
		PreparedStatement pst;
		ResultSet rs;


		try {
			sql = "SELECT NameOfRightsCategory,RightsCategoryAbbreviation FROM rightscategory ORDER BY NameOfRightsCategory";
			pst = c.prepareStatement(sql);
			rs = pst.executeQuery();

			while (rs.next()) {
								
				String nameOfRightsCategoryFromDB = rs.getString("NameOfRightsCategory");
				
				if (rightsCategories.contains(nameOfRightsCategoryFromDB) || rightsCategories.equals("All")){
					rightsCategoryAbbreviations = rightsCategoryAbbreviations + rs.getString("RightsCategoryAbbreviation") + ",";

				} 
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (rightsCategoryAbbreviations.length() > 0) {
			rightsCategoryAbbreviations = rightsCategoryAbbreviations.substring(0, rightsCategoryAbbreviations.length() - 1);
		} 
		
		return rightsCategoryAbbreviations;
	}
	
	
	public static String getRightCategoryUsingAbbreviation(String rightsCategoryAbbreviation) {
		Connection c = MySql.connect();
		String rightsCategory = "";
		String sql;
		PreparedStatement pst;
		ResultSet rs;

		
		try {
			sql = "SELECT NameOfRightsCategory FROM rightscategory WHERE RightsCategoryAbbreviation = ?";
			pst = c.prepareStatement(sql);
			pst.setString(1, rightsCategoryAbbreviation);
			rs = pst.executeQuery();

			while (rs.next()) {
				rightsCategory = rs.getString("RightsCategoryAbbreviation");
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rightsCategory;
	}
	

}

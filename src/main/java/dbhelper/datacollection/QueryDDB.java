package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.MySql;

public class QueryDDB {

	public static Country getPOCPriorities(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String AllRightsGrpPriorities[][] = new String[5][10];
		String AllPOCSubgrps[][] = new String[5][2];

		try {

			int i = 0;
			String sql = "SELECT * FROM poc_priorities_d1 WHERE CountryName = ? ORDER BY RightsGroup";
	
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
	
			// Test to see if the country exists. If the country doesn't
			// exist then set everything to an empty string.
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					AllRightsGrpPriorities[0][i] = StringUtils.defaultString(rs.getString("RefPriority"), "");
					AllRightsGrpPriorities[1][i] = StringUtils.defaultString(rs.getString("AsyPriority"), "");
					AllRightsGrpPriorities[2][i] = StringUtils.defaultString(rs.getString("IDPPriority"), "");
					AllRightsGrpPriorities[3][i] = StringUtils.defaultString(rs.getString("ReturneePriority"), "");
					AllRightsGrpPriorities[4][i] = StringUtils.defaultString(rs.getString("StatelessPriority"), "");
					
					i++;
				}
	
			} else {
				while (i < 10) {
					AllRightsGrpPriorities[0][i] = "";
					AllRightsGrpPriorities[1][i] = "";
					AllRightsGrpPriorities[2][i] = "";
					AllRightsGrpPriorities[3][i] = "";
					AllRightsGrpPriorities[4][i] = "";

					i++;
				}
			}
			rs.close();
			pst.close();

			countryObj.setPOCPriorities(AllRightsGrpPriorities);

			//Get any additional subgroup names if they exist. 
			
			String sql1 = "SELECT * FROM poc_priorities_d3 WHERE CountryName = ?";
			PreparedStatement pst1 = c.prepareStatement(sql1);
			pst1.setString(1, countryName);
			ResultSet rs1 = pst1.executeQuery();
			
			if (rs1.isBeforeFirst()) {

				while (rs1.next()) {

					AllPOCSubgrps[0][0] = StringUtils.defaultString(rs1.getString("RefOtherSubGrp1"), "");
					AllPOCSubgrps[1][0] = StringUtils.defaultString(rs1.getString("AsyOtherSubGrp1"), "");
					AllPOCSubgrps[2][0] = StringUtils.defaultString(rs1.getString("IDPOtherSubGrp1"), "");
					AllPOCSubgrps[3][0] = StringUtils.defaultString(rs1.getString("RetOtherSubGrp1"), "");
					AllPOCSubgrps[4][0] = StringUtils.defaultString(rs1.getString("StateOtherSubGrp1"), "");
					AllPOCSubgrps[0][1] = StringUtils.defaultString(rs1.getString("RefOtherSubGrp2"), "");
					AllPOCSubgrps[1][1] = StringUtils.defaultString(rs1.getString("AsyOtherSubGrp2"), "");
					AllPOCSubgrps[2][1] = StringUtils.defaultString(rs1.getString("IDPOtherSubGrp2"), "");
					AllPOCSubgrps[3][1] = StringUtils.defaultString(rs1.getString("RetOtherSubGrp2"), "");
					AllPOCSubgrps[4][1] = StringUtils.defaultString(rs1.getString("StateOtherSubGrp2"), "");
				}	
			} else {

					AllPOCSubgrps[0][0] = "";
					AllPOCSubgrps[1][0] = "";
					AllPOCSubgrps[2][0] = "";
					AllPOCSubgrps[3][0] = "";
					AllPOCSubgrps[4][0] = "";
					AllPOCSubgrps[0][1] = "";
					AllPOCSubgrps[1][1] = "";
					AllPOCSubgrps[2][1] = "";
					AllPOCSubgrps[3][1] = "";
					AllPOCSubgrps[4][1] = "";

			}			
			
			rs1.close();
			pst1.close();
			
			
			countryObj.setOtherSubGrps(AllPOCSubgrps);

			
			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getNarrativePriorities(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String allFields[] = new String[2]; 
		String noFields[] = new String[2];
	
		try {
		
			String sql = "SELECT * FROM poc_priorities_d4_narrative WHERE CountryName = ?";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					allFields[0] = StringUtils.defaultString(rs.getString("Narrative1"), "");
					allFields[1] = StringUtils.defaultString(rs.getString("Narrative2"), "");
					
				}		

				countryObj.setNarrativePriorities(allFields);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				noFields[0] = "";
				noFields[1] = "";
	
				countryObj.setNarrativePriorities(noFields);
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

package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.MySql;
import dbhelper.dbutilities.RightsCategoriesManagement;

public class QueryCDB {

	public static Country getPOCObstacles(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String personsofconcern = countryObj.getPOC();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String AllRightsGrpObs[][] = new String[numOfRightsCategories][30]; 
		int i = 0;
	
		String[] RightsGroupsNames = new String[numOfRightsCategories];
		RightsGroupsNames = RightsCategoriesManagement.getRightsCategories();
		
		try {
		
			String sql = "SELECT * FROM obstacles_c1 WHERE CountryName = ? AND POC = ? ORDER BY RightsGroup";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, personsofconcern);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllRightsGrpObs[i][0] = StringUtils.defaultString(rs.getString("RightsGroup"), "");
					
					AllRightsGrpObs[i][1] = StringUtils.defaultString(rs.getString("LegalStatusObs"), "");
					AllRightsGrpObs[i][2] = StringUtils.defaultString(rs.getString("LegalStatusObsComment"), "");
					AllRightsGrpObs[i][3] = StringUtils.defaultString(rs.getString("LegalObsGrps"), "");

					AllRightsGrpObs[i][4] = StringUtils.defaultString(rs.getString("FinancialObs"), "");
					AllRightsGrpObs[i][5] = StringUtils.defaultString(rs.getString("FinancialObsComment"), "");
					AllRightsGrpObs[i][6] = StringUtils.defaultString(rs.getString("FinObsGrps"), "");

					AllRightsGrpObs[i][7] = StringUtils.defaultString(rs.getString("DocObs"), "");
					AllRightsGrpObs[i][8] = StringUtils.defaultString(rs.getString("DocObsComment"), "");
					AllRightsGrpObs[i][9] = StringUtils.defaultString(rs.getString("DocObsGrps"), "");

					AllRightsGrpObs[i][10] = StringUtils.defaultString(rs.getString("GeoObs"), "");
					AllRightsGrpObs[i][11] = StringUtils.defaultString(rs.getString("GeoObsComment"), "");
					AllRightsGrpObs[i][12] = StringUtils.defaultString(rs.getString("GeoObsGrps"), "");

					AllRightsGrpObs[i][13] = StringUtils.defaultString(rs.getString("AdminObs"), "");
					AllRightsGrpObs[i][14] = StringUtils.defaultString(rs.getString("AdminObsComment"), "");
					AllRightsGrpObs[i][15] = StringUtils.defaultString(rs.getString("AdminObsGrps"), "");

					AllRightsGrpObs[i][16] = StringUtils.defaultString(rs.getString("SecObs"), "");
					AllRightsGrpObs[i][17] = StringUtils.defaultString(rs.getString("SecObsComment"), "");
					AllRightsGrpObs[i][18] = StringUtils.defaultString(rs.getString("SecObsGrps"), "");

					AllRightsGrpObs[i][19] = StringUtils.defaultString(rs.getString("DiscrimObs"), "");
					AllRightsGrpObs[i][20] = StringUtils.defaultString(rs.getString("DiscrimObsComment"), "");
					AllRightsGrpObs[i][21] = StringUtils.defaultString(rs.getString("DiscrimObsGrps"), "");

					AllRightsGrpObs[i][22] = StringUtils.defaultString(rs.getString("OtherNameObs"), "");
					AllRightsGrpObs[i][23] = StringUtils.defaultString(rs.getString("OtherObs"), "");
					AllRightsGrpObs[i][24] = StringUtils.defaultString(rs.getString("OtherObsComment"), "");
					AllRightsGrpObs[i][25] = StringUtils.defaultString(rs.getString("OtherObsGrps"), "");
					
					AllRightsGrpObs[i][26] = StringUtils.defaultString(rs.getString("Other2NameObs"), "");
					AllRightsGrpObs[i][27] = StringUtils.defaultString(rs.getString("Other2Obs"), "");
					AllRightsGrpObs[i][28] = StringUtils.defaultString(rs.getString("Other2ObsComment"), "");
					AllRightsGrpObs[i][29] = StringUtils.defaultString(rs.getString("Other2ObsGrps"), "");
					
				
					i++;
				}		

				countryObj.setObstacles(AllRightsGrpObs);
				
			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.
	            for (int j = 0; j < numOfRightsCategories; j++) {
					AllRightsGrpObs[j][0] = RightsGroupsNames[j];
	            	for (int x = 1; x <30; x++) {
	            		AllRightsGrpObs[j][x] = "";
	            	}
	            }
				countryObj.setObstacles(AllRightsGrpObs);
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	

	
	public static Country getPOCObstacleDocumentation(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String personsofconcern = countryObj.getPOC();
		int noOfDocs =  CountRows.countRowsCountryPOC("obstacles_c2_documentation",personsofconcern,countryName);
		String AllObsDocumentation[][] = new String[noOfDocs+1][3]; 
		int i = 0;
	
		try {
		
			String sql = "SELECT * FROM obstacles_c2_documentation WHERE CountryName = ? AND POC = ? ORDER BY FileDisplayName";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, personsofconcern);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllObsDocumentation[i][0] = StringUtils.defaultString(rs.getString("FileStorageName"), "");
					AllObsDocumentation[i][1] = StringUtils.defaultString(rs.getString("FileDisplayName"), "");
					AllObsDocumentation[i][2] = StringUtils.defaultString(rs.getString("FileURL"), "");
					
					
					i++;
				}		

				countryObj.setObstaclesDocumenation(AllObsDocumentation);
				
			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.

				AllObsDocumentation[0][0] = "noData";
				AllObsDocumentation[0][1] = "noData";
				AllObsDocumentation[0][2] = "noData";

				countryObj.setObstaclesDocumenation(AllObsDocumentation);
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

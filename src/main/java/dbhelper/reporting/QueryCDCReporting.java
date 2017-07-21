package dbhelper.reporting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Country;
import model.PersonsOfConcern;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.MySql;
import dbhelper.dbutilities.RightsCategoriesManagement;

public class QueryCDCReporting {
	
	
	public static Country getPOCObstacles(Country countryObj) {
		String countryName = countryObj.getCountryName();
		String[] PersonsOfConcern = new String[5];
		PersonsOfConcern[] poc = new PersonsOfConcern[PersonsOfConcern.length];
		int j = 0;
		
		PersonsOfConcern[0] = "Asylum Seekers";
		PersonsOfConcern[1] = "Internally Displaced Persons";
		PersonsOfConcern[2] = "Refugees";
		PersonsOfConcern[3] = "Returnees";
		PersonsOfConcern[4] = "Stateless Persons";
		
		try {
		
			while (j < PersonsOfConcern.length) {
				
				poc[j] = new PersonsOfConcern();

				String Obstacles[][] = getObstacles(countryName, PersonsOfConcern[j]);
				String obstaclesDocumenation[][] = getObstaclesDocumentation(countryName, PersonsOfConcern[j]);

				poc[j].setPersonOfConcernName(PersonsOfConcern[j]);
				poc[j].setObstacles(Obstacles);
				poc[j].setObstaclesDocumenation(obstaclesDocumenation);

				j++;

			}			
			
			countryObj.setPersonsOfConcern(poc);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}

	public static String[][] getObstacles(String countryName, String PersonOfConcern) {
		Connection c = MySql.connect();
		int numOfRightsCategories = CountRows.countRows("rightscategory");
		String sql;
		PreparedStatement pst;
		ResultSet rs;
		String ObstaclesToReturn[][] = null;
		String AllRightsGrpObs[][] = new String[numOfRightsCategories][26]; 

		int i = 0;

		try {

			sql = "SELECT * FROM obstacles_c1 WHERE CountryName = ? AND POC = ? ORDER BY RightsGroup";

			pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, PersonOfConcern);
			rs = pst.executeQuery();

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
					
					i++;
				}		

				
			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.
				String[] rightsCategories = RightsCategoriesManagement.getRightsCategories();

	            for (int j = 0; j < numOfRightsCategories ; j++) {
	            	AllRightsGrpObs[j][0] = rightsCategories[j];
	            	for (int x = 1; x <26; x++) {
	            		AllRightsGrpObs[j][x] = "";
	            	}
	            }
				
			}

			rs.close();
			pst.close();

			MySql.close(c);

			ObstaclesToReturn = AllRightsGrpObs;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ObstaclesToReturn;

	}

	public static String[][] getObstaclesDocumentation(String countryName, String PersonOfConcern) {

		Connection c = MySql.connect();
		int noOfDocs =  CountRows.countRowsCountryPOC("obstacles_c2_documentation",PersonOfConcern,countryName);
		String AllObsDocumentation[][] = new String[noOfDocs+1][3]; 
		String AllObsDocumentationToReturn [][] = null;
		int i = 0;
		

		try {

			String sql = "SELECT * FROM obstacles_c2_documentation WHERE CountryName = ? AND POC = ? ORDER BY FileDisplayName";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, PersonOfConcern);
			ResultSet rs = pst.executeQuery();

			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllObsDocumentation[i][0] = StringUtils.defaultString(rs.getString("FileStorageName"), "");
					AllObsDocumentation[i][1] = StringUtils.defaultString(rs.getString("FileDisplayName"), "");
					AllObsDocumentation[i][2] = StringUtils.defaultString(rs.getString("FileURL"), "");
					
					i++;
				}		

			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.

				AllObsDocumentation[0][0] = "noData";
				AllObsDocumentation[0][1] = "noData";
				AllObsDocumentation[0][2] = "noData";

			}

			rs.close();
			pst.close();

			MySql.close(c);

			AllObsDocumentationToReturn = AllObsDocumentation;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return AllObsDocumentationToReturn;
	}
}

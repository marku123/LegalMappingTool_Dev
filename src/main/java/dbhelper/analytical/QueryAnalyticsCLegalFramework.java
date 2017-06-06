package dbhelper.analytical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.MySql;
import model.Country;

public class QueryAnalyticsCLegalFramework {

	public static Country getLegalFrameworkData(Country countryObj) {
		
		countryObj = getConsistencyWithInternational(countryObj);
		
		//Determine if there is any data missing in the database.
		countryObj = getConsistencyWithInternationalMissingData(countryObj);
		
		return countryObj;

	}
	
	public static Country getConsistencyWithInternational(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int numOfRightsGroups = 12;
		String[] RightsGroupsNames = new String[numOfRightsGroups]; 
		String ConsistencyWithInternational[][] = new String[numOfRightsGroups][3]; 
		int y = 0;
			
		RightsGroupsNames[0] = "Documentation";
		RightsGroupsNames[1] = "Education";
		RightsGroupsNames[2] = "Fair Trial and Right to Redress";
		RightsGroupsNames[3] = "Family Unity";
		RightsGroupsNames[4] = "Freedom of Movement";
		RightsGroupsNames[5] = "Health";
		RightsGroupsNames[6] = "Housing, Land and Property";
		RightsGroupsNames[7] = "Liberty and Security of Person";
		RightsGroupsNames[8] = "Non-Discrimination";
		RightsGroupsNames[9] = "Political Participation";
		RightsGroupsNames[10] = "Right to Work and Rights at Work";
		RightsGroupsNames[11] = "Social Security";
		
		
		try {
		
            for (int j = 0; j < RightsGroupsNames.length ; j++) {
            	for (int x = 0; x <3; x++) {
            		ConsistencyWithInternational[j][x] = "-";
            	}
            }
			
			//Select statement for each person of concern. 
			String sql = "SELECT RightsGroup, \n" + 
					"Count(InstrumentName) AS NumberOfInstruments,\n" + 
					"SUM(CASE ConsistentStandards WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1  ELSE 0 end) AS SUMOFRESPONSES,\n" + 
					"COUNT(CASE ConsistentStandards WHEN 'yes' THEN 1 WHEN 'partially' THEN 1 WHEN 'no' THEN 1 end) AS NUMOFRESPONSES, \n" + 
					"ROUND(SUM(CASE ConsistentStandards WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1  ELSE 0 end)/\n" + 
					"COUNT(CASE ConsistentStandards WHEN 'yes' THEN 1 WHEN 'partially' THEN 1 WHEN 'no' THEN 1 end),2) AS AVERAGERATING\n" + 
					"FROM rightsgroup_b2_national WHERE CountryName = ? GROUP BY RightsGroup; ";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
	
			while (rs.next()) {
                
				for (int z = 0;z<numOfRightsGroups;z++){
					if (rs.getString("RightsGroup").equals(RightsGroupsNames[z])) {
						y=z;
						break;
					}	
				}				
				ConsistencyWithInternational[y][0] = StringUtils.defaultString(rs.getString("RightsGroup"), "-");	
				ConsistencyWithInternational[y][1] = StringUtils.defaultString(rs.getString("NumberOfInstruments"), "-");	
				ConsistencyWithInternational[y][2] = StringUtils.defaultString(rs.getString("AverageRating"), "-");	

				y++;
			}	
			
			countryObj.setConsistencyWithInternational(ConsistencyWithInternational);
				
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getConsistencyWithInternationalMissingData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int noOfRightsGroups = 12;
		Boolean missingData = false;
		
		try {
		
			//Select statement. 
			String sql = "SELECT DistinctRightsGroups,CountOfMissingData\n"
						+ "FROM \n"
						+ "(SELECT Count(DISTINCT RightsGroup) AS DistinctRightsGroups\n"
							+ "FROM rightsgroup_b2_national  \n"
							+ "WHERE CountryName = ?) AS NumOfRightsGroups,\n"
						+ "(SELECT Count(*) AS CountOfMissingData\n"
							+ "FROM rightsgroup_b2_national\n"
							+ "WHERE CountryName =? AND ConsistentStandards = '') AS MissingData"; 

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, countryName);
			ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
			
				//Figure out what POC the database is returning.
        		if ((rs.getInt("DistinctRightsGroups") != noOfRightsGroups) || rs.getInt("CountOfMissingData")>0) 
        		{missingData = true;} else
        		{missingData = false;} 

            }

			countryObj.setConsistencyWithInternationalDataMissing(missingData);
			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
}

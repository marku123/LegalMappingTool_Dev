package dbhelper.analytical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.MySql;
import model.Country;

public class QueryAnalyticsCNatInstruments {

	
	public static Country getNatInstruData(Country countryObj) {
		
		//Get the summary data for each POC. 
		countryObj = getNatInstruPOCDetailData(countryObj);

		//Get the individual Rights Groups data for each individual POC.
		countryObj = getNatInstruPOCSummaryData(countryObj);

		//Determine if there is any data missing in the database.
		countryObj = getNatInstruPOCMissingData(countryObj);

		return countryObj;

	}

	public static Country getNatInstruPOCDetailData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String POCNatInstru[][] = new String[13][6]; 
		String[] NamesOfPOC = new String[6];
		String[] RightsGroupsNames = new String[13];

		int i = 0;
		
		NamesOfPOC[0] = "Asylum Seekers";
		NamesOfPOC[1] = "Internally Displaced Persons";
		NamesOfPOC[2] = "Nationals";
		NamesOfPOC[3] = "Refugees";
		NamesOfPOC[4] = "Returnees";
		NamesOfPOC[5] = "Stateless Persons";
		
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
		
			//Select statement for each person of concern. 
			String sql = "SELECT \n" + 
					"RightsGroup,\n" + 
					"ROUND(AVG((CASE SupportAsyl WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1   end)),2) AS Asylum,\n" + 
					"ROUND(AVG((CASE SupportIDP WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS IDP,\n" + 
					"ROUND(AVG((CASE SupportNat WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Nationals,\n" + 
					"ROUND(AVG((CASE SupportRef WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Refugees,\n" + 
					"ROUND(AVG((CASE SupportReturn WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Returnees,\n" + 
					"ROUND(AVG((CASE SupportState WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Stateless\n" + 
					"\n" + 
					"FROM rightsgroup_b2_national\n" + 
					"\n" + 
					"WHERE CountryName = ?\n" + 
					"\n" + 
					"GROUP BY RightsGroup\n" + 
					"\n" + 
					"ORDER BY RightsGroup";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			
			//Initialize the output array. 
            for (int j = 0; j < 13 ; j++) {
            	for (int x = 0; x <6; x++) {
            		POCNatInstru[j][x] = "-";
            	}
            }
            
            while (rs.next()) {
			
				//Figure out what POC the database is returning.
        		if (rs.getString("RightsGroup").equals(RightsGroupsNames[0])) {i=0;}
        		if (rs.getString("RightsGroup").equals(RightsGroupsNames[1])) {i=1;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[2])) {i=2;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[3])) {i=3;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[4])) {i=4;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[5])) {i=5;}
        		if (rs.getString("RightsGroup").equals(RightsGroupsNames[6])) {i=6;}
        		if (rs.getString("RightsGroup").equals(RightsGroupsNames[7])) {i=7;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[8])) {i=8;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[9])) {i=9;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[10])) {i=10;}
				if (rs.getString("RightsGroup").equals(RightsGroupsNames[11])) {i=11;}

				
				POCNatInstru[i][0] = StringUtils.defaultString(rs.getString("ASYLUM"), "-");
				POCNatInstru[i][1] = StringUtils.defaultString(rs.getString("IDP"), "-");
				POCNatInstru[i][2] = StringUtils.defaultString(rs.getString("Nationals"),"-");
				POCNatInstru[i][3] = StringUtils.defaultString(rs.getString("Refugees"),"-");
				POCNatInstru[i][4] = StringUtils.defaultString(rs.getString("Returnees"), "-");
				POCNatInstru[i][5] = StringUtils.defaultString(rs.getString("Stateless"), "-");

				i++;

            }

			countryObj.setNatInstruPOCDetail(POCNatInstru);
			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getNatInstruPOCMissingData(Country countryObj) {
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
							+ "WHERE CountryName =? AND "
								+ "(SupportNat = '' OR "
								+ "SupportIDP = '' OR "
								+ "SupportRef = '' OR "
								+ "SupportAsyl = '' OR "
								+ "SupportReturn = '' OR "
								+ "SupportState = '')) AS MissingData"; 

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

			countryObj.setNatInstruPOCDataMissing(missingData);
			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	
	public static Country getNatInstruPOCSummaryData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String[] NamesOfPOC = new String[6];
		
		NamesOfPOC[0] = "-";
		NamesOfPOC[1] = "-";
		NamesOfPOC[2] = "-";
		NamesOfPOC[3] = "-";
		NamesOfPOC[4] = "-";
		NamesOfPOC[5] = "-";
		
		try {
		
			//Select statement for each person of concern. 
			String sql = "SELECT \n" + 
					"\n" + 
					"ROUND(AVG((CASE SupportAsyl WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1   end)),2) AS Asylum,\n" + 
					"ROUND(AVG((CASE SupportIDP WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS IDP,\n" + 
					"ROUND(AVG((CASE SupportNat WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Nationals,\n" + 
					"ROUND(AVG((CASE SupportRef WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Refugees,\n" + 
					"ROUND(AVG((CASE SupportReturn WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Returnees,\n" + 
					"ROUND(AVG((CASE SupportState WHEN 'yes' THEN 3 WHEN 'partially' THEN 2 WHEN 'no' THEN 1 end)),2) AS Stateless\n" + 
					"\n" + 
					"FROM rightsgroup_b2_national\n" + 
					"\n" + 
					"WHERE CountryName = ?";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
            while (rs.next()) {	
				
            	NamesOfPOC[0] = StringUtils.defaultString(rs.getString("Asylum"), "-");
            	NamesOfPOC[1] = StringUtils.defaultString(rs.getString("IDP"), "-");
            	NamesOfPOC[2] = StringUtils.defaultString(rs.getString("Nationals"),"-");
            	NamesOfPOC[3] = StringUtils.defaultString(rs.getString("Refugees"),"-");
            	NamesOfPOC[4] = StringUtils.defaultString(rs.getString("Returnees"), "-");
            	NamesOfPOC[5] = StringUtils.defaultString(rs.getString("Stateless"), "-");

	

            }


			countryObj.setNatInstruPOCSummary(NamesOfPOC);
				
			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	
}

package dbhelper.analytical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.MySql;

public class QueryAnalyticsCObstacles {
	
	public static Country getObstaclesData(Country countryObj) {
		
		//Get just the obstacles data. 
		countryObj = getPOCObstaclesData(countryObj);
		
		//Get the individual Rights Groups obstacles data for each individual POC.
		countryObj = getPOCObstaclesRightsGroupsData(countryObj);
		
		//Get the individual Rights Groups obstacles data for each all POCs (not individual POCs).
		countryObj = getAllPOCObstaclesRightsGroupsData(countryObj);
		
		//Get all Rights Groups obstacles data for each individual POC.
		countryObj = getPOCObstaclesAllRightsGroupsData(countryObj);

		//Get all Rights Groups obstacles data for all POCs.
		countryObj = getAllPOCObstaclesAllRightsGroupsData(countryObj);
		
		//Determine if there is any data missing in the obstacles table that the Analytics need.
		countryObj = getPOCObstaclesMissingData(countryObj);
		
		return countryObj;

	}

	public static Country getPOCObstaclesData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String POCObtacles[][] = new String[6][9]; 
		String[] NamesOfPOC = new String[5];
		int i = 0;
		
		NamesOfPOC[0] = "Asylum Seekers";
		NamesOfPOC[1] = "Internally Displaced Persons";
		NamesOfPOC[2] = "Refugees";
		NamesOfPOC[3] = "Returnees";
		NamesOfPOC[4] = "Stateless Persons";
		
		try {
		
			//Select statement for each person of concern. 
			String sql = "SELECT POC,\n" + 
					"\n" + 
					"ROUND((SUM(CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS LEGALAVG,\n" + 
					" \n" + 
					"ROUND((SUM(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS FINAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS DOCAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS GEOAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS ADMINAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS SECAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS DISCRIMAVG,\n" + 
					"\n" +
					"ROUND(((SUM(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)) +\n" + 
					"(SUM(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)))\n" + 
					"/\n" +  
					"((COUNT(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)) +\n" + 
					"(COUNT(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)))\n" + 
					",2) AS OTHERAVG,\n" +
					"\n" +
					"ROUND(((SUM(CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1 ELSE 0 end)+ \n" + 
					"SUM(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" +
					"SUM(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 					
					"SUM(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)+\n" + 
					"COUNT(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)+\n" + 
					"COUNT(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)\n" + 
					")),2) AS ALLOBSTACLES\n" + 
					"\n" + 
					"\n" + 
					"FROM obstacles_c1 \n" + 
					"WHERE CountryName = ? \n" + 
					"GROUP BY POC \n" + 
					"ORDER BY POC ";
			

			//Select statement for all persons of concern. 

			String sql2 = "SELECT  \n" + 
					"ROUND((SUM(CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS LEGALAVG,\n" + 
					" \n" + 
					"ROUND((SUM(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS FINAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS DOCAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS GEOAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)),2) AS ADMINAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS SECAVG,\n" + 
					"\n" + 
					"ROUND((SUM(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)),2) AS DISCRIMAVG,\n" + 
					"\n" +
					"ROUND(((SUM(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)) +\n" + 
					"(SUM(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)))\n" + 
					"/\n" +  
					"((COUNT(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)) +\n" + 
					"(COUNT(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)))\n" + 
					",2) AS OTHERAVG,\n" +
					"\n" +				
					"ROUND(((SUM(CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+ \n" + 
					"SUM(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" +
					"SUM(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"SUM(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))/\n" + 
					"(COUNT(CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)+\n" + 
					"COUNT(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  end)+\n" + 
					"COUNT(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)+\n" + 
					"COUNT(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 end)\n" + 
					")),2) AS ALLOBSTACLES\n" + 
					"\n" + 
					"\n" + 
					"FROM obstacles_c1 \n" + 
					"WHERE CountryName = ? \n"; 
			
			PreparedStatement pst = c.prepareStatement(sql);
			PreparedStatement pst2 = c.prepareStatement(sql2);
			pst.setString(1, countryName);
			pst2.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			ResultSet rs2 = pst2.executeQuery();
				
			//Initialize the output array. 
            for (int j = 0; j < 5 ; j++) {
            	for (int x = 0; x <POCObtacles[j].length; x++) {
            		POCObtacles[j][x] = "-";
            	}
            }
            
            while (rs.next()) {
			
				//Figure out what POC the database is returning.
        		if (rs.getString("POC").equals(NamesOfPOC[0])) {i=0;}
        		if (rs.getString("POC").equals(NamesOfPOC[1])) {i=1;}
				if (rs.getString("POC").equals(NamesOfPOC[2])) {i=2;}
				if (rs.getString("POC").equals(NamesOfPOC[3])) {i=3;}
				if (rs.getString("POC").equals(NamesOfPOC[4])) {i=4;}
							
				
				POCObtacles[i][0] = StringUtils.defaultString(rs.getString("LEGALAVG"), "-");
				POCObtacles[i][1] = StringUtils.defaultString(rs.getString("FINAVG"), "-");
				POCObtacles[i][2] = StringUtils.defaultString(rs.getString("DOCAVG"),"-");
				POCObtacles[i][3] = StringUtils.defaultString(rs.getString("GEOAVG"),"-");
				POCObtacles[i][4] = StringUtils.defaultString(rs.getString("ADMINAVG"), "-");
				POCObtacles[i][5] = StringUtils.defaultString(rs.getString("SECAVG"), "-");
				POCObtacles[i][6] = StringUtils.defaultString(rs.getString("DISCRIMAVG"), "-");
				POCObtacles[i][7] = StringUtils.defaultString(rs.getString("OTHERAVG"), "-");
				POCObtacles[i][8] = StringUtils.defaultString(rs.getString("ALLOBSTACLES"), "-");
				i++;

            }

			while (rs2.next()) {
				POCObtacles[5][0] = StringUtils.defaultString(rs2.getString("LEGALAVG"), "-");
				POCObtacles[5][1] = StringUtils.defaultString(rs2.getString("FINAVG"), "-");
				POCObtacles[5][2] = StringUtils.defaultString(rs2.getString("DOCAVG"),"-");
				POCObtacles[5][3] = StringUtils.defaultString(rs2.getString("GEOAVG"),"-");
				POCObtacles[5][4] = StringUtils.defaultString(rs2.getString("ADMINAVG"), "-");
				POCObtacles[5][5] = StringUtils.defaultString(rs2.getString("SECAVG"), "-");
				POCObtacles[5][6] = StringUtils.defaultString(rs2.getString("DISCRIMAVG"), "-");
				POCObtacles[5][7] = StringUtils.defaultString(rs2.getString("OTHERAVG"), "-");
				POCObtacles[5][8] = StringUtils.defaultString(rs2.getString("ALLOBSTACLES"), "-");
			}	
	
			countryObj.setPOCObstaclesDetail(POCObtacles);
							
			rs.close();
			rs2.close();
			pst.close();
			pst2.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	
	public static Country getPOCObstaclesMissingData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int noOfPOC = 5;
		Boolean missingData = false;
		
		try {
		
			//Select statement. 
			String sql = "SELECT DistinctPOC,CountOfMissingData\n"
						+ "FROM \n"
						+ "(SELECT Count(DISTINCT POC) AS DistinctPOC\n"
							+ "FROM obstacles_c1  \n"
							+ "WHERE CountryName = ?) AS NumOfPOC,\n"
						+ "(SELECT Count(*) AS CountOfMissingData\n"
							+ "FROM obstacles_c1\n"
							+ "WHERE CountryName =? AND "
								+ "(LegalStatusObs = '' OR "
								+ "FinancialObs = '' OR "
								+ "DocObs = '' OR "
								+ "GeoObs = '' OR "
								+ "AdminObs = '' OR "
								+ "SecObs = '' OR "
								+ "DiscrimObs = '')) AS MissingData"; 

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, countryName);
			ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
			
				//Figure out what POC the database is returning.
        		if ((rs.getInt("DistinctPOC") != noOfPOC) || rs.getInt("CountOfMissingData")>0) 
        		{missingData = true;} else
        		{missingData = false;} 

            }

			countryObj.setObstaclesMissingData(missingData);
			
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
//These four methods below are for the Rights Groups obstacles table. 
	
	
	//This method selects the obstacles data for each rights group and each POC. It does not include ALL POCs nor data for ALL rights groups.
	public static Country getPOCObstaclesRightsGroupsData(Country countryObj) {
		Connection c = MySql.connect();
		int numberOfRightsGroups = CountRows.countRows("rightscategory");
		String countryName = countryObj.getCountryName();
		String POCRightsGroupsObtacles[][] = new String[5][numberOfRightsGroups]; 
		int y = 0;
		int i = 0;
		String[] NamesOfPOC = new String[5];
		
		NamesOfPOC[0] = "Asylum Seekers";
		NamesOfPOC[1] = "Internally Displaced Persons";
		NamesOfPOC[2] = "Refugees";
		NamesOfPOC[3] = "Returnees";
		NamesOfPOC[4] = "Stateless Persons";
	
		try {
		
			//Initialize the output array. 
            for (int j = 0; j < 5 ; j++) {
            	for (int x = 0; x <numberOfRightsGroups; x++) {
            		POCRightsGroupsObtacles[j][x] = "-";
            	}
            }
					
			//Select statement for each person of concern. 
			String sql = "SELECT POC, RightsGroup,\n" + 
					"\n" + 
					"ROUND(((CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end))\n" + 
					"/\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)\n" + 
					"),2) AS AVERAGEALLOBSTACLES\n" + 
					"\n" + 
					"FROM obstacles_c1 WHERE CountryName = ? ORDER BY POC,RightsGroup; ";
			
						
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
				
			while (rs.next()) {
                
				//Figure out what POC the database is returning.
				if (i==0){
					if (rs.getString("POC").equals(NamesOfPOC[0])) {y=0;}
					if (rs.getString("POC").equals(NamesOfPOC[1])) {y=1;}
					if (rs.getString("POC").equals(NamesOfPOC[2])) {y=2;}
					if (rs.getString("POC").equals(NamesOfPOC[3])) {y=3;}
					if (rs.getString("POC").equals(NamesOfPOC[4])) {y=4;}
				}
				
				//As long as we are still below the max number of rights groups, we can keep adding them to the array. 
				if (i<numberOfRightsGroups) {
					POCRightsGroupsObtacles[y][i] = StringUtils.defaultString(rs.getString("AVERAGEALLOBSTACLES"), "-");

					i++;						
				} else {
					i = 0;
					if (rs.getString("POC").equals(NamesOfPOC[0])) {y=0;}
					if (rs.getString("POC").equals(NamesOfPOC[1])) {y=1;}
					if (rs.getString("POC").equals(NamesOfPOC[2])) {y=2;}
					if (rs.getString("POC").equals(NamesOfPOC[3])) {y=3;}
					if (rs.getString("POC").equals(NamesOfPOC[4])) {y=4;}
					POCRightsGroupsObtacles[y][i] = StringUtils.defaultString(rs.getString("AVERAGEALLOBSTACLES"), "-");
					i++;

				}
			}	
			
			countryObj.setPOCRightsGrouopsObstacles(POCRightsGroupsObtacles);
				
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	//This method selects the obstacles data for each rights group and ALL POCs. It does not include individual POCs nor data for ALL rights groups.
	public static Country getAllPOCObstaclesRightsGroupsData(Country countryObj) {
		Connection c = MySql.connect();
		int numOfRightsGroups = CountRows.countRows("rightscategory");
		String countryName = countryObj.getCountryName();
		String AllPOCRightsGroupsObtacles[] = new String[numOfRightsGroups]; 
		int i = 0;
	
		try {
		
			//Select statement for all persons of concern. 
			String sql = "SELECT RightsGroup,ROUND(SUM(SUMOFRESPONSES)/SUM(COUNTOFRESPONSES),2) AS AVERAGEALLOBSTACLES\n" + 
					"\n" + 
					"FROM\n" + 
					" \n" + 
					"(SELECT POC, RightsGroup,\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)) AS SUMOFRESPONSES\n" + 
					",\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)\n" + 
					")AS COUNTOFRESPONSES\n" + 
					"\n" + 
					"FROM obstacles_c1 WHERE CountryName = ? ORDER BY POC,RightsGroup) AS ALLRIGHTSGROUPDATANOAVERAGE\n" + 
					"GROUP BY RightsGroup;";
			

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllPOCRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGEALLOBSTACLES"), "-");
					i++;					
				}		
				
			
				countryObj.setAllPOCRightsGrouopsObstacles(AllPOCRightsGroupsObtacles);
				
			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.

				for (int x = 0; x < numOfRightsGroups; x++) {
					AllPOCRightsGroupsObtacles[x] = "-";
	           }          
				
				countryObj.setAllPOCRightsGrouopsObstacles(AllPOCRightsGroupsObtacles);
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	//This method selects the obstacles data for all rights group and individual POCs. 
	public static Country getPOCObstaclesAllRightsGroupsData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String POCALLRightsGroupsObtacles[] = new String[5]; 
		int i = 0;
		String[] NamesOfPOC = new String[5];
		
		NamesOfPOC[0] = "Asylum Seekers";
		NamesOfPOC[1] = "Internally Displaced Persons";
		NamesOfPOC[2] = "Refugees";
		NamesOfPOC[3] = "Returnees";
		NamesOfPOC[4] = "Stateless Persons";
	
		try {
		    
			//Initialize the return array. 
			for (int x = 0; x <5; x++) {
				POCALLRightsGroupsObtacles[x] = "-";
			} 
			//Select statement for all persons of concern. 
			String sql = "SELECT POC, ROUND(SUM(SUMOFRESPONSES)/SUM(COUNTOFRESPONSES),2) AS AVERAGERATING \n" + 
					"FROM\n" + 
					"\n" + 
					"(SELECT POC, RightsGroup,\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)\n" + 
					" ) AS SUMOFRESPONSES\n" + 
					",\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" +
					"(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)\n" + 
					")AS COUNTOFRESPONSES\n" + 
					"\n" + 
					"FROM obstacles_c1 WHERE CountryName = ? ORDER BY POC,RightsGroup) AS ALLRIGHTSGROUPDATANOAVERAGE\n" + 
					"GROUP BY POC;";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {	
				


				if (rs.getString("POC").equals(NamesOfPOC[0])) {
					i=0;
					POCALLRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
					continue;
				}
				
				if (rs.getString("POC").equals(NamesOfPOC[1])) {
					i=1;
					POCALLRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
					continue;
				}
				
				if (rs.getString("POC").equals(NamesOfPOC[2])) {
					i=2;
					POCALLRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
					continue;
				}
				
				if (rs.getString("POC").equals(NamesOfPOC[3])) {
					i=3;
					POCALLRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
					continue;
				}
				
				if (rs.getString("POC").equals(NamesOfPOC[4])) {
					i=4;
					POCALLRightsGroupsObtacles[i] = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
					continue;
				}
			}		
					
			countryObj.setPOCALLRightsGroupsObtacles(POCALLRightsGroupsObtacles);

			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	//This method selects the obstacles data for all rights group and all POCs. 
	
	public static Country getAllPOCObstaclesAllRightsGroupsData(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String AllPOCALLRightsGroupsObtacles = null; 
	
		try {

			//Select statement for all persons of concern. 
			String sql = "SELECT ROUND(SUM(SUMOFRESPONSES)/SUM(COUNTOFRESPONSES),2) AS AVERAGERATING \n" + 
					"FROM\n" + 
					"\n" + 
					"(SELECT POC, RightsGroup,\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 3 WHEN 'moderate' THEN 2 WHEN 'none' THEN 1  ELSE 0 end)\n" + 
					") AS SUMOFRESPONSES\n" + 
					",\n" + 
					"\n" + 
					"((CASE LegalStatusObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE FinancialObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1  ELSE 0 end)+\n" + 
					"(CASE DocObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE GeoObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE AdminObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE SecObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE DiscrimObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE OtherObs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)+\n" + 
					"(CASE Other2Obs WHEN 'significant' THEN 1 WHEN 'moderate' THEN 1 WHEN 'none' THEN 1 ELSE 0 end)\n" + 
					")AS COUNTOFRESPONSES\n" + 
					"\n" + 
					"FROM obstacles_c1 WHERE CountryName = ? ORDER BY POC,RightsGroup) AS ALLRIGHTSGROUPDATANOAVERAGE\n" + 
					";";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 

			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					AllPOCALLRightsGroupsObtacles = StringUtils.defaultString(rs.getString("AVERAGERATING"), "-");
				}			
			
				countryObj.setAllPOCALLRightsGroupsObtacles(AllPOCALLRightsGroupsObtacles);
				
			} else {
				
				//If there is no information in the database for the country, set everything to an empty string.

				AllPOCALLRightsGroupsObtacles = "-";
				countryObj.setAllPOCALLRightsGroupsObtacles(AllPOCALLRightsGroupsObtacles);
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

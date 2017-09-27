package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.MySql;

public class QueryA2DB {

	public static Country getLegalFrameworkSystemIntro(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		
		try {

			String sql = "SELECT POC, POCComments, CommonCivilPlural, PluralText, FederalState, TradMechComments, Comments "
					+ "FROM legalframework_a2_legalsystem_intro WHERE CountryName = ? ";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					countryObj.setPOCCountry(StringUtils.defaultString(rs.getString("POC"), ""));
					countryObj.setPOCCommentsCountry(StringUtils.defaultString(rs.getString("POCComments"), ""));
					countryObj.setCommonCivilPlural(StringUtils.defaultString(rs.getString("CommonCivilPlural"), ""));
					countryObj.setPluralText(StringUtils.defaultString(rs.getString("PluralText"), ""));
					countryObj.setFederalState(StringUtils.defaultString(rs.getString("FederalState"), ""));
					countryObj.setTradMechComments(StringUtils.defaultString(rs.getString("TradMechComments"), ""));
					countryObj.setComments(StringUtils.defaultString(rs.getString("Comments"), ""));

				}
			} else {
					countryObj.setPOCCountry("");
					countryObj.setPOCCommentsCountry(StringUtils.defaultString(rs.getString(""), ""));
					countryObj.setCommonCivilPlural("");
					countryObj.setPluralText("");
					countryObj.setFederalState("");
					countryObj.setTradMechComments("");
					countryObj.setComments("");

			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	/*public static Country getCountryPOCs(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String sql = "";
		PreparedStatement pst;
		ResultSet rs;
		String sql2 = ""; 
		PreparedStatement pst2;
		ResultSet rs2;
		
		try {

			//Get the persons of concern.
			sql = "SELECT POC "
					+ "FROM legalframework_a2_legalsystem_intro WHERE CountryName = ? ";

			pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					countryObj.setPOCCountry(StringUtils.defaultString(rs.getString("POC"), ""));
				}
			} else {
					countryObj.setPOCCountry("");
			}
			rs.close();
			pst.close();

			
			//Get the comments on the persons of concern.
			sql2 = "SELECT POCComments "
					+ "FROM legalframework_a2_legalsystem_intro WHERE CountryName = ? ";

			pst2 = c.prepareStatement(sql2);
			pst2.setString(1, countryName);
			rs2 = pst2.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs2.isBeforeFirst()) {
				while (rs2.next()) {
					countryObj.setPOCCommentsCountry(StringUtils.defaultString(rs2.getString("POCComments"), ""));
				}
			} else {
					countryObj.setPOCCommentsCountry("");
			}
			
			System.out.println("here are the comments: " + countryObj.getPOCCommentsCountry());
			rs2.close();
			pst2.close();
			
			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}*/
	
	public static Country getLegalFrameworkJudicialEntities(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int numberOfEntitiesCourts = CountRows.countRowsCountry("legalframework_a2_legalsystem_judicial", countryName);
		String AllEntitiesCourts[][] = new String[numberOfEntitiesCourts][6]; 
		String NoEntitiesCourts[][] = new String[1][6]; 
		int i = 0;
	
		try {
		
			String sql = "SELECT * "
					+ "FROM legalframework_a2_legalsystem_judicial WHERE CountryName = ? ORDER BY EntityCourtIndex";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);

			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllEntitiesCourts[i][0] = StringUtils.defaultString(rs.getString("JudicialEntityCourt"), "");
					AllEntitiesCourts[i][1] = StringUtils.defaultString(rs.getString("AsylumCanAccess"), "");
					AllEntitiesCourts[i][2] = StringUtils.defaultString(rs.getString("IDPCanAccess"), "");
					AllEntitiesCourts[i][3] = StringUtils.defaultString(rs.getString("RefugessCanAccess"), "");
					AllEntitiesCourts[i][4] = StringUtils.defaultString(rs.getString("ReturneesCanAccess"), "");
					AllEntitiesCourts[i][5] = StringUtils.defaultString(rs.getString("StatelessCanAccess"), "");
					
					i++;
				}		

				countryObj.setJudicialEntityCourt(AllEntitiesCourts);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				NoEntitiesCourts[0][0] = "";
				NoEntitiesCourts[0][1] = "";
				NoEntitiesCourts[0][2] = "";
				NoEntitiesCourts[0][3] = "";
				NoEntitiesCourts[0][4] = "";
				NoEntitiesCourts[0][5] = "";

				countryObj.setJudicialEntityCourt(NoEntitiesCourts);
			}
			rs.close();
			pst.close();

			MySql.close(c);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getLegalFrameworkAdminEntities(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int numberOfAdminEntities = CountRows.countRowsCountry("legalframework_a2_legalsystem_admin", countryName);
		String AllAdminEntities[][] = new String[numberOfAdminEntities][6]; 
		String NoAdminEntities[][] = new String[1][6]; 
		int i = 0;
	
		try {
		
			String sql = "SELECT * "
					+ "FROM legalframework_a2_legalsystem_admin WHERE CountryName = ? ORDER BY AdminEntityIndex";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);

			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {

					AllAdminEntities[i][0] = StringUtils.defaultString(rs.getString("AdminEntityName"), "");
					AllAdminEntities[i][1] = StringUtils.defaultString(rs.getString("AsylumCanAccess"), "");
					AllAdminEntities[i][2] = StringUtils.defaultString(rs.getString("IDPCanAccess"), "");
					AllAdminEntities[i][3] = StringUtils.defaultString(rs.getString("RefugessCanAccess"), "");
					AllAdminEntities[i][4] = StringUtils.defaultString(rs.getString("ReturneesCanAccess"), "");
					AllAdminEntities[i][5] = StringUtils.defaultString(rs.getString("StatelessCanAccess"), "");
					
					i++;
				}		

				countryObj.setAdminEntity(AllAdminEntities);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				NoAdminEntities[0][0] = "";
				NoAdminEntities[0][1] = "";
				NoAdminEntities[0][2] = "";
				NoAdminEntities[0][3] = "";
				NoAdminEntities[0][4] = "";
				NoAdminEntities[0][5] = "";

				countryObj.setAdminEntity(NoAdminEntities);
			}
			rs.close();
			pst.close();

			MySql.close(c);

			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getLegalFrameworkTradMechanisms(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		int numberOfTradMech = CountRows.countRowsCountry("legalframework_a2_legalsystem_trad", countryName);
		String AllTradMech[][] = new String[numberOfTradMech][6]; 
		String NoTradMech[][] = new String[1][6]; 
		int i = 0;
	
		try {
		
			String sql = "SELECT * "
					+ "FROM legalframework_a2_legalsystem_trad WHERE CountryName = ? ORDER BY TradMechIndex";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);

			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					AllTradMech[i][0] = StringUtils.defaultString(rs.getString("TradMechName"), "");
					AllTradMech[i][1] = StringUtils.defaultString(rs.getString("AsylumCanAccess"), "");
					AllTradMech[i][2] = StringUtils.defaultString(rs.getString("IDPCanAccess"), "");
					AllTradMech[i][3] = StringUtils.defaultString(rs.getString("RefugessCanAccess"), "");
					AllTradMech[i][4] = StringUtils.defaultString(rs.getString("ReturneesCanAccess"), "");
					AllTradMech[i][5] = StringUtils.defaultString(rs.getString("StatelessCanAccess"), "");
					
					i++;
				}		

				countryObj.setTradMech(AllTradMech);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				NoTradMech[0][0] = "";
				NoTradMech[0][1] = "";
				NoTradMech[0][2] = "";
				NoTradMech[0][3] = "";
				NoTradMech[0][4] = "";
				NoTradMech[0][5] = "";

				countryObj.setTradMech(NoTradMech);
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

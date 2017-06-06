package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Country;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.MySql;

public class QueryBDB {

	public static Country getRightsGroupsIntlIntruments(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String rightsGroup = countryObj.getRightsGroup();
		int numberOfIntlInstruForCountryAndRightsGroup = CountRows.countRowsRightsGroups("rightsgroup_b1_international", rightsGroup, countryName);
		//boolean countryHasRightsLinkedToInstru = CountryExists.countryExists(countryName,"rightsgroup_b1_international");	

		int i = 0;
		String sql; 
		PreparedStatement pst;
		ResultSet rs;
		
		try {
		
			if (rightsGroup.equals("")) {
				//If we have just arrived on the Section B page and have not selected a rights group than just initialize the array.
										
					String NoIntlInstru[][] = new String[1][7]; 

					NoIntlInstru[0][0] = "";
					NoIntlInstru[0][1]  = "";
					NoIntlInstru[0][2] = "";
					NoIntlInstru[0][3] = "";
					NoIntlInstru[0][4] = "";
					NoIntlInstru[0][5] = "";
					NoIntlInstru[0][6] = "";
					
					countryObj.setIntlInstruments(NoIntlInstru);

			} else if (numberOfIntlInstruForCountryAndRightsGroup > 0) {
              //There is data in the database for the country and for the rights group the user wants info on.


				sql = "SELECT * FROM rightsgroup_b1_international "
						+ "WHERE CountryName = ? and RightsGroup = ? "
						+ "ORDER BY InstrumentName"; 

				pst = c.prepareStatement(sql);
				pst.setString(1, countryName);
				pst.setString(2, rightsGroup);
				rs = pst.executeQuery();
				
				//Get the number of records. 
				rs.last();
				int noOfInstruments = rs.getRow();
				rs.beforeFirst();
				
				//Get all of the instruments from the result set.
				String IntlInstru[][] = new String[noOfInstruments][7]; 
				
				while (rs.next()) {
					
					IntlInstru[i][0] = StringUtils.defaultString(rs.getString("InstrumentName"), "");
					IntlInstru[i][1] = StringUtils.defaultString(rs.getString("InstrumentType"), "");
					IntlInstru[i][2] = StringUtils.defaultString(rs.getString("Ratified"), "");
					IntlInstru[i][3] = StringUtils.defaultString(rs.getString("Articles"), "");
					IntlInstru[i][4] = StringUtils.defaultString(rs.getString("Reservations"), "");
					IntlInstru[i][5] = StringUtils.defaultString(rs.getString("ReservationsNature"), "");
					IntlInstru[i][6] = StringUtils.defaultString(rs.getString("InstrumentID"), "");
					
					i++;
				}		

				countryObj.setIntlInstruments(IntlInstru);
				rs.close();
				pst.close();
				
			} else if (!rightsGroup.equals("") && (numberOfIntlInstruForCountryAndRightsGroup==0)) {
				//There is no data for the country in the database but we are not on the landing page.
				
				sql = "SELECT * FROM rightsgroup_b1_1_international WHERE RightsGroup = ? ORDER BY InstrumentName";
				
				pst = c.prepareStatement(sql);
				pst.setString(1, rightsGroup);					
				rs = pst.executeQuery();



				
				//Get the number of records. 
				rs.last();
				int noOfInstruments = rs.getRow();
				rs.beforeFirst();
				
				//Get all of the instruments from the result set.
				String AllInstruments[][] = new String[noOfInstruments][7]; 

				while (rs.next()) {

					AllInstruments[i][0] = StringUtils.defaultString(rs.getString("InstrumentName"), "");
					AllInstruments[i][1]  = StringUtils.defaultString(rs.getString("InstrumentType"), "");
					AllInstruments[i][2] = "";
					AllInstruments[i][3] = StringUtils.defaultString(rs.getString("Articles"), "");;
					AllInstruments[i][4] = "";
					AllInstruments[i][5] = "";
					AllInstruments[i][6] = StringUtils.defaultString(rs.getString("InstrumentID"), "");
					
					//Insert the data from the table with all of the international instruments into our table with the rights groups and instruments.
					String sql3 = "INSERT INTO rightsgroup_b1_international"
							+ "(CountryName,RightsGroup,InstrumentName,InstrumentType,Ratified,"
							+ "Articles,Reservations,ReservationsNature,InstrumentID) VALUES "
							+ "(?,?,"
								+ "'"+ AllInstruments[i][0].replace("'", "\\'")+"',"
								+ "'"+ AllInstruments[i][1]+"',"
								+ "'',"
								+ "'"+ AllInstruments[i][3].replace("'", "\\'")+"',"
								+ "'',"
								+ "'',"
								+ "'"+ AllInstruments[i][6]+"')";
										
					PreparedStatement pst3 = c.prepareStatement(sql3);
					pst3.setString(1, countryName);
					pst3.setString(2, rightsGroup);					
					pst3.executeUpdate();
					pst3.close();
					
					i++;
				}		

			
				countryObj.setIntlInstruments(AllInstruments);
				rs.close();
				pst.close();
			}
			
			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}
	
	public static Country getRightsGroupsNatlIntruments(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		String rightsGroup = countryObj.getRightsGroup();
		int numberOfNatlInstru = CountRows.countRowsRightsGroups("rightsgroup_b2_national", rightsGroup, countryName);
		String AllNatllInstru[][] = new String[numberOfNatlInstru][36]; 
		String NoNatlInstru[][] = new String[1][36]; 
		int i = 0;
	
		try {
		
			String sql = "SELECT * FROM rightsgroup_b2_national WHERE CountryName = ? "
						+ " AND RightsGroup = ? ORDER BY InstrumentName";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, rightsGroup);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
					
					
					AllNatllInstru[i][0] = StringUtils.defaultString(rs.getString("InstrumentName"), "");
					AllNatllInstru[i][1] = StringUtils.defaultString(rs.getString("RefWorldLink"), "");
					AllNatllInstru[i][2] = StringUtils.defaultString(rs.getString("FileStorageName"), "");
					AllNatllInstru[i][3] = StringUtils.defaultString(rs.getString("FileDisplayName"), "");
					AllNatllInstru[i][4] = StringUtils.defaultString(rs.getString("FileURL"), "");

					AllNatllInstru[i][5] = StringUtils.defaultString(rs.getString("FederalStateLocal"), "");
					AllNatllInstru[i][6] = StringUtils.defaultString(rs.getString("ConsistentStandards"), "");
					AllNatllInstru[i][7] = StringUtils.defaultString(rs.getString("ConsistentStandardsComm"), "");
					AllNatllInstru[i][8] = StringUtils.defaultString(rs.getString("Consistent1951"), "");
					AllNatllInstru[i][9] = StringUtils.defaultString(rs.getString("Consistent1951Comm"), "");
					
					AllNatllInstru[i][10] = StringUtils.defaultString(rs.getString("SupportNat"), "");									
					AllNatllInstru[i][11] = StringUtils.defaultString(rs.getString("RestrictNat"), "");
					AllNatllInstru[i][12] = StringUtils.defaultString(rs.getString("SupportRestrictNatComm"), "");
					
					AllNatllInstru[i][13] = StringUtils.defaultString(rs.getString("SupportIDP"), "");									
					AllNatllInstru[i][14] = StringUtils.defaultString(rs.getString("RestrictIDP"), "");
					AllNatllInstru[i][15] = StringUtils.defaultString(rs.getString("SupportRestrictIDPComm"), "");
					
					AllNatllInstru[i][16] = StringUtils.defaultString(rs.getString("SupportRef"), "");									
					AllNatllInstru[i][17] = StringUtils.defaultString(rs.getString("RestrictRef"), "");
					AllNatllInstru[i][18] = StringUtils.defaultString(rs.getString("SupportRestrictRefComm"), "");
					
					AllNatllInstru[i][19] = StringUtils.defaultString(rs.getString("SupportAsyl"), "");									
					AllNatllInstru[i][20] = StringUtils.defaultString(rs.getString("RestrictAsyl"), "");
					AllNatllInstru[i][21] = StringUtils.defaultString(rs.getString("SupportRestrictAsylComm"), "");
					
					AllNatllInstru[i][22] = StringUtils.defaultString(rs.getString("SupportReturn"), "");									
					AllNatllInstru[i][23] = StringUtils.defaultString(rs.getString("RestrictReturn"), "");
					AllNatllInstru[i][24] = StringUtils.defaultString(rs.getString("SupportRestrictReturnComm"), "");
					
					AllNatllInstru[i][25] = StringUtils.defaultString(rs.getString("SupportState"), "");									
					AllNatllInstru[i][26] = StringUtils.defaultString(rs.getString("RestrictState"), "");
					AllNatllInstru[i][27] = StringUtils.defaultString(rs.getString("SupportRestrictStateComm"), "");
					
					AllNatllInstru[i][28] = StringUtils.defaultString(rs.getString("Discrimination"), "");
					AllNatllInstru[i][29] = StringUtils.defaultString(rs.getString("DiscriminationOther"), "");
		
					AllNatllInstru[i][30] = StringUtils.defaultString(rs.getString("OtherLegis"), "");
					AllNatllInstru[i][31] = StringUtils.defaultString(rs.getString("OtherLegisComments"), "");
					AllNatllInstru[i][32] = StringUtils.defaultString(rs.getString("OtherLegisChecked"), "");
					
					AllNatllInstru[i][33] = StringUtils.defaultString(rs.getString("Articles"), "");

					AllNatllInstru[i][34] = StringUtils.defaultString(rs.getString("ArticleComments"), "");
					
					AllNatllInstru[i][35] = StringUtils.defaultString(rs.getString("Comments"), "");
					
					
					
					i++;
				}		

				countryObj.setNatlInstruments(AllNatllInstru);
				
			} else {
				//If there is no information in the database for the country, set everything to an empty string.

				for(int j=0;j<36;j++){
					NoNatlInstru[0][j] = "No Data in DB";
				}
				countryObj.setNatlInstruments(NoNatlInstru);
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

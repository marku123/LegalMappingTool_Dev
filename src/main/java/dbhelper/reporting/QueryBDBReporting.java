package dbhelper.reporting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.*;

public class QueryBDBReporting {

	public static Country getRightsGroupsNatIntlIntruments(Country countryObj) {
		String countryName = countryObj.getCountryName();
		String[] RightsCategories = RightsCategoriesManagement.getRightsCategories();
		RightsGroup[] rg = new RightsGroup[RightsCategories.length];
		int j = 0;

		try {

			while (j < RightsCategories.length) {

				rg[j] = new RightsGroup();

				String IntlInstru[][] = getIntlIntruments(countryName, RightsCategories[j]);
				String NatlInstru[][] = getNatlIntruments(countryName, RightsCategories[j]);

				rg[j].setRightsGroupName(RightsCategories[j]);
				rg[j].setIntlInstruments(IntlInstru);
				rg[j].setNatlInstruments(NatlInstru);

				j++;

			}

			countryObj.setRightsGroups(rg);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryObj;
	}

	public static String[][] getIntlIntruments(String countryName, String RightsCategories) {
		Connection c = MySql.connect();
		String sql;
		PreparedStatement pst;
		ResultSet rs;
		String IntlInstruToReturn[][] = null;

		try {

			sql = "SELECT * FROM rightsgroup_b1_international " + "WHERE CountryName = ? and RightsGroup = ? " + "ORDER BY InstrumentName";

			pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, RightsCategories);
			rs = pst.executeQuery();

			// Get the number of records.
			rs.last();
			int noOfInstruments = rs.getRow();
			rs.beforeFirst();

			// Get all of the instruments from the result set.
			String IntlInstru[][] = new String[noOfInstruments][11];

			int i = 0;

			while (rs.next()) {

				IntlInstru[i][0] = StringUtils.defaultString(rs.getString("InstrumentName"), "");
				IntlInstru[i][1] = StringUtils.defaultString(rs.getString("InstrumentType"), "");
				
				IntlInstru[i][2] = StringUtils.defaultString(rs.getString("RefWorldLink"), "");
				IntlInstru[i][3] = StringUtils.defaultString(rs.getString("FileStorageName"), "");
				IntlInstru[i][4] = StringUtils.defaultString(rs.getString("FileDisplayName"), "");
				IntlInstru[i][5] = StringUtils.defaultString(rs.getString("FileURL"), "");
				
				IntlInstru[i][6] = StringUtils.defaultString(rs.getString("Ratified"), "");
				
				IntlInstru[i][7] = StringUtils.defaultString(rs.getString("Articles"), "");
				IntlInstru[i][8] = StringUtils.defaultString(rs.getString("Reservations"), "");
				IntlInstru[i][9] = StringUtils.defaultString(rs.getString("ReservationsNature"), "");
				IntlInstru[i][10] = StringUtils.defaultString(rs.getString("InstrumentID"), "");

				i++;
			}

			rs.close();
			pst.close();

			MySql.close(c);

			IntlInstruToReturn = IntlInstru;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return IntlInstruToReturn;

	}

	public static String[][] getNatlIntruments(String countryName, String RightsCategories) {

		Connection c = MySql.connect();
		String sql;
		PreparedStatement pst;
		ResultSet rs;
		String NatlInstruToReturn[][] = null;

		try {

			sql = "SELECT * FROM rightsgroup_b2_national " + "WHERE CountryName = ? and RightsGroup = ? " + "ORDER BY InstrumentName";

			pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			pst.setString(2, RightsCategories);
			rs = pst.executeQuery();

			// Get the number of records.
			rs.last();
			int noOfInstruments = rs.getRow();
			rs.beforeFirst();

			// Get all of the instruments from the result set.
			String NatlInstru[][] = new String[noOfInstruments][38];

			int i = 0;

			while (rs.next()) {

				NatlInstru[i][0] = StringUtils.defaultString(rs.getString("InstrumentName"), "");
				NatlInstru[i][1] = StringUtils.defaultString(rs.getString("RefWorldLink"), "");
				NatlInstru[i][2] = StringUtils.defaultString(rs.getString("FileStorageName"), "");
				NatlInstru[i][3] = StringUtils.defaultString(rs.getString("FileDisplayName"), "");
				NatlInstru[i][4] = StringUtils.defaultString(rs.getString("FileURL"), "");

				NatlInstru[i][5] = StringUtils.defaultString(rs.getString("FederalStateLocal"), "");
				NatlInstru[i][6] = StringUtils.defaultString(rs.getString("ConsistentStandards"), "");
				NatlInstru[i][7] = StringUtils.defaultString(rs.getString("ConsistentStandardsComm"), "");
				NatlInstru[i][8] = StringUtils.defaultString(rs.getString("Consistent1951"), "");
				NatlInstru[i][9] = StringUtils.defaultString(rs.getString("Consistent1951Comm"), "");

				NatlInstru[i][10] = StringUtils.defaultString(rs.getString("SupportNat"), "");
				NatlInstru[i][11] = StringUtils.defaultString(rs.getString("RestrictNat"), "");
				NatlInstru[i][12] = StringUtils.defaultString(rs.getString("SupportRestrictNatComm"), "");

				NatlInstru[i][13] = StringUtils.defaultString(rs.getString("SupportIDP"), "");
				NatlInstru[i][14] = StringUtils.defaultString(rs.getString("RestrictIDP"), "");
				NatlInstru[i][15] = StringUtils.defaultString(rs.getString("SupportRestrictIDPComm"), "");

				NatlInstru[i][16] = StringUtils.defaultString(rs.getString("SupportRef"), "");
				NatlInstru[i][17] = StringUtils.defaultString(rs.getString("RestrictRef"), "");
				NatlInstru[i][18] = StringUtils.defaultString(rs.getString("SupportRestrictRefComm"), "");

				NatlInstru[i][19] = StringUtils.defaultString(rs.getString("SupportAsyl"), "");
				NatlInstru[i][20] = StringUtils.defaultString(rs.getString("RestrictAsyl"), "");
				NatlInstru[i][21] = StringUtils.defaultString(rs.getString("SupportRestrictAsylComm"), "");

				NatlInstru[i][22] = StringUtils.defaultString(rs.getString("SupportReturn"), "");
				NatlInstru[i][23] = StringUtils.defaultString(rs.getString("RestrictReturn"), "");
				NatlInstru[i][24] = StringUtils.defaultString(rs.getString("SupportRestrictReturnComm"), "");

				NatlInstru[i][25] = StringUtils.defaultString(rs.getString("SupportState"), "");
				NatlInstru[i][26] = StringUtils.defaultString(rs.getString("RestrictState"), "");
				NatlInstru[i][27] = StringUtils.defaultString(rs.getString("SupportRestrictStateComm"), "");

				NatlInstru[i][28] = StringUtils.defaultString(rs.getString("Discrimination"), "");
				NatlInstru[i][29] = StringUtils.defaultString(rs.getString("DiscriminationOther"), "");

				NatlInstru[i][30] = StringUtils.defaultString(rs.getString("OtherLegis"), "");
				NatlInstru[i][31] = StringUtils.defaultString(rs.getString("OtherLegisComments"), "");
				NatlInstru[i][32] = StringUtils.defaultString(rs.getString("OtherLegisChecked"), "");

				NatlInstru[i][33] = StringUtils.defaultString(rs.getString("Articles"), "");

				NatlInstru[i][34] = StringUtils.defaultString(rs.getString("ArticleComments"), "");

				NatlInstru[i][35] = StringUtils.defaultString(rs.getString("Comments"), "");
				
				NatlInstru[i][36] = StringUtils.defaultString(rs.getString("AllParts"), "");
				NatlInstru[i][37] = StringUtils.defaultString(rs.getString("AllPartsComm"), "");
				i++;
			}

			rs.close();
			pst.close();

			MySql.close(c);

			NatlInstruToReturn = NatlInstru;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return NatlInstruToReturn;
	}
}

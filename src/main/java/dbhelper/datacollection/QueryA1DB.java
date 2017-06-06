package dbhelper.datacollection;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.*;

public class QueryA1DB {

	public static Country getLegalFrameworkConstIntro(Country countryObj) {
		Connection c = MySql.connect();
		String countryName = countryObj.getCountryName();
		
		try {

			String sql = "SELECT Constitution, ConstitutionDate, ConstitutionAmend, ConstitutionAmendDate, "
					+ "ConstLinkOrig, ConstLinkFrenEng, BillLinkOrig, BillLinkFrenEng "
					+ "FROM legalframework_a1_const_intro WHERE CountryName = ? ";
			
			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);
			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
				
					countryObj.setConstitutionYesNo(StringUtils.defaultString(rs.getString("Constitution"), ""));
					countryObj.setConstitutionDate(StringUtils.defaultString(rs.getString("ConstitutionDate"), ""));
					countryObj.setConstitutionAmendYesNo(StringUtils.defaultString(rs.getString("ConstitutionAmend"), ""));
					countryObj.setConstitutionAmendDate(StringUtils.defaultString(rs.getString("ConstitutionAmendDate"), ""));
					countryObj.setConstLinkFrenEng(StringUtils.defaultString(rs.getString("ConstLinkFrenEng"), ""));
					countryObj.setConstLinkOrig(StringUtils.defaultString(rs.getString("ConstLinkOrig"), ""));
					countryObj.setBillLinkFrenEng(StringUtils.defaultString(rs.getString("BillLinkFrenEng"), ""));
					countryObj.setBillLinkOrig(StringUtils.defaultString(rs.getString("BillLinkOrig"), ""));
				}
			} else {
					countryObj.setConstitutionYesNo("");
					countryObj.setConstitutionDate("");
					countryObj.setConstitutionAmendYesNo("");
					countryObj.setConstitutionAmendDate("");
					countryObj.setConstLinkFrenEng("");
					countryObj.setConstLinkOrig("");
					countryObj.setBillLinkFrenEng("");
					countryObj.setBillLinkOrig("");	
			}
			rs.close();
			pst.close();

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return countryObj;

	}

	public static Country getLegalFrameworkConstAppPOC(Country countryObj) {
		Connection c = MySql.connect();
		String ConstAppToPoC[][] = new String[5][2]; 
		String countryName = countryObj.getCountryName();
		int i = 0;
			
		try {

			String sql = "SELECT PoC, RightsApplyPoc, Comments "
					+ "FROM legalframework_a1_const_app_poc WHERE CountryName = ? ORDER BY PoC";

			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, countryName);

			ResultSet rs = pst.executeQuery();
			
			//Test to see if the country exists. If  the country doesn't exist then set everything to an empty string. 
			if (rs.isBeforeFirst()) {
				while (rs.next()) {
				    
					ConstAppToPoC[i][0] = StringUtils.defaultString(rs.getString("RightsApplyPoc"), "");
					ConstAppToPoC[i][1] = StringUtils.defaultString(rs.getString("Comments"), "");
				    i++;
					
				}
			} else {
				
				for(int j = 0; j < 5; j++) {
					ConstAppToPoC[j][0] = "";
					ConstAppToPoC[j][1] = "";
			    }
				
			}
			rs.close();
			pst.close();

			MySql.close(c);

			countryObj.setConstAppToPoC(ConstAppToPoC);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return countryObj;

	}
	

}

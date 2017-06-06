package dbhelper.datacollection;

import java.sql.*;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.*;

public class UpdateA1DB {

	static public void updateCountryLegalFrameworkConstIntro(HttpServletRequest request) {

		Connection c = MySql.connect();
		String sql = null;
		String country = request.getParameter("country");
		String constitution = StringUtils.defaultString(request.getParameter("constitutionyesno"), ""); 
		String constitutiondate = (String) request.getParameter("constdateeffect");
		String constamendyesno = StringUtils.defaultString(request.getParameter("constamendyesno"), ""); 
		String constitutionamenddate = (String) request.getParameter("constdateeffectamend").replace("'", "\\'");
		String constlinkorig = (String) request.getParameter("constlinkorig").replace("'", "\\'");
		String constlinkfreneng = (String) request.getParameter("constlinkfreneng").replace("'", "\\'");
		String billlinkorig = (String) request.getParameter("billlinkorig").replace("'", "\\'");
		String billlinkfreneng = (String) request.getParameter("billlinkfreneng").replace("'", "\\'");

		try {

			if (CountryExists.countryExists(country,"legalframework_a1_const_intro")) {

				sql = "UPDATE legalframework_a1_const_intro "
					+ "SET Constitution = '" + constitution + "', "
						+ "ConstitutionDate = '" + constitutiondate + "', "
						+ "ConstitutionAmend = '" + constamendyesno + "', "
						+ "ConstitutionAmendDate = '" + constitutionamenddate + "', "
						+ "ConstLinkOrig = '" + constlinkorig + "', "
						+ "ConstLinkFrenEng = '" + constlinkfreneng + "', "
						+ "BillLinkOrig = '" + billlinkorig + "', "
						+ "BillLinkFrenEng = '" + billlinkfreneng + "' "
					+ "WHERE CountryName = ?";
				

			} else {
				
				sql = "INSERT INTO legalframework_a1_const_intro "
						+ "(CountryName,Constitution,ConstitutionDate,ConstitutionAmend,ConstitutionAmendDate,"
						+ "ConstLinkOrig,ConstLinkFrenEng,BillLinkOrig,BillLinkFrenEng) VALUES"
						+ "(?,'" + constitution + "','" + constitutiondate + "','" + constamendyesno + "',"
						+ "'" + constitutionamenddate + "','" + constlinkorig + "','" + constlinkfreneng + "',"
						+ "'" + billlinkorig + "','" + billlinkfreneng + "')"; 			
			}
					


			PreparedStatement pst = c.prepareStatement(sql);
			pst.setString(1, country);
			pst.executeUpdate();
			pst.close();
			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public void updateLegalFrameworkConstAppPOC(HttpServletRequest request) {

		Connection c = MySql.connect();
		int numberOfPOCs = 5;
		String sql[] = new String[numberOfPOCs];
		PreparedStatement[] pst = new PreparedStatement[numberOfPOCs];
		String POC[] = new String[numberOfPOCs];
		String constitutionPOC[] = new String[numberOfPOCs];
		String constitutionPOCComment[] = new String[numberOfPOCs];

		String country = request.getParameter("country");
		
		POC[0] = request.getParameter("POC1");
		constitutionPOC[0] = request.getParameter("constitutionPOC1");
		constitutionPOCComment[0] = request.getParameter("constitutionPOC1Comment").replace("'", "\\'");
		
		POC[1] = request.getParameter("POC2");
		constitutionPOC[1] = request.getParameter("constitutionPOC2");
		constitutionPOCComment[1]  = request.getParameter("constitutionPOC2Comment").replace("'", "\\'");
		
		POC[2] = request.getParameter("POC3");
		constitutionPOC[2] = request.getParameter("constitutionPOC3");
		constitutionPOCComment[2]  = request.getParameter("constitutionPOC3Comment").replace("'", "\\'");

		POC[3] = request.getParameter("POC4");
		constitutionPOC[3] = request.getParameter("constitutionPOC4");
		constitutionPOCComment[3]  = request.getParameter("constitutionPOC4Comment").replace("'", "\\'");
		
		POC[4] = request.getParameter("POC5");
		constitutionPOC[4] = request.getParameter("constitutionPOC5");
		constitutionPOCComment[4]  = request.getParameter("constitutionPOC5Comment").replace("'", "\\'");

		try {

			if (CountryExists.countryExists(country, "legalframework_a1_const_app_poc")) {

				for(int i = 0; i<5; i++) {
					sql[i] = "UPDATE legalframework_a1_const_app_poc SET RightsApplyPoc = "
							+ "'" + constitutionPOC[i] + "' , Comments = '" + constitutionPOCComment[i] + "' "
							+ "WHERE CountryName = ? AND PoC = '" + POC[i]+"' ";				
				}
				
			} else {
				
				for(int i = 0; i<5; i++) {
					sql[i] = "INSERT INTO legalframework_a1_const_app_poc "
							+ "(CountryName,PoC,RightsApplyPoc,Comments) VALUES"
							+ "(?,'"+POC[i]+"','"+constitutionPOC[i]+"','"+constitutionPOCComment[i]+"')";							
				}
			}
						
			for(int i = 0; i<5; i++) {
							
				pst[i] = c.prepareStatement(sql[i]);
				pst[i].setString(1, country);
				pst[i].executeUpdate();
				pst[i].close();		
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

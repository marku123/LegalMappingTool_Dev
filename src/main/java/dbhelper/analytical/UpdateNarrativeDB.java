package dbhelper.analytical;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.DeleteCountry;
import dbhelper.dbutilities.MySql;

public class UpdateNarrativeDB {

	
	static public void updateNarrative(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	

		String sql;
		PreparedStatement pst;

		String narrative1 = StringUtils.defaultString(request.getParameter("narrative1").replace("'", "\\'"),"");
		String narrative2 = StringUtils.defaultString(request.getParameter("narrative2").replace("'", "\\'"),"");
		String narrative3 = StringUtils.defaultString(request.getParameter("narrative3").replace("'", "\\'"),"");
		
				
		try {

			DeleteCountry.deleteCountryFromTable(country, "analytical_narrative");
		

			sql = "INSERT INTO analytical_narrative "
						+ "(CountryName,"
							+ "Narrative1,Narrative2,Narrative3) VALUES"
							+ "('"+country+"'"
							+ ",'"+narrative1+"','"+narrative2+"','"+narrative3+"')";	
				
		
			pst = c.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();


			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}		
	
	static public void updatePrioritiesNarrative(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	

		String sql;
		PreparedStatement pst;
		String narrative1 = StringUtils.defaultString(request.getParameter("narrative4").replace("'", "\\'"),"");
		String narrative2 = StringUtils.defaultString(request.getParameter("narrative5").replace("'", "\\'"),"");
				
		try {
		
			sql = "INSERT INTO poc_priorities_d4_narrative "
						+ "(CountryName,Narrative1,Narrative2) VALUES "
							+ "('"+country+"','"+ narrative1 +"','"+narrative2+"')"
							+ "ON DUPLICATE KEY "
							+ "UPDATE "
								+ "Narrative1 = '"+narrative1+"', "
								+ "Narrative2 = '"+narrative2+"'"
								+ "";				
		
			pst = c.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();


			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

}

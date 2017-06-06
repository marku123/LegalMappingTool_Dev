package dbhelper.feedback;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.MySql;

public class FeedbackDB {
	
	static public boolean updateFeedbackDB(HttpServletRequest request) {
		Connection c = MySql.connect();

		String sql;
		PreparedStatement pst;
		Boolean successfailure;

		String countryName = StringUtils.defaultString(request.getParameter("feedbackcountry").replace("'", "\\'"),"");
		String username = StringUtils.defaultString(request.getParameter("userName").replace("'", "\\'"),"");
		String feedbacktext = StringUtils.defaultString(request.getParameter("feedbacktext").replace("'", "\\'"),"");
		String filename = StringUtils.defaultString(request.getParameter("feedbackfilename").replace("'", "\\'"),"");
				
		try {

			sql = "INSERT INTO feedback "
						+ "(CountryName,"
							+ "UserName,FeedbackText,FileName,TimeStamp) VALUES"
							+ "('"+countryName+"'"
							+ ",'"+username+"','"+feedbacktext+"','"+filename+"',NOW())";	
				
		
			pst = c.prepareStatement(sql);
			pst.executeUpdate();
			pst.close();

			MySql.close(c);
			
			successfailure = true;

		} catch (Exception e) {
			successfailure = false;

			e.printStackTrace();
		}
		
		return successfailure;
	
	}	
	

}

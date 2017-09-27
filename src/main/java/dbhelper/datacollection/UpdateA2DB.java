package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.CountryExists;
import dbhelper.dbutilities.DeleteCountry;
import dbhelper.dbutilities.MySql;

public class UpdateA2DB {

	static public void updateCountryA2Intro(HttpServletRequest request) {

		Connection c = MySql.connect();
		String sql = null;
		String country = request.getParameter("country");
		
		String populationgroupschecked = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("populationgroupschecked[]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(", ", ",");
		String populationgroupscomments = StringUtils.defaultString(request.getParameter("populationgroupscomments").replace("'", "\\'"), "");

		String commoncivilplural = StringUtils.defaultString(request.getParameter("commoncivilplural"), "");
		String commoncivilpluraltext = StringUtils.defaultString(request.getParameter("commoncivilpluraltext").replace("'", "\\'"), "");	
		String federalstate = StringUtils.defaultString(request.getParameter("federalstate"), "");
		String tradmechcomments = StringUtils.defaultString(request.getParameter("tradmechcomments").replace("'", "\\'"), "");
		String legalsystemcomments = StringUtils.defaultString(request.getParameter("legalsystemcomments").replace("'", "\\'"), "");

		try {

			if (CountryExists.countryExists(country,"legalframework_a2_legalsystem_intro")) {

				sql = "UPDATE legalframework_a2_legalsystem_intro "
					+ "SET "
						+ "POC = '" + populationgroupschecked + "', "
						+ "POCComments = '" + populationgroupscomments + "', "
						+ "CommonCivilPlural = '" + commoncivilplural + "', "
						+ "PluralText = '" + commoncivilpluraltext + "', "
						+ "FederalState = '" + federalstate + "', "
						+ "TradMechComments = '" + tradmechcomments + "', "
						+ "Comments = '" + legalsystemcomments + "' "
					+ "WHERE CountryName = ?";

			} else {
				
				
				sql = "INSERT INTO legalframework_a2_legalsystem_intro "
						+ "(CountryName,POC,POCComments, CommonCivilPlural,PluralText,FederalState,TradMechComments,"
						+ "Comments) VALUES "
						+ "(?,'" + populationgroupschecked + "','" + populationgroupscomments + "','" + commoncivilplural + "','" + commoncivilpluraltext + "',"
						+ "'" + federalstate + "','" + tradmechcomments + "','" + legalsystemcomments + "')"; 	

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
	
	static public void updateA2JudicialEntities(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	

		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfEntitiesCourts = CountRows.getNumberOfRowsInTable(request,"entitycourt");		
		String sql[] = new String[numberOfEntitiesCourts];
		PreparedStatement[] pst = new PreparedStatement[numberOfEntitiesCourts];
		String entitycourt[] = new String[numberOfEntitiesCourts];
		String refugeesaccesscourt[] = new String[numberOfEntitiesCourts];
		String IDPsaccesscourt[] = new String[numberOfEntitiesCourts];
		String returneesaccesscourt[] = new String[numberOfEntitiesCourts];
		String statelessaccesscourt[] = new String[numberOfEntitiesCourts];
		String asylumaccesscourt[] = new String[numberOfEntitiesCourts];


		//Get all of the data from the request.
		for (int i=0;i< numberOfEntitiesCourts;i++){
			entitycourt[i] = StringUtils.defaultString(request.getParameter("entitycourt["+i+"]").replace("'", "\\'"),"");
			refugeesaccesscourt[i] = StringUtils.defaultString(request.getParameter("refugeesaccesscourt["+i+"]"),"");
			IDPsaccesscourt[i] = StringUtils.defaultString(request.getParameter("IDPsaccesscourt["+i+"]"),"");
			returneesaccesscourt[i] = StringUtils.defaultString(request.getParameter("returneesaccesscourt["+i+"]"),"");
			statelessaccesscourt[i] = StringUtils.defaultString(request.getParameter("statelessaccesscourt["+i+"]"),"");
			asylumaccesscourt[i] = StringUtils.defaultString(request.getParameter("asylumaccesscourt["+i+"]"),"");
		}
			
		try {

			DeleteCountry.deleteCountryFromTable(country, "legalframework_a2_legalsystem_judicial");

			for(int i = 0; i<numberOfEntitiesCourts; i++) { 
				
				//Ensure that we are not inserting an empty row.
				if (entitycourt[i] != "") {
					
					sql[i] = "INSERT INTO legalframework_a2_legalsystem_judicial "
							+ "(CountryName,EntityCourtIndex,JudicialEntityCourt,RefugessCanAccess,"
							+ "IDPCanAccess,ReturneesCanAccess,StatelessCanAccess,AsylumCanAccess) VALUES"
							+ "(?,'"+ i +"','"+entitycourt[i]+"','"+refugeesaccesscourt[i]+"'"
							+ ",'"+IDPsaccesscourt[i]+"','"+returneesaccesscourt[i]+"','"+statelessaccesscourt[i]+"',"
							+ "'" + asylumaccesscourt[i] + "')";
					
					pst[i] = c.prepareStatement(sql[i]);
					pst[i].setString(1, country);
					pst[i].executeUpdate();
					pst[i].close();
				}
							
			}
			

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
		
	static public void updateA2AdminEntities(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	

		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfAdminEntities = CountRows.getNumberOfRowsInTable(request,"adminentityname");		
		String sql[] = new String[numberOfAdminEntities];
		PreparedStatement[] pst = new PreparedStatement[numberOfAdminEntities];
		String adminentityname[] = new String[numberOfAdminEntities];
		String refugeesaccessadmin[] = new String[numberOfAdminEntities];
		String IDPsaccessadmin[] = new String[numberOfAdminEntities];
		String returneesaccessadmin[] = new String[numberOfAdminEntities];
		String statelessaccessadmin[] = new String[numberOfAdminEntities];
		String asylumaccessadmin[] = new String[numberOfAdminEntities];


		//Get all of the data from the request.
		for (int i=0;i< numberOfAdminEntities;i++){
			adminentityname[i] = StringUtils.defaultString(request.getParameter("adminentityname["+i+"]").replace("'", "\\'"),"");
			refugeesaccessadmin[i] = StringUtils.defaultString(request.getParameter("refugeesaccessadmin["+i+"]"),"");
			IDPsaccessadmin[i] = StringUtils.defaultString(request.getParameter("IDPsaccessadmin["+i+"]"),"");
			returneesaccessadmin[i] = StringUtils.defaultString(request.getParameter("returneesaccessadmin["+i+"]"),"");
			statelessaccessadmin[i] = StringUtils.defaultString(request.getParameter("statelessaccessadmin["+i+"]"),"");
			asylumaccessadmin[i] = StringUtils.defaultString(request.getParameter("asylumaccessadmin["+i+"]"),"");

		}
			
		try {

			DeleteCountry.deleteCountryFromTable(country, "legalframework_a2_legalsystem_admin");
			
			for(int i = 0; i<numberOfAdminEntities; i++) {

				//Ensure that we are not inserting an empty row.

				if (adminentityname[i] != "") {

					sql[i] = "INSERT INTO legalframework_a2_legalsystem_admin "
							+ "(CountryName,AdminEntityIndex,AdminEntityName,RefugessCanAccess,"
								+ "IDPCanAccess,ReturneesCanAccess,StatelessCanAccess,AsylumCanAccess) VALUES "
								+ "(?,'"+ i +"','"+adminentityname[i]+"','"+refugeesaccessadmin[i]+"'"
								+ ",'"+IDPsaccessadmin[i]+"','"+returneesaccessadmin[i]+"','"+statelessaccessadmin[i]+"',"
								+ "'" + asylumaccessadmin[i] + "')";	
				
					pst[i] = c.prepareStatement(sql[i]);
					pst[i].setString(1, country);
					pst[i].executeUpdate();
					pst[i].close();
				
				}
			}
		

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}	

	static public void updateA2TradMechanisms(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	

		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfTradMechanisms = CountRows.getNumberOfRowsInTable(request,"tradmechname");
		//Initialize the variables.
		String sql[] = new String[numberOfTradMechanisms];
		PreparedStatement[] pst = new PreparedStatement[numberOfTradMechanisms];
		String tradmechname[] = new String[numberOfTradMechanisms];
		String refugeesaccesstrad[] = new String[numberOfTradMechanisms];
		String IDPsaccesstrad[] = new String[numberOfTradMechanisms];
		String returneesaccesstrad[] = new String[numberOfTradMechanisms];
		String statelessaccesstrad[] = new String[numberOfTradMechanisms];
		String asylumaccesstrad[] = new String[numberOfTradMechanisms];


		//Get all of the data from the request.
		for (int i=0;i< numberOfTradMechanisms;i++){
			tradmechname[i] = StringUtils.defaultString(request.getParameter("tradmechname["+i+"]").replace("'", "\\'"),"");
			refugeesaccesstrad[i] = StringUtils.defaultString(request.getParameter("refugeesaccesstrad["+i+"]"),"");
			IDPsaccesstrad[i] = StringUtils.defaultString(request.getParameter("IDPsaccesstrad["+i+"]"),"");
			returneesaccesstrad[i] = StringUtils.defaultString(request.getParameter("returneesaccesstrad["+i+"]"),"");
			statelessaccesstrad[i] = StringUtils.defaultString(request.getParameter("statelessaccesstrad["+i+"]"),"");
			asylumaccesstrad[i] = StringUtils.defaultString(request.getParameter("asylumaccesstrad["+i+"]"),"");
		}
			
		try {

			DeleteCountry.deleteCountryFromTable(country, "legalframework_a2_legalsystem_trad");
			
			for(int i = 0; i<numberOfTradMechanisms; i++) {

				//Ensure that we are not inserting an empty row.
				if (tradmechname[i] != "") {

					sql[i] = "INSERT INTO legalframework_a2_legalsystem_trad "
							+ "(CountryName,TradMechIndex,TradMechName,RefugessCanAccess,"
								+ "IDPCanAccess,ReturneesCanAccess,StatelessCanAccess,AsylumCanAccess) VALUES"
								+ "(?,'"+ i +"','"+tradmechname[i]+"','"+refugeesaccesstrad[i]+"'"
								+ ",'"+IDPsaccesstrad[i]+"','"+returneesaccesstrad[i]+"','"+statelessaccesstrad[i]+"',"
								+ "'" + asylumaccesstrad[i] + "')";	
					
					pst[i] = c.prepareStatement(sql[i]);
					pst[i].setString(1, country);
					pst[i].executeUpdate();
					pst[i].close();
					
				}
				
			}
		
			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}		
}
	
	


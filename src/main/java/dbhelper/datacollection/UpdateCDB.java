package dbhelper.datacollection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.DeleteCountry;
import dbhelper.dbutilities.MySql;

public class UpdateCDB {
	
	static public void updateCObstacles(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        String personofconcern = request.getParameter("personofconcern");
		
		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfRightsGroups = CountRows.countRows("rightscategory");
		//Initialize the variables.
		String sql[] = new String[numberOfRightsGroups];
		PreparedStatement[] pst = new PreparedStatement[numberOfRightsGroups];
		
		String rightsgroup[] = new String[numberOfRightsGroups];

		String legalobsdrop[] = new String[numberOfRightsGroups];
		String legalobscomments[] = new String[numberOfRightsGroups];
		String legalobsgrps[] = new String[numberOfRightsGroups];
		
		String financeobsdrop[] = new String[numberOfRightsGroups];
		String financeobscomments[] = new String[numberOfRightsGroups];
		String finobsgrps[] = new String[numberOfRightsGroups];

		String docobsdrop[] = new String[numberOfRightsGroups];
		String docobscomments[] = new String[numberOfRightsGroups];
		String docobsgrps[] = new String[numberOfRightsGroups];

		String geoobsdrop[] = new String[numberOfRightsGroups];
		String geoobscomments[] = new String[numberOfRightsGroups];
		String geoobsgrps[] = new String[numberOfRightsGroups];

		String adminobsdrop[] = new String[numberOfRightsGroups];
		String adminobscomments[] = new String[numberOfRightsGroups];
		String adminobsgrps[] = new String[numberOfRightsGroups];

		String secobsdrop[] = new String[numberOfRightsGroups];
		String secobscomments[] = new String[numberOfRightsGroups];
		String secobsgrps[] = new String[numberOfRightsGroups];

		String discrimobsdrop[] = new String[numberOfRightsGroups];
		String discrimobscomments[] = new String[numberOfRightsGroups];
		String discrimobsgrps[] = new String[numberOfRightsGroups];

		String otherobsdrop[] = new String[numberOfRightsGroups];
		String otherobsname[] = new String[numberOfRightsGroups];
		String otherobscomments[] = new String[numberOfRightsGroups];
		String otherobsgrps[] = new String[numberOfRightsGroups];
	
		String other2obsdrop[] = new String[numberOfRightsGroups];
		String other2obsname[] = new String[numberOfRightsGroups];
		String other2obscomments[] = new String[numberOfRightsGroups];
		String other2obsgrps[] = new String[numberOfRightsGroups];
		
		//Get all of the data from the request.
		for (int i=0;i< numberOfRightsGroups;i++){
			rightsgroup[i] = StringUtils.defaultString(request.getParameter("rightsgroup["+i+"]"),"");

			legalobsdrop[i] = StringUtils.defaultString(request.getParameter("legalobsdrop["+i+"]"),"");
			legalobscomments[i] = StringUtils.defaultString(request.getParameter("legalobscomments["+i+"]").replace("'", "\\'"),"");
			legalobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("legalobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
				
			financeobsdrop[i] = StringUtils.defaultString(request.getParameter("financeobsdrop["+i+"]"),"");
			financeobscomments[i] = StringUtils.defaultString(request.getParameter("financeobscomments["+i+"]").replace("'", "\\'"),"");
			finobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("finobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
			
			docobsdrop[i] = StringUtils.defaultString(request.getParameter("docobsdrop["+i+"]"),"");
			docobscomments[i] = StringUtils.defaultString(request.getParameter("docobscomments["+i+"]").replace("'", "\\'"),"");
			docobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("docobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
			
			geoobsdrop[i] = StringUtils.defaultString(request.getParameter("geoobsdrop["+i+"]"),"");
			geoobscomments[i] = StringUtils.defaultString(request.getParameter("geoobscomments["+i+"]").replace("'", "\\'"),"");
			geoobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("geoobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
			
			adminobsdrop[i] = StringUtils.defaultString(request.getParameter("adminobsdrop["+i+"]"),"");
			adminobscomments[i] = StringUtils.defaultString(request.getParameter("adminobscomments["+i+"]").replace("'", "\\'"),"");
			adminobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("adminobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
			
			secobsdrop[i] = StringUtils.defaultString(request.getParameter("secobsdrop["+i+"]"),"");
			secobscomments[i] = StringUtils.defaultString(request.getParameter("secobscomments["+i+"]").replace("'", "\\'"),"");
			secobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("secobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");
			
			
			discrimobsdrop[i] = StringUtils.defaultString(request.getParameter("discrimobsdrop["+i+"]"),"");
			discrimobscomments[i] = StringUtils.defaultString(request.getParameter("discrimobscomments["+i+"]").replace("'", "\\'"),"");
			discrimobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("discrimobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");

			otherobsname[i] = StringUtils.defaultString(request.getParameter("otherobsname["+i+"]").replace("'", "\\'"),"");
			otherobsdrop[i] = StringUtils.defaultString(request.getParameter("otherobsdrop["+i+"]"),"");
			otherobscomments[i] = StringUtils.defaultString(request.getParameter("otherobscomments["+i+"]").replace("'", "\\'"),"");
			otherobsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("otherobsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");

			other2obsname[i] = StringUtils.defaultString(request.getParameter("other2obsname["+i+"]").replace("'", "\\'"),"");
			other2obsdrop[i] = StringUtils.defaultString(request.getParameter("other2obsdrop["+i+"]"),"");
			other2obscomments[i] = StringUtils.defaultString(request.getParameter("other2obscomments["+i+"]").replace("'", "\\'"),"");
			other2obsgrps[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("other2obsgrps["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll(",\\s",",");

		}

		try {

			DeleteCountry.deleteCountryPOCFromTable(country, personofconcern, "obstacles_c1");
			
			for(int i = 0; i<numberOfRightsGroups; i++) { 
				sql[i] = "INSERT INTO obstacles_c1 "
						+ "(CountryName,POC,RightsGroup,LegalStatusObs,LegalStatusObsComment,LegalObsGrps,"
							+ "FinancialObs,FinancialObsComment,FinObsGrps,"
							+ "DocObs,DocObsComment,DocObsGrps,"
							+ "GeoObs,GeoObsComment,GeoObsGrps,"
							+ "AdminObs,AdminObsComment,AdminObsGrps,"
							+ "SecObs,SecObsComment,SecObsGrps,"
							+ "DiscrimObs,DiscrimObsComment,DiscrimObsGrps,"
							+ "OtherNameObs,OtherObs,OtherObsComment,OtherObsGrps,"
							+ "Other2NameObs,Other2Obs,Other2ObsComment,Other2ObsGrps) VALUES"
							+ "(?,'"+ personofconcern +"','"+rightsgroup[i]+"','"+legalobsdrop[i]+"','"+legalobscomments[i]+"',?,"
									+ "'"+financeobsdrop[i]+"','"+financeobscomments[i]+"',?,"
									+ "'"+docobsdrop[i]+"','"+docobscomments[i]+"',?,"
									+ "'"+geoobsdrop[i]+"','"+geoobscomments[i]+"',?,"
									+ "'"+adminobsdrop[i]+"','"+adminobscomments[i]+"',?,"
									+ "'"+secobsdrop[i]+"','" + secobscomments[i] + "',?,"
									+ "'"+discrimobsdrop[i]+"','"+discrimobscomments[i]+"',?,"
									+ "'"+otherobsname[i]+"','"+otherobsdrop[i]+"','"+otherobscomments[i]+"',?,"
									+ "'"+other2obsname[i]+"','"+other2obsdrop[i]+"','"+other2obscomments[i]+"',?"
								+ ")";	
						
				pst[i] = c.prepareStatement(sql[i]);
				pst[i].setString(1, country);
				pst[i].setString(2, legalobsgrps[i]);
				pst[i].setString(3, finobsgrps[i]);
				pst[i].setString(4, docobsgrps[i]);
				pst[i].setString(5, geoobsgrps[i]);
				pst[i].setString(6, adminobsgrps[i]);
				pst[i].setString(7, secobsgrps[i]);
				pst[i].setString(8, discrimobsgrps[i]);
				pst[i].setString(9, otherobsgrps[i]);
				pst[i].setString(10, other2obsgrps[i]);

				pst[i].executeUpdate();
				pst[i].close();
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	static public void updateCObstacleDocumentation(HttpServletRequest request) {
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        String personofconcern = request.getParameter("personofconcern");
		 
		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfDocs = CountRows.getNumberOfRowsInTable(request,"docStorageName");

		String docStorageName[] = new String[numberOfDocs];
		String docDisplayName[] = new String[numberOfDocs];
		String docURL[] = new String[numberOfDocs];
		
		for (int i=0;i< numberOfDocs;i++){
			docStorageName[i] = StringUtils.defaultString(request.getParameter("docStorageName["+i+"]"),"");
			docDisplayName[i] = StringUtils.defaultString(request.getParameter("docDisplayName["+i+"]"),"");
			docURL[i] = StringUtils.defaultString(request.getParameter("docURL["+i+"]").replace("\\", "\\\\"),"");
			
		}
				
		try {
		
			for(int i = 0; i<numberOfDocs; i++) {

				String sql = "INSERT INTO obstacles_c2_documentation "
							+ "(CountryName,POC,FileStorageName,FileDisplayName,FileURL) VALUES "
								+ "('"+country+"','"+ personofconcern +"','"+docStorageName[i]+"','"+docDisplayName[i]+"','"+docURL[i]+"')"
								+ "ON DUPLICATE KEY "
								+ "UPDATE "
									+ "FileDisplayName = '"+docDisplayName[i]+"', "
									+ "FileURL = '"+docURL[i]+"' "
									+ "";				
			
				PreparedStatement pst = c.prepareStatement(sql);
				pst.executeUpdate();
				pst.close();
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
}

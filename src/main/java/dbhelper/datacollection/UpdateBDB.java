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
import dbhelper.dbutilities.RightsCategoriesManagement;

public class UpdateBDB {

	
	static public void updateB1InternationalIntruRepository(HttpServletRequest request) {
		
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        int numberOfRightsCategories = RightsCategoriesManagement.getRightsCategories().length;
		String[] AllRightsCategories = new String[numberOfRightsCategories];
		AllRightsCategories =  RightsCategoriesManagement.getRightsCategories();
		
		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfIntlIntruments = CountRows.getNumberOfRowsInTable(request,"intlinstruname");
		//Initialize the variables.

		
		String intlinstruname[] = new String[numberOfIntlIntruments];
		String intllinstrureflink[] = new String[numberOfIntlIntruments];
		String intlFileStorageName[] = new String[numberOfIntlIntruments];
		String intlFileDisplayName[] = new String[numberOfIntlIntruments];
		String intlFileURL[] = new String[numberOfIntlIntruments];
		String intlinstrutype[] = new String[numberOfIntlIntruments];
		String intlinstruratified[] = new String[numberOfIntlIntruments];
		String intlinstrnameOrig[] = new String[numberOfIntlIntruments];
		String intlinstruratifiedOrig[] = new String[numberOfIntlIntruments];
		String intlinstrrightschecked[] = new String[numberOfIntlIntruments];

		//Get all of the data from the request.
		for (int i=0;i< numberOfIntlIntruments;i++){
			intlinstruname[i] = StringUtils.defaultString(request.getParameter("intlinstruname["+i+"]"),"");
			
			intllinstrureflink[i] = StringUtils.defaultString(request.getParameter("intlinstrureflink["+i+"]"),"").replace("'", "\\'");

			intlFileStorageName[i] = StringUtils.defaultString(request.getParameter("intlFileStorageName["+i+"]"),"");
			intlFileDisplayName[i] = StringUtils.defaultString(request.getParameter("intlFileDisplayName["+i+"]"),"");
			intlFileURL[i] = StringUtils.defaultString(request.getParameter("intlFileURL["+i+"]").replace("\\", "\\\\"),"");
			
			intlinstrutype[i] = StringUtils.defaultString(request.getParameter("intlinstrutype["+i+"]"),"");
			intlinstruratified[i] = StringUtils.defaultString(request.getParameter("intlinstruratified["+i+"]"),"");
			
			intlinstrnameOrig[i] = StringUtils.defaultString(request.getParameter("intlinstrnameOrig["+i+"]"),"");
			intlinstruratifiedOrig[i] = StringUtils.defaultString(request.getParameter("intlinstruratifiedOrig["+i+"]"),"");

			intlinstrrightschecked[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("intlinstrrightschecked["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll("\\s+","");

		}
			
		
		try {
			//Loop through all of the instruments and add/update the data in the database.
			for(int i = 0; i<numberOfIntlIntruments; i++) {

				//If we are inserting an empty instrument, skip it and go to the next instrument.
				if (intlinstruname[i].equals(""))
					continue;
					
				//If we are dealing with a preentered international instrument then just update the ratified data and 
				//then go to the next instrument.
				if ((intllinstrureflink[i].equals("preentered"))) {
					
					//If nothing has changed in the preentered instrument then skip it and go to the next instrument.
					if(intlinstruratifiedOrig[i].equals(intlinstruratified[i]))
							continue;
					
					String sql;
					PreparedStatement pst;
					sql = "UPDATE rightsgroup_b1_international SET "
									+ "Ratified = '"+intlinstruratified[i]+"' "
								+ "WHERE CountryName = ? AND "
								+ "InstrumentName = ?";
					pst = c.prepareStatement(sql);
					pst.setString(1, country);
					pst.setString(2, intlinstruname[i]);
					pst.executeUpdate();
					pst.close();
					continue;
				}
				
				//If the user changed the instrument name update it before doing anything else.
				if (!intlinstruname[i].equals(intlinstrnameOrig[i]) && !intlinstrnameOrig[i].equals("")) {	
					String sql;
					PreparedStatement pst;
					sql = "UPDATE rightsgroup_b1_international SET "
									+ "InstrumentName = ? "
								+ "WHERE CountryName = ? AND "
								+ "InstrumentName = ?";
					pst = c.prepareStatement(sql);
					pst.setString(1, intlinstruname[i]);
					pst.setString(2, country);
					pst.setString(3, intlinstrnameOrig[i]);
					pst.executeUpdate();
					pst.close();
				}
				
				//Loop through all of the Rights Categories and update the instrument for each Rights Category.
				//This is for user added international instruments.
				for(int j = 0; j<numberOfRightsCategories + 1; j++) {
					
			        String rightsCategory;
					String rightsCategoryAbbrev;
					String sql;
					PreparedStatement pst;
					
					//Get the rights category unless we are dealing with the instrument with no rights category.
					if (j == numberOfRightsCategories) {
				        rightsCategory = "";
						rightsCategoryAbbrev = "";
					} else {
						rightsCategory = AllRightsCategories[j];
						rightsCategoryAbbrev = RightsCategoriesManagement.getRightCategoryAbbreviation(rightsCategory);
					}
					
			        //Check to see if the instrument belongs to a rights category. If it does then insert it into the database or 
			        //update the existing instrument.
			        //If it doesn't then make sure it is removed from the database. 
					if (intlinstrrightschecked[i].contains(rightsCategoryAbbrev) || (j==numberOfRightsCategories + 1)) {

						sql = "INSERT INTO rightsgroup_b1_international "
									+ "(CountryName,RightsGroup,InstrumentName,RefWorldLink,FileStorageName,FileDisplayName,FileURL,InstrumentType,Ratified,"
										+ "Articles,Reservations,ReservationsNature,InstrumentID) VALUES "
										+ "(?,'"+ rightsCategory +"',?,"
										+ "'"+intllinstrureflink[i]+"','"+intlFileStorageName[i]+"',"
										+ "'"+intlFileDisplayName[i]+"','"+intlFileURL[i]+"','"+intlinstrutype[i]+"',"
										+ "'"+intlinstruratified[i]+"','','','','') "	
									+ "ON DUPLICATE KEY "
									+ "UPDATE "
										+ "InstrumentName = ?, "
										+ "RefWorldLink = '"+intllinstrureflink[i]+"', "
										+ "FileStorageName = '"+intlFileStorageName[i]+"', "
										+ "FileDisplayName = '"+intlFileDisplayName[i]+"', "
										+ "FileURL = '"+intlFileURL[i]+"', "
										+ "InstrumentType = '"+intlinstrutype[i]+"', "
										+ "Ratified = '"+intlinstruratified[i]+"'";	
						
						pst = c.prepareStatement(sql);
						pst.setString(1, country);
						pst.setString(2, intlinstruname[i]);
						pst.setString(3, intlinstruname[i]);
						pst.executeUpdate();
						pst.close();
						
					
					} else {
						//Delete an instrument belonging to a rights category. Note that if there is no rights category selected,
						//it will not be deleted. 
						DeleteCountry.deleteCountryRightsGroupInstrumentFromTable(country, rightsCategory, intlinstruname[i],"rightsgroup_b1_international");
					}
				
				}
					
				}
				

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	static public void updateB1InternationalIntru(HttpServletRequest request) {

		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        String rightsGroup = request.getParameter("rightsgroup");	
		
		//Determine how many rows there are in the table we are submitting to the database.
		int numberOfIntlIntruments = CountRows.getNumberOfRowsInTable(request,"intlinstruname");
		//Initialize the variables.
		String sql[] = new String[numberOfIntlIntruments];
		PreparedStatement[] pst = new PreparedStatement[numberOfIntlIntruments];
		
		String intlinstruname[] = new String[numberOfIntlIntruments];

		String intlinstruarticles[] = new String[numberOfIntlIntruments];
		String intlinstrures[] = new String[numberOfIntlIntruments];
		String intlinstruresnature[] = new String[numberOfIntlIntruments];

		//Get all of the data from the request.
		for (int i=0;i< numberOfIntlIntruments;i++){
			intlinstruname[i] = StringUtils.defaultString(request.getParameter("intlinstruname["+i+"]"),"");
			
			intlinstruarticles[i] = StringUtils.defaultString(request.getParameter("intlinstruarticles["+i+"]").replace("'", "\\'"),"");
			intlinstrures[i] = StringUtils.defaultString(request.getParameter("intlinstrures["+i+"]"),"");
			intlinstruresnature[i] = StringUtils.defaultString(request.getParameter("intlinstruresnature["+i+"]").replace("'", "\\'"),"");

		}
			
		
		try {
					
			for(int i = 0; i<numberOfIntlIntruments; i++) {


				//Ensure that we are not inserting an empty row.
				if (!intlinstruname[i].equals("")) {	
						
		
					//If the instrument is new, enter it into the DB for the Rights Group. 
					//If it already exists, update record fields for the Rights Group. 
					
					sql[i] = "UPDATE rightsgroup_b1_international SET "
								+ "Articles = '"+intlinstruarticles[i]+"',"
								+ "Reservations = '"+intlinstrures[i]+"',"
								+ "ReservationsNature = '" + intlinstruresnature[i] + "' "
							+ "WHERE "
								+ "CountryName = ? AND "
								+ "RightsGroup = ? AND "
								+ "InstrumentName = ?";
					
			
					pst[i] = c.prepareStatement(sql[i]);
					pst[i].setString(1, country);
					pst[i].setString(2, rightsGroup);
					pst[i].setString(3, intlinstruname[i]);
					pst[i].executeUpdate();
					pst[i].close();
					
				}
				
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	static public void updateB2NationalIntruRepository(HttpServletRequest request) {

		//Initialize all variables. 
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
		int numberOfNatlIntruments = CountRows.getNumberOfRowsInTable(request,"natlinstruname");//Determine how many rows there are in the table we are submitting to the database.
		int numberOfRightsCategories = RightsCategoriesManagement.getRightsCategories().length;
		String[] AllRightsCategories = new String[numberOfRightsCategories];
		AllRightsCategories =  RightsCategoriesManagement.getRightsCategories();
		
		//Instrument name
		String natlinstruname[] = new String[numberOfNatlIntruments];
		
		//Refworld Link
		String natlinstrureflink[] = new String[numberOfNatlIntruments];
		
		//Upload files
		String natFileDisplayName[] = new String[numberOfNatlIntruments];
		String natFileStorageName[] = new String[numberOfNatlIntruments];
		String natFileURL[] = new String[numberOfNatlIntruments];

		//Instrument is applicable in all or parts.
		String natlinstruapplicable[] = new String[numberOfNatlIntruments];
		String natlinstruapplicablecomm[] = new String[numberOfNatlIntruments];
		
		//Other comments
		String natlinstrucomments[] = new String[numberOfNatlIntruments];
		
		//Instrument rights categories.
		String natinstrrightscategories[] = new String[numberOfNatlIntruments];

		//Original name of the instrument.
		String natinstrnameOrig[] = new String[numberOfNatlIntruments];

		//Get all of the data from the request.
		for (int i=0;i< numberOfNatlIntruments;i++){
			//Instrument Name			
			natlinstruname[i] = StringUtils.defaultString(request.getParameter("natlinstruname["+i+"]"),"");
			
			//Refworld Link
			natlinstrureflink[i] = StringUtils.defaultString(request.getParameter("natlinstrureflink["+i+"]"),"").replace("'", "\\'");
			
			//Upload files.
			natFileStorageName[i] = StringUtils.defaultString(request.getParameter("natFileStorageName["+i+"]"),"");
			natFileDisplayName[i] = StringUtils.defaultString(request.getParameter("natFileDisplayName["+i+"]"),"");
			natFileURL[i] = StringUtils.defaultString(request.getParameter("natFileURL["+i+"]").replace("\\", "\\\\"),"");

			//Instrument is applicable in all or parts or the country.
			natlinstruapplicable[i] = StringUtils.defaultString(request.getParameter("natlinstruapplicable["+i+"]"),"");
			natlinstruapplicablecomm[i] = StringUtils.defaultString(request.getParameter("natlinstruapplicablecomm["+i+"]").replace("'", "\\'"),"");
		
			//Rights categories
			natinstrrightscategories[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("natinstrrightscategories["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll("\\s+","");
			
			//Other Comments
			natlinstrucomments[i] = StringUtils.defaultString(request.getParameter("natlinstrucomments["+i+"]").replace("'", "\\'"),"");

			//Original instrument name.
			natinstrnameOrig[i] = StringUtils.defaultString(request.getParameter("natinstrnameOrig["+i+"]"),"");
			
		}
			
		try {

			
			for(int i = 0; i<numberOfNatlIntruments; i++) {
				
				//Ensure that we are not inserting an empty row.
				if (natlinstruname[i].equals("")) 
					continue;
					
				//If the user changed the instrument name update it before doing anything else.
				if (!natlinstruname[i].equals(natinstrnameOrig[i]) && !natinstrnameOrig[i].equals("")) {	
					
					String sql;
					PreparedStatement pst;
					sql = "UPDATE rightsgroup_b2_national SET "
									+ "InstrumentName = ? "
								+ "WHERE CountryName = ? AND "
								+ "InstrumentName = ?";
					pst = c.prepareStatement(sql);
					pst.setString(1, natlinstruname[i]);
					pst.setString(2, country);
					pst.setString(3, natinstrnameOrig[i]);
					pst.executeUpdate();
					pst.close();
				}
				
				//Loop through all of the Rights Categories and update the instrument for each Rights Category.
				//This is for user added international instruments.
				for(int j = 0; j<numberOfRightsCategories + 1; j++) {
					
					String rightsCategory;
					String rightsCategoryAbbrev;
					String sql;
					PreparedStatement pst;
					
					//Get the rights category unless we are dealing with the instrument with no rights category.
					if (j == numberOfRightsCategories) {
						rightsCategory = "";
						rightsCategoryAbbrev = "";
					} else {
						rightsCategory = AllRightsCategories[j];
						rightsCategoryAbbrev = RightsCategoriesManagement.getRightCategoryAbbreviation(rightsCategory);
					}
					
					//Check to see if the instrument belongs to a rights category. If it does then insert it into the database or 
					//update the existing instrument.
					//If it doesn't then make sure it is removed from the database. 
					if (natinstrrightscategories[i].contains(rightsCategoryAbbrev) || (j==numberOfRightsCategories + 1)) {
					
					
						sql = "INSERT INTO rightsgroup_b2_national "
								+ "(CountryName,RightsGroup,"
									+ "InstrumentName,RefWorldLink,"
									+ "FileStorageName,FileDisplayName,FileURL,"
									+ "FederalStateLocal,AllPartsComm,"
									+ "ConsistentStandards,ConsistentStandardsComm,Consistent1951,Consistent1951Comm,"
									+ "SupportNat,RestrictNat,SupportRestrictNatComm,"
									+ "SupportIDP,RestrictIDP,SupportRestrictIDPComm,"
									+ "SupportRef,RestrictRef,SupportRestrictRefComm,"
									+ "SupportAsyl,RestrictAsyl,SupportRestrictAsylComm,"
									+ "SupportReturn,RestrictReturn,SupportRestrictReturnComm,"
									+ "SupportState,RestrictState,SupportRestrictStateComm,"
									+ "Discrimination,DiscriminationOther,"
									+ "OtherLegis,OtherLegisComments,OtherLegisChecked,"
									+ "Articles,ArticleComments,"
									+ "Comments) VALUES"
									+ "(?,?,?,"
									+ "'"+natlinstrureflink[i]+"',"
									+ "'"+natFileStorageName[i]+"','"+natFileDisplayName[i]+"','"+natFileURL[i]+"',"
									+ "'"+natlinstruapplicable[i]+"','"+natlinstruapplicablecomm[i]+"',"
									+ "'','','','',"				
									+ "'','','',"
									+ "'','','',"
									+ "'','','',"
									+ "'','','',"
									+ "'','','',"
									+ "'','','',"
									+ "'','',"
									+ "'','','',"
									+ "'','',"
									+ "'" + natlinstrucomments[i] + "') "
								+ "ON DUPLICATE KEY "
								+ "UPDATE "
									+ "RefWorldLink = '"+natlinstrureflink[i]+"', "
									+ "FileStorageName = '"+natFileStorageName[i]+"', "
									+ "FileDisplayName = '"+natFileDisplayName[i]+"', "
									+ "FileURL = '"+natFileURL[i]+"', "
									+ "FederalStateLocal = '"+natlinstruapplicable[i]+"', "
									+ "AllPartsComm = '"+natlinstruapplicablecomm[i]+"', "
									+ "Comments = '"+natlinstrucomments[i]+"'"
									+ "";	
						
						pst = c.prepareStatement(sql);
						pst.setString(1, country);
						pst.setString(2, rightsCategory);
						pst.setString(3, natlinstruname[i]);
						pst.executeUpdate();
						pst.close();
					
					} else {
						DeleteCountry.deleteCountryRightsGroupInstrumentFromTable(country, rightsCategory, natlinstruname[i],"rightsgroup_b2_national");

					}
				}
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	
	
	static public void updateB2NationalIntru(HttpServletRequest request) {

		//Initialize all variables. 
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        String rightsGroup = request.getParameter("rightsgroup");	
		int numberOfNatlIntruments = CountRows.getNumberOfRowsInTable(request,"natlinstruname");//Determine how many rows there are in the table we are submitting to the database.

		String sql[] = new String[numberOfNatlIntruments];
		PreparedStatement[] pst = new PreparedStatement[numberOfNatlIntruments];
		String natlinstruname[] = new String[numberOfNatlIntruments];

		String natlinstruconsistent[] = new String[numberOfNatlIntruments];
		String natlinstruconsistentcomm[] = new String[numberOfNatlIntruments];
		String natlinstruconsistent1951[] = new String[numberOfNatlIntruments];
		String natlinstruconsistentcomm1951[] = new String[numberOfNatlIntruments];

		
		//Nationals
		String natlinstrusupportnatls[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictnatls[] = new String[numberOfNatlIntruments];
		String natlinstrusupportnatlscom[] = new String[numberOfNatlIntruments];
		
		//IDPs
		String natlinstrusupportidps[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictidps[] = new String[numberOfNatlIntruments];
		String natlinstrusupportidpscom[] = new String[numberOfNatlIntruments];
		
		//Refugees
		String natlinstrusupportrefug[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictrefug[] = new String[numberOfNatlIntruments];
		String natlinstrusupportrefugcom[] = new String[numberOfNatlIntruments];
		
		//Asylum Seekers
		String natlinstrusupportasyl[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictasyl[] = new String[numberOfNatlIntruments];
		String natlinstrusupportasylcom[] = new String[numberOfNatlIntruments];
		
		//Returnees
		String natlinstrusupportreturn[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictreturn[] = new String[numberOfNatlIntruments];
		String natlinstrusupportreturncom[] = new String[numberOfNatlIntruments];
		
		//Stateless Persons
		String natlinstrusupportstateless[] = new String[numberOfNatlIntruments];
		String natlinstrurestrictstateless[] = new String[numberOfNatlIntruments];
		String natlinstrusupportcomstateless[] = new String[numberOfNatlIntruments];
		
		String natinstrudiscrimination[] = new String[numberOfNatlIntruments];
		String natinstrudiscriminationother[] = new String[numberOfNatlIntruments];
		
		
		String natlinstruarticles[] =  new String[numberOfNatlIntruments];
		String natlinstruarticlescomm[] =  new String[numberOfNatlIntruments];


		//Get all of the data from the request.
		for (int i=0;i< numberOfNatlIntruments;i++){
			//Overview 
			
			natlinstruname[i] = StringUtils.defaultString(request.getParameter("natlinstruname["+i+"]"),"");
			
			//Articles
			natlinstruarticles[i] = StringUtils.defaultString(request.getParameter("natlinstruarticles["+i+"]").replace("'", "\\'"),"");
			natlinstruarticlescomm[i] = StringUtils.defaultString(request.getParameter("natlinstruarticlescomm["+i+"]").replace("'", "\\'"),"");
			
			//Consistency
			natlinstruconsistent[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistent["+i+"]"),"");
			natlinstruconsistentcomm[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistentcomm["+i+"]").replace("'", "\\'"),"");
			natlinstruconsistent1951[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistent1951["+i+"]"),"");
			natlinstruconsistentcomm1951[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistentcomm1951["+i+"]").replace("'", "\\'"),"");
	
			//Nationals Support/Restrict
			natlinstrusupportnatls[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportnatls["+i+"]"),"");
			natlinstrurestrictnatls[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictnatls["+i+"]"),"");
			natlinstrusupportnatlscom[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportnatlscom["+i+"]").replace("'", "\\'"),"");

			//IDPs Support/Restrict
			natlinstrusupportidps[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportidps["+i+"]"),"");
			natlinstrurestrictidps[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictidps["+i+"]"),"");
			natlinstrusupportidpscom[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportidpscom["+i+"]").replace("'", "\\'"),"");

			//Refugees Support/Restrict
			natlinstrusupportrefug[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportrefug["+i+"]"),"");
			natlinstrurestrictrefug[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictrefug["+i+"]"),"");
			natlinstrusupportrefugcom[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportrefugcom["+i+"]").replace("'", "\\'"),"");

			//Asylum Seekers Support/Restrict
			natlinstrusupportasyl[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportasyl["+i+"]"),"");
			natlinstrurestrictasyl[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictasyl["+i+"]"),"");
			natlinstrusupportasylcom[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportasylcom["+i+"]").replace("'", "\\'"),"");			
			
			//Returnees Support/Restrict
			natlinstrusupportreturn[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportreturn["+i+"]"),"");
			natlinstrurestrictreturn[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictreturn["+i+"]"),"");
			natlinstrusupportreturncom[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportreturncom["+i+"]").replace("'", "\\'"),"");	

			//Stateless Persons Support/Restrict
			natlinstrusupportstateless[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportstateless["+i+"]"),"");
			natlinstrurestrictstateless[i] = StringUtils.defaultString(request.getParameter("natlinstrurestrictstateless["+i+"]"),"");
			natlinstrusupportcomstateless[i] = StringUtils.defaultString(request.getParameter("natlinstrusupportcomstateless["+i+"]").replace("'", "\\'"),"");
			
			//Groups the Instrument Discriminates Against
			natinstrudiscrimination[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("natinstrudiscrimination["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll("\\s+","");
			natinstrudiscriminationother[i] = StringUtils.defaultString(request.getParameter("natinstrudiscriminationother["+i+"]"),"").replace("'", "\\'");

			
		}
			
		try {
			

			for(int i = 0; i<numberOfNatlIntruments; i++) {		
					
				sql[i] = "UPDATE rightsgroup_b2_national SET "
								+ "ConsistentStandards = '"+natlinstruconsistent[i]+"',"
								+ "ConsistentStandardsComm = '"+natlinstruconsistentcomm[i]+"',"
								+ "Consistent1951 = '"+natlinstruconsistent1951[i]+"',"
								+ "Consistent1951Comm = '"+natlinstruconsistentcomm1951[i]+"',"
								+ "SupportNat = '"+natlinstrusupportnatls[i]+"',"
								+ "RestrictNat = '"+natlinstrurestrictnatls[i]+"',"
								+ "SupportRestrictNatComm = '"+natlinstrusupportnatlscom[i]+"',"
								+ "SupportIDP = '"+natlinstrusupportidps[i]+"',"
								+ "RestrictIDP = '"+natlinstrurestrictidps[i]+"',"
								+ "SupportRestrictIDPComm = '"+natlinstrusupportidpscom[i]+"',"
								+ "SupportRef = '"+natlinstrusupportrefug[i]+"',"
								+ "RestrictRef = '"+natlinstrurestrictrefug[i]+"',"
								+ "SupportRestrictRefComm = '"+natlinstrusupportrefugcom[i]+"',"
								+ "SupportAsyl = '"+natlinstrusupportasyl[i]+"',"
								+ "RestrictAsyl = '"+natlinstrurestrictasyl[i]+"',"
								+ "SupportRestrictAsylComm = '"+natlinstrusupportasylcom[i]+"',"
								+ "SupportReturn = '"+natlinstrusupportreturn[i]+"',"
								+ "RestrictReturn = '"+natlinstrurestrictreturn[i]+"',"
								+ "SupportRestrictReturnComm = '"+natlinstrusupportreturncom[i]+"',"
								+ "SupportState = '"+natlinstrusupportstateless[i]+"',"
								+ "RestrictState = '"+natlinstrurestrictstateless[i]+"',"
								+ "SupportRestrictStateComm = '"+natlinstrusupportcomstateless[i]+"',"
								+ "Discrimination = '"+natinstrudiscrimination[i]+"',"
								+ "DiscriminationOther = '"+natinstrudiscriminationother[i]+"',"
								+ "Articles = '"+natlinstruarticles[i]+"',"
								+ "ArticleComments = '"+natlinstruarticlescomm[i]+"' "
							+ "WHERE "	
								+ "CountryName = ? AND "
								+ "RightsGroup = ? AND "
								+ "InstrumentName = ?";
				

				pst[i] = c.prepareStatement(sql[i]);
				pst[i].setString(1, country);
				pst[i].setString(2, rightsGroup);
				pst[i].setString(3, natlinstruname[i]);
				pst[i].executeUpdate();
				pst[i].close();
			}

			MySql.close(c);

		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	

	
}

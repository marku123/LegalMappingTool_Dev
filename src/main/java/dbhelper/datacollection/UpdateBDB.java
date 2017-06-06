package dbhelper.datacollection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Arrays;


import javax.servlet.http.HttpServletRequest;


import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import dbhelper.dbutilities.CountRows;
import dbhelper.dbutilities.DeleteCountry;
import dbhelper.dbutilities.GenerateIDs;
import dbhelper.dbutilities.MySql;

public class UpdateBDB {

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
		String intlinstrutype[] = new String[numberOfIntlIntruments];
		String intlinstruratified[] = new String[numberOfIntlIntruments];
		String intlinstruarticles[] = new String[numberOfIntlIntruments];
		String intlinstrures[] = new String[numberOfIntlIntruments];
		String intlinstruresnature[] = new String[numberOfIntlIntruments];
		String intlinstrID[] = new String[numberOfIntlIntruments];

		//Get all of the data from the request.
		for (int i=0;i< numberOfIntlIntruments;i++){
			intlinstruname[i] = StringUtils.defaultString(request.getParameter("intlinstruname["+i+"]").replace("'", "\\'"),"");
			intlinstrutype[i] = StringUtils.defaultString(request.getParameter("intlinstrutype["+i+"]"),"");
			intlinstruratified[i] = StringUtils.defaultString(request.getParameter("intlinstruratified["+i+"]"),"");
			intlinstruarticles[i] = StringUtils.defaultString(request.getParameter("intlinstruarticles["+i+"]").replace("'", "\\'"),"");
			intlinstrures[i] = StringUtils.defaultString(request.getParameter("intlinstrures["+i+"]"),"");
			intlinstruresnature[i] = StringUtils.defaultString(request.getParameter("intlinstruresnature["+i+"]").replace("'", "\\'"),"");
			intlinstrID[i] = StringUtils.defaultString(request.getParameter("intlinstrID["+i+"]"),"");

		}
			
		
		try {
					
			for(int i = 0; i<numberOfIntlIntruments; i++) {


				//Ensure that we are not inserting an empty row.
				if (!intlinstruname[i].equals("")) {	
                
							
					//Check to see if the instrument is new and an ID needs to be created. 
					if (intlinstrID[i].equals("")) {

						//Generate new IDs from the two tables that hold info on the international instruments. 
						int id1 = GenerateIDs.generateIntlInstruIDs("rightsgroup_b1_international");
						int id2 = GenerateIDs.generateIntlInstruIDs("rightsgroup_b1_1_international");
						

						//Choose the ID that is the largest
						if (id1 > id2 ){intlinstrID[i] = Integer.toString(id1); } else {intlinstrID[i] = Integer.toString(id2);}

					}					
					
					//If the instrument is new, enter it into the DB for the Rights Group. 
					//If it already exists, update record fields for the Rights Group. 
					
					sql[i] = "INSERT INTO rightsgroup_b1_international "
							+ "(CountryName,RightsGroup,InstrumentName,InstrumentType,Ratified,"
								+ "Articles,Reservations,ReservationsNature,InstrumentID) VALUES "
								+ "(?,'"+ rightsGroup +"','"+intlinstruname[i]+"','"+intlinstrutype[i]+"','"+intlinstruratified[i]+"',"
								+ "'"+intlinstruarticles[i]+"','"+intlinstrures[i]+"',"
								+ "'" + intlinstruresnature[i] + "','"+intlinstrID[i]+"') "	
							+ "ON DUPLICATE KEY "
							+ "UPDATE "
								+ "InstrumentName = '"+intlinstruname[i]+"', "
								+ "InstrumentType = '"+intlinstrutype[i]+"', "
								+ "Ratified = '"+intlinstruratified[i]+"', "
								+ "Articles = '"+intlinstruarticles[i]+"', "
								+ "Reservations = '"+intlinstrures[i]+"', "
								+ "ReservationsNature = '"+intlinstruresnature[i]+"' "
								+ "";	

					
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
	
	static public void updateB2NationalIntru(HttpServletRequest request) {

		//Initialize all variables. 
		Connection c = MySql.connect();
		String country = request.getParameter("country");	
        String rightsGroup = request.getParameter("rightsgroup");	
		int numberOfNatlIntruments = CountRows.getNumberOfRowsInTable(request,"natlinstruname");//Determine how many rows there are in the table we are submitting to the database.

		String sql[] = new String[numberOfNatlIntruments];
		PreparedStatement[] pst = new PreparedStatement[numberOfNatlIntruments];
		String natlinstruname[] = new String[numberOfNatlIntruments];
		String natlinstrureflink[] = new String[numberOfNatlIntruments];
		String natlinstrufederal[] = new String[numberOfNatlIntruments];
		String natlinstruconsistent[] = new String[numberOfNatlIntruments];
		String natlinstruconsistentcomm[] = new String[numberOfNatlIntruments];
		String natlinstruconsistent1951[] = new String[numberOfNatlIntruments];
		String natlinstruconsistentcomm1951[] = new String[numberOfNatlIntruments];
		String natFileDisplayName[] = new String[numberOfNatlIntruments];
		String natFileStorageName[] = new String[numberOfNatlIntruments];
		String natFileURL[] = new String[numberOfNatlIntruments];

		
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
		
		String natlinstruotherlegis[] = new String[numberOfNatlIntruments];
		String natlinstruotherlegistextarea[] = new String[numberOfNatlIntruments];
		String natinstruotherlegischecked[] = new String[numberOfNatlIntruments];
		
		String natlinstruarticles[] =  new String[numberOfNatlIntruments];
		String natlinstruarticlescomm[] =  new String[numberOfNatlIntruments];

		String natlinstrucomments[] = new String[numberOfNatlIntruments];

		//Get all of the data from the request.
		for (int i=0;i< numberOfNatlIntruments;i++){
			//Overview 
			
			natlinstruname[i] = StringUtils.defaultString(request.getParameter("natlinstruname["+i+"]").replace("'", "\\'"),"");
			natlinstrureflink[i] = StringUtils.defaultString(request.getParameter("natlinstrureflink["+i+"]"),"").replace("'", "\\'");
			natlinstrufederal[i] = StringUtils.defaultString(request.getParameter("natlinstrufederal["+i+"]"),"");
			natlinstruconsistent[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistent["+i+"]"),"");
			natlinstruconsistentcomm[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistentcomm["+i+"]").replace("'", "\\'"),"");
			natlinstruconsistent1951[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistent1951["+i+"]"),"");
			natlinstruconsistentcomm1951[i] = StringUtils.defaultString(request.getParameter("natlinstruconsistentcomm1951["+i+"]").replace("'", "\\'"),"");

			natFileStorageName[i] = StringUtils.defaultString(request.getParameter("natFileStorageName["+i+"]"),"");
			natFileDisplayName[i] = StringUtils.defaultString(request.getParameter("natFileDisplayName["+i+"]"),"");
			natFileURL[i] = StringUtils.defaultString(request.getParameter("natFileURL["+i+"]").replace("\\", "\\\\"),"");

		
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

			//Other Rights Groups
			natlinstruotherlegis[i] = StringUtils.defaultString(request.getParameter("natlinstruotherlegis["+i+"]"),"");
			natlinstruotherlegistextarea[i] = StringUtils.defaultString(request.getParameter("natlinstruotherlegistextarea["+i+"]").replace("'", "\\'"),"");  
			natinstruotherlegischecked[i] = Arrays.toString(ArrayUtils.nullToEmpty(request.getParameterValues("natinstruotherlegischecked["+i+"]"))).replaceAll("(^\\[|\\]$)", "").replaceAll("\\s+","");
			
			//Articles
			natlinstruarticles[i] = StringUtils.defaultString(request.getParameter("natlinstruarticles["+i+"]").replace("'", "\\'"),"");
			natlinstruarticlescomm[i] = StringUtils.defaultString(request.getParameter("natlinstruarticlescomm["+i+"]").replace("'", "\\'"),"");
			
			//Other Comments
			natlinstrucomments[i] = StringUtils.defaultString(request.getParameter("natlinstrucomments["+i+"]").replace("'", "\\'"),"");
			
		}
			
		try {

			DeleteCountry.deleteCountryRightsGroupFromTable(country, rightsGroup, "rightsgroup_b2_national");
			
			
			for(int i = 0; i<numberOfNatlIntruments; i++) {
				
				//Ensure that we are not inserting an empty row.
				if (!natlinstruname[i].equals("")) {
					
					
					sql[i] = "INSERT INTO rightsgroup_b2_national "
							+ "(CountryName,RightsGroup,"
								+ "InstrumentName,RefWorldLink,"
								+ "FileStorageName,FileDisplayName,FileURL,"
								+ "FederalStateLocal,ConsistentStandards,ConsistentStandardsComm,Consistent1951,Consistent1951Comm,"
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
								+ "(?,'"+ rightsGroup +"',"
								+ "'"+natlinstruname[i]+"','"+natlinstrureflink[i]+"',"
								+ "'"+natFileStorageName[i]+"','"+natFileDisplayName[i]+"','"+natFileURL[i]+"',"
								+ "'"+natlinstrufederal[i]+"','"+natlinstruconsistent[i]+"','"+natlinstruconsistentcomm[i]+"','"+natlinstruconsistent1951[i]+"','"+natlinstruconsistentcomm1951[i]+"',"				
								+ "'"+natlinstrusupportnatls[i]+"','"+natlinstrurestrictnatls[i]+"','"+natlinstrusupportnatlscom[i]+"',"
								+ "'"+natlinstrusupportidps[i]+"','"+natlinstrurestrictidps[i]+"','"+natlinstrusupportidpscom[i]+"',"
								+ "'"+natlinstrusupportrefug[i]+"','"+natlinstrurestrictrefug[i]+"','"+natlinstrusupportrefugcom[i]+"',"
								+ "'"+natlinstrusupportasyl[i]+"','"+natlinstrurestrictasyl[i]+"','"+natlinstrusupportasylcom[i]+"',"
								+ "'"+natlinstrusupportreturn[i]+"','"+natlinstrurestrictreturn[i]+"','"+natlinstrusupportreturncom[i]+"',"
								+ "'"+natlinstrusupportstateless[i]+"','"+natlinstrurestrictstateless[i]+"','"+natlinstrusupportcomstateless[i]+"',"
								+ "'"+natinstrudiscrimination[i]+"','"+natinstrudiscriminationother[i]+"',"
								+ "'"+natlinstruotherlegis[i]+"','"+natlinstruotherlegistextarea[i]+"','"+natinstruotherlegischecked[i]+"',"
								+ "'"+natlinstruarticles[i]+"','"+natlinstruarticlescomm[i]+"',"
								+ "'" + natlinstrucomments[i] + "')";	
					
					
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

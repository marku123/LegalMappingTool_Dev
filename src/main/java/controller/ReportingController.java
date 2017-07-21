package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelper.analytical.QueryAnalyticsCLegalFramework;
import dbhelper.analytical.QueryAnalyticsCNatInstruments;
import dbhelper.analytical.QueryAnalyticsCObstacles;
import dbhelper.analytical.QueryNarrativeAnalysis;
import dbhelper.datacollection.QueryA1DB;
import dbhelper.datacollection.QueryA2DB;
import dbhelper.datacollection.QueryCDB;
import dbhelper.datacollection.QueryDDB;
import dbhelper.reporting.QueryBDBReporting;
import dbhelper.reporting.QueryCDCReporting;
import model.Country;

/**
 * Servlet implementation class ReportingController
 */
public class ReportingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportingController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String country = request.getParameter("country");
		String page = null;
		Country countryObj = new Country();
		
	
		countryObj.setCountryName(country);

		if (action.equals("reporting")) {

			//Section A.1
			countryObj = QueryA1DB.getLegalFrameworkConstIntro(countryObj);
			countryObj = QueryA1DB.getLegalFrameworkConstAppPOC(countryObj);

			//Section A.2
			countryObj = QueryA2DB.getLegalFrameworkSystemIntro(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkJudicialEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkAdminEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkTradMechanisms(countryObj);
			
			//Section A.3
			countryObj = QueryBDBReporting.getRightsGroupsNatIntlIntruments(countryObj);

			
			//Section B
			countryObj = QueryCDCReporting.getPOCObstacles(countryObj);
			
			//Analytics. 

			countryObj = QueryAnalyticsCNatInstruments.getNatInstruData(countryObj);
			countryObj = QueryAnalyticsCObstacles.getObstaclesData(countryObj);
			countryObj = QueryAnalyticsCLegalFramework.getLegalFrameworkData(countryObj);
			
			//Get the Narrative data from the database. 
			
			countryObj = QueryNarrativeAnalysis.getAnalyticalNarrative(countryObj);
			countryObj = QueryDDB.getNarrativePriorities(countryObj);
			countryObj = QueryCDB.getNarrativeObstacles(countryObj);
			
			
			request.setAttribute("countryObj", countryObj);
			page = "/pages/reporting/reporting.jsp";

		} 		if (action.equals("reporting_html")) {

			//Section A.1
			countryObj = QueryA1DB.getLegalFrameworkConstIntro(countryObj);
			countryObj = QueryA1DB.getLegalFrameworkConstAppPOC(countryObj);

			//Section A.2
			countryObj = QueryA2DB.getLegalFrameworkSystemIntro(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkJudicialEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkAdminEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkTradMechanisms(countryObj);
			
			//Section A.3
			countryObj = QueryBDBReporting.getRightsGroupsNatIntlIntruments(countryObj);

			
			//Section B
			countryObj = QueryCDCReporting.getPOCObstacles(countryObj);
			
			//Analytics. 

			countryObj = QueryAnalyticsCNatInstruments.getNatInstruData(countryObj);
			countryObj = QueryAnalyticsCObstacles.getObstaclesData(countryObj);
			countryObj = QueryAnalyticsCLegalFramework.getLegalFrameworkData(countryObj);
			
			//Get the Narrative data from the database. 
			
			countryObj = QueryNarrativeAnalysis.getAnalyticalNarrative(countryObj);
			countryObj = QueryDDB.getNarrativePriorities(countryObj);
			countryObj = QueryCDB.getNarrativeObstacles(countryObj);
			
			
			request.setAttribute("countryObj", countryObj);
			page = "/pages/reporting/reporting_html.jsp";

		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);

		
	}

}

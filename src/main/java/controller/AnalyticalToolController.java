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
import dbhelper.analytical.UpdateNarrativeDB;
import dbhelper.datacollection.*;
import model.Country;


/**
 * Servlet implementation class AnalyticalToolCollector
 */
public class AnalyticalToolController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnalyticalToolController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String country = request.getParameter("country");
		String savedata = request.getParameter("savedata");
		String page = null;
		Country countryObj = new Country();

		countryObj.setCountryName(country);

		if (action.equals("analytical")) {

			countryObj = QueryA1DB.getLegalFrameworkConstIntro(countryObj);
			request.setAttribute("countryObj", countryObj);
			page = "/pages/analytical/analytical.jsp";

		} else if (action.equals("analytics")) {

		
			countryObj = QueryAnalyticsCNatInstruments.getNatInstruData(countryObj);
			countryObj = QueryAnalyticsCObstacles.getObstaclesData(countryObj);
			countryObj = QueryAnalyticsCLegalFramework.getLegalFrameworkData(countryObj);


			request.setAttribute("countryObj", countryObj);
			page = "/pages/analytical/analytics/analytics.jsp";

		} else if (action.equals("narrative")) {
			if (savedata != null) {
				//Insert narrative data into the database.

				UpdateNarrativeDB.updateNarrative(request);				
				UpdateNarrativeDB.updatePrioritiesNarrative(request);
				UpdateCDB.updateObstaclesNarrative(request);
			}
			
			//Get the Analytics data from the database. 

			countryObj = QueryAnalyticsCNatInstruments.getNatInstruData(countryObj);
			countryObj = QueryAnalyticsCObstacles.getObstaclesData(countryObj);
			countryObj = QueryAnalyticsCLegalFramework.getLegalFrameworkData(countryObj);
			
			//Get the Narrative data from the database. 
			
			countryObj = QueryNarrativeAnalysis.getAnalyticalNarrative(countryObj);
			countryObj = QueryDDB.getNarrativePriorities(countryObj);
			countryObj = QueryCDB.getNarrativeObstacles(countryObj);

			request.setAttribute("countryObj", countryObj);
			page = "/pages/analytical/narrative/narrative1.jsp";
		} 

		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}

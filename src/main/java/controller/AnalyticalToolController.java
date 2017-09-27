package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelper.analytical.QueryAnalyticsCLegalFramework;
import dbhelper.analytical.QueryAnalyticsCNatInstruments;
import dbhelper.analytical.QueryAnalyticsCObstacles;
import dbhelper.datacollection.QueryA2DB;
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
		String page = null;
		Country countryObj = new Country();

		countryObj.setCountryName(country);

		if (action.equals("analytics")) {

		
			countryObj = QueryAnalyticsCNatInstruments.getNatInstruData(countryObj);
			countryObj = QueryAnalyticsCObstacles.getObstaclesData(countryObj);
			countryObj = QueryAnalyticsCLegalFramework.getLegalFrameworkData(countryObj);
			//countryObj = QueryA2DB.getCountryPOCs(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkSystemIntro(countryObj);


			request.setAttribute("countryObj", countryObj);
			page = "/pages/analytical/analytics/analytics.jsp";

		} 

		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}

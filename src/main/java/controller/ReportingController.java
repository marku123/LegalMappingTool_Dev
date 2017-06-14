package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelper.datacollection.QueryA1DB;
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

			
			countryObj = QueryA1DB.getLegalFrameworkConstIntro(countryObj);
			countryObj = QueryA1DB.getLegalFrameworkConstAppPOC(countryObj);

			request.setAttribute("countryObj", countryObj);
			page = "/pages/reporting/reporting.jsp";

		}
		
		getServletContext().getRequestDispatcher(page).forward(request, response);

		
	}

}

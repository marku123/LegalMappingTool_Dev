package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import model.Country;
import dbhelper.datacollection.*;

/**
 * Servlet implementation class DataCollectionController
 */
//@javax.servlet.annotation.MultipartConfig 
public class DataCollectionController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DataCollectionController() {
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


		
		if (action.equals("datacollection")) {
	
			if (savedata != null) {
				UpdateA1DB.updateCountryLegalFrameworkConstIntro(request);
				UpdateA1DB.updateLegalFrameworkConstAppPOC(request);


			} 
			countryObj = QueryA1DB.getLegalFrameworkConstIntro(countryObj);
			countryObj = QueryA1DB.getLegalFrameworkConstAppPOC(countryObj);

			
			request.setAttribute("countryObj", countryObj);
			page = "/pages/datacollection/datacollection.jsp";
		
		} else if (action.equals("datacollection_a")) {
	
			if (savedata != null) {

				UpdateA2DB.updateCountryA2Intro(request);
				UpdateA2DB.updateA2JudicialEntities(request);
				UpdateA2DB.updateA2AdminEntities(request);
				UpdateA2DB.updateA2TradMechanisms(request);

			} 

			countryObj = QueryA2DB.getLegalFrameworkSystemIntro(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkJudicialEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkAdminEntities(countryObj);
			countryObj = QueryA2DB.getLegalFrameworkTradMechanisms(countryObj);
			
			request.setAttribute("countryObj", countryObj);
			page = "/pages/datacollection/datacollection_a.jsp";
			
		} else if (action.equals("datacollection2")) {
			String rightsgroup = StringUtils.defaultString(request.getParameter("rightsgroup"),"");
			countryObj.setRightsGroup(rightsgroup);                        
			
			if (savedata != null) {
			
				UpdateBDB.updateB1InternationalIntru(request);				
				UpdateBDB.updateB2NationalIntru(request);
				
			} 

			countryObj = QueryBDB.getRightsGroupsIntlIntruments(countryObj);
			countryObj = QueryBDB.getRightsGroupsNatlIntruments(countryObj);

			request.setAttribute("countryObj", countryObj);
			page = "/pages/datacollection/datacollection2.jsp";
		
		} else if (action.equals("datacollection3")) {
			String personofconcern = StringUtils.defaultString(request.getParameter("personofconcern"),"");
			countryObj.setPOC(personofconcern);
						
			if (savedata != null) {
				UpdateCDB.updateCObstacles(request);
				UpdateCDB.updateCObstacleDocumentation(request);
			} 
			
			countryObj = QueryCDB.getPOCObstacles(countryObj);
			countryObj = QueryCDB.getPOCObstacleDocumentation(countryObj);
			
			request.setAttribute("countryObj", countryObj);
			page = "/pages/datacollection/datacollection3.jsp";
		
		} else {
			
			//Remove this section when I have added the other data collection pages.
			request.setAttribute("countryObj", countryObj);
			page = "/pages/datacollection/datacollection.jsp";
		}
		

		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

}

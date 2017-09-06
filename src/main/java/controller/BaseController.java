package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Country;
import dbhelper.country.CreateCountry;

/**
 * Servlet implementation class BaseController
 */
public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseController() {
		super();
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

		String page = null;
		String action = request.getParameter("action");
		String country = request.getParameter("country");
		Country countryObj = new Country();

	
		
		if (action.equals("home")) {
			page = "/index.jsp";
		} else if (action.equals("admin")) {
			page = "/admin.jsp";
		} else if (action.equals("countryhome")) {
			
			
			if (country.equals("")) {
				page = "/index.jsp";
			} else {
				countryObj = CreateCountry.createCountry(country);
				request.setAttribute("countryObj", countryObj);
				page = "/pages/countryhome.jsp";

			}
		}  else {
			page = "/index.jsp";
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}
}

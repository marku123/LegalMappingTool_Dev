package access;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Country;

public class Authentication {

	public static Boolean AuthenticateUserNoCountry(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Boolean authenticated = (Boolean) session.getAttribute("authenticateduser");

		if (authenticated == null || !authenticated) {
			response.sendRedirect("Login");
		}
		//System.out.println("Authenticated:" + authenticated + "\n\n");

		return authenticated;

	}

	public static Boolean AuthenticateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Country countryObj = (Country) request.getAttribute("countryObj");
		String countryName = countryObj.getCountryName();
		HttpSession session = request.getSession();
		Boolean authenticated = (Boolean) session.getAttribute("authenticateduser");
		Boolean editPermission = (Boolean) session.getAttribute("editor");
		String countryToEdit = (String) session.getAttribute("country");
		Boolean finalEditPermission = false;

		if (authenticated == null || !authenticated) {
			finalEditPermission = false;
			response.sendRedirect("Login");
		} else if (editPermission && (countryToEdit.equals(countryName) || countryToEdit.equals("All"))) {

			finalEditPermission = true;
		}

		/*System.out.println("Authenticated:" + authenticated);
		System.out.println("Edit Persmission:" + editPermission);
		System.out.println("Country that can be edited: " + countryToEdit);
		System.out.println("finalEditPermission: " + finalEditPermission + "\n\n");*/


		return finalEditPermission;

	}

}

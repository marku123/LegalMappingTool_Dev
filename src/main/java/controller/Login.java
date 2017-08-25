package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelper.authentication.QueryAuthentication;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		String user = "";
		String pass = "";
		String loginbutton = "";
		String[] authenticationData = new String[3];
		Boolean loginattempted;
		Boolean authenticated;
		String page = null;
		HttpSession session = request.getSession();

		user = request.getParameter("user");
		pass = request.getParameter("pass");
		loginbutton = request.getParameter("loginbutton");
		authenticated = (Boolean) session.getAttribute("authenticateduser");

		if (loginbutton != null) {
			loginattempted = true;
			request.setAttribute("loginattempted", loginattempted);
		}

		if (authenticated == null || !authenticated) {

			if ((user == null || pass == null)) {
				session.invalidate();
				page = "/login.jsp";
			} else if (user.isEmpty() || pass.isEmpty()) {
				session.invalidate();
				page = "/login.jsp";
			} else if (!user.isEmpty() && !pass.isEmpty()) {
				
				authenticationData = QueryAuthentication.authenticateLogin(user, pass);
				Boolean authenticatedFromDB = Boolean.valueOf(authenticationData[0]);
				Boolean editorFromDB = Boolean.valueOf(authenticationData[1]);
				String countryFromDB = authenticationData[2];
				
				if(authenticatedFromDB){
					session.setAttribute("authenticateduser", authenticatedFromDB);
					session.setAttribute("editor", editorFromDB);
					session.setAttribute("country", countryFromDB);
					page = "/index.jsp";
				} else {
					page = "/login.jsp";
				}
				
			} else {
				session.invalidate();
				page = "/login.jsp";
			}

		} else if (authenticated) {

			page = "/index.jsp";

		}

		getServletContext().getRequestDispatcher(page).forward(request, response);

	}

}

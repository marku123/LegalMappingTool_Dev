package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			} else if (user.equals("costa_rica") && pass.equals("CRLEg@17%%Ed")) {
				session.setAttribute("authenticateduser", true);
				session.setAttribute("editor", true);
				session.setAttribute("country", "Costa Rica");
				page = "/index.jsp";
			} else if (user.equals("gen_user_noed") && pass.equals("LEg@17%%N0Ed")) {
				session.setAttribute("authenticateduser", true);
				session.setAttribute("editor", false);
				session.setAttribute("country", "None");
				page = "/index.jsp";
			} else if (user.equals("gen_user_ed") && pass.equals("LEg@17%%Ed")) {
				session.setAttribute("authenticateduser", true);
				session.setAttribute("editor", true);
				session.setAttribute("country", "All");
				page = "/index.jsp";
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

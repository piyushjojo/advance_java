package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pojos.User;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		// get PW
		try (PrintWriter pw = response.getWriter()) {
			// get http session from WC
			HttpSession session = request.getSession();
			// retrieve user details
			User user = (User) session.getAttribute("clnt_details");
			if (user != null) {
				pw.print("<h5> Hello , "+user.getName()+"</h5>");
				pw.print("<h5> You have logged out...</h5>");
				
			} else
				pw.print("<h5> No Cookies !!!!!!!!!!!!!! NO session Tracking!!!!!!!!!!!!</h5>");
			//invalidate Http Session
			session.invalidate();// To discard either newly created or existing session object
			//link
			pw.print("<h5> <a href='login.html'>Visit Again</a></h5>");
			
		}

	}

}

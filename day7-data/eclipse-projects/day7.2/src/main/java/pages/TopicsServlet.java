package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TopicsServlet
 */
@WebServlet("/topics")
public class TopicsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("in do-get of "+getClass());
		// set cont type
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
			pw.print("<h5>In topics page </h5>");
			// retrieve user info from the req scoped attr
			// API of HttpServletRequest : getAttribute
			pw.print("<h5> Clnt details from req scope "+request.getAttribute("user_details"));
			pw.print("<h5> Clnt's email "+request.getParameter("em")+"</h5>");
		

	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-post of "+getClass());
		doGet(req, resp);
	}
	
	

}

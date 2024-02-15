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
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>In topics page </h5>");
			// retrive user info from a cookie
			// API of HttpServletRequest
			// Cookie[] getCookies()
			Cookie[] cookies = request.getCookies();
			if (cookies != null)// => cookies present
			{
				// iterate over the cookies
				for (Cookie c : cookies)
					if (c.getName().equals("auth_user_dtls"))
						// print validated user details ....
						pw.print("<h5>Your Details  :" + c.getValue() + "</h5>");
			} else
				pw.print("<h5> No Cookies !!!!!!!!!!!!!! NO session Tracking!!!!!!!!!!!!</h5>");

		}

	}

}

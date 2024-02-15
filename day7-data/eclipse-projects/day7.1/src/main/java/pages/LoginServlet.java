package pages;

import static utils.DBUtils.closeConnection;
import static utils.DBUtils.openConnection;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImpl;
import pojos.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;

	/**
	 * @see Servlet#init()
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("in init of " + getClass());
		// open connection
		try {
			openConnection();
			// create dao instance
			userDao = new UserDaoImpl();
		} catch (Exception e) {
			// centralized err handling in servlets !
			throw new ServletException("err in init of " + getClass(), e);
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	@Override
	public void destroy() {
		try {
			// clean up dao/s
			userDao.cleanUp();
			// close cn
			closeConnection();
		} catch (Exception e) {
			// how to convey the err to the WC ?
			throw new RuntimeException("err in destroy of " + getClass(), e);

		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set content type
		response.setContentType("text/html");
		// get PW
		try (PrintWriter pw = response.getWriter()) {
			// read request params(body) sent from the client
			// API of ServletRequest : public String getParameter(String paramName)
			String email = request.getParameter("em");
			String password = request.getParameter("pass");
			// invoke dao 's method for user authentication
			User authenticatedUser = userDao.authenticateUser(email, password);
			if (authenticatedUser == null) // => invalid login
				pw.print("<h5> Invalid Login , Please <a href='login.html'>Retry</a></h5>");
			else // => login success
			{
				pw.print("<h5> Login Successful from login servlet </h5>");// will not appear on the clnt side
			//	 pw.flush();// un comment this line to chk IllegalStateExc on line no : 91 / 94
				// store clnt details : under request scope --for server Pull (Req dispatching
				// tech : forward)
				request.setAttribute("user_details", authenticatedUser);
				if (authenticatedUser.getRole().equals("CUSTOMER")) {
					// navigate the customer to topics page API :
					// API of HttpServletReq :
					// 1. create Req Dispatcher object : public RD getRequestDispatcher(String path)
					RequestDispatcher rd = request.getRequestDispatcher("topics");
					// 2. forward the clnt to the next resource , in the SAME request
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("admin");
					rd.forward(request, response);
				}
				System.out.println("back in login servlet");
				// WC : CLEARS / DISCARDS pw's buffer ---suspends this exec -- invokes
				// TopicServlet's doPost --> after it rets
				// control comes back n resp is sent to the clnt

			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}
	}

}

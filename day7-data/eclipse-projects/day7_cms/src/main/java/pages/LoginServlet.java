package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import dao.TutorialDaoImpl;
import dao.UserDaoImpl;
import pojos.User;

import static utils.DBUtils.*;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private TopicDaoImpl topicDao;
	private TutorialDaoImpl tutDao;

	/**
	 * @see Servlet#init()
	 */
	@Override
	public void init() throws ServletException {
		System.out.println("in init of " + getClass());
		// open connection
		try {
			openConnection();
			// create user dao instance
			userDao = new UserDaoImpl();
			//create topic dao instance
			topicDao=new TopicDaoImpl();
			//create tut dao instance
			tutDao=new TutorialDaoImpl();
			
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
			topicDao.cleanUp();
			tutDao.cleanUp();
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
		
				// In case of successful login , proceed to role based authorization (chk
				// permissions)
				//1 . get HttpSession from WC
				//API of HttpServletRequest : public HttpSession getSession()
				HttpSession session=request.getSession();
				System.out.println("Login servlet  : is session new "+session.isNew());//true
				System.out.println("session id "+session.getId());
				//2. store auth user details under session scope
				//API of HttpSession : public void setAttribute(String attrName, Object attrVal) 
				session.setAttribute("clnt_details", authenticatedUser);
				//store dao instances under session scope
				session.setAttribute("user_dao", userDao);
				session.setAttribute("topic_dao", topicDao);
				session.setAttribute("tut_dao",tutDao);
					if (authenticatedUser.getRole().equals("CUSTOMER"))
					// navigate the customer to topics page API :
					// API of HttpServletResp : public void sendRedirect(String redirectLoc) throws
					// IOExc
					response.sendRedirect("topics");
				else
					// navigate the admin to admin page
					response.sendRedirect("admin");
				//WC : CLEARS / DISCARDS pw's buffer ---send temp redirect resp , SC 302 | location : topics 
				//Set-Cookie : JSESSIONID : fgshfg6748667gjfg | body : EMPTY

			}

		} catch (Exception e) {
			throw new ServletException("err in do-post " + getClass(), e);
		}
	}

}

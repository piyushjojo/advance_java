package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.VoterDaoImpl;
import pojos.Voter;

import static utils.DBUtils.*;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet(value = "/validate", loadOnStartup = 1)
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VoterDaoImpl voterDao;
	private CandidateDaoImpl candidateDao;

	/**
	 * @see Servlet#init()
	 */
	public void init() throws ServletException {
		// open cn n create daos
		try {
			openConnection();
			voterDao = new VoterDaoImpl();
			candidateDao = new CandidateDaoImpl();
		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}

	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		try {
			// clean up daos
			voterDao.cleanUp();
			candidateDao.cleanUp();
			// close db connection
			closeConnection();
		} catch (Exception e) {
			throw new RuntimeException("err in destro of " + getClass(), e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set resp cont type : for clnt browser
		response.setContentType("text/html");
		// pw : chat buffered o/p strm connected from servlet ---> clnt
		try (PrintWriter pw = response.getWriter()) {
			// 1. read req params sent from clnt
			String userName = request.getParameter("name");
			String pwd = request.getParameter("pass");
			//2. get HttpSession from WC : NEW session
			HttpSession session=request.getSession();
			// invoke dao's method
			Voter user = voterDao.authenticateUser(userName, pwd);
			//save validated user details under session scope
			session.setAttribute("user_dtls", user);
			//store daos under session scope : for sharing with other dyn web pages from the SAME web app
			session.setAttribute("voter_dao",voterDao);
			session.setAttribute("candidate_dao",candidateDao);			
			//=> authentication done --proceed to role based authorization
			if(user.getRole().equals("admin"))
				response.sendRedirect("admin");
			else { //=> voter role , chk voting status
				if(user.isStatus()) //=> alrdy voted
					response.sendRedirect("status");
				else //voter : not yet voted
					response.sendRedirect("candidate_list");			
			}

		} catch (Exception e) {
			throw new ServletException("err in init of " + getClass(), e);
		}
	}

}

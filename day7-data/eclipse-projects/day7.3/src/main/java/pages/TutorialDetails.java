package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TutorialDaoImpl;
import pojos.Tutorial;

/**
 * Servlet implementation class TutorialDetails
 */
@WebServlet("/tut_details")
public class TutorialDetails extends HttpServlet {
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
			// get tut name from req param
			String tutName = request.getParameter("tut_name");
			// get session
			HttpSession session = request.getSession();
			TutorialDaoImpl tutDao = (TutorialDaoImpl) session.getAttribute("tut_dao");
			if (tutDao != null) {
				// update visits
				System.out.println(tutDao.updateVisits(tutName));
				// get updated tut details
				Tutorial tutorial = tutDao.getTutorialDetailsByName(tutName);
				pw.print("<h4> Tutotrial Details "+tutorial+"</h4>");
				//links
				pw.print("<h5> <a href='logout'>Log Me Out</a></h5>");
				pw.print("<h5> <a href='tutorials?tid="+tutorial.getTopicId()+"'>Back</a></h5>");

			} else
				pw.print("<h5> No Cookies !!!!!!!!!!!!!! NO session Tracking!!!!!!!!!!!!</h5>");
		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}
	}

}

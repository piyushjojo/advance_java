package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TutorialDaoImpl;

/**
 * Servlet implementation class TutorialsServlet
 */
@WebServlet("/tutorials")
public class TutorialsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// get selected topic id from req param
			int topicId = Integer.parseInt(request.getParameter("tid"));
			// get HttpSession from WC
			HttpSession session = request.getSession();
			// get tut dao from session scope
			TutorialDaoImpl tutDao = (TutorialDaoImpl) session.getAttribute("tut_dao");
			if (tutDao != null) {
				//invoke dao's method to get list of tut names
				List<String> tutNames=tutDao.getTutorialNamesByTopicId(topicId);
				for(String s : tutNames)
					pw.print("<h5><a href='tut_details?tut_name="+s+"'>"+s+"</a></h5>");

			} else
				pw.print("<h5> No Cookies !!!!!!!!!!!!!! NO session Tracking!!!!!!!!!!!!</h5>");
		} catch (Exception e) {
			throw new ServletException("err in do-get of "+getClass(), e);
		}
	}

}

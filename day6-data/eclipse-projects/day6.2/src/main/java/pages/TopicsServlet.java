package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.TopicDaoImpl;
import pojos.Topic;
import pojos.User;

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
		// set cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			pw.print("<h5>In topics page </h5>");
			// retrive user info from a HttpSession
			// API of HttpServletRequest to get HttpSession
			// public HttpSession getSession()
			HttpSession hs = request.getSession();
			System.out.println("Topic servlet  : is session new " + hs.isNew());// false provided cookies are accepted ,
																				// o.w true
			System.out.println("session id " + hs.getId());// same session id provided cookies are accepted , o.w
															// different

			// get user details from session scope
			User validatedUser = (User) hs.getAttribute("clnt_details");
			if (validatedUser != null)// => success !
			{
				// get topic dao from sesison scope
				TopicDaoImpl topicDao = (TopicDaoImpl) hs.getAttribute("topic_dao");
				// print validated user details ....
				pw.print("<h5>Your Details  :" + validatedUser + "</h5>");
				// get topic list from topic dao
				List<Topic> topics = topicDao.getAllTopics();
				// dyn form generation
				pw.print("<form action='tutorials'>");
				for (Topic t : topics)
					pw.print("<h5> <input type='radio' name='tid' value='" + t.getTopicId() + "'/>" + t.getTopicName()
							+ "</h5>");
				//submit btn
				pw.print("<h5> <input type='submit' value='Choose A Topic'/></h5>");
				pw.print("</form>");

			} else
				pw.print("<h5> No Cookies !!!!!!!!!!!!!! NO session Tracking!!!!!!!!!!!!</h5>");

		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}

	}

}

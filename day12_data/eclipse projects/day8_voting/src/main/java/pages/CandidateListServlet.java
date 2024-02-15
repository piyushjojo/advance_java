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

import dao.CandidateDaoImpl;
import pojos.Candidate;
import pojos.Voter;

/**
 * Servlet implementation class CandidateListServlet
 */
@WebServlet("/candidate_list")
public class CandidateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set resp cont type
		response.setContentType("text/html");
		try (PrintWriter pw = response.getWriter()) {
			// 1. get session
			HttpSession session = request.getSession();
			// 2. get candidate dao from the session scope (inner map : map of session
			// scoped attrs : ${sessionScope}
			CandidateDaoImpl dao = (CandidateDaoImpl) session.getAttribute("candidate_dao");
			// get voter details from HttpSession
			Voter voter = (Voter) session.getAttribute("user_dtls");
			pw.print("<h5> Hello , " + voter.getName() + "</h5>");
			// 3. get all candiate details from dao --> servlet
			List<Candidate> candidates = dao.getAllCandidates();
			// dyn form generation
			pw.print("<form action='status'>");
			for (Candidate c : candidates)
				pw.print("<h5><input type='radio' name='candiate_id' value='" + c.getCandidateId() + "'/>" + c.getName()
						+ " " + c.getPoliticalParty() + "</h5>");
			pw.print("<h5><input type='submit' value='Vote'/></h5>");			
			pw.print("</form>");
		} catch (Exception e) {
			throw new ServletException("err in do-get of " + getClass(), e);
		}
	}

}

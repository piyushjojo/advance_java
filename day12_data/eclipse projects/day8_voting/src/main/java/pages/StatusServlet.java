package pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CandidateDaoImpl;
import dao.VoterDaoImpl;
import pojos.Voter;

/**
 * Servlet implementation class StatusServlet
 */
@WebServlet("/status")
public class StatusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		try(PrintWriter pw =response.getWriter())
		{
			//1. get session
			HttpSession session=request.getSession();
			//get voter details from session scope
			Voter voter=(Voter)session.getAttribute("user_dtls");
			pw.print("<h5> Hello , " + voter.getName() + "</h5>");
			if(voter.isStatus()) //=> alrdy voted
				pw.print("<h5>You had already voted earlier , Can't vote again!!!!!!!!!!!!!!!!!!!!!</h5>");
			else {
				//get chosen candidate id from clnt
				int candidateId=Integer.parseInt(request.getParameter("candiate_id"));
				//voter has just now cast a vote
				//incr votes n voting status 
				VoterDaoImpl voterDaoImpl=(VoterDaoImpl) session.getAttribute("voter_dao");
				CandidateDaoImpl candidateDao=(CandidateDaoImpl)session.getAttribute("candidate_dao");
				pw.print("<h5>"+voterDaoImpl.updateVotingStatus(voter.getId())+"</h5>");
				System.out.println(candidateDao.incrementCandidateVotes(candidateId));				
			}
			//invalidate session
			session.invalidate();
			pw.print("<h5>You have logged out.....</h5>");
			
		} catch (Exception e) {
			throw new ServletException("err in do-get of "+getClass(), e);
		}

	}

}

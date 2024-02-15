package pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//@WebServlet(urlPatterns={"/hello","/hi"},loadOnStartup = 2)
@WebServlet(value={"/hello","/hi"},loadOnStartup = 2)
//Class level annotation , meant for WC , to be processed @ dep time , exactly once .
//Purpose : To create a mapping between URL pattern n servlet
//WC creates an EMPTY map @ app dep time
//Key : /hello (URL pattern)
//value : pages.WelcomeServlet (F.Q servlet cls name)
public class WelcomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("in do-get of "+getClass()+" invoked by "+Thread.currentThread());
		//set resp content type
		//Method of HttpServletResponse (inherited from ServletResp)
		//public void setContentType(String respCntType)
		resp.setContentType("text/html");
		//open PW : to send char , buffered resp to the clnt
		try(PrintWriter pw=resp.getWriter())
		{
			//generate dyn resp : pw.print /write
			pw.print("<h4> Welcome 2 Servlets , at Server time stamp "+LocalDateTime.now()+"</h4>");
		}//pw.close ---resp body will be flushed --resp sent to clnt
		
	}

	@Override
	public void destroy() {
		System.out.println("in destroy of "+getClass()+" invoked by "+Thread.currentThread());
	}

	@Override
	public void init() throws ServletException {
		System.out.println("in init of "+getClass()+" invoked by "+Thread.currentThread());
	}
	//life cycle methods --init ,doGet, destroy
	

}

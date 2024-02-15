<%@page import="pojos.User"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	HashMap<String, User> users;

	public void jspInit() {
		System.out.println("in jsp init");
		users = new HashMap<>();
		users.put("Rama", new User("Rama", "12345", 34));
		users.put("Nikhil", new User("Nikhil", "1345", 32));
	}%>
<body>
	<%
	String name = request.getParameter("nm");
	String pwd = request.getParameter("pass");
	User user = users.get(name);
	if (user != null) {
		if (user.getPassword().equals(pwd)) {
			//login success -- store user dtls under session scope(since redirect)
			session.setAttribute("user_dtls", user);
			//redirect the clnt to --> details.jsp in the NEXT req.
			//to enable URL rewriting : API of HttpServletResponse
			//public String encodeRedirectURL(String redirectLoc) 
			response.sendRedirect(response.encodeRedirectURL("details.jsp"));
		} else {
	%>
	<h5 style="color: red;">
		Invalid Password , Please <a href="login.jsp">Retry</a>
	</h5>
	<%
	}
	} else {
	%>
	<h5 style="color: red;">
		New User , Please <a href="register.jsp">Register</a> First
	</h5>
	<%
	}
	%>
</body>
</html>
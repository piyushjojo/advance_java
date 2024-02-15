<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4 align="center">
		Session ID :
		<%=session.getId()%></h4>
	<%--display email n pwd --%>
	<h5>User details via scriptlets</h5>
	<h5>
		Hello ,
		<%
	//JSP scriptlet : TO BE AVOIDED!!!!!!!!!!!!!!!!!!!
	out.print(request.getParameter("em"));
	%>
	</h5>
	<h5>
		Your Password :
		<%
	out.print(request.getParameter("pass"));
		//create page scoped attribute 
		pageContext.setAttribute("server_date", new Date());
		
	%>
	</h5>
	<hr />
	<h5>User details via JSP expression</h5>
	<h5>
		Hello ,
		<%=request.getParameter("em")%>
	</h5>
	<h5>
		Password :
		<%=request.getParameter("pass")%>
	</h5>
	<h5>
		Request :
		<%=request%></h5>
	<h5>
		page :
		<%=page%></h5>
	<h5>
		pageContext :
		<%=pageContext%></h5>
	<h5>param : ${param}</h5>
	<hr />
	<h5>User details via JSP EL Syntax</h5>
	<h5>Email : ${param.em}</h5>
	<h5>Password : ${param.pass}</h5>
	<%--How to display the value of page scoped attribute ? --%>
	<h5>Page Scoped attribute : ${pageScope.server_date}</h5>
	


</body>
</html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="user" class="beans.UserBean" scope="session"/>
<body>
	<h5>
		<a href="accept.jsp">Display User n Address Details</a>
	</h5>
</body>
</html>
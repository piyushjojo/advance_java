<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>mesg
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!String mesg = "some mesg!!!";%>
<body>
	<%
	int counter = 100;
	pageContext.setAttribute("nm", 1234);//auto boxing--->up casting
	%>
	<%@ include file="test3.jsp"%>
</body>
</html>
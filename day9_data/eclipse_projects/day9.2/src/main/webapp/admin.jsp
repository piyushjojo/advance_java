<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>${sessionScope.user.message}</h5>
<%--session.getAttribute("user").getValidatedUserDetails().getName() : sent automatically to client --%>
<h5>Hello , ${sessionScope.user.validatedUserDetails.name}</h5>
<h5><a href="add_tutorial.jsp">Add new tutorial</a></h5>
<h5><a href="logout.jsp">Logout</a></h5>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--conditional rendering  --%>
	<c:choose>
		<c:when test="${sessionScope.tut.message eq null}">
			<h5>${sessionScope.user.message}</h5>
		</c:when>
		<c:otherwise>
			<h5>${sessionScope.tut.message}</h5>
		</c:otherwise>
	</c:choose>
	<%--session.getAttribute("user").getValidatedUserDetails().getName() : sent to clnt --%>
	<h5>Hello , ${sessionScope.user.validatedUserDetails.name}</h5>
	<h5>
		<a href="add_tutorial.jsp">Add New Tutorial</a>
	</h5>
	<h5>
		<a href="logout.jsp">Log Out</a>
	</h5>
</body>
</html>
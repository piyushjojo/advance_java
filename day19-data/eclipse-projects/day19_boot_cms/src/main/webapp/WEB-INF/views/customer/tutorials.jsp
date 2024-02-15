<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Tutorials under Topic Id</h5>
	<h5></h5>
	<table style="background-color: lightgrey; margin: auto">
		<caption>Tutorial List for Topic with ID :  ${param.topicId}</caption>
		<c:forEach var="tutName" items="${requestScope.tut_names}">
		<tr>
			<td><a href="<spring:url value='/customer/tut_details?tut_name=${tutName}'/>">${tutName}</a></td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Tutorial Details : ${requestScope.tutorial_details}</h5>
	<h5>Authored By :
		${requestScope.tutorial_details.author.firstName}
		${requestScope.tutorial_details.author.lastName}</h5>
	<c:set var="topic_id" value="${requestScope.tutorial_details.topic.id}" />
	<h5>
		<a
			href="<spring:url value='/customer/tutorials?topicId=${topic_id}'/>">Back</a>
	</h5>
	<h5>

		<a href="<spring:url value='/user/logout'/>">Log Out</a>
	</h5>
</body>
</html>
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
	<h4>${requestScope.mesg}</h4>
	<h5>Valid User Details : ${sessionScope.user_dtls}</h5>
	<h5>In topics page : under role : ${sessionScope.user_dtls.roles}</h5>
	<spring:url var="url" value="/customer/tutorials"/>
	<form action="${url}">
		<table style="background-color: lightgrey; margin: auto">
		<caption>Topic List</caption>
			<tr>
				<td>Choose a Topic</td>
				<td><select name="topicId">
						<c:forEach var="topic" items="${requestScope.topic_list}">
							<option value="${topic.id}">${topic.topicName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Choose Topic" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
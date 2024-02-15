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
<h5 style="color: red;">${sessionScope.tut.message}</h5>
	<form action="process_form.jsp" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<caption>Add New Tutorial</caption>
			<tr>
				<td>Choose a Topic</td>
				<td><select name="topicId">
						<c:forEach var="t" items="${sessionScope.topic.allTopics}">
							<option value="${t.topicId}">${t.topicName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Enter Tutorial Name</td>
				<td><input type="text" name="name" required /></td>
			</tr>
			<tr>
				<td>Enter Author Name</td>
				<td><input type="text" name="author" /></td>
			</tr>

			<tr>
				<td>Enter Publish Date</td>
				<td><input type="date" name="pubDate" /></td>
			</tr>
			<tr>
				<td>Enter Contents</td>
				<td><textarea rows="10" cols="40" name="contents"></textarea>
			</tr>

			<tr>
				<td><input type="submit" value="Add New Tutorial" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
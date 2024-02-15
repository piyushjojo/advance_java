<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>${pageContext.session.id}</h5>
	<%--out.print(session.getAttribute("user_dtls").getName()) --%>
	<h5>Hello , ${sessionScope.user_dtls.name}</h5>
	<%-- invalidate session --%>
	<%-- <%
	session.invalidate();
	%> --%>
	${pageContext.session.invalidate()}
	<h5>
		<a href="index.jsp">Visit Again</a>
	</h5>

</body>
</html>
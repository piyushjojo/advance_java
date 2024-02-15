<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>From 1st page....</h5>
	<%
//	out.flush();
	//req scoped attr for server pull
	request.setAttribute("product_dtls", request.getParameter("product_id") + "@" + request.getParameter("price"));
	%>
	<%--forward the clnt to test5.jsp in the SAME request --%>
	<jsp:forward page="test5.jsp">
		<jsp:param value="oil" name="category" />
	</jsp:forward>
	<h5>Contents after forward....</h5>
</body>
</html>
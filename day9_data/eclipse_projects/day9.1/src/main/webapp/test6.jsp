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
	out.flush();
	pageContext.setAttribute("test", 1234);
	session.setAttribute("test2", 3455);
	application.setAttribute("test1", 3455);
	//req scoped attr for server pull
	request.setAttribute("product_dtls", request.getParameter("product_id") + "@" + request.getParameter("price"));
	%>
	<%--include at run time contents of  test7.jsp in the SAME request --%>
	<jsp:include page="test7.jsp">
		<jsp:param value="oil" name="category" />
	</jsp:include>
	<h5>Contents after include....</h5>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>From 2nd page</h5>
	<h5>Product details : ${requestScope.product_dtls}</h5>
	<h5>Page scoped attr ${pageScope.test}</h5>
	<h5>session scoped attr ${sessionScope.test2}</h5>
	<h5>app scoped attr ${applicationScope.test1}</h5>
	<h5>Category : ${param.category}</h5>
	<h5>param : ${param}</h5>
</body>
</html>
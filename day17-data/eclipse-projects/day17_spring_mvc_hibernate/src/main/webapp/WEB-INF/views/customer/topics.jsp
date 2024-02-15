<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>${requestScope.mesg}</h4>
<h5> Valid User Details : ${requestScope.user_dtls}</h5>
<h5>In topics page : under role : ${requestScope.user_dtls.roles}</h5>
</body>
</html>
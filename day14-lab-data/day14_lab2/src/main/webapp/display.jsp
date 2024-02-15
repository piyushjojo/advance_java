<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:setProperty property="*" name="user"/>
<body>
<h5>User Details : ${sessionScope.user.completeDetails.owner}</h5>
<h5>Address Details : ${sessionScope.user.completeDetails}</h5>
</body>
</html>
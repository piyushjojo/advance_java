<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="product" class="beans.ProductBean" scope="application"/>
<jsp:setProperty property="*" name="product"/>
<body>
<h5> Status : ${applicationScope.product.insertProductDetails()}</h5>
</body>
</html>
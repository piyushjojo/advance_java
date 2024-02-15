<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%-- import jstl supples core tag lib --%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--setters  --%>
<jsp:setProperty property="*" name="user" />
<%--session.getAttribute("user").validateUser() --%>
<body>
<%-- for server pull .. reoccurance of events in case of refresh --- RequestDispatcher rd = request.getRD(session.getAttr("user").validateUser().concat(".jsp"); rd.forward(rq,rs) --%>
	<%-- changed code see in ma'am ka code in day10.1 validate.jsp --%>
	<%-- <jsp:pageContext page="${sessionScope.user.validateUser()}.jsp" /> --%>
	<%--for this file : -> response.sendRedirect(response.encodeRedirectURL(session.getAttribute("user").validateUser().concat(".jsp")) --%>
	<c:redirect url="${sessionScope.user.validateUser()}.jsp" />
	
</body>
</html>
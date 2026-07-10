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
	<h1>Model 데이터 전송 테스트</h1>
	<p>userId = ${member.userId}</p>
	<p>password = ${member.password}</p>
	<%-- <c:if test="${not empty member}">
	    <p>userId = ${member.userId}</p>
	    <p>email = ${member.email}</p>
	    <p>password = ${member.password}</p>
    	<hr>
	</c:if>
	<c:forEach var="member" items="${members}">
		<p>userId = <c:out value="${member.userId}"></c:out></p>
	    <p>email = <c:out value="${member.email}"></c:out></p>
	    <p>password = <c:out value="${member.password}"></c:out></p>
		<hr>
	</c:forEach> --%>
	 
</body>
</html>
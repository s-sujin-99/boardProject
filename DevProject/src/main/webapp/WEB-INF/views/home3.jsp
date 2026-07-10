<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Locale"%>
<%
/* Script를 사용하면 자바코드를 사용할 수 있음. */
Date date = new Date();
String serverTime2 = date.toString();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home3</title>
</head>
<body>
	<!-- 	헤더 내용을 가져온다. -->
	<%@ include file="./includes/header.jsp"%>
	<p>서버에서 보내준 데이터를 출력 ${serverTime2}</p>
	<p>서버에서 보내준 데이터를 출력 <%=serverTime2%></p>
	<!-- 푸터 내용을 가져온다. -->
	<%@ include file="./includes/footer.jsp"%>

</body>
</html>
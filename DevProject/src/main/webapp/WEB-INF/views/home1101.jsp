<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home1101</title>
</head>
<body>
    <h1>home1101</h1>
    <p>서버에서 보내준 데이터를 출력 ${serverTime}</p>
    <p>서버에서 보내준 데이터를 출력 :<c:out value="${serverTime}" /></p>
</body>
</html>

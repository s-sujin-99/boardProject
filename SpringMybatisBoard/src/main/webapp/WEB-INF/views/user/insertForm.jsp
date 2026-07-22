<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Board - 회원 가입</title>
<link rel="stylesheet" href="https://rsms.me/inter/inter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertForm2.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

	<header class="nav-header">
		<div class="nav-container">
			<a href="${pageContext.request.contextPath}/user/list" class="nav-logo">Instagram</a>
			<div class="nav-menu">
				<a href="${pageContext.request.contextPath}/user/list">목록</a>
				<a href="${pageContext.request.contextPath}/user/insertForm">등록</a>
			</div>
		</div>
	</header>

	<div class="container">
		<div class="logo-title">Instagram</div>
		<p class="sub-title">새로운 계정을 만들고 친구들과 소통하세요.</p>
		
		<form:form modelAttribute="member" action="${pageContext.request.contextPath}/user/insert" method="post" class="register-form">
			
			<div class="input-group">
				<form:input path="userId" class="input-field" placeholder="아이디" />
				<span class="error-message"><form:errors path="userId" /></span>
			</div>
			
			<div class="input-group">
				<form:password path="userPw" class="input-field" placeholder="비밀번호" />
				<span class="error-message"><form:errors path="userPw" /></span>
			</div>
			
			<div class="input-group">
				<form:input path="userName" class="input-field" placeholder="성명" />
				<span class="error-message"><form:errors path="userName" /></span>
			</div>
			
			<button type="submit" class="btn-submit">가입하기</button>
		</form:form>
	</div>
	
	<div class="footer-box">
		계정이 있으신가요? <a href="${pageContext.request.contextPath}/user/list">로그인</a>
	</div>

</body>
</html>
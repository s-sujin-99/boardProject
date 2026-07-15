<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Instagram - 프로필 편집</title>
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
		<div class="logo-title">Edit Profile</div>
		<p class="sub-title">프로필 정보를 최신으로 업데이트하세요.</p>

		<form action="${pageContext.request.contextPath}/user/update" method="post" class="register-form">
			
			<input type="hidden" name="userNo" value="${member.userNo}" />
			
			<div class="input-group">
				<label style="font-size: 11px; font-weight: 600; color: #8e8e8e; display: block; margin-bottom: 4px;">회원 번호</label>
				<input type="text" value="${member.userNo}" class="input-field" disabled="disabled" style="background-color: #f2f2f2; color: #8e8e8e; cursor: not-allowed;" />
			</div>

			<div class="input-group">
				<label style="font-size: 11px; font-weight: 600; color: #8e8e8e; display: block; margin-bottom: 4px;">아이디</label>
				<input type="text" name="userId" value="${member.userId}" class="input-field" placeholder="변경할 아이디 입력" required="required" />
			</div>

			<div class="input-group">
				<label style="font-size: 11px; font-weight: 600; color: #8e8e8e; display: block; margin-bottom: 4px;">이름</label>
				<input type="text" name="userName" value="${member.userName}" class="input-field" placeholder="변경할 이름 입력" required="required" />
			</div>

			<div id="authHiddenContainer">
				<c:forEach items="${member.authList}" var="auth" varStatus="status">
					<input type="hidden" class="hidden-auth" value="${auth.auth}" />
				</c:forEach>
			</div>

			<button type="submit" class="btn-submit" style="margin-top: 20px;">변경사항 저장</button>
			
			<%-- ★ 수정 완료: /user/user/select 방지를 위해 중복 경로 수정 --%>
			<a href="${pageContext.request.contextPath}/user/select?userNo=${member.userNo}" class="btn-secondary" style="text-align: center; text-decoration: none; display: block; margin-top: 8px; line-height: 35px; height: 35px;">취소</a>
		</form>
	</div>

	<script>
	$(document).ready(function() {
		$(".register-form").on("submit", function() {
			$(".hidden-auth").each(function(index, element) {
				$(element).attr("name", "authList[" + index + "].auth");
			});
		});
	});
	</script>

</body>
</html>
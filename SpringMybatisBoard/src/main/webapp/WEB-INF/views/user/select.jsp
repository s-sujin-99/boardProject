<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram - 프로필 상세</title>
<link rel="stylesheet" href="https://rsms.me/inter/inter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertForm2.css">
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

	<div class="wide-container" style="max-width: 500px;">
		<div style="display: flex; align-items: center; border-bottom: 1px solid #dbdbdb; padding-bottom: 25px; margin-bottom: 20px;">
			<div class="profile-circle" style="width: 70px; height: 70px; line-height: 70px; font-size: 24px;">
				${member.userName.substring(0,1)}
			</div>
			<div style="margin-left: 20px;">
				<h2 style="font-size: 22px; font-weight: 300; display: inline-block; margin-right: 15px;">${member.userId}</h2>
				<p style="font-weight: 600; font-size: 14px; color: #8e8e8e; margin-top: 5px;">성명: ${member.userName}</p>
			</div>
		</div>

		<table class="insta-table" style="margin-bottom: 25px;">
			<tr>
				<td style="width: 120px; font-weight: 600; color: #8e8e8e; border:none;">회원 번호</td>
				<td style="border:none;">${member.userNo}</td>
			</tr>
			<tr>
				<td style="font-weight: 600; color: #8e8e8e; border:none;">보유 권한</td>
				<td style="border:none;">
					<c:forEach items="${member.authList}" var="auth" varStatus="status">
						<span style="background: #fafafa; border: 1px solid #dbdbdb; padding: 4px 8px; border-radius: 4px; font-size: 12px; margin-right: 5px;">
							${auth.auth}
						</span>
					</c:forEach>
				</td>
			</tr>
		</table>

		<div style="display: flex; gap: 8px;">
			<a href="${pageContext.request.contextPath}/user/updateForm?userNo=${member.userNo}" class="btn-submit" style="text-align: center; text-decoration: none; margin-top: 0;">프로필 편집</a>
			<a href="${pageContext.request.contextPath}/user/deleteMember?userNo=${member.userNo}" class="btn-secondary btn-danger" style="margin-top: 0;">회원 탈퇴</a>
		</div>
		<a href="${pageContext.request.contextPath}/user/list" class="btn-secondary">목록으로</a>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram - 회원 목록</title>
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

	<div class="wide-container">
		<h2 style="font-size: 20px; font-weight: 700; margin-bottom: 20px;">회원 목록</h2>
		
		<table class="insta-table">
			<thead>
				<tr>
					<th style="width: 50px;">번호</th>
					<th>아이디</th>
					<th>이름</th>
					<th>가입일</th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${empty memberList}">
						<tr>
							<td colspan="4" style="text-align: center; color: #8e8e8e; padding: 40px 0;">등록된 회원이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach items="${memberList}" var="m">
							<tr onclick="location.href='${pageContext.request.contextPath}/user/select?userNo=${m.userNo}'" style="cursor: pointer;">
								<td>${m.userNo}</td>
								<td style="font-weight: 600; display: flex; align-items: center;">
									<span class="profile-circle">${m.userName.substring(0,1)}</span>
									${m.userId}
								</td>
								<td>${m.userName}</td>
								<td style="color: #8e8e8e; font-size: 13px;">
									<fmt:formatDate value="${m.regDate}" pattern="yyyy-MM-dd"/>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>

</body>
</html>
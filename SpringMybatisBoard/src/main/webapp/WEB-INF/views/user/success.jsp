<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram - 완료</title>
<link rel="stylesheet" href="https://rsms.me/inter/inter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertForm2.css">
</head>
<body>

	<div class="container" style="margin-top: 10vh; text-align: center;">
		<div style="width: 70px; height: 70px; background-color: #e8f5e9; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin-bottom: 20px;">
			<span style="font-size: 32px; color: #2e7d32;">✓</span>
		</div>
		<h2 style="font-size: 22px; font-weight: 700; margin-bottom: 10px; color: #262626;">요청 완료</h2>
		<p style="font-size: 14px; color: #8e8e8e; margin-bottom: 30px; line-height: 1.5;">작업이 성공적으로 처리되었습니다.</p>

		<a href="${pageContext.request.contextPath}/user/list" class="btn-submit" style="text-decoration: none; display: block; text-align: center;">목록으로 이동</a>
	</div>

</body>
</html>
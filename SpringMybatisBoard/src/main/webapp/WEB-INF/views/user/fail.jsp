<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Instagram - 오류</title>
<link rel="stylesheet" href="https://rsms.me/inter/inter.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/insertForm2.css">
</head>
<body>

	<div class="container" style="margin-top: 10vh; text-align: center; border-color: #ffd2d2;">
		<div style="width: 70px; height: 70px; background-color: #ffebee; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin-bottom: 20px;">
			<span style="font-size: 32px; color: #c62828;">!</span>
		</div>
		<h2 style="font-size: 22px; font-weight: 700; margin-bottom: 10px; color: #c62828;">처리 실패</h2>
		<p style="font-size: 14px; color: #8e8e8e; margin-bottom: 30px; line-height: 1.5;">일시적인 오류가 발생했습니다.<br>다시 시도해 주세요.</p>

		<a href="javascript:history.back();" class="btn-submit" style="text-decoration: none; display: block; text-align: center; background-color: #8e8e8e;">이전 페이지로</a>
	</div>

</body>
</html>
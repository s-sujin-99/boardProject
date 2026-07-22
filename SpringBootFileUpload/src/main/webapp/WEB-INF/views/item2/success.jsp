<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Notion - 완료</title>
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/successForm.css">
</head>

<body>
   <div class="notion-success-container">

        <div class="notion-success-icon-area">
            <span class="success-emoji">✨</span>
        </div>

        <h1 class="notion-success-main-title">성공적으로 처리되었습니다</h1>
        <p class="notion-success-sub-title">새로운 페이지가 워크스페이스 데이터베이스에 안전하게 동기화되었습니다.</p>

        <c:if test="${not empty item}">
            <div class="notion-preview-block">
                <div class="preview-item">
                    <span class="preview-label">👤 이름</span>
                    <span class="preview-value">${item.itemName}</span>
                </div>
                <div class="preview-item">
                    <span class="preview-label">📄 파일명</span>
                    <span class="preview-value-badge">${item.pictureUrl}</span>
                </div>
            </div>
        </c:if>

        <div class="notion-action-links">
            <a href="/item2/list" class="notion-link-btn primary">📁 전체 목록 보기</a>
            <c:if var="hasNo" test="${item.itemId > 0}">
                <a href="/item2/select?itemId=${item.itemId}" class="notion-link-btn">생성된 페이지 확인</a>
            </c:if>
        </div>

   </div>
</body>
</html>
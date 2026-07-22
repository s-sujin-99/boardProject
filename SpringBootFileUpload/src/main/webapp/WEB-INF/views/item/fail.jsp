<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Board - Error</title>
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/failForm.css">
</head>

<body>
   <div class="notion-result-wrapper">

        <div class="notion-callout-block error">
            <span class="notion-callout-icon">
                <c:choose>
                    <c:when test="${mode eq 'delete'}">🗑️</c:when>
                    <c:when test="${mode eq 'select'}">🔍</c:when>
                    <c:otherwise>⚠️</c:otherwise>
                </c:choose>
            </span>
            
            <div class="notion-callout-content">
                <div class="notion-error-title">
                    <c:choose>
                        <c:when test="${mode eq 'update'}">수정에 실패하였습니다.</c:when>
                        <c:when test="${mode eq 'delete'}">삭제에 실패하였습니다.</c:when>
                        <c:when test="${mode eq 'select'}"> 불러오는 데 실패하였습니다.</c:when>
                        <c:otherwise>게시글 등록에 실패하였습니다.</c:otherwise>
                    </c:choose>
                </div>
                <div class="notion-error-desc">
                    <c:choose>
                        <c:when test="${mode eq 'select'}">존재하지 않거나 이미 삭제된 글번호일 수 있습니다.</c:when>
                        <c:otherwise>입력하신 내용을 다시 확인하거나 잠시 후 다시 시도해 주세요.</c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="notion-btn-group">
            <c:choose>
                <%-- 단순 조회나 삭제 실패는 뒤로 가기보다 목록으로 유도하는 게 안전합니다 --%>
                <c:when test="${mode eq 'select' || mode eq 'delete'}">
                    <a href="/board/list" class="notion-back-link primary">📁 목록으로 이동하기</a>
                </c:when>
                <%-- 입력/수정 실패는 사용자가 쓰던 입력 데이터 유지를 위해 history.back()을 실행합니다 --%>
                <c:otherwise>
                    <a href="javascript:history.back();" class="notion-back-link primary">
                        <c:choose>
                            <c:when test="${mode eq 'update'}">✍️ 다시 수정하기</c:when>
                            <c:otherwise>✍️ 다시 작성하기</c:otherwise>
                        </c:choose>
                    </a>
                    <a href="/item/list" class="notion-back-link">📁 목록으로 돌아가기</a>
                </c:otherwise>
            </c:choose>
        </div>

    </div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
    <title>Board</title>
    
    <link rel="stylesheet" href="https://rsms.me/inter/inter.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/listForm.css">

	<style>
        .search-container { margin-bottom: 20px; display: flex; justify-content: flex-end; }
        .search-form { display: flex; gap: 8px; }
        .search-select { padding: 6px 12px; border: 1px solid #e2e8f0; border-radius: 6px; outline: none; }
        .search-input { padding: 6px 12px; border: 1px solid #e2e8f0; border-radius: 6px; width: 200px; outline: none; }
        .search-btn { padding: 6px 16px; background-color: #f1f5f9; border: 1px solid #cbd5e1; border-radius: 6px; cursor: pointer; font-weight: 500; }
        .search-btn:hover { background-color: #e2e8f0; }
    </style>
</head>

<body>
   <div class="notion-list-wrapper">

        <div class="notion-header">
            <div class="notion-title-area">
                <span class="notion-title-icon">🚀</span>
                <h1 class="notion-title">자유 게시판</h1>
            </div>
            <a href="/board/insertForm" class="notion-write-btn">✍️ 새 글 작성</a>
        </div>
        
        <div class="search-container">
            <form action="/board/search" method="post" class="search-form">
                <select name="searchType" class="search-select">
                    <option value="title" ${param.searchType == 'title' ? 'selected' : ''}>제목</option>
                    <option value="writer" ${param.searchType == 'writer' ? 'selected' : ''}>작성자</option>
                </select>
                <input type="text" name="title" value="${param.title}" placeholder="검색어를 입력하세요..." class="search-input">
                <button type="submit" class="search-btn">검색</button>
            </form>
        </div>

        <div class="notion-table-container">
            <table class="notion-table">
                <thead>
                    <tr>
                        <th class="col-no">번호</th>
                        <th class="col-title">제목</th>
                        <th class="col-writer">작성자</th>
                        <th class="col-date">작성일</th> </tr>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${empty list}">
                        <tr>
                            <td colspan="3" class="notion-empty-row">
                                등록된 게시글이 없습니다.
                            </td>
                        </tr>
                    </c:if>
                    
                    <c:forEach var="boardDTO" items="${list}">
                        <tr onclick="location.href='/board/select?boardNo=${boardDTO.boardNo}'">
                            <td class="col-no">${boardDTO.boardNo}</td>
                            <td class="col-title">
                                <span class="title-text">${boardDTO.title}</span>
                            </td>
                            <td class="col-writer">
                                <span class="writer-badge">${boardDTO.writer}</span>
                            </td>
                      		<td class="col-date">
                                <span class="date-text">
                                    <fmt:formatDate value="${boardDTO.regDate}" pattern="yyyy-MM-dd"/>
                                </span>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </div>
</body>
</html>
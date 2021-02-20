<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board content</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<td>글번호</td>
			<td>${boardDTO.num}</td>
			<td>조회수</td>
			<td>${boardDTO.readcount}</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${boardDTO.writer}</td>
			<td>작성일</td>
			<td>${boardDTO.regdate}</td>
		</tr>
		<tr>
			<td>글제목</td>
			<td colspan="3">${boardDTO.title}</td>
		</tr>
		<tr>
			<td>글내용</td>
			<td colspan="3">${content}</td>
		</tr>
		<tr>
			<td>IP</td>
			<td colspan="3">${boardDTO.ip}</td>
		</tr>
	</table>
	<div align="center">
		<a href="${ctxpath}/board/list.do?pageNum=${pageNum}">목록</a>&nbsp;&nbsp;
		<a href="${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step}&re_level=${re_level}">답글</a>&nbsp;&nbsp;
		<c:if test="${sessionScope.memId eq writer or sessionScope.memId eq 'admin'}">
			<a href="${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}">수정</a>&nbsp;&nbsp;
			<a href="${ctxpath}/board/delete.do?num=${num}&pageNum=${pageNum}" onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>&nbsp;&nbsp;
		</c:if>
	</div>
</body>
</html>
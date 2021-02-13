<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board content</title>
<link href="style.css" rel="stylesheet" type="text/css">
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
		</tr>
	</table>
	<div align="center">
		<a href="${ctxpath}/board/updateForm.do?num=${num}&pageNum=${pageNum}">수정</a>&nbsp;
		<a href="${ctxpath}/board/deleteForm.do?num=${num}&pageNum=${pageNum}">삭제</a>&nbsp;
		<a href="${ctxpath}/board/writeForm.do?num=${num}&pageNum=${pageNum}&ref=${ref}&re_step=${re_step}&re_level=${re_level}">답글</a>&nbsp;
		<a href="${ctxpath}/board/list.do?pageNum=${pageNum}">목록</a>
	</div>
</body>
</html>
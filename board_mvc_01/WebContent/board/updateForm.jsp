<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>write</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="${ctxpath}/board/list.do">목록</a>
			</td>
		</tr>
	</table>
	<form name="writeform" method="post" action="${ctxpath}/board/updatePro.do?pageNum=${pageNum}" onsubmit="return writeCheck()">
		<input type="hidden" name="num" value="${num}">
		<table border="1">
		<%-- 
			<tr>
				<td>작성자</td>
				<td>${writer}<input type="hidden" name="writer" value="${nick}"></td>
			</tr>
			--%>
			<tr>
				<td>글제목</td>
				<td><input type="text" name="title" id="title" size="60" value="${boardDTO.title}"></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td><textarea name="content" id="content" rows="20" cols="90">${boardDTO.content}</textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="checkbox" name="rank" id="check" value="1">비공개
					<input type="hidden" name="rank" id="non_check" value="0">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>writeForm</title>
<link href="style.css" rel="stylesheet" type="text/css">
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
	<form name="writeform" method="post" action="${ctxpath}/board/writePro.do">
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="re_step" value="${re_step}">
		<input type="hidden" name="re_level" value="${re_level}">
		<table border="1">
		<%-- 
			<tr>
				<td>작성자</td>
				<td>${writer}<input type="hidden" name="writer" value="${nick}"></td>
			</tr>
			--%>
			<tr>
				<td>글제목</td>
				<td><input type="text" name="title" id="title" size="60"></td>
			</tr>
			<tr>
				<td>글내용</td>
				<td><textarea name="content" id="content" rows="20" cols="90"></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="radio" name="rank" id="rank" value="0" checked>공개
					<input type="radio" name="rank" id="rank" value="1">비공개
					<c:if test="${num!=0 and dto.rank==0}">
						<input type="radio" name="rank" id="rank" value="0" checked>공개
						<input type="radio" name="rank" id="rank" value="1">비공개
					</c:if>
					<c:if test="${num!=0 and dto.rank==1}">
						<input type="radio" name="rank" id="rank" value="0">공개
						<input type="radio" name="rank" id="rank" value="1" checked>비공개
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<%-- 원글 --%>
				<c:if test="${num==0}">
					<input type="submit" value="글쓰기">
				</c:if>
				<%-- 답글 --%>
				<c:if test="${num!=0}">
					<input type="submit" value="답글쓰기">
				</c:if>
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>notice writeForm</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<c:if test="${empty sessionScope.memId}">
		<table>
			<tr>
				<td><b>로그인 후 글을 작성해 주세요.</b></td>
			</tr>
			<tr>
				<td>
					<input type="button" value="로그인" onclick="location='${ctxpath}/member/loginForm.do'">
					<input type="button" value="회원가입" onclick="location='${ctxpath}/member/inputForm.do'">
				</td>
			</tr>
		</table>
	</c:if>
	<c:if test="${sessionScope.memId eq 'admin'}">
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>write</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="${ctxpath}/notice/noticeList.do">목록</a>
			</td>
		</tr>
	</table>
	<form name="writeform" method="post" action="${ctxpath}/notice/noticeWritePro.do" onsubmit="return writeCheck()">
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
	</c:if>
</body>
</html>
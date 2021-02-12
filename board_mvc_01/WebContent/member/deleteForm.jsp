<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>delete form</title>
<script src="script.js" type="text/javascript"></script>
</head>
<body onload="begin()">
	<form name="deleteform" method="post" action="${ctxpath}/member/deletePro.do" onsubmit="return deleteCheck()">
		<table style="margin:auto;">
			<tr>
				<td>ID</td>
				<td>${id}</td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="회원탈퇴">
					<input type="button" value="취소" onclick="history.back()">
					<input type="hidden" name="id" value="${sessionScope.memId}">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
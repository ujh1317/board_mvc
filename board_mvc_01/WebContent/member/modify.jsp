<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table style="margin:auto;">
		<tr>
			<td>
				<form name="modifyform" method="post" action="${ctxpath}/member/modifyForm.do">
					<input type="hidden" name="id" value="${sessionScope.memId}">
					<input type="submit" value="회원정보수정">
				</form>
			</td>
			<td>
				<form name="deleteform" method="post" action="${ctxpath}/member/deleteForm.do">
					<input type="hidden" name="id" value="${sessionScope.memId}">
					<input type="submit" value="회원탈퇴">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>
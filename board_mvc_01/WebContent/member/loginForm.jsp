<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>input form</title>
<script type="text/javascript" src="script.js"></script>
</head>
<body>
	<form name="loginform" method="post" action="${ctxpath}/member/loginPro.do">
		<table style="margin:auto;" cellpadding="10">
			<tr>
				<td colspan="2" align="center"><font size="6">로그인</font></td>
			</tr>
			<tr>
				<td>ID</td>
				<td><input type="text" name="id" id="id"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" style="width:100%;" value="로그인">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
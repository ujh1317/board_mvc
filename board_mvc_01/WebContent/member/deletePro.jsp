<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check==1}">
	<c:remove var="memId" scope="session"/>
	<meta http-equiv="Refresh" content="3;url=${ctxpath}/member/main.do">
	<table width="300" cellpadding="5">
		<tr height="40">
			<td align="center"><font size="+1"><b>회원정보가 삭제되었습니다.</b></font></td>
		</tr>
	</table>
	</c:if>
	
	<c:if test="${check==-1}">
		<script>
			alert("암호가 일치하지 않습니다.");
			history.back();
		</script>
	</c:if>
</body>
</html>
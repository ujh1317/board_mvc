<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<style>
table{
	width: auto;
	margin: auto;
	min-width:700;
	min-height:200;
}
.left{min-width:100;}
.content{padding:50; min-height:100;}
</style>
<body>
	<table border="1">
		<tr height="20">
			<td><a href="${ctxpath}/member/main.do">홈</a></td>
			<td colspan="2" align="right"><jsp:include page="/module/top.jsp" flush="false"/></td>
		</tr>
		<tr>
			<td class="left" width="15%" valign="top"><jsp:include page="/module/left.jsp" flush="false"/></td>
			<td class="content" width="80%" valign="middle"><jsp:include page="${CONTENT}" flush="false"/></td>
		</tr>
		<tr height="10">
			<td colspan="2" align="center"><jsp:include page="/module/bottom.jsp" flush="false"/></td>
		</tr>
	</table>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>noticeDelete</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
		<c:if test="${check==1}">
		<meta http-equiv="Refresh" content="0;url=${ctxpath}/notice/noticeList.do?pageNum=${pageNum}">
		</c:if>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>

<c:if test="${check==1}">
	<meta http-equiv="Refresh" content="0;url=${ctxpath}/board/list.do?pageNum=${pageNum}">
</c:if>
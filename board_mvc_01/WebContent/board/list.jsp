<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>boardList</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>게시판</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="${ctxpath}/board/writeForm.do">글쓰기</a>
			</td>
		</tr>
	</table>
	
	<c:if test="${count==0}">
	<table>
		<tr>
			<td align="center">게시글이 없습니다.</td>
		</tr>
	</table>
	</c:if>
	<c:if test="${count>0}">
	<table>
		<tr>
			<td>글번호</td>
			<td>글제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
		</tr>
		<c:forEach var="dto" items="${boardList}">
			<tr>
				<td>
					<c:out value="${number}"/>
					<c:set var="number" value="${number+1}"/>
				</td>
			</tr>
			<tr>
				<td>
					<c:if test="${boardDTO.re_level>0}">
						<img src="../imgs/level.gif" width="${5*boardDTO.re_level}" height="16"/>
						<img src="../imgs/re.gif"/>
					</c:if>
					<c:if test="${dto.re_level==0}">
						<img src="../imgs/level.gif" width="${5*boardDTO.re_level}" height="16"/>
					</c:if>
					<a href="${ctxpath}/board/content.do?num=${boardDTO.num}&pageNum=${pageNum}">${boardDTO.title}</a>
					<c:if test="${boardDTO.readcount>20}">
						<img src="../imgs/hot.gif" border="0" height="10"/>
					</c:if>
				</td>
				<td>
					<a href="mailto:hong@naver.com">${boardDTO.writer}</a>
				</td>
				<td>${boardDTO.regdate}</td>
				<td>${boardDTO.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%-- 페이지처리 --%>
	<c:if test="${count>0}">
		<table>
			<tr>
				<td>
					<c:if test="${endPage>pageCount}">
						<c:set var="endPage" value="${pageCount}"/>
					</c:if>
					<c:if test="${startPage>10}">
						<a href="${ctxpath}/board/list.do?pageNum=${startPage-10}">이전</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="${ctxpath}/board/list.do?pageNum=${i}">${i}</a>
					</c:forEach>
					<c:if test="${endPage<pageCount}">
						<a href="${ctxpath}/board/list.do?pageNum=${startPage+10}">다음</a>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>
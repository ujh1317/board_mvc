<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/module/header.jsp" %>
<fmt:requestEncoding value="utf-8"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>board list</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>게시판</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<c:if test="${!empty sessionScope.memId}">
					<a href="${ctxpath}/board/writeForm.do">글쓰기</a>
				</c:if>
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
	<table border="1">
		<tr>
			<td align="center" width="50">글번호</td>
			<td align="center">글제목</td>
			<td align="center" width="80">작성자</td>
			<td align="center" width="200">등록일</td>
			<td align="center" width="50">조회수</td>
		</tr>
		<c:forEach var="dto" items="${boardList}">
			<tr>
				<td align="center">
					<c:out value="${number}"/>
					<c:set var="number" value="${number-1}"/>
				</td>
				<td>
					<c:if test="${dto.re_level>0}">
						<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
						<img src="../imgs/re.gif"/>
					</c:if>
					<c:if test="${dto.re_level==0}">
						<img src="../imgs/level.gif" width="${5*dto.re_level}" height="16"/>
					</c:if>
					<c:if test="${dto.rank==1}">
						<img src="../imgs/bounds.jpg" height="16">
						<c:if test="${sessionScope.memId eq dto.writer or sessionScope.memId eq 'admin'}">
							<a href="${ctxpath}/board/content.do?num=${dto.num}&pageNum=${pageNum}">${dto.title}</a>
						</c:if>
					</c:if>
					<c:if test="${dto.rank==0}">
						<c:if test="${empty sessionScope.memId}">
							${dto.title}
						</c:if>
						<c:if test="${!empty sessionScope.memId}">
							<a href="${ctxpath}/board/content.do?num=${dto.num}&pageNum=${pageNum}">${dto.title}</a>
						</c:if>
					</c:if>
					<c:if test="${dto.readcount>20}">
						<img src="../imgs/hot.gif" border="0" height="10"/>
					</c:if>
				</td>
				<td align="center">
					<c:if test="${empty sessionScope.memId}">
						${dto.writer}
					</c:if>
					<c:if test="${!empty sessionScope.memId}">
						<a href="mailto:hong@naver.com">${dto.writer}</a>
					</c:if>
				</td>
				<td>${dto.regdate}</td>
				<td align="right">${dto.readcount}</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	<%-- 페이지처리 --%>
	<c:if test="${count>0}">
		<table>
			<tr>
				<td align="center">
					<c:if test="${endPage>pageCount}">
						<c:set var="endPage" value="${pageCount}"/>
					</c:if>
					<c:if test="${startPage>10}">
						<a href="${ctxpath}/board/list.do?pageNum=${startPage-10}">[이전]</a>
					</c:if>
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<a href="${ctxpath}/board/list.do?pageNum=${i}">[${i}]</a>
					</c:forEach>
					<c:if test="${endPage<pageCount}">
						<a href="${ctxpath}/board/list.do?pageNum=${startPage+10}">[다음]</a>
					</c:if>
				</td>
			</tr>
		</table>
	</c:if>
</body>
</html>
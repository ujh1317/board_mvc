<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/module/header.jsp" %>
<%@ page import="member.MemberDTO" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>modify form</title>
<link href="style.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script type="text/javascript" src="script.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
</head>
<body>
	<table>
		<tr>
			<td colspan="2" align="center"><font size="7"><b>회원정보수정</b></font></td>
		</tr>
		<tr>
			<td align="right">
				<a href="#" onclick="history.back()">이전</a>
			</td>
		</tr>
	</table>
	<form name="inputform" method="post" action="${ctxpath}/member/modifyPro.do" onsubmit="return inputCheck()">
		<table>
			<tr>
				<td nowrap width="10">사용자ID</td>
				<td>${memberDTO.id}<input type="hidden" name="id" id="id" value="${memberDTO.id}"></td>
			</tr>
			<tr>
				<td>암호</td>
				<td><input type="password" name="pw" id="pw" size="30"></td>
			</tr>
			<tr>
				<td>암호확인</td>
				<td><input type="password" name="pw2" id="pw2" size="30"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${memberDTO.name}<input type="hidden" name="name" id="name" value="${memberDTO.name}"></td>
			</tr>
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="nick" id="nick" value="${memberDTO.nick}"></td>
			</tr>
			<tr>
				<td>주민번호</td>
				<td>
					${memberDTO.jumin1}<input type="hidden" name="jumin1" id="jumin1">
					- ${memberDTO.jumin2}<input type="hidden" name="jumin2" id="jumin2">
				</td>
			</tr>
			<tr>
				<td>email</td>
				<td><input type="email" name="email" id="email" size="30" value="${memberDTO.email}"></td>
			</tr>
			<tr>
				<td>우편번호</td>
				<td>
					<input type="text" name="zipcode" id="zipcode" size="6" value="${memberDTO.zipcode}">
					<input type="button" value="검색" onclick="openDaumPostcode()">
				</td>
			</tr>
			<tr>
				<td>주소</td>
				<td>
					<input type="text" name="addr" id="addr" size="70" value="${memberDTO.addr}" readonly><br>
					상세주소 : <input type="text" name="addr2" id="addr2" size="60" value="${addr2}">
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정">
					<input type="reset" value="취소">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@page import="com.hottae.dao.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content="charset=UTF-8">
		<meta name="viewport" content="width=device-width, inintial-scale=1">
		<!--  bootstrap file -->
		<link rel="stylesheet" href="/BBS/css/bootstrap.css">
		<link rel="stylesheet" href="/BBS/css/custom.css">

		<!--  google j-query file -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/bbs/js/bootstrap.js"></script>
		<title>Index</title>
	</head>
	<body>
		<%
			String key = session.getAttribute("key").toString();
			String file = session.getAttribute("file").toString();
		%>
		<form action="testaction.jsp" method="get">
			<img src="/BBS/captcha/<%= file %>.jpg"><br>
			<input type="text" name="user_Value"><br>
			<input type="submit" value="확인하기">
		</form>
	</body>
</html>
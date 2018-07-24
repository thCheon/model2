<%@page import="naverapi.NaverCaptcha"%>
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
		NaverCaptcha a = new NaverCaptcha();
		String key = a.CaptchaKey();
		session.setAttribute("key", key);
		String filename = a.CaptchaImage(key);
		session.setAttribute("file", filename);
	%>
	key : <%= key %>
	<input type="button" onclick="location.href='test2.jsp'" value="확인하기">
	</body>
</html>
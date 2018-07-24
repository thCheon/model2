<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
	<link rel="shortcut icon" href="/BBS/img/favicon.ico" type="image/x-icon">
	<link rel="icon" href="/BBS/img/favicon.ico" type="image/x-icon">
	
	<meta content="charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<!-- google jquery file -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="/BBS/js/bootstrap.js"></script>
	<script src="/BBS/js/custom.js"></script>
	<!-- bootstrap file -->
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
	<link rel="stylesheet" href="/BBS/css/bootstrap.css">
	<link rel="stylesheet" href="/BBS/css/custom.css">
	
	<title>TestPage</title>
</head>
<section class="sticky-top">
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
	<a class="navbar-brand" href="/BBS/main.jsp">JSP Web</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#collapsibleNavbar">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="collapsibleNavbar">
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/BBS/BoardServlet?cmd=main">메인</a></li>
			<li class="nav-item"><a class="nav-link" href="/BBS/BoardServlet?cmd=boardList">게시판</a></li>
		</ul>   		
		<div style="margin: 0 auto;">
			<form action="BoardServlet?cmd=boardSearch" method="post">
				<select name="option" class="btn search-panel">
					<option value="all">전체검색</option>
					<option value="memid">작성자</option>
					<option value="bdtitle">제목</option>
					<option value="bdcontent">내용</option>
					<option value="bdtit_con">제목+내용</option>
				</SELECT> 
				<input class="form-control-sm"type="text" style="width:240px;" name="word" placeholder="게시물 전용 검색창 입니다."> 
				<input class="btn btn-danger" type="submit" value="검색">
			</form>
		</div>
		<br>
		<c:if test="${memID == null }">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">접속하기</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/BBS/BoardServlet?cmd=login">로그인</a>
					<a class="dropdown-item" href="/BBS/BoardServlet?cmd=join">회원가입</a>
				</div>
			</li>
		</ul>
		</c:if>
		<c:if test="${memID != null }">
		<ul class="navbar-nav ml-auto" style="margin-right: 30px;">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown">회원관리</a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/BBS/BoardServlet?cmd=logout">로그아웃</a>
					<a class="dropdown-item" href="/BBS/BoardServlet?cmd=info&id=${memID}">개인정보</a>
					<a class="dropdown-item" href="/BBS/BoardServlet?cmd=selfBoard&id=${memID}">나의 글</a>
				</div>
			</li>
		</ul>
		</c:if>
	</div>
</nav>
</section>
<br>
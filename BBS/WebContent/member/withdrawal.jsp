<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta content= "charset=UTF-8">
		<meta name="viewport" content ="width=device-width, inintial-scale=1">
		<link rel="stylesheet" href="/BBS/css/bootstrap.css">
		<link rel="stylesheet" href="/BBS/css/custom.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="/BBS/js/bootstrap.js"></script>
		<title>회원탈퇴</title>
	</head>
	<body>
		<div class="container"> <!-- container-fluid || 둘중 하나를 사용한다.-->
			<div class="row"> <!-- 12칸 분할됨. -->
				<div class="col-lg-2"></div>
				<div class="col-lg-8">
					<form action="BoardServlet?cmd=withdrawalAction" method="post">
						<h1 style="text-align: center">회원 탈퇴</h1>
						<input class="form-control" type="text" name="memID" value="${memID}" readonly>
						<input class="form-control" type="password" name="memPW" placeholder="Your passward" maxlength="20" required autofocus>
						<button class="form-control btn btn-primary" type="submit">탈퇴하기</button>
						<p>
							그대가 그렇게까지 회원을 탈.퇴.를 하시겠다 싶으시면 그냥 아이디랑 비밀번호 확 눌러버리고 탈퇴버튼 눌려버려!!!
						</p>
					</form>
				</div>
				<div class="col-lg-2"></div>
			</div>
		</div>
	</body>
</html>
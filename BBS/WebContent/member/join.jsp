<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<c:if test="${memID != null }">
			<script>
				alert('이미 로그인이 되어있슴돠~');
				location.href='/BBS/BoardServlet?cmd=main';
			</script>
		</c:if>
		<c:if test="${memID == null }">
			<div class = "container"> <!-- container-fluid || 둘중 하나를 사용한다.-->
				<div class = "row"> <!-- 12칸 분할됨. -->
					<div class = "col-lg-2"></div>
					<div class = "col-lg-8">
						<form action = "/BBS/member/joinAction.jsp" method="post">
							<h1 style="text-align: center">sign up</h1>
							<input class = "form-control" type = "text" name = "memID" placeholder="ID" maxlength="20" required autofocus> <!-- required - 값 유,무 확인-->
							<input class = "form-control" type = "password" name = "memPW" placeholder="Passward" maxlength="20" required>
							<input class = "form-control" type = "text" name = "memNickName" placeholder="NickName" maxlength="20" required>
							<input class = "form-control" type = "text" name = "memName" placeholder="Name" maxlength="20" required>
							<div class = "btn-group btn-group-toggle" data-toggle="buttons" align="center">
								<label class="btn btn-primary active">
									<input type="radio" name="memGender" value="여" checked>Girl
								</label>
								<label class="btn btn-primary">
									<input type="radio" name="memGender" value="남">Man
								</label>
							</div>
							<input class = "form-control" type = "email" name = "memEmail" placeholder="E-mail" maxlength="20" required>
							<button class = "btn btn-primary btn-block" type = "submit">Join</button> <!-- 버튼 우측정렬 - float-right || ml-auto -->
						</form>
					</div>
					<div class = "col-lg-2"></div>
				</div>
			</div>
		</c:if>
	</body>
	
</html>
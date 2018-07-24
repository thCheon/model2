<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<c:if test="${mvo == null }">
			<script>
				alert('로그인 하소~~');
				location.href='/BBS/BoardServlet?cmd=login';
			</script>
		</c:if>
		<c:if test="${mvo != null }">
			<div class="container"> <!-- container-fluid || 둘중 하나를 사용한다.-->
				<div class="row"> <!-- 12칸 분할됨. -->
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<form action="/BBS/BoardServlet?cmd=infoUpdateAction" method="post">
							<h1 style="text-align: center">Member Update</h1>
							<input class="form-control" type="text" name="memID" placeholder="Id" maxlength="20" value="${mvo.getMemID() }" readonly> <!-- required - 값 유,무 확인-->
							<input class="form-control" type="password" name="memPW" placeholder="Passward" maxlength="20" required autofocus>
							<input class="form-control" type="text" name="memNickName" placeholder="Name" maxlength="20" value="${mvo.getNickName()}"required >
							<input class="form-control" type="text" name="memName" placeholder="Name" maxlength="20" value="${mvo.getMemName()}"required >
							<div class="btn-group btn-group-toggle" data-toggle="buttons" align="center">
								<c:if test="${mvo.getMemGender().equals('여')}">
									<label class="btn btn-primary active">
										<input type="radio" name="memGender" value="여" checked>Girl
									</label>
									<label class="btn btn-primary">
										<input type="radio" name="memGender" value="남">Man
									</label>
								</c:if>
								<c:if test="${mvo.getMemGender().equals('남')}">
									<label class="btn btn-primary">
										<input type="radio" name="memGender" value="여" >Girl
									</label>
									<label class="btn btn-primary active">
										<input type="radio" name="memGender" value="남" checked>Man
									</label>
								</c:if>
							</div>
							<input class="form-control" type="email" name="memEmail" placeholder="E-mail" maxlength="20" value="${mvo.getMemEmail()}" required>
							<div class="container">
								<div class="row">
									<div class="col-lg-6">
										<button class="btn btn-info btn-block" type="submit">UPDATE 하러 Gazah!!!!</button> <!-- 버튼 우측정렬 - float-right || ml-auto -->
									</div>
									<div class="col-lg-6">
										<button class="btn btn-success btn-block" type="button" onclick="location.href='/BBS/BoardServlet?cmd=main'">메인가자</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="col-lg-2"></div>
				</div>
			</div>
		</c:if>
	</body>
</html>
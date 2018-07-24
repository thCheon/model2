<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
  	<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
	<body>
		<%
			
		    // session.setAttribute("memID", state);
 		%>
 		<c:if test="${memID != null }">
			<script>
				alert('이미 로그인이 되어있슴돠~');
				location.href='/BBS/BoardServlet?cmd=main';
			</script>
		</c:if>
		
		<c:if test="${memID == null }">
			<!-- 로그인 화면 만들기 (그리드 시스템) -->
			<div class="container"> <!-- container-fluid || 둘중 하나를 사용한다.-->
				<div class="row"> <!-- 12칸 분할됨. -->
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<form action="BoardServlet" method="post">
							<input type="hidden" name="cmd" value="loginAction">
							<h1 style="text-align: center">로그인</h1>
							<c:choose>
								<c:when test="${cookieID != null}">
									<input id="id" class="form-control" type="text" name="memID" placeholder="Your id" maxlength="20" value="${cookieID}" required autofocus> <!-- required - 값 유,무 확인-->
								</c:when>
								<c:otherwise>
									<input id="id" class="form-control" type="text" name="memID" placeholder="Your id" maxlength="20" required autofocus> <!-- required - 값 유,무 확인-->
								</c:otherwise>
							</c:choose>
							<input class="form-control" type="password" name="memPW" placeholder="Your passward" maxlength="20" required>
							<c:choose>
								<c:when test="${cookieID.length() > 1 }">
									<input type="checkbox" id="id_check" name="id_check" checked> 아디를 저장 하시겠습니까?
								</c:when>
								<c:otherwise>
									<input type="checkbox" id="id_check" name="id_check"> 아디를 저장 하시겠습니까?
								</c:otherwise>
							</c:choose>
							<div class="container">
								<div class="row">
									<div class="col-lg-5">
										<button class="form-control btn btn-primary" type="submit">로그인</button> <!-- 버튼 우측정렬 - float-right || ml-auto -->
									</div>
									<div class="col-lg-6">
										 <a href="${apiURL}"><img height="50" src="/BBS/img/naver_Login.PNG"/></a>
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
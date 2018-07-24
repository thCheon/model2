<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hottae.dto.MemberVO"%>
<%@ page import="com.hottae.dao.MemberDAO"%>
<%@ page import="java.io.PrintWriter"%>
	
<%
		request.setCharacterEncoding("utf-8");
	%>

<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	 <script>
 		function showPopup() { 
 			window.open("BoardServlet?cmd=withdrawal", "a", "width=400, height=300, left=100, top=50");
 		}
  	</script>
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
						<form action="/BBS/BoardServlet?cmd=main" method="post">
							<h1 style="text-align: center">Member Information</h1>
							<label class="form-control">User ID : ${mvo.getMemID() } </label>
							<label class="form-control">User Name : ${mvo.getMemName()} </label>
							<label class="form-control">User NickName : ${mvo.getNickName()} </label>
							<label class="form-control">User Gender : ${mvo.getMemGender()} </label>
							<label class="form-control">User E-Mail : ${mvo.getMemEmail()}</label>
							<div class="container">
								<div class="row">
									<div class="col-lg-5">
										<button class="btn btn-success btn-block" type="submit">Go - Main!!</button> <!-- 버튼 우측정렬 - float-right || ml-auto -->
									</div>
									<div class="col-lg-5">
										<a class="btn btn-info btn-block" href='/BBS/BoardServlet?cmd=infoUpdate&id=${mvo.getMemID()}'>Member Update!!</a>
									</div>
									<div class="col-lg-2" >
										<button class="btn btn-black btn-block" type="button" onclick="showPopup();">회원탈퇴</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="col-lg-2"></div>
				</div>
			</div>
		</c:if>
		<script>
			function Over(data) {
				if(data.style.visibility="visible"){
					data.style.visibility="hidden";
				} else {
					data.style.visibility="visible";
				}
			}
			setInterval(Over ,1000);
		</script>
		
	</body>
</html>
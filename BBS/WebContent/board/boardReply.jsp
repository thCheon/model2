<%@page import="com.hottae.dto.BoardVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hottae.dao.BoardDAO"%>
<%@page import="com.hottae.dto.MemberVO"%>
<%@page import="com.hottae.dao.MemberDAO"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<script>
			/* 사진 미리보기 */
			function getThumbnailPrivew(input, targetId) {
			    if (input.files && input.files[0]) {
			        var reader = new FileReader();
			        reader.onload = function (e) {
			            var element = window.document.getElementById(targetId);
			            element.setAttribute("src", e.target.result);
			        }
			        reader.readAsDataURL(input.files[0]);
			    }
			}
		</script>
		
		<c:if test="${memID == null }">
			<script>
				alert('로그인 하시오.');
				location.href = '/BBS/BoardServlet?cmd=login';
			</script>
		</c:if>
		<c:if test="${memID != null}">
			<div class="container">
				<div class="row"> <!-- 12개의 칼럼 -->
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<form action = "BoardServlet?cmd=replyAction" method="post" style="border:1px solid #dddddd" enctype="multipart/form-data">
							<input type="hidden" name="bdMgr" value="${old.getBdId()}">
							<table class="table table-striped" style="border:1px solid #dddddd">
								<thead>
									<tr>
										<th>
											<div class="form-control">
												└[re] 덧글달기 :${old.getBdTitle()}
											</div>
										</th>
									</tr>	
								</thead>
								<tbody>
									<tr>
										<td>
											<input class="form-control" type="text" placeholder="글 제목" name="bdTitle" maxlength="50" value="">
										</td>
									</tr>
									<tr>
										<td align="right">
											<img src="/BBS/images/" id="avatar_image" style="max-width: 100px; max-height: 100px'" ><br>
											<input type="file" name="file" id="file" accept=".bmp, .gif, .jpg, .png" onchange="getThumbnailPrivew(this, 'avatar_image');">
										</td>
									</tr>
									<tr>
										<td>
											<textarea class="form-control" placeholder="글 내용" name="bdContent"
												maxlength="2048" style="height: 350px;"></textarea>
										</td>
									</tr>
								</tbody>
							</table>
							<button class="btn btn-primary float-right" type="submit">글쓰기</button>
						</form>
					</div>
					<div class="col-lg-2"></div>
				</div>
			</div>
		</c:if>
	</body>
</html>
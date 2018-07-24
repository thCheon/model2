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
				alert('로그인 하세요~');
				location.href = '/BBS/BoardServlet?cmd=login';
			</script>
		</c:if>
		<c:if test="${memID != null }">
			<div class="container">
				<div class="row"> <!-- 12개의 칼럼 -->
					<div class="col-lg-2"></div>
					<div class="col-lg-8">
						<form action = "BoardServlet?cmd=updateAction" style="border:1px solid #dddddd" method="post" enctype="multipart/form-data">
							<table class="table table-striped" style="border:1px solid #dddddd">
								<thead>
									<tr>
										<th>
											<div class = "btn btn-success">
												<input  class = "btn btn-success" type="text" name="bdId" value="${bvo.getBdId()}" style="width: 50px;" readonly > 번 글
											</div>
										</th>
									</tr>	
								</thead>
								<tbody>
									<tr>
										<td>
											<input class="form-control" type="text" placeholder="글 제목" name="bdTitle" value="${bvo.getBdTitle()}" maxlength="50">
										</td>
									</tr>	
									<tr>
										<td align="right">
										<c:if test="${bvo.getFileName() != null}">
											<c:choose>
												<c:when test="${bvo.getFileName() != null}">
													<img src="/BBS/images/${bvo.getFileName()}" id="avatar_image" class="img-circle" width="100px" height="100px"><br>
													<input value="${bvo.getFileName()}" type="file" name="file" id="file" accept=".bmp, .gif, .jpg, .png" onchange="getThumbnailPrivew(this, 'avatar_image');">
												</c:when>
												<c:otherwise>
													<img id="avatar_image" style="max-width: 100px; max-height: 100px'"><br>
													<input type="file" name="file" id="file" accept=".bmp, .gif, .jpg, .png" onchange="getThumbnailPrivew(this, 'avatar_image');">
												</c:otherwise>
											</c:choose>
											<br>
										</c:if>
										
									</tr>
									<tr>
										<td>
											<textarea class="form-control" placeholder="글 내용" name="bdContent" maxlength="2048" style="height: 350px;">${bvo.getBdContent()}</textarea>
										</td>
									</tr>
								</tbody>
							</table>
							<button class="btn btn-primary float-right" type="submit">글수정</button>
						</form>
					</div>
					<div class="col-lg-2"></div>
				</div>
			</div>
		</c:if>
	</body>
</html>
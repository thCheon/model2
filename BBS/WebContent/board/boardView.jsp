<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<div class="container">
			<div class="row"> <!-- 12개의 칼럼 -->
				<div class="col-lg-2"></div>
				<div class="col-lg-8">
					<table class="table table-striped" style="border:1px solid #dddddd">
						<thead>
							<tr>
								<th>게시판 글 읽기</th>
							</tr>	
						</thead>
						<tbody>
							<tr>
								<td>
									<input class="form-control" type="text" placeholder="글 제목" 
										name="bdTitle" maxlength="50" value="${bvo.getBdTitle()}" readonly>
								</td>
							</tr>
							<tr>
								<td>
									<div class="form-control" style="height: auto; width: 100%">
										<c:if test="${bvo.getFileName() != null}">
											<c:choose>
												<c:when test="${img_Width > 670}">
													<img alt="${bvo.getFileName()}" src="/BBS/images/${bvo.getFileName()}" width="670">
												</c:when>
												<c:otherwise>
													<img alt="${bvo.getFileName()}" src="/BBS/images/${bvo.getFileName()}">
												</c:otherwise>
											</c:choose>
											<br>
										</c:if>
										<label>${bvo.getBdContent()}</label>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				<a class="btn btn-primary float-left" href="BoardServlet?cmd=boardList">목록보기</a>
				<a class="btn btn-primary float-right" href="BoardServlet?cmd=reply&bdId=${bvo.getBdId()}">답글 달기</a>
				
				<c:if test="${memID.equals(bvo.getMemId())}">
					<a class="btn btn-success float-right" href="BoardServlet?cmd=delete&bdId=${bvo.getBdId()}">삭제</a>
				</c:if>
				
				</div>
				<div class="col-lg-2"></div>
			</div>
		</div>
	</body>
</html>
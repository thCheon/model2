<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<div class="container my-container">
			<div class="row"> <!-- 12개의 칼럼 -->
				<div class="col-lg-2"></div>
				<div class="col-lg-8">
					<div>
						<h2 class="form-control" align="center">검색 내용 : ${word}</h2>
					</div>
					<table class="table table-striped" style="border:1px solid #dddddd">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>글 수정</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="list" items="${boardList}">
							<tr style="text-align: center">
								<td>${list.getBdRow() }</td>
								<td>
									<a href="BoardServlet?cmd=boardView&bdId=${list.getBdId()}&id=${memID}">
										<c:if test="${list.getFileName() != null }">
											<img src="/BBS/images/${list.getFileName()}" class="img-circle" width="50px" height="50px">
										</c:if>
										${list.getBdTitle()}
									</a>
								</td>
								<td>${list.getNickName() }</td>
								<td>${list.getBdDate() }</td>
								<td>
									<c:if test="${memID.equals(list.getMemId())}">
										<a class="btn btn-success float-right" 
											href="/BBS/board/boardUpdate.jsp?bdId=${list.getBdId()}">글 수정
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<c:if test="${prev == true}">
						<a class="btn btn-success" href="BoardServlet?cmd=boardSearch&pageNum=${pageNum - 1}&word=${word}&option=${option}"
							style = "margin:2px">이전</a>
					</c:if>
					<c:if test="${next == true}">
						<a class="btn btn-success" href="BoardServlet?cmd=boardSearch&pageNum=${pageNum + 1}&word=${word}&option=${option}"
							style = "margin:2px">다음</a>
					</c:if>
					<a class="btn btn-primary float-right" href="BoardServlet?cmd=write" style = "margin:2px">글쓰기</a>
				</div>
				<div class="col-lg-2"></div>
			</div>
		</div>
	</body>
</html>
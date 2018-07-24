<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
	 #bname:link { color: #6E6E6E; text-decoration: none;}
	 #bname:visited { color: #6E6E6E; text-decoration: none;}
	 #bname:hover { color: #6E6E6E; text-decoration: underline;}
	 
	 #btitle:link { color: #1C1C1C; text-decoration: none;}
	 #btitle:visited { color: #1C1C1C; text-decoration: none;}
	 #btitle:hover { color: #1C1C1C; text-decoration: underline;}
	 
	 .col-lg-3 { float:left; }
	 .col-lg-9 { float:right; }
</style>
	<%@ include file="/header.jsp" %>
	<body>
		<div class="container">
			<form action="BoardServlet" method="get">
    			<input type="hidden" name="cmd" value="nSearch">
				<div class="input-group">
					<div class="input-group-prepend">
						<select name="nSearch" class="input-group-text">
							<option value="blog">블로그 검색</option>
							<option value="movie">영화 검색</option>
						</SELECT>
					</div>
					<input type="text" name="text" class="form-control" placeholder="네이버 블로그를 검색합니다.">
					<button class="btn btn-success" type="submit"> 검색하기 </button>
				</div>
			</form>
		</div>
		<h2 class="container">검색어 : ${text}</h2>
		
		<c:forEach var="item" items="${ItemList}">
		<div class="container">
			<div class="row">
				<div class="card container">
					<div class="card-body">
						<c:if test="${nSearch.equals('blog')}">
							<div class="col-lg-3">
								<img alt="사진은 못불러옵니다 ㅜㅜ.." style="max-width:172px;max-height:300px;" src="../img/cowWhy.jpg">
							</div>
							<div class="col-lg-9">
								<h5><a id="btitle"href="${item.getLink()}">${item.getTitle()}</a></h5>
								<p><small>${item.getDescription()}</small></p>
								<small>
									<a id="bname" href="${item.getBloggerLink()}">${item.getBloggerName()}</a><br>
									<a href="${item.getLink()}"><c:out value="${fn:replace(item.getLink(),'?Redirect=Log&amp;logNo=','/')}"/></a>
									<label style="background-color: #81F7F3;" class="float-right">작성일 : ${item.getPostDate()}</label>
								</small>
							</div>
						</c:if>
						<c:if test="${nSearch.equals('movie')}">
							<div class="col-lg-3">
								<img style="max-width:172px;max-height:300px;" src="${item.getImage()}">
							</div>
							<div class="col-lg-9">
								<h5><a id="btitle" href="${item.getLink()}">${item.getTitle()}</a></h5>
								<small>
									평점 : ${item.getUserRating()}<br>
									부재목 : ${item.getSubtitle()} || 개봉일 : ${item.getPubDate()}<br>
									감독 : ${item.getDirector()}<br>
									출연 : ${item.getActor()}
								</small>
							</div>
						</c:if> 
					</div>
				</div>
			</div>
		</div>
		</c:forEach>
		<br>
	</body>
</html>
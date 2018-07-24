<%@page import="util.Common"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hottae.dto.BoardVO"%>
<%@page import="com.hottae.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<%@ include file="/header.jsp" %>
	<body>
		<div class="container">
  			<div class="jumbotron">
   				<h1>JSP WEB PAGE</h1>      
    			<p>해당 페이지는 부트스트랩4 와 JSP를 이용한 홈페이지 입니다.       
    			Oracle, Eclipse, Windows, CSS, HTML, JS, JQuery 를 이용하였습니다.</p>
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
						<button class="btn btn-success" type="submit">검색하기 </button>
					</div>
				</form>
  			</div>
		</div>
		<div class="container">
			<h2>I'm the korean top class - 췎↗ - TOP3 - </h2>
			<table class="table table-striped" style="border:1px solid #dddddd">
				<thead>
					<tr>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>조회 수</th>
					</tr>
				</thead>
				<tbody id="topview">
				</tbody>
			</table>
		</div>
		
		<div id="demo" class="container carousel slide" data-ride="carousel">
 			 <!-- Indicators -->
 			 <ul class="carousel-indicators">
    			<li data-target="#demo" data-slide-to="0" class="active"></li>
    			<li data-target="#demo" data-slide-to="1"></li>
	   			 <li data-target="#demo" data-slide-to="2"></li>
	  		</ul>		  
			<!-- The slideshow -->
			<div class="carousel-inner">
				<div class="carousel-item active">
			    	<img src="/BBS/img/1.gif" alt="Prinsess Dialy1" width="1100" height="500">
			    	<div class="carousel-caption">
		       			<h3>Prinsess Dialy1</h3>
		        		<p>넘모 귀여운 앤 헤서웨이 이닙뉘꽈~ 넘모 귀엽자너...</p>
		      		</div>
			    </div>
			    <div class="carousel-item">
			    	<img src="/BBS/img/2.gif" alt="Prinsess Dialy2" width="1100" height="500">
			    	<div class="carousel-caption">
		       			<h3>Prinsess Dialy2</h3>
		        		<p>땡큐 갈겨버리는 클라스...키야..넘모 귀엽다...</p>
		      		</div>
			    </div>
			    <div class="carousel-item">
			    	<img src="/BBS/img/3.gif" alt="Prinsess Dialy3" width="1100" height="500">
			    	<div class="carousel-caption">
		       			<h3>Prinsess Dialy3</h3>
		        		<p>We had such a great time in LA!</p>
		      		</div>
			    </div>
			</div>
			  
			<!-- Left and right controls -->
			<a class="carousel-control-prev" href="#demo" data-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</a>
			<a class="carousel-control-next" href="#demo" data-slide="next">
			    <span class="carousel-control-next-icon"></span>
			</a>
		</div>
		<br>
		<script>
		/* 아작스 사용 예시 */
			$(document).ready(function(){
				getAjaxList();
			});
			Object.equals = function(x, y) {
		         if (x === y)
		            return true;
		         if (!(x instanceof Object) || !(y instanceof Object))
		            return false;
		         if (x.constructor !== y.constructor)
		            return false;
		         for (var p in x) {
		            if (!x.hasOwnProperty(p))
		               continue;
		            if (!y.hasOwnProperty(p))
		               return false;
		            if (x[p] === y[p])
		               continue;
		            if (typeof (x[p]) !== "object")
		               return false
		            if (!Object.equals(x[p], y[p]))
		               return false;
		         }
		         for (p in y) {
		            if (y.hasOwnProperty(p) && !x.hasOwnProperty(p))
		               return false;
		         }
		         return true;
		      }
			var prev;
			function getAjaxList(){
				$.ajax({
					type:"GET",
					url:"AjaxList",
					dataType: "json",
					success : function(data) {
	                  if (!Object.equals(prev, data)) {
	                     $(topview).html("");
	                     for (var i = 0; i < data.length; i++) {
	                        $(topview).append("<tr><td><a href='/BBS/board/boardView.jsp?bdId="+data[i].bdId+"'>" + data[i].bdTitle + "</a></td> " +
	                           "<td>" + data[i].nickName + "</td> " +
	                           "<td>" + data[i].bdDate + "</td>"+
	                           "<td>" + data[i].readCount + "</td></tr>");
	                     }
	                     prev = data;
	                  }
					}
				});
			}
			setInterval(getAjaxList,5000);
		</script>
	</body>
</html>
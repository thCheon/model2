<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
	</head>
	<body>
		<c:choose>
			<c:when test="${result == 1}">
				<script>
					location.href='/BBS/BoardServlet?cmd=main';
				</script>
			</c:when>
			<c:when test="${result == 0}">
				<script>
					alert('아이디 혹은 비밀번호가 틀렸습니다.');
					history.back();
				</script>
			</c:when>
			<c:when test="${result==-2}">
				<script>
					history.back();
				</script>
			</c:when>
		</c:choose>
	</body>
</html>
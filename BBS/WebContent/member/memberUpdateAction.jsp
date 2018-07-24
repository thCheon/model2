<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<c:choose>
		<c:when test="${result == -2 }">
			<script>
				alert('DB-Error');
				history.back();
			</script>
		</c:when>
		<c:when test="${result == 1 }">
			<script>
				alert('회원정보 수정 하였습니다.');
				location.href='/BBS/BoardServlet?cmd=info';
			</script>
		</c:when>
	</c:choose>
</body>
</html>
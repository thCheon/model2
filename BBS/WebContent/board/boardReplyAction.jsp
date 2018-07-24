<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<body>
	<c:if test="${memID == null }">
		<script>
			alert('로그인 하세요.');
			location.href = 'BoardServlet?cmd=login';
		</script>
	</c:if>

	<c:if test="${memID != null}">
		<c:choose>
			<c:when test="${result == 1}">
				<script>
					alert('글 등록 성공');
					location.href = 'BoardServlet?cmd=boardList';
				</script>
			</c:when>
			<c:when test="${result == -2}">
				<script>
					alert('글 등록 실패');
					history.back();
				</script>
			</c:when>
		</c:choose>
	</c:if>
</body>
</html>
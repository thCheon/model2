<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<body>
		<c:if test="${result == 1 }">
			<script>
				alert('회원탈퇴 성공');
				opener.location.href='/BBS/BoardServlet?cmd=main';
				self.close();
			</script>
		</c:if>
		<c:if test="${result == -2 }">
			<script>
				alert('회원탈퇴 실패');
				history.back();
			</script>
		</c:if>
		<c:if test="${result == 0 }">
			<script>
				alert('비밀번호가 틀렷숨다~');
				history.back();
			</script>
		</c:if>
	</body>
</html>
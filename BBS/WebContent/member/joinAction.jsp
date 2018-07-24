<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- memPW -> SHA1, MD5 로 변형하여 저장하기 -->

<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:if test="${memID != null }">
		<script>
			alert('이미 로그인이 되어있슴돠~');
			location.href = '/BBS/BoardServlet?cmd=main';
		</script>
	</c:if>

	<c:if test="${memID == null }">
		<c:choose>
			<c:when test="${mvo.getMemID() == null or mvo.getMemPW() == null or
								mvo.getMemName() == null or mvo.getMemGender() == null or
								mvo.getMemEmail() == null or mvo.getNickName() == null }">
				<script>
					alert('입력되지 않은 사항이 있습니다.');
					history.back();
				</script>
			</c:when>
			<c:otherwise>
				<c:when test="${result == -2 }">
					<script>
						alert('이미 존재하는 아이디 입니다.');
						history.back();
					</script>
				</c:when>
				<c:when test="${result == 1 }">
					<script>
						alert('회원가입에 성공 하였습니다.');
						location.href = '/BBS/BoardServlet?cmd=main';
					</script>
				</c:when>
			</c:otherwise>
		</c:choose>
	</c:if>
</body>
</html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
</head>
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
<%--

	String bdId = multipartRequest.getParameter("bdId");
	b.setBdId(Integer.parseInt(bdId));

	if (memID == null) {
		script.println("<script>");
		script.println("alert('로그인 하세요.');");
		script.println("location.href='/BBS/member/login.jsp'; ");
		script.println("</script>");
	} else {
		b.setMemId(memID);
		System.out.println(3);
		if (b.getBdTitle() == null || b.getBdContent() == null || b.getBdTitle().equals("")
				|| b.getBdContent().equals("")) {
			script.println("<script>");
			script.println("alert('입력되지 않은 사항이 있습니다.');");
			script.println("history.back();");
			script.println("</script>");
		} else {
			BoardDAO bdView = BoardDAO.getInstance;
			System.out.println(4);
			// 새로운 Board 객체에 이전 정보를 가지고온다.
			String beforName = "";

			BoardVO bView = bdView.view(b.getBdId() + "");
			if (bView.getFileName() != null) {
				beforName = bView.getFileName();
				System.out.println("beforName : " + beforName);
			}

			// 새로넣은 사진이 있는지 확인.
			if (fileRealName == null) {
				b.setFileName(beforName); // 없으면 이전사진 그대로 이름
			} else {
				File file = new File(directory + beforName); // 있으면 이전파일 삭제
				file.delete();
			}

			// 수정된 내용 set하기
			//write함수 호출 int result값 받기
			BoardDAO bd = new BoardDAO();
			int result = bd.update(b);

			script.println("<script>");
			if (result == 1) {
				script.println("alert('글 수정 성공');");
				script.println("location.href = '/BBS/board/board.jsp' ");
			} else if (result == -2) {
				System.out.println("DB오류");
				script.println("alert('글 수정 실패');");
				script.println("history.back();");
			}
			script.println("</script>");
		}
	}
--%>

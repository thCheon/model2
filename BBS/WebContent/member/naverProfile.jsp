<%@page import="java.io.PrintWriter"%>
<%@page import="com.hottae.dao.MemberDAO"%>
<%@page import="com.hottae.dto.MemberVO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta content="charset=UTF-8">
<meta name="viewport" content="width=device-width, inintial-scale=1">
<!--  bootstrap file -->
<link rel="stylesheet" href="/BBS/css/bootstrap.css">
<link rel="stylesheet" href="/BBS/css/custom.css">

<!--  google j-query file -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="/BBS/js/bootstrap.js"></script>
<title>Insert title here</title>
</head>
<body>
	<%
		System.out.println("naverProfile.jsp");
		String token = request.getParameter("access_token"); // 네이버 로그인 접근 토큰;
		String header = "Bearer " + token; // Bearer 다음에 공백 추가
		try {
			String apiURL = "https://openapi.naver.com/v1/nid/me";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Authorization", header);
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			System.out.println(1);
			String inputLine;
			StringBuffer sbr = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				sbr.append(inputLine);
			}
			JSONParser parser = new JSONParser();
			JSONObject jsonData = (JSONObject) parser.parse(sbr.toString());
			JSONObject res = (JSONObject) jsonData.get("response");
			String id = res.get("id").toString();
			String gender = res.get("gender").toString();
			String email = res.get("email").toString();
			String name = res.get("name").toString();
			MemberDAO dao = MemberDAO.getInstance();
			MemberVO member = dao.info(id);
			if (member == null) {
				MemberVO m = new MemberVO();
				m.setMemID(id);
				m.setMemGender(gender);
				m.setMemEmail(email);
				m.setMemName(name);
				int a = dao.join(m);
				if (a == 1) {
					response.sendRedirect("/BBS/BoardServlet?cmd=main&memID="+id);
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('join-Error');");
					script.println("history.back();");
					script.println("</script>");
				}
			} else {
				response.sendRedirect("/BBS/BoardServlet?cmd=main&memID="+id);
			}
			System.out.println("id : " + id + ", gd : " + gender + ", email : " + email + ", name : " + name);
			br.close();
			System.out.println(response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	%>
</body>
</html>
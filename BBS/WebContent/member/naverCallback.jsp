<%@page import="java.io.PrintWriter"%>
<%@page import="com.hottae.dao.MemberDAO"%>
<%@page import="naverapi.NaverAPI"%>
<%@page import="org.json.simple.parser.JSONParser"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.net.URL"%>
<%@ page import="java.net.HttpURLConnection"%>
<%@ page import="java.io.BufferedReader"%>
<%@ page import="java.io.InputStreamReader"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>

<body>
	<%
		PrintWriter script = response.getWriter();

		String clientId = "your clientId";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "yout clientSecret";//애플리케이션 클라이언트 시크릿값";
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String redirectURI = URLEncoder.encode("your Callback_URL", "UTF-8");
		String apiURL;
		apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		apiURL += "client_id=" + clientId;
		apiURL += "&client_secret=" + clientSecret;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&code=" + code;
		apiURL += "&state=" + state;
		String access_token = "", refresh_token = "", token_type = "", expires_in = "";
		String error = "", error_description = "";
		System.out.println("apiURL=" + apiURL);

		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer res = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				out.println(res.toString());
				JSONParser parser = new JSONParser();
				JSONObject jsonData = (JSONObject) parser.parse(res.toString());
				access_token = jsonData.get("access_token").toString();
				String memID = new NaverAPI().setAccess(access_token);
				if (memID != null) {
					System.out.println(memID);
					session.setAttribute("memID", memID);
					session.setMaxInactiveInterval(60 * 60);
					script.println("<script>");
					script.println("location.href='/BBS/BoardServlet?cmd=main'");
					script.println("</script>");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	%>
</body>
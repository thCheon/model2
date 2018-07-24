package com.hottae.mbaction;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.bdaction.Action;

public class MemberLoginForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/login.jsp";
		
		//-------------------------------------------------------------
		
		String cookieID="";
		Cookie[] cookies=request.getCookies();
		for(Cookie c : cookies){
			if(c.getName().equals("id")){
				// Element찾아서 넣어주면 됨.
				cookieID=c.getValue();
			}
		}
		
		//-------------------------------------------------------------
		
		String clientId="your clientID";//애플리케이션 클라이언트 아이디값";
	    String redirectURI=URLEncoder.encode("your CallBack URL", "UTF-8");
	    SecureRandom random=new SecureRandom();
	    String state=new BigInteger(130, random).toString();
	    String apiURL="https://nid.naver.com/oauth2.0/authorize?response_type=code";
	    apiURL += "&client_id=" + clientId;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&state=" + state;
	    
		//-------------------------------------------------------------

		request.setAttribute("cookieID", cookieID);
		request.setAttribute("apiURL", apiURL);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
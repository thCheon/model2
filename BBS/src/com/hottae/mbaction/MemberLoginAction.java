package com.hottae.mbaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hottae.bdaction.Action;
import com.hottae.dao.MemberDAO;

public class MemberLoginAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-------------------------------------------------------------
		
		String url = "/member/loginAction.jsp";
		HttpSession session = request.getSession();
		
		//-------------------------------------------------------------
		
		String id = request.getParameter("memID");
		String pw = request.getParameter("memPW");
		MemberDAO md = MemberDAO.getInstance();
		int result = md.login(id, pw.hashCode());
		
		String check = "";
		if(request.getParameter("id_check") != null){
			check = request.getParameter("id_check"); // 체크박스 체크O : on, 체크X : null	
		}
		
		//-------------------------------------------------------------
		
		if(result == 1){
			session.setAttribute("memID", id);
			session.setMaxInactiveInterval(60*60);
			 if(check.equals("on")){
				Cookie cookie = new Cookie("id", id); //쿠키값 넣기
				cookie.setMaxAge(3000);	// 유지시간(초단위)
				response.addCookie(cookie); // 넘기기
				System.out.println("쿠키 생성");
			 } else {
				Cookie cookie = new Cookie("id", null); //쿠키값 null
				cookie.setMaxAge(0);	// 유지시간(초단위)
				response.addCookie(cookie); // 넘기기
				System.out.println("쿠키 노생성");
			 }
		} 
		
		//-------------------------------------------------------------
		
		request.setAttribute("result", result);
		
		
		//-------------------------------------------------------------
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
package com.hottae.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hottae.bdaction.Action;

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doProcess(request, response);
	}
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {request.setCharacterEncoding("UTF-8");
		response.setContentType("html/text; charset=UTF-8");
		HttpSession session = request.getSession();
		String memID = null;
		System.out.println("memID:" + session.getAttribute("memID"));
		if (session.getAttribute("memID") != null) { // 로그인된 상태 일 때만
			// You are signed in. ID값을 memID에 넣어준다.	
			memID = (String) session.getAttribute("memID");
		}
		System.out.println("memID :" + memID);
		request.setAttribute("memID", memID);
		String cmd = request.getParameter("cmd");
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(cmd);
		if(action != null) {
			action.execute(request, response);
		}
	}
}

package com.hottae.mbaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hottae.bdaction.Action;
import com.hottae.dao.MemberDAO;

public class MemberDeleteAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-------------------------------------------------------------
		
		String url = "/member/withdrawalAction.jsp";
		MemberDAO dao = MemberDAO.getInstance();
		HttpSession session = request.getSession();
		
		//-------------------------------------------------------------
		
		String id = request.getParameter("memID");
		String pw = request.getParameter("memPW");
		
		//-------------------------------------------------------------
	    
		int result = dao.login(id, pw.hashCode());
		if(result == 1) {
			result = dao.withdrawal(id, pw.hashCode());
			session.invalidate();
		}
		
		request.setAttribute("result", result);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
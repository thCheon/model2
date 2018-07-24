package com.hottae.mbaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.bdaction.Action;

public class MemberJoinForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/member/join.jsp";
		
		//-------------------------------------------------------------
		
		//-------------------------------------------------------------
		
		//-------------------------------------------------------------
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
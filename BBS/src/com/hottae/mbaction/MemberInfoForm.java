package com.hottae.mbaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.bdaction.Action;
import com.hottae.dao.MemberDAO;
import com.hottae.dto.MemberVO;

public class MemberInfoForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-------------------------------------------------------------default_setting
		
		String url = "/member/info.jsp";
		MemberDAO md = MemberDAO.getInstance();
		String id = request.getParameter("id");
		
		//-------------------------------------------------------------Search User Information
		
		MemberVO mvo = md.info(id);
		
		//-------------------------------------------------------------setAttribute Info_Data
		
		request.setAttribute("mvo", mvo);
		
		//-------------------------------------------------------------
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
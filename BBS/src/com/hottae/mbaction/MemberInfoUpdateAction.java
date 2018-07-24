package com.hottae.mbaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.bdaction.Action;
import com.hottae.dao.MemberDAO;
import com.hottae.dto.MemberVO;

public class MemberInfoUpdateAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//-------------------------------------------------------------default_setting
		
		String url = "/member/memberUpdateAction.jsp";
		MemberVO mvo = new MemberVO();
		MemberDAO dao = MemberDAO.getInstance();
		
		//-------------------------------------------------------------getParameter
		
		String id = request.getParameter("memID");
		String pw = request.getParameter("memPW");
		String name = request.getParameter("memName");
		String gender = request.getParameter("memGender");
		String email = request.getParameter("memEmail");
		String nickname = request.getParameter("memNickName");
		
		System.out.println("id : "+id);
		System.out.println("pw : "+pw.hashCode());
		System.out.println("name : "+name);
		System.out.println("gender : "+gender);
		System.out.println("email : "+email);
		System.out.println("nick : "+nickname);
		
		//-------------------------------------------------------------set_MemberVO_items
		
		mvo.setMemID(id);
		mvo.setMemPW(pw);
		mvo.setMemName(name);
		mvo.setMemGender(gender);
		mvo.setMemEmail(email);
		mvo.setNickName(nickname);
		
		//-------------------------------------------------------------

		int result = dao.update(mvo);
		
		//-------------------------------------------------------------
		
		request.setAttribute("result", result);
		request.setAttribute("mvo", mvo);
		
		//-------------------------------------------------------------
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
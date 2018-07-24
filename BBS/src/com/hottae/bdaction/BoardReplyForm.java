package com.hottae.bdaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

public class BoardReplyForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardReply.jsp";
		BoardDAO bd = BoardDAO.getInstance();
		
		String bdId = request.getParameter("bdId");	
		BoardVO old = bd.view(bdId);
		request.setAttribute("old", old);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
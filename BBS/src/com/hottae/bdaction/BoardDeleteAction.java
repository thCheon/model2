package com.hottae.bdaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.dao.BoardDAO;

public class BoardDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -------------------------------------------------------------Default Setting
		
		String url = "/board/boardDeleteAction.jsp";
		BoardDAO bd = BoardDAO.getInstance();
		String bdId = "";	
		
		// -------------------------------------------------------------request_Parameter
		
		if(request.getParameter("bdId") != null){
			bdId = request.getParameter("bdId");	
		}

		// -------------------------------------------------------------BoardDelete_Action_BoardDAO
		
		int result = bd.delete(bdId);
		request.setAttribute("result", result);
		
		// -------------------------------------------------------------URL Dispatcher
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
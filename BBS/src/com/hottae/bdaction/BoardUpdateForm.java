package com.hottae.bdaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

public class BoardUpdateForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -------------------------------------------------------------Default Setting
		
		String url = "/board/boardUpdate.jsp";
		BoardDAO bd = BoardDAO.getInstance();
		
		// -------------------------------------------------------------request_Parameter
		
		String bdId = request.getParameter("bdId");
		
		// -------------------------------------------------------------GetBoardAction
		
		BoardVO bvo = bd.view(bdId);
		
		// -------------------------------------------------------------setAttribute
		
		request.setAttribute("bvo", bvo);
			
		// -------------------------------------------------------------URL Dispatcher
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
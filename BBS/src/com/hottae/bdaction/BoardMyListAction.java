package com.hottae.bdaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

public class BoardMyListAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/myBoard.jsp";
		
		//-------------------------------------------------------------
		
		int pageNum = 1;
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//-------------------------------------------------------------
		
		BoardDAO dao = BoardDAO.getInstance();
		String id = request.getParameter("id");
		List<BoardVO> list = dao.myBoard(id, pageNum);
		
		//-------------------------------------------------------------
		
		boolean prev = true, next = true;
		if(dao.getList(pageNum-1).size() == 0) {
			prev = false;
		}
		if(dao.getList(pageNum+1).size() == 0) {
			next = false;
		}
		
		//-------------------------------------------------------------
		
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("boardList", list);
		request.setAttribute("pageNum", pageNum);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
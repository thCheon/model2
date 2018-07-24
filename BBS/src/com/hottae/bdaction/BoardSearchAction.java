package com.hottae.bdaction;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

public class BoardSearchAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/boardSearch.jsp";
		
		//-------------------------------------------------------------option&word Setting
		
		String option = request.getParameter("option");
		String word = request.getParameter("word");

		request.setAttribute("option", option);
		
		if(option.equals("all")) {
			option = "memid || bdcontent || bdtitle";
		} else if(option.equals("bdtit_con")) {
			option = "memid || bdcontent";
		}
		
		//-------------------------------------------------------------PageNum Setting
		
		int pageNum = 1;
		
		if(request.getParameter("pageNum") != null){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		
		//-------------------------------------------------------------CallSearchList()
		
		BoardDAO dao = BoardDAO.getInstance();
		List<BoardVO> list = dao.serchList(option, word, pageNum);
		
		//-------------------------------------------------------------Next or Prev Confirm
		
		boolean prev = true, next = true;
		if(dao.serchList(option, word, pageNum-1).size() == 0) {
			prev = false;
		}
		if(dao.serchList(option, word, pageNum+1).size() == 0) {
			next = false;
		}
		
		//-------------------------------------------------------------URL Dispatcher
		
		request.setAttribute("boardList", list);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("prev", prev);
		request.setAttribute("next", next);
		request.setAttribute("word", word);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
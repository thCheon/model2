package com.hottae.bdaction;

import java.awt.Image;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

public class BoardViewForm implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -------------------------------------------------------------Default Setting
		
		String url = "/board/boardView.jsp";
		BoardDAO bd = BoardDAO.getInstance();
		
		// -------------------------------------------------------------request_Parameter

		String id = request.getParameter("id");
		String bdId = request.getParameter("bdId");
		
		// -------------------------------------------------------------Board_CountUpdate
		
		BoardVO bvo = bd.view(bdId);
		if(!id.equals(bvo.getMemId())){ 
			bd.countPlus(Integer.parseInt(bdId));
		}

		// -------------------------------------------------------------[re]GetBoardAction
		
		bvo = bd.view(bdId);
		
		// -------------------------------------------------------------get_ImageWidth
		
		Image img  = new ImageIcon("D:/BusanIT/JSPProject/eclipse-workspace/BBS/WebContent/images/" + bvo.getFileName()).getImage();
		int img_Width = img.getWidth(null);
		
		// -------------------------------------------------------------setAttribute
		
		request.setAttribute("bvo", bvo);
		request.setAttribute("img_Width", img_Width);
			
		// -------------------------------------------------------------URL Dispatcher
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
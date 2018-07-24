package com.hottae.naveraction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hottae.bdaction.Action;

import naverapi.NaverAPI;
import naverapi.NaverItems;

public class NaverSearchAction implements Action { 
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/board/naverSearch.jsp";
		
		//-------------------------------------------------------------
		
		String text = "";
		if(request.getParameter("text") != null){
			text = request.getParameter("text");
		}
		String nSearch = request.getParameter("nSearch");
		
		//-------------------------------------------------------------getList
		
		ArrayList<NaverItems> ItemList = new NaverAPI().naverSearch(text, nSearch);
		
		//-------------------------------------------------------------
		
		request.setAttribute("ItemList", ItemList);
		request.setAttribute("text", text);
		request.setAttribute("nSearch", nSearch);
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
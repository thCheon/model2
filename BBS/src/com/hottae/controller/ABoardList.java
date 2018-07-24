package com.hottae.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hottae.dao.BoardDAO;
import com.hottae.dto.BoardVO;

@WebServlet("/AjaxList")
public class ABoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ABoardList() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("doGet");
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("doPost");
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		BoardDAO bd = BoardDAO.getInstance();
		ArrayList<BoardVO> list = bd.topView();
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(list);
		
		// System.out.println(jsonData);
		// JSONObject object = new JSONObject();
		// object.put("post", jsonData);
		// System.out.println(object);
		PrintWriter out = response.getWriter();
		out.println(jsonData);
	}
}

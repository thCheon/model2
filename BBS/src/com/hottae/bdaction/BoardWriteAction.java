package com.hottae.bdaction;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hottae.dao.BoardDAO;
import com.hottae.dao.MemberDAO;
import com.hottae.dto.BoardVO;
import com.hottae.dto.MemberVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// -------------------------------------------------------------Default Setting
		
		String url = "/board/boardWriteAction.jsp";
		MemberDAO md = MemberDAO.getInstance();
		BoardDAO bd = BoardDAO.getInstance();
		BoardVO b = new BoardVO();
		
		// -------------------------------------------------------------NickName Setting
		
		HttpSession session = request.getSession();
		String memID = null;
		if (session.getAttribute("memID") != null) {
			// You are signed in. ID값을 memID에 넣어준다.
			memID = (String) session.getAttribute("memID");
		}
		MemberVO m = md.info(memID);
		String nickName = m.getNickName();
		
		// -------------------------------------------------------------request_Parameter
		
		String directory = "D:/BusanIT/JSPProject/eclipse-workspace/BBS/WebContent/images/";
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";

		MultipartRequest multipartRequest = new MultipartRequest(request, directory, maxSize, encoding,
				new DefaultFileRenamePolicy());
		Enumeration fileNames = multipartRequest.getFileNames();

		String parameter = "", fileName = "", fileRealName = "";

		if (fileNames.hasMoreElements()) {
			parameter = (String) fileNames.nextElement();
			fileName = multipartRequest.getOriginalFileName(parameter); // 상대가 보낸 내용
			fileRealName = multipartRequest.getFilesystemName(parameter); // db에 올라갈내용
		}

		// -------------------------------------------------------------IP Setting
		
		String getIP = "";
		System.out.println("IP주소 : " + request.getRemoteAddr());

		if (request.getRemoteAddr().equals("192.168.0.1") || request.getRemoteAddr().equals("127.0.0.1")) {
			getIP = InetAddress.getLocalHost().getHostAddress();
		} else {
			getIP = request.getRemoteAddr();
		}

		// -------------------------------------------------------------BoardVO Setting
		
		b.setBdTitle(multipartRequest.getParameter("bdTitle"));
		b.setBdContent(multipartRequest.getParameter("bdContent"));
		b.setMemId(memID);
		b.setFileName(fileRealName);
		b.setNickName(nickName);
		b.setBdIp(getIP);

		// -------------------------------------------------------------BoardWrite_Action_BoardDAO
		
		int result = bd.write(b);
		request.setAttribute("result", result);
		
		// -------------------------------------------------------------URL Dispatcher
		
		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
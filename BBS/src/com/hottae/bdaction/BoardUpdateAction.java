package com.hottae.bdaction;

import java.io.File;
import java.io.IOException;
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

public class BoardUpdateAction implements Action {
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

		String parameter = "", fileRealName = "";

		if (fileNames.hasMoreElements()) {
			parameter = (String) fileNames.nextElement();
			fileRealName = multipartRequest.getFilesystemName(parameter);
			if (fileRealName != null) {
				System.out.println("fileRealName : " + fileRealName.toString() + " ;");
				b.setFileName(fileRealName);
			}
		}
		// -------------------------------------------------------------BoardVO Setting

		b.setBdTitle(multipartRequest.getParameter("bdTitle"));
		b.setBdContent(multipartRequest.getParameter("bdContent"));
		b.setBdId(Integer.parseInt(multipartRequest.getParameter("bdId")));
		b.setMemId(memID);
		b.setFileName(fileRealName);
		b.setNickName(nickName);

		// -------------------------------------------------------------Check Old_FileName
		BoardDAO bdView = BoardDAO.getInstance();
		String beforName = "";

		BoardVO bView = bdView.view(b.getBdId() + "");
		if (bView.getFileName() != null) {
			beforName = bView.getFileName();
			System.out.println("beforName : " + beforName);
		}
		
		// -------------------------------------------------------------Check New_FileName
		if (fileRealName == null) {
			b.setFileName(beforName); // 없으면 이전사진 그대로 이름 || If there are no new files, keep the old files
		} else {
			File file = new File(directory + beforName); // Delete old files if new ones exist
			file.delete();
		}

		int result = bd.update(b);
		request.setAttribute("result", result);

		// -------------------------------------------------------------URL Dispatcher

		RequestDispatcher dis = request.getRequestDispatcher(url);
		dis.forward(request, response);
	}
}
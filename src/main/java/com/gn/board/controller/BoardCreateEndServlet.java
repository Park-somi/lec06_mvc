package com.gn.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gn.board.service.BoardService;
import com.gn.board.vo.Board;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/board/createEnd")
public class BoardCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public BoardCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. multipart/form-data 형식으로 파일이 왔는지 확인 -> 잘 안왔으면 Redirect
		if(ServletFileUpload.isMultipartContent(request)) {
			// 2. 파일 저장 위치 설정
			String dir = request.getServletContext().getRealPath("/upload"); // 프로젝트(webapp)의 위치 - upload 라는 폴더에 파일을 저장
			// 3. 저장 파일의 최대 크기 설정(10MB)
			int maxSize = 1024 * 1024 * 10;
			// 4. 인코딩 설정
			String encoding = "UTF-8";
			// 5. Rename 클래스(파일명 중복되지 않게 해주는 클래스 - 같은 파일명 넣을 때 이름 뒤에 숫자를 붙여줌)
			DefaultFileRenamePolicy dfr = new DefaultFileRenamePolicy();
			// 6. MultipartRequest 매개변수(5개)
			// HttpServletRequest, 저장 위치, 최대크기, 인코딩, Rename 규칙
			MultipartRequest mr 
				= new MultipartRequest(request, dir, maxSize, encoding, dfr);
			// 7. 파일명 정보
			String oriName = mr.getOriginalFileName("thumbnail"); // 기존 파일명
			String reName = mr.getFilesystemName("thumbnail"); // 바뀐 파일명
//			System.out.println(oriName + "->" +reName);
			String title = mr.getParameter("board_title");
			String content = mr.getParameter("board_content");
			String writer = mr.getParameter("board_writer");
			
			// Board 객체에 정보 담기
			Board b = new Board();
			b.setBoard_title(title);
			b.setBoard_content(content);
			b.setBoard_writer(writer);
			b.setOri_thumbnail(oriName);
			b.setNew_thumbnail(reName);
			
			int result = new BoardService().createBoard(b);
			RequestDispatcher view = request.getRequestDispatcher("/views/board/create_fail.jsp");
			if(result > 0) {
				view = request.getRequestDispatcher("/views/board/create_success.jsp");
			}
			view.forward(request, response);
			
		} else {
			response.sendRedirect("/board/create");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

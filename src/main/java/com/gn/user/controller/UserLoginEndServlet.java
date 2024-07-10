package com.gn.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gn.user.service.UserService;
import com.gn.user.vo.User;

@WebServlet(name="userLoginEnd",urlPatterns="/user/loginEnd")
public class UserLoginEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserLoginEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 -> 비밀번호 확인(사용자 입력 == 회원가입)
		// 회원가입 비밀번호 암호화 == 사용자 입력
		String id = request.getParameter("user_id"); // 사용자가 입력한 아이디
		String pw = request.getParameter("user_pw"); // 사용자가 입력한 비밀번호
		
//		User u = new UserService().loginUser(id,pw);
		// 1. User u = new UserService().loginUser(id, pw); 코드 주석 처리
		// 2. User 객체의 매개변수 생성자에 데이터베이스 실제 값을 넣어서 User 구성
		// 예시) new User(1, "admin", "암호화된 비밀번호", "관리자");
		// 3. 매개변수 생성자로 만든 객체 주석처리
		// 4. User u = null;
//		User u = new User(1, "user01", "parksomi7575*", "박소미");
		User u = new User(1, "user01", "1ARVn2Auq2/WAqx2gNrL+q3RNjAzXpUfCXrzkA6d4Xa22yhRLy4AC50E+6UTPoscbo31nbOoq51gvkuXzJ6B2w==","박소미");
//		User u = null;
		if(u != null) {
			HttpSession session = request.getSession(true);
			// 새 session 이거나 session 에 user 속성을 넣은 적이 없는 경우
			if(session.isNew() || session.getAttribute("user") == null) {
				session.setAttribute("user", u); // user 속성에 유저 정보 넣어줌
				session.setMaxInactiveInterval(60*30); // 'session'의 유효기간 (보통 30분)
			}
			response.sendRedirect("/"); // 지정한 페이지로 이동, ()안에 경로 작성
		} else {
			// 비밀번호가 틀렸다는 것을 알려주기 위해 이동
			RequestDispatcher view = request.getRequestDispatcher("/views/user/login_fail.jsp");
			view.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

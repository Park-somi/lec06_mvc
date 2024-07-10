package com.gn.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user/logout")
public class UserLogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserLogoutServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// false : HttpSession 이 존재하면 현재 HttpSession 을 반환, 존재하지 않으면 새로이 세션 생성 X, null 반환
		// <-> true : 존재하지 않으면 새로이 세션 생성 O
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("user") != null) {
			session.removeAttribute("user"); // session 안에 들어가있는 user 정보 제거
			session.invalidate(); // session 무효화
		}
		response.sendRedirect("/");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.gn.user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gn.common.sql.JDBCTemplate;
import com.gn.user.service.UserService;
import com.gn.user.vo.User;

@WebServlet(name="userCreateEnd",urlPatterns="/user/createEnd")
public class UserCreateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserCreateEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("user_id");
		String pw = request.getParameter("user_pw");
		String name = request.getParameter("user_name");
		
		User u = new User();
		u.setUser_id(id);
		u.setUser_pw(pw);
		u.setUser_name(name);
		
		int result = new UserService().createUser(u);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/user/create_fail.jsp");
		if(result > 0) {
			view = request.getRequestDispatcher("/views/user/create_success.jsp");
		} 
		view.forward(request,response);
		//JDBCTemplate.getConnection();
//		if(result > 0) {
//			System.out.println("성공");
//		} else {
//			System.out.println("실패");
//		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

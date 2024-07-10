package com.gn.user.service;

import java.sql.Connection;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.getConnection;

import com.gn.user.dao.UserDao;
import com.gn.user.vo.User;

public class UserService {

	public int createUser(User u) {
		Connection conn = getConnection();
		
		int result = new UserDao().createUser(u,conn);
		close(conn);
		return result;
	}
	
}

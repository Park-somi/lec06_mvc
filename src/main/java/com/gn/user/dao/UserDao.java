package com.gn.user.dao;

import static com.gn.common.sql.JDBCTemplate.close;
import static com.gn.common.sql.JDBCTemplate.commit;
import static com.gn.common.sql.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gn.user.vo.User;

public class UserDao {

	public int createUser(User u, Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			String sql1 = "SELECT COUNT(*) AS cnt FROM user WHERE user_id = ?";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, u.getUser_id());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("cnt"); // 0 ~
			}
			
			if(result == 0) {
				String sql2 = "INSERT INTO `user`(`user_id`,`user_pw`,`user_name`) "
						+ "VALUES(?,?,?)";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, u.getUser_id());
				pstmt.setString(2, u.getUser_pw());
				pstmt.setString(3, u.getUser_name());
				result = pstmt.executeUpdate(); // 0 ~ 1
			} else {
				result = 0;
				System.out.println("중복되는 이름이 있습니다.");
			}
			commit(conn);
			
		} catch (Exception e) {
			rollback(conn);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return result;
	}

}

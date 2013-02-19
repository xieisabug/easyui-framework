package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpSession;

import com.zhnjy.entity.User;

public class LoginDB {

	public static User login(String userName, String password) {
		String sql = "select * from  User where userName='"
				+ userName + "' and password='" + password + "';";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		User user = null;
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				int level = rs.getInt("level");
				String remark = rs.getString("remark");
				int siteId = rs.getInt("siteId");
				user = new User(id, userName, password, level, remark, siteId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
			DBHelper.close(stmt);
			DBHelper.close(rs);

		}
		return user;
	}
}

package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhnjy.entity.Course;

public class FilterDB {

	/**
	 * 根据level和Url判断该用户是否可以访问该路径。在数据库中保存的是不能访问的。
	 * 
	 * @return
	 */
	public static boolean isPermission(int level, String url) {
		boolean result = false;
		int id = getIdbyUrl(url);
		if(id == -1){
			return true;
		}else{
			String sql = "select * from Permissions where level="+level+" and urlId="+id;
			Connection conn = DBHelper.getConnection();
			Statement stmt = DBHelper.getStatement(conn);
			ResultSet rs = DBHelper.getResultSet(stmt, sql);

			try {
				if (rs.next()) {
					result = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn.close();
					stmt.close();
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	
	/**
	 * 根据传进来的URL得到该URL的id,返回-1说明该URL任何用户都可以访问
	 */
	public static int getIdbyUrl(String url) {
		
		String sql = "select id from Urls where url='" + url+"'";
		int id = -1;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

}

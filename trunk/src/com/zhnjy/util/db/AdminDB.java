package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.User;
import com.zhnjy.util.string.StaticVariable;

public class AdminDB {
	// 管理员添加方法。
	public static int add(User user) {
		int i = 0;
		String sql = "insert User(id,userName,password,level,remark,siteId) values(null,?,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getLevel());
			pstmt.setString(4, user.getRemark());
			pstmt.setInt(5, user.getSiteId());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;
	}

	// 管理员删除方法1,通过ID删除
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from User where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		try {
			i = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return i;
	}

	public static int update(User user) {
		int i = 0;
		String sql = "update User set userName=?,password=?,level=?,remark=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			pstmt.setInt(3, user.getLevel());
			pstmt.setString(4, user.getRemark());
			pstmt.setInt(5, user.getId());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close(conn);
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return i;
	}

	
	public static List<User> query() {

		List<User> list = new ArrayList<User>();
		String sql = "select * from User";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				int level = rs.getInt("level");
				String remark = rs.getString("remark");
				int siteId = rs.getInt("siteId");
				User user = new User(id, userName, password, level, remark,
						siteId);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public static List<User> getListByPage(int uLevel, int uSiteId){

		List<User> list = new ArrayList<User>();
		String sql = "select * from User";
		if(uLevel != 1) {
			sql += " where siteId=" + uSiteId;
			if(uLevel == 3 || uLevel == 4) {
				sql += " and level=" + uLevel;
			}
		}
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				int level = rs.getInt("level");
				String remark = rs.getString("remark");
				int siteId = rs.getInt("siteId");
				
				User user = new User(id, userName, password, level, remark, siteId);
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	// 通过Id得到单个Admin对象
	public static User getAdmin(int _id) {
		String sql = "select * from User where id=" + _id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		User user = null;
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String userName = rs.getString("userName");
				String password = rs.getString("password");
				int level = rs.getInt("level");
				String remark = rs.getString("remark");
				int siteId = rs.getInt("siteId");
				
				user = new User(id, userName, password, level, remark, siteId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;

	}
	
	//通过权限等级值获得权限等级的名称
	public static String getLevelName(int level) {
		String levelName = "";
		switch (level) {
		case 1:
			levelName = "超级管理员";
			break;
		case 2:
			levelName = "高级操作员";
			break;
		case 3:
			levelName = "站点管理员";
			break;
		case 4:
			levelName = "站点操作员";
			break;
		case 5:
			levelName = "财务管理员";
			break;
		case 6:
			levelName = "阅览账号";
			break;
		default:
			break;
		}
		
		return levelName;
	}
	
	
	public static int getPageCount(){
		int pageCount = 1;
		String sql = "select count(*) as count from User";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if(rs.next()) {
				pageCount = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(pageCount % StaticVariable.PAGE_SIZE == 0) {
			pageCount /= StaticVariable.PAGE_SIZE;
		} else {
			pageCount /= StaticVariable.PAGE_SIZE;
			pageCount++;
		}

		return pageCount;
	}
	
	/**
	 * 通过id得到名字,id不存在就返回“无”
	 */
	public static String getNameById(int id){
		String name = null;
		String sql = "select * from User where id="+id;
		
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if(rs.next()) {
				name = rs.getString("userName"); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return name;
	}
	
}



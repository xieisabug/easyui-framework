package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Course;
import com.zhnjy.entity.Message;
import com.zhnjy.util.string.StaticVariable;

public class MessageDB {

	
	public static int add(Message message){
		int  i = 0;
		String sql = "insert Message(id,title,content,time,operaterId)values(null,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, message.getTitle());
			pstmt.setString(2, message.getContent());
			pstmt.setString(3, message.getTime());
			pstmt.setInt(4, message.getOperaterId());
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
	
	/**
	 * 根据ID得到一个message
	 * 
	 * @return
	 */
	public static Message getMessage(int id) {

		Message message = null;
		String sql = "select * from Message where id=" + id;

		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = rs.getString("time");
				int operaterId = rs.getInt("operaterId");
				String operaterName = AdminDB.getNameById(operaterId);
				message = new Message(id, title, content, time, operaterId);
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
		return message;
	}

	/**
	 * 根据id删除一个公告
	 */

	public static int delete(int id) {
		int i = 0;

		String sql = "delete from Message where id=" + id;
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

	/**
	 * 根据id更新一个Message
	 * 
	 */

	public static int update(Message message) {
		int i = 0;
		String sql = "update Message set title=?,content=?,operaterId=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, message.getTitle());
			pstmt.setString(2, message.getContent());
			pstmt.setInt(3, message.getOperaterId());
			pstmt.setInt(4, message.getId());
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
	/**
	 * 获得数据库中的全部公告
	 */

	public static List getList() {
		List<Message> list = new ArrayList<Message>();
		String sql = "select * from Message";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = rs.getString("time");
				int operaterId = rs.getInt("operaterId");
				Message message = new Message(id, title, content, time,
						operaterId);
				list.add(message);
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
		return list;
	}

	/**
	 * 获得数据库中的全部公告
	 */

	public static List getListByPage(int page) {
		List<Message> list = new ArrayList<Message>();
		String sql = "select * from Message limit "+(page-1)*StaticVariable.PAGE_SIZE+","+StaticVariable.PAGE_SIZE;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String time = rs.getString("time");
				int operaterId = rs.getInt("operaterId");
				String operaterName = AdminDB.getNameById(operaterId);
				Message message = new Message(id, title, content, time, operaterId);
				list.add(message);
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
		return list;
	}
	
	public static int getPageCount(){
		int pageCount = 1;
		String sql = "select count(*) as count from Message";
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
}

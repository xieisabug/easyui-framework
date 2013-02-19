package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Site;
import com.zhnjy.util.string.StaticVariable;

public class SiteDB {

	// 站点添加方法。
	public static int add(Site site) {
		int i = 0;
		String sql = "insert Site(id,name) values(null,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, site.getName());
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

	// 站点删除方法1,通过ID删除
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from Site where id=" + id;
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
			}

		}
		return i;
	}

	public static int update(Site site) {
		int i = 0;
		String sql = "update Site set name=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, site.getName());
			pstmt.setInt(2, site.getId());
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
	 * 得到数据库全部的站点
	 * @param page 页数
	 * @return 
	 */
	public static List<Site> getListByPage(int page) {
		List<Site> list = new ArrayList<Site>();
		String sql = "select * from Site limit " + (page-1)*StaticVariable.PAGE_SIZE + ","+StaticVariable.PAGE_SIZE;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Site st = new Site(id, name);
				list.add(st);
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
	/**
	 * 通过参数获得数据的总页数
	 * @param name 课程名
	 * @param number 课程代码
	 * @param level 课程等级
	 * @return pageCount 数据的总页数
	 */
	public static int getPageCount(){
		int pageCount = 1;
		String sql = "select count(*) as count from Site";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if(rs.next()) {
				pageCount = rs.getInt("count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
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
	// 通过Id得到单个site对象
	public static Site getSite(int _id) {
		String sql = "select * from Site where id =" + _id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		Site st = null;
		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				st = new Site(id, name);
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
		return st;
	}
	
	//通过Id得到site的名字
	public static String getSiteNameById(int id) {
		Site site = getSite(id);
		return site.getName();
	}
	// 得到数据库全部的站点
	public static List<Site> query() {
		List<Site> list = new ArrayList<Site>();
		String sql = "select * from Site";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Site st = new Site(id, name);
				list.add(st);
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
}

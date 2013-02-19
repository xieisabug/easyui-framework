package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Major;
import com.zhnjy.util.string.StaticVariable;

public class MajorDB {

	// 添加专业到数据库
	public static int add(Major major) {
		int i = 0;
		
		
		if(isRepeat(major.getNumber())){
			i = 1;
			return i;
		}
		String sql = "insert Major(id,name,number,level) values(null,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, major.getName());
			pstmt.setString(2, major.getNumber());
			pstmt.setString(3, major.getLevel());
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
	 * 通过编码判断是否添加过
	 * @param id
	 * @return
	 */
	public static boolean isRepeat(String number){
		boolean bool = false;
		String sql = "select * from Major where number='"+number+"'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		
		try {
			if(rs.next()){
				bool = true;
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
		return bool;
	}

	// 根据ID删除一门专业的一条记录。
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from Major where id=" + id;
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

	// 根据id更新专业信息
	public static int update(Major major) {
		int i = 0;
		String sql = "update Major set name=?,number=?,level=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, major.getName());
			pstmt.setString(2, major.getNumber());
			pstmt.setString(3, major.getLevel());
			pstmt.setInt(4, major.getId());
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

	// 根据专业编码得到专业
	public static Major getMajor(String number) {
		Major major = null;
		String sql = "select * from Major where number='" + number + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				// String number = rs.getString("number");
				String level = rs.getString("level");
				major = new Major(id, name, number, level);
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
		return major;
	}

	// 根据专业ID得到一门专业
	public static Major getMajor(int id) {
		Major maj = null;
		String sql = "select* from Major where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if (rs.next()) {
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");
				maj = new Major(id, name, number, level);
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
		return maj;
	}

	// 专业的模糊查询
	public static List<Major> getList(String _name, String _level, String _number) {
		List<Major> list = new ArrayList<Major>();

		String sql = "select * from Major where name like '%" + _name
				+ "%' and number like '%" + _number + "%' and level like '%"
				+ _level + "%'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");

				Major maj = new Major(id, name, number, level);
				list.add(maj);
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
	
	//通过number的到id
	public static int getId(String number){
		String sql = "select id from Major where number='" +number+ "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		int id = -1;
		try {
			if(rs.next()){
				 id = rs.getInt("id");
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
		return id;
	}

	//通过Id得到NAME
	public static String getName(int id){
		String sql = "select name from Major where id=" +id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String name = null;
		try {
			if(rs.next()){
				 name = rs.getString("name");
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
	//通过Id得到number
	public static String getNumber(int id){
		String sql = "select number from Major where id=" +id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String number = null;
		try {
			if(rs.next()){
				 number = rs.getString("number");
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
		return number;
	}
	
	//通过number判断改专业是否存在
	public static boolean isHave(String number){
		String sql = "select id from Major where number='" +number+ "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		boolean bool = false;
		try {
			if(rs.next()){
				 bool = true;
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
		return bool;
	}
	/**
	 * 通过参数和页数得到专业列表
	 * @param _name 专业名字
	 * @param _level 专业层次
	 * @param _number 专业编码
	 * @param page 页数
	 * @return
	 */
	public static List<Major> getListByPage(String _name, String _level, String _number,int page) {
		
		List<Major> list = new ArrayList<Major>();
		String sql = "select * from Major where name like '%" + _name
				+ "%' and number like '%" + _number + "%' and level like '%"
				+ _level + "%' limit " + (page-1)*StaticVariable.PAGE_SIZE + ","+StaticVariable.PAGE_SIZE;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");

				Major maj = new Major(id, name, number, level);
				list.add(maj);
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
	 * 通过参数获得数据的总页数
	 * @param _name 专业名字
	 * @param _level专业层次
	 * @param _number专业编码
	 * @return
	 */
	public static int getPageCount(String _name, String _number, String _level){
		int pageCount = 1;
		String sql = "select count(*) as count from Major where name like '%" + _name
				+ "%' and number like '%" + _number + "%' and level like '%"
				+ _level + "%'";
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
}

package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.RecordItem;

public class RecordItemDB {

	/**
	 * 增加一项缴费项目。
	 * @param recordItem
	 * @return
	 */
	public static int add(RecordItem recordItem){
		int i = 0;
		String sql = "insert RecordItem(id,name) values(null,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1,recordItem.getName());
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
	 * 删除一项缴费项目
	 */
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from RecordItem where id=" + id;
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
	
	// 根据id更新缴费项目
	public static int update(RecordItem recordItem) {
		int i = 0;
		String sql = "update RecordItem set name=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, recordItem.getName());
			pstmt.setInt(2,recordItem.getId());
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
	 * 根据名字模糊查询项目
	 */
	public static List<RecordItem> getList(String _name) {
		
		List<RecordItem> list = new ArrayList<RecordItem>();
		String sql = "select * from RecordItem where name like '%" + _name
				+ "%'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				RecordItem rt = new RecordItem(id, name);
				list.add(rt);
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
	 * 得到全部的缴费项目
	 */
	
	public static List<RecordItem> getAll(){
		List<RecordItem> list = new ArrayList<RecordItem>();
		String sql = "select * from RecordItem";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				RecordItem rt = new RecordItem(id, name);
				list.add(rt);
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
	 * 通过id得到记录
	 *
	 */
	public static RecordItem getRecordItem(int id){
		String sql = "select * from RecordItem where id="+id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		RecordItem rt = null;
		try {
			if(rs.next()) {
				String name = rs.getString("name");
				rt = new RecordItem(id, name);
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
		return rt;
	}
	/**
	 * 通过id得到name
	 */
	public static String getNameById(int id){
		String sql = "select * from RecordItem where id="+id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String name = null;
		try {
			if(rs.next()) {
				name = rs.getString("name");
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
		return name;
	}
	
	/**
	 * 通过name得到id 
	 */
	public static int getIdByName(String name){
		String sql = "select * from RecordItem where name='"+name+"'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		int id = -1;
		try {
			if(rs.next()) {
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

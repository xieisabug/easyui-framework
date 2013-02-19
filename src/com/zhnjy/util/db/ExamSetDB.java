package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zhnjy.entity.Course;

public class ExamSetDB {
	
	//设置考期
	public static int examDate(String examDate){
		int i = 0;
		String sql = "update Property set value=? where name=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1,examDate);
			pstmt.setString(2,"examDate");
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

	//设置单元数
	public static int unitNum(String unitNum){
		int i = 0;
		String sql = "update Property set value=? where name=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1,unitNum);
			pstmt.setString(2,"unitNum");
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
	
	//得到考期
	public static String getExamDate() {
		String sql = "select * from Property where name='examDate'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String examDate = null;
		try {
			if (rs.next()) {
				examDate = rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return examDate ;
	}
	
	//得到单元数
	public static String getUnitNum() {
		String sql = "select * from Property where name='unitNum'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String unitNum = null;
		try {
			if (rs.next()) {
				unitNum = rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return unitNum;
	}
	//增加一个name和value
	public static int add(String name,String value){
		int i = 0;
		String sql = "insert Property(name,value) values(?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, name);
			pstmt.setString(2, value);
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
	//更新一个叫name的value
	public static int update(String name,String value){
		int i = 0;
		String sql = "update Property set value=? where name=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1,value);
			pstmt.setString(2,name);
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
	
	//删除一个叫name的记录
	public static int delete(String name){
		int i=0;
		String sql = "delete from Property where name='" + name+"'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		try {
			stmt.executeUpdate(sql);
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
		return 0;
	}
	//删除所有的day
	public static boolean deleteDay(int k){
		boolean bo = true;
		for(int i = 1; i <= k; i++){
			if(delete("day"+i) == 0){
				bo = false;
			}
		}
		return bo;
	}
	//删除所有的unit
	public static boolean deleteUnit(int k){
		boolean bo = true;
		for(int i = 1; i <= k; i++){
			if(delete("unit"+i+"time") == 0){
				bo = false;
			}
		}
		return bo;
	}
	
	//得到一个叫name的value
	public static String get(String name){
		String value = null;
		String sql = "select * from Property where name='"+name+"'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String unitNum = null;
		try {
			if (rs.next()) {
				value= rs.getString("value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return value;
	}
}



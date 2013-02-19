package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Major;

public class SchoolMajorDB {

	public static int add(int schoolId, int majorId) {

		int i = 0;

		if (isRepeat(schoolId, majorId)) {
			i = 1;
			return i;
		}

		String sql = "insert School_major(id,schoolId,majorId)values(null,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setInt(1, schoolId);
			pstmt.setInt(2, majorId);
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

	// 通过传入的学校的id得到该专业的所有专业的实体类。
	public static List<Major> getMajorlist(int schoolId) {
		List<Major> list = new ArrayList<Major>();
		String sql = "select majorId from School_major where schoolId="
				+ schoolId;
		Major major = null;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int majorId = rs.getInt("majorId");
				major = MajorDB.getMajor(majorId);
				list.add(major);
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

	// 通过传入的majorId和schoolId删除该门专业属于该学校

	public static int delete(int schoolId, int majorId) {
		int i = 0;
		String sql = "delete from School_major where majorId=" + majorId
				+ " and schoolId=" + schoolId;
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

	// 通过传入的学校的id和专业Id判断是否已经添加。
	public static boolean isRepeat(int schoolId, int majorId) {
		String sql = "select * from School_major where schoolId=" + schoolId
				+ " and majorId=" + majorId;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		boolean bool = false;
		try {
			if (rs.next()) {
				bool = true;
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

		return bool;
	}

}

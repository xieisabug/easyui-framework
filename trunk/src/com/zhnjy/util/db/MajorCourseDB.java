package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Course;

public class MajorCourseDB {

	/**
	 * 通过传入的专业的id得到该专业的所有课程的实体类
	 * 
	 * @param majorId
	 * @return
	 */
	public static List getCourselist(int majorId) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Major_course where majorId=" + majorId;
		Course course = null;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int courseId = rs.getInt("courseId");
				int flag = rs.getInt("flag");
				int unit = rs.getInt("unit");
				course = CourseDB.getCourse(courseId);
				course.setFlag(flag);
				course.setUnit(unit);
				list.add(course);
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

	// 通过传入的majorId和courseId设置该门课程属于该门专业

	public static int add(int majorId, int courseId) {
		int i = 0;

		if(isRepeat(majorId, courseId)){
			i = 1 ;
			return i;
		}
		String sql = "insert Major_course(id,majorId,courseId) values(null,?,?)";

		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setInt(1, majorId);
			pstmt.setInt(2, courseId);
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

	// 通过传入的majorId和courseId删除该门课程属于该门专业

	public static int delete(int majorId, int courseId) {
		int i = 0;
		String sql = "delete from Major_course where majorId=" + majorId
				+ " and courseId=" + courseId;
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

	// 设置该课程是否可以报考
	public static void set(int majorId, int courseId, int flag, int unit) {
		String sql = "update Major_course set flag=?,unit=? where majorId=? and courseId=?";

		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);

		try {
			pstmt.setInt(1, flag);
			pstmt.setInt(2, unit);
			pstmt.setInt(3, majorId);
			pstmt.setInt(4, courseId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 通过传入的专业的id得到该专业的所有可以报考的课程。
	public static List<Course> getSelectableCourse(int majorId,String idCard) {
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Major_course where majorId=" + majorId
				+ " and flag=1";
		Course course = null;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int courseId = rs.getInt("courseId");
				int unit = rs.getInt("unit");
				course = CourseDB.getCourse(courseId);
				course.setUnit(unit);
				//判断改学生是不是已经通过这门课。
				if(!GradeDB.isPass(idCard,course.getNumber())){
					list.add(course);
				}
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
	 * 通过单元号得到所有课程的实体
	 */
	public static List<Course> getCourseIdByUnit(int unit) {
		List<Course> list = new ArrayList<Course>();
		Course course = null;
		String sql = "select courseId from Major_course where unit=" + unit;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("courseId");
				course = CourseDB.getCourse(id);
				list.add(course);
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
	
	// 通过传入的majorId和courseId判断是否重复设置专业下的课程
	public static boolean isRepeat(int majorId, int courseId) {
		boolean bool = false;
		String sql = "select * from Major_course where majorId=" + majorId
				+ " and courseId=" + courseId;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if(rs.next()) {
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

	public static boolean isSelected(List<Course> courses,String idCard){
		for(int i = 0; i < courses.size(); i++){
			Course course = courses.get(i);
			if(ExamItemDB.isSelect(idCard, course.getId()))
				return true;
		}
		return false;
	}
}

package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Grade;
import com.zhnjy.entity.Student;
import com.zhnjy.util.string.StaticVariable;

public class GradeDB {
	// 通过考籍号得到该学生的身份证号码
	public static String getIdCard(String stuNumber) {
		String sql = "select idCard from Student where bExamNumber='"
				+ stuNumber+"'";
		//System.out.println(sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String idCard = null;
		boolean bool = false;
		try {
			if (rs.next()) {
				idCard = rs.getString("idCard");
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
		// 如果在专科考籍号中没找到，则在本科考籍号中找；
		if (bool == false) {
			String sql2 = "select idCard from Student where zExamNumber="
					+ stuNumber;
			Connection conn2 = DBHelper.getConnection();
			Statement stmt2 = DBHelper.getStatement(conn2);
			ResultSet rs2 = DBHelper.getResultSet(stmt2, sql2);
			try {
				if (rs2.next()) {
					idCard = rs2.getString("idCard");
					bool = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					conn2.close();
					stmt2.close();
					rs2.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return idCard;
	}

	public static int add(Grade grade) {
		int i = 0;
		String sql = "insert Grade(id,idCard,grade,courseNumber,time,operaterId) values(null,?,?,?,?,?)";
		//System.out.println(sql);
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, grade.getStuIdCard());
			pstmt.setString(2, grade.getGrade());
			pstmt.setString(3, grade.getCourseNumber());
			pstmt.setString(4, grade.getTime());
			pstmt.setInt(5, grade.getOperaterId());
			i = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i;

	}
	
	public static int getPageCount(String _idCard, String _name, String _number,
			String _nationality, String _sex, String _phone1, String _phone2,
			String _siteId, String _studyLevel, String _bSchoolId,
			String _bMajorId, String _bExamNumber, String _bStudyForm,
			String _bStudyType, String _bStatus, String _bJoinTime,
			String _zSchoolId, String _zMajorId, String _zExamNumber,
			String _zStudyForm, String _zStudyType, String _zStatus,
			String _zJoinTime){
		if(_idCard == null){
			_idCard = "";
		}
		if(_name == null){
			_name = "";
		}
		if(_number == null){
			_number = "";
		}
		if(_nationality == null){
			_nationality = "";
		}
		if(_sex == null){
			_sex = "";
		}
		if(_phone1 == null){
			_phone1 = "";
		}
		if(_phone2 == null){
			_phone2 = "";
		}
		if(_studyLevel == null){
			_studyLevel = "";
		}
		if(_bExamNumber == null){
			_bExamNumber = "";
		}
		if(_bStudyForm == null){
			_bStudyForm = "";
		}
		if(_bStudyType == null){
			_bStudyType = "";
		}
		if(_bStatus == null){
			_bStatus = "";
		}
		if(_bJoinTime == null){
			_bJoinTime = "";
		}
		if(_zStudyForm == null){
			_zStudyForm = "";
		}
		if(_zStudyType == null){
			_zStudyType = "";
		}
		if(_zStatus == null){
			_zStatus = "";
		}
		if(_zExamNumber == null){
			_zExamNumber = "";
		}
		if(_zJoinTime == null){
			_zJoinTime = "";
		}
		if (_bSchoolId == null ||_bSchoolId.equals("")) {
			_bSchoolId = ">=0";
		} else {
			_bSchoolId = "=" + _bSchoolId;
		}

		if (_bMajorId == null || _bMajorId.equals("")) {
			_bMajorId = ">=0";
		} else {
			_bMajorId = "=" + _bMajorId;
		}

		if ( _zSchoolId == null || _zSchoolId.equals("")) {
			_zSchoolId = ">=0";
		} else {
			_zSchoolId = "=" + _zSchoolId;
		}

		if ( _zMajorId == null || _zMajorId.equals("")) {
			_zMajorId = ">=0";
		} else {
			_zMajorId = "=" + _zMajorId;
		}

		if ( _siteId == null|| _siteId.equals("")) {
			_siteId = ">=0";
		} else {
			_siteId = "=" + _siteId;
		}
		int pageCount = 1;
		String sql = "select count(*) as count from Student where idCard like '%" + _idCard
				+ "%' and name like '%" + _name + "%' and number like '%"
				+ _number + "%' and nationality like '%" + _nationality
				+ "%' and sex like '%" + _sex + "%' and phone1 like '%"
				+ _phone1 + "%' and phone2 like '%" + _phone2 + "%' and siteId"
				+ _siteId + " and bSchoolId" + _bSchoolId + " and bMajorId"
				+ _bMajorId + " and bExamNumber like '%" + _bExamNumber
				+ "%' and bStudyForm like '%" + _bStudyForm
				+ "%' and bStudyType like '%" + _bStudyType
				+ "%' and bStatus like '%" + _bStatus
				+ "%' and bJoinTime like '%" + _bJoinTime + "%' and zSchoolId"
				+ _zSchoolId + " and zMajorId" + _zMajorId
				+ " and zExamNumber like '%" + _zExamNumber
				+ "%' and zStudyForm like '%" + _zStudyForm
				+ "%' and zStudyType like '%" + _zStudyType
				+ "%' and zStatus like '%" + _zStatus
				+ "%' and studyLevel like '%" + _studyLevel + "%' and zJoinTime like '%"+_zJoinTime+"%'";
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
		if(pageCount % 5 == 0) {
			pageCount /= 5;
		} else {
			pageCount /= 5;
			pageCount++;
		}

		return pageCount;
	}
	
	public static List<Grade> getGradesByIdCard(String idCard) {
		
		List<Grade> grades = new ArrayList<Grade>();
		String sql = "select * from grade where idCard='" + idCard + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		
		try {
			while(rs.next()) {
				int id = rs.getInt("id");
				String stuIdCard = rs.getString("idCard");
				String grade = rs.getString("grade");
				String courseNumber = rs.getString("courseNumber");
				String time = rs.getString("time");
				int operaterId = rs.getInt("operaterId");
				Grade g = new Grade(id, stuIdCard, grade, courseNumber, time, operaterId);
				grades.add(g);
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
		
		return grades;
	}

	// 根据id更新学生成绩
	public static int update(Grade grade) {
		int i = 0;
		String sql = "update Grade set grade=? where idCard=? and courseNumber=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, grade.getGrade());
			pstmt.setString(2,grade.getStuIdCard());
			pstmt.setString(3, grade.getCourseNumber());
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
	 * 通过学生的身份证号码和课程编码判断改学生是否通过该们课程。
	 */
	public static boolean isPass(String idCard, String number) {
		boolean bool = false;
		String sql = "select * from Grade where idCard='" + idCard
				+ "' and courseNumber='" + number + "'";
		//System.out.println("sql" + sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

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

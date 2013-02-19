package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Student;
import com.zhnjy.entity.User;

public class ExamItemDB {
	/**
	 * 根据身份证号码和考期得到该学生这次考期报考的科目。
	 */
	public static List getList(String idCard,String examDate){
		
		String sql = "select * from ExamItem where idCard='"+idCard+"' and examDate='"+examDate+"'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		List list = new ArrayList<String>();
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			while(rs.next()){
				String unit = rs.getString("unit");
				int courseId = rs.getInt("courseId");
				list.add(Integer.parseInt(unit), CourseDB.getNumber(courseId));
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
		return list;
	}
	
	/**
	 * 把学生选课的信息存入数据库
	 */
	public static int add(String idCard,int courseId,int unit,String examDate,int operatorId ){
		int i = 0;
		String delete = "delete from ExamItem where idCard='" + idCard + "' and unit=" + unit + 
						" and examDate='" + examDate + "'"; 
		String sql = "insert into ExamItem(idCard,courseId,unit,examDate,operaterId) values(?,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		try {
			if(stmt.executeUpdate(delete) < 0) {
				stmt.close();
				conn.close();
				return 0;
			}
			stmt.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, idCard);
			pstmt.setInt(2, courseId);
			pstmt.setInt(3, unit);
			pstmt.setString(4, examDate);
			pstmt.setInt(5, operatorId);
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
	 * 通过课程id得到报考了这门课程的所有学生
	 */
	public static List<Student> getStudentByCourseId(int courseId,User user){
		List<Student> list = new ArrayList<Student>();
		String sql = "select idCard from ExamItem where courseId="+courseId; 
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			while(rs.next()){
				String idCard = rs.getString("idCard");
				Student student = StudentDB.getStudent(idCard);
				//判断该用户是否有权限查看改学生
				if(user.getLevel() == 1 || user.getLevel() == 2){
					list.add(student);	
				}else{
					if(student.getSiteId() == user.getSiteId()){
						list.add(student);
					}
				}	
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
		
	return list;
	
	}
	
	/**
	 * 通过学生的身份证号码判断该学生在本次考期是否已经选了这门课
	 */
	public static boolean isSelect(String idCard,int courseId){
		boolean bool = false;
		String examDate = ExamSetDB.getExamDate();
		String sql = "select * from ExamItem where idCard='"+idCard+"' and examDate='"+examDate+"' and courseId="+courseId;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		Student student = null;
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
	
}

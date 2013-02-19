package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Student;
import com.zhnjy.entity.User;

public class SingleCountDB {
	public static List<Student> getListByPage(User user, int courseId, int page) {
		List<Student> students = new ArrayList<Student>();
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
					students.add(student);	
				}else{
					if(student.getSiteId() == user.getSiteId()){
						students.add(student);
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
		return students;
	}
}

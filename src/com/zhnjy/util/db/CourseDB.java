package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Course;
import com.zhnjy.entity.Major;
import com.zhnjy.util.string.StaticVariable;

public class CourseDB {
	// 添加课程到数据库
	public static int add(Course course) {
		int i = 0;
		
		if(isRepeat(course.getNumber())){
			i = 1;
			return i;
		}
		
		String sql = "insert Course(id,name,number,level,remark) values(?,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setInt(1, course.getId());
			pstmt.setString(2, course.getName());
			pstmt.setString(3, course.getNumber());
			pstmt.setString(4, course.getLevel());
			pstmt.setString(5, course.getRemark());
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
		String sql = "select * from Course where number='"+number+"'";
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
		String sql = "delete from Course where id=" + id;
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

	// 根据id更新课程信息
	public static int update(Course course) {
		int i = 0;
		String sql = "update Course set name=?,number=?,level=?,remark=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, course.getName());
			pstmt.setString(2, course.getNumber());
			pstmt.setString(3, course.getLevel());
			pstmt.setString(4, course.getRemark());
			pstmt.setInt(5, course.getId());
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

	// 模糊查询课程
	public static List getList(String _name,
			String _number, String _level) {
		
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Course  where  name like '%" + _name
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
				String remark = rs.getString("remark");

				Course cou = new Course(id, name, number, level, remark);
				list.add(cou);
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

	// 根据ID得到一门课程
	public static Course getCourse(int id) {
		Course cou = null;
		String sql = "select * from Course where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if (rs.next()) {
				// int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");
				String remark = rs.getString("remark");
				cou = new Course(id, name, number, level, remark);
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
		return cou;
	}

	// 根据身份证和课程编码判断是否有成绩
	public static boolean hasGrade(String idCard, String number) {
		String sql = "select * from Grade where idCard='" + idCard
				+ "' and courseNumber='" + number + "'";
		boolean result = false;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			if (rs.next()) {
				result = true;
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
		return result;
	}

	// 得到本科专业列表
	public static List getbSchoolMajorList() {
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Course where level='bk'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");
				String remark = rs.getString("remark");
				Course cou = new Course(id, name, number, level, remark);

				list.add(cou);
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

	// 得到专科专业列表
	public static List getzSchoolMajorList() {
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Course where level='zk'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");
				String remark = rs.getString("remark");

				Course cou = new Course(id, name, number, level, remark);
				list.add(cou);
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

	// 通过number的到id
	public static int getId(String number) {
		String sql = "select id from Course where number='" + number + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		int id = -1;
		try {
			if (rs.next()) {
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

	/**
	 * 通过id得到number;
	 * 
	 * @param number
	 * @return
	 */
	public static String getNumber(int id) {
		String sql = "select number from Course where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String number = null;
		try {
			if (rs.next()) {
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

	// 通过number判断课程是否存在
	public static boolean isHave(String number) {
		String sql = "select id from Course where number='" + number + "'";
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
	
	/**新！！
	 * 通过几个参数和页码获取课程列表
	 * @param _name 课程名
	 * @param _number 课程代码
	 * @param _level 课程等级
	 * @param page 页码
	 * @return 符合条件的课程列表
	 */
	public static List<Course> getListByPage(String _name,
		String _number, String _level, int page, int rows) {
		
		List<Course> list = new ArrayList<Course>();
		String sql = "select * from Course where  name like '%"
				+ _name + "%' and number like '%" + _number + "%' and level like '%"
				+ _level + "%' limit " + (page-1)*rows + "," + rows;
//System.out.println(sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String number = rs.getString("number");
				String level = rs.getString("level");
				String remark = rs.getString("remark");

				Course cou = new Course(id, name, number, level, remark);
				list.add(cou);
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
	
	/**新！！
	 * 通过参数获得数据的总页数
	 * @param name 课程名
	 * @param number 课程代码
	 * @param level 课程等级
	 * @return pageCount 数据的总页数
	 */
	public static int getPageCount(String name, String number, String level){
		int pageCount = 1;
		String sql = "select count(*) as count from Course where  name like '%"
				+ name + "%' and number like '%" + number + "%' and level like '%"
				+ level + "%'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if(rs.next()) {
				pageCount = rs.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		/*
		if(pageCount % StaticVariable.PAGE_SIZE == 0) {
			pageCount /= StaticVariable.PAGE_SIZE;
		} else {
			pageCount /= StaticVariable.PAGE_SIZE;
			pageCount++;
		}
		*/

		return pageCount;
	}
}

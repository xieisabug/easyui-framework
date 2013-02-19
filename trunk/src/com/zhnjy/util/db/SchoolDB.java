package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.School;
import com.zhnjy.util.string.StaticVariable;

public class SchoolDB {

	// 添加学校到数据库
	public static int add(School school) {
		int i = 0;

		if (isRepeat(school.getName(), school.getLevel())) {
			i = 1;
			return i;
		}
		String sql = "insert School(id,name,level) values(?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setInt(1, school.getId());
			pstmt.setString(2, school.getName());
			pstmt.setString(3, school.getLevel());
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
	 * 通过名字和层次判断是否添加过
	 * 
	 * @param id
	 * @return
	 */
	public static boolean isRepeat(String name, String level) {
		boolean bool = false;
		String sql = "select * from School where name='" + name
				+ "' and level = '" + level + "'";
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

	// 根据ID删除一所学校的一条记录。
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from School where id=" + id;
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

	// 根据更新学校信息
	public static int update(School school) {
		int i = 0;
		String sql = "update School set name=?,level=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, school.getName());
			pstmt.setString(2, school.getLevel());
			pstmt.setInt(3, school.getId());
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
	 *  根据名字和级别来查询学校
	 * @param _name 学校
	 * @param _level 学校层次
	 * @param page页数
	 * @return
	 */
	public static List<School> getListByPage(String _name, String _level,int page) {
		List<School> list = new ArrayList<School>();
		String sql = "select * from School where name like '%" + _name
				+ "%' and level like '%" + _level + "%' limit " + (page - 1)
				* StaticVariable.PAGE_SIZE + "," + StaticVariable.PAGE_SIZE;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				School sch = new School(id, name, level);
				list.add(sch);
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
	 * 得到总页数
	 * @param _name 学校名字
	 * @param _level 学校层次
	 * @return
	 */
	public static int getPageCount(String _name, String _level){
		int pageCount = 1;
		String sql = "select count(*) as count from School where name like '%" + _name
				+ "%' and level like '%" + _level + "%'";
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
	// 根据id得到一所学校的全部信息
	public static School getSchool(int id) {
		String sql = "select * from School where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		School sch = null;
		try {
			if (rs.next()) {
				String name = rs.getString("name");
				String level = rs.getString("level");
				sch = new School(id, name, level);
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
		return sch;
	}

	// 得到本科学校列表
	public static List<School> getbSchoolList() {
		List<School> list = new ArrayList<School>();
		String sql = "select * from School where level='bk'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				School sch = new School(id, name, level);
				list.add(sch);
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

	// 得到专科学校列表
	public static List<School> getzSchoolList() {
		List<School> list = new ArrayList<School>();
		String sql = "select * from School where level='zk'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				School sch = new School(id, name, level);
				list.add(sch);
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
	 * 得到所有学校列表
	 * 
	 * @return
	 */
	public static List<School> getAllSchoolList() {
		List<School> list = new ArrayList<School>();
		String sql = "select * from School";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				School sch = new School(id, name, level);
				list.add(sch);
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
	 * 通过number的到id
	 * 
	 * @param number
	 * @return
	 */
	public static int getId(String number) {
		String sql = "select id from School where number='" + number + "'";
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

	/**
	 * 通过学校的id判断是否是本科
	 * 
	 * @param schoolId
	 * @return
	 */
	public static boolean isBk(int schoolId) {
		String sql = "select level from School where id=" + schoolId;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		boolean bo = false;
		try {
			if (rs.next()) {
				String level = rs.getString("level");
				if (level.equals("BK")) {
					bo = true;
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
		return bo;
	}

	// 通过Id得到学校的名字
	public static String getSchoolNameById(int id) {
		School school = getSchool(id);
		return school.getName();
	}
	//根据名字和级别来查询学校
	public static List<School> getList(String _name,String _level){
		List<School> list = new ArrayList<School>();
		String sql = "select * from School where name like '%"+_name+"%' and level like '%"+_level+"%'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		
		try {
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String level = rs.getString("level");
				School sch = new School(id, name, level);
				list.add(sch);
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

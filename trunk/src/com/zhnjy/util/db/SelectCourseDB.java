package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zhnjy.entity.Course;
import com.zhnjy.entity.SelectCourse;
import com.zhnjy.entity.Student;

public class SelectCourseDB {

	public static List<SelectCourse> getListByPage(String _idCard,
			String _name, String _number, String _nationality, String _sex,
			String _phone1, String _phone2, String _siteId, String _studyLevel,
			String _bSchoolId, String _bMajorId, String _bExamNumber,
			String _bStudyForm, String _bStudyType, String _bStatus,
			String _bJoinTime, String _zSchoolId, String _zMajorId,
			String _zExamNumber, String _zStudyForm, String _zStudyType,
			String _zStatus, String _zJoinTime, int page, int rows) {

		List<SelectCourse> datas = new ArrayList<SelectCourse>();
		// 处理int类型在模糊查询里的特殊。

		if (_idCard == null) {
			_idCard = "";
		}
		if (_name == null) {
			_name = "";
		}
		if (_number == null) {
			_number = "";
		}
		if (_nationality == null) {
			_nationality = "";
		}
		if (_sex == null) {
			_sex = "";
		}
		if (_phone1 == null) {
			_phone1 = "";
		}
		if (_phone2 == null) {
			_phone2 = "";
		}
		if (_studyLevel == null) {
			_studyLevel = "";
		}
		if (_bExamNumber == null) {
			_bExamNumber = "";
		}
		if (_bStudyForm == null) {
			_bStudyForm = "";
		}
		if (_bStudyType == null) {
			_bStudyType = "";
		}
		if (_zStudyForm == null) {
			_zStudyForm = "";
		}
		if (_zStudyType == null) {
			_zStudyType = "";
		}
		if (_zStatus == null) {
			_zStatus = "";
		}
		if (_bStatus == null) {
			_bStatus = "";
		}
		if (_bJoinTime == null) {
			_bJoinTime = "";
		}
		if (_zExamNumber == null) {
			_zExamNumber = "";
		}
		if (_zJoinTime == null) {
			_zJoinTime = "";
		}

		if (_bSchoolId == null || _bSchoolId.equals("")) {
			_bSchoolId = ">=0";
		} else {
			_bSchoolId = "=" + _bSchoolId;
		}

		if (_bMajorId == null || _bMajorId.equals("")) {
			_bMajorId = ">=0";
		} else {
			_bMajorId = "=" + _bMajorId;
		}

		if (_zSchoolId == null || _zSchoolId.equals("")) {
			_zSchoolId = ">=0";
		} else {
			_zSchoolId = "=" + _zSchoolId;
		}

		if (_zMajorId == null || _zMajorId.equals("")) {
			_zMajorId = ">=0";
		} else {
			_zMajorId = "=" + _zMajorId;
		}

		if (_siteId == null || _siteId.equals("")) {
			_siteId = ">=0";
		} else {
			_siteId = "=" + _siteId;
		}

		String sql = "select distinct Student.idCard from Student,examitem where Student.idCard=examitem.idCard "
				+ "and Student.idCard like '%"
				+ _idCard
				+ "%' and name like '%"
				+ _name
				+ "%' and number like '%"
				+ _number
				+ "%' and nationality like '%"
				+ _nationality
				+ "%' and sex like '%"
				+ _sex
				+ "%' and phone1 like '%"
				+ _phone1
				+ "%' and phone2 like '%"
				+ _phone2
				+ "%' and siteId"
				+ _siteId
				+ " and bSchoolId"
				+ _bSchoolId
				+ " and bMajorId"
				+ _bMajorId
				+ " and bExamNumber like '%"
				+ _bExamNumber
				+ "%' and bStudyForm like '%"
				+ _bStudyForm
				+ "%' and bStudyType like '%"
				+ _bStudyType
				+ "%' and bStatus like '%"
				+ _bStatus
				+ "%' and bJoinTime like '%"
				+ _bJoinTime
				+ "%' and zSchoolId"
				+ _zSchoolId
				+ " and zMajorId"
				+ _zMajorId
				+ " and zExamNumber like '%"
				+ _zExamNumber
				+ "%' and zStudyForm like '%"
				+ _zStudyForm
				+ "%' and zStudyType like '%"
				+ _zStudyType
				+ "%' and zStatus like '%"
				+ _zStatus
				+ "%' and studyLevel like '%"
				+ _studyLevel
				+ "%' and zJoinTime like '%"
				+ _zJoinTime
				+ "%'"
				+ " limit "
				+ (page - 1) * rows + "," + rows;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				String idCard = rs.getString("idCard");

				String sql2 = "select * from examitem where idCard='" + idCard
						+ "'";
				Statement stmt2 = DBHelper.getStatement(conn);
				ResultSet rs2 = DBHelper.getResultSet(stmt2, sql2);

				String examDate = ExamSetDB.getExamDate();
				SelectCourse sc = null;
				String firstUnit = "无记录";
				String secondUnit = "无记录";
				String thirdUnit = "无记录";
				String fourthUnit = "无记录";
				String fifthUnit = "无记录";
				String sixthUnit = "无记录";
				while (rs2.next()) {
					Course c = CourseDB.getCourse(rs2.getInt("courseId"));
					int unit = rs2.getInt("unit");
					switch (unit) {
					case 1:
						firstUnit = c.getNumber() + " " + c.getName();
						break;
					case 2:
						secondUnit = c.getNumber() + " " + c.getName();
						break;
					case 3:
						thirdUnit = c.getNumber() + " " + c.getName();
						break;
					case 4:
						fourthUnit = c.getNumber() + " " + c.getName();
						break;
					case 5:
						fifthUnit = c.getNumber() + " " + c.getName();
						break;
					case 6:
						sixthUnit = c.getNumber() + " " + c.getName();
						break;
					default:
						break;
					}
				}
				sc = new SelectCourse(idCard, firstUnit, secondUnit, thirdUnit, fourthUnit, fifthUnit, sixthUnit, examDate);
				datas.add(sc);
				rs2.close();
				stmt2.close();
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

		return datas;
	}

	public static int getPageCount(String _idCard, String _name,
			String _number, String _nationality, String _sex, String _phone1,
			String _phone2, String _siteId, String _studyLevel,
			String _bSchoolId, String _bMajorId, String _bExamNumber,
			String _bStudyForm, String _bStudyType, String _bStatus,
			String _bJoinTime, String _zSchoolId, String _zMajorId,
			String _zExamNumber, String _zStudyForm, String _zStudyType,
			String _zStatus, String _zJoinTime) {

		if (_idCard == null) {
			_idCard = "";
		}
		if (_name == null) {
			_name = "";
		}
		if (_number == null) {
			_number = "";
		}
		if (_nationality == null) {
			_nationality = "";
		}
		if (_sex == null) {
			_sex = "";
		}
		if (_phone1 == null) {
			_phone1 = "";
		}
		if (_phone2 == null) {
			_phone2 = "";
		}
		if (_studyLevel == null) {
			_studyLevel = "";
		}
		if (_bExamNumber == null) {
			_bExamNumber = "";
		}
		if (_bStudyForm == null) {
			_bStudyForm = "";
		}
		if (_bStudyType == null) {
			_bStudyType = "";
		}
		if (_bStatus == null) {
			_bStatus = "";
		}
		if (_bJoinTime == null) {
			_bJoinTime = "";
		}
		if (_zStudyForm == null) {
			_zStudyForm = "";
		}
		if (_zStudyType == null) {
			_zStudyType = "";
		}
		if (_zStatus == null) {
			_zStatus = "";
		}
		if (_zExamNumber == null) {
			_zExamNumber = "";
		}
		if (_zJoinTime == null) {
			_zJoinTime = "";
		}
		if (_bSchoolId == null || _bSchoolId.equals("")) {
			_bSchoolId = ">=0";
		} else {
			_bSchoolId = "=" + _bSchoolId;
		}

		if (_bMajorId == null || _bMajorId.equals("")) {
			_bMajorId = ">=0";
		} else {
			_bMajorId = "=" + _bMajorId;
		}

		if (_zSchoolId == null || _zSchoolId.equals("")) {
			_zSchoolId = ">=0";
		} else {
			_zSchoolId = "=" + _zSchoolId;
		}

		if (_zMajorId == null || _zMajorId.equals("")) {
			_zMajorId = ">=0";
		} else {
			_zMajorId = "=" + _zMajorId;
		}

		if (_siteId == null || _siteId.equals("")) {
			_siteId = ">=0";
		} else {
			_siteId = "=" + _siteId;
		}
		int pageCount = 1;
		String sql = "select count(distinct Student.idCard) as count from Student,examitem where Student.idCard=examitem.idCard "
				+ "and Student.idCard like '%"
				+ _idCard
				+ "%' and name like '%"
				+ _name
				+ "%' and number like '%"
				+ _number
				+ "%' and nationality like '%"
				+ _nationality
				+ "%' and sex like '%"
				+ _sex
				+ "%' and phone1 like '%"
				+ _phone1
				+ "%' and phone2 like '%"
				+ _phone2
				+ "%' and siteId"
				+ _siteId
				+ " and bSchoolId"
				+ _bSchoolId
				+ " and bMajorId"
				+ _bMajorId
				+ " and bExamNumber like '%"
				+ _bExamNumber
				+ "%' and bStudyForm like '%"
				+ _bStudyForm
				+ "%' and bStudyType like '%"
				+ _bStudyType
				+ "%' and bStatus like '%"
				+ _bStatus
				+ "%' and bJoinTime like '%"
				+ _bJoinTime
				+ "%' and zSchoolId"
				+ _zSchoolId
				+ " and zMajorId"
				+ _zMajorId
				+ " and zExamNumber like '%"
				+ _zExamNumber
				+ "%' and zStudyForm like '%"
				+ _zStudyForm
				+ "%' and zStudyType like '%"
				+ _zStudyType
				+ "%' and zStatus like '%"
				+ _zStatus
				+ "%' and studyLevel like '%"
				+ _studyLevel
				+ "%' and zJoinTime like '%"
				+ _zJoinTime
				+ "%'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		if (rs == null) {
			return 1;
		}
		try {
			if (rs.next()) {
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
		return pageCount;
	}

	public static boolean deleteStuAll(String idCard) {
		String sql = "delete from ExamItem where idCard='" + idCard + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		try {
			if (stmt.executeUpdate(sql) >= 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static List<Student> getNotChooseStu(int page, int rows) {
		List<Student> students = new ArrayList<Student>();

		String sql = "select * from student where bStatus='在籍' or zStatus='在籍' or bStatus='待注册' or zStatus='待注册' and idCard not in ( "
				+ "select idCard from examitem group by idCard) limit "
				+ (page - 1) * rows + "," + rows;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				String idCard = rs.getString("idCard");
				String password = rs.getString("password");
				String number = rs.getString("number");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String nationality = rs.getString("nationality");
				String phone1 = rs.getString("phone1");
				String studyLevel = rs.getString("studyLevel");
				int siteId = rs.getInt("siteId");
				String phone2 = rs.getString("phone2");
				String remark = rs.getString("remark");
				String qq = rs.getString("qq");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String postCode = rs.getString("postCode");
				String politicalLevel = rs.getString("politicalLevel");
				String tuition = rs.getString("tuition");
				int bSchoolId = rs.getInt("bSchoolId");
				int bMajorId = rs.getInt("bMajorId");
				String bStudyForm = rs.getString("bStudyForm");
				String bStudyType = rs.getString("bStudyType");
				String bExamNumber = rs.getString("bExamNumber");
				String bStatus = rs.getString("bStatus");
				String bJoinTime = rs.getString("bJoinTime");
				int zSchoolId = rs.getInt("zSchoolId");
				int zMajorId = rs.getInt("zMajorId");
				String zStudyForm = rs.getString("zStudyForm");
				String zStudyType = rs.getString("zStudyType");
				String zExamNumber = rs.getString("zExamNumber");
				String zStatus = rs.getString("zStatus");
				String zJoinTime = rs.getString("zJoinTime");
				Student s = new Student(idCard, password, number, name, sex,
						nationality, phone1, studyLevel, siteId, phone2,
						remark, qq, email, address, postCode, politicalLevel,
						tuition, bSchoolId, bMajorId, bStudyForm, bStudyType,
						bExamNumber, bStatus, bJoinTime, zSchoolId, zMajorId,
						zStudyForm, zStudyType, zExamNumber, zStatus, zJoinTime);
				students.add(s);
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
		return students;
	}

	public static int getNotChooseStuPageCount() {
		String sql = "select count(*) as count from student where bStatus='在籍' or zStatus='在籍' or bStatus='待注册' or zStatus='待注册' and idCard not in ( "
				+ "select idCard from examitem group by idCard" + ")";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		int pageCount = 0;
		try {
			if (rs.next()) {
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
		return pageCount;
	}
}

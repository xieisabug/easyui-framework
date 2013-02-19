package com.zhnjy.util.db;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.zhnjy.entity.Student;
import com.zhnjy.util.string.ValueHelper;

public class StudentDB {
	// 添加非空属性到数据库，返回1插入成功
	public static int addMust(Student student) {
		int i = 0;
		String sql = "insert Student(idCard, password,number,name,"
				+ "sex,nationality,phone1,phone2,studyLevel,"
				+ "siteId,remark)values(?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, student.getIdCard());
			pstmt.setString(2, student.getPassword());
			pstmt.setString(3, student.getNumber());
			pstmt.setString(4, student.getName());
			pstmt.setString(5, student.getSex());
			pstmt.setString(6, student.getNationality());
			pstmt.setString(7, student.getPhone1());
			pstmt.setString(8, student.getPhone2());
			pstmt.setString(9, student.getStudyLevel());
			pstmt.setInt(10, student.getSiteId());
			pstmt.setString(11, student.getRemark());
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

	// 添加可以为空的属性到数据库，返回1插入成功
	public static int addSelectable(Student student) {
		int i = 0;
		String sql = "update Student set qq=?,email=?,address=?,postCode=?,politicalLevel=?,"
				+ "tuition=?,bSchoolId=?,bMajorId=?,bStudyForm=?,bStudyType=?,"
				+ "bExamNumber=?,bStatus=?,bJoinTime=?,zSchoolId=?,zMajorId=?,zStudyForm=?,zStudyType=?,"
				+ "zExamNumber=?,zStatus=?,zJoinTime=? " + "where idCard=?";

		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);

		try {
			if (student.getQq() == null) {
				student.setQq("");
			}
			pstmt.setString(1, student.getQq());

			if (student.getEmail() == null) {
				student.setEmail("");
			}
			pstmt.setString(2, student.getEmail());

			if (student.getAddress() == null) {
				student.setAddress("");
			}
			pstmt.setString(3, student.getAddress());

			if (student.getPostCode() == null) {
				student.setPostCode("");
			}
			pstmt.setString(4, student.getPostCode());

			if (student.getPoliticalLevel() == null) {
				student.setPoliticalLevel("");
			}
			pstmt.setString(5, student.getPoliticalLevel());

			if (student.getTuition() == null) {
				student.setTuition("");
			}
			pstmt.setString(6, student.getTuition());

			// if(student.getbSchoolId() == null){
			// student.setbSchool("");
			// }
			pstmt.setInt(7, student.getbSchoolId());

			// if(student.getbMajor() == null){
			// student.setbMajor("");
			// }
			pstmt.setInt(8, student.getbMajorId());

			if (student.getbStudyForm() == null) {
				student.setbStudyForm("");
			}
			pstmt.setString(9, student.getbStudyForm());

			if (student.getbStudyType() == null) {
				student.setbStudyType("");
			}
			pstmt.setString(10, student.getbStudyType());

			if (student.getbExamNumber() == null) {
				student.setbExamNumber("");
			}
			pstmt.setString(11, student.getbExamNumber());

			if (student.getbStatus() == null) {
				student.setbStatus("");
			}
			pstmt.setString(12, student.getbStatus());

			if (student.getbJoinTime() == null) {
				student.setbJoinTime("");
			}
			pstmt.setString(13, student.getbJoinTime());

			// if(student.getzSchool() == null){
			// student.setzSchool("");
			// }
			pstmt.setInt(14, student.getzSchoolId());

			// if(student.getzMajor() == null){
			// student.setzMajor("");
			// }
			pstmt.setInt(15, student.getzMajorId());

			if (student.getzStudyForm() == null) {
				student.setzStudyForm("");
			}
			pstmt.setString(16, student.getzStudyForm());

			if (student.getzStudyType() == null) {
				student.setzStudyType("");
			}
			pstmt.setString(17, student.getzStudyType());

			if (student.getzExamNumber() == null) {
				student.setzExamNumber("");
			}
			pstmt.setString(18, student.getzExamNumber());

			if (student.getzStatus() == null) {
				student.setzStatus("");
			}
			pstmt.setString(19, student.getzStatus());

			if (student.getzJoinTime() == null) {
				student.setzJoinTime("");
			}
			pstmt.setString(20, student.getzJoinTime());

			pstmt.setString(21, student.getIdCard());
			i = pstmt.executeUpdate();
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
		return i;
	}

	// 根据idCard删除一条学生信息
	public static int deleteStudent(String idCard) {

		int i = 0;

		String sql = "delete from Student where idCard='" + idCard + "'";
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

	// 通过学生的身份证号码得到一个学生的实体类
	public static Student getStudent(String idCard) {

		String sql = "select * from Student where idCard='" + idCard + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		Student student = null;
		try {
			if (rs.next()) {
				// String idCard = rs.getString("idCard");
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
				student = new Student(idCard, password, number, name, sex,
						nationality, phone1, studyLevel, siteId, phone2,
						remark, qq, email, address, postCode, politicalLevel,
						tuition, bSchoolId, bMajorId, bStudyForm, bStudyType,
						bExamNumber, bStatus, bJoinTime, zSchoolId, zMajorId,
						zStudyForm, zStudyType, zExamNumber, zStatus, zJoinTime);
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
		return student;
	}

	// 更新学生信息
	public static int updateStudent(Student student) {
		String sql = "update Student set number=?,name=?,sex=?,nationality=?,"
				+ "phone1=?,phone2=?,studyLevel=?,"
				+ "siteId=?,remark=? where idCard=?";

		int i = 0;
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			// pstmt.setString(1, student.getIdCard());
			pstmt.setString(1, student.getNumber());
			pstmt.setString(2, student.getName());
			pstmt.setString(3, student.getSex());
			pstmt.setString(4, student.getNationality());
			pstmt.setString(5, student.getPhone1());
			pstmt.setString(6, student.getPhone2());
			pstmt.setString(7, student.getStudyLevel());
			pstmt.setInt(8, student.getSiteId());
			pstmt.setString(9, student.getRemark());
			pstmt.setString(10, student.getIdCard());
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
		// student.setIdCard(idCard);
		addSelectable(student);
		return i;
	}

	public static List<Student> getList(String _idCard, String _name, String _number,
			String _nationality, String _sex, String _phone1, String _phone2,
			String _siteId, String _studyLevel, String _bSchoolId,
			String _bMajorId, String _bExamNumber, String _bStudyForm,
			String _bStudyType, String _bStatus, String _bJoinTime,
			String _zSchoolId, String _zMajorId, String _zExamNumber,
			String _zStudyForm, String _zStudyType, String _zStatus,
			String _zJoinTime) {
		List<Student> list = new ArrayList<Student>();
		// 处理int类型在模糊查询里的特殊。
		
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
		if(_zStudyForm == null){
			_zStudyForm = "";
		}
		if(_zStudyType == null){
			_zStudyType = "";
		}
		if(_zStatus == null){
			_zStatus = "";
		}
		if(_bStatus == null){
			_bStatus = "";
		}
		if(_bJoinTime == null){
			_bJoinTime = "";
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

		String sql = "select * from Student where idCard like '%" + _idCard
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
				Student stu = new Student(idCard, password, number, name, sex,
						nationality, phone1, studyLevel, siteId, phone2,
						remark, qq, email, address, postCode, politicalLevel,
						tuition, bSchoolId, bMajorId, bStudyForm, bStudyType,
						bExamNumber, bStatus, bJoinTime, zSchoolId, zMajorId,
						zStudyForm, zStudyType, zExamNumber, zStatus, zJoinTime);
				list.add(stu);
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

	// 根据专科考籍号得到该学生。

	public static Student getStudentByzExamNumber(String zExamNumber) {
		String sql = "select idCard from Student where zExamNumber='"
				+ zExamNumber + "'";

		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String idCard = null;
		Student student = null;
		try {
			if (rs.next()) {

				idCard = rs.getString("idCard");
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
		if (idCard != null) {
			student = getStudent(idCard);
		}
		return student;
	}

	// 根据本科考籍号得到该学生。
	public static Student getStudentBybExamNumber(String bExamNumber) {
		String sql = "select idCard from Student where bExamNumber='"
				+ bExamNumber + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		String idCard = null;
		Student student = null;
		try {
			if (rs.next()) {
				idCard = rs.getString("idCard");
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
		if (idCard != null) {
			student = getStudent(idCard);
		}
		return student;
	}

	/**
	 * 根据学校id得到报考了该学校的所有学生
	 * 
	 * @param schoolId
	 * @return
	 */
	public static List<String> getStudentBySchoolId(int schoolId) {

		List<String> list = new ArrayList<String>();
		String sql;
		if (SchoolDB.isBk(schoolId)) {
			sql = "select idCard from Student where bSchoolId=" + schoolId;
		} else {
			sql = "select idCard from Student where zSchoolId=" + schoolId;
		}
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				String idCard = rs.getString("idCard");
				list.add(idCard);
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
	 * 得到总记录数的方法
	 */
	public static int getStudentAmount() {
		int i = 0;
		String sql = "select count(*) from Student";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			while (rs.next()) {
				i = rs.getInt(1);
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
		return i;
	}
	
	public static void print(List<Student> students,String realPath) {
		// 选择模板文件
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(realPath + "resources/stuInfo.xls");
			//System.out.println("url=" + realPath + "resources/stuInfo.xls");
			// 读取模板
			Workbook wb = WorkbookFactory.create(new File(realPath
					+ "resources/stuInfoAll.xls"));
			Sheet sheet = wb.getSheet("First");
			// 获取当前系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String time = df.format(new Date());// new Date()为获取当前系统时间
			// 获取数据表中的某一行
			Row row = sheet.getRow(1);
			// 获取一行中的一个单元格
			Cell c = row.getCell(6);
			c.setCellValue("时间：" + time);
			// 拿到数据格式
			CellStyle cs = sheet.getRow(3).getCell(0).getCellStyle();

			for (int k = 3; k < students.size() + 3; k++) {
				Row ro = sheet.createRow(k);
				Student student = (Student) students.get(k - 3);
				for (int i = 0; i < 24; i++) {
					Cell cell = null;
					switch (i) {

					case 0:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getIdCard());
						break;
					case 1:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getName());
						break;
					case 2:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getNumber());// Nunber是推荐人
						break;
					case 3:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getSex());
						break;
					case 4:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getNationality());
						break;
					case 5:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String site = SiteDB.getSiteNameById(student
								.getSiteId());
						cell.setCellValue(site);
						break;
					case 6:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getPhone1());
						break;
					case 7:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String z = null;
						if (student.getzSchoolId() > 0) {
							z = SchoolDB.getSchoolNameById(student
									.getzSchoolId());
						} else {
							z = "";
						}
						cell.setCellValue(z);

						break;
					case 8:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String m = MajorDB.getName(student.getzMajorId());
						cell.setCellValue(m);
						break;
					case 9:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getzExamNumber());
						break;
					case 10:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getzStudyForm());// 专科学习形式
						break;
					case 11:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getzStudyType());// 专科学习类型;
						break;
					case 12:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getzStatus());
						break;
					case 13:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getzJoinTime());
						break;
					case 14:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String s = null;
						if (student.getbSchoolId() > 0) {
							s = SchoolDB.getSchoolNameById(student
									.getbSchoolId());
						} else {
							s = "";
						}
						cell.setCellValue(s);
						break;
					case 15:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String m2 = MajorDB.getName(student.getbMajorId());
						cell.setCellValue(m2);
						break;
					case 16:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getbExamNumber());
						break;
					case 17:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getbStudyForm());
						break;
					case 18:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getbStudyType());
						break;
					case 19:
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getbStatus());
						break;
					case 20:

						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getbJoinTime());

						break;
					case 21:

						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(ValueHelper.getNameByLevel(student.getStudyLevel()));
						break;
					case 22:

						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getTuition());

						break;
					case 23:

						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getRemark());

						break;
					}
				}
			}
			wb.write(fos);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//return "printsuccess";
	}
	public static void printSome(List<Student> students,String realPath) {
		// 选择模板文件
		FileOutputStream fos = null;
		String school;
		String major;
		String studyType;
		String joinTime;	
		try {
			fos = new FileOutputStream(realPath + "resources/stuInfo.xls");
			//System.out.println("url=" + realPath + "resources/stuInfo.xls");
			// 读取模板
			Workbook wb = WorkbookFactory.create(new File(realPath
					+ "resources/stuInfoSome.xls"));
			Sheet sheet = wb.getSheet("First");
			// 获取当前系统时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
			String time = df.format(new Date());// new Date()为获取当前系统时间
			// 获取数据表中的某一行
			Row row = sheet.getRow(1);
			// 获取一行中的一个单元格
			Cell c = row.getCell(7);
			c.setCellValue("时间：" + time);
			// 拿到数据格式
			CellStyle cs = sheet.getRow(3).getCell(0).getCellStyle();
			for (int k = 3; k < students.size() + 3; k++) {
				Row ro = sheet.createRow(k);
				Student student = (Student) students.get(k - 3);
				
				if(student.getStudyLevel() == "zk"){
					school  = student.getzSchoolName()+"(专)";
					major = student.getzMajorName()+"(专)";
					studyType = student.getzStudyType()+"(专)";
					joinTime = student.getzJoinTime()+"(专)";	
				}else{
					school  = student.getbSchoolName()+"(本)";
					major = student.getbMajorName()+"(本)";
					studyType = student.getbStudyType()+"(本)";
					joinTime = student.getbJoinTime()+"(本)";	
				}
				for (int i = 0; i < 10; i++) {
					Cell cell = null;
					switch (i) {
//入学时间，报考学校，报考专业，，学习类型
					case 0://身份证
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getIdCard());
						break;
					case 1://名字
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getName());
						break;
					case 2://推荐人
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getNumber());// Nunber是推荐人
						break;
					case 3://助学站
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String site = SiteDB.getSiteNameById(student
								.getSiteId());
						cell.setCellValue(site);
						break;
					case 4://主联系电话
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getPhone1());
						break;
					case 5://学校
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(school);
						break;
					case 6://专业
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(major);
						break;
					case 7://学习类型;
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(studyType);
						break;
					case 8://入学时间
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(joinTime);
						break;
					
					case 9://学习层次
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getStudyLevel());
						break;
					}
				}
			}
			wb.write(fos);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//return "printsuccess";
	}
	
	public static List<Student> getListByPage(String _idCard, String _name, String _number,
			String _nationality, String _sex, String _phone1, String _phone2,
			String _siteId, String _studyLevel, String _bSchoolId,
			String _bMajorId, String _bExamNumber, String _bStudyForm,
			String _bStudyType, String _bStatus, String _bJoinTime,
			String _zSchoolId, String _zMajorId, String _zExamNumber,
			String _zStudyForm, String _zStudyType, String _zStatus,
			String _zJoinTime, int page, int rows) {
		List<Student> list = new ArrayList<Student>();
		// 处理int类型在模糊查询里的特殊。
		
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
		if(_zStudyForm == null){
			_zStudyForm = "";
		}
		if(_zStudyType == null){
			_zStudyType = "";
		}
		if(_zStatus == null){
			_zStatus = "";
		}
		if(_bStatus == null){
			_bStatus = "";
		}
		if(_bJoinTime == null){
			_bJoinTime = "";
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

		String sql = "select * from Student where idCard like '%" + _idCard
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
				+ "%' and studyLevel like '%" + _studyLevel + "%' and zJoinTime like '%"+_zJoinTime+"%'"
				+ " limit " + (page-1)*rows + "," + rows;
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
				Student stu = new Student(idCard, password, number, name, sex,
						nationality, phone1, studyLevel, siteId, phone2,
						remark, qq, email, address, postCode, politicalLevel,
						tuition, bSchoolId, bMajorId, bStudyForm, bStudyType,
						bExamNumber, bStatus, bJoinTime, zSchoolId, zMajorId,
						zStudyForm, zStudyType, zExamNumber, zStatus, zJoinTime);
				list.add(stu);
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

		return list;
	}
	
	public static List<Student> getSomeByPage(String _idCard, String _name, String _number,
			String _nationality, String _sex, String _phone1, String _phone2,
			String _siteId, String _studyLevel, String _bSchoolId,
			String _bMajorId, String _bExamNumber, String _bStudyForm,
			String _bStudyType, String _bStatus, String _bJoinTime,
			String _zSchoolId, String _zMajorId, String _zExamNumber,
			String _zStudyForm, String _zStudyType, String _zStatus,
			String _zJoinTime, int page) {
		List<Student> list = new ArrayList<Student>();
		// 处理int类型在模糊查询里的特殊。
		
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
		if(_zStudyForm == null){
			_zStudyForm = "";
		}
		if(_zStudyType == null){
			_zStudyType = "";
		}
		if(_zStatus == null){
			_zStatus = "";
		}
		if(_bStatus == null){
			_bStatus = "";
		}
		if(_bJoinTime == null){
			_bJoinTime = "";
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

		String sql = "select * from Student where idCard like '%" + _idCard
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
				+ "%' and studyLevel like '%" + _studyLevel + "%' and zJoinTime like '%"+_zJoinTime+"%'"
				+ " limit " + (page-1)*5 + "," + 5;
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
				Student stu = new Student(idCard, password, number, name, sex,
						nationality, phone1, studyLevel, siteId, phone2,
						remark, qq, email, address, postCode, politicalLevel,
						tuition, bSchoolId, bMajorId, bStudyForm, bStudyType,
						bExamNumber, bStatus, bJoinTime, zSchoolId, zMajorId,
						zStudyForm, zStudyType, zExamNumber, zStatus, zJoinTime);
				list.add(stu);
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
		
		//System.out.println(sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if(rs.next()) {
				pageCount = rs.getInt("count");
				//System.out.println(sql);
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

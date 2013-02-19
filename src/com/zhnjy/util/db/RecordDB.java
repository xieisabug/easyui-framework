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

import com.zhnjy.entity.Record;
import com.zhnjy.entity.Student;

public class RecordDB {
	/**
	 * 增加一条缴费记录
	 * 
	 * @param record
	 * @return
	 */
	public static int add(Record record) {
		int i = 0;
		String sql = "insert Record(id,idCard,recordItemId,money,bill,yearth,date,goUp,goUpBill,goDown,goDownBill,goUpWhere,goDownWhere,goMoneyWhere,remark) values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, record.getIdCard());
			pstmt.setInt(2, record.getRecordItemId());
			pstmt.setFloat(3, record.getMoney());
			pstmt.setString(4, record.getBill());
			pstmt.setString(5, record.getYearth());
			pstmt.setString(6, record.getDate());
			pstmt.setFloat(7, record.getGoUp());
			pstmt.setString(8, record.getGoUpBill());
			pstmt.setFloat(9, record.getGoDown());
			pstmt.setString(10, record.getGoDownBill());
			pstmt.setString(11, record.getGoUpWhere());
			pstmt.setString(12, record.getGoDownWhere());
			pstmt.setString(13, record.getGoMoneyWhere());
			pstmt.setString(14, record.getRemark());
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
	 * 根据id删除一条缴费记录
	 */
	public static int delete(int id) {
		int i = 0;
		String sql = "delete from Record where id=" + id;
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

	/**
	 * 根据id得到一条缴费记录
	 * 
	 * @param id
	 * @return
	 */
	public static Record getRecord(int id) {
		String sql = "select * from Record where id=" + id;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		Record record = null;
		try {
			if (rs.next()) {
				String idCard = rs.getString("idCard");
				int recordItemId = rs.getInt("recordItemId");
				float money = rs.getFloat("money");
				String bill = rs.getString("bill");
				String yearth = rs.getString("yearth");
				String date = rs.getString("date");
				float goUp = rs.getFloat("goUp");
				String goUpBill = rs.getString("goUpBill");
				float goDown = rs.getFloat("goDown");
				String goDownBill = rs.getString("goDownBill");
				String goUpWhere = rs.getString("goUpWhere");
				String goDownWhere = rs.getString("goDownWhere");
				String goMoneyWhere = rs.getString("goMoneyWhere");
				String remark = rs.getString("remark");
				record = new Record(id, idCard, recordItemId, money, bill,
						yearth, date, goUp, goUpBill, goDown, goDownBill,
						goUpWhere, goDownWhere, goMoneyWhere, remark);
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
		return record;
	}

	/**
	 * 根据传进来的id更新一条缴费记录。
	 */
	public static int update(Record record) {
		int i = 0;
		String sql = "update Record set idCard=?,recordItemId=?,money=?,bill=?,yearth?,goUp=?,goUpBill=?,goDown=?,goDownBill=?,goUpWhere=?,goDownWhere=?,goMoneyWhere=?,remark=? where id=?";
		Connection conn = DBHelper.getConnection();
		PreparedStatement pstmt = DBHelper.prepare(conn, sql);
		try {
			pstmt.setString(1, record.getIdCard());
			pstmt.setInt(2, record.getRecordItemId());
			pstmt.setFloat(3, record.getMoney());
			pstmt.setString(4, record.getBill());
			pstmt.setString(5, record.getYearth());
			pstmt.setFloat(6, record.getGoUp());
			pstmt.setString(7, record.getGoUpBill());
			pstmt.setFloat(8, record.getGoDown());
			pstmt.setString(9, record.getGoDownBill());
			pstmt.setString(10, record.getGoUpWhere());
			pstmt.setString(11, record.getGoDownWhere());
			pstmt.setString(12, record.getGoMoneyWhere());
			pstmt.setString(13, record.getRemark());
			pstmt.setInt(14, record.getId());
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
	 * 缴费记录的模糊查询
	 */

	public static List<Record> getList(String _idCard, String _recordItemId,
			String _bill, String _yearth, String _date, String _goUp,
			String _goUpBill, String _goDown, String _goDownBill,
			String _goUpWhere, String _goDownWhere, String _goMoneyWhere,
			String _remark) {

		List<Record> list = new ArrayList<Record>();
		if (_recordItemId.equals("") || _recordItemId == null) {
			_recordItemId = ">=0";
		} else {
			_recordItemId = "=" + _recordItemId;
		}

		if (_goUp.equals("") || _goUp == null) {
			_goUp = ">=0";
		} else {
			_goUp = "=" + _goUp;
		}

		if (_goDown.equals("") || _goDown == null) {
			_goDown = ">=0";
		} else {
			_goDown = "=" + _goDown;
		}

		String sql = "select * from Record where idCard like '%" + _idCard
				+ "%' and recordItemId" + _recordItemId + " and bill like '%"
				+ _bill + "%' and yearth like '%" + _yearth
				+ "%' and date like '%" + _date + "%' and goUp" + _goUp
				+ " and goUpBill like '%" + _goUpBill + "%' and goDown"
				+ _goDown + " and goDownBill like '%" + _goDownBill
				+ "%' and remark like '%" + _remark
				+ "%' and goUpWhere like '%" + _goUpWhere
				+ "%' and goDownWhere like '%" + _goDownWhere
				+ "%' and goMoneyWhere like '%" + _goMoneyWhere + "%'";
		// System.out.println(sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String idCard = rs.getString("idCard");
				int recordItemId = rs.getInt("recordItemId");
				float money = rs.getFloat("money");
				String bill = rs.getString("bill");
				String yearth = rs.getString("yearth");
				String date = rs.getString("date");
				float goUp = rs.getFloat("goUp");
				String goUpBill = rs.getString("goUpBill");
				float goDown = rs.getFloat("goDown");
				String goDownBill = rs.getString("goDownBill");
				String goUpWhere = rs.getString("goUpWhere");
				String goDownWhere = rs.getString("goDownWhere");
				String goMoneyWhere = rs.getString("goMoneyWhere");
				String remark = rs.getString("remark");
				Record record = new Record(id, idCard, recordItemId, money,
						bill, yearth, date, goUp, goUpBill, goDown, goDownBill,
						goUpWhere, goDownWhere, goMoneyWhere, remark);
				list.add(record);
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

	public static List<Record> getList(String _idCard, String _name,
			String _number, String _nationality, String _sex, String _phone1,
			String _phone2, String _siteId, String _studyLevel,
			String _bSchoolId, String _bMajorId, String _bExamNumber,
			String _bStudyForm, String _bStudyType, String _bStatus,
			String _bJoinTime, String _zSchoolId, String _zMajorId,
			String _zExamNumber, String _zStudyForm, String _zStudyType,
			String _zStatus, String _zJoinTime, String _recordItemId,
			String _bill, String _yearth, String _date, String _goUp,
			String _goUpBill, String _goDown, String _goDownBill,
			String _goUpWhere, String _goDownWhere, String _goMoneyWhere,
			String _remark, int page, int rows) {
		List<Record> list = new ArrayList<Record>();
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

		if (_recordItemId.equals("") || _recordItemId == null) {
			_recordItemId = ">=0";
		} else {
			_recordItemId = "=" + _recordItemId;
		}

		if (_goUp.equals("") || _goUp == null) {
			_goUp = ">=0";
		} else {
			_goUp = "=" + _goUp;
		}

		if (_goDown.equals("") || _goDown == null) {
			_goDown = ">=0";
		} else {
			_goDown = "=" + _goDown;
		}
		String sql = "select Record.idCard as RidCard,id,recordItemId,money,bill,yearth,date,goUp,goUpBill,goUpWhere," +
				"goDown,goDownBill,goDownWhere,goMoneyWhere,Record.remark as Rremark" +
				" from Student,Record where Student.idCard=Record.idCard "
				+ "and Record.idCard like '%"
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
				+ "%' and bill like '%"
				+ _bill
				+ "%' and yearth like '%"
				+ _yearth
				+ "%' and date like '%"
				+ _date
				+ "%' and goUp"
				+ _goUp
				+ " and goUpBill like '%"
				+ _goUpBill
				+ "%' and goDown"
				+ _goDown
				+ " and goDownBill like '%"
				+ _goDownBill
				+ "%' and Record.remark like '%"
				+ _remark
				+ "%' and goUpWhere like '%"
				+ _goUpWhere
				+ "%' and goDownWhere like '%"
				+ _goDownWhere
				+ "%' and goMoneyWhere like '%" + _goMoneyWhere + "%'"
				+ " limit " + (page-1)*rows + "," + rows;
		//System.out.println("sql"+ sql);
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String idCard = rs.getString("RidCard");
				int recordItemId = rs.getInt("recordItemId");
				float money = rs.getFloat("money");
				String bill = rs.getString("bill");
				String yearth = rs.getString("yearth");
				String date = rs.getString("date");
				float goUp = rs.getFloat("goUp");
				String goUpBill = rs.getString("goUpBill");
				float goDown = rs.getFloat("goDown");
				String goDownBill = rs.getString("goDownBill");
				String goUpWhere = rs.getString("goUpWhere");
				String goDownWhere = rs.getString("goDownWhere");
				String goMoneyWhere = rs.getString("goMoneyWhere");
				String remark = rs.getString("Rremark");
				Record r = new Record(id, idCard, recordItemId, money, bill,
						yearth, date, goUp, goUpBill, goDown, goDownBill,
						goUpWhere, goDownWhere, goMoneyWhere, remark);
				//System.out.println(r.getIdCard());
				list.add(r);
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
	
	public static int getPageCount(String _idCard, String _name,
			String _number, String _nationality, String _sex, String _phone1,
			String _phone2, String _siteId, String _studyLevel,
			String _bSchoolId, String _bMajorId, String _bExamNumber,
			String _bStudyForm, String _bStudyType, String _bStatus,
			String _bJoinTime, String _zSchoolId, String _zMajorId,
			String _zExamNumber, String _zStudyForm, String _zStudyType,
			String _zStatus, String _zJoinTime, String _recordItemId,
			String _bill, String _yearth, String _date, String _goUp,
			String _goUpBill, String _goDown, String _goDownBill,
			String _goUpWhere, String _goDownWhere, String _goMoneyWhere,
			String _remark) {
		int pageCount = 0;
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

		if (_recordItemId.equals("") || _recordItemId == null) {
			_recordItemId = ">=0";
		} else {
			_recordItemId = "=" + _recordItemId;
		}

		if (_goUp.equals("") || _goUp == null) {
			_goUp = ">=0";
		} else {
			_goUp = "=" + _goUp;
		}

		if (_goDown.equals("") || _goDown == null) {
			_goDown = ">=0";
		} else {
			_goDown = "=" + _goDown;
		}

		String sql = "select count(*) as count from Student,Record where Student.idCard=Record.idCard "
				+ "and Record.idCard like '%"
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
				+ "%' and recordItemId"
				+ _recordItemId
				+ " and bill like '%"
				+ _bill
				+ "%' and yearth like '%"
				+ _yearth
				+ "%' and date like '%"
				+ _date
				+ "%' and goUp"
				+ _goUp
				+ " and goUpBill like '%"
				+ _goUpBill
				+ "%' and goDown"
				+ _goDown
				+ " and goDownBill like '%"
				+ _goDownBill
				+ "%' and Record.remark like '%"
				+ _remark
				+ "%' and goUpWhere like '%"
				+ _goUpWhere
				+ "%' and goDownWhere like '%"
				+ _goDownWhere
				+ "%' and goMoneyWhere like '%" + _goMoneyWhere + "%'";
		// System.out.println("sql"+ sql);
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

		return pageCount;
	}
	/**
	 * 通过idCard得到该学生的缴费记录
	 */
	public static List<Record> getList(String _idCard) {

		List<Record> list = new ArrayList<Record>();

		String sql = "select * from Record where idCard='" + _idCard + "'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String idCard = rs.getString("idCard");
				int recordItemId = rs.getInt("recordItemId");
				float money = rs.getFloat("money");
				String bill = rs.getString("bill");
				String yearth = rs.getString("yearth");
				String date = rs.getString("date");
				float goUp = rs.getFloat("goUp");
				String goUpBill = rs.getString("goUpBill");
				float goDown = rs.getFloat("goDown");
				String goDownBill = rs.getString("goDownBill");
				String goUpWhere = rs.getString("goUpWhere");
				String goDownWhere = rs.getString("goDownWhere");
				String goMoneyWhere = rs.getString("goMoneyWhere");
				String remark = rs.getString("remark");
				Record record = new Record(id, idCard, recordItemId, money,
						bill, yearth, date, goUp, goUpBill, goDown, goDownBill,
						goUpWhere, goDownWhere, goMoneyWhere, remark);
				list.add(record);
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
	 * 
	 * 判断该学生是否有该项缴费记录,如果有该项费用的缴费记录则为true，否则为false。
	 */

	public static boolean isPay(String idCard, String name, String yearth) {
		List<Record> list = getList(idCard);

		if (list.size() == 0 || (name.equals("") && yearth.equals(""))) {
			return false;
		} else {
			Record re = null;
			String reName = null;
			for (int i = 0; i < list.size(); i++) {
				re = list.get(i);
				reName = RecordItemDB.getNameById(re.getRecordItemId());
				if (!name.equals("") && !yearth.equals("")) {
					if (reName.equals(name) && re.getYearth().equals(yearth)) {
						return true;
					}
				} else {
					if (!name.equals("")) {
						if (reName.equals(name)) {
							return true;
						}
					} else {
						if (re.getYearth().equals(yearth)) {
							return true;
						}
					}
				}
			}
			return false;
		}

	}

	/*
	 * 判断一个学生是否交了考试费
	 */
	public static boolean isPay(String idCard, String examDate) {
		boolean bool = false;
		int recordItemId = RecordItemDB.getIdByName("考试费");
		if (recordItemId == -1) {
			recordItemId = RecordItemDB.getIdByName("报考费");
		}

		String sql = "select * from Record where idCard='" + idCard
				+ "' and recordItemId=" + recordItemId + " and yearth='"
				+ examDate + "'";
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

	public static void print(List<Record> records, String realPath) {
		// 选择模板文件
		FileOutputStream fos = null;
		String school;
		String major;
		String studyType;
		String joinTime;
		try {
			fos = new FileOutputStream(realPath + "resources/stuInfo.xls");
			// System.out.println("url=" + realPath + "resources/stuInfo.xls");
			// 读取模板
			Workbook wb = WorkbookFactory.create(new File(realPath
					+ "resources/finance.xls"));
			Sheet sheet = wb.getSheet("First");
			// 获取当前系统时间
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
			for (int k = 3; k < records.size() + 3; k++) {
				Row ro = sheet.createRow(k);
				Record record = (Record) records.get(k - 3);
				Student student = StudentDB.getStudent(record.getIdCard());

				if (student.getStudyLevel() == "zk") {
					school = student.getzSchoolName() + "(专)";
					major = student.getzMajorName() + "(专)";
					studyType = student.getzStudyType() + "(专)";
					joinTime = student.getzJoinTime() + "(专)";
				} else {
					school = student.getbSchoolName() + "(本)";
					major = student.getbMajorName() + "(本)";
					studyType = student.getbStudyType() + "(本)";
					joinTime = student.getbJoinTime() + "(本)";
				}
				for (int i = 0; i < 20; i++) {
					Cell cell = null;
					switch (i) {
					// 入学时间，报考学校，报考专业，，学习类型
					case 0:// 身份证
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getIdCard());
						break;
					case 1:// 名字
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getName());
						break;
					case 2:// 推荐人
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getNumber());// Nunber是推荐人
						break;
					case 3:// 助学站
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						String site = SiteDB.getSiteNameById(student
								.getSiteId());
						cell.setCellValue(site);
						break;
					case 4:// 主联系电话
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getPhone1());
						break;
					case 5:// 学校
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(school);
						break;
					case 6:// 专业
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(major);
						break;
					case 7:// 学习类型;
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(studyType);
						break;
					case 8:// 入学时间
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(joinTime);
						break;

					case 9:// 学习层次
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(student.getStudyLevel());
						break;

					case 10:// 费用名称
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(RecordItemDB.getNameById(record
								.getRecordItemId()));
						break;

					case 11:// 金额
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getMoney());
						break;
					case 12:// 学年
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getYearth());
						break;
					case 13:// 票号
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getBill());
						break;

					case 14:// 结算
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getGoUp());
						break;

					case 15:// 结算票号
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getGoUpBill());
						break;

					case 16:// 返还
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getGoDown());
						break;

					case 17:// 返还票号
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getGoDownBill());
						break;
					case 18:// 缴费日期
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getDate());
						break;
					case 19:// 费用去向
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getGoMoneyWhere());
						break;
					case 20:// 备注
						cell = ro.createCell(i);
						cell.setCellStyle(cs);
						cell.setCellValue(record.getRemark());
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
	}
}

package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RegisterDB {

	
	public static HashMap getBkMap(int schoolId,
			String startTime, String endTime) {
		// 筛选符合学校，时间段，专业。状态为待注册的本科学生。
		String sql = "select * from Student where bSchoolId=" + schoolId + " and bStatus='待注册'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		HashMap map = new HashMap<String, String>(); 
		try {
			while (rs.next()) {
				String idCard = rs.getString("idCard");
				int id = rs.getInt("bMajorId");
				String majorNumber = MajorDB.getNumber(id);
				String time = rs.getString("bJoinTime");
				if (isBetweenDays(startTime, endTime, time)) {
					map.put(idCard, majorNumber);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
		return map;
	}

	public static HashMap getZkMap(int schoolId,
			String startTime, String endTime) {
		// 筛选符合学校，时间段，专业。状态为待注册的专科学生。
		String sql = "select * from Student where zSchoolId=" + schoolId + " and bStatus='待注册'";
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		HashMap map = new HashMap<String, String>(); 
		try {
			while (rs.next()) {
				String idCard = rs.getString("idcard");
				int id = rs.getInt("zMajorId");
				String majorNumber = MajorDB.getNumber(id);
				String time = rs.getString("zJoinTime");
				if (isBetweenDays(startTime, endTime, time)) {
					map.put(idCard, majorNumber);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
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
		return map;
	}

	// 根据ID判断是不是本科的
	public static boolean isBK(int schoolId) {
		String sql = "select level from School where id=" + schoolId;
		Connection conn = DBHelper.getConnection();
		Statement stmt = DBHelper.getStatement(conn);
		String level = null;
		ResultSet rs = DBHelper.getResultSet(stmt, sql);
		try {
			if (rs.next()) {
				level = rs.getString("level");
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
		if (level.equals("bk")) {
			return true;
		} else {
			return false;
		}
	}

	// 输入一个时间段，判断另一个时间是否在这个时间段中,格式yyyy-MM-dd。
	public static boolean isBetweenDays(String startTime, String endTime,
			String time) throws ParseException {

		boolean result = true;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date d1 = format.parse(startTime);
		Date d2 = format.parse(endTime);
		System.out.println("time"+time);
		Date d3 = format.parse(time);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Calendar c3 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		c3.setTime(d3);

		if (c3.after(c2) || c1.after(c3)) {
			result = false;
		}
		return result;
	}

}

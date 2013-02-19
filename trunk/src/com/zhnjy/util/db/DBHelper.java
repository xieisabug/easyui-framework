package com.zhnjy.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DBHelper {

	public static Connection getConnection(HttpServletRequest request,
			HttpServletResponse response) {
		String host = request.getHeader("BAE_ENV_ADDR_SQL_IP");
		String port = request.getHeader("BAE_ENV_ADDR_SQL_PORT");
		String username = request.getHeader("BAE_ENV_AK");
		String password = request.getHeader("BAE_ENV_SK");
		String driverName = "com.mysql.jdbc.Driver";
		String dbUrl = "jdbc:mysql://";
		String serverName = host + ":" + port + "/";
		String databaseName = "NsxSTVmNfWLOmpUOIagh";
		String connName = dbUrl + serverName + databaseName
				+ "?useUnicode=true&characterEncoding=utf8";

		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(connName, username,
					password);

		} catch (ClassNotFoundException ex) {

		} catch (SQLException e) {

		}
		return connection;
	}

	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=640310");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static PreparedStatement prepare(Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static PreparedStatement prepare(Connection conn, String sql,
			int autoGenereatedKeys) {
		PreparedStatement pstmt = null;
		try {
			if (conn != null) {
				pstmt = conn.prepareStatement(sql, autoGenereatedKeys);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static Statement getStatement(Connection conn) {
		Statement stmt = null;
		try {
			if (conn != null) {
				stmt = conn.createStatement();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}


	public static ResultSet getResultSet(Statement stmt, String sql) {
		ResultSet rs = null;
		try {
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	public static void executeUpdate(Statement stmt, String sql) {
		try {
			if (stmt != null) {
				stmt.executeUpdate(sql);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

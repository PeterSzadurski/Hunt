package util;

import java.sql.*;

public class DBUtil {
	private static Connection conn = null;
	// "final static" saves memory --- instantiated only once in memory
	private final static String DB_USERNAME = "root";
	private final static String DB_PASSWORD = "1234";
	private final static String DB_URL = "jdbc:mysql://localhost:3306/Hunt";
	private final static String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER_CLASS);
			conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
		} catch (Exception ex) {
			ex.getMessage();
		}
		return conn;
	}
	
	public static void closeConnection() {
		try {
			conn.close();
		} catch(Exception ex) {
			ex.getMessage();
		}
	}
	
}

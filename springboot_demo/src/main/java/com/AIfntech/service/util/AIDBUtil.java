package com.AIfntech.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AIDBUtil {
	public static final String SPRINGBOOT_URL = "jdbc:mysql://59.110.172.52:3306/springboot_demo";
	public static final String APP_URL = "jdbc:mysql://59.110.172.52:3306/app";
	public static final String USER = "root";
	public static final String PASSWORD = "MySql@qq521521";
	private static Connection con = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(SPRINGBOOT_URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public static Connection getAppConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				con = DriverManager.getConnection(APP_URL,USER,PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
		
	}
}


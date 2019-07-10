package com.AIfntech.service.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AIDBUtil {
	public static final String URL = "jdbc:mysql://59.110.172.52:3306/springboot_demo";
	public static final String USER = "root";
	public static final String PASSWORD = "MySql@qq521521";
	private static Connection con = null;
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
}


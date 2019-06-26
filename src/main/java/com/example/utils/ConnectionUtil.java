package com.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionUtil {

	

//	public static final String DRIVER = "com.mysql.jdbc.Driver";
//	public static final String URL = "jdbc:mysql://localhost/mydb";
//	public static final String USER = "root";
//	// TODO Password
//	public static final String PASSWORD =  "";

	public static Connection getConnection() {
		try {

			ResourceBundle bundle = ResourceBundle.getBundle("com.example.utils.db");
			String driver = bundle.getString("DRIVER");
			String url = bundle.getString("URL");
			String user = bundle.getString("USER");
			String password = bundle.getString("PASSWORD");
			
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

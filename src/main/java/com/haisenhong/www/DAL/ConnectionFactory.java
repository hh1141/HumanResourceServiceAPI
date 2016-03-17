package com.haisenhong.www.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public static final String URL = "jdbc:mysql://localhost:3306/User";
	public static final String UserName = "root";
	public static final String Password = "root";
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("get connection success");
			return DriverManager.getConnection(URL, UserName, Password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			throw new RuntimeException("Error connecting to the database", e);
		}
		return null;
	}
	
}

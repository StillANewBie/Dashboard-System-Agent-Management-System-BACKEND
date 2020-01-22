package com.mercury.pm.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://msi-pm.caw8ojtkl7pa.us-east-2.rds.amazonaws.com:5432/PMDB";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "password";
	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch(Exception e){
			System.err.println(e);
		}
		return conn;
	}
}
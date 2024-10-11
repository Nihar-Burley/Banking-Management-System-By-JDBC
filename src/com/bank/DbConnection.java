package com.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbConnection {
	private static final String url="jdbc:mysql://localhost/db";
	private static final String userName="root";
	private static final String password="Mysql@123";
	static Connection conn=null;
	
	public static Connection connect()
	{
		try {
			conn=DriverManager.getConnection(url,userName, password);
			Statement st=conn.createStatement();
			
//			Table Creation 
//			String query="CREATE TABLE accounts (account_number BIGINT PRIMARY KEY,fullname VARCHAR(225),email VARCHAR(225) UNIQUE,balance DECIMAl(10, 2),security_pin CHAR(4));";
//			st.execute(query);
//			System.out.println("accounts Table created successfully");
//			
//			String quer1="CREATE TABLE user (fullname VARCHAR(225), email VARCHAR(225) PRIMARY KEY,password VARCHAR(225));";
//			st.execute(quer1);
//			System.out.println("user Table created successfully");
		}catch(Exception e)
		{
			e.printStackTrace();
		}	
		return conn;
	}
}

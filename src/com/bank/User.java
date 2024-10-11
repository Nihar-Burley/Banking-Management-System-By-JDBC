package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bank.DbConnection;

public class User {
	
	
	
	public static void register(String fullName,String email,String password)
	{
		String fn=fullName;
		String em=email;
		String pass=password;
		Connection con=DbConnection.connect();
		String query=Query.insert;
		
		if(userExist(email))
		{
			System.out.println("User Already Exists for this Email Address");
			return;
		}
		try {
			PreparedStatement pst=con.prepareStatement(query);
			
			pst.setString(1,fn);
			pst.setString(2,em);
			pst.setString(3,pass);
			
			if(pst.executeUpdate()>0)
			{
				System.out.println("Registration SuccessFull !");
			}
			else
			{
				System.out.println("Registration Failed");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String login(String email,String password)
	{
		String query=Query.loginQuery;
		Connection con=DbConnection.connect();
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, email);
			pst.setString(2, password);
			ResultSet resultSet=pst.executeQuery();
			if(resultSet.next())
			{
				return email;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public static boolean userExist(String email)
	{
		Connection con=DbConnection.connect();
		String query=Query.existQuery;
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1, email);
			
			ResultSet result=pst.executeQuery();
			if(result.next())
			{
				return true;
			}
			else
			{
				return false;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	
		
	}
}


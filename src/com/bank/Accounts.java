package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Accounts {
	private Connection con;
	
	Accounts()
	{
		this.con=DbConnection.connect();
	}
	
	public long openAccount(String fullName,String email,double balance,String securityPin)
	{
		long accountNo=110010000;
		if(!account_exist(email))
		{
			
			String query=Query.openAccountQuery;
			PreparedStatement pst;
			try {
				pst = con.prepareStatement(query);
				pst.setLong(1, accountNo);
				pst.setString(2, fullName);
				pst.setString(3, email);
				pst.setDouble(4, balance);
				pst.setString(5, securityPin);
				
				if(pst.executeUpdate()>0)
				{
					return accountNo;
				}
				else
				{
					throw new RuntimeException("Account Creation Failed!!!");
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return accountNo;
	}

	public long getAccountNumber(String email)
	{
		String query=Query.getAccountNoQuery;
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,email);
			
			ResultSet resultSet=pst.executeQuery();
			if(resultSet.next())
			{
				return resultSet.getLong("account_number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	
	}
	
	public long generateAccountNo() 
	{
		String query=Query.generateAccountNoQuery;
		try {
			ResultSet resultSet=con.createStatement().executeQuery(query);
			if(resultSet.next())
			{
				long last_acc_no=resultSet.getLong("account_number");
				return last_acc_no+1;
			}
			else
			{
				return 100001000;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 100001000;
	}
	
	public boolean account_exist(String email) 
{
		
		String query=Query.accountExistQuery;
		try {
			PreparedStatement pst=con.prepareStatement(query);
			pst.setString(1,email);
			
			ResultSet resultSet=pst.executeQuery();
			if(resultSet.next())
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

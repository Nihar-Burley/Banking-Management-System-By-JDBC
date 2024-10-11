package com.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
	private Connection con;
	private PreparedStatement pst;
	private String query;
	
	AccountManager()
	{
		this.con=DbConnection.connect();
	}

	
	public void creditMoney(long account_number,double amount,String pin)
	{
		try {
			con.setAutoCommit(false);
			if(account_number!=0)
			{
				pst=con.prepareStatement("Select * from Accounts Where account_number = ? and security_pin = ?");
				pst.setLong(1,account_number);
				pst.setString(2,pin);
				ResultSet resultSet=pst.executeQuery();
				
				if(resultSet.next())
				{
						query=Query.creditQuery;
						pst=con.prepareStatement(query);
						pst.setDouble(1, amount);
						pst.setLong(2, account_number);
						int rowAffected=pst.executeUpdate();
						if(rowAffected>0)
						{
							System.out.println("Amount "+ amount +" Credited Successfully");
							con.commit();
							con.setAutoCommit(true);
						}
						else
						{
							System.out.println("Trancation Failed ");
							con.rollback();
							con.setAutoCommit(true);
						}
					}
				else
				{
					System.out.println("Invalid pin");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void debitMoney(long account_number,double amount,String pin)
	{
		try {
			con.setAutoCommit(false);
			if(account_number!=0)
			{
				
				pst=con.prepareStatement("Select * from Accounts Where account_number = ? and security_pin = ?");
				pst.setLong(1,account_number);
				pst.setString(2,pin);
				ResultSet resultSet=pst.executeQuery();
				
				if(resultSet.next())
				{
					double current_balance=resultSet.getDouble("balance");
					if(amount<=current_balance)
					{
						query=Query.debitQuery;
						pst=con.prepareStatement(query);
						pst.setDouble(1, amount);
						pst.setLong(2, account_number);
						
						int rowAffected=pst.executeUpdate();
						if(rowAffected>0)
						{
							System.out.println("Amount "+ amount +" Dedited Successfully");
							con.commit();
							con.setAutoCommit(true);
						}
						else
						{
							System.out.println("Trancation Failed ");
							con.rollback();
							con.setAutoCommit(true);
						}
					}else
					{
						System.out.println("Insufficient Balance");
					}
					
				}
			}
			else
			{
				System.out.println("Inavlid pin");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void transferMoney(long sender_account,long receiver_account,double amount,String pin)
	{
		try {
			con.setAutoCommit(false);
			if(sender_account !=0 && receiver_account!=0)
			{
				pst=con.prepareStatement("Select 8 from Accounts Where account_number = ? And security_pin = ?");
				pst.setLong(1, sender_account);
				pst.setString(2, pin);
				ResultSet resultSet=pst.executeQuery();
				
				if(resultSet.next())
				{
					double curr_balance=resultSet.getDouble("balance");
					if(amount<=curr_balance)
					{
						PreparedStatement credit_pst=con.prepareStatement(Query.creditQuery);
						PreparedStatement debit_pst=con.prepareStatement(Query.debitQuery);
						credit_pst.setDouble(1, amount);
						credit_pst.setLong(2, receiver_account);
						debit_pst.setDouble(1, amount);
						debit_pst.setLong(2, sender_account);
						int row1=debit_pst.executeUpdate();
						int row2=credit_pst.executeUpdate();
						
						if(row1>0 && row2>0)
						{
							System.out.println("Transcation Completed !!");
							System.out.println(amount+"  Transferred SuccessFully");
							con.commit();
							con.setAutoCommit(true);
						}
						else
						{
							System.out.println("Transcation failed ");
							con.rollback();
							con.setAutoCommit(true);
						}
						
						
					}else
					{
						System.out.println("Insufficient Balance");
					}
					
				}else
				{
					System.out.println("Invalid Pin");
				}
				
			}
			else {
				System.out.println("Invalid account number  ");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
 
	public void getBalance(long account_number, String pin)
	{
		try {
			query=Query.getBalanceQuery;
			pst=con.prepareStatement(query);
			pst.setLong(1,account_number);
			pst.setString(2, pin);
			
			ResultSet resultSet=pst.executeQuery();

			if(resultSet.next())
			{
			System.out.println("Balance - "+resultSet.getDouble("balance"));
			}
			else
			{
				System.out.println("Invalid pin");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

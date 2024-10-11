package com.bank;

public class Query {
	public static String insert="Insert into user Values (?,?,?)";
	public static String update="Update user set sname=? where sid=? ";
	public static String delete="Delete from user where sid=? ";
	public static String select="Select * from user ";
	public static String loginQuery="Select * from user where email=? and password=? ";
	public static String existQuery="Select * from user where email=? ";
	
	
	//Query used in Account.java 
	public static String openAccountQuery="Insert into Accounts Values (?,?,?,?,?)";
	public static String getAccountNoQuery="Select account_number from Accounts Where email=?";
	public static String generateAccountNoQuery="Select account_number from Accounts Order By account_number Desc Limit 1";
	public static String accountExistQuery="Select account_number from Accounts Where email=?";
	
	//Query used in AccountManager.java
	public static String creditQuery="Update Accounts Set balance = balance + ? Where account_number = ? ";
	public static String debitQuery="Update Accounts Set balance = balance - ? Where account_number = ? ";
	public static String getBalanceQuery="Select balance from accounts where account_number = ? and security_pin = ?";
	

}

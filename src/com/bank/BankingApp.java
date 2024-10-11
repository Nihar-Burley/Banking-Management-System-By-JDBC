package com.bank;

import java.util.Scanner;

public class BankingApp {

	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		User user=new User();
		Accounts accounts=new Accounts();
		AccountManager accountManager=new AccountManager();
		
		String email;
		long account_number;
		
		
		while(true)
		{
			System.out.println();
			System.out.println("------------------------");
			System.out.println("-- Banking System --");
			System.out.println("1. Register ");
			System.out.println("2. Login ");
			System.out.println("3. Exit");
			System.out.println("------------------------");
			System.out.println();
			
			int choice=sc.nextInt();
			
			if(choice<1 || choice >3)
				break;
			
			switch(choice)
			{
				case 1:
					sc.nextLine();
					System.out.println("Enter Full Name ");
					String fn=sc.nextLine();
					System.out.println("Enter Email Id  ");
					String em=sc.nextLine();
					System.out.println("Enter Password ");
					String pass=sc.nextLine();
					user.register(fn, em, pass);
					
					break;
					
				case 2:
					sc.nextLine();
					System.out.println("Enter Email Id  ");
					String u=sc.nextLine();
					System.out.println("Enter Password ");
					String p=sc.nextLine();
					email=user.login(u,p);
					if(email!=null)
					{
						System.out.println();
						System.out.println("User logged In");
						if(!accounts.account_exist(email))
						{
							System.out.println();
							System.out.println("1. Open a new Bank Account ");
							System.out.println("2. Exit ");
							int c=sc.nextInt();
							
							switch(c)
							{
								case 1:
									//Create new account
									sc.nextLine();
									System.out.println("Enter Full Name ");
									String s=sc.nextLine();
									System.out.println("Enter Initial Amount ");
									double amt=sc.nextDouble();
									System.out.println("Enter Security Pin ");
									sc.nextLine();
									String pin=sc.nextLine();
									
									account_number=accounts.openAccount(s, email, amt, pin);
									System.out.println("Account created successfully");
									break;
								case 2:
									//Exit
									break;
							}
						}
				
						account_number=accounts.getAccountNumber(email);
						
						
						int choice2=0;
						
						while(choice2 !=5 )
						{
							System.out.println("");
							System.out.println("1. Debit Money");
							System.out.println("2. Credit Money");
							System.out.println("3. Transfer Money");
							System.out.println("4. Check Balance");
							System.out.println("5. Logout");
							System.out.println("Enetr your choice");
							choice2=sc.nextInt();
							double amt;
							String pin;
							switch(choice2)
							{
								case 1:
									System.out.println("Enter Amount ");
									amt=sc.nextDouble();
									System.out.println("Enter Security Pin ");
									sc.nextLine();
									pin=sc.nextLine();
									System.out.println(account_number);
									accountManager.debitMoney(account_number, amt, pin);
									break;
								case 2:
									System.out.println("Enter Amount ");
									amt=sc.nextDouble();
									System.out.println("Enter Security Pin ");
									sc.nextLine();
									pin=sc.nextLine();
									accountManager.creditMoney(account_number, amt, pin);
									break;
								case 3:
									System.out.println("Enter Account Number ");
									long r=sc.nextLong();
									System.out.println("Enter Amount ");
									amt=sc.nextDouble();
									System.out.println("Enter Security Pin ");
									sc.nextLine();
									pin=sc.nextLine();
									accountManager.transferMoney(account_number, r, amt, pin);
									break;
								case 4:
									sc.nextLine();
									System.out.println("Enter Security Pin ");
									pin=sc.nextLine();
									accountManager.getBalance(account_number, pin);
									break;
								case 5:
									break;
 							}
						}
						
					}
					break;
					
				case 3:
					//Exit
					System.out.println("Thank you for using Banking System !!!!");
					System.out.println("Exiting System");
					break;
			}
			
		}

	}

}

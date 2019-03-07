package com.capgemini.payment.main;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.payment.service.*;

public class View {

	public static void main(String[] args) throws Exception  {
		
		WalletService walletService=new WalletServiceImp();
		
		Scanner sc=new Scanner(System.in);
		while(true)
		{
			System.out.println("Press 1 to create Account");
			System.out.println("Press 2 to deposit Amount");
			System.out.println("Press 3 to fund transfer");
			System.out.println("Press 4 to withdraw amount");
			System.out.println("Press 5 to show balance");
			
			int ch=sc.nextInt();
			
			switch(ch)
			{
			case 1:
					System.out.println("Enter name");
					String name=sc.next();
					System.out.println("Enter mobile number");
					String mob=sc.next();
					System.out.println("Enter balance");
					BigDecimal bal=sc.nextBigDecimal();
					try
					{
						System.out.println(walletService.createAccount(name, mob, bal));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
			
			case 2:
					System.out.println("Enter mobile number");
					String mob1=sc.next();
					System.out.println("Enter balance for deposit");
					BigDecimal bal1=sc.nextBigDecimal();
					try
					{
						System.out.println(walletService.depositAmount(mob1, bal1));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
			
			case 3: 
					System.out.println("Enter source mobile number");
					String mob2=sc.next();
					System.out.println("Enter destination mobile number");
					String mob3=sc.next();
					System.out.println("Enter balance which you want to transfer");
					BigDecimal bal2=sc.nextBigDecimal();
					try
					{
						System.out.println(walletService.fundTransfer(mob2, mob3, bal2));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
					
			case 4:
					System.out.println("Enter mobile number");
					String mob4=sc.next();
					System.out.println("Enter balance for withdraw amount");
					BigDecimal bal3=sc.nextBigDecimal();
					try
					{
						System.out.println(walletService.withdrawAmount(mob4, bal3));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
		
			case 5:
					System.out.println("Enter mobile number for show balance");
					String mob5=sc.next();
					try{
					System.out.println(walletService.showBalance(mob5));
					}
					catch(Exception e)
					{
						System.out.println(e.getMessage());
					}
					break;
					
					}
		}
	  //  System.out.println(walletService.createAccount("Abhinav Singh","9639399344",BigDecimal.valueOf(20000)));
	  //  System.out.println(walletService.createAccount("Aditya Nath Singh","8931887477",BigDecimal.valueOf(50000)));
	    
	  /* System.out.println(walletService.depositAmount("9639399344",BigDecimal.valueOf(1000)));*/
	   
	 //  System.out.println(walletService.showBalance("8931887477"));
	 //  System.out.println(walletService.showBalance("9639399344"));
	   	   
	   /*System.out.println(walletService.fundTransfer("8931887477", "9639399344", BigDecimal.valueOf(1000)));

	   System.out.println(walletService.showBalance("8931887477"));
	   
	   System.out.println(walletService.withdrawAmount("9639399344", BigDecimal.valueOf(52000)));*/
	   
	}

}

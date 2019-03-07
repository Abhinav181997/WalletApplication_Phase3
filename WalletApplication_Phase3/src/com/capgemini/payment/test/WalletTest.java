package com.capgemini.payment.test;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.junit.Test;

import com.capgemini.payment.exceptions.DuplicateMobileNumberException;
import com.capgemini.payment.exceptions.InsufficientAmountException;
import com.capgemini.payment.exceptions.InvalidMobileNumberException;
import com.capgemini.payment.exceptions.NameNotEnteredException;
import com.capgemini.payment.service.WalletService;
import com.capgemini.payment.service.WalletServiceImp;

public class WalletTest {
	

	WalletService walletService=new WalletServiceImp();

		    

	
  /* 
   *  -----------------------when name is not entered in a create account method -----------------------
  */
	
	@Test(expected=com.capgemini.payment.exceptions.NameNotEnteredException.class)
	public void whenNameNotEnteredInCreateAccount() throws NameNotEnteredException, Exception{
		
		  walletService.createAccount("","9639399344",BigDecimal.valueOf(20000));
		    
	}
	
	
	/*
	 *  ----------------------when amount is not sufficient in an account---------------------
	 */
	
	@Test(expected=com.capgemini.payment.exceptions.InsufficientAmountException.class)
	public void whenTheAmountEnterdIsInsufficient() throws  ClassNotFoundException, SQLException, Exception{
		
		 walletService.createAccount("Aditya","9639399344",BigDecimal.valueOf(200));
		walletService.withdrawAmount("9639399344", BigDecimal.valueOf(1000));
		    
	}
	
	
	
	/*
	 * 
	 *----------------------when valid mobile number is passed show balance successfully------------------- 
	 *
	 */
	@Test
	public void whenValidInfoIsPassedShowBalanceSuccessfully() throws ClassNotFoundException, SQLException, Exception {
		walletService.createAccount("Nawab", "9854121412",BigDecimal.valueOf(20000));
		walletService.showBalance("9854121412");	

}
	
	/*
	 * -------------------when valid information is passed fund transfer successfully----------------------------
	 * 
	 */
	@Test
	public void whenValidInfoIsPassedFundSuccessfully() throws ClassNotFoundException, SQLException, Exception {
	//	walletService.createAccount("Nawab", "9854121412",BigDecimal.valueOf(20000));
	//	walletService.createAccount("suraj", "9854121413", BigDecimal.valueOf(60000));
		walletService.fundTransfer("9854121412","9639399344",BigDecimal.valueOf(2000));	
	
}
	

	/*
	 * 
	 * -------------------when valid info is passed account created successfully-------------------------
	 */
	@Test
	public void whenValidInfoIsPassedCreateAccountSuccessfully() throws DuplicateMobileNumberException, NameNotEnteredException {
		walletService.createAccount("sushil", "9854121412", new BigDecimal("100.00"));
}

}
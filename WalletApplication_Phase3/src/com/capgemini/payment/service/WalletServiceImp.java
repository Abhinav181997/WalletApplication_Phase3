package com.capgemini.payment.service;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.payment.beans.Customer;
import com.capgemini.payment.beans.Wallet;
import com.capgemini.payment.repo.WalletRepo;
import com.capgemini.payment.repo.WalletRepoImp;
import com.capgemini.payment.exceptions.*;

public class WalletServiceImp implements WalletService {

	WalletRepo walletRepo;
/*
	public WalletServiceImp(WalletRepo walletRepo) {
		this.walletRepo = walletRepo;
	}
*/
	public WalletServiceImp() {
		walletRepo=new WalletRepoImp();
	}
	
	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount)
			throws DuplicateMobileNumberException, NameNotEnteredException {

		Wallet wallet = new Wallet();
		Customer customer = new Customer();

		if (name == "")
			throw new NameNotEnteredException();
		
		wallet.setBalance(amount);
		customer.setName(name);
		customer.setMobileNo(mobileNo);
		customer.setWallet(wallet);
		
		
		if (walletRepo.save(customer)) {
			System.out.println("Account created successfully");
			return customer;
		}
		System.out.println("Account not created");
		return null;
	}

	@Override
	public Customer showBalance(String mobileNo) throws ClassNotFoundException, SQLException, Exception {

		Customer customer = walletRepo.findOne(mobileNo);
		try {
			if ((customer.getMobileNo()).equals(mobileNo)) {
				return customer;
			}
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		return null;

	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws ClassNotFoundException, SQLException, Exception{

		Customer c1 = walletRepo.findOne(sourceMobileNo);
		Customer c2 = walletRepo.findOne(targetMobileNo);

		if (c1 != null && c2 != null) {
		Wallet w1 = new Wallet();
		Wallet w2 = new Wallet();
		w1.setBalance(amount.add(c2.getWallet().getBalance()));
		w2.setBalance((c1.getWallet().getBalance()).subtract(amount));

			c1.setWallet(w2);
			c2.setWallet(w1);
			System.out.println("Fund Transfer Successfully");
			System.out.println("------UPADATED BALANCE------");
			
			BigDecimal s=c1.getWallet().getBalance();
			BigDecimal t=c2.getWallet().getBalance();
			
			walletRepo.update(targetMobileNo,c2.getWallet().getBalance());
			walletRepo.update(sourceMobileNo,c1.getWallet().getBalance());
			return c2;

		}
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount)
			throws ClassNotFoundException, SQLException, Exception {

		Customer customer = walletRepo.findOne(mobileNo);

		if ((walletRepo.findOne(mobileNo).getMobileNo()).equals(mobileNo)) {
			customer.getWallet().setBalance(amount.add(customer.getWallet().getBalance()));
			
			walletRepo.update(mobileNo,customer.getWallet().getBalance());
			return customer;
		}

		throw new InvalidMobileNumberException();
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws ClassNotFoundException, SQLException, Exception {

		Customer customer = walletRepo.findOne(mobileNo);
		Wallet wallet = customer.getWallet();

		if (amount.compareTo(wallet.getBalance()) > 0) {

			throw new InsufficientAmountException();
		}
		if ((walletRepo.findOne(mobileNo).getMobileNo()).equals(mobileNo)) {
			wallet.setBalance((customer.getWallet().getBalance()).subtract(amount));
			customer.setWallet(wallet);
			walletRepo.update(mobileNo,customer.getWallet().getBalance());
			return customer;
		}
		throw new InvalidMobileNumberException();
	}

	
	
}

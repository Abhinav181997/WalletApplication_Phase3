package com.capgemini.payment.repo;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import com.capgemini.payment.beans.*;
import com.capgemini.payment.exceptions.DuplicateMobileNumberException;
import com.capgemini.payment.jdbc.CollectionUtil;

public class WalletRepoImp implements WalletRepo{

	CollectionUtil collectionUtil=new CollectionUtil();
	
	@Override
	public boolean save(Customer customer) {
		
		try
		{
			EntityManager entityManager=collectionUtil.getEntityManager();
			entityManager.getTransaction().begin();
			Customer customer1=new Customer();
			Wallet wallet=new Wallet();
			customer1.setName(customer.getName());
			customer1.setMobileNo(customer.getMobileNo());
			wallet.setBalance(customer.getWallet().getBalance());
			customer1.setWallet(wallet);
			
				entityManager.persist(customer1);
				entityManager.getTransaction().commit();
				entityManager.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return true;
	
		/*
		if (hashmap.containsKey(customer.getMobileNo())) {
			return false;
		}
		hashmap.put(customer.getMobileNo(), customer);
		return true;
		*/
		/*String mobileNo=customer.getMobileNo();
	hashmap.put(mobileNo,customer);
	
		return true;*/
	}

	@Override
	public Customer findOne(String mobileNo) throws DuplicateMobileNumberException,Exception{
	
		try
		{
		EntityManager entityManager=collectionUtil.getEntityManager();
		Customer customer;
		customer=entityManager.find(Customer.class, mobileNo);
		entityManager.close();
		return customer;
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
		/*
		for (Map.Entry<String, Customer> entry : hashmap.entrySet()) {
			if (entry.getValue().getMobileNo().equals(mobileNo)) {
				return entry.getValue();
			}
		}
		throw new DuplicateMobileNumberException();
		
	*/
	}

	

	@Override
	public void update(String mobileNo, BigDecimal amount) throws ClassNotFoundException, SQLException {
	
	try
	{
		EntityManager entityManager=collectionUtil.getEntityManager();
		entityManager.getTransaction().begin();
		Customer customer=new Customer();
		Wallet wallet=new Wallet();
		customer=entityManager.find(Customer.class,mobileNo);
		wallet.setBalance(amount);
		customer.setWallet(wallet);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
		
	}
}


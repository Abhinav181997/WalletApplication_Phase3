package com.capgemini.payment.repo;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.capgemini.payment.beans.*;
import com.capgemini.payment.exceptions.DuplicateMobileNumberException;

public interface WalletRepo {
	
	public boolean save(Customer customer);
    public Customer findOne(String mobileNo) throws DuplicateMobileNumberException, ClassNotFoundException, SQLException, Exception;
   
    void update(String mobileNo, BigDecimal amount) throws ClassNotFoundException,SQLException;
}

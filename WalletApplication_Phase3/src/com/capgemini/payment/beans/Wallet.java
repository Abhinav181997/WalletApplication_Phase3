package com.capgemini.payment.beans;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
@Embeddable
public class Wallet {

private BigDecimal balance;

public BigDecimal getBalance() {
	return balance;
}

public void setBalance(BigDecimal balance) {
	this.balance = balance;
}

public Wallet(BigDecimal balance) {
	
}

public Wallet() {

}

@Override
public String toString() {
	return "Wallet [balance=" + balance + "]";
}

}

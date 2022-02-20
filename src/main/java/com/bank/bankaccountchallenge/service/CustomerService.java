package com.bank.bankaccountchallenge.service;

import java.util.Date;
import java.util.List;

import com.bank.bankaccountchallenge.entity.Transaction;

public interface CustomerService {
	
	String deposit(String email, double inr);
	
	String withdraw(String email, Double inr);
	
	List<Transaction> history(String email, Date startDate, Date endDate);

}

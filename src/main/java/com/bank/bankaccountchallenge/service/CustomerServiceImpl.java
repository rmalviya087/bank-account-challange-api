package com.bank.bankaccountchallenge.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankaccountchallenge.entity.Customer;
import com.bank.bankaccountchallenge.entity.Transaction;
import com.bank.bankaccountchallenge.repository.CustomerRepo;
import com.bank.bankaccountchallenge.repository.TransactionRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	public static final String W = "withdraw";
	public static final String D = "Deposite";
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	
	@Override
	public String deposit(String email, double inr) {
		
		Customer customerAccount = customerRepo.getByEmail(email);
		double  amount = customerAccount.getBalance()+inr;
		
		List<Transaction> list = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		transaction.setTransactionAmount(inr);
		transaction.setTransactionDate(new Date());
		transaction.setTransactionNumber(UUID.randomUUID().toString());
		transaction.setTransactionType(W);
		transaction.setFinalAmount(amount);
		transaction.setEmail(email);
		
		list.add(transaction);
		customerAccount.setTransactions(list);
		customerAccount.setBalance(amount);
		
		customerRepo.saveAndFlush(customerAccount);
		
		return "You have Deposite amount "+inr+"INR, your outstanding amount is "+amount+" INR";
	}

	@Override
	public String withdraw(String email, Double inr) {
		
		Customer customerAccount = customerRepo.getByEmail(email);
		Double balance = customerAccount.getBalance();
		if(inr>balance)
		{
			return "You entered invalid Amount "+inr+", Account doesn't have sufficient balance "+balance;
		}
		else
		{
			double  finalAmount = balance-inr;
			List<Transaction> list = new ArrayList<Transaction>();
			Transaction transaction = new Transaction();
			transaction.setTransactionAmount(inr);
			transaction.setTransactionDate(new Date());
			transaction.setTransactionNumber(UUID.randomUUID().toString());
			transaction.setTransactionType(D);
			transaction.setFinalAmount(finalAmount);
			transaction.setEmail(email);
			
			list.add(transaction);
			customerAccount.setTransactions(list);
			customerAccount.setBalance(finalAmount);
			
			customerRepo.saveAndFlush(customerAccount);
			
			return "Your Withdrawal amount is "+inr+"INR, your outstanding amount is "+finalAmount+" INR";
		}
	}

	@Override
	public List<Transaction> history(String email, Date startDate, Date endDate) {

		return transactionRepo.fetchTransactions(email, startDate, endDate);
	}

}

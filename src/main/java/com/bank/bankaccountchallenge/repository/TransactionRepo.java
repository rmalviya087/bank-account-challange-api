package com.bank.bankaccountchallenge.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.bankaccountchallenge.entity.Transaction;

@Component
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
	
	
	@Query("SELECT t FROM Transaction t WHERE t.email = :email AND t.transactionDate > :startDate AND t.transactionDate <= :endDate")
	public List<Transaction> fetchTransactions(String email, Date startDate, Date endDate);

}

package com.bank.bankaccountchallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import com.bank.bankaccountchallenge.entity.Customer;

@Component
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	
	@Query("UPDATE Customer u SET u.failedAttempt = :failAttempts WHERE u.email = :email")
    public void updateFailedAttempts(int failAttempts, String email);
	
	public Customer getByEmail(String email);
	
}

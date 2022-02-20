package com.bank.bankaccountchallenge.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.bankaccountchallenge.entity.Customer;
import com.bank.bankaccountchallenge.repository.CustomerRepo;

@Service
@Transactional
public class UserServices {
 
    public static final int MAX_FAILED_ATTEMPTS = 3;
     
    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 hours
     
    @Autowired
    private CustomerRepo repo;
     
    public void increaseFailedAttempts(Customer customer) {
        int newFailAttempts = customer.getFailedAttempt() + 1;
        repo.updateFailedAttempts(newFailAttempts, customer.getEmail());
    }
     
    public void resetFailedAttempts(String email) {
        repo.updateFailedAttempts(0, email);
    }
     
    public void lock(Customer customer) {
    	customer.setAccountNonLocked(false);
    	customer.setLockTime(new Date());
         
        repo.save(customer);
    }
     
    public boolean unlockWhenTimeExpired(Customer customer) {
        long lockTimeInMillis = customer.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();
         
        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
        	customer.setAccountNonLocked(true);
        	customer.setLockTime(null);
        	customer.setFailedAttempt(0);
             
            repo.save(customer);
             
            return true;
        }
         
        return false;
    }
}

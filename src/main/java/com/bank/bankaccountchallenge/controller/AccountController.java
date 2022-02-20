package com.bank.bankaccountchallenge.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.bankaccountchallenge.entity.Transaction;
import com.bank.bankaccountchallenge.service.CustomerService;

@RestController
public class AccountController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("deposit/INR/{inr}/EMAIL/{emal}")
	public void deposit(@PathVariable("inr") Double inr, @PathVariable("email") String email)
	{
		customerService.deposit(email, inr);
	}
	
	@PostMapping("withdraw/INR/{inr}/EMAIL/{emal}")
	public void withdraw(@PathVariable("inr") Double inr, @PathVariable("email") String email)
	{
		customerService.withdraw(email, inr);
	}
	
	@PostMapping("histroy/EMAIL/{emal}/start/{startDate}/end/{endDate}")
	public List<Transaction> history(@PathVariable("email") String email, @PathVariable("startDate") Date startDate, @PathVariable("endDate") Date endDate)
	{
		return 	customerService.history(email, startDate, endDate);
	}
	
	@GetMapping("test")
	public String test()
	{
		return "Hello World test!!";
	}

}

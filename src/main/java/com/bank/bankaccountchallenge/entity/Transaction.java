package com.bank.bankaccountchallenge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "email")
	private String email;

	@Column(name="transaction_number")
	private String transactionNumber;
	
	@Column(name="transaction_type")
	private String transactionType;

	@Column(name="transaction_date")
	private Date transactionDate;

	@Column(name="transaction_amount")
	private Double transactionAmount;

	@Column(name="final_amount")
	private Double finalAmount;
	
	@ManyToOne
	@JoinColumn(name = "email", insertable = false, updatable = false)
	private Customer customer;
	
	public Transaction() {}

	public Transaction(Long id, String email, String transactionNumber, String transactionType, Date transactionDate,
			Double transactionAmount, Double finalAmount, Customer customer) {
		super();
		this.id = id;
		this.email = email;
		this.transactionNumber = transactionNumber;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.transactionAmount = transactionAmount;
		this.finalAmount = finalAmount;
		this.customer = customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}

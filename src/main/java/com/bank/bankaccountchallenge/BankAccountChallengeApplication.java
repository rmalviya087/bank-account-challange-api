package com.bank.bankaccountchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class BankAccountChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountChallengeApplication.class, args);
	}

}

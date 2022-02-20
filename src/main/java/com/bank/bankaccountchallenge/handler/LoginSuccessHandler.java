package com.bank.bankaccountchallenge.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bank.bankaccountchallenge.entity.Customer;
import com.bank.bankaccountchallenge.service.UserServices;

@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
 
    @Autowired
    private UserServices userService;
     
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	Customer customer =  (Customer) authentication.getPrincipal();
    	
        if (customer.getFailedAttempt() > 0) {
            userService.resetFailedAttempts(customer.getEmail());
        }
         
        super.onAuthenticationSuccess(request, response, authentication);
    }
     
}

package com.avinash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class ForgotPasswordController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/forgotPwd")
	public String forgotPwd(@RequestParam String emailId) {
		
		return userService.forgotPassword(emailId);
		
	}
	
}

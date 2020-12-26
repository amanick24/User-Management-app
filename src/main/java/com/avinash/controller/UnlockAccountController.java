package com.avinash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class UnlockAccountController {

	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth")
	public boolean checkTmpPwdValid(@RequestParam String emailId, @RequestParam String tempPwd) {
		
		boolean tempPwdValid = userService.isTempPwdValid(emailId, tempPwd);
		return tempPwdValid;
	}
	
	
	@PostMapping("/auth")
	public String updateNewPwd(@RequestParam String emailId, @RequestParam String newPwd) {

		boolean unlockAccount = userService.unlockAccount(emailId, newPwd);
		if(unlockAccount == true) {
			return "Successfully unlocked your account";
		}
		
		
		return "Password not updated";
		
		
	}
	
}

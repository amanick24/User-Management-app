package com.avinash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.model.User;
import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class UnlockAccountController {

	@Autowired
	private UserService userService;

	@PostMapping("/unlockAcc")
	public String unlockAcc(@RequestBody User user) {
		
		if(userService.isTempPwdValid(user.getEmailId(), user.getTempPwd())) {
		 userService.unlockAccount(user.getEmailId(), user.getNewPwd());
			return "Successfully unlocked your account";
		 
		}
		return "Temp password not valid/Password not updated";
	}

}

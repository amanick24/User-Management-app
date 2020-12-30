package com.avinash.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.model.UserLogin;
import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private UserService userService;

	@PostMapping("/auth")
	public String login(@RequestBody UserLogin userLogin) {

		return userService.loginCheck(userLogin.getEmailId(), userLogin.getPassword());

	}

}

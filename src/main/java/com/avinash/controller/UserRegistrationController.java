package com.avinash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.entity.UserEntity;
import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{emailId}")
	public String findEmailUnique(@PathVariable(name = "emailId") String emailId) {

		boolean emailUnique = userService.isEmailUnique(emailId);
		if (emailUnique == false) {

			return "DUPLICATE";
		}

		return "UNIQUE";
	}

	@GetMapping("/countries")
	public Map<Integer, String> findAllCountries() {

		Map<Integer, String> allCountries = userService.getAllCountries();

		return allCountries;
	}

	@GetMapping("/states/{countryId}")
	public Map<Integer, String> findStatesByCountryId(@PathVariable(name = "countryId") Integer countryId) {

		Map<Integer, String> allStatesByCountryID = userService.getAllStatesByCountryID(countryId);

		return allStatesByCountryID;

	}

	@GetMapping("/cities/{stateId}")
	public Map<Integer, String> findCitiesByStateId(@PathVariable(name = "stateId") Integer stateId) {

		Map<Integer, String> allCitiesByStateId = userService.getAllCitiesByStateId(stateId);

		return allCitiesByStateId;
	}

	@PostMapping(value = "/register" , consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> registerUser(@RequestBody UserEntity userEntity) {

		boolean saveUser = userService.saveUser(userEntity);
		if (saveUser == true) {
			return new ResponseEntity<String>("Registration success", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("Registration Failed", HttpStatus.BAD_REQUEST);
	}

}

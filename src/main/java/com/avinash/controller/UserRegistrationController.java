package com.avinash.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avinash.entity.CountryEntity;
import com.avinash.entity.UserEntity;
import com.avinash.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRegistrationController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/{emailId}")
	public String findEmailUnique(@PathVariable(name="emailId") String emailId) {

		UserEntity user= new UserEntity();
		user.setfName("Avinash");
		user.setlName("Ram");
		user.setEmailId("amanick@gmail.com");
		user.setPhNo(984951);
		user.setDob("24/12/1996");
		user.setGender("Male");
		user.setCountry("India");
		user.setCity("Hyderabad");
		user.setState("Telangana");
		
		userService.saveUser(user);
		
		
		boolean emailUnique = userService.isEmailUnique(emailId);
		if (emailUnique == false) {

			return "EmailId already taken";
		}

		return "EmailId is not taken ";
	}


	@GetMapping("/countries")
 public Map<Integer,String> findAllCountries() {
 
		Map<Integer, String> allCountries = userService.getAllCountries();
 
		return allCountries;
 }
 
	@GetMapping("/states/{countryId}")
 public Map<Integer,String> findStatesByCountryId(@PathVariable(name="countryId") Integer countryId) {
		
		Map<Integer, String> allStatesByCountryID = userService.getAllStatesByCountryID(countryId);
		
		return allStatesByCountryID;
 
		
 }
	
	@GetMapping("/cities/{stateId}")
	public Map<Integer,String> findCitiesByStateId(@PathVariable(name="stateId")Integer stateId) {
 
	 Map<Integer, String> allCitiesByStateId = userService.getAllCitiesByStateId(stateId);
	 
	 return allCitiesByStateId;
 }
 
		
	
	  @PostMapping("/register") 
	  public String registerUser(@RequestBody UserEntity userEntity) {
	  
	  boolean saveUser = userService.saveUser(userEntity);
	  if(saveUser == true) {
	  return "Registration success";
	  }
	  return "Registration Failed";
	  }
	 
	 
 }
 
package com.avinash.service;

import java.util.Map;

import com.avinash.entity.UserEntity;

public interface UserService {

	//registration
	public boolean saveUser(UserEntity userEntity);
	
	//Get all the countries
	public Map<Integer,String> getAllCountries();
	
	//Get all the States
	public Map<Integer,String> getAllStatesByCountryID(Integer countryId);
	
	//Get all the cities
	public Map<Integer, String> getAllCitiesByStateId(Integer stateId);
	
	public boolean isEmailUnique(String emailId);
	
	public String loginCheck(String emailId, String pwd);
	
	public boolean isTempPwdValid(String emailId, String tempPwd);

	public boolean unlockAccount(String emailId, String newPwd);

	public String forgotPassword(String emailId);
}

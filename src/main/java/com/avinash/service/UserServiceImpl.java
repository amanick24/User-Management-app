package com.avinash.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avinash.dao.CityRepository;
import com.avinash.dao.CountryRepository;
import com.avinash.dao.StateRepository;
import com.avinash.dao.UserRepository;
import com.avinash.entity.CityEntity;
import com.avinash.entity.CountryEntity;
import com.avinash.entity.StateEntity;
import com.avinash.entity.UserEntity;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean saveUser(UserEntity userEntity) {
		//TODO Change to random password generator
		userEntity.setPassword("Test123");
		userEntity.setAccStatus("LOCKED");
		
		UserEntity saveObj = userRepo.save(userEntity);
		
		return saveObj.getUserId()!= null;
	}

	@Override
	public Map<Integer, String> getAllCountries() {
		
		List<CountryEntity> countriesObjs = countryRepo.findAll();
	    
		Map<Integer, String> map = new HashMap<>();
		
		for (CountryEntity country : countriesObjs) { 
			map.put(country.getCountryId(),country.getCountryName());
		}
			
		
		return map;
	}

	@Override
	public Map<Integer, String> getAllStatesByCountryID(Integer countryId) {
		
		List<StateEntity> stateObjByCountryId = stateRepo.findByCountryId(countryId);
		
		Map<Integer, String> map = new HashMap<>();
		
		for(StateEntity state : stateObjByCountryId) {
			map.put(state.getStateId(), state.getStateName());
		}
		
		return map;
	}

	@Override
	public Map<Integer, String> getAllCitiesByStateId(Integer stateId) {
		
		List<CityEntity> cityObjByStateId =  cityRepo.findByStateId(stateId);
		
		Map<Integer, String> map = new HashMap<>();
		
		for(CityEntity city : cityObjByStateId) {
			map.put(city.getCityId(), city.getCityName());
		}
		return map;
	}

	@Override
	public boolean isEmailUnique(String emailId) {
		
		UserEntity userObj = userRepo.findByEmailId(emailId);
		if(userObj == null) {
			return true;
		}
		
		return false;
	}

	@Override
	public String loginCheck(String emailId, String pwd) {
		UserEntity userObj = userRepo.findByEmailIdAndPassword(emailId, pwd);
		
		if(userObj.getPassword() == pwd) {
			if(userObj.getAccStatus() == "LOCKED") {
				return "Please UNLOCK your passoword";
			}
			return "Valid password";
		 }
			
		return "Username/Password Invalid";
	}

	@Override
	public boolean isTempPwdValid(String emailId, String tempPwd) {
		
		UserEntity userObj = userRepo.findByEmailId(emailId);
		String password = userObj.getPassword();
		if(password.equals(tempPwd)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean unlockAccount(String emailId, String newPwd) {
		UserEntity userObj = userRepo.findByEmailId(emailId);
		userObj.setPassword(newPwd);
		userObj.setAccStatus("UNLOCKED");
		try {
		userRepo.save(userObj);
		return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
		
	}

	@Override
	public String forgotPassword(String emailId) {
		UserEntity userObj = userRepo.findByEmailId(emailId);
		if(userObj.getEmailId() != null) {
			//TODO Logic to send Temp password to email
			//TODO Update the password to temppwd
			return "Mail sent successfully ";
		}
		
		return "Invalid Email address";
	}
	

}

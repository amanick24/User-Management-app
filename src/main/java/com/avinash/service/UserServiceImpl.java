package com.avinash.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
public class UserServiceImpl implements UserService {

	@Autowired
	private CountryRepository countryRepo;
	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private JavaMailSender mailSender;

	@Override
	public boolean saveUser(UserEntity userEntity) {
		// TODO Change to random password generator
		userEntity.setPassword(passwordGenerator(8));
		userEntity.setAccStatus("LOCKED");

		String filename = "UNLOCK-ACC-EMAIL-BODY-TEMPLATE.TXT";
		UserEntity saveObj = userRepo.save(userEntity);
		String emailBody = getEmailBody(userEntity, filename);
		String subject = "Unlock your account";
		
		boolean issentMail = sendMail(subject, emailBody, userEntity.getEmailId());

		//return saveObj.getUserId() != null;
		return saveObj.getUserId() != null && issentMail;
	}

	@Override
	public Map<Integer, String> getAllCountries() {

		List<CountryEntity> countriesObjs = countryRepo.findAll();

		Map<Integer, String> map = new HashMap<>();

		for (CountryEntity country : countriesObjs) {
			map.put(country.getCountryId(), country.getCountryName());
		}

		return map;
	}

	@Override
	public Map<Integer, String> getAllStatesByCountryID(Integer countryId) {

		List<StateEntity> stateObjByCountryId = stateRepo.findByCountryId(countryId);

		Map<Integer, String> map = new HashMap<>();

		for (StateEntity state : stateObjByCountryId) {
			map.put(state.getStateId(), state.getStateName());
		}

		return map;
	}

	@Override
	public Map<Integer, String> getAllCitiesByStateId(Integer stateId) {

		List<CityEntity> cityObjByStateId = cityRepo.findByStateId(stateId);

		Map<Integer, String> map = new HashMap<>();

		for (CityEntity city : cityObjByStateId) {
			map.put(city.getCityId(), city.getCityName());
		}
		return map;
	}

	@Override
	public boolean isEmailUnique(String emailId) {

		UserEntity userObj = userRepo.findByEmailId(emailId);
		if (userObj == null) {
			return true;
		}

		return false;
	}

	@Override
	public String loginCheck(String emailId, String pwd) {
		UserEntity userObj = userRepo.findByEmailIdAndPassword(emailId, pwd);

		if (userObj.getPassword().equals(pwd)) {
			if (userObj.getAccStatus() == "LOCKED") {
				return "Please UNLOCK your passoword";
			}
			return "Valid";
		}

		return "Invalid";
	}

	@Override
	public boolean isTempPwdValid(String emailId, String tempPwd) {

		UserEntity userObj = userRepo.findByEmailId(emailId);
		if(userObj == null) { return false;}
		String password = userObj.getPassword();
		if (password.equals(tempPwd)) {
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
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public String forgotPassword(String emailId) {
		UserEntity userObj = userRepo.findByEmailId(emailId);
		if (userObj != null) {
			// TODO Logic to send Temp password to email
			userObj.setPassword(passwordGenerator(8));
			userObj.setAccStatus("LOCKED");
			userRepo.save(userObj);
			String filename= "FORGOT-PASSWORD-EMAIL-BODY-TEMPLATE.TXT";
			String emailBody = getEmailBody(userObj, filename);
			String subject = "Reset password";
			
			boolean issentMail = sendMail(subject, emailBody, userObj.getEmailId());
			if(issentMail == true) {
				return "Mail sent successfully ";	
			}
			
			return "Could not send mail";
		}

		return "Invalid Email address";
	}
	
	
	public String passwordGenerator(int len) {
		
		String smallLetters= "abcdefghijklmnopqrstuvwxyz";
		String captialLetters ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numeric = "1234567890";
		String combinedString = smallLetters + captialLetters + numeric ;
		Random rd = new Random();
		char[] pass = new char[len];
		
		for(int i=0; i<len ; i++) {
			
			pass[i] = combinedString.charAt(rd.nextInt(combinedString.length()));
			}
		return new String(pass);
	}
	
	public boolean sendMail(String subject, String body, String to) {
		boolean isSent = false;
		try {
			//SimpleMailMessage msg = new SimpleMailMessage();
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper mime = new MimeMessageHelper(mailMessage);

			mime.setSubject(subject);
			mime.setTo(to);
			mime.setText(body, true);
			/*
			 * msg.setSubject(subject); msg.setTo(to); msg.setText(body);
			 */
			
			mailSender.send(mailMessage);
			isSent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	
	private String getEmailBody(UserEntity userEntity, String fileName) {
		/*
		 * StringBuffer sb = new StringBuffer(""); try { File f = new File(filename);
		 * FileReader fr= new FileReader(f); BufferedReader br = new BufferedReader(fr);
		 * String line = br.readLine(); while(line != null) {
		 * if(line.contains("{FNAME}")){ line =
		 * line.replace("{FNAME}",userEntity.getfName()); } if(line.contains("{LNAME}"))
		 * { line = line.replace("{LNAME}",userEntity.getlName()); }
		 * 
		 * if(line.contains("{TEMP-PWD}")) { line = line.replace("{TEMP-PWD}",
		 * userEntity.getPassword()); } if(line.contains("{EMAIL}")) {
		 * 
		 * line = line.replace("{EMAIL}", userEntity.getEmailId()); }
		 * 
		 * sb.append(line);
		 * 
		 * line = br.readLine(); } br.close();}
		 */	 
	List<String> replacedLines = null;
	String mailBody = null;
	try {
		Path path = Paths.get(fileName, "");
		Stream<String> lines = Files.lines(path);
		replacedLines = lines.map(line -> line.replace("{FNAME}", userEntity.getfName())
									.replace("{LNAME}", userEntity.getlName())
									.replace("{TEMP-PWD}", userEntity.getPassword())
									.replace("{EMAIL}", userEntity.getEmailId()))
									.collect(Collectors.toList());
		mailBody = String.join("", replacedLines);
	}
	
	catch (Exception e) {
		e.printStackTrace();
	}

	return mailBody;
}

}

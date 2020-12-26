package com.avinash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "User_Table")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "FIRST_NAME")
	private String fName;

	@Column(name = "LAST_NAME")
	private String lName;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PH_NUMBER")
	private long phNo;

	@Column(name = "DOB")
	private String dob;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "STATE")
	private String state;

	@Column(name = "CITY")
	private String city;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "ACC_STATUS")
	private String accStatus;
	
	public UserEntity() {
		
	}

	public UserEntity(Integer userId, String fName, String lName, String emailId, long phNo, String dob, String gender,
			String country, String state, String city, String password, String accStatus) {
		super();
		this.userId = userId;
		this.fName = fName;
		this.lName = lName;
		this.emailId = emailId;
		this.phNo = phNo;
		this.dob = dob;
		this.gender = gender;
		this.country = country;
		this.state = state;
		this.city = city;
		this.password = password;
		this.accStatus = accStatus;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhNo() {
		return phNo;
	}

	public void setPhNo(long phNo) {
		this.phNo = phNo;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccStatus() {
		return accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

}

package com.avinash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CNTRY_TABLE")
public class CountryEntity {

	@Id
	@Column(name = "CNTRY_ID")
	private Integer countryId;

	
	@Column(name = "CNTRY_NAME")
	private String countryName;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

}

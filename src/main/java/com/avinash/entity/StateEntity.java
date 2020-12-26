package com.avinash.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE_TABLE")
public class StateEntity {

	@Id
	@Column(name = "STATE_ID")
	private Integer stateId;

	@Column(name = "STATE_NAME")
	private String stateName;

	@Column(name = "CNTRY_ID")
	private Integer countryId;

	public Integer getStateId() {
		return stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public Integer getCountryId() {
		return countryId;
	}
	
}

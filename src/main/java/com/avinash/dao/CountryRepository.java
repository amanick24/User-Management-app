package com.avinash.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avinash.entity.CountryEntity;

public interface CountryRepository extends JpaRepository<CountryEntity, Serializable>{

}

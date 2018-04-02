package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.UserType;

public interface UserTypeService {

	UserType findOne(Long id);
	

	List<UserType> findAll();
	
	UserType save(UserType country);
	
	List<UserType> save(List<UserType> countries);
	
	UserType delete(Long id);
	
	void delete(List<Long> ids);
}

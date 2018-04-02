package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.UserType;

public interface UserTypeService {

	UserType findOne(Long id);

	List<UserType> findAll();
	
	UserType save(UserType userType);
	
	List<UserType> save(List<UserType> userTypes);
	
	UserType delete(Long id);
	
	void delete(List<Long> ids);
}

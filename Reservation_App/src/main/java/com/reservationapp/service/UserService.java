package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.User;

public interface UserService {

	User findOneById(Long id);
	
	User findOneByEmail(String email);

	List<User> findAll();
	
	User save(User user);
	
	List<User> save(List<User> users);
	
	User delete(Long id);
	
	void delete(List<Long> ids);
}

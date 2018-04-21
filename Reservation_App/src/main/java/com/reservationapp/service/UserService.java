package com.reservationapp.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.reservationapp.model.User;

public interface UserService {

	User findOneById(Long id);
	
	User findOneByEmail(String email);
	
	User findOneByToken(String token);

	List<User> findAll();
	
	User save(User user);
	
	List<User> save(List<User> users);
	
	User delete(Long id);
	
	void delete(List<Long> ids);
	
	List<User> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(String firstName, String lastName);
	
}

package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findOneByEmail(String email);
	
	User findOneByToken(String token);
	
	List<User> findByFirstNameIgnoreCaseStartingWithAndLastNameIgnoreCaseStartingWith(String firstName, String lastName);
}

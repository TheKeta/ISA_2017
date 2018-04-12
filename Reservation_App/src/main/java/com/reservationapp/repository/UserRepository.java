package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.User;


public interface UserRepository extends JpaRepository<User, Long>{

	User findOneByEmail(String email);
}

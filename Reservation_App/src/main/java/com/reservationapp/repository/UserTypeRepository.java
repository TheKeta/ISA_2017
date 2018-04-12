package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.UserType;

public interface UserTypeRepository extends JpaRepository<UserType, Long>{

	UserType findOneByName(String name);

}

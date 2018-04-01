package com.reservationapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.reservationapp.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

}

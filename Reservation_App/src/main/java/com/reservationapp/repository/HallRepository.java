package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Hall;

public interface HallRepository extends JpaRepository<Hall, Long>{

}

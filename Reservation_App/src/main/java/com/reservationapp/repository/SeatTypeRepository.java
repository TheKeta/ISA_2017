package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.SeatType;

public interface SeatTypeRepository extends JpaRepository<SeatType, Long>{

}

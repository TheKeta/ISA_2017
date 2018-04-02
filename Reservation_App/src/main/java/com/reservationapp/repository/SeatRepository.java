package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long>{

}

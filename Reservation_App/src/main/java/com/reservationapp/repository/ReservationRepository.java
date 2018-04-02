package com.reservationapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}

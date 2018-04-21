package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Event;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	public List<Reservation> findBySeat(Seat seat);
	
	public List<Reservation> findByEvent(Event event);
	
}

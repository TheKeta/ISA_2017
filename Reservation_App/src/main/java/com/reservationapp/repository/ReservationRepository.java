package com.reservationapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Event;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;
import com.reservationapp.model.User;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	public List<Reservation> findBySeat(Seat seat);
	
	public List<Reservation> findByEvent(Event event);
	
	public List<Reservation> findByUser(User user);
	
	//public List<Reservation> searchBetweenDates(Date fromDate, Date toDate);
}

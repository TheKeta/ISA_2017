package com.reservationapp.service;

import java.util.Date;
import java.util.List;

import com.reservationapp.model.Reservation;

public interface ReservationService {

	Reservation findOne(Long id);

	List<Reservation> findAll();
	
	Reservation save(Reservation reservation);
	
	List<Reservation> save(List<Reservation> reservations);
	
	Reservation delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Reservation> findByInstitution(Long id);
	
	List<Reservation> searchBetweenDates(Date fromDate, Date toDate);
}

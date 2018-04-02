package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Seat;

public interface SeatService {

	Seat findOne(Long id);

	List<Seat> findAll();
	
	Seat save(Seat seat);
	
	List<Seat> save(List<Seat> seats);
	
	Seat delete(Long id);
	
	void delete(List<Long> ids);
}
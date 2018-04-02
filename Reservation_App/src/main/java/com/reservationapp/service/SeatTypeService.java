package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.SeatType;

public interface SeatTypeService {

	SeatType findOne(Long id);

	List<SeatType> findAll();
	
	SeatType save(SeatType seatType);
	
	List<SeatType> save(List<SeatType> seatTypes);
	
	SeatType delete(Long id);
	
	void delete(List<Long> ids);
}

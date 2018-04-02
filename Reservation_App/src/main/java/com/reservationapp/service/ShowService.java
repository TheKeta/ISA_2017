package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Show;

public interface ShowService {

	Show findOne(Long id);

	List<Show> findAll();
	
	Show save(Show show);
	
	List<Show> save(List<Show> shows);
	
	Show delete(Long id);
	
	void delete(List<Long> ids);
}

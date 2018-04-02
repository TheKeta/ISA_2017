package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Event;

public interface EventService {

	Event findOne(Long id);

	List<Event> findAll();
	
	Event save(Event event);
	
	List<Event> save(List<Event> events);
	
	Event delete(Long id);
	
	void delete(List<Long> ids);
}

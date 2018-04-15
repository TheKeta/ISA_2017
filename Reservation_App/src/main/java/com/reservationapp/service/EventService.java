package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Institution;

public interface EventService {

	Event findOne(Long id);

	List<Event> findAll();
	
	Event save(Event event);
	
	List<Event> save(List<Event> events);
	
	Event delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Event> findByInstitution(Institution institution);
}

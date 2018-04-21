package com.reservationapp.service;

import java.util.Date;
import java.util.List;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Show;

public interface EventService {

	Event findOne(Long id);

	List<Event> findAll();
	
	Event save(Event event);
	
	List<Event> save(List<Event> events);
	
	Event delete(Long id);
	
	void delete(List<Long> ids);
	
	List<Event> findByInstitution(Institution institution);
	
	List<Event> findByHall(Hall hall);
	
	List<Event> findByHallAndShow(Hall hall, Show show);
	
	List<Event> findByDateBetween(Date fromDate, Date toDate);
}

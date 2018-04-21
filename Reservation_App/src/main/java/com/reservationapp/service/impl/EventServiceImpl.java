package com.reservationapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Show;
import com.reservationapp.repository.EventRepository;
import com.reservationapp.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private HallServiceImpl hallService;

	@Override
	public Event findOne(Long id) {
		return eventRepository.findById(id).get();
	}
	
	@Override
	public List<Event> findAll() {
		return eventRepository.findAll();
	}

	@Override 
	public Event save(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public List<Event> save(List<Event> events) {
		return eventRepository.saveAll(events);
	}

	@Override
	public Event delete(Long id) {
		Event event = eventRepository.findById(id).get();
		if(event == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		eventRepository.delete(event);
		return event;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}

	@Override
	public List<Event> findByHallAndShow(Hall hall, Show show) {
		return eventRepository.findByHall(hall);
	}

	@Override
	public List<Event> findByHall(Hall hall) {
		return eventRepository.findByHall(hall);
	}

	@Override
	public List<Event> findByInstitution(Institution institution) {
		List<Hall> halls = hallService.findByInstitution(institution);
		List<Event> events = new ArrayList<Event>();
		for(Hall h : halls){
			List<Event> temp = findByHall(h);
			for(Event e : temp){
				events.add(e);
			}
		}
		return events;
	}

	@Override
	public List<Event> findByDateBetween(Date fromDate, Date toDate) {
		return eventRepository.findByStartDateBetween(fromDate, toDate);
	}

}

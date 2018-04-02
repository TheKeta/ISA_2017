package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Event;
import com.reservationapp.repository.EventRepository;
import com.reservationapp.service.EventService;

@Service
@Transactional
public class EventServiceImpl implements EventService{

	@Autowired
	private EventRepository eventRepository;

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

}

package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.Event;
import com.reservationapp.model.Institution;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;

@RestController
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	private EventServiceImpl eventService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@RequestMapping(value="/getEvents/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getEvents(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		return new ResponseEntity<>(eventService.findByInstitution(institution), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Event event = eventService.findOne(id);
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Event> addEvent(@RequestBody Event event){
		Event newEvent = eventService.save(event);
		return new ResponseEntity<>(newEvent, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> delete(@PathVariable Long id) {
		Event deleted = eventService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

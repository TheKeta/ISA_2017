package com.reservationapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.EventShows;
import com.reservationapp.DTOs.EventShowsHalls;
import com.reservationapp.DTOs.InstitutionEvents;
import com.reservationapp.model.Event;
import com.reservationapp.model.Institution;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.ShowServiceImpl;

@RestController
@RequestMapping(value = "/event")
public class EventController {

	@Autowired
	private EventServiceImpl eventService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private ShowServiceImpl showService;
	
	@Autowired
	private HallServiceImpl hallService;
	
	@RequestMapping(value="/getEvents/{id}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionEvents> getEvents(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		return new ResponseEntity<>(new InstitutionEvents(eventService.findByInstitution(institution)), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventShows> getEventShows(@PathVariable Long id) {
		Event event = eventService.findOne(id);
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new EventShows(event, showService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/eventShowsHalls/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventShowsHalls> getEventShowsHalls(@PathVariable Long id) {
		Event event = eventService.findOne(id);
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(new EventShowsHalls(event, showService.findAll(), hallService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEvent/{id}", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> delete(@PathVariable Long id) {
		Event deleted = eventService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

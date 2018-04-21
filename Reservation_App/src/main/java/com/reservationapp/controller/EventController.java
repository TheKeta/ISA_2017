package com.reservationapp.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.EventReservationsSeats;
import com.reservationapp.DTOs.EventShows;
import com.reservationapp.DTOs.EventShowsHalls;
import com.reservationapp.DTOs.InstitutionEvents;
import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.SeatServiceImpl;
import com.reservationapp.service.impl.ShowServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

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
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private SeatServiceImpl seatService;
	
	@Autowired
	private ReservationServiceImpl reservationService;
	
	@RequestMapping(value="/getEvents/{id}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionEvents> getInstitutionEvents(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		return new ResponseEntity<>(new InstitutionEvents(eventService.findByInstitution(institution)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/events/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Event>> getEvents(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		return new ResponseEntity<>(eventService.findByInstitution(institution), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getEventSeats/{id}", method = RequestMethod.GET)
	public ResponseEntity<EventReservationsSeats> getEventSeats(@PathVariable Long id){
		if(loggedUser() == null){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Event event = eventService.findOne(id);
		List<Seat> seats = seatService.findByHall(hallService.findOne(event.getHall().getId()));
		List<Reservation> reservations = reservationService.findByEvent(event);
		
		return new ResponseEntity<>(new EventReservationsSeats(event, seats, reservations), HttpStatus.OK);
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
		
		return new ResponseEntity<>(new EventShowsHalls(event, showService.findAll(), hallService.findByInstitution(event.getHall().getInstitution())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEvent/{id}", method = RequestMethod.GET)
	public ResponseEntity<Event> getEvent(@PathVariable Long id) {
		Event event = eventService.findOne(id);
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		if(event.getHall().getInstitution().getAdmin() != loggedUser()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}		
		return new ResponseEntity<>(event, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Event> addEvent(@RequestBody Event event){
		Hall hall = hallService.findOne(event.getHall().getId());
		if(loggedUser() != hall.getInstitution().getAdmin()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);	
		}
		event.setHall(hall);
		
		Event newEvent = eventService.save(event);
		
		
		return new ResponseEntity<>(newEvent, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Event> delete(@PathVariable Long id) {
		if(eventService.findOne(id).getHall().getInstitution().getAdmin() != loggedUser()){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Event deleted = eventService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

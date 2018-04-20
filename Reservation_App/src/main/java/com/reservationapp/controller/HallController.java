package com.reservationapp.controller;

import java.util.ArrayList;
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

import com.reservationapp.DTOs.ReservedSeats;
import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Seat;
import com.reservationapp.model.User;
import com.reservationapp.service.SeatService;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/hall")
public class HallController {

	@Autowired
	private HallServiceImpl hallService;
	
	@Autowired
	private SeatService seatService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private EventServiceImpl eventService;
	
	@Autowired
	private ReservationServiceImpl reservationService;
	
	@RequestMapping(value="/getHalls/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Hall>> getHalls(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		return new ResponseEntity<>(hallService.findByInstitution(institution), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Seat>> getHall(@PathVariable Long id) {
		Hall hall = hallService.findOne(id);
		
		if (hall == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(hall.getInstitution().getAdmin() != loggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		List<Seat> seats = new ArrayList<Seat>();
		seats = seatService.findByHall(hall);
		
		return new ResponseEntity<>(seats, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getByEvent/{id}", method = RequestMethod.GET)
	public ResponseEntity<ReservedSeats> getByEvent(@PathVariable Long id) {
		Event event = eventService.findOne(id);
 
		if (event == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Hall hall = event.getHall();
		
		if(hall.getInstitution().getAdmin() != loggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		List<Seat> seats = new ArrayList<Seat>();
		seats = seatService.findByHall(hall);
		
		List<Reservation> reservations = reservationService.findByEvent(event);
		
		return new ResponseEntity<>(new ReservedSeats(reservations, seats), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Hall> addHall(@RequestBody Hall hall){
		Hall newHall = hallService.save(hall);
		return new ResponseEntity<>(newHall, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Hall> delete(@PathVariable Long id) {
		Hall hall = hallService.findOne(id);
		if(hall.getInstitution().getAdmin() != loggedUser()) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		List<Seat> seats = seatService.findByHall(hall);
	
		for(Seat s : seats){
			seatService.delete(s.getId());
		}
		Hall deleted = hallService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

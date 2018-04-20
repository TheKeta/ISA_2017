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

import com.reservationapp.DTOs.CreateSeats;
import com.reservationapp.DTOs.HallSeats;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Seat;
import com.reservationapp.model.SeatType;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.SeatServiceImpl;
import com.reservationapp.service.impl.SeatTypeServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {

	@Autowired
	private SeatServiceImpl seatService;
	
	@Autowired
	private HallServiceImpl hallService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private SeatTypeServiceImpl seatTypeService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/getSeats/{id}", method = RequestMethod.GET)
	public ResponseEntity<HallSeats> getSeats(@PathVariable Long id){
		Institution institution = institutionService.findOne(id);
		if(institution == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		List<Hall> halls = hallService.findByInstitution(institution);
		if(halls.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		List<List<Seat>> seats = new ArrayList<List<Seat>>();
		
		for(Hall h : halls){
			List<Seat> temp = new ArrayList<Seat>();
			for(Seat s : seatService.findByHall(h)){
				temp.add(s);	
			}
			seats.add(temp);
		}
		return new ResponseEntity<>(new HallSeats(seats, halls), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Seat> getSeat(@PathVariable Long id) {
		Seat seat = seatService.findOne(id);
		if (seat == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(seat, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Seat> addSeat(@RequestBody CreateSeats seat){
		Hall hall = hallService.findOne(seat.getHallId());
		if(hall == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		if(hall.getInstitution().getAdmin() != loggedUser()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SeatType type = seatTypeService.findByName(seat.getTypeName());
		if(type == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		List<Seat> deleteSeats = seatService.findByHall(hall);
		if(deleteSeats != null) {
			List<Long> deleteIds = new ArrayList<Long>();
			for(Seat s : deleteSeats) {
				if(s.getSeatType() == type) {
					deleteIds.add(s.getId());
				}
				
			}
			seatService.delete(deleteIds);
		}
		
		List<Seat> seats = new ArrayList<Seat>();
		for(int i = 1; i <= seat.getRows(); i++) {
			for(int j = 1; j <= seat.getCols(); j++) {
				seats.add(new Seat(i, j, hall, type));
			}
		}
		seatService.save(seats);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Seat> delete(@PathVariable Long id) {
		Seat deleted = seatService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

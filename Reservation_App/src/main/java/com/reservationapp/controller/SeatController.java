package com.reservationapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.HallSeats;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Seat;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.SeatServiceImpl;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {

	@Autowired
	private SeatServiceImpl seatService;
	
	@Autowired
	private HallServiceImpl hallService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
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
		List<Seat> seats = new ArrayList<Seat>();
		for(Hall h : halls){
			for(Seat s : seatService.findByHall(h)){
				seats.add(s);	
			}
		}
		if(seats.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new HallSeats(seats), HttpStatus.OK);
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
	public ResponseEntity<Seat> addSeat(@RequestBody Seat seat){
		Seat newSeat = seatService.save(seat);
		return new ResponseEntity<>(newSeat, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Seat> delete(@PathVariable Long id) {
		Seat deleted = seatService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

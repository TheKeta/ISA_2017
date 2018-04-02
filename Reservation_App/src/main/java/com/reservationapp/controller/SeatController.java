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

import com.reservationapp.model.Seat;
import com.reservationapp.service.impl.SeatServiceImpl;

@RestController
@RequestMapping(value = "/seat")
public class SeatController {

	@Autowired
	private SeatServiceImpl seatService;
	
	
	@RequestMapping(value="/getSeats", method = RequestMethod.GET)
	public ResponseEntity<List<Seat>> getSeats(){
		return new ResponseEntity<>(seatService.findAll(), HttpStatus.OK);
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

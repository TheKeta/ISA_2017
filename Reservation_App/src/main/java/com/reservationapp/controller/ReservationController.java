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

import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.model.Reservation;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl reservationService;
	
	
	@RequestMapping(value="/getReservations", method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservations(){
		return new ResponseEntity<>(reservationService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Reservation> getHall(@PathVariable Long id) {
		Reservation reservation = reservationService.findOne(id);
		if (reservation == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation){
		Reservation newReservation = reservationService.save(reservation);
		return new ResponseEntity<>(newReservation, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Reservation> delete(@PathVariable Long id) {
		Reservation deleted = reservationService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

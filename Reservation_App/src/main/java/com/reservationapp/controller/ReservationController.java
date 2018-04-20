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
import com.reservationapp.model.Reservation;
import com.reservationapp.model.SeatType;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.SeatServiceImpl;
import com.reservationapp.service.impl.SeatTypeServiceImpl;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private ReservationServiceImpl reservationService;
	
	@Autowired
	private EventServiceImpl eventService;
	
	@Autowired
	private SeatTypeServiceImpl seatTypeService;
	
	@Autowired
	private SeatServiceImpl seatService;
	
	@RequestMapping(value="/getReservations/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Reservation>> getReservations(@PathVariable Long id){
		return new ResponseEntity<>(reservationService.findByInstitution(id), HttpStatus.OK);
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
	public ResponseEntity<Reservation> addReservation(@RequestBody List<Reservation> reservations){
		for(Reservation r : reservations) {
			Event event = eventService.findOne(r.getEvent().getId());
			List<Reservation> temps = reservationService.findByEvent(event);
			boolean permission = true;
			for(Reservation t : temps) {
				if(t.getSeats().getRow() == r.getSeats().getRow() && 
						t.getSeats().getSeatNumber() == r.getSeats().getSeatNumber() &&
						t.getSeats().getSeatType().getName().equals(r.getSeats().getSeatType().getName())) {
					
						permission = false;
				}
			}
			if(permission) {
				SeatType type = seatTypeService.findByName(r.getSeats().getSeatType().getName());
				reservationService.save(new Reservation(r.getPrice(), seatService.findByRowAndSeatNumber(r.getSeats().getRow(), r.getSeats().getSeatNumber(), type), event, null ));
			}
		}
		
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Reservation> delete(@PathVariable Long id) {
		Reservation deleted = reservationService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

package com.reservationapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.reservationapp.DTOs.ReservationsSum;
import com.reservationapp.model.Event;
import com.reservationapp.model.Hall;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.SeatType;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.EventServiceImpl;
import com.reservationapp.service.impl.HallServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.SeatServiceImpl;
import com.reservationapp.service.impl.SeatTypeServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

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
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private HallServiceImpl hallService;
	
	@Autowired
	private InstitutionServiceImpl institutionService;
	
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
	
	@RequestMapping(value = "/reserve/{id}", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Reservation> reserve(@PathVariable Long id){		
		Reservation reserve = reservationService.findOne(id);
		if(reserve.isQuick() && reserve.getUser() == null){
			reserve.setUser(loggedUser());
			reservationService.save(reserve);
		}else{
			return new ResponseEntity<>(reserve, HttpStatus.UNAUTHORIZED);
		}

		return new ResponseEntity<>(reserve, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<List<Reservation>> addReservation(@RequestBody List<Reservation> reservations){
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
				Hall hall = hallService.findOne(r.getSeats().getHall().getId());
				reservationService.save(new Reservation(r.getPrice(), seatService.findByRowAndSeatNumberAndHallAndSeatType(r.getSeats().getRow(), r.getSeats().getSeatNumber(), hall, type), event, null, r.isQuick()));
			}
		}
		
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/report/{id}/{fromDate}/{toDate}", method = RequestMethod.GET)
	public ResponseEntity<ReservationsSum> delete(@PathVariable Long id, @PathVariable String fromDate, @PathVariable String toDate) {
		Institution institution = institutionService.findOne(id);
		if(institution == null || institution.getAdmin() != loggedUser()){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		Date date2 = new Date();
		try {
			date1 = formatter.parse(fromDate);
			date2 = formatter.parse(toDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Reservation> tempRes = reservationService.searchBetweenDates(date1, date2);
		List<Reservation> reservations = new ArrayList<Reservation>();
		for(Reservation r : tempRes){
			if(r.getSeats().getHall().getInstitution() == institution && r.getUser() != null){
				reservations.add(r);
			}
		}
		return new ResponseEntity<>(new ReservationsSum(reservations), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Reservation> delete(@PathVariable Long id) {
		Reservation res = reservationService.findOne(id);
		if(res.getSeats().getHall().getInstitution().getAdmin() != loggedUser()){
			return new ResponseEntity<>(res, HttpStatus.UNAUTHORIZED);
		}
		Reservation deleted = reservationService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

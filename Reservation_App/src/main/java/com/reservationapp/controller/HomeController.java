package com.reservationapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.InstitutionDTO;
import com.reservationapp.DTOs.ReservationDTO;
import com.reservationapp.model.History;
import com.reservationapp.model.Institution;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.HistoryServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private HistoryServiceImpl historyService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ReservationServiceImpl reservationService;
	
	@RequestMapping(value="/getHistory", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionDTO>> getHistory(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		User user = userService.findOneByEmail(auth.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		History history = historyService.findByUser(user);
		List<InstitutionDTO> res = new ArrayList<InstitutionDTO>();
		
		if(history == null)
			return new ResponseEntity<>(res, HttpStatus.OK);
		
		for(Institution i : history.getInstitutions()){
			res.add(new InstitutionDTO(i));
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/reservations", method = RequestMethod.GET)
	public ResponseEntity<List<ReservationDTO>> getReservations(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		User user = userService.findOneByEmail(auth.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<ReservationDTO> res = new ArrayList<ReservationDTO>();
		List<Reservation> reservations = reservationService.findByUser(user);
		if(reservations == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		for(Reservation r : reservations){
			res.add(new ReservationDTO(r));
		}
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@RequestMapping(value="/cancelReservation", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<String> cancelReservation(@RequestBody ReservationDTO reservation){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return new ResponseEntity<>("Please login", HttpStatus.NOT_FOUND);
		
		User user = userService.findOneByEmail(auth.getName());
		if(user == null)
			return new ResponseEntity<>("Please login", HttpStatus.NOT_FOUND);
		
		Reservation res = reservationService.findOne(reservation.getId());
		if(res==null || res.getUser().getId() != user.getId())
			return new ResponseEntity<>("Error occured while canceling reservation", HttpStatus.NOT_FOUND);
		
		Date currentDate = new Date();
		Long diff = (res.getEvent().getEventDate().getTime() - currentDate.getTime())/(60*1000);
		if(diff<30){
			return new ResponseEntity<>("You can cancel your reservation at least 30 minutes before the event", HttpStatus.OK);
		}
		
		if(res.isQuick()){
			res.setUser(null);
			reservationService.save(res);
		}else{
			reservationService.delete(res.getId());
		}
		
		return new ResponseEntity<>("Reservation canceled", HttpStatus.OK);
	}
}

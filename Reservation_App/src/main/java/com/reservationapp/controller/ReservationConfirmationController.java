package com.reservationapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.reservationapp.model.Reservation;
import com.reservationapp.model.User;
import com.reservationapp.service.UserService;
import com.reservationapp.service.impl.ReservationServiceImpl;

@Controller
public class ReservationConfirmationController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ReservationServiceImpl reservationService;

	@RequestMapping(value="/confirmReservation", method = RequestMethod.GET)
	public ModelAndView confirmReservation(ModelAndView modelAndView, @RequestParam("token") String token, @RequestParam("id") Long id, @RequestParam("friend") Long friend) {
		
		User sender = userService.findOneByToken(token);
		User reciever = userService.findOneById(friend);
		Reservation reservation = reservationService.findOne(id);
		if(sender == null || reservation == null || reciever == null){
			modelAndView.setViewName("redirect:login");
			modelAndView.addObject("message", "Sorry, something went wrong");
			return modelAndView;
		}
		
		if(reservation.getUser().getId() != sender.getId()){
			modelAndView.setViewName("redirect:login");
			modelAndView.addObject("message", "Sorry, someone already reserved this ticket");
			return modelAndView;
		}
		
		reservation.setUser(reciever);
		reservationService.save(reservation);
		modelAndView.setViewName("redirect:login");
		modelAndView.addObject("message", "You have successfuly reserved the ticket!");
		return modelAndView;
	}
}

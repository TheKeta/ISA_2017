package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.Message;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.MessageServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/message")
public class MessageController {
	@Autowired
	private MessageServiceImpl msgService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<String> getBid(@PathVariable Long id) {
		User user= userService.findOneById(id);
		if (user == null) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user.getFirstName()+" "+user.getLastName(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/getRMessages", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getRM(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			return new ResponseEntity<>(msgService.findRMSG(user.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/getSMessages", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getSM(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			return new ResponseEntity<>(msgService.findSMSG(user.getId()), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
}

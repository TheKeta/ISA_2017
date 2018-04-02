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

import com.reservationapp.model.User;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	
	@RequestMapping(value="/getUser", method = RequestMethod.GET)
	public ResponseEntity<List<User>> getUsers(){
		return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.findOne(id);
		if (user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<User> addUser(@RequestBody User user){
		User newUser = userService.save(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> delete(@PathVariable Long id) {
		User deleted = userService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}
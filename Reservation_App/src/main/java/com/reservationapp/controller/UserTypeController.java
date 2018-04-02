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

import com.reservationapp.model.UserType;
import com.reservationapp.service.impl.UserTypeServiceImpl;

@RestController
@RequestMapping(value = "/usertype")
public class UserTypeController {
	
	@Autowired
	private UserTypeServiceImpl userTypeService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<UserType>> getUserTypes(){
		return new ResponseEntity<>(userTypeService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/radi", method = RequestMethod.GET)
	public String getShitDone(){
		return "asdasdasdasd";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserType> getUserType(@PathVariable Long id) {
		UserType userType = userTypeService.findOne(id);
		if (userType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(userType, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<UserType> addUserType(@RequestBody UserType userType){
		System.out.println(userType.getName());
		UserType newUserType = userTypeService.save(userType);
		return new ResponseEntity<>(newUserType, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<UserType> delete(@PathVariable Long id) {
		UserType deleted = userTypeService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

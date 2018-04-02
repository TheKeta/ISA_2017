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

import com.reservationapp.model.Hall;
import com.reservationapp.service.impl.HallServiceImpl;

@RestController
@RequestMapping(value = "/hall")
public class HallController {

	@Autowired
	private HallServiceImpl hallService;
	
	
	@RequestMapping(value="/getHalls", method = RequestMethod.GET)
	public ResponseEntity<List<Hall>> getHalls(){
		return new ResponseEntity<>(hallService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Hall> getHall(@PathVariable Long id) {
		Hall hall = hallService.findOne(id);
		if (hall == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(hall, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Hall> addHall(@RequestBody Hall hall){
		Hall newHall = hallService.save(hall);
		return new ResponseEntity<>(newHall, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Hall> delete(@PathVariable Long id) {
		Hall deleted = hallService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

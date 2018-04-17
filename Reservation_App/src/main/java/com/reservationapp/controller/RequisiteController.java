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

import com.reservationapp.model.Requisite;
import com.reservationapp.service.impl.RequisiteServiceImpl;

@RestController
@RequestMapping(value = "/requisite")
public class RequisiteController {
	@Autowired
	private RequisiteServiceImpl requisiteService;
	
	
	@RequestMapping(value="/getRequisites", method = RequestMethod.GET)
	public ResponseEntity<List<Requisite>> getRequisites(){
		return new ResponseEntity<>(requisiteService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Requisite> getRequisite(@PathVariable Long id) {
		Requisite requisite = requisiteService.findOne(id);
		if (requisite == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(requisite, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Requisite> addRequisite(@RequestBody Requisite requisite){
		Requisite requisiteType = requisiteService.save(requisite);
		return new ResponseEntity<>(requisiteType, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Requisite> delete(@PathVariable Long id) {
		Requisite deleted = requisiteService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

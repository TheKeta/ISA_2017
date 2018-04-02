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

import com.reservationapp.model.Institution;
import com.reservationapp.service.impl.InstitutionServiceImpl;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {

	@Autowired
	private InstitutionServiceImpl institutionService;
	
	
	@RequestMapping(value="/getEvents", method = RequestMethod.GET)
	public ResponseEntity<List<Institution>> getEvents(){
		return new ResponseEntity<>(institutionService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Institution> getEvent(@PathVariable Long id) {
		Institution institution = institutionService.findOne(id);
		if (institution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(institution, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Institution> addEvent(@RequestBody Institution institution){
		Institution newInstitution = institutionService.save(institution);
		return new ResponseEntity<>(newInstitution, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Institution> delete(@PathVariable Long id) {
		Institution deleted = institutionService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

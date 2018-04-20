package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.InstitutionTypeUsers;
import com.reservationapp.model.InstitutionType;
import com.reservationapp.service.impl.InstitutionTypeServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/institutionType")
public class InstitutionTypeController {

	@Autowired
	private InstitutionTypeServiceImpl institutionTypeService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/getInstitutionTypes/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionType>> getInstitutionTypes(@PathVariable String type){
		return new ResponseEntity<>(institutionTypeService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/institutionTypeId/{type}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionTypeUsers> getInstitutionTypeId(@PathVariable String type){
		return new ResponseEntity<>(new InstitutionTypeUsers(institutionTypeService.findByName(type), userService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionType> getInstitution(@PathVariable Long id) {
		InstitutionType institution = institutionTypeService.findOne(id);
		if (institution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(institution, HttpStatus.OK);
	}
}

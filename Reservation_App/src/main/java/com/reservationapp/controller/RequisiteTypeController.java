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

import com.reservationapp.model.RequisiteType;
import com.reservationapp.service.impl.RequisiteTypeServiceImpl;

@RestController
@RequestMapping(value = "/requisitetype")
public class RequisiteTypeController {

	@Autowired
	private RequisiteTypeServiceImpl requisiteTypeService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public ResponseEntity<List<RequisiteType>> getRequisiteTypes(){
		return new ResponseEntity<>(requisiteTypeService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<RequisiteType> getRequisiteType(@PathVariable Long id) {
		RequisiteType requisiteType = requisiteTypeService.findOne(id);
		if (requisiteType == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(requisiteType, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<RequisiteType> addRequisiteType(@RequestBody RequisiteType requisiteType){
		RequisiteType newrequisiteType = requisiteTypeService.save(requisiteType);
		return new ResponseEntity<>(newrequisiteType, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<RequisiteType> delete(@PathVariable Long id) {
		RequisiteType deleted = requisiteTypeService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

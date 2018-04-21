package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.ShowType;
import com.reservationapp.service.impl.ShowTypeServiceImpl;
@RestController
@RequestMapping(value = "/showType")
public class ShowTypeController {

	
	@Autowired
	private ShowTypeServiceImpl showTypeService;
	
	@RequestMapping(value="/getShowTypes", method = RequestMethod.GET)
	public ResponseEntity<List<ShowType>> getShowTypes(){
		return new ResponseEntity<>(showTypeService.findAll(), HttpStatus.OK);
	}
}

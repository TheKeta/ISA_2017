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

import com.reservationapp.model.Show;
import com.reservationapp.service.impl.ShowServiceImpl;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

	@Autowired
	private ShowServiceImpl showService;
	
	
	@RequestMapping(value="/getHalls", method = RequestMethod.GET)
	public ResponseEntity<List<Show>> getShows(){
		return new ResponseEntity<>(showService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Show> getShow(@PathVariable Long id) {
		Show show = showService.findOne(id);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(show, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Show> addShow(@RequestBody Show show){
		Show newShow = showService.save(show);
		return new ResponseEntity<>(newShow, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Show> delete(@PathVariable Long id) {
		Show deleted = showService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

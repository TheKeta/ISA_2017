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

import com.reservationapp.model.Bid;
import com.reservationapp.model.Requisite;
import com.reservationapp.service.impl.BidServiceImpl;

@RestController
@RequestMapping(value = "/bid")
public class BidController {
	@Autowired
	private BidServiceImpl bidService;
	
	@RequestMapping(value="/getBids", method = RequestMethod.GET)
	public ResponseEntity<List<Bid>> getBids(){
		return new ResponseEntity<>(bidService.findAll(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Bid> getBid(@PathVariable Long id) {
		Bid bid = bidService.findOne(id);
		if (bid == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(bid, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Bid> addBid(@RequestBody Bid bid){
		Bid bidd = bidService.save(bid);
		return new ResponseEntity<>(bidd, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Bid> delete(@PathVariable Long id) {
		Bid deleted = bidService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

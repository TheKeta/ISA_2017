package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.Bid;
import com.reservationapp.model.Requisite;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.BidServiceImpl;
import com.reservationapp.service.impl.RequisiteServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/bid")
public class BidController {
	@Autowired
	private BidServiceImpl bidService;
	
	@Autowired
	private RequisiteServiceImpl reqService;
	
	@Autowired
	private UserServiceImpl userService;
	
	
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
	
	@RequestMapping(value = "/addNewBid", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Bid> addBid(@RequestBody Bid bid){
		Requisite req = reqService.findOne(bid.getItemsID());
		if(req!=null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findOneByEmail(auth.getName());
			if(user!=null) {
				bid.setBiddersID(user.getId());
				bid.setPrice(req.getPrice());
				bid.setReservation(true);
				
				Bid bidd = bidService.save(bid);
				return new ResponseEntity<>(bidd, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		
	}
	
	@RequestMapping(value = "/addNewBidd", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Bid> addBidd(@RequestBody Bid bid){
		Requisite req = reqService.findOne(bid.getItemsID());
		if(req!=null) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findOneByEmail(auth.getName());
			if(user!=null) {
				if(req.getPrice()<bid.getPrice()) {
					bid.setBiddersID(user.getId());
					req.setPrice(bid.getPrice());
					bid.setReservation(false);
					
					Bid bidd = bidService.save(bid);
					req = reqService.save(req);
					return new ResponseEntity<>(bidd, HttpStatus.CREATED);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Bid> delete(@PathVariable Long id) {
		Bid deleted = bidService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

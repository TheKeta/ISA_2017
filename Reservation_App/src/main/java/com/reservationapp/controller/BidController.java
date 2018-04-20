package com.reservationapp.controller;

import java.util.ArrayList;
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
import com.reservationapp.model.Message;
import com.reservationapp.model.Requisite;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.BidServiceImpl;
import com.reservationapp.service.impl.MessageServiceImpl;
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
	
	@Autowired
	private MessageServiceImpl messageService;
	
	
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
	
	@RequestMapping(value = "/order", method=RequestMethod.POST, consumes="application/json")
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
					
					Message message = new Message();
					message.setReciverID(bidd.getBiddersID());
					message.setSenderID(bidd.getBiddersID());
					message.setRead(false);
					message.setText("You can collect your item: "+req.getName()+" at reception. ");
					messageService.save(message);
					return new ResponseEntity<>(bidd, HttpStatus.CREATED);
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		
	}
	
	@RequestMapping(value="/acceptBid", method=RequestMethod.POST)
	public ResponseEntity<Bid> accept(@RequestBody Bid bid){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			Bid newBid =bidService.findHeighestBid(bid.getItemsID());
			//treba obavestiti sve o ovome;
			// i postaviti rekvizit na neaktivan
			Requisite re = reqService.findOne(newBid.getItemsID());
			Message message = new Message();
			message.setReciverID(newBid.getBiddersID());
			message.setSenderID(re.getCreator());
			message.setRead(false);
			message.setText("Your bid for "+re.getName()+" is selected as best, please proceed to make arrangements with seller. ");
			messageService.save(message);
			
			Message message1 = new Message();
			message1.setReciverID(re.getCreator());
			message1.setSenderID(newBid.getBiddersID());
			message1.setRead(false);
			message1.setText("Please proceed to make arrangements with buyer.(item: "+ re.getName() +")");
			messageService.save(message1);
			
			
			List<Bid> losers = bidService.findByItemsid(newBid.getItemsID());
			if(losers!=null) {
				List<Long> l2 = new ArrayList<Long>();
				for(Bid bi : losers) {
					if(!bi.getBiddersID().equals(re.getCreator()) && !bi.getBiddersID().equals(newBid.getBiddersID()) ) {
						if(!l2.contains(bi.getBiddersID())) {
							Message message3 = new Message();
							message3.setReciverID(bi.getBiddersID());
							message3.setSenderID(re.getCreator());
							message3.setRead(false);
							message3.setText("Your bid for item "+re.getName()+" was not accepted not accepted. ");
							messageService.save(message3);
							l2.add(bi.getBiddersID());
						}
					}
				}
			}
			
			re.setActive(false);
			reqService.update(re);
			return new ResponseEntity<>(newBid, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Bid> delete(@PathVariable Long id) {
		Bid deleted = bidService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

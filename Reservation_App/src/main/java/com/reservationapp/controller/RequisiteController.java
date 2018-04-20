package com.reservationapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.model.Requisite;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.RequisiteServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/requisite")
public class RequisiteController {
	@Autowired
	private RequisiteServiceImpl requisiteService;
	@Autowired
	private UserServiceImpl userService;
	
	
	@RequestMapping(value="/getRequisites", method = RequestMethod.GET)
	public ResponseEntity<List<Requisite>> getRequisites(){
		return new ResponseEntity<>(requisiteService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRequisites/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Requisite>> getRequisitesU(@PathVariable String type){
		return new ResponseEntity<>(requisiteService.findAllUserReqs(type), HttpStatus.OK);
	}
		
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Requisite> getRequisite(@PathVariable Long id) {
		Requisite requisite = requisiteService.findOne(id);
		if (requisite == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(requisite, HttpStatus.OK);
	}
	
	@RequestMapping(value="/addNewReq", method=RequestMethod.POST, consumes="application/json" )
	public ResponseEntity<Requisite> addRequisite(@RequestBody Requisite requisite){		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			try {
				if(user.getUserType().getName().equals("ADMINFZ")) {
					//admin fun zone
					requisite.setCreator(user.getId());
					requisite.setType("new");
					requisite.setPictureDB(null);
					requisite.setActive(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
				else if(user.getUserType().getName().equals("VISITOR")) {
					//obican
					requisite.setCreator(user.getId());
					requisite.setType("used");
					requisite.setPictureDB(null);
					requisite.setActive(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null; //nije ulogovan ili je nesto znjto
	}
	
	@RequestMapping(value="/addNewReqq", method=RequestMethod.POST)
	public ResponseEntity<Requisite> addRequisitee(@ModelAttribute Requisite requisite){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			try {
				if(user.getUserType().getName().equals("ADMINFZ")) {
					//admin fun zone
					requisite.setCreator(user.getId());
					requisite.setType("new");
					requisite.setPictureDB(requisite.getPicture().getBytes());
					requisite.setPicture(null);
					requisite.setActive(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
				else if(user.getUserType().getName().equals("VISITOR")) {
					//obican
					requisite.setCreator(user.getId());
					requisite.setType("used");
					requisite.setPictureDB(requisite.getPicture().getBytes());
					requisite.setPicture(null);
					requisite.setActive(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null; //nije ulogovan ili je nesto znjto
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Requisite> delete(@PathVariable Long id) {
		Requisite deleted = requisiteService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

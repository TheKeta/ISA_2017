package com.reservationapp.controller;

import java.util.Date;
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
	
	@RequestMapping(value="/getFreshRequisites", method = RequestMethod.GET)
	public ResponseEntity<List<Requisite>> getRequisitesF(){
		return new ResponseEntity<>(requisiteService.findAllUserFreshReqs(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/getRequisites/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<Requisite>> getRequisitesU(@PathVariable String type){
		return new ResponseEntity<>(requisiteService.findAllUserReqs(type), HttpStatus.OK);
	}
	
	@RequestMapping(value="/approve/{id}", method = RequestMethod.POST)
	public ResponseEntity<Requisite> updateA(@PathVariable String id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			if(user.getUserType().getName().equals("ADMINFZ")) {
				try {
					Long idd = Long.parseLong(id);
					Requisite req = requisiteService.findOne(idd);
					req.setApproved(true);
					return new ResponseEntity<>(requisiteService.update(req), HttpStatus.OK);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@RequestMapping(value="/disprove/{id}", method = RequestMethod.POST)
	public ResponseEntity<Requisite> updateD(@PathVariable String id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			if(user.getUserType().getName().equals("ADMINFZ")) {
				try {
					Long idd = Long.parseLong(id);
					Requisite req = requisiteService.findOne(idd);
					req.setActive(false);
					return new ResponseEntity<>(requisiteService.update(req), HttpStatus.OK);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
					Date today = new Date();
					if(!requisite.getEndDate().after(today)) {
						return null;
					}
					requisite.setCreator(user.getId());
					requisite.setType("new");
					requisite.setPictureDB(null);
					requisite.setActive(true);
					requisite.setApproved(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
				else if(user.getUserType().getName().equals("VISITOR")) {
					//obican
					Date today = new Date();
					if(!requisite.getEndDate().after(today)) {
						return null;
					}
					requisite.setCreator(user.getId());
					requisite.setType("used");
					requisite.setPictureDB(null);
					requisite.setActive(true);
					requisite.setApproved(false);
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
					Date today = new Date();
					if(!requisite.getEndDate().after(today)) {
						return null;
					}
					requisite.setCreator(user.getId());
					requisite.setType("new");
					requisite.setPictureDB(requisite.getPicture().getBytes());
					requisite.setPicture(null);
					requisite.setActive(true);
					requisite.setApproved(true);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
				else if(user.getUserType().getName().equals("VISITOR")) {
					//obican
					Date today = new Date();
					if(!requisite.getEndDate().after(today)) {
						return null;
					}
					requisite.setCreator(user.getId());
					requisite.setType("used");
					requisite.setPictureDB(requisite.getPicture().getBytes());
					requisite.setPicture(null);
					requisite.setActive(true);
					requisite.setApproved(false);
					Requisite requisiteType = requisiteService.save(requisite);
					return new ResponseEntity<>(requisiteType, HttpStatus.OK);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null; //nije ulogovan ili je nesto znjto
	}
	
	@RequestMapping(value="/editReqq", method=RequestMethod.POST)
	public ResponseEntity<Requisite> editRequisitee(@ModelAttribute Requisite requisite){		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			try {
				if(user.getUserType().getName().equals("ADMINFZ")) {
					//admin fun zone
					Requisite oldReq = requisiteService.findOne(requisite.getId());
					if(requisite.getName()!=null)
						oldReq.setName(requisite.getName());
					if(requisite.getDescription()!= null)
						oldReq.setDescription(requisite.getDescription());
					if(requisite.getEndDate()!= null) {
						Date today = new Date();
						if(!requisite.getEndDate().after(today))
							return null;
						oldReq.setEndDate(requisite.getEndDate());
					}
					if(requisite.getPrice()>0 )
						oldReq.setPrice(requisite.getPrice());
					oldReq.setPictureDB(requisite.getPicture().getBytes());
					oldReq.setApproved(true);
					
					return new ResponseEntity<>(requisiteService.update(oldReq), HttpStatus.OK);
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null; //nije ulogovan ili je nesto znjto
	}
	
	@RequestMapping(value="/editReq", method=RequestMethod.POST, consumes="application/json" )
	public ResponseEntity<Requisite> editRequisite(@RequestBody Requisite requisite){	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			try {
				if(user.getUserType().getName().equals("ADMINFZ")) {
					//admin fun zone
					System.out.println("AAAA   "+requisite.getName()+requisite.getId());
					Requisite oldReq = requisiteService.findOne(requisite.getId());
					if(requisite.getName()!=null)
						oldReq.setName(requisite.getName());
					if(requisite.getDescription()!= null)
						oldReq.setDescription(requisite.getDescription());
					if(requisite.getEndDate()!= null) {
						Date today = new Date();
						if(!requisite.getEndDate().after(today))
							return null;
						oldReq.setEndDate(requisite.getEndDate());
					}
					if(requisite.getPrice()>0 )
						oldReq.setPrice(requisite.getPrice());
					
					oldReq.setApproved(true);
					return new ResponseEntity<>(requisiteService.update(oldReq), HttpStatus.OK);
					
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return null; //nije ulogovan ili je nesto znjto
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Requisite> delete(@PathVariable Long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		if(user!=null) {
			try {
				if(user.getUserType().getName().equals("ADMINFZ")) {
					Requisite deleted = requisiteService.delete(id);
					return new ResponseEntity<>(deleted, HttpStatus.OK);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}

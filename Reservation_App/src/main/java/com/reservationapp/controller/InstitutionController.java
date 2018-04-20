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

import com.reservationapp.DTOs.InstitutionRated;
import com.reservationapp.DTOs.InstitutionUsers;
import com.reservationapp.model.Institution;
import com.reservationapp.model.InstitutionRating;
import com.reservationapp.model.InstitutionType;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.InstitutionRatingServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;
import com.reservationapp.service.impl.InstitutionTypeServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {

	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private InstitutionRatingServiceImpl institutionRatingService;
	
	@Autowired
	private InstitutionTypeServiceImpl institutionTypeService; 
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/getInstitutions/{type}", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionRated>> getInstitutions(@PathVariable String type){
		List<Institution> tempList = institutionService.searchByType(type);
		if(tempList == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		List<InstitutionRated> ratedList = new ArrayList<InstitutionRated>();
		for(Institution inst : tempList) {
			ratedList.add(new InstitutionRated(inst, institutionRatingService.calculateRating(institutionRatingService.searchByInstitution(inst))));
		}
		if(ratedList.size() > 0){
			return new ResponseEntity<>(ratedList, HttpStatus.OK);
		}else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionUsers> getInstitution(@PathVariable Long id) {
		Institution institution = institutionService.findOne(id);
		if (institution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(new InstitutionUsers(institution, userService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Institution> addInstitution(@RequestBody Institution institution){
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Institution newInstitution = institutionService.save(institution);;
		return new ResponseEntity<>(newInstitution, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Institution> delete(@PathVariable Long id) {
		Institution attempt = institutionService.findOne(id);
		
		if (attempt == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		Institution deleted = institutionService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	} 
	

	@RequestMapping(value ="/search/{type}/{searchText}", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionRated>> getSearchResult(@PathVariable String type, @PathVariable String searchText){
		InstitutionType instType = institutionTypeService.findByName(type);
		List<Institution> institutions = institutionService.searchByNameAndType(instType, searchText);
		List<InstitutionRated> ratedList = new ArrayList<InstitutionRated>();
		for(Institution inst : institutions) {
			ratedList.add(new InstitutionRated(inst, institutionRatingService.calculateRating(institutionRatingService.searchByInstitution(inst))));
		}
		return new ResponseEntity<>(ratedList, HttpStatus.OK);
	}

	
	@RequestMapping(value ="/rate/{id}/{rating}", method = RequestMethod.GET)
	public ResponseEntity<InstitutionRating> rate(@PathVariable Long id, @PathVariable int rating){
		Institution institution = institutionService.findOne(id);
		if(institution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		InstitutionRating alreadyRated = institutionRatingService.findByInstitutionAndUser(institution, loggedUser());
		if(alreadyRated != null) {
			alreadyRated.setRating(rating);
			institutionRatingService.save(alreadyRated);
			return new ResponseEntity<>(alreadyRated, HttpStatus.OK);
		}
		InstitutionRating newRating = new InstitutionRating(loggedUser(), rating, institution);
		institutionRatingService.save(newRating);
		return new ResponseEntity<>(newRating, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

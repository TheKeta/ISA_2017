package com.reservationapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.InstitutionRated;
import com.reservationapp.model.Institution;
import com.reservationapp.service.impl.InstitutionRatingServiceImpl;
import com.reservationapp.service.impl.InstitutionServiceImpl;

@RestController
@RequestMapping(value = "/institution")
public class InstitutionController {

	@Autowired
	private InstitutionServiceImpl institutionService;
	
	@Autowired
	private InstitutionRatingServiceImpl institutionRatingService;
	
	
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
		return new ResponseEntity<>(ratedList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Institution> getInstitution(@PathVariable Long id) {
		Institution institution = institutionService.findOne(id);
		if (institution == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(institution, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Institution> addInstitution(@RequestBody Institution institution){
		Institution newInstitution = institutionService.save(institution);
		return new ResponseEntity<>(newInstitution, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Institution> delete(@PathVariable Long id) {
		Institution deleted = institutionService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	} 
	
	@RequestMapping(value ="/search/{type}/{searchText}", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionRated>> getSearchResult(@PathVariable String type, @PathVariable String searchText){
		List<Institution> tempList = institutionService.searchByType(type);
		if(tempList == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		List<InstitutionRated> ratedList = new ArrayList<InstitutionRated>();
		for(Institution inst : tempList) {
			ratedList.add(new InstitutionRated(inst, institutionRatingService.calculateRating(institutionRatingService.searchByInstitution(inst))));
		}
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}

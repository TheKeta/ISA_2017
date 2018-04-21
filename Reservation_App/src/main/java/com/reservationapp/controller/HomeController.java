package com.reservationapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.InstitutionDTO;
import com.reservationapp.model.History;
import com.reservationapp.model.Institution;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.HistoryServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private HistoryServiceImpl historyService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping(value="/getHistory", method = RequestMethod.GET)
	public ResponseEntity<List<InstitutionDTO>> getHistory(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		User user = userService.findOneByEmail(auth.getName());
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		History history = historyService.findByUser(user);
		List<InstitutionDTO> res = new ArrayList<InstitutionDTO>();
		
		if(history == null)
			return new ResponseEntity<>(res, HttpStatus.OK);
		
		for(Institution i : history.getInstitutions()){
			res.add(new InstitutionDTO(i));
		}
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
}

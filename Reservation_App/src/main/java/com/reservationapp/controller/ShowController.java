package com.reservationapp.controller;

import java.io.IOException;
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

import com.reservationapp.DTOs.ShowGenresTypes;
import com.reservationapp.model.Show;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.GenreServiceImpl;
import com.reservationapp.service.impl.ShowServiceImpl;
import com.reservationapp.service.impl.ShowTypeServiceImpl;
import com.reservationapp.service.impl.UserServiceImpl;

@RestController
@RequestMapping(value = "/show")
public class ShowController {

	@Autowired
	private ShowServiceImpl showService;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private GenreServiceImpl genreService;
	
	@Autowired
	private ShowTypeServiceImpl showTypeService;
	
	@RequestMapping(value="/getShows", method = RequestMethod.GET)
	public ResponseEntity<List<Show>> getShows(){
		return new ResponseEntity<>(showService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ShowGenresTypes> getShow(@PathVariable Long id) {
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Show show = showService.findOne(id);
		if (show == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(new ShowGenresTypes(show, genreService.findAll(), showTypeService.findAll()), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Show> addShow(@ModelAttribute Show show){
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			show.setPictureDB(show.getPicture().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Show newShow = showService.save(show);
		
		return new ResponseEntity<>(newShow, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Show> delete(@PathVariable Long id) {
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		Show deleted = showService.delete(id);
		
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
	
	private User loggedUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOneByEmail(auth.getName());
		return user;
	}
}

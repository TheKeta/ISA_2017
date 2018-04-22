package com.reservationapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.reservationapp.DTOs.ShowGenresTypes;
import com.reservationapp.DTOs.ShowRated;
import com.reservationapp.model.Genre;
import com.reservationapp.model.Reservation;
import com.reservationapp.model.Show;
import com.reservationapp.model.ShowRating;
import com.reservationapp.model.ShowType;
import com.reservationapp.model.User;
import com.reservationapp.service.impl.GenreServiceImpl;
import com.reservationapp.service.impl.ReservationServiceImpl;
import com.reservationapp.service.impl.ShowRatingServiceImpl;
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
	
	@Autowired
	private ShowRatingServiceImpl showRatingService;
	
	@Autowired
	private ReservationServiceImpl reservationService;
	
	
	@RequestMapping(value="/getShows", method = RequestMethod.GET)
	public ResponseEntity<List<ShowRated>> getShows(){
		List<ShowRated> rated = new ArrayList<ShowRated>();
		
		for(Show s : showService.findAll()){
			List<ShowRating> ratings = showRatingService.findByShow(s);
			double rating = 0;
			for(ShowRating r : ratings){
				rating += r.getRating();
			}
			rated.add(new ShowRated(s, rating));
		}
		return new ResponseEntity<>(rated, HttpStatus.OK);
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
	
	@RequestMapping(value = "/rate/{id}/{rating}", method = RequestMethod.GET)
	public ResponseEntity<ShowRating> rate(@PathVariable Long id, @PathVariable int rating) {
		if(loggedUser() == null){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		Reservation reservation = reservationService.findOne(id);
		Date date = new Date();
		if(reservation.getEvent().getEventDate().after(date)){
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		Show show = null;
		if(reservation != null){
			show = reservation.getEvent().getShow();
		}
		
		ShowRating showRating = showRatingService.findByShowAndUser(show, loggedUser());
		if(showRating != null){
			showRating.setRating(rating);
			showRatingService.save(showRating);
			return new ResponseEntity<>(showRating, HttpStatus.OK);
		}
		ShowRating newRating = new ShowRating(loggedUser(), rating, show);
		showRatingService.save(newRating);
		
		return new ResponseEntity<>(newRating, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/add/{genreId}/{typeId}", method=RequestMethod.POST)
	public ResponseEntity<Show> addShow(@ModelAttribute Show show, @PathVariable Long genreId, @PathVariable Long typeId){
		Genre genre = genreService.findOne(genreId);
		ShowType type = showTypeService.findOne(typeId);
		if(!loggedUser().getUserType().getName().equals("ADMIN")){
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		try {
			if(show.getPicture() == null){
				Show s = null;
				if(show.getId() != null){
					s = showService.findOne(show.getId());	
				}
				if(s != null){
					show.setPictureDB(s.getPictureDB());	
				}
			}else{
				show.setPictureDB(show.getPicture().getBytes());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		show.setGenre(genre);
		show.setType(type);
		show.setPicture(null);
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

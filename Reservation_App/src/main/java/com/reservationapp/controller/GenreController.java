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

import com.reservationapp.model.Genre;
import com.reservationapp.service.impl.GenreServiceImpl;

@RestController
@RequestMapping(value = "/genre")
public class GenreController {
	
	@Autowired
	private GenreServiceImpl genreService;
	
	
	@RequestMapping(value="/getGenres", method = RequestMethod.GET)
	public ResponseEntity<List<Genre>> getEvents(){
		return new ResponseEntity<>(genreService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Genre> getGenre(@PathVariable Long id) {
		Genre genre = genreService.findOne(id);
		if (genre == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(genre, HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
		Genre newGenre = genreService.save(genre);
		return new ResponseEntity<>(newGenre, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Genre> delete(@PathVariable Long id) {
		Genre deleted = genreService.delete(id);
		return new ResponseEntity<>(deleted, HttpStatus.OK);
	}
}

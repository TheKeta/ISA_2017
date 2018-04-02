package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Genre;

public interface GenreService {

	Genre findOne(Long id);

	List<Genre> findAll();
	
	Genre save(Genre genre);
	
	List<Genre> save(List<Genre> genre);
	
	Genre delete(Long id);
	
	void delete(List<Long> ids);
}

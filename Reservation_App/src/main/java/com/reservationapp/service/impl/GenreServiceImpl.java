package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Genre;
import com.reservationapp.repository.GenreRepository;
import com.reservationapp.service.GenreService;


@Service
@Transactional
public class GenreServiceImpl implements GenreService{

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public Genre findOne(Long id) {
		return genreRepository.findById(id).get();
	}
	
	@Override
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	@Override
	public Genre save(Genre genre) {
		return genreRepository.save(genre);
	}

	@Override
	public List<Genre> save(List<Genre> genre) {
		return genreRepository.saveAll(genre);
	}

	@Override
	public Genre delete(Long id) {
		Genre genre = genreRepository.findById(id).get();
		if(genre == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		genreRepository.delete(genre);
		return genre;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}

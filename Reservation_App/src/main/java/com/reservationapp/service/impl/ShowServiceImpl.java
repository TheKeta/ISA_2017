package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.Show;
import com.reservationapp.repository.ShowRepository;
import com.reservationapp.service.ShowService;

@Service
@Transactional
public class ShowServiceImpl implements ShowService{

	@Autowired
	private ShowRepository showRepository;

	@Override
	public Show findOne(Long id) {
		return showRepository.findById(id).get();
	}
	
	@Override
	public List<Show> findAll() {
		return showRepository.findAll();
	}

	@Override
	public Show save(Show show) {
		return showRepository.save(show);
	}

	@Override
	public List<Show> save(List<Show> show) {
		return showRepository.saveAll(show);
	}

	@Override
	public Show delete(Long id) {
		Show show = showRepository.findById(id).get();
		if(show == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		showRepository.delete(show);
		return show;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
	
}

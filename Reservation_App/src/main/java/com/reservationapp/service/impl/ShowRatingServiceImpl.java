package com.reservationapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reservationapp.model.ShowRating;
import com.reservationapp.repository.ShowRatingRepository;
import com.reservationapp.service.ShowRatingService;

@Service
@Transactional
public class ShowRatingServiceImpl implements ShowRatingService{

	@Autowired
	private ShowRatingRepository showRatingRepository;

	@Override
	public ShowRating findOne(Long id) {
		return showRatingRepository.findById(id).get();
	}
	
	@Override
	public List<ShowRating> findAll() {
		return showRatingRepository.findAll();
	}

	@Override
	public ShowRating save(ShowRating showRating) {
		return showRatingRepository.save(showRating);
	}

	@Override
	public List<ShowRating> save(List<ShowRating> showRating) {
		return showRatingRepository.saveAll(showRating);
	}

	@Override
	public ShowRating delete(Long id) {
		ShowRating showRating = showRatingRepository.findById(id).get();
		if(showRating == null){
			throw new IllegalArgumentException("Tried to delete"
					+ "non-existant user");
		}
		showRatingRepository.delete(showRating);
		return showRating;
	}

	@Override
	public void delete(List<Long> ids) {
		for(Long id : ids){
			this.delete(id);
		}
	}
}

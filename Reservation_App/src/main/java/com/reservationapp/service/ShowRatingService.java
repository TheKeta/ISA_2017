package com.reservationapp.service;

import java.util.List;

import com.reservationapp.model.Show;
import com.reservationapp.model.ShowRating;
import com.reservationapp.model.User;

public interface ShowRatingService {

	ShowRating findOne(Long id);

	List<ShowRating> findAll();
	
	ShowRating save(ShowRating showRating);
	
	List<ShowRating> save(List<ShowRating> showRatings);
	
	ShowRating delete(Long id);
	
	void delete(List<Long> ids);
	
	ShowRating findByShowAndUser(Show show, User user);
	
	List<ShowRating> findByShow(Show show);
}

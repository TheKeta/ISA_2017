package com.reservationapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reservationapp.model.Show;
import com.reservationapp.model.ShowRating;
import com.reservationapp.model.User;

public interface ShowRatingRepository extends JpaRepository<ShowRating, Long>{

	public ShowRating findByShowAndUser(Show show, User user);
	
	public List<ShowRating> findByShow(Show show);

}
